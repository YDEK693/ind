package main.java.com.ubo.tp.message.ihm;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.core.database.IDatabaseObserver;
import main.java.com.ubo.tp.message.core.directory.IWatchableDirectory;
import main.java.com.ubo.tp.message.core.directory.IWatchableDirectoryObserver;
import main.java.com.ubo.tp.message.core.directory.WatchableDirectory;
import main.java.com.ubo.tp.message.datamodel.Message;
import main.java.com.ubo.tp.message.datamodel.User;
import main.java.com.ubo.tp.message.ihm.component.ConnectionView;
import main.java.com.ubo.tp.message.ihm.component.CreateUserView;
import main.java.com.ubo.tp.message.ihm.component.MenuView;
import main.java.com.ubo.tp.message.ihm.component.ProfileView;
import main.java.com.ubo.tp.message.ihm.component.ReadMessageView;
import main.java.com.ubo.tp.message.ihm.component.WriteMessageView;
import main.java.com.ubo.tp.message.ihm.controller.RCController;
import main.java.com.ubo.tp.message.ihm.controller.MenuController;
import main.java.com.ubo.tp.message.ihm.controller.MessageController;
import main.java.com.ubo.tp.message.ihm.controller.ProfileController;
import main.java.com.ubo.tp.message.ihm.observer.IMessageObserver;
import main.java.com.ubo.tp.message.ihm.observer.MenuObserver;
import main.java.com.ubo.tp.message.ihm.observer.RCObserver;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;
import main.java.com.ubo.tp.message.ihm.session.Session;
import mock.MessageAppMock;

/**
 * Classe principale l'application.
 *
 * @author S.Lucas
 */
public class MessageApp implements IDatabaseObserver, IWatchableDirectory, RCObserver, ISessionObserver, MenuObserver, IMessageObserver {
	Boolean debug = true;
	protected User userTest = new User(UUID.randomUUID(), "#1420", "--", "Julien", new HashSet<String>(), "");
	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected MessageAppMainView mMainView;

	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;

	protected RCController mRCController;

	protected MessageController mMessageController;
	// session connecte en cours
	protected User currentUser;

	protected ProfileController mProfileController;

	protected MenuController mMenuController;

	/**
	 * Constructeur.
	 *
	 * @param entityManager
	 * @param database
	 */
	public MessageApp(IDatabase database, EntityManager entityManager) {
		this.mDatabase = database;
		this.mEntityManager = entityManager;
		this.mRCController = new RCController(this.mDatabase, this.mEntityManager);

		this.mMessageController = new MessageController(this.mDatabase, this.mEntityManager, null);
		this.mProfileController = new ProfileController(null);
		this.mMenuController = new MenuController();
	}

	/**
	 * Initialisation de l'application.
	 */
	public void init() {
		// Init du look and feel de l'application
		this.initLookAndFeel();

		// Initialisation du répertoire d'échange
		this.initDirectory();

		// Initialisation de l'IHM
		this.initGui();
		// Initialisation des observers
		this.initObserver();
	}

	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel() {
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		this.mMainView = new MessageAppMainView(mDatabase, mEntityManager);

		if (debug) {
			String chemin = "C://Users/yoann/OneDrive/Bureau/M2";
			// String chemin = "H:/Telechargements/IHM/Rep";
			this.initDirectory(chemin);

		} else {
			File dir = this.mMainView.selectDirectory();

			while (!this.isValideExchangeDirectory(dir)) {
				dir = this.mMainView.selectDirectory();
			}

			this.initDirectory(dir.getAbsolutePath());
		}

		this.mMainView.initGui();
		ConnectionView log = new ConnectionView(this.mRCController);
		// test envoie message
		// WriteMessageView log = new WriteMessageView(this.mMessageController);
		MenuView menu = new MenuView(this.mMenuController);
		this.mMainView.updateMenu(menu);
		this.mMainView.selectView(log);
	}

	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	protected void initDirectory() {
	}

	/**
	 * Indique si le fichier donné est valide pour servir de répertoire d'échange
	 *
	 * @param directory , Répertoire à tester.
	 */
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}

	/**
	 * Initialisation du répertoire d'échange.
	 *
	 * @param directoryPath
	 */
	protected void initDirectory(String directoryPath) {
		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}

	public void show() {
	}

	protected void initObserver() {
		this.mDatabase.addObserver(this);
		this.mRCController.addRCO(this);
		this.mRCController.addSession(this);
		this.mMenuController.addMenuObserver(this);
	}

	@Override
	public void notifyMessageAdded(Message addedMessage) {
		System.out.println(	addedMessage.getSender().getName()+" : " + addedMessage);

	}

	@Override
	public void notifyMessageDeleted(Message deletedMessage) {
		System.out.println("notifyMessageDeleted");

	}

	@Override
	public void notifyMessageModified(Message modifiedMessage) {
		System.out.println("notifyMessageDeleted");

	}

	@Override
	public void notifyUserAdded(User addedUser) {
		System.out.println("AddUser" + addedUser);

	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		System.out.println("userDeleted" + deletedUser);

	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		System.out.println("notifyUserModified" + modifiedUser);

	}

	@Override
	public void initWatching() {
		System.out.println("initWatching");

	}

	@Override
	public void stopWatching() {
		System.out.println("stopWatching");

	}

	@Override
	public void changeDirectory(String directoryPath) {
		System.out.println("changeDirectory");

	}

	@Override
	public void addObserver(IWatchableDirectoryObserver observer) {
		System.out.println("addObserver");

	}

	@Override
	public void removeObserver(IWatchableDirectoryObserver observer) {
		System.out.println("removeObserver");

	}

	@Override
	public void openRegister() {
		System.out.println("switchView");
		CreateUserView vue = new CreateUserView(this.mRCController);
		this.mMainView.selectView(vue);
	}

	@Override
	public void notifyLogin(User connectedUser) {
		System.out.println("Connecte");
		this.currentUser = connectedUser;

		JPanel vue = new JPanel();

		ImageIcon icon = new ImageIcon("H:/workspace/MessageApp/src/main/resources/images/rickroll-roll.gif");

		// Cr�ation d'un JLabel pour afficher le GIF
		JLabel label = new JLabel(icon);

		// Ajout du JLabel � la fen�tre
		vue.add(label);

		// this.mProfileController.setCurrentUser(this.currentUser);

		// ProfileView profile = new ProfileView(this.mProfileController);

		this.mMainView.selectView(vue);
	}

	@Override
	public void notifyLogout() {
		System.out.println("deconnecte");

	}

	@Override
	public void switchProfile() {
		System.out.println("switchProfile");
		this.mProfileController.setCurrentUser(this.currentUser);
		ProfileView profile = new ProfileView(this.mProfileController);
		this.mMainView.selectView(profile);

	}

	@Override
	public void switchDeconnexion() {
		System.out.println("Deconnecte");
		this.currentUser = null;
		ConnectionView log = new ConnectionView(this.mRCController);
		this.mMainView.selectView(log);

	}

	@Override
	public void notifyMessageSend(Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void switchSendMessage() {
		System.out.println("switchSendMessage");
		this.mMessageController.setCurrentUser(this.currentUser);
		WriteMessageView writeMessage = new WriteMessageView(this.mMessageController);
		this.mMainView.selectView(writeMessage);
		
	}
}
