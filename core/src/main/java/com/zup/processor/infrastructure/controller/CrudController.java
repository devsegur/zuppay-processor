package com.zup.processor.infrastructure.controller;

import com.zup.processor.domain.dto.BaseDTO;
import com.zup.processor.domain.exception.message.AlreadySavedException;
import java.util.Collection;
import javassist.tools.web.BadHttpRequest;

public interface CrudController<T extends BaseDTO> {

  Collection<T> listAll();

  T save(T dto) throws AlreadySavedException, BadHttpRequest;
}
