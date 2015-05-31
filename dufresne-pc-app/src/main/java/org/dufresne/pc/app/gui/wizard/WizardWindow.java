package org.dufresne.pc.app.gui.wizard;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.apache.commons.lang3.StringUtils;

public class WizardWindow extends JFrame {

	private static final long serialVersionUID = -8869139906778701335L;

	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;

	private WizardPanel wizardPanel = new WizardPanel();

	public WizardWindow(String title, int width, int height) {
		customizeAppearance(title, width, height);
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

}
