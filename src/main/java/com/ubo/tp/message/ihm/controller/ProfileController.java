package main.java.com.ubo.tp.message.ihm.controller;

import java.lang.reflect.Array;
import java.util.Set;

import javax.swing.JPanel;

import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;

public class ProfileController {
	protected User currentUser;
	protected IDatabase database;
	
	public ProfileController(User user, IDatabase data) {
		this.currentUser = user;
		this.database = data;
	}
	
	public String getUserTag() {
		return this.currentUser.getUserTag();
	}
	
	public String getUserName() {
		return this.currentUser.getName();
	}
	
	public Set<String> getUserFollows() {
		return this.currentUser.getFollows();
	}
	
	public String getUserAvatar() {
		return this.currentUser.getAvatarPath();
	}
	
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}
	
	public User getUserByTag(String tag) {
		User retour = null;
		
		for(User u : this.database.getUsers()) {
			if(u.getUserTag().equals(tag)) {
				retour = u;
			}
		}
		
		return retour;
	}
}
