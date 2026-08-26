package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.dto.ExchangeRateDataDTO;
import com.wexinc.purchase.api.model.response.ExchangeRateDataResponseModel;
import java.math.BigDecimal;
import java.util.function.BiFunction;
import org.mapstruct.Javadoc;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Interface responsible for mapping a {@link ExchangeRateDataDTO} object and its {@link BigDecimal
 * purchase amout} into a {@link ExchangeRateDataResponseModel}.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see ExchangeRateDataDTO
 * @see ExchangeRateDataResponseModel
 */
@Mapper
@FunctionalInterface
@Javadoc(
    """
                    Concrete implementation type responsible for mapping a {@link ExchangeRateDataDTO} object and its {@link BigDecimal purchase amout} into a {@link ExchangeRateDataResponseModel}.

                    @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
                    @since 1.0.0
                    @version 1.0.0
                    @see ExchangeRateDataDTO
            @see ExchangeRateDataResponseModel""")
public interface ExchangeRatePresentationMapper
    extends BiFunction<BigDecimal, ExchangeRateDataDTO, ExchangeRateDataResponseModel> {

  /**
   * Method responsible for mapping a {@link ExchangeRateDataDTO} object into a {@link
   * ExchangeRateDataResponseModel}.
   *
   * @param purchaseAmount The original purchase amount retrieved from the database.
   * @param dto The {@link ExchangeRateDataDTO} object containing exchange rates returned by the
   *     American treasury exchange API ready to transport its information through the different
   *     layers of the application.
   * @return The already mapped {@link ExchangeRateDataResponseModel} object containing exchange
   *     rates returned by the American treasury exchange API ready to served as the application
   *     response.
   * @since 1.0.0
   */
  @Override
  @Mapping(
      target = "convertedAmount",
      expression =
          "java(dto == null || purchaseAmount == null ? null : dto.exchangeRate().multiply(purchaseAmount))")
  ExchangeRateDataResponseModel apply(BigDecimal purchaseAmount, ExchangeRateDataDTO dto);
}
