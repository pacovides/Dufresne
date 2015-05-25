package org.dufresne.pc.app.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dufresne.pc.app.gui.NewLedgerWizard;
import org.dufresne.pc.app.gui.model.LedgerFileObserver;

import com.pacovides.money.exception.DufresneIOException;
import com.pacovides.money.service.LedgerService;

public class NewLedgerAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7775731649289865015L;

	private static final Logger logger = LogManager.getLogger(NewLedgerAction.class);

	private LedgerService ledgerService;

	private final JFileChooser fileChooser = new JFileChooser();

	private List<LedgerFileObserver> ledgerFileObservers = new ArrayList<LedgerFileObserver>();

	public NewLedgerAction(LedgerService ledgerService) {
		// Initializes with name and image TODO
		super("New Ledger...");
		// We need a controller to delegate to
		this.ledgerService = ledgerService;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// We create a new wizard upon each action.
		NewLedgerWizard wizard = new NewLedgerWizard(ledgerService);
		wizard.setVisible(true);
	}

	private void saveLedger() {
		// TODO consider depending on top level frame
		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			try {
				ledgerService.openLedger(selectedFile.getPath());
			} catch (DufresneIOException ioEx) {
				JOptionPane.showMessageDialog(null, "Unnable to open file. " + ioEx.getMessage(), "Ooops!",
						JOptionPane.ERROR_MESSAGE);
				logger.error("Error opening file", ioEx);
			}
			if (ledgerService.getLedger() != null) {
				for (LedgerFileObserver observer : ledgerFileObservers) {
					observer.changedActiveLedger(ledgerService.getLedger());
				}
				logger.info("ledger opened {}", ledgerService.getLedger().getName());
			}
		}
	}

	public void addObserver(LedgerFileObserver observer) {
		ledgerFileObservers.add(observer);
	}

}
