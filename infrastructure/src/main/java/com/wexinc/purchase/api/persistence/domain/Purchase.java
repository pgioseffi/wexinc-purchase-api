package com.wexinc.purchase.api.persistence.domain;

import com.wexinc.purchase.api.shared.constant.ConstantsInfrastructure;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Class responsible for maintaining the abstraction of information with the purchase table in the
 * database.
 *
 * @author <a href="mailto:pgioseffi@gmail.com">Philippe Gioseffi &lt;pgioseffi@gmail.com&gt;</a>
 * @since 1.0.0
 * @version 1.0.0
 */
@Entity
@Table(name = "purchase")
public class Purchase {

  /**
   * Field responsible for holding the purchase positive numeric identifier.
   *
   * @since 1.0.0
   */
  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = ConstantsInfrastructure.PURCHASE_IDENTIFICATION_SEQUENCE_GENERATOR)
  @SequenceGenerator(
      name = ConstantsInfrastructure.PURCHASE_IDENTIFICATION_SEQUENCE_GENERATOR,
      sequenceName = ConstantsInfrastructure.PURCHASE_IDENTIFICATION_SEQUENCE,
      allocationSize = 1)
  @Positive
  private Long id;

  /**
   * Field responsible for holding the purchase description. Must not be null or empty and can have
   * only fifty characters.
   *
   * @since 1.0.0
   */
  @NotBlank
  @Size(max = 50)
  private String description;

  /**
   * Field responsible for holding the purchase transaction date. Must be in the past.
   *
   * @since 1.0.0
   */
  @NotNull @Past private LocalDateTime transactionDate;

  /**
   * Field responsible for holding the purchase amount. Must be a positive decimal number.
   *
   * @since 1.0.0
   */
  @NotNull @Positive private BigDecimal amount;

  /**
   * Empty constructor.
   *
   * @since 1.0.0
   */
  public Purchase() {
    super();
  }

  /**
   * Complete constructor.
   *
   * @param idParam Parameter responsible to set the value of the {@link #id} field.
   * @param descriptionParam Parameter responsible to set the value of the {@link #description}
   *     field.
   * @param transactionDateParam Parameter responsible to set the value of the {@link
   *     #transactionDate} field.
   * @param amountParam Parameter responsible to set the value of the {@link #amount} field.
   * @since 1.0.0
   */
  public Purchase(
      @Positive final Long idParam,
      @NotBlank @Size(max = 50) final String descriptionParam,
      @NotNull @Past final LocalDateTime transactionDateParam,
      @NotNull @Positive final BigDecimal amountParam) {
    this();
    this.id = idParam;
    this.description = descriptionParam;
    this.transactionDate = transactionDateParam;
    this.amount = amountParam;
  }

  /**
   * Method responsible for returning the <code>long</code> value of the {@link #id} field.
   *
   * @return The <code>long</code> value of the {@link #id} field.
   * @since 1.0.0
   */
  public Long getId() {
    return this.id;
  }

  /**
   * Method responsible to set the value of the {@link #id} field.
   *
   * @param idParam Parameter responsible to set the value of the {@link #id} field.
   * @since 1.0.0
   */
  public void setId(final Long idParam) {
    this.id = idParam;
  }

  /**
   * Method responsible for returning the {@link String} value of the {@link #description} field.
   *
   * @return The {@link String} value of the {@link #description} field.
   * @since 1.0.0
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Method responsible to set the value of the {@link #description} field.
   *
   * @param descriptionParam Parameter responsible to set the value of the {@link #description}
   *     field.
   * @since 1.0.0
   */
  public void setDescription(final String descriptionParam) {
    this.description = descriptionParam;
  }

  /**
   * Method responsible for returning the {@link LocalDateTime} value of the {@link
   * #transactionDate} field.
   *
   * @return The {@link LocalDateTime} value of the {@link #transactionDate} field.
   * @since 1.0.0
   */
  public LocalDateTime getTransactionDate() {
    return this.transactionDate;
  }

  /**
   * Method responsible to set the value of the {@link #transactionDate} field.
   *
   * @param transactionDateParam Parameter responsible to set the value of the {@link
   *     #transactionDate} field.
   * @since 1.0.0
   */
  public void setTransactionDate(final LocalDateTime transactionDateParam) {
    this.transactionDate = transactionDateParam;
  }

  /**
   * Method responsible for returning the {@link BigDecimal} value of the {@link #amount} field.
   *
   * @return The {@link BigDecimal} value of the {@link #amount} field.
   * @since 1.0.0
   */
  public BigDecimal getAmount() {
    return this.amount;
  }

  /**
   * Method responsible to set the value of the {@link #amount} field.
   *
   * @param amountParam Parameter responsible to set the value of the {@link #amount} field.
   * @since 1.0.0
   */
  public void setAmount(final BigDecimal amountParam) {
    this.amount = amountParam;
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.0.0
   */
  @Override
  public int hashCode() {
    return Objects.hash(Long.valueOf(this.id));
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.0.0
   */
  @Override
  public boolean equals(final Object obj) {
    return this == obj || obj instanceof final Purchase other && this.id == other.id;
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.0.0
   */
  @Override
  public String toString() {
    return "Purchase [id=%d, description=%s, transactionDate=%s, amount=%.2f]"
        .formatted(Long.valueOf(this.id), this.description, this.transactionDate, this.amount);
  }
}
