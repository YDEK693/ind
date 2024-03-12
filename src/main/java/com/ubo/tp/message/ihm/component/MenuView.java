package main.java.com.ubo.tp.message.ihm.component;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import main.java.com.ubo.tp.message.ihm.controller.MenuController;

public class MenuView extends JMenuBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1757498991220843790L;
	
	protected MenuController controller;
	
	protected List<JMenu> compostion = new ArrayList<JMenu>();
	
	public MenuView(MenuController controller) {
		this.controller = controller;
		
		this.initBarLaunch();
	}
	
	public MenuView(MenuController controller, int temp) {
		this.controller = controller;
		
		this.initBarLogOff();
	}
	
	public MenuView(MenuController controller, boolean temp) {
		this.controller = controller;
		
		this.initBarLogIn();
	}
	
	protected void initBarLaunch() {       
        this.initDeconnexion();
        
        this.controller.switchLaunch(this.compostion);
	}
	
	protected void initBarLogOff() {       
        this.initDeconnexion();
        
        this.controller.switchDeconnexion(this.compostion);
	}
	
	protected void initBarLogIn() {
		 this.initConnexion();
	        
	     this.controller.switchConnexion(this.compostion);
	}

	protected JMenu initFichier() {
		JMenu fichier = new JMenu("Fichier");
        JMenuItem QuitButton = new JMenuItem("Quitter");
        QuitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
        fichier.add(QuitButton);
        
        return fichier;
	}
	
	protected JMenu initCompte() {
		JMenu compte = new JMenu("Compte");
        JMenuItem profile = new JMenuItem("Mon Profile");
        profile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuView.this.controller.switchProfile();
			}
		});
        JMenuItem logoutButton = new JMenuItem("Déconnexion");
        logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuView.this.initDeconnexion();
				MenuView.this.controller.switchDeconnexion(MenuView.this.compostion);
			}
		});
        JMenuItem follower = new JMenuItem("Abonnements");
        follower.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuView.this.controller.switchFollower();
			}
		});
        
        compte.add(profile);
        compte.add(follower);
        compte.add(logoutButton);
        
        return compte;
	}
	
	protected JMenu initMessage() {
		JMenu message = new JMenu("Message");
        JMenuItem envoyerMessage = new JMenuItem("Chat");
        envoyerMessage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuView.this.controller.switchSendMessage();
			}
		});    
        message.add(envoyerMessage);
        
        return message;
	}
	
	protected JMenu initHelp() {
		JMenu help = new JMenu("?");
        JMenuItem aPropos = new JMenuItem("A propos");
        aPropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             JOptionPane.showMessageDialog(MenuView.this, "N'hésiter pas à me soutenir sur Patreon @HornyBeeAimeL'Argent");  
            }
        }); 
        help.add(aPropos);
        
        return help;
	}
	
	protected void initDeconnexion() {
		this.compostion.clear();
		
		this.compostion.add(this.initFichier());
        this.compostion.add(this.initHelp());
	}
	
	private void initConnexion() {
		this.compostion.clear();
		
		this.compostion.add(this.initFichier());
		this.compostion.add(this.initMessage());
		this.compostion.add(this.initCompte());
        this.compostion.add(this.initHelp());
	}
}
