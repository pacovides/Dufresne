package org.dufresne.pc.app.gui;

import com.pacovides.money.model.Ledger;

public interface LedgerFileObserver {

	void changeActiveLedger(Ledger ledger);

}
