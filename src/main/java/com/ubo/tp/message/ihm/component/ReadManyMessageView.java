package main.java.com.ubo.tp.message.ihm.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.util.Set;

import javax.swing.JPanel;

import main.java.com.ubo.tp.message.datamodel.Message;

public class ReadManyMessageView extends JPanel {
	private static final long serialVersionUID = 1L;
	protected Insets nest = new Insets(5, 5, 5, 5);

	public ReadManyMessageView(Set<Message> allMessages) {
		this.initGraph(allMessages);
	}

	protected void initGraph(Set<Message> allMessages) {
		this.setLayout(new GridBagLayout());
		this.refresh(allMessages);
	}
	
	@SuppressWarnings("deprecation")
	public void refresh(Set<Message> allMessages) {
		this.removeAll();
		
		int i = 0;

		for (Message m : allMessages) {
			ReadMessageView rm = new ReadMessageView(m);
			this.add(rm, new GridBagConstraints(0, i, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
					this.nest, 1, 1));
			i++;
		}
		
		this.show();
		this.revalidate();
		this.repaint();
	}
}
