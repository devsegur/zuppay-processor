package com.zup.processor.domain.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

class CreditCardDTOTest {

  @Test
  void mustHaveGetCreditCardId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCardDTO.class, method -> method.getName().equals("getCreditCardId")));
  }

  @Test
  void mustHaveGetOwnerName() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCardDTO.class, method -> method.getName().equals("getOwnerName")));
  }

  @Test
  void mustHaveGetCardNumber() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCardDTO.class, method -> method.getName().equals("getCardNumber")));
  }

  @Test
  void mustHaveGetExpirationDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCardDTO.class, method -> method.getName().equals("getExpirationDate")));
  }

  @Test
  void mustHaveGetSecurityCode() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCardDTO.class, method -> method.getName().equals("getSecurityCode")));
  }

  @Test
  void mustHaveGetPayment() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCardDTO.class, method -> method.getName().equals("getPayment")));
  }

  @Test
  void mustHaveSetCreditCardId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCardDTO.class, method -> method.getName().equals("setCreditCardId")));
  }

  @Test
  void mustHaveSetOwnerName() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCardDTO.class, method -> method.getName().equals("setOwnerName")));
  }

  @Test
  void mustHaveSetCardNumber() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCardDTO.class, method -> method.getName().equals("setCardNumber")));
  }

  @Test
  void mustHaveSetExpirationDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCardDTO.class, method -> method.getName().equals("setExpirationDate")));
  }

  @Test
  void mustHaveSetSecurityCode() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCardDTO.class, method -> method.getName().equals("setSecurityCode")));
  }

  @Test
  void mustHaveSetPayment() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCardDTO.class, method -> method.getName().equals("setPayment")));
  }
}
