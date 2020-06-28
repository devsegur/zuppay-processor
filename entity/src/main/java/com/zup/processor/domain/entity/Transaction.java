package com.zup.processor.domain.entity;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@SuperBuilder
@Data
public class Transaction extends BaseEntity {

  @Id
  @NotNull
  @GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private UUID transactionId;

  @NotNull private LocalDate chargeDate;

  @Column(length = 100)
  @NotNull
  private String paymentStatus;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  private Payment payment;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  private CreditCard creditCard;
}
