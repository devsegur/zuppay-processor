package com.zup.domain.entity;

import java.util.Collection;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
public class CreditCard extends BaseEntity {

  @Id
  @NotNull
  @GeneratedValue(generator = "uuid2", strategy = GenerationType.IDENTITY)
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  private UUID creditCardId;

  @Column(length = 100)
  @NotNull
  private String ownerName;

  @Column(length = 100)
  @NotNull
  private String cardNumber;

  @Column(length = 100)
  @NotNull
  private String expirationDate;

  @Column(length = 100)
  @NotNull
  private String securityCode;

  @OneToMany(mappedBy = "creditCard", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  private Collection<Payment> payment;

  @OneToOne(mappedBy = "creditCard", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  private Transaction transaction;
}
