package org.dufresne.pc.app.gui.wizard;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WizardPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -8150781320430206980L;

	private static final Logger logger = LogManager.getLogger(WizardPanel.class);

	private static final String BACK_CMD = "BACK";

	private JPanel cardPanel;
	private CardLayout cardLayout;

	private JButton backButton;
	private JButton nextButton;
	private JButton finishButton;
	private JButton cancelButton;

	// This list contains the panels for each step of the wizard.
	private List<JPanel> stepPanels;

	// This map contains the index of the panels so we can lookup by name
	private Map<String, Integer> stepIndexMap;

	// Marks the current step of the process
	private int currentStepIndex;

	public WizardPanel() {
		initMainComponents();
		this.setLayout(new BorderLayout());
		this.add(cardPanel, BorderLayout.CENTER);
		this.add(createButtonPanel(), BorderLayout.PAGE_END);
	}

	public JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		Box buttonBox = new Box(BoxLayout.X_AXIS);

		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(new JSeparator(), BorderLayout.NORTH);

		buttonBox.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
		buttonBox.add(backButton);
		buttonBox.add(Box.createHorizontalStrut(5));
		buttonBox.add(nextButton);
		buttonBox.add(Box.createHorizontalStrut(10));
		buttonBox.add(finishButton);
		buttonBox.add(Box.createHorizontalStrut(10));
		buttonBox.add(cancelButton);
		buttonPanel.add(buttonBox, BorderLayout.CENTER);
		return buttonPanel;
	}

	public void initMainComponents() {

		// We use a list to store the step the panels so we can be mindful about
		// insertion order. We keep an index so we can also refer by name.
		// We could achieve similar results with just the map, but we would be
		// bound to insertion order implementations.
		stepPanels = new ArrayList<JPanel>();
		stepIndexMap = new HashMap<String, Integer>();
		currentStepIndex = 0;

		// The cardPanel will be our main placeholder for the wizard steps
		cardPanel = new JPanel();
		cardPanel.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));

		// The card panel will have a card layout to interchange steps
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);

		backButton = new JButton("Back");
		nextButton = new JButton("Next");
		cancelButton = new JButton("Cancel");
		finishButton = new JButton("Finish");

		backButton.addActionListener(this);
		nextButton.addActionListener(this);
		cancelButton.addActionListener(this);
		finishButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton) {
			logger.debug("back");
		}

	}

	public void goNext() {

	}

	public void goBack() {

	}

	public int getCurrentStepIndex() {
		return currentStepIndex;
	}

}
