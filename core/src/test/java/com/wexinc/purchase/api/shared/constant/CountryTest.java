package com.wexinc.purchase.api.shared.constant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CountryTest {

  private static final Country ACTUAL = Country.BRAZIL;

  @Test
  void testName() {
    Assertions.assertEquals(
        "BRAZIL", CountryTest.ACTUAL.name(), Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testOrdinal() {
    Assertions.assertEquals(
        Country.BRAZIL.ordinal(),
        CountryTest.ACTUAL.ordinal(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testCapitalizedName() {
    Assertions.assertEquals(
        "Brazil", CountryTest.ACTUAL.getCapitalizedName(), Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testCapitalizedNameMultipleWords() {
    Assertions.assertEquals(
        "Antigua And Barbuda",
        Country.ANTIGUA_AND_BARBUDA.getCapitalizedName(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }
}
