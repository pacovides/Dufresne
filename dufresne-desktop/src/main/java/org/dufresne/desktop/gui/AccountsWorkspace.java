package org.dufresne.desktop.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import com.pacovides.money.model.Account;

public class AccountsWorkspace extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3576774609211994941L;

	public AccountsWorkspace() {
		super(new BorderLayout());
		// TODO real impl
		List<Account> mainAccounts = getMainAccounts();

		Account rootAccount = new Account("ALL");
		rootAccount.addSubaccounts(mainAccounts);
		AccountTreeNode rootNode = new AccountTreeNode(rootAccount);

		// Create a tree that allows one selection at a time.
		JTree tree = new JTree(rootNode);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Create the scroll pane and add the tree to it.
		JScrollPane treeView = new JScrollPane(tree);
		this.add(treeView);

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

}
