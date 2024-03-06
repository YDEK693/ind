package main.java.com.ubo.tp.message.ihm.component;

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
		
		JLabel nomLabel = new JLabel("Nom : " + controller.getUserName() + "#" + 
				controller.getUserTag());
		
		ImageIcon avatar = new ImageIcon(this.controller.getUserAvatar());
		JLabel imageLabel = new JLabel(avatar);
		
		JList<String> jlistFollow = new JList<String>(this.controller.getUserFollows());
        JScrollPane scrollPane = new JScrollPane(jlistFollow);
        JLabel followLabel = new JLabel("Tous mes follows :");
          
        //Ajout au panel
  		this.add(imageLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
  				GridBagConstraints.NONE, this.nest, 1, 1));
  		this.add(nomLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
  				GridBagConstraints.NONE, this.nest, 1, 1));
  		this.add(scrollPane, new GridBagConstraints(1, 0, 2, 1, 1, 1, GridBagConstraints.CENTER,
  				GridBagConstraints.NONE, this.nest, 1, 1));
  		this.add(followLabel, new GridBagConstraints(1, 0, 2, 1, 1, 1, GridBagConstraints.NORTHWEST,
  				GridBagConstraints.NONE, this.nest, 1, 1));
	}
}
