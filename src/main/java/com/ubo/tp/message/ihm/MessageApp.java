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
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
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
import main.java.com.ubo.tp.message.ihm.component.MessageView;
import main.java.com.ubo.tp.message.ihm.component.ProfileView;
import main.java.com.ubo.tp.message.ihm.component.ReadMessageView;
import main.java.com.ubo.tp.message.ihm.component.FiltreView;
import main.java.com.ubo.tp.message.ihm.component.FollowerManagementView;
import main.java.com.ubo.tp.message.ihm.component.WriteMessageView;
import main.java.com.ubo.tp.message.ihm.controller.RCController;
import main.java.com.ubo.tp.message.ihm.controller.FollowerController;
import main.java.com.ubo.tp.message.ihm.controller.MenuController;
import main.java.com.ubo.tp.message.ihm.controller.MessageController;
import main.java.com.ubo.tp.message.ihm.controller.ProfileController;
import main.java.com.ubo.tp.message.ihm.observer.IMessageObserver;
import main.java.com.ubo.tp.message.ihm.observer.MenuObserver;
import main.java.com.ubo.tp.message.ihm.observer.RCObserver;
import main.java.com.ubo.tp.message.ihm.session.ISessionObserver;
import main.java.com.ubo.tp.message.ihm.session.Session;


/**
 * Classe principale l'application.
 *
 * @author S.Lucas
 */
public class MessageApp implements IDatabaseObserver, IWatchableDirectory, RCObserver, ISessionObserver, MenuObserver, IMessageObserver {
	Boolean debug = false;
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
	
	protected FollowerController mfollowerController;

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
		this.mProfileController = new ProfileController(null, database);
		this.mMenuController = new MenuController();
		this.mfollowerController = new FollowerController(mDatabase, null, this.mEntityManager);	
	}

	/**
	 * Initialisation de l'application.
	 */
	public void init() {
		// Initialisation de l'IHM
		this.initGui();
		
		// Initialisation du répertoire d'échange
		this.initDirectory();
		
		// Initialisation des observers
		this.initObserver();
		
		// Init du look and feel de l'application
		this.initLookAndFeel();
		
		//this.initTest();
	}

	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel() {
		MenuView menu = new MenuView(this.mMenuController);
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		this.mMainView = new MessageAppMainView(mDatabase, mEntityManager);
		
		
		if (debug) {
			//String chemin = "C://Users/yoann/OneDrive/Bureau/M2";
			String chemin = "H:/Telechargements/IHM/Rep";
			
			this.mEntityManager.setExchangeDirectory(chemin);
			
			File dir = new File(chemin);
			File[] files = dir.listFiles();
			Set<File> presentFiles = new HashSet<File>();
			for(File f : files) {
				presentFiles.add(f);
			}
			
			this.mEntityManager.notifyPresentFiles(presentFiles);
			
			//this.initDirectory(chemin);

		} else {
			File dir = this.mMainView.selectDirectory();

			while (!this.isValideExchangeDirectory(dir)) {
				dir = this.mMainView.selectDirectory();
			}

			this.mEntityManager.setExchangeDirectory(dir.getAbsolutePath());
			
			File[] files = dir.listFiles();
			Set<File> presentFiles = new HashSet<File>();
			for(File f : files) {
				presentFiles.add(f);
			}
			
			this.mEntityManager.notifyPresentFiles(presentFiles);

			
			//this.initDirectory(dir.getAbsolutePath());
		}
		this.mMainView.initGui();
		//ConnectionView log = new ConnectionView(this.mRCController);
		// test envoie message
		// WriteMessageView log = new WriteMessageView(this.mMessageController);
//		MenuView menu = new MenuView(this.mMenuController);
//		this.mMainView.updateMenu(menu);
		//this.mMainView.selectView(log);
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
//		System.out.println(	addedMessage.getSender().getName()+" : " + addedMessage.getText());
//		System.out.println("UserTags :");
//		for(String s : addedMessage.getUserTags()) {
//			System.out.println(s);
//		}
//		System.out.println("Tags :");
//		for(String s : addedMessage.getTags()) {
//			System.out.println(s);
//		}

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
		//System.out.println("AddUser" + addedUser);
		ConnectionView log = new ConnectionView(this.mRCController);
		this.mMainView.selectView(log);
		
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
		
		MenuView menu = new MenuView(this.mMenuController, true);

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
	public void switchDeconnexion(List<JMenu> compostion) {
		System.out.println("Deconnecte");
		this.currentUser = null;
		ConnectionView log = new ConnectionView(this.mRCController);
		this.mMainView.updateMenu(compostion);
		this.mMainView.selectView(log);
	}
	
	@Override
	public void switchLaunch(List<JMenu> compostion) {
		System.out.println("Start");
		ConnectionView log = new ConnectionView(this.mRCController);
		this.mMainView.updateMenu(compostion);
		this.mMainView.selectView(log);
	}
	
	@Override
	public void switchConnexion(List<JMenu> compostion) {
		System.out.println("Connecte");
		
		this.mMainView.updateMenu(compostion);
	}

	@Override
	public void notifyMessageSend(Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void switchSendMessage() {
		System.out.println("switchSendMessage");
		this.mMessageController.setCurrentUser(this.currentUser);
		
		MessageView writeMessage = new MessageView(this.mMessageController);
		this.mMainView.selectView(writeMessage);
		
	}

	@Override
	public void goBack() {
		ConnectionView log = new ConnectionView(this.mRCController);
		this.mMainView.selectView(log);
	}
	
	@Override
	public void switchFollower() {
		System.out.println("switchSendFollower");
		this.mfollowerController.setCurrentUser(this.currentUser);
		
		FollowerManagementView followView = new FollowerManagementView(this.mfollowerController);
		this.mMainView.selectView(followView);
		
	}
	
	protected void initTest() {
		User u = new User(UUID.randomUUID(), "1420", "--", "Julien", new HashSet<String>(), 
				"H:/workspace/MessageApp/src/main/resources/images/abeille.png");
		this.mDatabase.addUser(u);
		this.mEntityManager.writeUserFile(u);
		Message m = new Message(u, "Encore plus de place dans ma bibliothéque");
		this.mDatabase.addMessage(m);
		this.mEntityManager.writeMessageFile(m);
		m = new Message(u, "Bon j'ai craqué, j'ai pris une case de la rarity collection 2 #YGO");
		this.mDatabase.addMessage(m);
		this.mEntityManager.writeMessageFile(m);
		
		
		u = new User(UUID.randomUUID(), "1543", "--", "Jake", new HashSet<String>(), 
				"H:/workspace/MessageApp/src/main/resources/images/j.PNG");
		this.mDatabase.addUser(u);
		this.mEntityManager.writeUserFile(u);
		m = new Message(u, "Oups j'ai encore acheté un #gundam");
		this.mDatabase.addMessage(m);
		this.mEntityManager.writeMessageFile(m);
		
		u = new User(UUID.randomUUID(), "1234", "--", "Yoann", new HashSet<String>(), 
				"H:/workspace/MessageApp/src/main/resources/images/y.png");
		this.mDatabase.addUser(u);
		this.mEntityManager.writeUserFile(u);
		m = new Message(u, "@Julien , tu viens au tournoi samedi ? #YGO");
		this.mDatabase.addMessage(m);
		this.mEntityManager.writeMessageFile(m);
		
		u = new User(UUID.randomUUID(), "8043", "--", "Justine", new HashSet<String>(), 
				"H:/workspace/MessageApp/src/main/resources/images/ju.PNG");
		this.mDatabase.addUser(u);
		this.mEntityManager.writeUserFile(u);
		m = new Message(u, "@Julien , on fait des lasagnes ?");
		this.mDatabase.addMessage(m);
		this.mEntityManager.writeMessageFile(m);
		
		u = new User(UUID.randomUUID(), "3210", "--", "Théliau", new HashSet<String>(), 
				"H:/workspace/MessageApp/src/main/resources/images/t.PNG");
		this.mDatabase.addUser(u);
		this.mEntityManager.writeUserFile(u);
		m = new Message(u, "Encore un raid ce soir...");
		this.mDatabase.addMessage(m);
		this.mEntityManager.writeMessageFile(m);
	}
}
