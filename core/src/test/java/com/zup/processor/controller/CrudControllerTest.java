package com.zup.processor.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.zup.processor.domain.dto.BaseDTO;
import com.zup.processor.infrastructure.controller.CrudController;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

@EnableRabbit
class CrudControllerTest {

  @Test
  void mustBeDeclaredMethodListAll() {
    assertTrue(
        ReflectionUtils.isMethodPresent(
            CrudController.class, method -> method.getName().equals("listAll")));
  }

  @Test
  void mustBeDeclaredMethodSave() {
    assertTrue(ReflectionUtils.findMethod(CrudController.class, "save", BaseDTO.class).isPresent());
  }
}
