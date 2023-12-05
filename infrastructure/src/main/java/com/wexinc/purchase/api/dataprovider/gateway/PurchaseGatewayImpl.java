package com.wexinc.purchase.api.dataprovider.gateway;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wexinc.purchase.api.dto.PurchaseDTO;
import com.wexinc.purchase.api.gateway.PurchaseGateway;
import com.wexinc.purchase.api.mapper.PurchaseInfrastructureMapper;
import com.wexinc.purchase.api.persistence.repository.PurchaseRepository;
import com.wexinc.purchase.api.shared.constant.ConstantsCore;
import com.wexinc.purchase.api.shared.exception.EntityNotFoundException;

/**
 * Concrete class responsible for actually going to the repository layer to look for a purchase in the database.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see PurchaseRepository
 */
public class PurchaseGatewayImpl implements PurchaseGateway {

	/**
	 * Field responsible for holding the repository that, in fact, will go to the database to create, read, update or
	 * delete a purchase.
	 *
	 * @since 1.0.0
	 */
	private final PurchaseRepository purchaseRepository;

	/**
	 * Field responsible for holding the mapper that will transform a
	 * {@link com.wexinc.purchase.api.persistence.domain.Purchase Purchase} database object into a {@link PurchaseDTO
	 * PurchaseDTO} and vice-versa.
	 *
	 * @since 1.0.0
	 */
	private final PurchaseInfrastructureMapper purchaseInfrastructureMapper;

	/**
	 * Class complete constructor.
	 *
	 * @param purchaseRepositoryParam Parameter responsible for holding the repository that will initialize the
	 *                                {@link #purchaseRepository} field of this class.
	 * @param purchaseMapperParam     Parameter responsible for holding the mapper that will initialize the
	 *                                {@link #purchaseInfrastructureMapper} field of this class.
	 * @since 1.0.0
	 */
	public PurchaseGatewayImpl(final PurchaseRepository purchaseRepositoryParam,
			final PurchaseInfrastructureMapper purchaseMapperParam) {
		super();
		this.purchaseRepository = purchaseRepositoryParam;
		this.purchaseInfrastructureMapper = purchaseMapperParam;
	}

	/**
	 * <p>
	 * {@inheritDoc}
	 * </p>
	 * <p>
	 * For filtering and paging consider using
	 * {@link org.springframework.data.repository.query.QueryByExampleExecutor#findAll(org.springframework.data.domain.Example, org.springframework.data.domain.Pageable)
	 * QueryByExampleExecutor.findAll(Example, Pageable)}.
	 * </p>
	 *
	 * @since 1.0.0
	 */
	@Override
	public List<PurchaseDTO> findAll() {
		return this.purchaseRepository.findAll().stream().map(this.purchaseInfrastructureMapper::fromEntityToDTO)
				.toList();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @since 1.0.0
	 */
	@Override
	public PurchaseDTO findById(final Long id) {
		return this.purchaseInfrastructureMapper.fromEntityToDTO(this.purchaseRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(ConstantsCore.EXCEPTION_MESSAGE_PURCHASE_NOT_FOUND.formatted(id))));
	}

	/**
	 * {@inheritDoc}
	 *
	 * @since 1.0.0
	 */
	@Override
	public boolean existsById(final Long id) {
		return this.purchaseRepository.existsById(id);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @since 1.0.0
	 */
	@Override
	@Transactional
	public PurchaseDTO save(final PurchaseDTO purchase) {
		return this.purchaseInfrastructureMapper.fromEntityToDTO(
				this.purchaseRepository.save(this.purchaseInfrastructureMapper.fromDTOToEntity(purchase)));
	}

	/**
	 * {@inheritDoc}
	 *
	 * @since 1.0.0
	 */
	@Override
	@Transactional
	public void deleteById(final Long id) {
		this.purchaseRepository.deleteById(id);
	}
}
