package org.dufresne.pc.app.gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.dufresne.pc.app.gui.model.AccountSelectionObserver;
import org.dufresne.pc.app.gui.model.TransactionsTableModel;

import com.pacovides.money.model.Account;
import com.pacovides.money.model.Transaction;
import com.pacovides.money.service.LedgerService;
import com.pacovides.money.service.impl.AccountBasedFilter;

public class TransactionsWorkspace extends JPanel implements AccountSelectionObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1806120807271778888L;

	private JTable transactionsTable;

	private LedgerService ledgerService;

	public TransactionsWorkspace(LedgerService ledgerService) {
		super(new BorderLayout());
		this.ledgerService = ledgerService;
		transactionsTable = new JTable(null);
		this.add(new JScrollPane(transactionsTable), BorderLayout.CENTER);
	}

	private void setTransactions(List<Transaction> transactions) {
		if (transactions == null) {
			transactionsTable.setModel(null);
		} else {
			TransactionsTableModel tableModel = new TransactionsTableModel(transactions);
			transactionsTable.setModel(tableModel);
		}

		transactionsTable.repaint();
	}

	@Override
	public void accountSelected(Account account) {
		if (account != null) {
			AccountBasedFilter filter = new AccountBasedFilter(account);
			filter.setRecursive(true);// TODO do this based on setting.
			List<Transaction> filteredTransactions = ledgerService.getFilteredTransactions(filter);
			setTransactions(filteredTransactions);
		}

	}

}
