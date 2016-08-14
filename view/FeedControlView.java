package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Adventure;
import model.InTransitException;
import model.Knapsack;
import model.Squad;
import supplies.Food;

public class FeedControlView extends JPanel implements ActionListener, Observer {

	private Adventure adventure;
	private Squad squad;
	private Knapsack knapsack;
	private Food[] edibles;
	private String[] players;
	private int num_of_player;

	private JButton feed;
	private JPanel edibles_panel;
	private JPanel player_panel;
	private JComboBox edible_list;
	private JComboBox player_list;

	public FeedControlView(Adventure adventure) {
		this.adventure = adventure;
		squad = this.adventure.getSquad();
		knapsack = squad.getKnapsack();

		num_of_player = squad.getNumPlayers();

		players = new String[num_of_player];
		for (int i = 0; i < num_of_player; i++) {
			players[i] = squad.getPlayer(i).getName();
		}

		this.setLayout(new FlowLayout());
		setPreferredSize(new Dimension(350, 200));

		edibles_panel = new JPanel();
		updateEdibleList();

		player_panel = new JPanel();
		player_panel.add(new JLabel("to "));
		player_list = new JComboBox(players);
		player_panel.add(player_list);

		// add(edibles_panel, BorderLayout.NORTH);
		// add(player_panel, BorderLayout.SOUTH);
		add(edibles_panel);
		add(player_panel);

		knapsack.addObserver(this);

	}

	/**
	 * After knapsack content is changed, update the edible supplies list
	 */
	private void updateEdibleList() {
		edibles = knapsack.getEdibleSupplies();
		edible_list = new JComboBox(edibles);
		edibles_panel.setLayout(new FlowLayout());
		edibles_panel.add(feed = new JButton("Feed "));
		edibles_panel.add(edible_list);
		feed.addActionListener(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		edibles_panel.removeAll();
		updateEdibleList();
		edibles_panel.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (adventure.isTravelling())
				throw new InTransitException();
			// press feed button
			edibles = knapsack.getEdibleSupplies();
			int edible_index = edible_list.getSelectedIndex();
			int player_index = player_list.getSelectedIndex();
			squad.feed(edibles[edible_index], squad.getPlayer(player_index));

		} catch (InTransitException e1) {
			System.out.println("No feeding while travelling.");
		}

	}
}