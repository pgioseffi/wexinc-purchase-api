package com.wexinc.purchase.api.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.wexinc.purchase.api.dto.EnhancedPurchaseDTO;
import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.model.request.PurchaseRequestModel;
import com.wexinc.purchase.api.model.response.EnhancedPurchaseResponseModel;
import com.wexinc.purchase.api.model.response.PurchaseResponseModel;

/**
 * Interface responsible for mapping a {@link PurchaseRequestModel} into a {@link PurchaseDTO} and a {@link PurchaseDTO}
 * into a {@link PurchaseResponseModel}.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see PurchaseDTO
 * @see PurchaseRequestModel
 * @see PurchaseResponseModel
 */
@Mapper(uses = ExchangeRatePresentationMapper.class)
public interface PurchasePresentationMapper {

	/**
	 * Method responsible for mapping a {@link PurchaseDTO} into a {@link PurchaseResponseModel}.
	 *
	 * @param purchaseDTO The data transfer object {@link PurchaseDTO} containing the purchase information to be
	 *                    converted into a {@link PurchaseResponseModel}.
	 * @return The response model object {@link PurchaseResponseModel} containing the purchase information already
	 *         converted.
	 * @see PurchaseDTO
	 * @see PurchaseResponseModel
	 * @since 1.0.0
	 */
	PurchaseResponseModel fromDTOToResponseModel(PurchaseDTO purchaseDTO);

	/**
	 * Method responsible for mapping a {@link PurchaseRequestModel} into a {@link PurchaseDTO}.
	 *
	 * @param purchaseRequestModel The request model object {@link PurchaseRequestModel} containing the purchase
	 *                             information to be converted into a {@link PurchaseDTO}.
	 * @return The data transfer object {@link PurchaseDTO} containing the purchase information already converted and
	 *         ready to navigate among the application layers.
	 * @see PurchaseDTO
	 * @see PurchaseRequestModel
	 * @since 1.0.0
	 */
	@Mapping(target = "id", ignore = true)
	@InheritInverseConfiguration
	PurchaseDTO fromRequestModelToDTO(PurchaseRequestModel purchaseRequestModel);

	/**
	 * Method responsible for mapping a {@link EnhancedPurchaseDTO} into a {@link EnhancedPurchaseResponseModel}.
	 *
	 * @param dto The data transfer object {@link EnhancedPurchaseDTO} containing the purchase information plus, its
	 *            exchange rate according to the country information, to be converted into a
	 *            {@link EnhancedPurchaseResponseModel}.
	 * @return The response model object {@link EnhancedPurchaseResponseModel} containing the purchase information plus
	 *         its exchange rate according to the country information, already converted to serve as the application
	 *         response.
	 * @see EnhancedPurchaseDTO
	 * @see EnhancedPurchaseResponseModel
	 * @since 1.0.0
	 */
	EnhancedPurchaseResponseModel fromEnhancedPurchaseDTOToEnhancedPurchaseResponseModel(EnhancedPurchaseDTO dto);

}
