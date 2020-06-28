package com.zup.processor.domain.service;

import com.zup.processor.domain.dto.TransactionDTO;
import com.zup.processor.domain.mapper.TransactionMapper;
import com.zup.processor.infrastructure.repository.TransactionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@Log
@AllArgsConstructor
public class TransactionService implements CrudService<TransactionDTO> {

  private final TransactionRepository repository;
  private final TransactionMapper mapper;

  @Override
  public List<TransactionDTO> listAll() {
    return repository.findAll().parallelStream().map(mapper::map).collect(Collectors.toList());
  }

  @Override
  public TransactionDTO save(TransactionDTO dto) {
    var entity = mapper.map(dto);
    return mapper.map(repository.save(entity));
  }
}
