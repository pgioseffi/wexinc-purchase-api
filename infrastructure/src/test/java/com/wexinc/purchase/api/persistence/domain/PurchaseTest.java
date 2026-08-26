package com.wexinc.purchase.api.persistence.domain;

import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PurchaseTest {

  @Test
  void testEqualsAndHashCode() {
    final var purchase01 =
        new Purchase(
            CoreTestConstants.LONG_MIN_VALUE,
            StringUtils.EMPTY,
            CoreTestConstants.FIXED_LOCAL_DATE_TIME,
            BigDecimal.ZERO);
    final var purchase02 =
        new Purchase(
            CoreTestConstants.LONG_MIN_VALUE,
            StringUtils.EMPTY,
            CoreTestConstants.FIXED_LOCAL_DATE_TIME,
            BigDecimal.ZERO);
    final var purchase03 =
        new Purchase(
            Long.valueOf(0),
            StringUtils.EMPTY,
            CoreTestConstants.FIXED_LOCAL_DATE_TIME,
            BigDecimal.ZERO);

    Assertions.assertEquals(purchase01, purchase01, CoreTestConstants.EXPECTED_THE_SAME_RESULT);

    Assertions.assertEquals(purchase01, purchase02, CoreTestConstants.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        purchase01.hashCode(), purchase02.hashCode(), CoreTestConstants.EXPECTED_THE_SAME_RESULT);

    Assertions.assertNotEquals(purchase01, purchase03, CoreTestConstants.EXPECTED_DIFFERENT_RESULT);
    Assertions.assertNotEquals(
        purchase01.hashCode(), purchase03.hashCode(), CoreTestConstants.EXPECTED_DIFFERENT_RESULT);
    Assertions.assertNotEquals(
        purchase01, StringUtils.EMPTY, CoreTestConstants.EXPECTED_DIFFERENT_RESULT);
  }

  @Test
  void testToString() {
    Assertions.assertEquals(
        "Purchase [id=%d, description=%s, transactionDate=%s, amount=%.2f]"
            .formatted(
                CoreTestConstants.LONG_MIN_VALUE,
                StringUtils.EMPTY,
                CoreTestConstants.FIXED_LOCAL_DATE_TIME,
                BigDecimal.ZERO),
        new Purchase(
                CoreTestConstants.LONG_MIN_VALUE,
                StringUtils.EMPTY,
                CoreTestConstants.FIXED_LOCAL_DATE_TIME,
                BigDecimal.ZERO)
            .toString(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testModifiersMethods() {
    final var purchase01 =
        new Purchase(
            CoreTestConstants.LONG_MIN_VALUE,
            StringUtils.EMPTY,
            CoreTestConstants.FIXED_LOCAL_DATE_TIME,
            BigDecimal.ZERO);

    final var purchase02 = new Purchase();
    purchase02.setId(CoreTestConstants.LONG_MIN_VALUE);
    purchase02.setDescription(StringUtils.EMPTY);
    purchase02.setTransactionDate(CoreTestConstants.FIXED_LOCAL_DATE_TIME);
    purchase02.setAmount(BigDecimal.ZERO);

    Assertions.assertEquals(purchase01, purchase02, CoreTestConstants.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        purchase01.getId(), purchase02.getId(), CoreTestConstants.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        purchase01.getDescription(),
        purchase02.getDescription(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        purchase01.getTransactionDate(),
        purchase02.getTransactionDate(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
    Assertions.assertEquals(
        purchase01.getAmount(), purchase02.getAmount(), CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }
}
