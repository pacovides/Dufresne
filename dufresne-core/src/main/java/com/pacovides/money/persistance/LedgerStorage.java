package com.pacovides.money.persistance;

import com.pacovides.money.model.Ledger;

public interface LedgerStorage {

	/**
	 * Saves the ledger to the specified output file
	 * 
	 * @param ledger
	 * @param outputFile
	 */
	void saveLedger(Ledger ledger, String outputFileName);

	/**
	 * Opens the ledger from the specified input file
	 * 
	 * @param file
	 * @return
	 */
	Ledger openLedger(String file);

}
