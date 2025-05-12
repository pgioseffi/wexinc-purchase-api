package com.wexinc.purchase.api.persistence.domain;

import com.wexinc.purchase.api.shared.constant.Constantes;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PurchaseTest {

  @Test
  void testEqualsAndHashCode() {
    final var purchase01 =
        new Purchase(
            Constantes.LONG_MIN_VALUE,
            StringUtils.EMPTY,
            Constantes.FIXED_LOCAL_DATE_TIME,
            BigDecimal.ZERO);
    final var purchase02 =
        new Purchase(
            Constantes.LONG_MIN_VALUE,
            StringUtils.EMPTY,
            Constantes.FIXED_LOCAL_DATE_TIME,
            BigDecimal.ZERO);
    final var purchase03 =
        new Purchase(
            Long.valueOf(0), StringUtils.EMPTY, Constantes.FIXED_LOCAL_DATE_TIME, BigDecimal.ZERO);

    Assertions.assertEquals(purchase01, purchase01, Constantes.EXPECTED_THE_SAME_RESULT);

    Assertions.assertEquals(purchase01, purchase02, Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        purchase01.hashCode(), purchase02.hashCode(), Constantes.EXPECTED_THE_SAME_RESULT);

    Assertions.assertNotEquals(purchase01, purchase03, Constantes.EXPECTED_DIFFERENT_RESULT);
    Assertions.assertNotEquals(
        purchase01.hashCode(), purchase03.hashCode(), Constantes.EXPECTED_DIFFERENT_RESULT);
    Assertions.assertNotEquals(purchase01, StringUtils.EMPTY, Constantes.EXPECTED_DIFFERENT_RESULT);
  }

  @Test
  void testToString() {
    Assertions.assertEquals(
        "Purchase [id=%d, description=%s, transactionDate=%s, amount=%.2f]"
            .formatted(
                Constantes.LONG_MIN_VALUE,
                StringUtils.EMPTY,
                Constantes.FIXED_LOCAL_DATE_TIME,
                BigDecimal.ZERO),
        new Purchase(
                Constantes.LONG_MIN_VALUE,
                StringUtils.EMPTY,
                Constantes.FIXED_LOCAL_DATE_TIME,
                BigDecimal.ZERO)
            .toString(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testModifiersMethods() {
    final var purchase01 =
        new Purchase(
            Constantes.LONG_MIN_VALUE,
            StringUtils.EMPTY,
            Constantes.FIXED_LOCAL_DATE_TIME,
            BigDecimal.ZERO);

    final var purchase02 = new Purchase();
    purchase02.setId(Constantes.LONG_MIN_VALUE);
    purchase02.setDescription(StringUtils.EMPTY);
    purchase02.setTransactionDate(Constantes.FIXED_LOCAL_DATE_TIME);
    purchase02.setAmount(BigDecimal.ZERO);

    Assertions.assertEquals(purchase01, purchase02, Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        purchase01.getId(), purchase02.getId(), Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        purchase01.getDescription(),
        purchase02.getDescription(),
        Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        purchase01.getTransactionDate(),
        purchase02.getTransactionDate(),
        Constantes.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        purchase01.getAmount(), purchase02.getAmount(), Constantes.EXPECTED_THE_SAME_RESULT);
  }
}
