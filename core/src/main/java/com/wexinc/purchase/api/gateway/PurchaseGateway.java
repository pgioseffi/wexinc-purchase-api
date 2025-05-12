package com.wexinc.purchase.api.gateway;

import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.shared.exception.EntityNotFoundException;
import java.util.List;

/**
 * Class responsible for calling the repository layer to consult, read, update or delete a purchase
 * from the database.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
public interface PurchaseGateway {

  /**
   * Method responsible for calling the repository layer to return all purchases in the database.
   * Use with caution, this method does not consider any database filter parameters and it is not
   * paginated.
   *
   * @return Will return a {@link List} of {@link PurchaseDTO} objects containing information of all
   *     the purchases in the database.
   * @since 1.0.0
   */
  List<PurchaseDTO> findAll();

  /**
   * Method responsible for calling the repository layer to return a purchase in the database.
   *
   * @param id The positive numeric identifier of a purchase.
   * @return Will return a {@link PurchaseDTO} if found.
   * @since 1.0.0
   * @throws EntityNotFoundException if not found.
   */
  PurchaseDTO findById(Long id);

  /**
   * Method responsible for calling the repository layer to delete a purchase from the database.
   *
   * @param id The positive numeric identifier of a purchase.
   * @since 1.0.0
   * @throws EntityNotFoundException if not found.
   */
  void deleteById(Long id);

  /**
   * Method responsible for calling the repository layer to save a purchase in the database.
   *
   * @param purhcaseDTO The {@linkplain PurchaseDTO} object containing all the information from a
   *     purchase.
   * @return Will return a {@link PurchaseDTO} containing all the information from a purchase plus
   *     its positive numeric identifier.
   * @since 1.0.0
   */
  PurchaseDTO save(PurchaseDTO purhcaseDTO);

  /**
   * Method responsible for calling the repository layer to check if a purchase exists in the
   * database.
   *
   * @param id The positive numeric identifier of a purchase.
   * @return <code>true</code> if found, <code>false</code> otherwise.
   * @since 1.0.0
   * @throws EntityNotFoundException if not found.
   */
  boolean existsById(Long id);
}
