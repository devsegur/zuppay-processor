package com.zup.processor.domain.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zup.processor.domain.dto.BaseDTO;
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
  void mustBeDeclaredMethodSave() {
    assertTrue(ReflectionUtils.findMethod(CrudService.class, "save", BaseDTO.class).isPresent());
  }
}
