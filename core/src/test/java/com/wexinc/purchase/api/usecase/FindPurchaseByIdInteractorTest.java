package com.wexinc.purchase.api.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wexinc.purchase.api.gateway.PurchaseGateway;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.exception.EntityNotFoundException;
import com.wexinc.purchase.api.shared.util.PurchaseDTOFixture;

@ExtendWith(MockitoExtension.class)
class FindPurchaseByIdInteractorTest {

	@InjectMocks
	private FindPurchaseByIdInteractor instance;

	@Mock
	private PurchaseGateway purchaseGateway;

	@Test
	void shouldNotFindPurchase() {
		Mockito.when(this.purchaseGateway.findById(Constantes.LONG_MIN_VALUE)).thenThrow(EntityNotFoundException.class);

		Assertions.assertThrows(EntityNotFoundException.class, () -> this.instance.apply(Constantes.LONG_MIN_VALUE),
				Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

		Mockito.verify(this.purchaseGateway, Mockito.times(1)).findById(Constantes.LONG_MIN_VALUE);
	}

	@Test
	void shouldUpdatePurchase() {
		Mockito.when(this.purchaseGateway.findById(Constantes.LONG_MIN_VALUE))
				.thenReturn(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);

		Assertions.assertEquals(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO, this.instance.apply(Constantes.LONG_MIN_VALUE),
				Constantes.EXPECTED_THE_SAME_RESULT);

		Mockito.verify(this.purchaseGateway, Mockito.times(1)).findById(Constantes.LONG_MIN_VALUE);
	}
}
