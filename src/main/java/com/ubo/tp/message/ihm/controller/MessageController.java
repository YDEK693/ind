package main.java.com.ubo.tp.message.ihm.controller;

import java.util.HashSet;
import java.util.Set;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.component.ReadManyMessageView;

public class MessageController implements IDatabaseObserver {

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
	
	protected ReadManyMessageView readManyMessage;
	

	public MessageController(IDatabase database, EntityManager entityManager,User user) {
		this.mDatabase = database;
		this.mEntityManager = entityManager;
		this.currentUser = user;
		this.readManyMessage = new ReadManyMessageView(this.getMessages());
		this.mDatabase.addObserver(this);
	}
	
	public MessageController(Message message) {
		this.readMessage = message;
	}
	
	
	public void sendMessage(String text) {
		Message m = new Message(currentUser, text);
		mDatabase.addMessage(m);
		this.mEntityManager.writeMessageFile(m);
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
	
	public Set<Message> getMessages() {
		return this.mDatabase.getMessages();
	}
	
	public void filtreMessage(String keyword) {
		Set<Message> result = new HashSet<Message>();
		String key = keyword;

		if(!keyword.contains("#")) {
			if (keyword.contains("@")) {
				key = keyword.substring(1);
			}
			for (User u : this.mDatabase.getUsers()) {
				if (u.getName().equals(key)) {
					result.addAll(this.mDatabase.getUserMessages(u));
				}
			}
			result.addAll(this.mDatabase.getMessagesWithUserTag(key));
		}
		
		if(!keyword.contains("@")) {
			if (keyword.contains("#")) {
				key = keyword.substring(1);
			}
			result.addAll(this.mDatabase.getMessagesWithTag(key));
		}
		
		if(!keyword.contains("@") && !keyword.contains("#")) {
			for(Message m : this.mDatabase.getMessages()) {
				if(m.getText().contains(keyword)) {
					result.add(m);
				}
			}
		}
		
		if(keyword.equals("")) {
			result = this.mDatabase.getMessages();
		}
		
		
		this.refreshManyMessage(result);
	}
	
	public ReadManyMessageView getReadManyMessageView() {
		return this.readManyMessage;
	}
	
	protected void refreshManyMessage(Set<Message> allMessage) {
		this.readManyMessage.refresh(allMessage);
	}

	@Override
	public void notifyMessageAdded(Message addedMessage) {
//		System.out.println("CONTROLLER MESSAGE");
		this.refreshManyMessage(this.mDatabase.getMessages());
	}

	@Override
	public void notifyMessageDeleted(Message deletedMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyMessageModified(Message modifiedMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}
}
