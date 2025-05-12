package com.wexinc.purchase.api.usecase;

import com.wexinc.purchase.api.boundary.input.UpdatePurchaseInputBoundary;
import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.gateway.PurchaseGateway;
import com.wexinc.purchase.api.mapper.PurchaseCoreMapper;
import com.wexinc.purchase.api.shared.constant.ConstantsCore;
import com.wexinc.purchase.api.shared.exception.EntityNotFoundException;
import java.util.Objects;

/**
 * Concrete class containing the business rules to update a purchase through the gateway.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public class UpdatePurchaseInteractor implements UpdatePurchaseInputBoundary {

  /**
   * Field that will actually perform an action on the database, such as updating, in this case.
   *
   * @since 1.0.0
   */
  private final PurchaseGateway purchaseGateway;

  /**
   * Field that will perform a mapping action from a {@link PurchaseDTO} without a positive numeric
   * identifier to one with.
   *
   * @since 1.0.0
   * @see PurchaseCoreMapper
   * @see PurchaseCoreMapper#fromIdAndDTOToDTO(Long, PurchaseDTO)
   */
  private final PurchaseCoreMapper purchaseCoreMapper;

  /**
   * Class complete constructor.
   *
   * @param purchaseGatewayParam Parameter responsible for holding the gateway that will initialize
   *     the {@link #purchaseGateway} field of this class.
   * @param purchaseCoreMapperParam Parameter responsible for holding the mapper that will
   *     initialize the {@link #purchaseCoreMapper} field of this class.
   * @since 1.0.0
   */
  public UpdatePurchaseInteractor(
      final PurchaseGateway purchaseGatewayParam,
      final PurchaseCoreMapper purchaseCoreMapperParam) {
    super();
    this.purchaseGateway = purchaseGatewayParam;
    this.purchaseCoreMapper = purchaseCoreMapperParam;
  }

  /** {@inheritDoc} */
  @Override
  public PurchaseDTO apply(final Long id, final PurchaseDTO purchaseDTO) {
    if (this.purchaseGateway.existsById(id)) {
      Objects.requireNonNull(purchaseDTO, "Parameter purchaseDTO cannot be null.");

      return this.purchaseGateway.save(this.purchaseCoreMapper.fromIdAndDTOToDTO(id, purchaseDTO));
    }

    throw new EntityNotFoundException(
        ConstantsCore.EXCEPTION_MESSAGE_PURCHASE_NOT_FOUND.formatted(id));
  }
}
