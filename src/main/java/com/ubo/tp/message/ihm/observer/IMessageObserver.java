package main.java.com.ubo.tp.message.ihm.observer;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;

public interface IMessageObserver {
	/**
	 * Notification de connection d'un utilisateur.
	 *
	 * @param connectedUser, utilisateur nouvellement connecté.
	 */
	void notifyMessageSend(Message message);
	
	void switchSendMessage();
}
