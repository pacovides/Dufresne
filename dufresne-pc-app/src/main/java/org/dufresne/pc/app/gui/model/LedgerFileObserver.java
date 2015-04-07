package org.dufresne.pc.app.gui.model;

import com.pacovides.money.model.Ledger;

public interface LedgerFileObserver {

	void changeActiveLedger(Ledger ledger);

}
