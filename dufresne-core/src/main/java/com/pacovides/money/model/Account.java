package com.pacovides.money.model;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Map;

public class Account {

	private AccountType type;

	/**
	 * The account name should be unique within a ledger.
	 */
	private String name;
	private Currency defaultCurrency;
	private String description;
	private Map<String, String> accountProperties;
	private List<String> accountLabels;

	private Account parentAccount;
	private List<Account> subAccounts = new ArrayList<Account>();

	public Account() {
	}

	public Account(String name) {
		this.name = name;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getParentAccount() {
		return parentAccount;
	}

	// We disallow external classes to directly set the parent, instead the
	// parent class should be created first and when the child is added the
	// parent wil be set.
	private void setParentAccount(Account parentAccount) {
		this.parentAccount = parentAccount;
	}

	public Currency getDefaultCurrency() {
		return defaultCurrency;
	}

	public void setDefaultCurrency(Currency defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getAccountProperties() {
		return accountProperties;
	}

	public void setAccountProperties(Map<String, String> accountProperties) {
		this.accountProperties = accountProperties;
	}

	public List<String> getAccountLabels() {
		return accountLabels;
	}

	public void setAccountLabels(List<String> accountLabels) {
		this.accountLabels = accountLabels;
	}

	public void addSubaccount(Account subAccount) {
		// We link two-ways for easy navigation
		subAccount.setParentAccount(this);
		subAccounts.add(subAccount);
	}

	public void addSubaccounts(List<Account> additionalAccounts) {
		for (Account additionalAccount : additionalAccounts) {
			// We link two-ways for easy navigation
			additionalAccount.setParentAccount(this);
			subAccounts.add(additionalAccount);
		}
	}

	public void removeSubAccount(Account subAccount) {
		subAccounts.remove(subAccount);
	}

	public boolean hasSubAccounts() {
		return !subAccounts.isEmpty();
	}

	public List<Account> getSubAccounts() {
		return subAccounts;
	}

	@Override
	public String toString() {
		return this.getName();
	}

}
