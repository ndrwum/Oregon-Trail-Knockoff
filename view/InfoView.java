package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import model.Adventure;

public class InfoView extends JPanel {

	private Adventure adventure;

	private DayDistanceInfoView ddiv;
	private MiscInfoView misciv;

	public InfoView(Adventure adventure) {
		this.adventure = adventure;

		ddiv = new DayDistanceInfoView(adventure);
		misciv = new MiscInfoView(adventure);

		setLayout(new BorderLayout());
		add(ddiv, BorderLayout.CENTER);
		add(misciv, BorderLayout.SOUTH);

	}
}