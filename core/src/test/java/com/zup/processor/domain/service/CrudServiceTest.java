package com.zup.processor.domain.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zup.processor.domain.dto.BaseDTO;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

class CrudServiceTest {

  @Test
  void mustBeDeclaredMethodListAll() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CrudService.class, method -> method.getName().equals("listAll")));
  }

  @Test
  void mustBeDeclaredMethodFindOneById() {
    assertTrue(
        ReflectionUtils.findMethod(CrudService.class, "findOneById", UUID.class).isPresent());
  }

  @Test
  void mustBeDeclaredMethodSave() {
    assertTrue(ReflectionUtils.findMethod(CrudService.class, "save", BaseDTO.class).isPresent());
  }

  @Test
  void mustBeDeclaredMethodUpdate() {
    assertTrue(ReflectionUtils.findMethod(CrudService.class, "update", BaseDTO.class).isPresent());
  }

  @Test
  void mustBeDeclaredMethodDelete() {
    assertTrue(ReflectionUtils.findMethod(CrudService.class, "delete", BaseDTO.class).isPresent());
  }
}
