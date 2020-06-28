package com.zup.domain.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

class PaymentDTOTest {

  @Test
  void mustHaveGetPaymentId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("getPaymentId")));
  }

  @Test
  void mustHaveGetProductId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("getProductId")));
  }

  @Test
  void mustHaveGetDescription() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("getDescription")));
  }

  @Test
  void mustHaveGetDueDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("getDueDate")));
  }

  @Test
  void mustHaveGetMoney() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("getMoney")));
  }

  @Test
  void mustHaveGetCurrency() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("getCurrency")));
  }

  @Test
  void mustHaveGetCreditCard() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("getCreditCard")));
  }

  @Test
  void mustHaveGetTransaction() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("getTransaction")));
  }

  @Test
  void mustHaveSetPaymentId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("setPaymentId")));
  }

  @Test
  void mustHaveSetProductId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("setProductId")));
  }

  @Test
  void mustHaveSetDescription() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("setDescription")));
  }

  @Test
  void mustHaveSetDueDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("setDueDate")));
  }

  @Test
  void mustHaveSetMoney() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("setMoney")));
  }

  @Test
  void mustHaveSetCurrency() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("setCurrency")));
  }

  @Test
  void mustHaveSetCreditCard() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("setCreditCard")));
  }

  @Test
  void mustHaveSetTransaction() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            PaymentDTO.class, method -> method.getName().equals("setTransaction")));
  }
}
