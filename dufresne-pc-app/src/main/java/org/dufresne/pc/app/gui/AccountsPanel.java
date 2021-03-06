package org.dufresne.pc.app.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dufresne.pc.app.gui.model.AccountSelectionObserver;
import org.dufresne.pc.app.gui.model.AccountTreeNode;
import org.dufresne.pc.app.gui.model.LedgerFileObserver;

import com.pacovides.money.model.Account;
import com.pacovides.money.model.Ledger;

public class AccountsPanel extends JPanel implements LedgerFileObserver, TreeSelectionListener {

	private JTree accountsTree;

	private static final long serialVersionUID = -3576774609211994941L;

	private final Logger logger = LogManager.getLogger(this.getClass());

	private List<AccountSelectionObserver> observers = new ArrayList<AccountSelectionObserver>();

	public AccountsPanel() {
		super(new BorderLayout());

		// Create a tree that allows one selection at a time.
		accountsTree = new JTree();
		accountsTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		accountsTree.setRootVisible(false);
		accountsTree.setShowsRootHandles(true);
		// Listen for when the selection changes.
		accountsTree.addTreeSelectionListener(this);

		// Create the scroll pane and add the tree to it.
		JScrollPane treeView = new JScrollPane(accountsTree);
		accountsTree.setModel(null);
		accountsTree.setEnabled(false);
		this.add(treeView);

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

	public void addObserver(AccountSelectionObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void changedActiveLedger(Ledger ledger) {
		this.setAccounts(ledger.getMainAccountsAsList());
		accountsTree.setEnabled(true);
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		AccountTreeNode accountTreeNode = (AccountTreeNode) accountsTree.getLastSelectedPathComponent();
		if (accountTreeNode == null) {
			logger.debug("empty node selected");
			return;
		}

		Account account = accountTreeNode.getAccount();
		for (AccountSelectionObserver observer : observers) {
			observer.accountSelected(account);
		}

	}

}
