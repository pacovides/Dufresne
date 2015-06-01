package org.dufresne.pc.app.gui.wizard;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.apache.commons.lang3.StringUtils;

public class WizardWindow extends JFrame implements WizardObserver, WizardComponent {

	private static final long serialVersionUID = -8869139906778701335L;

	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;

	private WizardPanel wizardPanel;

	/**
	 * If set to true the window will automatically close upon cancel or finish
	 */
	private boolean autoClose = true;

	public WizardWindow(String title, int width, int height) {
		customizeAppearance(title, width, height);
		wizardPanel = new WizardPanel();
		wizardPanel.addObserver(this);
		this.setContentPane(wizardPanel);
		this.pack();
	}

	public WizardWindow(String title) {
		this(title, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	private void customizeAppearance(String title, int width, int height) {
		int prefferedWidth = width > 0 ? width : DEFAULT_WIDTH;
		int prefferedHeight = height > 0 ? height : DEFAULT_HEIGHT;
		Dimension preferredSize = new Dimension(prefferedWidth, prefferedHeight);
		this.setPreferredSize(preferredSize);
		this.setMinimumSize(preferredSize);
		if (StringUtils.isNotEmpty(title)) {
			this.setTitle(title);
		}
		this.setLocationRelativeTo(null);
	}

	@Override
	public void wizardNext() {
	}

	@Override
	public void wizardBack() {
	}

	@Override
	public void wizardCancel() {
		if (autoClose) {
			closeWindow();
		}
	}

	@Override
	public void wizardFinished() {
		if (autoClose) {
			closeWindow();
		}
	}

	public void closeWindow() {
		this.setVisible(false);
		this.dispose();
	}

	public void setAutoClose(boolean autoClose) {
		this.autoClose = autoClose;
	}

	@Override
	public void addStep(WizardStep step) {
		wizardPanel.addStep(step);
	}

	@Override
	public void addObserver(WizardObserver observer) {
		wizardPanel.addObserver(observer);
	}

	@Override
	public void goNext() {
		wizardPanel.goNext();
	}

	@Override
	public void goBack() {
		wizardPanel.goBack();
	}
}
