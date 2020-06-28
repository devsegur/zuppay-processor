package com.zup.processor.domain.mapper;

import com.zup.processor.domain.dto.PaymentDTO;
import com.zup.processor.domain.entity.CreditCard;
import com.zup.processor.domain.entity.Payment;
import com.zup.processor.domain.entity.Transaction;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PaymentMapper {

  @Mappings({
    @Mapping(source = "paymentId", target = "paymentId"),
    @Mapping(source = "productId", target = "productId"),
    @Mapping(source = "description", target = "description"),
    @Mapping(source = "dueDate", target = "dueDate"),
    @Mapping(source = "money", target = "money"),
    @Mapping(source = "currency", target = "currency"),
    @Mapping(
        expression = "java(getCreditCardUuid(payment.getCreditCard()))",
        target = "creditCard"),
    @Mapping(
        expression = "java(getTransactionUuid(payment.getTransaction()))",
        target = "transaction"),
  })
  public abstract PaymentDTO map(Payment payment);

  @Mappings({
    @Mapping(source = "paymentId", target = "paymentId"),
    @Mapping(source = "productId", target = "productId"),
    @Mapping(source = "description", target = "description"),
    @Mapping(source = "dueDate", target = "dueDate"),
    @Mapping(source = "money", target = "money"),
    @Mapping(source = "currency", target = "currency"),
    @Mapping(expression = "java(getCreditCards(dto.getCreditCard()))", target = "creditCard"),
    @Mapping(expression = "java(getTransactions(dto.getTransaction()))", target = "transaction"),
  })
  public abstract Payment map(PaymentDTO dto);

  protected CreditCard getCreditCards(UUID values) {
    return CreditCard.builder().creditCardId(values).build();
  }

  protected UUID getCreditCardUuid(CreditCard values) {
    return values.getCreditCardId();
  }

  protected Transaction getTransactions(UUID values) {
    return Transaction.builder().transactionId(values).build();
  }

  protected UUID getTransactionUuid(Transaction values) {
    return values.getTransactionId();
  }
}
