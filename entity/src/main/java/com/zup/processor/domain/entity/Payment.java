package com.zup.processor.domain.entity;

import com.zup.processor.domain.enumerations.CurrencyEnum;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Payment extends BaseEntity {

  @Id
  @NotNull
  @GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private UUID paymentId;

  @Column(length = 100)
  @NotNull
  private String productId;

  @Column(length = 100)
  @NotNull
  private String description;

  @NotNull private LocalDate dueDate;

  @NotNull
  @Column(precision = 2, scale = 2)
  private BigDecimal money;

  @NotNull private CurrencyEnum currency;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  private CreditCard creditCard;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  private Transaction transaction;
}
