package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import person.Person;

public class PersonView extends JPanel implements Observer {
	private Person person;
	private JLabel label;

	public PersonView(Person p) {
		person = p;
		label = new JLabel(p.toString());

		add(label);
		person.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				label.setText(person.toString());
			}
		});
	}

}