package main.java.com.ubo.tp.message.ihm.component;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public MenuView(MenuController controller) {
		this.controller = controller;
		
		this.initBar();
	}

	protected void initBar() {
        JMenu fichier = new JMenu("Fichier");
        JMenuItem QuitButton = new JMenuItem("Quitter");
        QuitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
        this.add(fichier);
        fichier.add(QuitButton);
     
        JMenu compte = new JMenu("Compte");
        JMenuItem profile = new JMenuItem("Mon Profile");
        profile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuView.this.controller.switchProfile();
			}
		});
        JMenuItem logoutButton = new JMenuItem("D�connexion");
        logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuView.this.controller.switchDeconnexion();
			}
		});
        
        JMenu message = new JMenu("Message");
        JMenuItem envoyerMessage = new JMenuItem("Envoyer Message");
        envoyerMessage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MenuView.this.controller.switchSendMessage();
			}
		});
        
        this.add(message);
        message.add(envoyerMessage);
        
        
        this.add(compte);
        compte.add(profile);
        compte.add(logoutButton);
        
        JMenu help = new JMenu("?");
        JMenuItem aPropos = new JMenuItem("� propos");
        aPropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             JOptionPane.showMessageDialog(MenuView.this, "N'h�siter pas � me soutenir sur Patreon @HornyBeeAimeL'Argent");  
            }
        }); 
        this.add(help);
        help.add(aPropos);
	}
}
