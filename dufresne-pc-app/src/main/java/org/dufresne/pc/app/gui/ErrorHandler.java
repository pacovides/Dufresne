package org.dufresne.pc.app.gui;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dufresne.pc.app.actions.NewLedgerAction;

public class ErrorHandler {

	private static final Logger logger = LogManager.getLogger(NewLedgerAction.class);

	public static void displayUserError(String errorMsg) {
		JOptionPane.showMessageDialog(null, errorMsg, "Wait!", JOptionPane.ERROR_MESSAGE);
		// Although it is displayed as an error to the user, we log it as an
		// info, as it is on user side.
		logger.info(errorMsg);
	}

	/**
	 * Displays the user a warning, but gives the option to continue. warning is
	 * expected to come in the form of a yes/no question.
	 * 
	 * @param warnQuestion
	 * @return
	 */
	public static boolean displayUserWarn(String warnQuestion) {

		int answer = JOptionPane.showConfirmDialog(null, warnQuestion, "Sure?", JOptionPane.YES_NO_OPTION);
		while (!(answer == JOptionPane.YES_OPTION || answer == JOptionPane.NO_OPTION)) {
			answer = JOptionPane.showConfirmDialog(null, warnQuestion, "I asked a question: YES or NO?",
					JOptionPane.YES_NO_OPTION);
		}

		boolean binaryAnswer = (answer == JOptionPane.YES_OPTION);

		logger.info(warnQuestion + ": " + binaryAnswer);
		return binaryAnswer;
	}

}
