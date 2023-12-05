package com.wexinc.purchase.api.usecase;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wexinc.purchase.api.gateway.PurchaseGateway;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.util.PurchaseDTOFixture;

@ExtendWith(MockitoExtension.class)
class FindAllPurchasesInteractorTest {

	@InjectMocks
	private FindAllPurchasesInteractor instance;

	@Mock
	private PurchaseGateway purchaseGateway;

	@Test
	void shouldFindAllPurchases() {
		final var result = List.of(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);
		Mockito.when(this.purchaseGateway.findAll()).thenReturn(result);

		Assertions.assertEquals(result, this.instance.get(), Constantes.EXPECTED_THE_SAME_RESULT);

		Mockito.verify(this.purchaseGateway, Mockito.times(1)).findAll();
	}
}
