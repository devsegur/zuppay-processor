package com.zup.processor.domain.scheduler;

import com.zup.processor.domain.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log
@AllArgsConstructor
public class PaymentScheduler {

  private PaymentService service;

  @Scheduled(cron = "0 0 6 * * 1-5")
  public void checkDuePayments() {
    service.existsPaymentsMessageSender();
  }
}
