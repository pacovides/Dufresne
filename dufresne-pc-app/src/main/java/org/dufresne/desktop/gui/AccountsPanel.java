package org.dufresne.desktop.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.pacovides.money.model.Account;
import com.pacovides.money.model.Ledger;

public class AccountsPanel extends JPanel implements LedgerFileObserver {

	private JTree accountsTree;

	/**
	 * 
	 */
	private static final long serialVersionUID = -3576774609211994941L;

	public AccountsPanel() {
		super(new BorderLayout());

		// Create a tree that allows one selection at a time.
		accountsTree = new JTree();
		accountsTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		accountsTree.setRootVisible(false);
		accountsTree.setShowsRootHandles(true);

		// Create the scroll pane and add the tree to it.
		JScrollPane treeView = new JScrollPane(accountsTree);

		this.add(treeView);

		List<Account> mainAccounts = getMainAccounts();

		setAccounts(mainAccounts);

	}

	private void setAccounts(List<Account> accounts) {
		Account rootAccount = new Account("ALL");
		rootAccount.addSubaccounts(accounts);
		AccountTreeNode rootNode = new AccountTreeNode(rootAccount);
		TreeModel model = new DefaultTreeModel(rootNode);
		accountsTree.setModel(model);
		accountsTree.repaint();
	}

	// Mock impl
	private List<Account> getMainAccounts() {
		List<Account> mainAccounts = new ArrayList<Account>();

		Account assets = new Account("assets");
		Account bankA = new Account("bancopailas");
		Account cash = new Account("cash-lucas");
		assets.addSubaccount(bankA);
		assets.addSubaccount(cash);
		mainAccounts.add(assets);

		Account liability = new Account("liability");
		mainAccounts.add(liability);

		Account expense = new Account("expense");
		Account food = new Account("food");
		Account porn = new Account("porn");
		expense.addSubaccount(food);
		expense.addSubaccount(porn);
		mainAccounts.add(expense);

		Account income = new Account("income");
		Account empanadaSales = new Account("empanadaSales");
		income.addSubaccount(empanadaSales);
		mainAccounts.add(income);
		return mainAccounts;
	}

	@Override
	public void newLedgerFile(Ledger ledger) {
		this.setAccounts(ledger.getAccountList());

	}

}
