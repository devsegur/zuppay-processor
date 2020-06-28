package com.zup.processor.domain.exception.handler;

import com.zup.processor.domain.exception.message.NotFoundedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class NotFoundedHandler extends ResponseEntityExceptionHandler {

  private NotFoundedException message;
}
