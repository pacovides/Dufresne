package org.dufresne.pc.app.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pacovides.money.exception.DufresneIOException;
import com.pacovides.money.service.LedgerService;

public class SaveLedgerAction extends AbstractAction {

	private static final long serialVersionUID = 5280642659863801591L;

	private final Logger logger = LogManager.getLogger(this.getClass());

	private final JFileChooser fileChooser = new JFileChooser();

	private LedgerService ledgerService;

	public SaveLedgerAction(LedgerService ledgerService) {
		// Initializes with name and image TODO
		super("Save Ledger...");
		// Lets make it easy to save
		this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		// We need a controller for when the time comes
		this.ledgerService = ledgerService;

		// In this case we are only interested in files to save
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		saveLedger();
	}

	private void saveLedger() {
		// TODO consider depending on top level frame
		int returnValue = fileChooser.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			try {
				ledgerService.saveLedger(selectedFile.getPath());
				logger.info("ledger saved");
			} catch (DufresneIOException ioEx) {
				JOptionPane.showMessageDialog(null, "Unnable to save file. " + ioEx.getMessage(), "Ooops!",
						JOptionPane.ERROR_MESSAGE);
				logger.error("Error opening file", ioEx);
			}
		}
	}

}
