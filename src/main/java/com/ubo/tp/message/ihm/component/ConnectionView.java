package main.java.com.ubo.tp.message.ihm.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.com.ubo.tp.message.ihm.controller.RCController;


public class ConnectionView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3508653672751789899L;
	
	protected Insets nest = new Insets(5, 5, 5, 5);
	
	protected RCController controller; 

	public ConnectionView(RCController cmd) {
		this.controller = cmd;
		
		this.initGraph();
	}
	
	protected void initGraph() {
		this.setLayout(new GridBagLayout());
		this.setSize(getMaximumSize());
		
		JLabel tagLabel = new JLabel("Entrer votre tag :");
		JTextField connectionField = new JTextField();
		connectionField.setColumns(20);
		JButton connectionButton = new JButton("Connexion");
		connectionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConnectionView.this.controller.connection(connectionField.getText());
			}
		});
		JButton registerButton =  new JButton("Cr�er un compte");
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConnectionView.this.controller.openRegister();
				
			}
		});
		JPanel buttons = new JPanel();
		
		
		//Ajout au panel
		buttons.add(registerButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
				GridBagConstraints.NONE, this.nest, 1, 1));
		buttons.add(connectionButton, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.EAST,
				GridBagConstraints.NONE, this.nest, 1, 1));
		this.add(tagLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, this.nest, 1, 1));
		this.add(connectionField, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, this.nest, 1, 1));
		this.add(buttons, new GridBagConstraints(0, 2, 2, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, this.nest, 1, 1));
	}
}
