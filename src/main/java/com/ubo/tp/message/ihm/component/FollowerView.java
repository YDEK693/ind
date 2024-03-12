package main.java.com.ubo.tp.message.ihm.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.controller.FollowerController;
import main.java.com.ubo.tp.message.ihm.controller.ProfileController;

public class FollowerView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9015052069424056718L;
	protected User user;
	protected FollowerController followerController;
	protected Insets nest = new Insets(5, 5, 5, 5);
	
	public FollowerView(FollowerController followerController, User user) {
		this.user = user;
		this.followerController = followerController;
		this.initProfile();
	}

	private void initProfile() {
		this.setLayout(new GridBagLayout());
		this.setSize(getMaximumSize());
		ImageIcon icon = new ImageIcon(this.user.getAvatarPath());
		//JLabel AvatarLabel = new JLabel(icon);
		//AvatarLabel.setSize(50, 50);
		JLabel userName = new JLabel(this.user.getName());
		JLabel userId = new JLabel(this.user.getUserTag());
		JButton registerButton = null;
		if(!this.followerController.isFollowing(user)) {
			registerButton =  new JButton("Subscribe");
			registerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					FollowerView.this.followerController.addfollowUser(user.getUserTag());
					
				}
			});
		}else {
			registerButton =  new JButton("unSubscribe");
			registerButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					FollowerView.this.followerController.removefollowUser(user.getUserTag());
					
				}
			});
		}
		
		this.add(userName, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
				GridBagConstraints.NONE, this.nest, 1, 1));
		this.add(registerButton, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, this.nest, 1, 1));
		this.add(userId, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.NONE, this.nest, 1, 1));

		this.show();
		this.revalidate();
		this.repaint();
	}
}

