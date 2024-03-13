package main.java.com.ubo.tp.message.ihm.observer;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;

public interface IMessage {
	/**
	 * Ajoute un observateur à la session.
	 *
	 * @param observer
	 */
	void addObserver(IMessageObserver observer);

	/**
	 * Retire un observateur à la session.
	 *
	 * @param observer
	 */
	void removeObserver(IMessageObserver observer);
	
	
	void messageSend(Message message);
}
