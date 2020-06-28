package com.zup.domain.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

class CreditCardTest {

  @Test
  void mustHaveGetCreditCardId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("getCreditCardId")));
  }

  @Test
  void mustHaveGetOwnerName() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("getOwnerName")));
  }

  @Test
  void mustHaveGetCardNumber() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("getCardNumber")));
  }

  @Test
  void mustHaveGetExpirationDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("getExpirationDate")));
  }

  @Test
  void mustHaveGetSecurityCode() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("getSecurityCode")));
  }

  @Test
  void mustHaveSetCreditCardId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("setCreditCardId")));
  }

  @Test
  void mustHaveSetOwnerName() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("setOwnerName")));
  }

  @Test
  void mustHaveSetCardNumber() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("setCardNumber")));
  }

  @Test
  void mustHaveSetExpirationDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("setExpirationDate")));
  }

  @Test
  void mustHaveSetSecurityCode() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CreditCard.class, method -> method.getName().equals("setSecurityCode")));
  }
}
