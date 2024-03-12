package main.java.com.ubo.tp.message.ihm.component;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.controller.FollowerController;
import main.java.com.ubo.tp.message.ihm.controller.RCController;

public class FollowerManagementView extends JPanel {
	private static final long serialVersionUID = 1L;
	protected Insets nest = new Insets(5, 5, 5, 5);



	public FollowerManagementView(FollowerController followerController) {
		this.initGraph(followerController);
		
	}
	protected void initGraph(FollowerController followerController) {
		this.setLayout(new GridBagLayout());
		
		FiltreUserView filtre = new FiltreUserView(followerController);
		FollowerManyView followerMany = followerController.getFollowerManyView();
		followerMany.initGraph(followerController);
		JScrollPane scroll = new JScrollPane(followerMany, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(500, 500));
		
		this.add(filtre, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, this.nest, 1, 1));
		this.add(scroll, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, this.nest, 1, 1));
		this.revalidate();
		
		
		
	}
	

}
