package org.dufresne.pc.app.gui.wizard;

import javax.swing.JPanel;

import org.apache.commons.lang3.StringUtils;

public class WizardStep {

	private JPanel displayPanel;

	private String name;

	/**
	 * When set to true, the wizard will automatically move to the next step,
	 * without controller intervention.
	 */
	private boolean autoFwd = true;

	public WizardStep(JPanel displayPanel, String name) {
		super();

		if (displayPanel == null || StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException("The step must have a valid name and panel");
		}
		this.displayPanel = displayPanel;
		this.name = name;
	}

	public JPanel getDisplayPanel() {
		return displayPanel;
	}

	public String getName() {
		return name;
	}

	public boolean isAutoFwd() {
		return autoFwd;
	}

	public void setAutoFwd(boolean autoFwd) {
		this.autoFwd = autoFwd;
	}

}
