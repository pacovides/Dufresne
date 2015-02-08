package com.pacovides.money.persistance.impl;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pacovides.money.exception.DufresneIOException;
import com.pacovides.money.model.Ledger;
import com.pacovides.money.persistance.LedgerStorage;

/**
 * Uses java.beans.XMLEncoder to read write xml files
 * 
 * TODO consider woodstox as an alternative
 * 
 * @author Francisco
 *
 */
public class XMLLedgerStorage implements LedgerStorage {

	private static final Logger logger = LogManager.getLogger(XMLLedgerStorage.class);

	@Override
	public void saveLedger(Ledger ledger, String outputFileName) {
		if (ledger == null) {
			throw new IllegalArgumentException("ledger is null");
		}

		if (StringUtils.isEmpty(outputFileName)) {
			throw new IllegalArgumentException("outputFileName is null");
		}
		/*
		 * File file = new File(outputFileName); if (file.isDirectory() ||
		 * !file.getParentFile().exists()) { throw new
		 * DufresneIOException("The specified file " + outputFileName +
		 * " does not exists or is invalid"); }
		 */
		writeToXML(ledger, outputFileName);

	}

	private void writeToXML(Ledger ledger, String filename) {
		// Try with resources avoids closing the opened resources.
		try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)))) {
			encoder.writeObject(ledger);
			encoder.close();
		} catch (Exception e) {
			String message = "error while saving file " + filename;
			logger.error(message, e);
			throw new DufresneIOException(message, e);
		}

	}

	@Override
	public Ledger openLedger(String file) {
		// TODO Auto-generated method stub
		return null;
	}

}
