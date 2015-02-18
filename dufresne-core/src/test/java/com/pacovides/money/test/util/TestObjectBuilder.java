package com.pacovides.money.test.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.pacovides.money.model.Account;
import com.pacovides.money.model.AccountType;
import com.pacovides.money.model.Ledger;
import com.pacovides.money.model.Transaction;

/**
 * Simple builder used to create mock objects in test environment
 * 
 * @author Francisco
 *
 */
public class TestObjectBuilder {

	public static final String ACCOUNT_A_NAME = "accountA";
	public static final String ACCOUNT_B_NAME = "accountB";
	public static final String ACCOUNT_C_NAME = "accountC";

	public static final Currency DEFAULT_TRANSACTION_CURRENCY = Currency.getInstance("USD");
	public static final String DEFAULT_LEDGER_NAME = "test ledger";
	public static final String DEFAULT_LEDGER_DESCRIPTION = "some ledger description";

	/**
	 * Builds a ledger object for testing using the transactions that are passed
	 * 
	 * @return
	 */
	public static Ledger buildLedger(Transaction... transactions) {

		Ledger ledger = new Ledger();
		ledger.setDateCreated(new Date());
		ledger.setName(DEFAULT_LEDGER_NAME);
		ledger.setDescription(DEFAULT_LEDGER_DESCRIPTION);
		ledger.setTransactionList(Arrays.asList(transactions));

		// We add a few accounts to test
		ledger.addAccount(buildAccount(ACCOUNT_A_NAME));
		ledger.addAccount(buildAccount(ACCOUNT_B_NAME));
		ledger.addAccount(buildAccount(ACCOUNT_C_NAME));

		return ledger;

	}

	/**
	 * Builds a transaction using amount only. Defaults to USD and current date
	 * @param amount
	 * 
	 * @return
	 */
	public static Transaction buildTransaction(Account accountFrom, Account accountTo, BigDecimal amount) {
		return buildTransaction(accountFrom, accountTo, new Date(), amount, DEFAULT_TRANSACTION_CURRENCY);
	}

	/**
	 * Builds a transaction using date and amount defaults other values
	 * @param date
	 * @param amount
	 * @param currency
	 * 
	 * @return
	 */
	public static Transaction buildTransaction(Account accountFrom, Account accountTo, Date date, BigDecimal amount,
			Currency currency) {
		Transaction transaction = new Transaction();
		transaction.setAccountTo(accountTo);
		transaction.setAccountFrom(accountFrom);
		transaction.setDate(date);
		transaction.setAmount(amount);
		transaction.setCurrency(currency);
		transaction.setDescription("test transaction");
		transaction.setNotes("sample notes");
		transaction.setTransactionLabels(Collections.singletonList("testTransactionLabel"));
		return transaction;
	}

	public static Account buildAccount(String name) {
		Account account = new Account();
		account.setName(name);
		account.setAccountLabels(Collections.singletonList("testAccountLabel"));
		account.setDescription("a testing account");
		Map<String, String> accountProperties = new HashMap<String, String>();
		accountProperties.put("accountNumber", "123456");
		accountProperties.put("secretQuestion", "who cares?");
		account.setAccountProperties(accountProperties);
		account.setDefaultCurrency(DEFAULT_TRANSACTION_CURRENCY);
		account.setType(AccountType.ASSET);

		return account;
	}
}
