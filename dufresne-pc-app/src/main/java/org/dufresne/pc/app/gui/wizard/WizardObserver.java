package org.dufresne.pc.app.gui.wizard;

public interface WizardObserver {

	/**
	 * Triggered upon next button click
	 */
	void wizardNext();

	/**
	 * Triggered upon back button click
	 */
	void wizardBack();

	/**
	 * Triggered upon cancel button click
	 */
	void wizardCancel();

	/**
	 * Triggered upon finish on tht last step of the wizard
	 */
	void wizardFinished();

}
