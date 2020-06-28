package com.zup.processor.domain.mapper;

import com.zup.processor.domain.dto.TransactionDTO;
import com.zup.processor.domain.entity.CreditCard;
import com.zup.processor.domain.entity.Payment;
import com.zup.processor.domain.entity.Transaction;
import java.util.Optional;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TransactionMapper {

  @Mappings({
    @Mapping(source = "transactionId", target = "transactionId"),
    @Mapping(source = "chargeDate", target = "chargeDate"),
    @Mapping(source = "paymentStatus", target = "paymentStatus"),
    @Mapping(expression = "java(getPaymentUuid(transaction.getPayment()))", target = "payment"),
    @Mapping(
        expression = "java(getCreditCardUuid(transaction.getCreditCard()))",
        target = "creditCard"),
  })
  public abstract TransactionDTO map(Transaction transaction);

  @Mappings({
    @Mapping(source = "transactionId", target = "transactionId"),
    @Mapping(source = "chargeDate", target = "chargeDate"),
    @Mapping(source = "paymentStatus", target = "paymentStatus"),
    @Mapping(expression = "java(getPayments(dto.getPayment()))", target = "payment"),
    @Mapping(expression = "java(getCreditCards(dto.getCreditCard()))", target = "creditCard"),
  })
  public abstract Transaction map(TransactionDTO dto);

  protected CreditCard getCreditCards(UUID uuid) {
    return CreditCard.builder().creditCardId(uuid).build();
  }

  protected UUID getCreditCardUuid(CreditCard card) {
    return card.getCreditCardId();
  }

  protected Payment getPayments(UUID uuid) {
    return Payment.builder().paymentId(uuid).build();
  }

  protected UUID getPaymentUuid(Payment payment) {
    return Optional.ofNullable(payment).map(Payment::getPaymentId).orElse(null);
  }
}
