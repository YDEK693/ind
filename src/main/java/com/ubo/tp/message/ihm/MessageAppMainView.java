package main.java.com.ubo.tp.message.ihm;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.java.com.ubo.tp.message.core.EntityManager;
import main.java.com.ubo.tp.message.core.database.IDatabase;
import main.java.com.ubo.tp.message.ihm.component.CreateUserView;

/**
 * Classe de la vue principale de l'application.
 */
public class MessageAppMainView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2055343770028618239L;
	//vue actuelle
	protected JPanel currentVue;
	protected JMenuBar currentBar;
	
	public MessageAppMainView(IDatabase database, EntityManager entityManager) {
		super("MSN");
		// this.add(new CreateUserComponent(database, entityManager));
		//this.initGui();
	}
	
	protected void initGui() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
		this.setLayout(new GridBagLayout());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 1000);
		ImageIcon avatar = new ImageIcon("H:/workspace/MessageApp/src/main/resources/images/FrameLogo.png");	
		this.setIconImage(avatar.getImage());
//		this.setLayout(new FlowLayout());
//		
		this.currentBar = new JMenuBar();
//        JMenu fichier = new JMenu("Fichier");
//        JMenu help = new JMenu("?");
//        JMenuItem aPropos = new JMenuItem("A propos");
//        menuBar.add(fichier);
//        menuBar.add(help);
//        help.add(aPropos);
//
//        // Ajout du menu � la fen�tre
//        this.setJMenuBar(menuBar);
//		
//        aPropos.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(MessageAppMainView.this, "N'h�siter pas � me soutenir sur Patreon @HornyBeeAimeL'Argent");
//            }
//        });
//        
//        Button QuitButton = new Button("Quitter");
//        QuitButton.setPreferredSize(new Dimension(100, 50));
//        QuitButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				System.exit(0);
//			}
//		});
//        
//        fichier.add(QuitButton);
//        this.updateMenu(null);
		//Icon Moche 
//		ImageIcon icon = new ImageIcon("H:/workspace/MessageApp/src/main/resources/images/exitIcon_20.png");
//		JLabel textLabel = new JLabel("Quitter");
//		JLabel imageLabel = new JLabel(icon);
//		
//		JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//        panel.add(textLabel, BorderLayout.CENTER);
//        panel.add(imageLabel, BorderLayout.LINE_START);
//		
//		JButton QuitButton = new JButton("Quitter");
//		//QuitButton.setPreferredSize(new Dimension(100, 50));
//		QuitButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				System.exit(0);
//			}
//		});
//		
//		QuitButton.setLayout(new BorderLayout());
//		QuitButton.add(panel);
		
//		this.add(QuitButton, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTHWEST,
//				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
	}
	
	protected File selectDirectory() {
		File selectedDirectory = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fileChooser.showOpenDialog(MessageAppMainView.this);
		if (result == JFileChooser.APPROVE_OPTION) {
			// R�cup�rer le dossier s�lectionn�
			selectedDirectory = fileChooser.getSelectedFile();
			JOptionPane.showMessageDialog(MessageAppMainView.this,
					"Dossier s�lectionn� : " + selectedDirectory.getAbsolutePath());
		}
		
		return selectedDirectory;
	}
      
	@SuppressWarnings("deprecation")
	protected void selectView(JPanel vue) {
		if(this.currentVue != null) {
			this.remove(this.currentVue);
		}
		
		this.currentVue = vue;
		
		this.add(this.currentVue);
		this.show();
		this.repaint();
		
	}
	
	public void updateMenu(List<JMenu> bar) {
		this.currentBar.removeAll();
		
		for(JMenu menu : bar) {
			this.currentBar.add(menu);
		}
		
		this.setJMenuBar(this.currentBar);
		this.revalidate();
		this.repaint();
	}
}
