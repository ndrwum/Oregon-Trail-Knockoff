package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Adventure;
import model.InTransitException;
import model.InsufficientFundsException;
import model.ItemNotForSaleException;
import model.Squad;
import model.Store;

public class PurchaseControlView extends JPanel implements ActionListener {

	private Adventure adventure;
	private Store store;
	private JTextField qty;
	private JComboBox sale_list;
	private JButton order;

	public PurchaseControlView(Adventure adventure) {

		this.adventure = adventure;
		try {
			store = adventure.getCurrentCity().getStore();
		} catch (InTransitException e) {
			System.out.println("Travelling");
		}

		this.setLayout(new FlowLayout());
		this.setPreferredSize(new Dimension(200, 100));

		add(new JLabel("Buy "));
		qty = new JTextField(10);
		add(qty);
		add(new JLabel("of "));

		String[] items = store.getItemNames();
		String[] item_price = new String[items.length];
		double price = 0.0;
		int i = 0;
		for (String item : items) {
			try {
				price = store.getPrice(item);
				item_price[i] = item + " ($" + price + ")";
				i++;
			} catch (ItemNotForSaleException e) {
				System.out.println("Not for sale");
			}
		}
		this.sale_list = new JComboBox(item_price);
		add(sale_list);

		order = new JButton("Order");
		order.addActionListener(this); // register to click event
		JPanel button_panel = new JPanel();
		button_panel.add(order);
		add(button_panel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			store = adventure.getCurrentCity().getStore();

			Squad squad = adventure.getSquad();
			int item_number = sale_list.getSelectedIndex();
			int count = Integer.parseInt(qty.getText());

			squad.purchaseSupply(store.getItemNames()[item_number], count, store);

		} catch (InTransitException e1) {
			System.out.println("No store while travelling.");
		} catch (ItemNotForSaleException e1) {
			System.out.println("Item not for sale.");
		} catch (InsufficientFundsException e1) {
			System.out.println("Insufficient balance.");
		}
	}
}