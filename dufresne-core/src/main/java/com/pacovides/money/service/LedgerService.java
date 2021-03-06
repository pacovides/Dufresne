package com.pacovides.money.service;

import java.util.List;

import com.pacovides.money.model.Ledger;
import com.pacovides.money.model.Transaction;

public interface LedgerService {

	/**
	 * Saves the active ledger to the specified output file
	 * 
	 * @param ledger
	 * @param outputFile
	 */
	void saveLedger(String outputFile);

	/**
	 * Opens the ledger from the specified input file and makes it the active
	 * ledger
	 * 
	 * @param file
	 * @return
	 */
	void openLedger(String file);

	/**
	 * Clears out the existing ledger and creates a new one with the specified
	 * name and description. No accounts are created.
	 * 
	 * @param ledgerName
	 */
	void createNewLedger(String ledgerName, String ledgerDescription);

	/**
	 * Basic getter, returns the complete active ledger. It is discouraged to
	 * operate on it. Instead use the service to modify.
	 * 
	 * @return
	 */
	Ledger getLedger();

	/**
	 * Given a particular filter it returns the transactions passed.
	 * 
	 * @param filter
	 * @return
	 */
	List<Transaction> getFilteredTransactions(TransactionFilter filter);



}
