package main.java.com.ubo.tp.message.ihm.controller;

import java.util.HashSet;
import java.util.Set;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.component.FollowerManyView;
import main.java.com.ubo.tp.message.ihm.component.ReadManyMessageView;

public class FollowerController {
	
	protected User user;
	protected IDatabase mDatabase;
	protected FollowerManyView followerMany;
	protected EntityManager mEntityManager;
	
	public FollowerController(IDatabase database, User user, EntityManager mEntityManager) {
		this.mDatabase = database;
		this.followerMany = new FollowerManyView();
		this.mEntityManager = mEntityManager;
	}

	public int getNumberFollowers(User u) {
		return this.mDatabase.getFollowersCount(u);
	}
	
	public void addfollowUser(String followed) {
		user.addFollowing(followed);
		this.mDatabase.modifiyUser(user);
		this.mEntityManager.writeUserFile(user);
		this.followerMany.refresh(this);
	}
	public boolean isFollowing(User follower) {
			if(user.getFollows().contains(follower.getUserTag()) ) {
				return true;
			}
		
		return false;
	}
	/*public String[] getUserFollows() {
		int i = 0;
		String[] retour = {};
		
		for(String s : this.currentUser.getFollows()) {
			retour[i] = s;
			i = i +1;
		}
		
		return retour;
	}*/
	
	public Set<User>getOtherUsers(){
		Set<User> ret = this.mDatabase.getUsers();
		ret.remove(user);
		return ret;
	}


	public void setCurrentUser(User currentUser) {
		this.user = currentUser;
		
	}
	public void setFollowerVue(User currentUser) {
		this.user = currentUser;
		
	}

	public void removefollowUser(String userTag) {
		user.removeFollowing(userTag);
		this.mDatabase.modifiyUser(user);
		this.mEntityManager.writeUserFile(user);
		this.followerMany.refresh(this);
		
	}


	public FollowerManyView getFollowerManyView() {
		return this.followerMany;
	}

	public void filtreUser(String text) {
		Set<User> result = new HashSet<User>();
		for (User u : this.mDatabase.getUsers()) {
			if (u.getUserTag().equals(text)) {
				result.add(u);
			}
		}
		this.refreshManyFollower(result);
		
	}

	public void refreshManyFollower(Set<User> FilteredUsers) {
		this.followerMany.refreshFilter(this, FilteredUsers);
	}

	
	
}
