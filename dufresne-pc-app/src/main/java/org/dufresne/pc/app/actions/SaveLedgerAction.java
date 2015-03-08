package org.dufresne.pc.app.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.pacovides.money.service.LedgerService;

public class SaveLedgerAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5280642659863801591L;

	private final Logger logger = LogManager.getLogger(this.getClass());

	private LedgerService ledgerService;

	public SaveLedgerAction(LedgerService ledgerService) {
		// Initializes with name and image TODO
		super("Save Ledger...");
		// Lets make it easy to save
		this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		// We need a controller for when the time comes
		this.ledgerService = ledgerService;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO actually save
		logger.info("ledger saved");

	}

}
