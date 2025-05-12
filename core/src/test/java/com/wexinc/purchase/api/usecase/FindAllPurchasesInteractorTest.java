package com.wexinc.purchase.api.usecase;

import com.wexinc.purchase.api.gateway.PurchaseGateway;
import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.fixture.PurchaseDTOFixture;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindAllPurchasesInteractorTest {

  @InjectMocks private FindAllPurchasesInteractor instance;

  private final PurchaseGateway purchaseGateway;

  FindAllPurchasesInteractorTest(@Mock final PurchaseGateway purchaseGatewayParam) {
    this.purchaseGateway = purchaseGatewayParam;
  }

  @Test
  void testShouldFindAllPurchases() {
    final var result = List.of(PurchaseDTOFixture.ACTUAL_PURCHASE_DTO);
    Mockito.when(this.purchaseGateway.findAll()).thenReturn(result);

    Assertions.assertEquals(result, this.instance.get(), Constantes.EXPECTED_THE_SAME_RESULT);

    Mockito.verify(this.purchaseGateway).findAll();
  }
}
