package com.wexinc.purchase.api.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.persistence.domain.Purchase;

/**
 * Interface responsible for mapping a {@link Purchase} object into a {@link PurchaseDTO} and vice-versa.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see Purchase
 * @see PurchaseDTO
 */
@Mapper
public interface PurchaseInfrastructureMapper {

	/**
	 * Method responsible for mapping a {@link Purchase} object into a {@link PurchaseDTO}.
	 *
	 * @param purchase The database object containing the purchase information.
	 * @return The {@link PurchaseDTO} object containing purchase information converted into the data transfer object
	 *         among the different layers of the application.
	 * @since 1.0.0
	 */
	PurchaseDTO fromEntityToDTO(Purchase purchase);

	/**
	 * Method responsible for mapping a {@link PurchaseDTO} object into a {@link Purchase}.
	 *
	 * @param purchaseDTO The data transfer object {@link PurchaseDTO} containing purchase information that will be
	 *                    converted into the {@link Purchase} object used by the repository layer.
	 * @return The {@link Purchase} object containing purchase information that will be used only by the repository
	 *         layer.
	 * @since 1.0.0
	 */
	@InheritInverseConfiguration
	Purchase fromDTOToEntity(PurchaseDTO purchaseDTO);

}
