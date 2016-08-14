
package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import model.Adventure;

public class ControlView extends JPanel {

	public ControlView(Adventure adventure) {

		setLayout(new FlowLayout());

		setPreferredSize(new Dimension(350, 300));

		TravelControlView tcv = new TravelControlView(adventure);

		PurchaseControlView pcv = new PurchaseControlView(adventure);

		FeedControlView fcv = new FeedControlView(adventure);

		// add(tcv, BorderLayout.NORTH);

		// add(pcv, BorderLayout.SOUTH);

		// add(fcv, BorderLayout.CENTER);

		add(tcv);

		add(pcv);

		add(fcv);

	}

}
