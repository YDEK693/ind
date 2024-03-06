package main.java.com.ubo.tp.message.ihm.controller;

import java.util.HashSet;
import java.util.Set;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.observer.RCObserver;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;

public class MessageController {

	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;
	
	/**
	 * Utilisateur connecté
	 */
	protected User currentUser;
	
	/**
	 * message
	 */
	protected Message readMessage;
	

	public MessageController(IDatabase database, EntityManager entityManager,User user) {
		this.mDatabase = database;
		this.mEntityManager = entityManager;
		this.currentUser = user;
	}
	
	public MessageController(Message message) {
		this.readMessage = message;
	}
	
	
	public void sendMessage(String text) {
		Message m = new Message(currentUser, text);
		mDatabase.addMessage(m);
	}
	
	public Message getMessage() {
		return this.readMessage;
	}
	
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}
	
	public void setMessage(Message message) {
		this.readMessage = message;
	}

}
