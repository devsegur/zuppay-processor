package com.zup.processor.domain.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

class BaseDTOTest {

  @Test
  void mustHaveGetCreatedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseDTO.class, method -> method.getName().equals("getCreatedDate")));
  }

  @Test
  void mustHaveGetUpdatedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseDTO.class, method -> method.getName().equals("getUpdatedDate")));
  }

  @Test
  void mustHaveGetDeletedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseDTO.class, method -> method.getName().equals("getDeletedDate")));
  }

  @Test
  void mustHaveSetCreatedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseDTO.class, method -> method.getName().equals("setCreatedDate")));
  }

  @Test
  void mustHaveSetUpdatedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseDTO.class, method -> method.getName().equals("setUpdatedDate")));
  }

  @Test
  void mustHaveSetDeletedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseDTO.class, method -> method.getName().equals("setDeletedDate")));
  }
}
