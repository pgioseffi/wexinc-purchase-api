package com.wexinc.purchase.api.shared.exception;

import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EntityNotFoundExceptionTest {

  private static final Throwable CAUSE = new Exception();

  @Test
  void testThrowExceptionWithAllParams() {
    Assertions.assertThrows(
        EntityNotFoundException.class,
        () -> {
          throw new EntityNotFoundException(
              StringUtils.EMPTY, EntityNotFoundExceptionTest.CAUSE, false, false);
        },
        CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN);
  }

  @Test
  void testThrowExceptionWithThrowable() {
    Assertions.assertThrows(
        EntityNotFoundException.class,
        () -> {
          throw new EntityNotFoundException(EntityNotFoundExceptionTest.CAUSE);
        },
        CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN);
  }

  @Test
  void testThrowExceptionWithMessageThrowableCase() {
    Assertions.assertThrows(
        EntityNotFoundException.class,
        () -> {
          throw new EntityNotFoundException(StringUtils.EMPTY, EntityNotFoundExceptionTest.CAUSE);
        },
        CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN);
  }

  @Test
  void testThrowExceptionWithoutParams() {
    Assertions.assertThrows(
        EntityNotFoundException.class,
        () -> {
          throw new EntityNotFoundException();
        },
        CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN);
  }

  @Test
  void testThrowExceptionWithMessage() {
    Assertions.assertThrows(
        EntityNotFoundException.class,
        () -> {
          throw new EntityNotFoundException(StringUtils.EMPTY);
        },
        CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN);
  }
}
