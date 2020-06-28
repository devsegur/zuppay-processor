package com.zup.processor.infrastructure.service;

import static java.util.Optional.ofNullable;

import com.google.common.collect.ImmutableList;
import com.paypal.http.exceptions.HttpException;
import com.paypal.payouts.CreatePayoutRequest;
import com.paypal.payouts.Currency;
import com.paypal.payouts.PayoutItem;
import com.paypal.payouts.PayoutsPostRequest;
import com.paypal.payouts.SenderBatchHeader;
import com.zup.processor.application.auth.PaypalAuth;
import com.zup.processor.domain.dto.PaymentDTO;
import com.zup.processor.domain.dto.TransactionDTO;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaypalPaymentService extends PaypalAuth {

  public TransactionDTO createPayout(PaymentDTO payload) throws IOException {

    PayoutsPostRequest request = buildRequestBody(payload);

    try {
      client().execute(request).result();
      return TransactionDTO.builder()
          .payment(payload.getPaymentId())
          .creditCard(payload.getCreditCard())
          .paymentStatus("OK")
          .chargeDate(LocalDate.now())
          .build();
    } catch (HttpException e) {
      return TransactionDTO.builder()
          .paymentStatus("FAILURE")
          .payment(payload.getPaymentId())
          .creditCard(payload.getCreditCard())
          .chargeDate(LocalDate.now())
          .build();
    }
  }

  private PayoutsPostRequest buildRequestBody(@NonNull PaymentDTO payload) {
    var payout =
        new PayoutItem()
            .senderItemId(
                ofNullable(payload).map(PaymentDTO::getPaymentId).map(UUID::toString).orElse(null))
            .note(ofNullable(payload).map(PaymentDTO::getDescription).orElse(null))
            .receiver(getMailToSendTransactions())
            .amount(buildAmount(payload));

    CreatePayoutRequest payoutBatch =
        new CreatePayoutRequest().senderBatchHeader(buildSender()).items(ImmutableList.of(payout));

    return new PayoutsPostRequest().requestBody(payoutBatch);
  }

  private Currency buildAmount(@NonNull PaymentDTO payload) {
    return new Currency()
        .currency(payload.getCurrency().toString())
        .value(payload.getMoney().toString());
  }

  private SenderBatchHeader buildSender() {
    return new SenderBatchHeader()
        .senderBatchId("Test_sdk_" + RandomStringUtils.randomAlphanumeric(7))
        .emailMessage("SDK payouts test txn")
        .emailSubject("This is a test transaction from SDK")
        .note("Enjoy your Payout!!")
        .recipientType("EMAIL");
  }
}
