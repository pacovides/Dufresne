package com.pacovides.money.service;

import com.pacovides.money.model.Ledger;

public interface LedgerService {
	
	/**
	 * Saves the ledger to the specified output file
	 * 
	 * @param ledger
	 * @param outputFile
	 */
	void saveLedger(Ledger ledger, String outputFile);

	/**
	 * Opens the ledger from the specified input file
	 * 
	 * @param file
	 * @return
	 */
	Ledger openLedger(String file);

}
