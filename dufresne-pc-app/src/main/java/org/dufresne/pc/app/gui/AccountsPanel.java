package org.dufresne.pc.app.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.pacovides.money.model.Account;
import com.pacovides.money.model.AccountType;
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

	private void setAccounts(List<Account> mainAccounts) {
		// We create a root node with the main accounts
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Accounts");
		for (Account mainAccount : mainAccounts) {
			AccountTreeNode mainNode = new AccountTreeNode(mainAccount);
			root.add(mainNode);
		}
		TreeModel model = new DefaultTreeModel(root);
		accountsTree.setModel(model);
		accountsTree.repaint();
	}

	// Mock impl
	private List<Account> getMainAccounts() {
		List<Account> mainAccounts = new ArrayList<Account>();

		Account assets = new Account("assets", AccountType.ASSET);
		Account bankA = new Account("bancopailas", AccountType.ASSET);
		Account cash = new Account("cash-lucas", AccountType.ASSET);
		assets.addSubaccount(bankA);
		assets.addSubaccount(cash);
		mainAccounts.add(assets);

		Account liability = new Account("liability", AccountType.LIABILITY);
		Account creditCard = new Account("visa", AccountType.LIABILITY);
		liability.addSubaccount(creditCard);
		mainAccounts.add(liability);

		Account expense = new Account("expense", AccountType.EXPENSE);
		Account food = new Account("food", AccountType.EXPENSE);
		Account porn = new Account("porn", AccountType.EXPENSE);
		expense.addSubaccount(food);
		expense.addSubaccount(porn);
		mainAccounts.add(expense);

		Account income = new Account("income", AccountType.INCOME);
		Account empanadaSales = new Account("empanadaSales", AccountType.INCOME);
		income.addSubaccount(empanadaSales);
		mainAccounts.add(income);
		return mainAccounts;
	}

	@Override
	public void newLedgerFile(Ledger ledger) {
		this.setAccounts(ledger.getBaseAccountsAsList());

	}

}
