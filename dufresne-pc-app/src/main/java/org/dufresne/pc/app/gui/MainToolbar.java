package org.dufresne.pc.app.gui;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class MainToolbar extends JPanel implements ViewSelectionObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2237455968158496414L;

	public MainToolbar() {
		super(new CardLayout());
	}

	public void addToolbar(WorkspaceView viewIdentifier, JPanel toolbar) {
		this.add(toolbar, viewIdentifier.getViewname());
	}

	@Override
	public void viewSelected(WorkspaceView selectedView) {
		CardLayout cl = (CardLayout) (this.getLayout());
		cl.show(this, (String) selectedView.getViewname());
	}



}
