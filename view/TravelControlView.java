package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Adventure;
import model.City;

public class TravelControlView extends JPanel implements ActionListener {

	private Adventure adventure;
	private JComboBox city_list;
	private JLabel travel_to;

	public TravelControlView(Adventure adv) {
		this.adventure = adv;

		travel_to = new JLabel("Travel to: ");

		City[] cities = adv.getCities();
		this.city_list = new JComboBox(cities);

		JPanel travel = new JPanel();
		travel.setLayout(new FlowLayout());
		travel.setPreferredSize(new Dimension(200, 100));
		// add label to panel
		travel.add(travel_to);
		// add dropdown list to panel
		travel.add(city_list);
		// add panel to this viewer;
		this.add(travel);

		// register components with this class
		// => when choose items, action is performed
		city_list.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// travel to next city as selected
		this.adventure.travel((City) city_list.getSelectedItem());

	}

}