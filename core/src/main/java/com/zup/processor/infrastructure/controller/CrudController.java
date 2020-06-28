package com.zup.processor.infrastructure.controller;

import com.zup.processor.domain.dto.BaseDTO;
import com.zup.processor.domain.exception.message.AlreadySavedException;
import com.zup.processor.domain.exception.message.NotFoundedException;
import java.util.Collection;
import java.util.UUID;
import javassist.tools.web.BadHttpRequest;

public interface CrudController<T extends BaseDTO> {

  Collection<T> listAll();

  T findOneById(UUID id) throws NotFoundedException;

  T save(T dto) throws AlreadySavedException, BadHttpRequest;

  T update(T dto) throws NotFoundedException;

  T delete(T dto) throws NotFoundedException;
}
