/**
 * 
 */
package com.pacovides.money.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pacovides.money.model.Ledger;
import com.pacovides.money.model.Transaction;
import com.pacovides.money.model.TransactionFilter;
import com.pacovides.money.persistance.LedgerStorage;
import com.pacovides.money.service.LedgerService;

/**
 * @author Francisco
 *
 */
public class SimpleLedgerService implements LedgerService {

	private static final Logger logger = LogManager.getLogger(SimpleLedgerService.class);

	// A common message when trying to operate on an empty ledger
	private static final String MSG_EMPTY_LEDGER = "Attempted to operate on an empty ledger! You should create or open a ledger first";

	private LedgerStorage ledgerStorage;

	private Ledger activeLedger = null;

	/**
	 * The simple ledger service needs a ledger storage to persist data
	 * 
	 * @param ledgerStorage
	 */
	public SimpleLedgerService(LedgerStorage ledgerStorage) {
		this.ledgerStorage = ledgerStorage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pacovides.money.service.LedgerService#saveLedger(com.pacovides.money
	 * .model.Ledger, java.lang.String)
	 */
	@Override
	public void saveLedger(String outputFile) {
		if (activeLedger == null) {
			logger.warn(MSG_EMPTY_LEDGER);
			return;
		}

		logger.info("saving ledger {} to output file {}", activeLedger.getName(), outputFile);
		activeLedger.setLastModified(new Date());

		ledgerStorage.saveLedger(activeLedger, outputFile);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pacovides.money.service.LedgerService#openLedger(java.lang.String)
	 */
	@Override
	public void openLedger(String file) {
		logger.info("reading ledger from {} ", file);
		Ledger ledgerRead = ledgerStorage.openLedger(file);
		if (ledgerRead != null) {
			activeLedger = ledgerRead;
		} else {
			logger.warn("Ledger could not be read.");
		}

	}

	@Override
	public void createNewLedger(String ledgerName, String ledgerDescription) {
		logger.info("creating new ledger with name {} ", ledgerName);
		activeLedger = new Ledger();
		activeLedger.setName(ledgerName);
		activeLedger.setDescription(ledgerDescription);
		activeLedger.setDateCreated(new Date());

	}

	@Override
	public Ledger getLedger() {
		return activeLedger;
	}

	@Override
	public List<Transaction> getFilteredTransactions(TransactionFilter filter) {
		if (activeLedger == null) {
			logger.warn(MSG_EMPTY_LEDGER);
			return new ArrayList<Transaction>();
		}

		// TODO actually filter
		return activeLedger.getTransactionList();
	}

}
