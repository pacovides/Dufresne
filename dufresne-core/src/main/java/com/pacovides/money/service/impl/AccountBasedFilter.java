package com.pacovides.money.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pacovides.money.model.Account;
import com.pacovides.money.model.Transaction;
import com.pacovides.money.service.TransactionFilter;

public class AccountBasedFilter implements TransactionFilter {

	private Account account;

	private boolean recursive;

	public AccountBasedFilter(Account account) {
		if (account == null) {
			throw new IllegalArgumentException("account cannot be null.");
		}
		this.account = account;
		recursive = false;
	}

	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
	}

	@Override
	public List<Transaction> filter(List<Transaction> inputTransactions) {

		if (inputTransactions == null) {
			return null;
		}

		List<Transaction> outputTransactions = new ArrayList<Transaction>();
		for (Transaction transaction : inputTransactions) {
			if (transaction.getAccountFrom().equals(account)) {
				outputTransactions.add(transaction);
			} else if (transaction.getAccountTo().equals(account)) {
				outputTransactions.add(transaction);
			}
		}

		if (recursive && account.hasSubAccounts()) {
			for (Account subAccount : account.getDirectSubAccounts()) {
				AccountBasedFilter subFilter = new AccountBasedFilter(subAccount);
				outputTransactions.addAll(subFilter.filter(inputTransactions));
			}
		}

		return outputTransactions;
	}

}
