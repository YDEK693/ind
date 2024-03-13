package main.java.com.ubo.tp.message.ihm.component;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import main.java.com.ubo.tp.message.ihm.controller.MessageController;
import main.java.com.ubo.tp.message.ihm.observer.IMessageObserver;




public class MessageView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2813464326029654222L;
	protected MessageController controller; 
	protected Insets nest = new Insets(5, 5, 5, 5);
	
	public MessageView(MessageController cmd) {
		this.controller = cmd;
		this.initGraph();
	}
	
	protected void initGraph() {
		this.setLayout(new GridBagLayout());
		
		WriteMessageView send = new WriteMessageView(controller);		
		ReadManyMessageView readManyMessage = this.controller.getReadManyMessageView();	
		FiltreView filtre = new FiltreView(this.controller);
		JScrollPane scroll = new JScrollPane(readManyMessage, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(500, 500));
		
		this.add(filtre, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, this.nest, 1, 1));
		this.add(scroll, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, this.nest, 1, 1));
		this.add(send, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, this.nest, 1, 1));
		this.revalidate();
	}	
}
