package com.wexinc.purchase.api.mapper;

import com.wexinc.purchase.api.dto.PurchaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Interface responsible for mapping a {@link PurchaseDTO} without a positive numeric identifier to
 * one with.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see PurchaseDTO
 */
@Mapper
public interface PurchaseCoreMapper {

  /**
   * Method responsible for mapping a {@link PurchaseDTO} without a positive numeric identifier to
   * one with.
   *
   * @param newId The new positive numeric identifier.
   * @param purchaseDTO The {@link PurchaseDTO} object with all the information to be copied.
   * @return A new {@link PurchaseDTO} object with all the information to copied plus the positive
   *     numeric identifier.
   * @see PurchaseDTO
   * @since 1.0.0
   */
  @Mapping(target = "id", source = "newId")
  PurchaseDTO fromIdAndDTOToDTO(Long newId, PurchaseDTO purchaseDTO);
}
