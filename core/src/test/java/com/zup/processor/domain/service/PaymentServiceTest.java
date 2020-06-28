package com.zup.processor.domain.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.zup.processor.domain.dto.PaymentDTO;
import com.zup.processor.domain.entity.CreditCard;
import com.zup.processor.domain.entity.Payment;
import com.zup.processor.domain.entity.Transaction;
import com.zup.processor.domain.enumerations.CurrencyEnum;
import com.zup.processor.domain.enumerations.CurrencyEnumDTO;
import com.zup.processor.domain.mapper.PaymentMapper;
import com.zup.processor.infrastructure.repository.PaymentRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
  void mustPerformSaveAndReturnResponse() {

    var paymentUuid = UUID.fromString("4002-8922-2490-9141-2222");
    var transactionRandomUuid = UUID.randomUUID();
    var creditCardUuid = UUID.randomUUID();
    var description = "New Product";
    var expectedResponse =
        buildPaymentDTO(paymentUuid, transactionRandomUuid, creditCardUuid, description);

    var response = service.save(expectedResponse);

    assertThat(response, is(equalTo(null)));
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
