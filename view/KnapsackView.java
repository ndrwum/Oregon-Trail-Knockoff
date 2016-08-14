package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Knapsack;
import supplies.Supplies;

public class KnapsackView extends JPanel implements Observer {
	private Knapsack knapsack;
	private JPanel list_panel;

	public KnapsackView(Knapsack k) {
		this.knapsack = k;
		k.getSupplies();
		knapsack.addObserver(this);

		setLayout(new BorderLayout());

		JLabel name = new JLabel("knapsack:");
		add(name, BorderLayout.NORTH);

		list_panel = new JPanel();
		list_panel.setLayout(new GridLayout(0, 1));
		buildListPanel();
		add(new JScrollPane(list_panel), BorderLayout.CENTER);

	}

	private void buildListPanel() {
		for (Supplies s : knapsack.getSupplies()) {
			JLabel supply_label = new JLabel(s.toString());
			list_panel.add(supply_label);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		list_panel.removeAll();
		buildListPanel();
		list_panel.revalidate();
	}

}