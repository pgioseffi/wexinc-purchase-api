package com.wexinc.purchase.api.delivery.rest;

import com.wexinc.purchase.api.boundary.input.DeletePurchaseByIdInputBoundary;
import com.wexinc.purchase.api.boundary.input.FindAllPurchasesInputBoundary;
import com.wexinc.purchase.api.boundary.input.FindEnhancedPurchaseByIdInputBoundary;
import com.wexinc.purchase.api.boundary.input.FindPurchaseByIdInputBoundary;
import com.wexinc.purchase.api.boundary.input.SavePurchaseInputBoundary;
import com.wexinc.purchase.api.boundary.input.UpdatePurchaseInputBoundary;
import com.wexinc.purchase.api.mapper.PurchasePresentationMapper;
import com.wexinc.purchase.api.model.request.PurchaseRequestModel;
import com.wexinc.purchase.api.model.response.EnhancedPurchaseResponseModel;
import com.wexinc.purchase.api.model.response.PurchaseResponseModel;
import com.wexinc.purchase.api.shared.constant.ConstantsPresentation;
import com.wexinc.purchase.api.shared.constant.Country;
import java.util.List;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class of type {@link RestController} responsible for containing the methods with implementations
 * that will call the service classes containing the business rules.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see FindAllPurchasesInputBoundary
 * @see FindPurchaseByIdInputBoundary
 * @see SavePurchaseInputBoundary
 * @see UpdatePurchaseInputBoundary
 * @see DeletePurchaseByIdInputBoundary
 * @see PurchaseRestAPI
 * @see PurchaseRequestModel
 * @see PurchaseResponseModel
 * @see PurchasePresentationMapper
 * @see RestController
 */
@RestController
@RequestMapping(ConstantsPresentation.PURCHASE_REQUEST_MAPPING_VALUE)
// The @Validated annotation is mandatory here so that Spring and Hibernate Validator work together
// on validation when we are not using POJOs such as numeric path variables.
@Validated
public class PurchaseRestController implements PurchaseRestAPI {

  /**
   * Field responsible for holding the interface that defines the class with the business rules to
   * perform a purchase search in this application database.
   *
   * @since 1.0.0
   * @see FindAllPurchasesInputBoundary
   */
  private final FindAllPurchasesInputBoundary findAllPurchasesInputBoundary;

  /**
   * Field responsible for holding the interface that defines the class with the business rules to
   * perform a purchase search in this application database.
   *
   * @since 1.0.0
   * @see FindPurchaseByIdInputBoundary
   */
  private final FindPurchaseByIdInputBoundary findPurchaseByIdInputBoundary;

  /**
   * Field responsible for holding the interface that defines the class with the business rules to
   * perform a purchase search in this application database.
   *
   * @since 1.0.0
   * @see SavePurchaseInputBoundary
   */
  private final SavePurchaseInputBoundary savePurchaseInputBoundary;

  /**
   * Field responsible for holding the interface that defines the class with the business rules to
   * perform a purchase search in this application database.
   *
   * @since 1.0.0
   * @see UpdatePurchaseInputBoundary
   */
  private final UpdatePurchaseInputBoundary updatePurchaseInputBoundary;

  /**
   * Field responsible for holding the interface that defines the class with the business rules to
   * perform a purchase search in this application database.
   *
   * @since 1.0.0
   * @see DeletePurchaseByIdInputBoundary
   */
  private final DeletePurchaseByIdInputBoundary deletePurchaseByIdInputBoundary;

  /**
   * Field responsible for holding the interface that defines the class with the business rules to
   * perform a purchase search in this application database and return it with its exchange rates
   * data according to the country specified.
   *
   * @since 1.0.0
   * @see FindEnhancedPurchaseByIdInputBoundary
   */
  private final FindEnhancedPurchaseByIdInputBoundary findEnhancedPurchaseByIdInputBoundary;

  /**
   * Field responsible for holding the interface that defines the mapping between the data
   * transition object between the different layers of the application to the {@link
   * PurchaseResponseModel response object} for whoever calls this service.
   *
   * @since 1.0.0
   * @see PurchasePresentationMapper
   */
  private final PurchasePresentationMapper purchasePresentationMapper;

  /**
   * Complete constructor responsible for initializing the fields of this class.
   *
   * @param findAllPurchasesInputBoundaryParam Parameter responsible for initializing the {@link
   *     #findAllPurchasesInputBoundary} field of this class.
   * @param findPurchaseByIdInputBoundaryParam Parameter responsible for initializing the {@link
   *     #findPurchaseByIdInputBoundary} field of this class.
   * @param savePurchaseInputBoundaryParam Parameter responsible for initializing the {@link
   *     #savePurchaseInputBoundary} field of this class.
   * @param updatePurchaseInputBoundaryParam Parameter responsible for initializing the {@link
   *     #updatePurchaseInputBoundary} field of this class.
   * @param deletePurchaseByIdInputBoundaryParam Parameter responsible for initializing the {@link
   *     #deletePurchaseByIdInputBoundary} field of this class.
   * @param findEnhancedPurchaseByIdInputBoundaryParam Parameter responsible for initializing the
   *     {@link #findEnhancedPurchaseByIdInputBoundary} field of this class.
   * @param purchasePresentationMapperParam Parameter responsible for initializing the {@link
   *     #purchasePresentationMapper} field of this class.
   * @since 1.0.0
   * @see PurchaseRestController#findAllPurchasesInputBoundary
   * @see PurchaseRestController#findPurchaseByIdInputBoundary
   * @see PurchaseRestController#savePurchaseInputBoundary
   * @see PurchaseRestController#updatePurchaseInputBoundary
   * @see PurchaseRestController#deletePurchaseByIdInputBoundary
   * @see PurchaseRestController#purchasePresentationMapper
   */
  public PurchaseRestController(
      final FindAllPurchasesInputBoundary findAllPurchasesInputBoundaryParam,
      final FindPurchaseByIdInputBoundary findPurchaseByIdInputBoundaryParam,
      final SavePurchaseInputBoundary savePurchaseInputBoundaryParam,
      final UpdatePurchaseInputBoundary updatePurchaseInputBoundaryParam,
      final DeletePurchaseByIdInputBoundary deletePurchaseByIdInputBoundaryParam,
      final PurchasePresentationMapper purchasePresentationMapperParam,
      final FindEnhancedPurchaseByIdInputBoundary findEnhancedPurchaseByIdInputBoundaryParam) {
    super();
    this.findAllPurchasesInputBoundary = findAllPurchasesInputBoundaryParam;
    this.findPurchaseByIdInputBoundary = findPurchaseByIdInputBoundaryParam;
    this.savePurchaseInputBoundary = savePurchaseInputBoundaryParam;
    this.updatePurchaseInputBoundary = updatePurchaseInputBoundaryParam;
    this.deletePurchaseByIdInputBoundary = deletePurchaseByIdInputBoundaryParam;
    this.purchasePresentationMapper = purchasePresentationMapperParam;
    this.findEnhancedPurchaseByIdInputBoundary = findEnhancedPurchaseByIdInputBoundaryParam;
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.0.0
   */
  @Override
  @GetMapping
  public List<PurchaseResponseModel> findAll() {
    return this.findAllPurchasesInputBoundary.get().stream()
        .map(this.purchasePresentationMapper::fromDTOToResponseModel)
        .toList();
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.0.0
   */
  @Override
  @GetMapping(ConstantsPresentation.ID_REQUEST_PATH_VARIABLE)
  public PurchaseResponseModel findByID(@PathVariable final Long id) {
    return this.findPurchaseByIdInputBoundary
        .andThen(this.purchasePresentationMapper::fromDTOToResponseModel)
        .apply(id);
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.0.0
   */
  @Override
  @GetMapping(
      ConstantsPresentation.ENHANCED_REQUEST_MAPPING_VALUE
          + ConstantsPresentation.ID_REQUEST_PATH_VARIABLE)
  public EnhancedPurchaseResponseModel findEnhancedPurchaseByID(
      @PathVariable final Long id, @RequestParam final Set<Country> countries) {
    return this.findEnhancedPurchaseByIdInputBoundary
        .andThen(PurchasePresentationMapper::fromEnhancedPurchaseDTOToEnhancedPurchaseResponseModel)
        .apply(id, countries);
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.0.0
   */
  @Override
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PurchaseResponseModel save(@RequestBody final PurchaseRequestModel body) {
    return this.savePurchaseInputBoundary
        .compose(this.purchasePresentationMapper::fromRequestModelToDTO)
        .andThen(this.purchasePresentationMapper::fromDTOToResponseModel)
        .apply(body);
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.0.0
   */
  @Override
  @PutMapping(ConstantsPresentation.ID_REQUEST_PATH_VARIABLE)
  public PurchaseResponseModel update(
      @PathVariable final Long id, @RequestBody final PurchaseRequestModel body) {
    return this.updatePurchaseInputBoundary
        .andThen(this.purchasePresentationMapper::fromDTOToResponseModel)
        .apply(id, this.purchasePresentationMapper.fromRequestModelToDTO(body));
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.0.0
   */
  @Override
  @DeleteMapping(ConstantsPresentation.ID_REQUEST_PATH_VARIABLE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final Long id) {
    this.deletePurchaseByIdInputBoundary.accept(id);
  }
}
