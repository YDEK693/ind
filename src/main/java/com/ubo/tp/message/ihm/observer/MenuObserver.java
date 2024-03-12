package main.java.com.ubo.tp.message.ihm.observer;

import java.util.List;
import java.util.Set;

import javax.swing.JMenu;

public interface MenuObserver {

	void switchProfile();

	void switchDeconnexion(List<JMenu> compostion);
	
	void switchLaunch(List<JMenu> compostion);
	
	void switchConnexion(List<JMenu> compostion);
	
	void switchSendMessage();

	void switchFollower();
}
