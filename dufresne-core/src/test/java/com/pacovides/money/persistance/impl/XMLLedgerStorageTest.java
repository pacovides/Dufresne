package com.pacovides.money.persistance.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pacovides.money.model.Ledger;
import com.pacovides.money.test.util.ResourceFilesUtil;
import com.pacovides.money.test.util.TestObjectBuilder;

public class XMLLedgerStorageTest {

	// Temp directory used to store test output
	private static final String TEMP_OUTPUT_DIR = "XMLLedgerStorageTest-tmp";

	// We make some required prep work for the tests to run
	@BeforeClass
	public static void setUp() throws IOException {

		File outputDir = ResourceFilesUtil.getResourceFile(TEMP_OUTPUT_DIR);

		if (outputDir.exists()) {
			System.out.println("cleaning up existing output from " + outputDir.getPath());
			FileUtils.deleteDirectory(outputDir);
		}

		if (!outputDir.mkdir()) {
			Assert.fail("Unnable to create " + outputDir.getPath() + " required for testing.");
		}

	}

	/**
	 * Test saving a ledger with some basic info but no transactions on it
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSaveEmptyLedger() throws IOException {
		File ledgerFile = ResourceFilesUtil.getResourceFile(TEMP_OUTPUT_DIR + "/empty.xml");
		XMLLedgerStorage xmlLedgerStorage = new XMLLedgerStorage();
		xmlLedgerStorage.saveLedger(TestObjectBuilder.buildLedger(), ledgerFile.getPath());
		// Now we check everything was saved correctly
		String storedInfo = ResourceFilesUtil.getResourceFileAsString(ledgerFile);
		Assert.assertNotNull(storedInfo);
		Assert.assertTrue(storedInfo.contains(TestObjectBuilder.DEFAULT_LEDGER_NAME));
	}

	/**
	 * Tests saving a ledger with a few sample transactions
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSaveLedgerWithData() throws IOException {
		File ledgerFile = ResourceFilesUtil.getResourceFile(TEMP_OUTPUT_DIR + "/simple.xml");
		XMLLedgerStorage xmlLedgerStorage = new XMLLedgerStorage();
		Ledger ledger = TestObjectBuilder.buildLedger();
		ledger.addTransaction(TestObjectBuilder.buildTransaction(new BigDecimal(12)));
		ledger.addTransaction(TestObjectBuilder.buildTransaction(new BigDecimal(114)));
		xmlLedgerStorage.saveLedger(ledger, ledgerFile.getPath());
		// Now we check everything was saved correctly
		String storedInfo = ResourceFilesUtil.getResourceFileAsString(ledgerFile);
		Assert.assertNotNull(storedInfo);
		Assert.assertTrue(storedInfo.contains(TestObjectBuilder.DEFAULT_LEDGER_NAME));
		Assert.assertTrue(storedInfo.contains(TestObjectBuilder.DEFAULT_TRANSACTION_CURRENCY));
	}

}
