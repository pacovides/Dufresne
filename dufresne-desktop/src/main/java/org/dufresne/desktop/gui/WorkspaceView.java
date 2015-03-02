package org.dufresne.desktop.gui;

/**
 * Supported views in the Dufresne GUI
 * 
 * @author Francisco
 *
 */
public enum WorkspaceView {
	TRANSACTION_VIEW("Transaction View"), ACCOUNT_VIEW("Account View");

	private String viewname;

	private WorkspaceView(String viewname) {
		this.viewname = viewname;
	}

	public String getViewname() {
		return viewname;
	}

}
