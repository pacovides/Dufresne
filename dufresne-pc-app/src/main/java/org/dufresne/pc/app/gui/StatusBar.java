package org.dufresne.pc.app.gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.dufresne.pc.app.gui.model.AccountSelectionObserver;

import com.pacovides.money.model.Account;

public class StatusBar extends JPanel implements AccountSelectionObserver {

	private static final long serialVersionUID = 5174844697179542666L;

	private JLabel statusLabel;

	public StatusBar() {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		statusLabel = new JLabel("Create a new ledger or open an existing one to begin...", SwingConstants.LEFT);
		this.add(statusLabel, BorderLayout.LINE_START);
	}

	@Override
	public void accountSelected(Account account) {
		if (account != null) {
			statusLabel.setText("Account: " + account.getName());
		}

	}

}
