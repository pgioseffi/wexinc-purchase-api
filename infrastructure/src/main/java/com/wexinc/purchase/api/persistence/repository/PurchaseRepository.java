package com.wexinc.purchase.api.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wexinc.purchase.api.persistence.domain.Purchase;

/**
 * Interface responsible for holding the signatures of methods written by developers for access and persistence to/in
 * the database, as well as pre-defined Spring methods.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 * @see Long
 * @see JpaRepository
 * @see Purchase
 */
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	// No methods to implement so far, taking advantage of those provided by Spring itself.
}
