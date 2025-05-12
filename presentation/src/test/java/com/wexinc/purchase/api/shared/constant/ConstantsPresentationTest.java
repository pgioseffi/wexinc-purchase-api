package com.wexinc.purchase.api.shared.constant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConstantsPresentationTest {

  @Test
  void testConstructor() throws NoSuchMethodException, SecurityException {
    final var constructor = ConstantsPresentation.class.getDeclaredConstructor();

    Assertions.assertTrue(
        Modifier.isPrivate(constructor.getModifiers()), Constantes.CONSTRUCTOR_IS_NOT_PRIVATE);

    constructor.setAccessible(true);
    Assertions.assertThrows(
        InvocationTargetException.class,
        constructor::newInstance,
        Constantes.CONSTRUCTOR_CAN_BE_INITIALIZED);
  }
}
