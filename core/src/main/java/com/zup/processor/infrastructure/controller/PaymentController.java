package com.zup.processor.infrastructure.controller;

import com.zup.processor.domain.dto.PaymentDTO;
import com.zup.processor.domain.service.PaymentService;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/perform-payment", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController implements CrudController<PaymentDTO> {

  private final PaymentService service;

  @Override
  @GetMapping
  public Collection<PaymentDTO> listAll() {
    return service.listAll();
  }

  @Override
  @PostMapping
  public PaymentDTO save(@NonNull @RequestBody PaymentDTO dto) {
    return service.save(dto);
  }

  @GetMapping
  public ResponseEntity<HttpStatus> performPay() {
    service.existsPaymentsMessageSender();
    return ResponseEntity.ok().build();
  }
}
