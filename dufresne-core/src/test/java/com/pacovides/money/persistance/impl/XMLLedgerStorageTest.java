package com.pacovides.money.persistance.impl;

import java.math.BigDecimal;

import org.junit.Test;

import com.pacovides.money.model.Ledger;
import com.pacovides.money.test.util.TestObjectBuilder;

public class XMLLedgerStorageTest {

	private static final String OUTPUT_DIR = "outputFiles/";

	@Test
	public void testSaveEmptyLedger() {
		XMLLedgerStorage xmlLedgerStorage = new XMLLedgerStorage();
		xmlLedgerStorage.saveLedger(TestObjectBuilder.buildLedger(), "outputFiles/empty.xml");
	}

	@Test
	public void testSaveLedgerWithData() {
		XMLLedgerStorage xmlLedgerStorage = new XMLLedgerStorage();
		Ledger ledger = TestObjectBuilder.buildLedger();
		ledger.addTransaction(TestObjectBuilder.buildTransaction(new BigDecimal(12)));
		ledger.addTransaction(TestObjectBuilder.buildTransaction(new BigDecimal(114)));

		xmlLedgerStorage.saveLedger(ledger, OUTPUT_DIR + "simple.xml");
	}

}
