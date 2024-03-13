package main.java.com.ubo.tp.message.ihm.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JMenu;

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
	
	public void switchDeconnexion(List<JMenu> compostion) {
		for (MenuObserver observer : mObservers) {
			observer.switchDeconnexion(compostion);
		}
	}
	
	public void switchLaunch(List<JMenu> compostion) {
		for (MenuObserver observer : mObservers) {
			observer.switchLaunch(compostion);
		}
	}
	
	public void switchConnexion(List<JMenu> compostion) {
		for (MenuObserver observer : mObservers) {
			observer.switchConnexion(compostion);
		}
	}
	
	public void switchSendMessage() {
		for (MenuObserver observer : mObservers) {
			observer.switchSendMessage();
		}
	}
	
	public void switchFollower() {
		for (MenuObserver observer : mObservers) {
			observer.switchFollower();
		}
	}
}
