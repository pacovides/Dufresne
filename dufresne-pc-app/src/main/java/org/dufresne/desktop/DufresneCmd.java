package org.dufresne.desktop;

import com.pacovides.money.model.Ledger;
import com.pacovides.money.persistance.LedgerStorage;
import com.pacovides.money.persistance.impl.XMLLedgerStorage;
import com.pacovides.money.service.LedgerService;
import com.pacovides.money.service.impl.SimpleLedgerService;

public class DufresneCmd {

	public static void main(String[] args) {
		LedgerStorage ledgerStorage = new XMLLedgerStorage();
		LedgerService ledgerService = new SimpleLedgerService(ledgerStorage);
		Ledger ledger = new Ledger();
		ledger.setName("myLedger");
		ledgerService.createNewLedger("myLedger", "my ledger's description");
		ledgerService.saveLedger("myFile");

	}

}
