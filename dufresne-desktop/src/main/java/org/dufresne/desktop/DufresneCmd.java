package org.dufresne.desktop;

import com.pacovides.money.model.Ledger;
import com.pacovides.money.service.LedgerService;
import com.pacovides.money.service.impl.SimpleLedgerService;

public class DufresneCmd {

	public static void main(String[] args) {
		LedgerService ledgerService = new SimpleLedgerService();
		Ledger ledger = new Ledger();
		ledger.setName("myLedger");
		ledgerService.saveLedger(ledger, "myFile");

	}

}
