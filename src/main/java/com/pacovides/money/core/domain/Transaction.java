package com.pacovides.money.core.domain;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.List;

public class Transaction {

	/**
	 * Short description of the transaction. (e.g. "rent may",
	 * "groceries at heb", "dinner with stacy at straits"...) Ideally more
	 * detailed information of the transaction can be inputed in notes. This is
	 * meant mostly to facilitate display and summaries. Where the information
	 * will be truncated due to space limitations.
	 */
	private String description;

	/**
	 * Full description of the transaction or additional notes.
	 */
	private String notes;

	/**
	 * Date/time when the transaction took place
	 */
	private Date date;

	/**
	 * Amount of the transaction in the specified currency
	 */
	private BigDecimal amount;

	/**
	 * Currency in which the transaction took place
	 */
	private Currency currency;

	/**
	 * A transaction can have as many labels as needed. This can be used to
	 * group transactions by label. Note that this is independent of the source
	 * or destiny account. The relationship between a label and a transaction is
	 * many to many
	 */
	private List<String> transactionLabels;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public List<String> getTransactionLabels() {
		return transactionLabels;
	}

	public void setTransactionLabels(List<String> transactionLabels) {
		this.transactionLabels = transactionLabels;
	}

}
