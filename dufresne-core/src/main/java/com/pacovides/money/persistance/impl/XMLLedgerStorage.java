package com.pacovides.money.persistance.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pacovides.money.exception.DufresneIOException;
import com.pacovides.money.model.Account;
import com.pacovides.money.model.Ledger;
import com.pacovides.money.model.Transaction;
import com.pacovides.money.persistance.LedgerStorage;
import com.thoughtworks.xstream.XStream;

/**
 * Uses Xstream to read write xml files
 * 
 * @author Francisco
 *
 */
public class XMLLedgerStorage implements LedgerStorage {

	private static final Logger logger = LogManager.getLogger(XMLLedgerStorage.class);

	// We force write to UTF8 to avoid encoding issues
	private final static Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;

	private final XStream xstream = new XStream();

	public XMLLedgerStorage() {
		// We add some nicer aliases for readability
		xstream.alias("ledger", Ledger.class);
		xstream.alias("account", Account.class);
		xstream.alias("transaction", Transaction.class);

		// We use id references that result in more human-readable output
		xstream.setMode(XStream.ID_REFERENCES);
	}

	@Override
	public void saveLedger(Ledger ledger, String outputFileName) {
		if (ledger == null) {
			throw new IllegalArgumentException("ledger is null");
		}

		if (StringUtils.isEmpty(outputFileName)) {
			throw new IllegalArgumentException("outputFileName is null");
		}

		writeToXML(ledger, outputFileName);

	}

	private void writeToXML(Ledger ledger, String filename) {

		String xml = xstream.toXML(ledger);
		logger.debug("Saving ledger to " + filename);
		logger.trace(xml);

		File outputFile = new File(filename);
		writeStringToFile(xml, outputFile);

	}

	private void writeStringToFile(String content, File file) {
		try {
			FileUtils.writeStringToFile(file, content, DEFAULT_ENCODING);
		} catch (IOException e) {
			String message = "error while saving file " + file.getName();
			logger.error(message, e);
			throw new DufresneIOException(message, e);
		}
	}

	@Override
	public Ledger openLedger(String fileName) {
		try {
			File file = new File(fileName);
			String ledgerXml = FileUtils.readFileToString(file);
			return (Ledger) xstream.fromXML(ledgerXml);
		} catch (IOException e) {
			String message = "error while reading from file " + fileName;
			logger.error(message, e);
			throw new DufresneIOException(message, e);
		}
	}

}
