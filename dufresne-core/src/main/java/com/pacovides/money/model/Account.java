package com.pacovides.money.model;

import java.util.Currency;
import java.util.List;
import java.util.Map;

public class Account {
	
	private AccountType type;

	/**
	 * The account name should be unique within a ledger.
	 */
	private String name;
	private Account parentAccount;
	private Currency defaultCurrency;
	private String description;
	private Map<String, String> accountProperties;
	private List<String> accountLabels;

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

	public void setParentAccount(Account parentAccount) {
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

}
