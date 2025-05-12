package com.wexinc.purchase.api.usecase;

import com.wexinc.purchase.api.boundary.input.FindPurchaseByIdInputBoundary;
import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.gateway.PurchaseGateway;

/**
 * Concrete class containing the business rules to search for a purchase through the gateway.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public class FindPurchaseByIdInteractor implements FindPurchaseByIdInputBoundary {

  /**
   * Field that will actually perform an action on the database, such as finding, in this case.
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
  public FindPurchaseByIdInteractor(final PurchaseGateway purchaseGatewayParam) {
    super();
    this.purchaseGateway = purchaseGatewayParam;
  }

  /**
   * {@inheritDoc}
   *
   * @throws com.wexinc.purchase.api.shared.exception.EntityNotFoundException If does not find the
   *     purchase.
   * @since 1.0.0
   */
  @Override
  public PurchaseDTO apply(final Long id) {
    return this.purchaseGateway.findById(id);
  }
}
