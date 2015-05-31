package org.dufresne.pc.app.gui.form;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class FormField extends JPanel {

	private static final long serialVersionUID = 5234866686742938038L;

	public abstract void setValue(String textValue);

	public abstract String getValue();

	protected JLabel label = new JLabel();

	/**
	 * Initializes the layout. Should be called by child constructors after
	 * component initialization.
	 * 
	 * @param fieldName
	 */
	protected void initLayout(String fieldName) {
		this.setLayout(new BorderLayout());
		label.setText(fieldName);
		// Used for assistive technologies
		label.setLabelFor(getActiveFieldContainer());
		this.add(label, BorderLayout.PAGE_START);
		this.add(getActiveFieldContainer(), BorderLayout.CENTER);

	}

	/**
	 * This is expected to return the JComponent that contains the active field.
	 * Note that this can be the field itself, like in the case of a textField.
	 * But it can also be a container panel like the scrollPane of a textArea or
	 * a JPanel of an inlineFileFIeld.
	 * 
	 * @return
	 */
	protected abstract JComponent getActiveFieldContainer();

}
