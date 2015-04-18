package org.dufresne.pc.app.gui.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.pacovides.money.model.Transaction;

public class TransactionsTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5523615266397049761L;

	private List<String> columnNames = new ArrayList<String>();

	// Active transactions can be empty but they should not be null at any time
	private List<Transaction> activeTransactions = new ArrayList<Transaction>();

	private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");

	/**
	 * Formats a currency using the system locale
	 */
	private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

	public TransactionsTableModel(List<Transaction> activeTransactions) {
		super();
		if (activeTransactions != null) {
			this.activeTransactions = activeTransactions;
		}

		initializeColumnNames();
	}

	private void initializeColumnNames() {

		// Right now we are order dependent, messing with this order will mess
		// up the rendering. More elegant ways of doing this are welcome.
		// A map maybe?
		columnNames.add("Date");
		columnNames.add("Description");
		columnNames.add("Amount");
		columnNames.add("from Account");
		columnNames.add("to Account");
	}

	@Override
	public int getRowCount() {
		return activeTransactions.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public String getColumnName(int column) {
		return columnNames.get(column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Transaction transaction = activeTransactions.get(rowIndex);

		switch (columnIndex) {
		case 0: // Date
			return formatDate(transaction.getDate());
		case 1: // Description
			return transaction.getDescription();
		case 2: // Amount
			return formatAmount(transaction.getAmount(), transaction.getCurrency());
		case 3:// Account from
			return transaction.getAccountFrom().getName();
		case 4:// Account to
			return transaction.getAccountTo().getName();
		default:
			return "???";
		}

	}

	private Object formatAmount(BigDecimal amount, Currency currency) {
		return currencyFormatter.format(amount.doubleValue());
	}

	private Object formatDate(Date date) {
		return dateFormatter.format(date);
	}

	public List<Transaction> getActiveTransactions() {
		return activeTransactions;
	}

	public void setActiveTransactions(List<Transaction> activeTransactions) {
		if (activeTransactions != null) {
			this.activeTransactions = activeTransactions;
		}
	}

}
