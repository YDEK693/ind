package main.java.com.ubo.tp.message.ihm.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Set;

import javax.swing.JPanel;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.controller.FollowerController;

public class FollowerManyView extends JPanel {
	private static final long serialVersionUID = 1L;
	protected Insets nest = new Insets(5, 5, 5, 5);

	public FollowerManyView(FollowerController followerController) {
		this.initGraph(followerController);
	}

	public FollowerManyView() {
	}
	
	protected void initGraph(FollowerController followerController) {
		this.setLayout(new GridBagLayout());
		int i =0;
		for (User u : followerController.getOtherUsers()) {
			FollowerView rm = new FollowerView(followerController, u);
			this.add(rm, new GridBagConstraints(0, i, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
					this.nest, 1, 1));
			i++;
		}
		//this.refresh(followers);
	}
	
	public void refresh(FollowerController followerController) {
		this.removeAll();

		int i = 0;
		for (User m : followerController.getOtherUsers()) {
			FollowerView f = new FollowerView(followerController ,m);
			this.add(f, new GridBagConstraints(0, i, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
					this.nest, 1, 1));
			i++;
		}
		this.revalidate();
		this.repaint();
	}
	
	public void refreshFilter(FollowerController followerController,Set<User> FilteredUsers) {
		this.removeAll();

		int i = 0;
		for (User m : FilteredUsers) {
			FollowerView f = new FollowerView(followerController ,m);
			this.add(f, new GridBagConstraints(0, i, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
					this.nest, 1, 1));
			i++;
		}
		this.show();
		this.revalidate();
		this.repaint();
	}
}