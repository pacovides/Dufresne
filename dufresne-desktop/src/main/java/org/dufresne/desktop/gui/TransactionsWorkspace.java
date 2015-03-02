package org.dufresne.desktop.gui;

import java.awt.BorderLayout;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.pacovides.money.model.Transaction;

public class TransactionsWorkspace extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1806120807271778888L;

	private JTable transactionsTable;

	public TransactionsWorkspace() {
		super(new BorderLayout());
		createTransactionsTable();
		this.add(new JScrollPane(transactionsTable), BorderLayout.CENTER);
	}

	private void createTransactionsTable() {
		List<Transaction> sampleTransactions = new ArrayList<Transaction>();
		Transaction sample1 = new Transaction();
		sample1.setDate(new Date());
		sample1.setDescription("buy some stuff");
		sample1.setAmount(new BigDecimal(10));
		sampleTransactions.add(sample1);

		Transaction sample2 = new Transaction();
		sample2.setDate(new Date());
		sample2.setDescription("buy more stuff");
		sample2.setAmount(new BigDecimal(25.20));
		sampleTransactions.add(sample2);

		TransactionsTableModel tableModel = new TransactionsTableModel(sampleTransactions);

		transactionsTable = new JTable(tableModel);

	}

}
