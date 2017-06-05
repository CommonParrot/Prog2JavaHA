package de.tuberlin.snet.prog2.ue07.chinesecheckers.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.PieceColor;
import de.tuberlin.snet.prog2.ue07.chinesecheckers.model.Player;


public class GameStatus extends JPanel {
	/**
	 * labels containing the status data
	 */
	final private JLabel lblGameState = new JLabel();
	final private JLabel lblActivePlayer = new JLabel();
	final private JLabel lblStartPlayer = new JLabel();
	/**
	 * table data
	 */
    final String[] columnNames = {"Name", "Farbe", "Fortschritt"};
	private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
	private JTable table = new JTable(tableModel);
	
	
	public GameStatus() {
		createGui();
		initDummyData();
	}


	/**
	 * adds necessary GUI elements to the GameStatus
	 */
	private void createGui() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));    	
		this.add(getStatusPanel());
		this.add(getPlayerContainers());
		
	}
	/**
	 * Creates and returns a panel containing all
	 * status information elements
	 * @return panel with all alements
	 */
	private JPanel getStatusPanel() {
		JPanel pnlConfig = new JPanel();
	    pnlConfig.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Konfiguration"));		
	    pnlConfig.setLayout(new GridLayout(0,2));
	    pnlConfig.add(new JLabel("Startspieler: "));
	    pnlConfig.add(lblStartPlayer);
	    pnlConfig.add(new JLabel("Spielphase: "));
	    pnlConfig.add(lblGameState);
	    pnlConfig.add(new JLabel("Aktiver Spieler: "));
	    pnlConfig.add(lblActivePlayer);

	    return pnlConfig;
	}
	
	/**
	 * Returns the panel containing the
	 * table with all players
	 * @return panel with all players
	 */
	private JPanel getPlayerContainers() {
		table.setEnabled(false);
		table.getColumnModel().getColumn(1).setCellRenderer(new ColorCellRenderer());
        table.getColumnModel().getColumn(1).setMaxWidth(50);
        table.getColumnModel().getColumn(2).setMaxWidth(75);
        JScrollPane scrollPane = new JScrollPane(table);
        
        JPanel container = new JPanel();
        container.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Mitspieler"));
        container.setLayout(new BorderLayout());
		container.add(scrollPane, BorderLayout.CENTER);

		return container;
	}


	/**
	 * Initialises the table with dummy data
	 */
	private void initDummyData() {
		tableModel.addRow(new Object[]{"Tom", PieceColor.BLUE, "70%"});
		tableModel.addRow(new Object[]{"Heinz", PieceColor.RED, "45%"});
	}
	/**
	 * Sets the game phase
	 */
	public void setGamePhase(String text) {
		lblGameState.setText(text);
	}
	/**
	 * Sets the active player
	 */
	public void setActivePlayer(String text) {
		lblActivePlayer.setText(text);
	}

	/**
	 * Sets the start player
	 */
	public void setStartPlayer(String text) {
		lblStartPlayer.setText(text);
	}
	
	/**
	 * Sets the progress for a certain player
	 * @param name name of the player whose progress is set
	 * @param progress progress to set
	 */
	public void setPlayerProgress(String name, String progress) {
		for (int i=0; i<tableModel.getRowCount(); i++) {
			String nameRow = (String)tableModel.getValueAt(i, 0);
			if (name.equals(nameRow)) {
				tableModel.setValueAt(progress, i, 2);
			}
		}
	}


	/**
	 * Sets the players
	 * @param players players to be set
	 */
	public void setPlayers(Collection<Player> players) {

		int rowCount = tableModel.getRowCount();
		for (int i=0; i<rowCount; i++) {
			tableModel.removeRow(0);
		}
		
		
		for (Player player : players) {
			PieceColor color = player.getColor();
			String progress = "0%";
			
			tableModel.addRow(new Object[]{player.getName(), color, progress});
		}
		

	}
}
