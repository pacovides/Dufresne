package org.dufresne.pc.app.gui;

import com.pacovides.money.persistance.LedgerStorage;
import com.pacovides.money.persistance.impl.XMLLedgerStorage;
import com.pacovides.money.service.LedgerService;
import com.pacovides.money.service.impl.SimpleLedgerService;


public class DufresneGUI {

	public static void main(String[] args) {

		// Service initialization
		LedgerStorage ledgerStorage = new XMLLedgerStorage();
		LedgerService ledgerService = new SimpleLedgerService(ledgerStorage);

		// Graphical User Interface initialization
		MainWindow mainWindow = new MainWindow(ledgerService);
		mainWindow.setVisible(true);

	}

}
