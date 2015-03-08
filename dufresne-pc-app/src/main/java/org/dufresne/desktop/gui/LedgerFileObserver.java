package org.dufresne.desktop.gui;

import com.pacovides.money.model.Ledger;

public interface LedgerFileObserver {

	void newLedgerFile(Ledger ledger);

}
