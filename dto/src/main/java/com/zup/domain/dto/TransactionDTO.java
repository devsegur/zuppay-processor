package com.zup.domain.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TransactionDTO extends BaseDTO {

  private UUID transactionId;

  private LocalDate chargeDate;

  private String paymentStatus;

  private UUID payment;

  private UUID creditCard;
}
