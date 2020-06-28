package com.zup.processor.domain.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.zup.processor.domain.dto.TransactionDTO;
import com.zup.processor.domain.entity.CreditCard;
import com.zup.processor.domain.entity.Payment;
import com.zup.processor.domain.entity.Transaction;
import com.zup.processor.domain.mapper.TransactionMapper;
import com.zup.processor.infrastructure.repository.TransactionRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

  @InjectMocks TransactionService service;
  @Mock TransactionRepository repository;
  @Mock TransactionMapper mapper;

  @Test
  void mustReturnAllTransactionsWhenListAll() {

    var uuid = UUID.fromString("4002-8922-2490-9141-2222");
    var transactionRandomUuid = UUID.randomUUID();
    var creditCardRandomUuid = UUID.randomUUID();
    var chargedDate = LocalDate.of(2020, 12, 19);
    var expectedResponse = buildDTO(uuid, chargedDate, transactionRandomUuid, creditCardRandomUuid);

    when(repository.findAll())
        .thenReturn((buildEntity(uuid, chargedDate, transactionRandomUuid, creditCardRandomUuid)));
    when(mapper.map(
            buildEntity(uuid, chargedDate, transactionRandomUuid, creditCardRandomUuid).get(0)))
        .thenReturn(
            buildDTO(uuid, chargedDate, transactionRandomUuid, creditCardRandomUuid).get(0));
    when(mapper.map(
            buildEntity(uuid, chargedDate, transactionRandomUuid, creditCardRandomUuid).get(1)))
        .thenReturn(
            buildDTO(uuid, chargedDate, transactionRandomUuid, creditCardRandomUuid).get(1));
    when(mapper.map(
            buildEntity(uuid, chargedDate, transactionRandomUuid, creditCardRandomUuid).get(2)))
        .thenReturn(
            buildDTO(uuid, chargedDate, transactionRandomUuid, creditCardRandomUuid).get(2));
    var response = service.listAll();

    assertThat(response, is(equalTo(expectedResponse)));
  }

  @Test
  void mustPerformSaveAndReturnResponse() {

    var uuid = UUID.fromString("4002-8922-2490-9141-2222");
    var transactionRandomUuid = UUID.randomUUID();
    var creditCardUuid = UUID.randomUUID();
    var description = "New Product";
    var transaction = buildTransaction(uuid, transactionRandomUuid, creditCardUuid, description);
    var expectedResponse =
        buildTransactionDTO(uuid, transactionRandomUuid, creditCardUuid, description);

    when(mapper.map(expectedResponse)).thenReturn(transaction);
    when(mapper.map(transaction)).thenReturn(expectedResponse);
    when(repository.save(transaction)).thenReturn(transaction);
    var response = service.save(expectedResponse);

    assertThat(response, is(equalTo(expectedResponse)));
  }

  private List<Transaction> buildEntity(
      UUID uuid, LocalDate chargedDate, UUID paymentUuid, UUID creditCardUuid) {
    return ImmutableList.of(
        Transaction.builder()
            .transactionId(uuid)
            .chargeDate(chargedDate)
            .paymentStatus("OK")
            .payment(Payment.builder().paymentId(paymentUuid).build())
            .creditCard(CreditCard.builder().creditCardId(creditCardUuid).build())
            .build(),
        Transaction.builder()
            .transactionId(uuid)
            .chargeDate(chargedDate)
            .paymentStatus("Failure")
            .payment(Payment.builder().paymentId(paymentUuid).build())
            .creditCard(CreditCard.builder().creditCardId(creditCardUuid).build())
            .build(),
        Transaction.builder()
            .transactionId(uuid)
            .chargeDate(chargedDate)
            .paymentStatus("OK")
            .payment(Payment.builder().paymentId(paymentUuid).build())
            .creditCard(CreditCard.builder().creditCardId(creditCardUuid).build())
            .build());
  }

  private List<TransactionDTO> buildDTO(
      UUID uuid, LocalDate chargedDate, UUID paymentUuid, UUID creditCardUuid) {
    return ImmutableList.of(
        TransactionDTO.builder()
            .transactionId(uuid)
            .chargeDate(chargedDate)
            .paymentStatus("OK")
            .payment(paymentUuid)
            .creditCard(creditCardUuid)
            .build(),
        TransactionDTO.builder()
            .transactionId(uuid)
            .chargeDate(chargedDate)
            .paymentStatus("Failure")
            .payment(paymentUuid)
            .creditCard(creditCardUuid)
            .build(),
        TransactionDTO.builder()
            .transactionId(uuid)
            .chargeDate(chargedDate)
            .paymentStatus("OK")
            .payment(paymentUuid)
            .creditCard(creditCardUuid)
            .build());
  }

  private Transaction buildTransaction(
      UUID uuid, UUID paymentUuid, UUID creditCardUuid, String description) {
    return Transaction.builder()
        .transactionId(uuid)
        .chargeDate(LocalDate.MAX)
        .paymentStatus("OK")
        .payment(Payment.builder().paymentId(paymentUuid).build())
        .creditCard(CreditCard.builder().creditCardId(creditCardUuid).build())
        .build();
  }

  private TransactionDTO buildTransactionDTO(
      UUID uuid, UUID paymentUuid, UUID creditCardUuid, String description) {
    return TransactionDTO.builder()
        .transactionId(uuid)
        .chargeDate(LocalDate.MAX)
        .paymentStatus("OK")
        .payment(paymentUuid)
        .creditCard(creditCardUuid)
        .build();
  }
}
