package com.pacovides.money.test.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;

import com.pacovides.money.model.Ledger;
import com.pacovides.money.model.Transaction;

/**
 * Simple builder used to create mock objects in test environment
 * 
 * @author Francisco
 *
 */
public class TestObjectBuilder {

	/**
	 * Builds a ledger object for testing using the transactions that are passed
	 * 
	 * @return
	 */
	public static Ledger buildLedger(Transaction... transactions) {

		Ledger ledger = new Ledger();
		ledger.setDateCreated(new Date());
		ledger.setName("test Ledger");
		ledger.setDescription("ledger description");
		ledger.setTransactionList(Arrays.asList(transactions));

		return ledger;

	}

	/**
	 * Builds a transaction using amount only. Defaults to USD and current date
	 * 
	 * @param amount
	 * @return
	 */
	public static Transaction buildTransaction(BigDecimal amount) {
		return buildTransaction(new Date(), amount, Currency.getInstance("USD"));
	}

	/**
	 * Builds a transaction using date and amount defaults other values
	 * 
	 * @param date
	 * @param amount
	 * @param currency
	 * @return
	 */
	public static Transaction buildTransaction(Date date, BigDecimal amount, Currency currency) {
		Transaction transaction = new Transaction();
		transaction.setDate(date);
		transaction.setAmount(amount);
		transaction.setCurrency(currency);
		transaction.setDescription("test transaction");
		transaction.setNotes("sample notes");
		transaction.setTransactionLabels(Collections.singletonList("testLabel"));
		return transaction;
	}

}
