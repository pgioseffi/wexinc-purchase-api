package com.wexinc.purchase.api.dataprovider.gateway;

import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.mapper.PurchaseInfrastructureMapper;
import com.wexinc.purchase.api.persistence.repository.PurchaseRepository;
import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.fixture.PurchaseDTOFixture;
import com.wexinc.purchase.api.shared.fixture.PurchaseFixture;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PurchaseGatewayImplTest {

  @InjectMocks private PurchaseGatewayImpl instance;

  private final PurchaseRepository purchaseRepository;
  private final PurchaseInfrastructureMapper purchaseInfrastructureMapper;

  PurchaseGatewayImplTest(
      @Mock final PurchaseRepository purchaseRepositoryParam,
      @Mock final PurchaseInfrastructureMapper purchaseInfrastructureMapperParam) {
    this.purchaseRepository = purchaseRepositoryParam;
    this.purchaseInfrastructureMapper = purchaseInfrastructureMapperParam;
  }

  @Test
  void testShouldNotFindAnything() {
    Mockito.when(this.purchaseRepository.findAll()).thenReturn(List.of());

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertIterableEquals(
                List.of(), this.instance.findAll(), CoreTestConstants.EXPECTED_THE_SAME_RESULT),
        () -> Mockito.verify(this.purchaseRepository).findAll(),
        () -> Mockito.verifyNoInteractions(this.purchaseInfrastructureMapper));
  }

  @Test
  void testShouldFindAll() {
    final var expected = List.of(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE);

    Mockito.when(this.purchaseRepository.findAll())
        .thenReturn(List.of(PurchaseFixture.ACTUAL_PURCHASE));
    Mockito.when(this.purchaseInfrastructureMapper.fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertIterableEquals(
                expected, this.instance.findAll(), CoreTestConstants.EXPECTED_THE_SAME_RESULT),
        () -> Mockito.verify(this.purchaseRepository).findAll(),
        () ->
            Mockito.verify(this.purchaseInfrastructureMapper, Mockito.times(expected.size()))
                .fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE));
  }

  @Test
  void testShouldFindAllMultipleResults() {
    final var expected =
        List.of(
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE,
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE);

    Mockito.when(this.purchaseRepository.findAll())
        .thenReturn(List.of(PurchaseFixture.ACTUAL_PURCHASE, PurchaseFixture.ACTUAL_PURCHASE));
    Mockito.when(this.purchaseInfrastructureMapper.fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE))
        .thenReturn(
            PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE,
            new PurchaseDTO(
                CoreTestConstants.LONG_MIN_VALUE,
                StringUtils.EMPTY,
                CoreTestConstants.FIXED_LOCAL_DATE_TIME,
                BigDecimal.ZERO));

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertIterableEquals(
                expected, this.instance.findAll(), CoreTestConstants.EXPECTED_THE_SAME_RESULT),
        () -> Mockito.verify(this.purchaseRepository).findAll(),
        () ->
            Mockito.verify(this.purchaseInfrastructureMapper, Mockito.times(expected.size()))
                .fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE));
  }

  @Test
  void testShouldFindById() {
    Mockito.when(this.purchaseRepository.findById(CoreTestConstants.LONG_MIN_VALUE))
        .thenReturn(Optional.of(PurchaseFixture.ACTUAL_PURCHASE));
    Mockito.when(this.purchaseInfrastructureMapper.fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertEquals(
                Optional.of(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE),
                this.instance.findById(CoreTestConstants.LONG_MIN_VALUE),
                CoreTestConstants.EXPECTED_THE_SAME_RESULT),
        () -> Mockito.verify(this.purchaseRepository).findById(CoreTestConstants.LONG_MIN_VALUE),
        () ->
            Mockito.verify(this.purchaseInfrastructureMapper)
                .fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE));
  }

  @Test
  void testShouldNotFindById() {
    Mockito.when(this.purchaseRepository.findById(CoreTestConstants.LONG_MIN_VALUE))
        .thenReturn(Optional.empty());

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertEquals(
                Optional.empty(),
                this.instance.findById(CoreTestConstants.LONG_MIN_VALUE),
                CoreTestConstants.EXPECTED_THE_SAME_RESULT),
        () -> Mockito.verify(this.purchaseRepository).findById(CoreTestConstants.LONG_MIN_VALUE),
        () -> Mockito.verifyNoInteractions(this.purchaseInfrastructureMapper));
  }

  @Test
  void testShouldThrowIllegalArgumentExceptionWhenFindingById() {
    Mockito.when(this.purchaseRepository.findById(null)).thenThrow(IllegalArgumentException.class);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertThrowsExactly(
                IllegalArgumentException.class,
                () -> this.instance.findById(null),
                CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN),
        () -> Mockito.verify(this.purchaseRepository).findById(null),
        () -> Mockito.verifyNoInteractions(this.purchaseInfrastructureMapper));
  }

  @Test
  void testShouldExistById() {
    Mockito.when(
            Boolean.valueOf(this.purchaseRepository.existsById(CoreTestConstants.LONG_MIN_VALUE)))
        .thenReturn(Boolean.TRUE);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertTrue(
                this.instance.existsById(CoreTestConstants.LONG_MIN_VALUE),
                CoreTestConstants.THE_RESULT_SHOULD_HAVE_BEEN_TRUE),
        () -> Mockito.verify(this.purchaseRepository).existsById(CoreTestConstants.LONG_MIN_VALUE),
        () -> Mockito.verifyNoInteractions(this.purchaseInfrastructureMapper));
  }

  @Test
  void testShouldNotExistById() {
    Mockito.when(
            Boolean.valueOf(this.purchaseRepository.existsById(CoreTestConstants.LONG_MIN_VALUE)))
        .thenReturn(Boolean.FALSE);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertFalse(
                this.instance.existsById(CoreTestConstants.LONG_MIN_VALUE),
                CoreTestConstants.THE_RESULT_SHOULD_HAVE_BEEN_FALSE),
        () -> Mockito.verify(this.purchaseRepository).existsById(CoreTestConstants.LONG_MIN_VALUE),
        () -> Mockito.verifyNoInteractions(this.purchaseInfrastructureMapper));
  }

  @Test
  void testShouldShouldThrowIllegalArgumentExceptionWhenCheckingExistenceById() {
    Mockito.when(Boolean.valueOf(this.purchaseRepository.existsById(null)))
        .thenThrow(IllegalArgumentException.class);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertThrowsExactly(
                IllegalArgumentException.class,
                () -> this.instance.existsById(null),
                CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN),
        () -> Mockito.verify(this.purchaseRepository).existsById(null),
        () -> Mockito.verifyNoInteractions(this.purchaseInfrastructureMapper));
  }

  @Test
  void testShouldSave() {
    Mockito.when(
            this.purchaseInfrastructureMapper.fromDTOToEntity(
                PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE))
        .thenReturn(PurchaseFixture.ACTUAL_PURCHASE);
    Mockito.when(this.purchaseRepository.save(PurchaseFixture.ACTUAL_PURCHASE))
        .thenReturn(PurchaseFixture.ACTUAL_PURCHASE);
    Mockito.when(this.purchaseInfrastructureMapper.fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertEquals(
                PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE,
                this.instance.save(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE),
                CoreTestConstants.EXPECTED_THE_SAME_RESULT),
        () ->
            Mockito.verify(this.purchaseInfrastructureMapper)
                .fromDTOToEntity(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE),
        () -> Mockito.verify(this.purchaseRepository).save(PurchaseFixture.ACTUAL_PURCHASE),
        () ->
            Mockito.verify(this.purchaseInfrastructureMapper)
                .fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE));
  }

  @Test
  void testShouldShouldThrowIllegalArgumentExceptionWhenSaving() {
    Mockito.when(this.purchaseInfrastructureMapper.fromDTOToEntity(null)).thenReturn(null);
    Mockito.when(this.purchaseRepository.save(null)).thenThrow(IllegalArgumentException.class);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertThrowsExactly(
                IllegalArgumentException.class,
                () -> this.instance.save(null),
                CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN),
        () -> Mockito.verify(this.purchaseRepository).save(null),
        () -> Mockito.verify(this.purchaseInfrastructureMapper).fromDTOToEntity(null),
        () -> Mockito.verifyNoMoreInteractions(this.purchaseInfrastructureMapper));
  }

  @Test
  void testShouldDelete() {
    final ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
    Mockito.doNothing().when(this.purchaseRepository).deleteById(valueCapture.capture());

    this.instance.deleteById(CoreTestConstants.LONG_MIN_VALUE);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertEquals(
                CoreTestConstants.LONG_MIN_VALUE,
                valueCapture.getValue(),
                CoreTestConstants.EXPECTED_THE_SAME_RESULT),
        () -> Mockito.verify(this.purchaseRepository).deleteById(CoreTestConstants.LONG_MIN_VALUE),
        () -> Mockito.verifyNoInteractions(this.purchaseInfrastructureMapper));
  }

  @Test
  void testShouldShouldThrowIllegalArgumentExceptionWhenDeleting() {
    Mockito.doThrow(IllegalArgumentException.class).when(this.purchaseRepository).deleteById(null);

    Assertions.assertAll(
        CoreTestConstants.ONE_OR_MORE_TESTS_HAVE_FAILED,
        () ->
            Assertions.assertThrowsExactly(
                IllegalArgumentException.class,
                () -> this.instance.deleteById(null),
                CoreTestConstants.THE_EXCEPTION_WAS_NOT_THROWN),
        () -> Mockito.verify(this.purchaseRepository).deleteById(null),
        () -> Mockito.verifyNoInteractions(this.purchaseInfrastructureMapper));
  }
}
