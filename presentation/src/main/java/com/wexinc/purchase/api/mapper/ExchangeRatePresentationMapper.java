package com.wexinc.purchase.api.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.wexinc.purchase.api.dto.ExchangeRateDTO;
import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.model.response.ExchangeRateDataResponseModel;
import com.wexinc.purchase.api.resource.ExchangeRateResource;
import com.wexinc.purchase.api.shared.constant.ConstantsPresentation;

/**
 * Interface responsible for mapping a {@link ExchangeRateResource} object into a {@link ExchangeRateDTO}.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see ExchangeRateDTO
 * @see ExchangeRateResource
 */
@Mapper(imports = { ConstantsPresentation.class, RoundingMode.class })
public interface ExchangeRatePresentationMapper {

	/**
	 * Method responsible for mapping a {@link ExchangeRateResource} object into a {@link ExchangeRateDTO}.
	 *
	 * @param resource The resource object {@link ExchangeRateResource} containing exchange rates returned by the
	 *                 american treasury exchange API.
	 * @return The already mapped {@link ExchangeRateDTO} object containing exchange rates returned by the american
	 *         treasury exchange API ready to transport its information through the different layers of the application.
	 * @since 1.0.0
	 */
	ExchangeRateDTO fromResourceToDTO(ExchangeRateResource resource);

	/**
	 * Method responsible for mapping a {@link ExchangeRateDataDTO} object into a {@link ExchangeRateDataResponseModel}.
	 *
	 * @param purchaseAmount The original purchase amount retrieved from the database.
	 * @param dto            The {@link ExchangeRateDTO} object containing exchange rates returned by the american
	 *                       treasury exchange API ready to transport its information through the different layers of
	 *                       the application.
	 * @return The already mapped {@link ExchangeRateDataResponseModel} object containing exchange rates returned by the
	 *         american treasury exchange API ready to served as the application response.
	 * @since 1.0.0
	 */
	@Mapping(target = "convertedAmount", expression = "java(dto == null || purchaseAmount == null ? null : dto.exchangeRate().multiply(purchaseAmount).setScale(ConstantsPresentation.TWO, RoundingMode.HALF_EVEN))")
	ExchangeRateDataResponseModel fromDTOToResponseModel(BigDecimal purchaseAmount, ExchangeRateDataDTO dto);

}
