package com.pacovides.money.service;

import java.util.List;

import com.pacovides.money.model.Account;

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
	 * Clears out the existing ledger and creates a new one with the specified
	 * name and description and initial set of accounts
	 * 
	 * @param ledgerName
	 */
	void createNewLedger(String ledgerName, String ledgerDescription, List<Account> initialAccounts);

}
