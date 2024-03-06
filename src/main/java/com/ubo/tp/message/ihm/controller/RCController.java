package main.java.com.ubo.tp.message.ihm.controller;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.observer.RCObserver;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;

public class RCController {
	
	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;
	
	//register
	protected Set<RCObserver> mRCObservers;
	//session
	protected Set<ISessionObserver> mSessionObservers;
	
	public RCController(IDatabase database, EntityManager entityManager) {
		this.mDatabase = database;
		this.mEntityManager = entityManager;
		this.mRCObservers = new HashSet<RCObserver>();
		this.mSessionObservers = new HashSet<ISessionObserver>();
		this.initTest();
	}
	
	/**
	 * Test si le user peut être créé
	 * @param name
	 * @param tag
	 * @return true le tag n'existe pas
	 */
	public Boolean verifyTextField(String name, String tag) {		
		return !verifyTagExist(tag);
	}
	
	/**
	 * Test si le tag est déjà présent dans la bdd
	 * @param tag
	 * @return true le tag n'existe pas
	 */
	public Boolean verifyTagExist(String tag) {
		Boolean tagfound = false;
		
		for(User u: this.mDatabase.getUsers()) {
			if(u.getUserTag().equals(tag)) {
				tagfound = true;
			}
		}
		
		return tagfound;
	}
	
	/**
	 * Ajoute un utilisateur à la base de donnée.
	 * @param avatarPathValue 
	 */
	public void addUserInDatabase(String name, String tag, String avatarPathValue) {
		// Création d'un utilisateur fictif
		User newUser = new User(UUID.randomUUID(), tag, "--", name, new HashSet<>(), avatarPathValue);
		// Ajout de l'utilisateur à la base
		this.mDatabase.addUser(newUser);
	}
	

	public Boolean connection(String tag) {
		if(this.verifyTagExist(tag)) {
			User user = getUserByTag(tag);
			
			for (ISessionObserver observer : mSessionObservers) {
				observer.notifyLogin(user);
				return true;
			}
		}
		
		return false;
	}
	
	public User getUserByTag(String tag) {
		User user = null;
		
		for(User u: this.mDatabase.getUsers()) {
			if(u.getUserTag().equals(tag)) {
				user = u;
			}
		}
		
		return user;
	}
	
	public void addRCO(RCObserver observer) {
		this.mRCObservers.add(observer);
	}
	
	public void addSession(ISessionObserver observer) {
		this.mSessionObservers.add(observer);
	}
	
	protected void initTest() {
		User u = new User(UUID.randomUUID(), "#1420", "--", "Julien", new HashSet<String>(), "");
		this.mDatabase.addUser(u);
		u = new User(UUID.randomUUID(), "#1543", "--", "Jake", new HashSet<String>(), "");
		this.mDatabase.addUser(u);
		u = new User(UUID.randomUUID(), "#1234", "--", "Yoann", new HashSet<String>(), "C:\\Users\\yoann\\Documents\\logo_50.png");
		this.mDatabase.addUser(u);
		u = new User(UUID.randomUUID(), "#8043", "--", "Justine", new HashSet<String>(), "");
		this.mDatabase.addUser(u);
		u = new User(UUID.randomUUID(), "#3210", "--", "Th�liau", new HashSet<String>(), "");
		this.mDatabase.addUser(u);
	}

	public void openRegister() {
		for (RCObserver observer : mRCObservers) {
			observer.openRegister();
		}
	}
}
