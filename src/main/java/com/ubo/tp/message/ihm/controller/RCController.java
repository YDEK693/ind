package main.java.com.ubo.tp.message.ihm.controller;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.Message;
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
		//this.initTest();
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
		this.mEntityManager.writeUserFile(newUser);
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
		User u = new User(UUID.randomUUID(), "1420", "--", "Julien", new HashSet<String>(), 
				"H:/workspace/MessageApp/src/main/resources/images/abeille.png");
		this.mDatabase.addUser(u);
		this.mEntityManager.writeUserFile(u);
		Message m = new Message(u, "Encore plus de place dans ma bibliothéque");
		this.mDatabase.addMessage(m);
		this.mEntityManager.writeMessageFile(m);
		m = new Message(u, "Bon j'ai craqué, j'ai pris une case de la rarity collection 2 #YGO");
		this.mDatabase.addMessage(m);
		this.mEntityManager.writeMessageFile(m);
		
		
		u = new User(UUID.randomUUID(), "1543", "--", "Jake", new HashSet<String>(), 
				"H:/workspace/MessageApp/src/main/resources/images/j.PNG");
		this.mDatabase.addUser(u);
		this.mEntityManager.writeUserFile(u);
		m = new Message(u, "Oups j'ai encore acheté un #gundam");
		this.mDatabase.addMessage(m);
		this.mEntityManager.writeMessageFile(m);
		
		u = new User(UUID.randomUUID(), "1234", "--", "Yoann", new HashSet<String>(), 
				"H:/workspace/MessageApp/src/main/resources/images/y.png");
		this.mDatabase.addUser(u);
		this.mEntityManager.writeUserFile(u);
		m = new Message(u, "@Julien , tu viens au tournoi samedi ? #YGO");
		this.mDatabase.addMessage(m);
		this.mEntityManager.writeMessageFile(m);
		
		u = new User(UUID.randomUUID(), "8043", "--", "Justine", new HashSet<String>(), 
				"H:/workspace/MessageApp/src/main/resources/images/ju.PNG");
		this.mDatabase.addUser(u);
		this.mEntityManager.writeUserFile(u);
		m = new Message(u, "@Julien , on fait des lasagnes ?");
		this.mDatabase.addMessage(m);
		this.mEntityManager.writeMessageFile(m);
		
		u = new User(UUID.randomUUID(), "3210", "--", "Théliau", new HashSet<String>(), 
				"H:/workspace/MessageApp/src/main/resources/images/t.PNG");
		this.mDatabase.addUser(u);
		this.mEntityManager.writeUserFile(u);
		m = new Message(u, "Encore un raid ce soir...");
		this.mDatabase.addMessage(m);
		this.mEntityManager.writeMessageFile(m);
	}

	public void openRegister() {
		for (RCObserver observer : mRCObservers) {
			observer.openRegister();
		}
	}
	
	public void goBack() {
		for (RCObserver observer : mRCObservers) {
			observer.goBack();
		}
	}
}
