package com.wexinc.purchase.api.usecase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wexinc.purchase.api.gateway.PurchaseGateway;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.exception.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class DeletePurchaseByIdInteractorTest {

	@InjectMocks
	private DeletePurchaseByIdInteractor instance;

	@Mock
	private PurchaseGateway purchaseGateway;

	@Test
	void shouldNotDeleteNonexistentPurchase() {
		Mockito.when(Boolean.valueOf(this.purchaseGateway.existsById(Constantes.LONG_MIN_VALUE)))
				.thenReturn(Boolean.FALSE);

		Assertions.assertThrows(EntityNotFoundException.class, () -> this.instance.accept(Long.MIN_VALUE),
				Constantes.THE_EXCEPTION_WAS_NOT_THROWN);

		Mockito.verify(this.purchaseGateway, Mockito.times(1)).existsById(Constantes.LONG_MIN_VALUE);
		Mockito.verify(this.purchaseGateway, Mockito.times(0)).deleteById(Constantes.LONG_MIN_VALUE);
	}

	@Test
	void shouldDeletePurchase() {
		Mockito.when(Boolean.valueOf(this.purchaseGateway.existsById(Constantes.LONG_MIN_VALUE)))
				.thenReturn(Boolean.TRUE);
		final ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
		Mockito.doNothing().when(this.purchaseGateway).deleteById(valueCapture.capture());

		this.instance.accept(Long.MIN_VALUE);

		Assertions.assertEquals(Long.MIN_VALUE, valueCapture.getValue(), Constantes.EXPECTED_THE_SAME_RESULT);

		Mockito.verify(this.purchaseGateway, Mockito.times(1)).existsById(Constantes.LONG_MIN_VALUE);
		Mockito.verify(this.purchaseGateway, Mockito.times(1)).deleteById(Constantes.LONG_MIN_VALUE);
	}
}
