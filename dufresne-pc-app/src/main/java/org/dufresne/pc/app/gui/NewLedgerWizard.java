package org.dufresne.pc.app.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.dufresne.pc.app.gui.form.FormFieldType;
import org.dufresne.pc.app.gui.form.SimpleFormPanel;

import com.pacovides.money.service.LedgerService;

public class NewLedgerWizard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5430141554114741177L;

	private LedgerService ledgerService;

	public NewLedgerWizard(LedgerService ledgerService) {
		this.ledgerService = ledgerService;
		customizeAppearance();
		initActions();
		this.setContentPane(createMainPanel());
		this.pack();
	}

	private void customizeAppearance() {
		int width = 300;
		int height = 300;

		this.setPreferredSize(new Dimension(width, height));
		this.setLocationRelativeTo(null);

		this.setMinimumSize(new Dimension(300, 300));
		this.setTitle("Create new ledger");

	}

	private void initActions() {
		// TODO Auto-generated method stub

	}

	private Container createMainPanel() {
		JPanel wizardPanel = new JPanel();
		// TODO add borders + card layout
		wizardPanel.setLayout(new BorderLayout());

		SimpleFormPanel basicLedgerInfoPanel = new SimpleFormPanel();
		basicLedgerInfoPanel.addField(FormFieldType.TEXT, "Ledger Name:", "");
		basicLedgerInfoPanel.addField(FormFieldType.LARGE_TEXT, "Description:", "");
		basicLedgerInfoPanel.addField(FormFieldType.FILE, "File Location:", "");

		wizardPanel.add(basicLedgerInfoPanel, BorderLayout.CENTER);

		return wizardPanel;
	}

}
