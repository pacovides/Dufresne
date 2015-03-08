package org.dufresne.desktop.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import org.dufresne.desktop.gui.LedgerFileObserver;

import com.pacovides.money.service.LedgerService;

public class OpenLedgerAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7328069339815381671L;

	private LedgerService ledgerService;

	private final JFileChooser fileChooser = new JFileChooser();

	private List<LedgerFileObserver> ledgerFileObservers = new ArrayList<LedgerFileObserver>();

	public OpenLedgerAction(LedgerService ledgerService) {
		// Initializes with name and image TODO
		super("Open Ledger...");
		// We need a controller for when the time comes
		this.ledgerService = ledgerService;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO consider depending on top level frame
		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			ledgerService.openLedger(selectedFile.getPath());
			for (LedgerFileObserver observer : ledgerFileObservers) {
				observer.newLedgerFile(ledgerService.getLedger());
			}
		}
	}

	public void addObserver(LedgerFileObserver observer) {
		ledgerFileObservers.add(observer);
	}

}
