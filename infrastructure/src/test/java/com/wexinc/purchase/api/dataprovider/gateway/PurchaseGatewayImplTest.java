package com.wexinc.purchase.api.dataprovider.gateway;

import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.mapper.PurchaseInfrastructureMapper;
import com.wexinc.purchase.api.persistence.domain.Purchase;
import com.wexinc.purchase.api.persistence.repository.PurchaseRepository;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.exception.EntityNotFoundException;
import com.wexinc.purchase.api.shared.fixture.PurchaseDTOFixture;
import com.wexinc.purchase.api.shared.util.PurchaseFixture;
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

    Assertions.assertEquals(
        List.of(), this.instance.findAll(), Constantes.EXPECTED_THE_SAME_RESULT);

    Mockito.verify(this.purchaseRepository).findAll();
    Mockito.verifyNoInteractions(this.purchaseInfrastructureMapper);
  }

  @Test
  void testShouldFindAll() {
    final var result = List.of(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);

    Mockito.when(this.purchaseRepository.findAll())
        .thenReturn(List.of(PurchaseFixture.ACTUAL_PURCHASE));
    Mockito.when(this.purchaseInfrastructureMapper.fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);

    Assertions.assertEquals(result, this.instance.findAll(), Constantes.EXPECTED_THE_SAME_RESULT);

    Mockito.verify(this.purchaseRepository).findAll();
    Mockito.verify(this.purchaseInfrastructureMapper, Mockito.times(result.size()))
        .fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE);
  }

  @Test
  void testShouldFindAllMultipleResults() {
    final var purchaseDTO02 =
        new PurchaseDTO(
            Long.valueOf(Long.MAX_VALUE),
            StringUtils.EMPTY,
            Constantes.FIXED_LOCAL_DATE_TIME,
            BigDecimal.ZERO);
    final var purchase02 =
        new Purchase(
            Long.valueOf(Long.MAX_VALUE),
            StringUtils.EMPTY,
            Constantes.FIXED_LOCAL_DATE_TIME,
            BigDecimal.ZERO);
    final var result = List.of(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO, purchaseDTO02);
    final var repositoryResult = List.of(PurchaseFixture.ACTUAL_PURCHASE, purchase02);

    Mockito.when(this.purchaseRepository.findAll()).thenReturn(repositoryResult);

    Mockito.when(this.purchaseInfrastructureMapper.fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);
    Mockito.when(this.purchaseInfrastructureMapper.fromEntityToDTO(purchase02))
        .thenReturn(purchaseDTO02);

    // Assert.
    final var expectedResult = this.instance.findAll();
    Assertions.assertEquals(expectedResult, result, Constantes.EXPECTED_THE_SAME_RESULT);

    // Verify that the repository is called just one time.
    Mockito.verify(this.purchaseRepository).findAll();

    // Verify that the mapper is called for each entity.
    Mockito.verify(this.purchaseInfrastructureMapper)
        .fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE);
    Mockito.verify(this.purchaseInfrastructureMapper).fromEntityToDTO(purchase02);

    // Assertion for checking the size of the repositoryResult
    Assertions.assertEquals(2, expectedResult.size(), Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testShouldFindById() {
    Mockito.when(this.purchaseRepository.findById(Constantes.LONG_MIN_VALUE))
        .thenReturn(Optional.of(PurchaseFixture.ACTUAL_PURCHASE));
    Mockito.when(this.purchaseInfrastructureMapper.fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);

    Assertions.assertEquals(
        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO,
        this.instance.findById(Constantes.LONG_MIN_VALUE),
        Constantes.EXPECTED_THE_SAME_RESULT);

    Mockito.verify(this.purchaseRepository).findById(Constantes.LONG_MIN_VALUE);
    Mockito.verify(this.purchaseInfrastructureMapper)
        .fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE);
  }

  @Test
  void testShouldNotFindById() {
    Mockito.when(this.purchaseRepository.findById(Constantes.LONG_MIN_VALUE))
        .thenReturn(Optional.empty());

    Assertions.assertThrows(
        EntityNotFoundException.class,
        () -> this.instance.findById(Constantes.LONG_MIN_VALUE),
        Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

    Mockito.verify(this.purchaseRepository).findById(Constantes.LONG_MIN_VALUE);
    Mockito.verifyNoInteractions(this.purchaseInfrastructureMapper);
  }

  @Test
  void testShouldThrowIllegalArgumentExceptionWhenFindingById() {
    Mockito.when(this.purchaseRepository.findById(null)).thenThrow(IllegalArgumentException.class);

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> this.instance.findById(null),
        Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

    Mockito.verify(this.purchaseRepository).findById(null);
  }

  @Test
  void testShouldExistById() {
    Mockito.when(Boolean.valueOf(this.purchaseRepository.existsById(Constantes.LONG_MIN_VALUE)))
        .thenReturn(Boolean.TRUE);

    Assertions.assertEquals(
        Boolean.TRUE,
        Boolean.valueOf(this.instance.existsById(Constantes.LONG_MIN_VALUE)),
        Constantes.EXPECTED_THE_SAME_RESULT);

    Mockito.verify(this.purchaseRepository).existsById(Constantes.LONG_MIN_VALUE);
  }

  @Test
  void testShouldNotExistById() {
    Mockito.when(Boolean.valueOf(this.purchaseRepository.existsById(Constantes.LONG_MIN_VALUE)))
        .thenReturn(Boolean.FALSE);

    Assertions.assertEquals(
        Boolean.FALSE,
        Boolean.valueOf(this.instance.existsById(Constantes.LONG_MIN_VALUE)),
        Constantes.EXPECTED_THE_SAME_RESULT);

    Mockito.verify(this.purchaseRepository).existsById(Constantes.LONG_MIN_VALUE);
  }

  @Test
  void testShouldShouldThrowIllegalArgumentExceptionWhenCheckingExistenceById() {
    Mockito.when(Boolean.valueOf(this.purchaseRepository.existsById(null)))
        .thenThrow(IllegalArgumentException.class);

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> this.instance.existsById(null),
        Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

    Mockito.verify(this.purchaseRepository).existsById(null);
  }

  @Test
  void testShouldSave() {
    Mockito.when(
            this.purchaseInfrastructureMapper.fromDTOToEntity(
                PurchaseDTOFixture.ACTUAL_PURCHASE_DTO))
        .thenReturn(PurchaseFixture.ACTUAL_PURCHASE);
    Mockito.when(this.purchaseRepository.save(PurchaseFixture.ACTUAL_PURCHASE))
        .thenReturn(PurchaseFixture.ACTUAL_PURCHASE);
    Mockito.when(this.purchaseInfrastructureMapper.fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE))
        .thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);

    Assertions.assertEquals(
        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO,
        this.instance.save(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO),
        Constantes.EXPECTED_THE_SAME_RESULT);

    Mockito.verify(this.purchaseRepository).save(PurchaseFixture.ACTUAL_PURCHASE);
    Mockito.verify(this.purchaseInfrastructureMapper)
        .fromEntityToDTO(PurchaseFixture.ACTUAL_PURCHASE);
  }

  @Test
  void testShouldShouldThrowIllegalArgumentExceptionWhenSaving() {
    Mockito.when(this.purchaseRepository.save(null)).thenThrow(IllegalArgumentException.class);

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> this.instance.save(null),
        Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

    Mockito.verify(this.purchaseRepository).save(null);
  }

  @Test
  void testShouldDelete() {
    final ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
    Mockito.doNothing().when(this.purchaseRepository).deleteById(valueCapture.capture());

    this.instance.deleteById(Constantes.LONG_MIN_VALUE);

    Assertions.assertEquals(
        Constantes.LONG_MIN_VALUE, valueCapture.getValue(), Constantes.EXPECTED_THE_SAME_RESULT);

    Mockito.verify(this.purchaseRepository).deleteById(Constantes.LONG_MIN_VALUE);
  }

  @Test
  void testShouldShouldThrowIllegalArgumentExceptionWhenDeleting() {
    Mockito.doThrow(IllegalArgumentException.class).when(this.purchaseRepository).deleteById(null);

    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> this.instance.deleteById(null),
        Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

    Mockito.verify(this.purchaseRepository).deleteById(null);
  }
}
