package com.wexinc.purchase.api.usecase;

import com.wexinc.purchase.api.boundary.input.DeletePurchaseByIdInputBoundary;
import com.wexinc.purchase.api.gateway.PurchaseGateway;
import com.wexinc.purchase.api.shared.constant.ConstantsCore;
import com.wexinc.purchase.api.shared.exception.EntityNotFoundException;

/**
 * Concrete class containing the business rules for deleting a purchase in the database through the
 * gateway.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public class DeletePurchaseByIdInteractor implements DeletePurchaseByIdInputBoundary {

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
  public DeletePurchaseByIdInteractor(final PurchaseGateway purchaseGatewayParam) {
    super();
    this.purchaseGateway = purchaseGatewayParam;
  }

  /** {@inheritDoc} */
  @Override
  public void accept(final Long id) {
    if (this.purchaseGateway.existsById(id)) {
      this.purchaseGateway.deleteById(id);
      return;
    }

    throw new EntityNotFoundException(
        ConstantsCore.EXCEPTION_MESSAGE_PURCHASE_NOT_FOUND.formatted(id));
  }
}
