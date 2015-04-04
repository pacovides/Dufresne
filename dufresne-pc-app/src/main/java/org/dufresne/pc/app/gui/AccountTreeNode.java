package org.dufresne.pc.app.gui;

import javax.swing.tree.DefaultMutableTreeNode;

import com.pacovides.money.model.Account;

public class AccountTreeNode extends DefaultMutableTreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4294986531527813062L;

	private Account account;

	/**
	 * Builds the root node with the specified account and its children
	 * 
	 * @param account
	 */
	public AccountTreeNode(Account account) {
		super(account);
		for (Account subAccount : account.getDirectSubAccounts()) {
			AccountTreeNode subAccountNode = new AccountTreeNode(subAccount);
			this.add(subAccountNode);
		}

	}

	public Account getAccount() {
		return account;
	}



}
