package main.java.com.ubo.tp.message.ihm.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.ihm.controller.MessageController;

public class FiltreView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -125031547258008544L;
	
	protected Insets nest = new Insets(5, 5, 5, 5);
	protected MessageController controller; 
	
	public FiltreView(MessageController controller) {
		this.controller = controller;
		this.initGraph();
	}

	private void initGraph() {
		this.setLayout(new GridBagLayout());
		
		this.afficherFiltre();
		//this.afficherMessage(this.controller.getMessages());
	}
	
	@SuppressWarnings("deprecation")
	private void afficherMessage(Set<Message> messages) {
		int i = 1;
		
		this.removeAll();
		
		this.afficherFiltre();
		
		for(Message m : messages) {
			ReadMessageView rm = new ReadMessageView(m);
			this.add(rm, new GridBagConstraints(0, i, 1, 1, 1, 1, GridBagConstraints.CENTER,
	  				GridBagConstraints.NONE, this.nest, 1, 1));
			i++;
		}
		
		this.show();
		this.revalidate();
		this.repaint();
	}
	
	@SuppressWarnings("deprecation")
	private void afficherFiltre() {
		JLabel filtreLabel = new JLabel("Filtre :");
		JTextField filtreField = new JTextField();
		filtreField.setColumns(20);
		JButton filtreButton = new JButton("Filtre");
		filtreButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			FiltreView.this.controller.filtreMessage(filtreField.getText());
			}
		});
		
		this.add(filtreLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.NONE, this.nest, 1, 1));
		this.add(filtreField, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, this.nest, 1, 1));
		this.add(filtreButton, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.NONE, this.nest, 1, 1));
		
		this.show();
		this.revalidate();
		this.repaint();
	}
}
