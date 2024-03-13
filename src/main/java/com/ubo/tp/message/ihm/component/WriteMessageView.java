package main.java.com.ubo.tp.message.ihm.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;



import main.java.com.ubo.tp.message.ihm.controller.MessageController;

public class WriteMessageView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Insets nest = new Insets(5, 5, 5, 5);
	protected MessageController controller; 

	public WriteMessageView(MessageController cmd) {
		this.controller = cmd;
		
		this.initGraph();
	}
	
	protected void initGraph() {
		this.setLayout(new GridBagLayout());
		this.setSize(getMaximumSize());
		
		JLabel messageLabel = new JLabel("Message :");

		JTextArea messageField = new JTextArea();
		messageField.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Définir une bordure par défaut
		messageField.setLineWrap(true); // Activer le retour à la ligne automatique
		messageField.setWrapStyleWord(true); // Assurer que les mots entiers sont sautés à la ligne
		messageField.setBorder(BorderFactory.createCompoundBorder(
		        messageField.getBorder(),
		        BorderFactory.createEmptyBorder(5, 10, 5, 10))); // Ajouter une marge à l'intérieur de la zone de texte
		messageField.setBackground(new Color(240, 240, 240)); // Définir une couleur de fond
		messageField.setPreferredSize(new Dimension(300, 100)); // Définir la taille de la zone de texte



		JButton sendButton = new JButton("Envoyer");
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// Vérifier si le champ faismoins de 200 caractères
				if (messageField.getText().length() > 200) {
					JOptionPane.showMessageDialog(WriteMessageView.this, "message trop long",
							"Erreur", JOptionPane.ERROR_MESSAGE);
				}else if (!messageField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(WriteMessageView.this, "message envoy�",
							"Valide", JOptionPane.INFORMATION_MESSAGE);
					WriteMessageView.this.controller.sendMessage(messageField.getText());
					messageField.setText("");
				} 
			}
		});
		

		this.add(messageLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
				GridBagConstraints.NONE, this.nest, 1, 1));
		this.add(messageField, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, this.nest, 1, 1));
		this.add(sendButton, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, this.nest, 1, 1));

	}
}
