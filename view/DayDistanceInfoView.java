package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Adventure;
import model.City;
import model.InTransitException;
import model.TravelObserver;

public class DayDistanceInfoView extends JPanel implements TravelObserver {

	private Adventure adventure;

	private JTextField tf;
	private JTextArea ta;

	public DayDistanceInfoView(Adventure adventure) {
		this.adventure = adventure;
		adventure.addTravelObserver(this);

		setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(350, 200));

		tf = new JTextField();
		try {
			tf.setText("Arrive at " + adventure.getCurrentCity().getName());
		} catch (InTransitException e) {
			System.out.println("Travelling");
		}
		// Sets the specified boolean to indicate whether or not
		// this textfield should be editable.
		tf.setEditable(false);

		ta = new JTextArea();
		ta.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(ta);

		// Add Components to this panel.
		add(new JLabel("You are at: "), BorderLayout.NORTH);
		add(tf, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}

	@Override
	public void travelUpdate(Adventure adventure, int distance_to_destination, City destination) {
		try {
			tf.setText("");
			;
			if (adventure.isTravelling()) {
				tf.setText("Travelling");
			} else {
				tf.setText(adventure.getCurrentCity().getName() + " - Day " + adventure.getDay());
			}

			String update = null;
			if (distance_to_destination == 0) {
				update = "Arrived at " + destination.getName() + "\n";
			} else {
				update = "On day " + adventure.getDay() + " you are " + distance_to_destination + " miles from "
						+ destination.getName() + "\n";
			}
			ta.append(update);
		} catch (InTransitException e) {
			System.out.println("Travelling.");
		}
	}
}
