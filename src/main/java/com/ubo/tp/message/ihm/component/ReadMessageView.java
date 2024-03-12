package main.java.com.ubo.tp.message.ihm.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.ihm.controller.MessageController;

public class ReadMessageView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Insets nest = new Insets(5, 5, 5, 5);
	protected Message message; 

	public ReadMessageView(Message m) {
		this.message = m;
		this.initGraph();
	}
	
	protected void initGraph() {
		this.setLayout(new GridBagLayout());
		this.setSize(getMaximumSize());
		
		ImageIcon icon = new ImageIcon(this.message.getSender().getAvatarPath());
		JLabel textLabel = new JLabel("Quitter");
		JLabel AvatarLabel = new JLabel(icon);
		AvatarLabel.setSize(50, 50);

		JTextArea messageField = new JTextArea();
		messageField.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Définir une bordure par défaut
		messageField.setLineWrap(true); // Activer le retour à la ligne automatique
		messageField.setWrapStyleWord(true); // Assurer que les mots entiers sont sautés à la ligne
		messageField.setBorder(BorderFactory.createCompoundBorder(
		        messageField.getBorder(),
		        BorderFactory.createEmptyBorder(5, 10, 5, 10))); // Ajouter une marge à l'intérieur de la zone de texte
		messageField.setBackground(new Color(240, 240, 240)); // Définir une couleur de fond
		messageField.setPreferredSize(new Dimension(300, 100)); // Définir la taille de la zone de texte
		messageField.setText(this.message.getText());
		messageField.setEnabled(false);

		this.add(AvatarLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, this.nest, 1, 1));
		this.add(messageField, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, this.nest, 1, 1));


	}
}
