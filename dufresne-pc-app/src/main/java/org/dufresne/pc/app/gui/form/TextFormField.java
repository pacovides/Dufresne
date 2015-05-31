package org.dufresne.pc.app.gui.form;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTextField;

public class TextFormField extends FormField {

	private static final long serialVersionUID = -8642549999089239316L;

	private JTextField textField = new JTextField();

	public TextFormField(String fieldName) {
		super();
		initLayout(fieldName);
		// Text fields are expected to be one liners. If more text is needed use
		// text area
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getPreferredSize().height));
	}

	@Override
	public void setAlignmentX(float alignmentX) {
		label.setAlignmentX(alignmentX);
		textField.setAlignmentX(alignmentX);
	}

	@Override
	public void setValue(String textValue) {
		textField.setText(textValue);
	}

	@Override
	protected JComponent getActiveFieldContainer() {
		return textField;
	}

	@Override
	public String getValue() {
		return textField.getText();
	}

}
