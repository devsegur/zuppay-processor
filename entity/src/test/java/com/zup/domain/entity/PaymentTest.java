package com.zup.domain.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

class PaymentTest {

  @Test
  void mustHaveGetPaymentId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("getPaymentId")));
  }

  @Test
  void mustHaveGetCreditCard() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("getCreditCard")));
  }

  @Test
  void mustHaveGetProductId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("getProductId")));
  }

  @Test
  void mustHaveGetDescription() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("getDescription")));
  }

  @Test
  void mustHaveGetDueDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("getDueDate")));
  }

  @Test
  void mustHaveGetMoney() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("getMoney")));
  }

  @Test
  void mustHaveGetCurrency() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("getCurrency")));
  }

  @Test
  void mustHaveSetPaymentId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("setPaymentId")));
  }

  @Test
  void mustHaveSetCreditCard() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("setCreditCard")));
  }

  @Test
  void mustHaveSetProductId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("setProductId")));
  }

  @Test
  void mustHaveSetDescription() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("setDescription")));
  }

  @Test
  void mustHaveSetDueDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("setDueDate")));
  }

  @Test
  void mustHaveSetMoney() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("setMoney")));
  }

  @Test
  void mustHaveSetCurrency() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            Payment.class, method -> method.getName().equals("setCurrency")));
  }
}
