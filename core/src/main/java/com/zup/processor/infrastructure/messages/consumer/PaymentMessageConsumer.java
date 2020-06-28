package com.zup.processor.infrastructure.messages.consumer;

import com.google.gson.Gson;
import com.zup.processor.domain.dto.PaymentDTO;
import com.zup.processor.domain.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile(value = "!test")
@AllArgsConstructor
@Component
@RabbitListener(queues = "${spring.rabbitmq.queue.name}")
public class PaymentMessageConsumer {

  private final PaymentService service;
  private final Gson gson;

  @RabbitHandler
  public void receive(String paymentMessage) {
    service.duePayment(gson.fromJson(paymentMessage, PaymentDTO.class));
  }
}
