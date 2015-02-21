/**
 * 
 */
package com.pacovides.money.service.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pacovides.money.model.Ledger;
import com.pacovides.money.persistance.LedgerStorage;
import com.pacovides.money.service.LedgerService;

/**
 * @author Francisco
 *
 */
public class SimpleLedgerService implements LedgerService {

	private static final Logger logger = LogManager.getLogger(SimpleLedgerService.class);

	private LedgerStorage ledgerStorage;

	private Ledger activeLedger;

	/**
	 * The simple ledger service needs a ledger storage to persist data
	 * 
	 * @param ledgerStorage
	 */
	public SimpleLedgerService(LedgerStorage ledgerStorage) {
		this.ledgerStorage = ledgerStorage;
		this.activeLedger = new Ledger();
	}

	/* (non-Javadoc)
	 * @see com.pacovides.money.service.LedgerService#saveLedger(com.pacovides.money.model.Ledger, java.lang.String)
	 */
	@Override
	public void saveLedger(Ledger ledger, String outputFile) {
		if (ledger == null) {
			logger.error("Attempted to save null ledger!");
			throw new IllegalArgumentException("ledger is null");
		}

		logger.info("saving ledger {} to output file {}", ledger.getName(), outputFile);
		ledger.setLastModified(new Date());

		// If creation date is not yet set this must be a new ledger, set
		// creation date to now
		if (ledger.getDateCreated() == null) {
			ledger.setDateCreated(new Date());
		}

		ledgerStorage.saveLedger(ledger, outputFile);

	}

	/* (non-Javadoc)
	 * @see com.pacovides.money.service.LedgerService#openLedger(java.lang.String)
	 */
	@Override
	public Ledger openLedger(String file) {
		logger.info("reading ledger from {} ", file);
		Ledger ledgerRead = ledgerStorage.openLedger(file);
		if (ledgerRead != null) {
			activeLedger = ledgerRead;
		} else {
			logger.warn("Ledger could not be read.");
		}

		return activeLedger;
	}

}
