package com.zup.processor.domain.service;

import static java.util.Optional.of;
import static java.util.stream.Collectors.toList;

import com.zup.processor.domain.dto.PaymentDTO;
import com.zup.processor.domain.dto.TransactionDTO;
import com.zup.processor.domain.entity.Payment;
import com.zup.processor.domain.mapper.PaymentMapper;
import com.zup.processor.infrastructure.message.producer.PaymentMessageProducer;
import com.zup.processor.infrastructure.repository.PaymentRepository;
import com.zup.processor.infrastructure.service.PaypalPaymentService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log
public class PaymentService implements CrudService<PaymentDTO> {

  private final PaymentMapper mapper;
  private final PaymentRepository repository;
  private final PaymentMessageProducer producer;
  private final PaypalPaymentService apiService;
  private final TransactionService transactionService;

  @Override
  public List<PaymentDTO> listAll() {
    return repository.findAll().parallelStream().map(mapper::map).collect(toList());
  }

  @Override
  public PaymentDTO save(PaymentDTO dto) {
    return null;
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

  public void duePayment(PaymentDTO paymentDTO) throws IOException {
    log.info(String.format("Message of Payment %s", paymentDTO));
    TransactionDTO payout = apiService.createPayout(paymentDTO);
    TransactionDTO transactionDTO = transactionService.save(payout);
    log.info(String.format("Saved Transaction %s", transactionDTO));
  }

  private Function<Payment, Payment> sentDuePaymentWhenDueToday(PaymentDTO dto) {
    return payment1 ->
        of(payment1)
            .map(Payment::getDueDate)
            .filter(isDueDate())
            .map(duePayment(dto, payment1))
            .orElse(payment1);
  }

  private Function<LocalDate, Payment> duePayment(PaymentDTO dto, Payment payment1) {
    return localDate -> {
      producer.sentMessage(dto);
      return payment1;
    };
  }

  private Predicate<LocalDate> isDueDate() {
    return localDate -> localDate.isEqual(LocalDate.now());
  }
}
