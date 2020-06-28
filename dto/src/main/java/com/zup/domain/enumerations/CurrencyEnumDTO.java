package com.zup.domain.enumerations;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum CurrencyEnumDTO {
  USD("USD"),
  EUR("EUR"),
  BRL("BRL");

  private String value;

  CurrencyEnumDTO(String enumName) {
    this.value = enumName;
  }
}
