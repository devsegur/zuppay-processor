package com.zup.processor.domain.exception.handler;

import com.zup.processor.domain.exception.message.AlreadySavedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class AlreadySavedHandler extends ResponseEntityExceptionHandler {

  private AlreadySavedException message;
}
