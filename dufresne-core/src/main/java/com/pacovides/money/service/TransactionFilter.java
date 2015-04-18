package com.pacovides.money.service;

import java.util.List;

import com.pacovides.money.model.Transaction;

public interface TransactionFilter {

	/**
	 * Filters the list of input transactions and returns a new filtered list.
	 * 
	 * @param inputTransactions
	 * @return
	 */
	List<Transaction> filter(List<Transaction> inputTransactions);

}
