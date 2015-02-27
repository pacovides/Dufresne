package org.dufresne.desktop.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.dufresne.desktop.actions.SaveLedgerAction;
import org.dufresne.desktop.util.ImageStore;

import com.pacovides.money.service.LedgerService;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1680144728615370750L;

	private LedgerService ledgerService;

	public MainWindow(LedgerService ledgerService) {
		this.ledgerService = ledgerService;
		customizeAppearance();
		this.setJMenuBar(createMenuBar());
		this.pack();
	}

	private JMenuBar createMenuBar() {

		// Main menu bar
		JMenuBar menuBar = new JMenuBar();

		// File menu (F)
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		JMenuItem newLedger = new JMenuItem("New Ledger...");
		fileMenu.add(newLedger);

		JMenuItem openLedger = new JMenuItem("Open Ledger...");
		fileMenu.add(openLedger);

		SaveLedgerAction saveLedgerAction = new SaveLedgerAction(ledgerService);
		JMenuItem saveLedger = new JMenuItem(saveLedgerAction);
		fileMenu.add(saveLedger);

		menuBar.add(fileMenu);

		return menuBar;
	}

	private void customizeAppearance() {
		int width = 600;
		int height = 400;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int X = (screenSize.width / 2) - (width / 2); // Center horizontally.
		int Y = (screenSize.height / 2) - (height / 2); // Center vertically.

		this.setBounds(X, Y, width, height);
		this.setPreferredSize(new Dimension(width, height));

		// center the jframe on screen
		this.setLocationRelativeTo(null);

		Image appIcon = ImageStore.getImage("piggy.png");
		this.setIconImage(appIcon);

		this.setMinimumSize(new Dimension(300, 300));
		this.setTitle("Dufresne");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
