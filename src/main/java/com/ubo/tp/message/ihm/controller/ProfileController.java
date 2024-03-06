package main.java.com.ubo.tp.message.ihm.controller;

import java.util.Set;

import main.java.com.ubo.tp.message.datamodel.User;

public class ProfileController {
	protected User currentUser;
	
	public ProfileController(User user) {
		this.currentUser = user;
	}
	
	public String getUserTag() {
		return this.currentUser.getUserTag();
	}
	
	public String getUserName() {
		return this.currentUser.getName();
	}
	
	public String[] getUserFollows() {
		int i = 0;
		String[] retour = {};
		
		for(String s : this.currentUser.getFollows()) {
			retour[i] = s;
			i = i +1;
		}
		
		return retour;
	}
	
	public String getUserAvatar() {
		return this.currentUser.getAvatarPath();
	}
	
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}
}
