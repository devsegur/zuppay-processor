package com.zup.processor.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.zup.processor.infrastructure.controller.PaymentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = PaymentController.class)
class PaymentControllerTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private PaymentController controller;

  @Test
  void mustReturnStatusOkWhenPerformGetMapping() throws Exception {
    String path = "/payment/";
    mockMvc.perform(get(path)).andExpect(status().isOk()).andReturn();
  }
}
