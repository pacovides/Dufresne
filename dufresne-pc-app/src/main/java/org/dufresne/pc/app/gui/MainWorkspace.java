package org.dufresne.pc.app.gui;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class MainWorkspace extends JPanel implements ViewSelectionObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2237455968158496414L;

	public void addView(WorkspaceView viewIdentifier, JPanel viewComponent) {
		this.add(viewComponent, viewIdentifier.getViewname());
	}

	public MainWorkspace() {
		super(new CardLayout());
	}

	@Override
	public void viewSelected(WorkspaceView selectedView) {
		CardLayout cl = (CardLayout) (this.getLayout());
		cl.show(this, (String) selectedView.getViewname());
	}

}
