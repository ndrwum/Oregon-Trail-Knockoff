package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import model.Adventure;
import model.Knapsack;
import model.Squad;
import person.Person;

/**
 * Viewer Structure AdventureView | List View: | SquadStatusListView | multiple
 * PersonView | KnapsackView | Info View | DayDistanceInfoView | MiscInfoView |
 * Control View | TravelControlView | PurchaseControlView | FeedControlView
 * 
 * 
 *
 */
public class AdventureView extends JPanel {

	private Adventure adventure;

	public AdventureView(Adventure adventure) {
		this.adventure = adventure;

		setLayout(new BorderLayout());

		Squad s = adventure.getSquad();

		int num_of_players = s.getNumPlayers();
		Person[] players = new Person[num_of_players];
		for (int i = 0; i < num_of_players; i++) {
			players[i] = s.getPlayer(i);
			// System.out.println(players[i].toString()); // for debugging
		}

		Knapsack knapsack = s.getKnapsack();

		// list view
		SquadStatusListView squad_list = new SquadStatusListView(players);
		KnapsackView knapsack_list = new KnapsackView(knapsack);
		ListView lv = new ListView(squad_list, knapsack_list);

		// control view
		ControlView cv = new ControlView(adventure);

		// info view
		InfoView iv = new InfoView(adventure);

		add(cv, BorderLayout.EAST);
		add(lv, BorderLayout.WEST);
		add(iv, BorderLayout.CENTER);
	}
}
