package com.zup.processor.domain.service;

import com.zup.processor.domain.dto.BaseDTO;
import com.zup.processor.domain.exception.message.AlreadySavedException;
import java.util.Collection;
import javassist.tools.web.BadHttpRequest;

public interface CrudService<T extends BaseDTO> {

  Collection<T> listAll();

  T save(T dto) throws AlreadySavedException, BadHttpRequest;
}
