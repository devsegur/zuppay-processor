package com.zup.domain.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

class TransactionDTOTest {

  @Test
  void mustHaveGetTransactionId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            TransactionDTO.class, method -> method.getName().equals("getTransactionId")));
  }

  @Test
  void mustHaveGetChargeDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            TransactionDTO.class, method -> method.getName().equals("getChargeDate")));
  }

  @Test
  void mustHaveGetPaymentStatus() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            TransactionDTO.class, method -> method.getName().equals("getPaymentStatus")));
  }

  @Test
  void mustHaveGetPayment() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            TransactionDTO.class, method -> method.getName().equals("getPayment")));
  }

  @Test
  void mustHaveGetCreditCard() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            TransactionDTO.class, method -> method.getName().equals("getCreditCard")));
  }

  @Test
  void mustHaveSetTransactionId() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            TransactionDTO.class, method -> method.getName().equals("setTransactionId")));
  }

  @Test
  void mustHaveSetChargeDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            TransactionDTO.class, method -> method.getName().equals("setChargeDate")));
  }

  @Test
  void mustHaveSetPaymentStatus() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            TransactionDTO.class, method -> method.getName().equals("setPaymentStatus")));
  }

  @Test
  void mustHaveSetPayment() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            TransactionDTO.class, method -> method.getName().equals("setPayment")));
  }

  @Test
  void mustHaveSetCreditCard() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            TransactionDTO.class, method -> method.getName().equals("setCreditCard")));
  }
}
