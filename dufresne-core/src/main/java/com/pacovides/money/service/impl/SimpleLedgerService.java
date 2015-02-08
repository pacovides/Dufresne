/**
 * 
 */
package com.pacovides.money.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pacovides.money.model.Ledger;
import com.pacovides.money.service.LedgerService;

/**
 * @author Francisco
 *
 */
public class SimpleLedgerService implements LedgerService {

	private static final Logger logger = LogManager.getLogger(SimpleLedgerService.class);

	/* (non-Javadoc)
	 * @see com.pacovides.money.service.LedgerService#saveLedger(com.pacovides.money.model.Ledger, java.lang.String)
	 */
	@Override
	public void saveLedger(Ledger ledger, String outputFile) {
		if (ledger == null) {
			logger.error("Attempted to save null ledger!");
			throw new IllegalArgumentException("ledger is null");
		}

		// Checkout http://www.rgagnon.com/javadetails/java-0470.html

		logger.info("saving ledger {} to output file {}", ledger.getName(), outputFile);
		// TODO real impl.

	}

	/* (non-Javadoc)
	 * @see com.pacovides.money.service.LedgerService#openLedger(java.lang.String)
	 */
	@Override
	public Ledger openLedger(String file) {

		logger.info("reading ledger from {} ", file);

		// TODO Real impl
		return null;
	}

}
