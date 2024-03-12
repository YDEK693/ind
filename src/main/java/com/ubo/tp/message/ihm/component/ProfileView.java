package main.java.com.ubo.tp.message.ihm.component;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.controller.ProfileController;

public class ProfileView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9015052069424056718L;
	protected ProfileController controller;
	
	protected Insets nest = new Insets(5, 5, 5, 5);
	
	public ProfileView(ProfileController controller) {
		this.controller = controller;
		
		this.initProfile();
	}

	private void initProfile() {
		this.setLayout(new GridBagLayout());
		this.setSize(getMaximumSize());
		int i = 0;
		
		JLabel nomLabel = new JLabel("Nom : " + controller.getUserName() + "#" + 
				controller.getUserTag());
		
		ImageIcon avatar = new ImageIcon(this.controller.getUserAvatar());
		JLabel imageLabel = new JLabel(avatar);
		
		JPanel follow = new JPanel();
		follow.setLayout(new GridBagLayout());
		
		for(String s : this.controller.getUserFollows()) {
			follow.add(this.createFollowPanel(this.controller.getUserByTag(s)), 
					new GridBagConstraints(0, i, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, this.nest, 1, 1));
			i++;
		}
		
		
        JScrollPane scrollPane = new JScrollPane(follow, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(400, 500));
        JLabel followLabel = new JLabel("Tous mes follows :");
          
        //Ajout au panel
  		this.add(imageLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
  				GridBagConstraints.NONE, this.nest, 1, 1));
  		this.add(nomLabel, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST,
  				GridBagConstraints.NONE, this.nest, 1, 1));
  		this.add(scrollPane, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
  				GridBagConstraints.CENTER, this.nest, 1, 1));
  		this.add(followLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
  				GridBagConstraints.NONE, this.nest, 1, 1));
	}
	
	protected JPanel createFollowPanel(User follow) {
		JPanel retour = new JPanel();
		
		JLabel nomLabel = new JLabel("Nom : " + follow.getName() + "#" + 
				follow.getUserTag());
		ImageIcon avatar = new ImageIcon(follow.getAvatarPath());
		JLabel imageLabel = new JLabel(avatar);
		
		retour.add(imageLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, this.nest, 1, 1));
		retour.add(nomLabel, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, this.nest, 1, 1));
		
		return retour;
	}
}
