package com.zup.domain.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

class BaseEntityTest {

  @Test
  void mustHaveGetCreatedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseEntity.class, method -> method.getName().equals("getCreatedDate")));
  }

  @Test
  void mustHaveGetUpdatedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseEntity.class, method -> method.getName().equals("getUpdatedDate")));
  }

  @Test
  void mustHaveGetDeletedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseEntity.class, method -> method.getName().equals("getDeletedDate")));
  }

  @Test
  void mustHaveSetCreatedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseEntity.class, method -> method.getName().equals("setCreatedDate")));
  }

  @Test
  void mustHaveSetUpdatedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseEntity.class, method -> method.getName().equals("setUpdatedDate")));
  }

  @Test
  void mustHaveSetDeletedDate() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            BaseEntity.class, method -> method.getName().equals("setDeletedDate")));
  }
}
