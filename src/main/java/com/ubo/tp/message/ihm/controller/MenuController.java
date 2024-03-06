package main.java.com.ubo.tp.message.ihm.controller;

import java.util.HashSet;
import java.util.Set;

import main.java.com.ubo.tp.message.ihm.observer.MenuObserver;
import main.java.com.ubo.tp.message.ihm.observer.RCObserver;

public class MenuController {
	
	protected Set<MenuObserver> mObservers;
	
	public MenuController() {
		this.mObservers = new HashSet<MenuObserver>();
	}
	
	public void addMenuObserver(MenuObserver observer) {
		this.mObservers.add(observer);
	}
	
	public void switchProfile() {
		for (MenuObserver observer : mObservers) {
			observer.switchProfile();
		}
	}
	
	public void switchDeconnexion() {
		for (MenuObserver observer : mObservers) {
			observer.switchDeconnexion();
		}
	}
	
	public void switchSendMessage() {
		for (MenuObserver observer : mObservers) {
			observer.switchSendMessage();
		}
	}
}
