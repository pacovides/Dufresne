package org.dufresne.pc.app.gui.form;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The TextAreaFormField allows input of long text. It allows multiple lines and
 * automatically renders scrollbars when needed.
 * 
 * @author Francisco
 *
 */
public class TextAreaFormField extends FormField {

	private static final long serialVersionUID = -8642549999089239316L;

	private JTextArea textAreaField = new JTextArea();

	private JScrollPane scrollPane;

	public TextAreaFormField(String fieldName) {
		super();
		scrollPane = new JScrollPane(textAreaField, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		initLayout(fieldName);
	}

	@Override
	public void setAlignmentX(float alignmentX) {
		label.setAlignmentX(alignmentX);
		textAreaField.setAlignmentX(alignmentX);
	}

	@Override
	public void setValue(String textValue) {
		textAreaField.setText(textValue);
	}

	@Override
	protected JComponent getActiveFieldContainer() {
		return scrollPane;
	}

	@Override
	public String getValue() {
		return textAreaField.getText();
	}

}
