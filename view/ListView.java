package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class ListView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SquadStatusListView squad_list;
	private KnapsackView knapsack_list;

	public ListView(SquadStatusListView squad_list, KnapsackView knapsack_list) {
		this.squad_list = squad_list;
		this.knapsack_list = knapsack_list;

		setLayout(new GridLayout(2, 1));
		add(squad_list);
		add(knapsack_list);
	}
}