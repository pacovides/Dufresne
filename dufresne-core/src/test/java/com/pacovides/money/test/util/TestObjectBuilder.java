package com.pacovides.money.test.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

	public static final String SAMPLE_INCOME_1 = "Almost Legal Business";
	public static final String SAMPLE_INCOME_2 = "Honest Job";
	public static final String SAMPLE_CC = "Visa Rewards Credit Card";
	public static final String SAMPLE_ASSET = "Cash in wallet";
	public static final String SAMPLE_PARENT_EXPENSE = "Food";
	public static final String SAMPLE_PARENT_EXPENSE_2 = "Automotive";
	public static final String SAMPLE_EXPENSE_1 = "Fancy Restaurants";
	public static final String SAMPLE_EXPENSE_2 = "Junk Food";
	public static final String SAMPLE_EXPENSE_3 = "Fuel";
	public static final String SAMPLE_EXPENSE_4 = "Car Maintenence";
	public static final String SAMPLE_EXPENSE_5 = "Rent";
	public static final String SAMPLE_BANK = "Bank of Naboo";

	public static final Currency DEFAULT_TRANSACTION_CURRENCY = Currency.getInstance("USD");
	public static final String DEFAULT_LEDGER_NAME = "test ledger";
	public static final String DEFAULT_LEDGER_DESCRIPTION = "some ledger description";

	/**
	 * Builds a ledger object for testing
	 * 
	 * @return
	 */
	public static Ledger buildEmptyLedger() {
		Ledger ledger = new Ledger();
		ledger.setDateCreated(new Date());
		ledger.setName(DEFAULT_LEDGER_NAME);
		ledger.setDescription(DEFAULT_LEDGER_DESCRIPTION);
		return ledger;

	}

	public static List<Account> buildSampleAccounts() {
		List<Account> sampleAccounts = new ArrayList<Account>();
		sampleAccounts.add(buildSampleAccount(SAMPLE_BANK, AccountType.ASSET, null));
		sampleAccounts.add(buildSampleAccount(SAMPLE_ASSET, AccountType.ASSET, null));
		sampleAccounts.add(buildSampleAccount(SAMPLE_CC, AccountType.LIABILITY, null));
		sampleAccounts.add(buildSampleAccount(SAMPLE_INCOME_1, AccountType.INCOME, null));
		sampleAccounts.add(buildSampleAccount(SAMPLE_INCOME_2, AccountType.INCOME, null));
		Account foodAccount = buildSampleAccount(SAMPLE_PARENT_EXPENSE, AccountType.EXPENSE, null);
		buildSampleAccount(SAMPLE_EXPENSE_1, AccountType.EXPENSE, foodAccount);
		buildSampleAccount(SAMPLE_EXPENSE_2, AccountType.EXPENSE, foodAccount);
		sampleAccounts.add(foodAccount);
		Account autoAccount = buildSampleAccount(SAMPLE_PARENT_EXPENSE_2, AccountType.EXPENSE, null);
		buildSampleAccount(SAMPLE_EXPENSE_3, AccountType.EXPENSE, autoAccount);
		buildSampleAccount(SAMPLE_EXPENSE_4, AccountType.EXPENSE, autoAccount);
		sampleAccounts.add(autoAccount);
		sampleAccounts.add(buildSampleAccount(SAMPLE_EXPENSE_5, AccountType.EXPENSE, null));
		return sampleAccounts;
	}

	/**
	 * Builds a transaction using amount only. Defaults to USD and current date
	 * 
	 * @param amount
	 * 
	 * @return
	 */
	public static Transaction buildTransaction(Account accountFrom, Account accountTo, BigDecimal amount) {
		return buildTransaction(accountFrom, accountTo, new Date(), amount, DEFAULT_TRANSACTION_CURRENCY);
	}

	/**
	 * Builds a transaction using date and amount defaults other values
	 * 
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

	public static Account buildSampleAccount(String name, AccountType type, Account parent) {
		Account account = new Account(name, type);
		account.setAccountLabels(Collections.singletonList("testAccountLabel"));
		account.setDescription("a testing account");
		Map<String, String> accountProperties = new HashMap<String, String>();
		accountProperties.put("accountNumber", "123456");
		accountProperties.put("secretQuestion", "who cares?");
		account.setAccountProperties(accountProperties);
		account.setDefaultCurrency(DEFAULT_TRANSACTION_CURRENCY);
		if (parent != null) {
			parent.addSubaccount(account);
		}

		return account;
	}
}
