package com.zup.processor.infrastructure.message.producer;

import static java.util.Optional.ofNullable;

import com.google.gson.Gson;
import com.zup.processor.domain.dto.PaymentDTO;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@Log
public class PaymentMessageProducer {

  private final RabbitTemplate template;
  private final Queue queue = new Queue(ofNullable(System.getenv("QUEUE_NAME")).orElse("payments"));
  private final Gson gson;

  public void sentMessage(PaymentDTO payment) {
    template.convertAndSend(queue.getName(), gson.toJson(payment));
  }
}
