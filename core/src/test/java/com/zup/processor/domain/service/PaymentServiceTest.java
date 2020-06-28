package com.zup.processor.domain.service;

import static java.util.Optional.ofNullable;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.zup.processor.domain.dto.PaymentDTO;
import com.zup.processor.domain.entity.CreditCard;
import com.zup.processor.domain.entity.Payment;
import com.zup.processor.domain.entity.Transaction;
import com.zup.processor.domain.enumerations.CurrencyEnum;
import com.zup.processor.domain.enumerations.CurrencyEnumDTO;
import com.zup.processor.domain.exception.message.AlreadySavedException;
import com.zup.processor.domain.exception.message.NotFoundedException;
import com.zup.processor.domain.mapper.PaymentMapper;
import com.zup.processor.infrastructure.repository.PaymentRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

  @InjectMocks PaymentService service;
  @Mock PaymentRepository repository;
  @Mock PaymentMapper mapper;

  @Test
  void mustReturnAllPaymentsWhenListAll() {

    var paymentUuid = UUID.fromString("4002-8922-2490-9141-2222");
    var transactionRandomUuid = UUID.randomUUID();
    var creditCardRandomUuid = UUID.randomUUID();
    var expectedResponse = buildDTO(paymentUuid, transactionRandomUuid, creditCardRandomUuid);

    when(repository.findAll())
        .thenReturn((buildEntity(paymentUuid, transactionRandomUuid, creditCardRandomUuid)));
    when(mapper.map(buildEntity(paymentUuid, transactionRandomUuid, creditCardRandomUuid).get(0)))
        .thenReturn(buildDTO(paymentUuid, transactionRandomUuid, creditCardRandomUuid).get(0));
    when(mapper.map(buildEntity(paymentUuid, transactionRandomUuid, creditCardRandomUuid).get(1)))
        .thenReturn(buildDTO(paymentUuid, (transactionRandomUuid), creditCardRandomUuid).get(1));
    when(mapper.map(buildEntity(paymentUuid, transactionRandomUuid, creditCardRandomUuid).get(2)))
        .thenReturn(buildDTO(paymentUuid, transactionRandomUuid, creditCardRandomUuid).get(2));
    var response = service.listAll();

    assertThat(response, is(equalTo(expectedResponse)));
  }

  @Test
  void mustReturnOneWhenFindById() throws NotFoundedException {
    var paymentUuid = UUID.fromString("4002-8922-2490-9141-2222");
    var transactionRandomUuid = UUID.randomUUID();
    var creditCardUuid = UUID.randomUUID();
    var description = "New Product";
    var payment = buildPayment(paymentUuid, transactionRandomUuid, creditCardUuid, description);
    var expectedResponse =
        buildPaymentDTO(paymentUuid, transactionRandomUuid, creditCardUuid, description);

    when(repository.findById(paymentUuid)).thenReturn(ofNullable(payment));
    when(mapper.map(payment)).thenReturn(expectedResponse);
    var response = service.findOneById(paymentUuid);

    assertThat(response, is(equalTo(expectedResponse)));
  }

  @Test
  void mustPerformSaveAndReturnResponse() throws AlreadySavedException {

    var paymentUuid = UUID.fromString("4002-8922-2490-9141-2222");
    var transactionRandomUuid = UUID.randomUUID();
    var creditCardUuid = UUID.randomUUID();
    var description = "New Product";
    var payment = buildPayment(paymentUuid, transactionRandomUuid, creditCardUuid, description);
    var expectedResponse =
        buildPaymentDTO(paymentUuid, transactionRandomUuid, creditCardUuid, description);

    when(mapper.map(expectedResponse)).thenReturn(payment);
    when(mapper.map(payment)).thenReturn(expectedResponse);
    when(repository.save(payment)).thenReturn(payment);
    var response = service.save(expectedResponse);

    assertThat(response, is(equalTo(expectedResponse)));
  }

  @Test
  void mustThrowAlreadySavedResponse() {

    var paymentUuid = UUID.fromString("4002-8922-2490-9141-2222");
    var transactionRandomUuid = UUID.randomUUID();
    var creditCardUuid = UUID.randomUUID();
    var description = "New Product";
    var matcher = ExampleMatcher.matching().withIgnorePaths("paymentId");
    var payment = buildPayment(paymentUuid, transactionRandomUuid, creditCardUuid, description);
    var givenParam =
        buildPaymentDTO(paymentUuid, transactionRandomUuid, creditCardUuid, description);

    when(mapper.map(givenParam)).thenReturn(payment);
    when(repository.exists(Example.of(payment, matcher))).thenReturn(true);
    AlreadySavedException exception =
        assertThrows(AlreadySavedException.class, () -> service.save(givenParam));

    assertThat(exception.getMessage(), is(equalTo("PAYMENT_ALREADY_SAVED")));
  }

  @Test
  void mustPerformUpdateAndReturnResponseWithOtherDescription() throws NotFoundedException {
    var paymentUuid = UUID.fromString("4002-8922-2490-9141-2222");
    var transactionRandomUuid = UUID.randomUUID();
    var creditCardUuid = UUID.randomUUID();
    var thenDescription = "New Product";
    var pastDescription = "Old Product";
    var payment = buildPayment(paymentUuid, transactionRandomUuid, creditCardUuid, pastDescription);
    var expectedResponse =
        buildPaymentDTO(paymentUuid, transactionRandomUuid, creditCardUuid, thenDescription);

    when(mapper.map(expectedResponse)).thenReturn(payment);
    when(mapper.map(payment)).thenReturn(expectedResponse);
    when(repository.save(payment)).thenReturn(payment);
    when(repository.existsById(expectedResponse.getPaymentId())).thenReturn(true);
    var response = service.update(expectedResponse);

    assertThat(response, is(equalTo(expectedResponse)));
  }

  @Test
  void mustPerformDeleteAndReturnResponseWithDeletedDate() throws NotFoundedException {
    var paymentUuid = UUID.fromString("4002-8922-2490-9141-2222");
    var transactionRandomUuid = UUID.randomUUID();
    var creditCardUuid = UUID.randomUUID();
    var thenDescription = "New Product";
    var deletedDate = LocalDateTime.now();
    var givenPaymentDTO =
        buildPaymentDTO(paymentUuid, transactionRandomUuid, creditCardUuid, thenDescription);
    Payment payment =
        buildPayment(paymentUuid, transactionRandomUuid, creditCardUuid, thenDescription);
    payment.setDeletedDate(deletedDate);
    Payment deletedPayment =
        buildPayment(paymentUuid, transactionRandomUuid, creditCardUuid, thenDescription);
    deletedPayment.setDeletedDate(deletedDate);
    PaymentDTO expectedResponse =
        buildPaymentDTO(paymentUuid, transactionRandomUuid, creditCardUuid, thenDescription);
    expectedResponse.setDeletedDate(deletedDate);

    when(mapper.map(givenPaymentDTO)).thenReturn(payment);
    when(mapper.map(payment)).thenReturn(expectedResponse);
    doReturn(deletedPayment).when(repository).save(any());
    doReturn(true).when(repository).existsById(UUID.fromString(String.valueOf(paymentUuid)));
    var response = service.delete(givenPaymentDTO);

    assertThat(response, is(equalTo(expectedResponse)));
  }

  @Test()
  void mustRespondWithNotFoundExceptionWhenFindByIdGivenNull() {
    assertThrows(NotFoundedException.class, () -> service.findOneById(null));
  }

  @Test()
  void mustRespondWithNotFoundExceptionWhenUpdateGivenNull() {
    assertThrows(NotFoundedException.class, () -> service.update(null));
  }

  @Test()
  void mustRespondWithNotFoundExceptionWhenDeleteGivenNull() {
    assertThrows(NotFoundedException.class, () -> service.delete(null));
  }

  @Test()
  void mustRespondWithNotFoundExceptionWhenFindById() {
    var uuid = UUID.randomUUID();

    assertThrows(NotFoundedException.class, () -> service.findOneById(uuid));
  }

  @Test()
  void mustRespondWithNotFoundExceptionWhenUpdate() {
    var uuid = UUID.randomUUID();

    assertThrows(
        NotFoundedException.class,
        () -> service.update(PaymentDTO.builder().paymentId(uuid).build()));
  }

  @Test()
  void mustRespondWithNotFoundExceptionWhenDelete() {
    var uuid = UUID.randomUUID();

    assertThrows(
        NotFoundedException.class,
        () -> service.delete(PaymentDTO.builder().paymentId(uuid).build()));
  }

  private List<Payment> buildEntity(UUID uuid, UUID transactionUuid, UUID creditCardUuid) {
    return ImmutableList.of(
        Payment.builder()
            .paymentId(uuid)
            .productId("2384")
            .currency(CurrencyEnum.BRL)
            .description("Payment One")
            .money(BigDecimal.TEN)
            .creditCard(CreditCard.builder().creditCardId(creditCardUuid).build())
            .transaction(Transaction.builder().transactionId(transactionUuid).build())
            .dueDate(LocalDate.EPOCH)
            .build(),
        Payment.builder()
            .paymentId(uuid)
            .productId("2385")
            .currency(CurrencyEnum.BRL)
            .description("Payment Two")
            .money(BigDecimal.TEN)
            .creditCard(CreditCard.builder().creditCardId(creditCardUuid).build())
            .transaction(Transaction.builder().transactionId(transactionUuid).build())
            .dueDate(LocalDate.EPOCH)
            .build(),
        Payment.builder()
            .paymentId(uuid)
            .productId("2386")
            .currency(CurrencyEnum.BRL)
            .description("Payment Three")
            .money(BigDecimal.TEN)
            .creditCard(CreditCard.builder().creditCardId(creditCardUuid).build())
            .transaction(Transaction.builder().transactionId(transactionUuid).build())
            .dueDate(LocalDate.EPOCH)
            .build());
  }

  private List<PaymentDTO> buildDTO(UUID paymentUuid, UUID transactionUuid, UUID creditCardUuid) {
    return ImmutableList.of(
        PaymentDTO.builder()
            .paymentId(paymentUuid)
            .productId("2384")
            .description("New Product One")
            .dueDate(LocalDate.EPOCH)
            .money(BigDecimal.TEN)
            .currency(CurrencyEnumDTO.BRL)
            .creditCard(creditCardUuid)
            .transaction(transactionUuid)
            .build(),
        PaymentDTO.builder()
            .paymentId(paymentUuid)
            .productId("2385")
            .description("New Product Two")
            .dueDate(LocalDate.EPOCH)
            .money(BigDecimal.TEN)
            .currency(CurrencyEnumDTO.BRL)
            .creditCard(creditCardUuid)
            .transaction(transactionUuid)
            .build(),
        PaymentDTO.builder()
            .paymentId(paymentUuid)
            .productId("2386")
            .description("New Product Three")
            .dueDate(LocalDate.EPOCH)
            .money(BigDecimal.TEN)
            .currency(CurrencyEnumDTO.BRL)
            .creditCard(creditCardUuid)
            .transaction(transactionUuid)
            .build());
  }

  private Payment buildPayment(
      UUID paymentUuid, UUID transactionRandomUuid, UUID creditCardUuid, String description) {
    return Payment.builder()
        .paymentId(paymentUuid)
        .productId("2384")
        .currency(CurrencyEnum.BRL)
        .description(description)
        .money(BigDecimal.TEN)
        .creditCard(CreditCard.builder().creditCardId(transactionRandomUuid).build())
        .transaction(Transaction.builder().transactionId(creditCardUuid).build())
        .dueDate(LocalDate.EPOCH)
        .build();
  }

  private PaymentDTO buildPaymentDTO(
      UUID paymentUuid, UUID transactionUuid, UUID creditCardUuid, String description) {
    return PaymentDTO.builder()
        .paymentId(paymentUuid)
        .productId("2384")
        .description(description)
        .dueDate(LocalDate.EPOCH)
        .money(BigDecimal.TEN)
        .currency(CurrencyEnumDTO.BRL)
        .creditCard(creditCardUuid)
        .transaction(transactionUuid)
        .build();
  }
}
