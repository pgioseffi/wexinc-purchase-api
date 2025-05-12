package com.wexinc.purchase.api.usecase;

import com.wexinc.purchase.api.boundary.input.FindAllPurchasesInputBoundary;
import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.gateway.PurchaseGateway;
import java.util.List;

/**
 * Concrete class containing the business rules for finding all purchases in the database through
 * the gateway.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public class FindAllPurchasesInteractor implements FindAllPurchasesInputBoundary {

  /**
   * Field that will actually perform an action on the database, such as deleting, in this case.
   *
   * @since 1.0.0
   */
  private final PurchaseGateway purchaseGateway;

  /**
   * Class complete constructor.
   *
   * @param purchaseGatewayParam Parameter responsible for holding the gateway that will initialize
   *     the {@link #purchaseGateway} field of this class.
   * @since 1.0.0
   */
  public FindAllPurchasesInteractor(final PurchaseGateway purchaseGatewayParam) {
    super();
    this.purchaseGateway = purchaseGatewayParam;
  }

  /** {@inheritDoc} */
  @Override
  public List<PurchaseDTO> get() {
    return this.purchaseGateway.findAll();
  }
}
