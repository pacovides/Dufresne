/**
 * 
 */
package com.pacovides.money.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pacovides.money.model.Account;
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
	public void saveLedger(String outputFile) {
		if (activeLedger == null) {
			logger.error("Attempted to save empty ledger! You should call createNewLedger(..) first");
			throw new IllegalArgumentException("ledger is null");
		}

		logger.info("saving ledger {} to output file {}", activeLedger.getName(), outputFile);
		activeLedger.setLastModified(new Date());

		ledgerStorage.saveLedger(activeLedger, outputFile);

	}

	/* (non-Javadoc)
	 * @see com.pacovides.money.service.LedgerService#openLedger(java.lang.String)
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
		this.createNewLedger(ledgerName, ledgerDescription, null);

	}

	@Override
	public void createNewLedger(String ledgerName, String ledgerDescription, List<Account> initialAccounts) {
		logger.info("creating new ledger with name {} ", ledgerName);
		activeLedger = new Ledger();
		activeLedger.setName(ledgerName);
		activeLedger.setDescription(ledgerDescription);
		activeLedger.setAccountList(initialAccounts);
		activeLedger.setDateCreated(new Date());

	}

}
