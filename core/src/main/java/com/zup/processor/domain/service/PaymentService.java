package com.zup.processor.domain.service;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

import com.zup.processor.domain.dto.PaymentDTO;
import com.zup.processor.domain.entity.Payment;
import com.zup.processor.domain.exception.message.AlreadySavedException;
import com.zup.processor.domain.exception.message.NotFoundedException;
import com.zup.processor.domain.mapper.PaymentMapper;
import com.zup.processor.infrastructure.messages.producer.PaymentMessageProducer;
import com.zup.processor.infrastructure.repository.PaymentRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log
public class PaymentService implements CrudService<PaymentDTO> {

  private final PaymentMapper mapper;
  private final PaymentRepository repository;
  private final PaymentMessageProducer producer;

  @Override
  public List<PaymentDTO> listAll() {
    return repository.findAll().parallelStream().map(mapper::map).collect(Collectors.toList());
  }

  @Override
  public PaymentDTO findOneById(UUID uuid) throws NotFoundedException {
    return repository.findById(uuid).map(mapper::map).orElseThrow(NotFoundedException::new);
  }

  @Override
  public PaymentDTO save(PaymentDTO dto) throws AlreadySavedException {

    var payment = mapper.map(dto);

    return of(repository)
        .filter(onlyWhenDTOIsNotNull(dto))
        .filter(onlyWhenNotAlreadySavedPayment(payment))
        .map(save(payment))
        .map(mapper::map)
        .orElseThrow(AlreadySavedException::new);
  }

  @Override
  public PaymentDTO update(PaymentDTO dto) throws NotFoundedException {
    return ofNullable(dto)
        .filter(ifExistsOnDatabase())
        .map(mapper::map)
        .map(repository::save)
        .map(mapper::map)
        .orElseThrow(NotFoundedException::new);
  }

  @Override
  public PaymentDTO delete(PaymentDTO dto) throws NotFoundedException {
    return ofNullable(dto)
        .filter(ifExistsOnDatabase())
        .map(mapper::map)
        .map(setPaymentDeleted())
        .map(repository::save)
        .map(mapper::map)
        .orElseThrow(NotFoundedException::new);
  }

  public void duePayment(PaymentDTO paymentDTO) {
    log.info(String.format("Message of Payment %s", paymentDTO));
  }

  public void existsPaymentsMessageSender() {
    Payment payment = Payment.builder().dueDate(LocalDate.now()).build();
    Example<Payment> paymentExample = Example.of(payment);
    repository
        .findAll(paymentExample)
        .parallelStream()
        .map(mapper::map)
        .forEachOrdered(producer::sentMessage);
  }

  private Predicate<PaymentDTO> ifExistsOnDatabase() {
    return optDTO -> repository.existsById(optDTO.getPaymentId());
  }

  private Function<Payment, Payment> setPaymentDeleted() {
    return payment -> {
      Payment clonedPayment = ((Payment) SerializationUtils.clone(payment));
      clonedPayment.setDeletedDate(LocalDateTime.now());
      return clonedPayment;
    };
  }

  private Predicate<PaymentRepository> onlyWhenDTOIsNotNull(PaymentDTO dto) {
    return paymentRepository -> Objects.nonNull(dto);
  }

  private Function<PaymentRepository, Payment> save(Payment payment) {
    return paymentRepository -> paymentRepository.save(payment);
  }

  private Predicate<PaymentRepository> onlyWhenNotAlreadySavedPayment(Payment payment) {
    return paymentRepository -> !paymentRepository.exists(getExample(payment));
  }

  private Example<Payment> getExample(Payment payment) {
    var matcher = ExampleMatcher.matching().withIgnorePaths("paymentId");
    return Example.of(payment, matcher);
  }
}
