package de.tuberlin.snet.prog2.ue07.chinesecheckers.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.controller.Main;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.exceptions.InvalidConfigurationException;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.ArtificialPlayer;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.GameState;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.PieceColor;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.Player;


/**
 * This Dialog is shown after the user chooses to play a new 
 * local game.
 *
 */
public class DialogStart extends JDialog {
	final static private long serialVersionUID = 1187054005152688351L;
	final private GameState newGameState = new GameState();
	
	
	/**
	 * Text field for the name of the current player
	 */
	private JTextField tfName = new JTextField("Tim");
	
	/**
	 * ComboBox for the color of the current player
	 */
	private JComboBox<Color> cbbColor = new JComboBox<>(PieceColor.getColors());
	
	/**
	 * CheckBox if current player is played by the computer
	 */
	private JCheckBox checkAI = new JCheckBox("KI");
	

	/**
	 * Table that shows all players
	 */
    final String[] columnNames = {"Name", "Farbe", "KI"};
	private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
	private JTable table = new JTable(tableModel);
	/**
	 * Button to show the gamefield
	 */
	private JButton btnStart = new JButton("Spiel starten");
	
	/**
	 * Button to show the gamefield
	 */
	private JButton btnCancel = new JButton("Abbrechen");

	/**
	 * Textfield for the start player
	 */
	private JTextField tfStartName = new JTextField();

	/**
	 * Creates a new dialog
	 */
	public DialogStart() {
		super(Main.window, true);
        setTitle("Start a local game");

		this.setPreferredSize(new Dimension(350, 300));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		createDialog();

		
		this.pack();
		this.setLocationRelativeTo(Main.window);
	}

	/**
	 * Creates the frame of the <code>GameStartDialog</code> and the components in
	 * it
	 */
	public void createDialog() {
		JLabel lblName = new JLabel("Name:");
		cbbColor.setRenderer(new ColorCellRenderer());
		cbbColor.setSelectedIndex(1);
		cbbColor.setMaximumSize(new Dimension(50,26));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(new ColorCellRenderer());
        table.getColumnModel().getColumn(1).setMaxWidth(50);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setMaxWidth(25);
        JScrollPane scrollPane = new JScrollPane(table);
        
		JButton btnAdd = new JButton("Hinzufuegen");
		JPanel pnlNewPlayer = new JPanel();
		pnlNewPlayer.setLayout(new BoxLayout(pnlNewPlayer, BoxLayout.LINE_AXIS));
		pnlNewPlayer.add(lblName);
		pnlNewPlayer.add(tfName);
		pnlNewPlayer.add(cbbColor);
		pnlNewPlayer.add(btnAdd);
		pnlNewPlayer.add(checkAI);
		
	    JPanel pnlPlayers = new JPanel();
		pnlPlayers.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Mitspieler"));		
		pnlPlayers.setLayout(new BoxLayout(pnlPlayers, BoxLayout.PAGE_AXIS));
		pnlPlayers.add(scrollPane);
		pnlPlayers.add(pnlNewPlayer);

	    JPanel pnlConfig = new JPanel();
	    pnlConfig.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Konfiguration"));		
	    pnlConfig.setLayout(new GridLayout(0,2));
	    pnlConfig.add(new JLabel("Startspieler: "));
	    pnlConfig.add(tfStartName);
	    		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.LINE_AXIS));
		pnlButtons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		pnlButtons.add(Box.createHorizontalGlue());
		pnlButtons.add(btnCancel);
		pnlButtons.add(Box.createRigidArea(new Dimension(10, 0)));
		pnlButtons.add(btnStart);

	    
		btnAdd.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addNewPlayer();
				} catch (InvalidConfigurationException ice) {
					JOptionPane.showMessageDialog(null, ice.getMessage(), "Fehler bei der Eingabe:", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	    
		btnCancel.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				hideDialog();
			}
		});
		
		// Start-Button
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					startGame();
				} catch (InvalidConfigurationException ice) {
					JOptionPane.showMessageDialog(null, ice.getMessage(), "Fehler bei der Eingabe:", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	
				
		// Merge all together in a container
		JPanel pnlAll = new JPanel();
		pnlAll.setLayout(new BoxLayout(pnlAll, BoxLayout.PAGE_AXIS));
		pnlAll.add(pnlPlayers);
		pnlAll.add(pnlConfig);
		pnlAll.add(pnlButtons);
		
		// MainWindow
		Container pane = this.getContentPane();
		pane.setLayout(new BorderLayout());
		pane.add(pnlAll, BorderLayout.CENTER);
	}
	
	/**
	 * This method is called when the user hits the button
	 * to add a new player. It reads the data from the dialog
	 * and adds the player to the game state.
	 * @throws InvalidConfigurationException 
	 */
	public void addNewPlayer() throws InvalidConfigurationException {

		String name = tfName.getText();
		boolean isAI = checkAI.isSelected();
		String ai = "";
		if (isAI)
			ai = "x";
		PieceColor color = PieceColor.getPieceColor((Color)cbbColor.getSelectedItem());
		checkNewPlayer(newGameState, name, color);
		
		Player newPlayer;
		if (isAI)
			newPlayer = new ArtificialPlayer(name, color);
		else
			newPlayer = new Player(name, color);
		newGameState.addPlayer(newPlayer);
		
		String currentStartPlayerName = tfStartName.getText();
		if (currentStartPlayerName == null || currentStartPlayerName.length() == 0)
			tfStartName.setText(name);
		
		tableModel.addRow(new Object[]{name, color, ai});
		int nextIndex = (cbbColor.getSelectedIndex() +1) % PieceColor.values().length;
		cbbColor.setSelectedIndex(nextIndex);
	}
	
	

	/**
	 * Creates and starts the gamefield
	 * @throws InvalidConfigurationException 
	 */
	public void startGame() throws InvalidConfigurationException {
		
		String startPlayerName = tfStartName.getText();
		newGameState.setStartPlayerName(startPlayerName);

		checkConfig(newGameState);
				
		Main.startGame(newGameState);
		hideDialog();
	}
	
	
	public static void checkNewPlayer(GameState gameState, String name, PieceColor color) throws InvalidConfigurationException {
		if (name == null || name.length() == 0)
			throw new InvalidConfigurationException("Spielername ungueltig!");
		if (gameState.getPlayerNames().contains(name))
			throw new InvalidConfigurationException("Spielername bereits vorhanden!");
		if (gameState.getPlayerColors().contains(color))
			throw new InvalidConfigurationException("Spielerfarbe bereits vorhanden!");
	}
	
	public static void checkConfig(GameState gameState) throws InvalidConfigurationException {
		if (gameState.getPlayerNames().size() < 1)
			throw new InvalidConfigurationException("Mindestens ein Spieler muss definiert werden!");			
		if (gameState.getStartPlayer() == null || !gameState.getPlayerNames().contains(gameState.getStartPlayer().getName()))
			throw new InvalidConfigurationException("Startspieler muss definiert werden!");
	}
	
	
	/**
	 * Shows the dialog.
	 */
	public void showDialog() {
		newGameState.clear();
		int rowCount = tableModel.getRowCount();
		for (int i=0; i<rowCount; i++) {
			tableModel.removeRow(0);
		}
		tfStartName.setText("");
		
		
		
		setLocationRelativeTo(Main.window);
		setVisible(true);
	}
	
	/**
	 * Hides the dialog.
	 */
	public void hideDialog() {
		setVisible(false);
	}
}
