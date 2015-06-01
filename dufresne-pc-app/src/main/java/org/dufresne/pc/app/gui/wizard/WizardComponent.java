package org.dufresne.pc.app.gui.wizard;

public interface WizardComponent {

	/**
	 * Adds one step to the end of the wizard component
	 * 
	 * @param step
	 */
	public void addStep(WizardStep step);

	/**
	 * Adds an observer to the component
	 * 
	 * @param observer
	 */
	public void addObserver(WizardObserver observer);

	/**
	 * Commands the wizard to go to the next step if applicable
	 */
	public void goNext();

	/**
	 * Commands the wizard to go to the previous step if applicable
	 */
	public void goBack();
}
