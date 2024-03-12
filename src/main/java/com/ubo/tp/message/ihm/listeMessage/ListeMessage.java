package main.java.com.ubo.tp.message.ihm.listeMessage;

import java.util.HashSet;
import java.util.Set;

import main.java.com.ubo.tp.message.common.Constants;
import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.observer.IMessage;
import main.java.com.ubo.tp.message.ihm.observer.IMessageObserver;

public class ListeMessage implements IDatabaseObserver, IListeMessage{
	protected Set<Message> lm = new HashSet<>();

	
	protected final Set<IDatabaseObserver> mObservers;
	
	//protected final Set<IMessageObserver> mObservers;

	public ListeMessage(Set<Message> lm) {
		this.lm = lm;
		mObservers = new HashSet<>();
	}

	@Override
	public void notifyMessageAdded(Message addedMessage) {
		this.notifyMessageSendUpdateReadList(new ListeMessage(this.lm));	
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
	
	//Iliste message
	@Override
	public void addObserver(IListeMessageObserver observer) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeObserver(IListeMessageObserver observer) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notifyMessageSendUpdateReadList(ListeMessage lm) {
		// TODO Auto-generated method stub
		
	}

	
	
}
