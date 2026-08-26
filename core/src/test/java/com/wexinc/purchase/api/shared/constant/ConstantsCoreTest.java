package com.wexinc.purchase.api.shared.constant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConstantsCoreTest {

  @Test
  void testConstructor() throws NoSuchMethodException, SecurityException {
    final var constructor = ConstantsCore.class.getDeclaredConstructor();

    Assertions.assertTrue(
        Modifier.isPrivate(constructor.getModifiers()),
        CoreTestConstants.CONSTRUCTOR_IS_NOT_PRIVATE);

    constructor.setAccessible(true);
    Assertions.assertThrows(
        InvocationTargetException.class,
        constructor::newInstance,
        CoreTestConstants.CONSTRUCTOR_CAN_BE_INITIALIZED);
  }
}
