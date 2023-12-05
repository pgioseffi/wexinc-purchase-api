package com.wexinc.purchase.api.delivery.rest;

import java.util.List;
import java.util.Set;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;

import com.wexinc.purchase.api.model.request.PurchaseRequestModel;
import com.wexinc.purchase.api.model.response.EnhancedPurchaseResponseModel;
import com.wexinc.purchase.api.model.response.PurchaseResponseModel;
import com.wexinc.purchase.api.shared.constant.ConstantsPresentation;
import com.wexinc.purchase.api.shared.constant.Country;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Interface responsible for containing the signatures of the methods implemented by the {@link PurchaseRestController}
 * concrete class.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 *
 * @since 1.0.0
 * @version 1.0.0
 * @see PurchaseRestController
 * @see PurchaseResponseModel
 */
@Tag(name = "Purchase API")
public interface PurchaseRestAPI {

	/**
	 * Method responsible for calling the business layer to search for all purchases in the database.
	 *
	 * @return A {@link List} of all {@link PurchaseRequestModel purchases information}. More feedback information can
	 *         be obtained in the Swagger of the application.
	 * @since 1.0.0
	 */
	List<PurchaseResponseModel> findAll();

	/**
	 * Method responsible for calling the business layer to search for a purchase in the database.
	 *
	 * @param id The numeric identifier of a purchase. It cannot be negative or zero.
	 * @return Purchase information, if found. More feedback information can be obtained in the Swagger of the
	 *         application.
	 * @since 1.0.0
	 */
	@Operation(summary = "Search for a purchase in the database using a numeric identifier.", description = "Receives a numerical purchase identifier and searches the database.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_200, description = "Purchase found and data returned successfully.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PurchaseResponseModel.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_400, description = ConstantsPresentation.RESPONSE_DESCRIPTION_STATUS_CODE_400, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_404, description = "Purchase not found.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_422, description = ConstantsPresentation.RESPONSE_DESCRIPTION_STATUS_CODE_422, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_500, description = ConstantsPresentation.RESPONSE_DESCRIPTION_STATUS_CODE_500, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))) })
	PurchaseResponseModel findByID(
			@Positive(message = "You cannot pass a negative or zero ID for this search.") @NotNull(message = "You cannot pass a null id for this search.") @Parameter(description = "The numeric identifier of a purchase.", example = "98767367", name = "id", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = @Schema(implementation = Long.class))) Long id);

	/**
	 * Method responsible for calling the business layer to delete a purchase in the database.
	 *
	 * @param body The purchase payload to be persisted in the database
	 * @return A {@link PurchaseResponseModel} containing all the purchase information plus its numeric positive
	 *         identifier.
	 * @since 1.0.0
	 */
	@Operation(summary = "Search for a purchase in the database using a numeric identifier.", description = "Receives a numerical purchase identifier and searches the database.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_200, description = "Purchase found and data returned successfully.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PurchaseResponseModel.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_400, description = ConstantsPresentation.RESPONSE_DESCRIPTION_STATUS_CODE_400, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_404, description = "Purchase not found.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_422, description = ConstantsPresentation.RESPONSE_DESCRIPTION_STATUS_CODE_422, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_500, description = ConstantsPresentation.RESPONSE_DESCRIPTION_STATUS_CODE_500, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))) })
	PurchaseResponseModel save(@Valid PurchaseRequestModel body);

	/**
	 * Method responsible for calling the business layer to update a purchase in the database.
	 *
	 * @param id   The numeric identifier of a purchase. It cannot be negative or zero.
	 * @param body The purchase payload to be updated in the database.
	 * @return An {@link PurchaseResponseModel} object containing the already updated purchase information.
	 * @since 1.0.0
	 */
	@Operation(summary = "Search for a purchase in the database using a numeric identifier.", description = "Receives a numerical purchase identifier and searches the database.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_200, description = "Purchase found and data returned successfully.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PurchaseResponseModel.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_400, description = ConstantsPresentation.RESPONSE_DESCRIPTION_STATUS_CODE_400, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_404, description = "Purchase not found.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_422, description = ConstantsPresentation.RESPONSE_DESCRIPTION_STATUS_CODE_422, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_500, description = ConstantsPresentation.RESPONSE_DESCRIPTION_STATUS_CODE_500, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))) })
	PurchaseResponseModel update(
			@Positive(message = "You cannot pass a negative or zero ID for this search.") @NotNull(message = "You cannot pass a null id for this search.") @Parameter(description = "The numeric identifier of a purchase.", example = "98767367", name = "id", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = @Schema(implementation = Long.class))) Long id,
			@Valid PurchaseRequestModel body);

	/**
	 * Method responsible for calling the business layer to delete a purchase in the database.
	 *
	 * @param id The numeric identifier of a purchase. It cannot be negative or zero.
	 * @since 1.0.0
	 */
	@Operation(summary = "Search for a purchase in the database using a numeric identifier.", description = "Receives a numerical purchase identifier and searches the database.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_200, description = "Purchase found and data returned successfully.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PurchaseResponseModel.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_400, description = ConstantsPresentation.RESPONSE_DESCRIPTION_STATUS_CODE_400, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_404, description = "Purchase not found.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_422, description = ConstantsPresentation.RESPONSE_DESCRIPTION_STATUS_CODE_422, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))),
			@ApiResponse(responseCode = ConstantsPresentation.RESPONSE_STATUS_CODE_500, description = ConstantsPresentation.RESPONSE_DESCRIPTION_STATUS_CODE_500, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorAttributes.class))) })
	void delete(
			@Positive(message = "You cannot pass a negative or zero ID for this exclusion.") @NotNull(message = "You cannot pass a null id for this exclusion.") @Parameter(description = "The numeric identifier of a purchase.", example = "98767367", name = "id", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE, schema = @Schema(implementation = Long.class))) Long id);

	/**
	 * {@inheritDoc}
	 *
	 * @param id The numeric identifier of a purchase. It cannot be negative or zero.
	 * @since 1.0.0
	 */
	EnhancedPurchaseResponseModel findEnhancedPurchaseByID(Long id, Set<Country> countries);

}
