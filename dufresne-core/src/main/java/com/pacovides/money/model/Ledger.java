package com.pacovides.money.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ledger {

	private List<Transaction> transactionList = new ArrayList<Transaction>();

	private List<Account> accountList = new ArrayList<Account>();

	private String name;

	private String description;

	private Date dateCreated;

	private Date lastModified;

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

	public List<Account> getAccountList() {
		return accountList;
	}

	/**
	 * Sets a non-null account list
	 * 
	 * @param accountList
	 */
	public void setAccountList(List<Account> accountList) {
		if (accountList != null) {
			// We ensure the List implementation remains the same
			this.accountList = new ArrayList<Account>(accountList);
		}
	}

	/**
	 * Adds a single account to the account list
	 * 
	 * @param account
	 */
	public void addAccount(Account account) {
		accountList.add(account);
	}

}
