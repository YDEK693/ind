package main.java.com.ubo.tp.message.ihm.component;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.ihm.MessageAppMainView;
import main.java.com.ubo.tp.message.ihm.controller.RCController;

//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//SwingUtilities.updateComponentTreeUI(this);
public class CreateUserView extends JPanel {
	private JTextField nom;
	private JTextField tag;
	private JTextField avatarPath;
	private JButton submitButton;
	private RCController createUserController;

	public CreateUserView(RCController createUserController) {
		this.initGui();
		this.createUserController = createUserController;
	}

	protected void initGui() {
		this.setLayout(new GridBagLayout());

		JLabel firstNameLabel = new JLabel("Name:");
		JLabel tagLabel = new JLabel("Tag:");
		add(firstNameLabel);
		nom = new JTextField();
		nom.setColumns(20);
		tag = new JTextField();
		tag.setColumns(20);

		// Ajouter un bouton pour sélectionner une image
		JButton selectImageButton = new JButton("Avatar");
		avatarPath = new JTextField();
		avatarPath.setColumns(20);

		selectImageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif");
				fileChooser.setFileFilter(filter);

				int returnValue = fileChooser.showOpenDialog(CreateUserView.this);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String selectedFilePath = selectedFile.getAbsolutePath();
					// Met à jour le champ JTextField avec le chemin du fichier sélectionné
					avatarPath.setText(selectedFilePath);
				}

			}
		});
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			//
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nomValue = CreateUserView.this.nom.getText();
				String tagValue = CreateUserView.this.tag.getText();
				String avatarPath = CreateUserView.this.tag.getText();

				// Vérifier si le champ obligatoire est vide
				if (nomValue.isEmpty() || tagValue.isEmpty()) {
					JOptionPane.showMessageDialog(CreateUserView.this, "Veuillez remplir tous les champs obligatoires.",
							"Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					// Les champs obligatoires sont remplis, procéder au traitement des données
					CreateUserView.this.verifyTextField();
				}
			}

		});
		// submitButton.addActionListener(this);

		setVisible(true);
		this.add(firstNameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		add(nom, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		add(tagLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		add(tag, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));

		/*
		 * this.add(QuitButton, new GridBagConstraints(0, 1, 1, 1, 1, 1,
		 * GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5,
		 * 5), 0, 0));
		 */

		add(selectImageButton, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		add(avatarPath, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
		add(submitButton, new GridBagConstraints(0, 3, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(5, 5, 5, 5), 0, 0));
	}

	protected void verifyTextField() {
		String nameValue = nom.getText();
		String tagValue = tag.getText();
		String avatarPathValue = avatarPath.getText();
		if (createUserController.verifyTextField(nameValue, tagValue)) {
			createUserController.addUserInDatabase(nameValue, tagValue, avatarPathValue);
			JOptionPane.showMessageDialog(CreateUserView.this, "Votre compte a été crée.", "User créer",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(CreateUserView.this, "Tag déjà pris", "Erreur", JOptionPane.ERROR_MESSAGE);
		}

	}

}
