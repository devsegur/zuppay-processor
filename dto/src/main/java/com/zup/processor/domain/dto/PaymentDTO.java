package com.zup.processor.domain.dto;

import com.zup.processor.domain.enumerations.CurrencyEnumDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.DecimalMax;
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
public class PaymentDTO extends BaseDTO {

  private UUID paymentId;

  private String productId;

  private String description;

  private LocalDate dueDate;

  @DecimalMax(value = "2")
  private BigDecimal money;

  private CurrencyEnumDTO currency;

  private UUID creditCard;

  private UUID transaction;
}
