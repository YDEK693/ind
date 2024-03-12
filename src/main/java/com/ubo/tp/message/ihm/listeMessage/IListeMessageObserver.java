package main.java.com.ubo.tp.message.ihm.listeMessage;


import java.util.Set;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;

public interface IListeMessageObserver {
	/**
	 * Notification de connection d'un utilisateur.
	 *
	 * @param connectedUser, utilisateur nouvellement connecté.
	 */
	void notifyMessageSend(ListeMessage message);
	

}
