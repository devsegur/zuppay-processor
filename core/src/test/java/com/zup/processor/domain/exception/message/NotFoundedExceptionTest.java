package com.zup.processor.domain.exception.message;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.jupiter.api.Test;

class NotFoundedExceptionTest {

  NotFoundedException exception = new NotFoundedException();

  @Test
  void mustBeMessageWhenThrown() {
    var message = exception.getMessage();
    var expectedMessage = "NOT_FOUNDED";

    assertThat(message, is(equalTo(expectedMessage)));
  }
}
