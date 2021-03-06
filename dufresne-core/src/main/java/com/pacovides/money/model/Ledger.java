package com.pacovides.money.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ledger {

	private String name;

	private String description;

	private Date dateCreated;

	private Date lastModified;

	private Map<AccountType, Account> baseAccounts = new HashMap<AccountType, Account>();

	private List<Transaction> transactionList = new ArrayList<Transaction>();

	public Ledger() {
		initializeBaseAccounts();
	}

	/**
	 * Creates the basic accounts that will serve as parent for all other
	 * accounts.
	 */
	private void initializeBaseAccounts() {
		for (AccountType accountType : AccountType.values()) {
			Account baseAccount = new Account(getBaseAccountName(accountType), accountType);
			baseAccounts.put(accountType, baseAccount);
		}
	}

	/**
	 * Given an account type return its display name, in the future this can be
	 * localized.
	 * 
	 * @param accountType
	 * @return
	 */
	private String getBaseAccountName(AccountType accountType) {
		switch (accountType) {
		case ASSET:
			return "Assets";
		case LIABILITY:
			return "Liabilities";
		case INCOME:
			return "Income";
		case EXPENSE:
			return "Expenses";
		default:
			return "Undefined Account";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Account getMainAccount(AccountType type) {
		return baseAccounts.get(type);
	}

	public List<Account> getMainAccountsAsList() {
		return new ArrayList<Account>(baseAccounts.values());
	}

	public Account getAccount(String name, AccountType type) {
		return baseAccounts.get(type).findSubAccount(name);
	}

	public void addAccount(Account account) {
		if (account != null) {
			baseAccounts.get(account.getType()).addSubaccount(account);
		}
	}

	public void addAccount(Account account, Account parent) {
		if (parent != null) {
			// We make sure we add to the account in this ledger
			Account foundParent = getAccount(parent.getName(), parent.getType());
			if (foundParent == null) {
				throw new IllegalArgumentException("invalid parent account " + parent.getName());
			}
			foundParent.addSubaccount(account);
		}
	}

	public void addAccounts(List<Account> accounts) {
		if (accounts != null) {
			for (Account account : accounts) {
				addAccount(account);
			}
		}
	}

	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	/**
	 * Sets a non-null transaction list
	 * 
	 * @param transactionList
	 */
	public void setTransactionList(List<Transaction> transactionList) {
		if (transactionList != null) {
			// We ensure the List implementation remains the same
			this.transactionList = new ArrayList<Transaction>(transactionList);
		}
	}

	public void addTransaction(Transaction transaction) {
		transactionList.add(transaction);
	}

}
