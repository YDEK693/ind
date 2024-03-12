package main.java.com.ubo.tp.message.ihm.listeMessage;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;

public interface IListeMessage {
	/**
	 * Ajoute un observateur à la session.
	 *
	 * @param observer
	 */
	void addObserver(IListeMessageObserver observer);

	/**
	 * Retire un observateur à la session.
	 *
	 * @param observer
	 */
	void removeObserver(IListeMessageObserver observer);
	
	/**
	 * Retire un observateur à la session.
	 *
	 * @param observer
	 */
	void notifyMessageSendUpdateReadList(ListeMessage lm);

}
