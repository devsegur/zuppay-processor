package com.zup.processor.infrastructure.controller;

import com.zup.processor.domain.dto.PaymentDTO;
import com.zup.processor.domain.exception.message.AlreadySavedException;
import com.zup.processor.domain.exception.message.NotFoundedException;
import com.zup.processor.domain.service.PaymentService;
import java.util.Collection;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController implements CrudController<PaymentDTO> {

  private PaymentService service;

  @Override
  @GetMapping
  public Collection<PaymentDTO> listAll() {
    return service.listAll();
  }

  @Override
  @GetMapping(value = "/{id}")
  public PaymentDTO findOneById(@NonNull @PathVariable(value = "id") UUID id)
      throws NotFoundedException {
    return service.findOneById(id);
  }

  @Override
  @PostMapping
  public PaymentDTO save(@NonNull @RequestBody PaymentDTO dto) throws AlreadySavedException {
    return service.save(dto);
  }

  @Override
  @PutMapping
  public PaymentDTO update(@NonNull @RequestBody PaymentDTO dto) throws NotFoundedException {
    return service.update(dto);
  }

  @Override
  @DeleteMapping()
  public PaymentDTO delete(@NonNull @RequestBody PaymentDTO dto) throws NotFoundedException {
    return service.delete(dto);
  }
}
