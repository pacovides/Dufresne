package org.dufresne.desktop.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

public class ViewSelector extends JComboBox<WorkspaceView> implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9081599841625146276L;

	private List<ViewSelectionObserver> observers = new ArrayList<ViewSelectionObserver>();

	public ViewSelector() {
		super();
		this.addItem(WorkspaceView.ACCOUNT_VIEW);
		this.addItem(WorkspaceView.TRANSACTION_VIEW);
		this.addItemListener(this);
		this.setEditable(false);

	}

	public void addObserver(ViewSelectionObserver observer) {
		observers.add(observer);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		for (ViewSelectionObserver observer : observers) {
			observer.viewSelected((WorkspaceView) e.getItem());
		}
	}


}
