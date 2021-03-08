package windows;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import gemeral.JUtilities;

public class MainWindow {
	public static File items = null;
	public static File recepies = null;
	public static File mobs = null;
	public static JFrame mainWindow = null;
	public static JPanel mainWindowPanel = null;
	private static void openFiel(int value) {
		JFileChooser fileChooser = JUtilities.createJFileChooser();
		int returnValue = fileChooser.showOpenDialog(null);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			
			OpenFileWindow.openFile(file, value, mainWindowPanel);
			
		}
	}
	ActionListener chooseFile = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			openFiel(0);
		}
	};
	ActionListener chooseFile1 = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			openFiel(1);
		}
	};
	ActionListener chooseFile2 = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			openFiel(2);
		}
	};
	ActionListener chooseFile3 = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			openFiel(3);
		}
	};
	static ActionListener createItem = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (items != null) {
				new Item();
			} else {
				openFiel(1);
			}
			
		}
	};
	static ActionListener createRecepie = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (recepies != null) {
				CreateRecepie.createRecepie();
			} else {
				openFiel(2);
			}
			
		}
	};
	static ActionListener createMob = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (mobs != null) {
				CreateMob.createMob();
			} else {
				openFiel(3);
			}
			
		}
	};
	
	public static JButton createItems = JUtilities.createButton("new item", 0, 0, 0, 0, createItem);
	public static JButton createRecepies = JUtilities.createButton("new recepie", 0, 0, 0, 0, createRecepie);
	public static JButton createMobs = JUtilities.createButton("new mob", 0, 0, 0, 0, createMob);
	
	
	JButton Items = JUtilities.createButton("Items", 0, 0, 0, 0, chooseFile1, new Font("Serif", Font.BOLD, 20));
	JButton Recepies = JUtilities.createButton("Recepies", 0, 0, 0, 0, chooseFile2, new Font("Serif", Font.BOLD, 20));
	JButton Mobs = JUtilities.createButton("Mobs", 0, 0, 0, 0, chooseFile3, new Font("Serif", Font.BOLD, 20));
	
	JMenuItem openItem = JUtilities.createJMenuItem("Importiere Datei..", chooseFile);
	JMenu jMenu = JUtilities.createJMenu("Datei", openItem);
	JMenuBar jMenuBar = JUtilities.createJMenuBar(jMenu);
	
	JFrame jFrame = JUtilities.createJFrame("Generator", 0, 0, 1000, 1000);
	
	JPanel jPanel = JUtilities.createJPanel(0, 0, 100, 100, new GridLayout(0, 3), Items, Recepies, Mobs, createItems, createRecepies, createMobs);
	public MainWindow() {
		mainWindow = jFrame;
		mainWindowPanel = jPanel;
		jFrame.add(jMenuBar, BorderLayout.NORTH);
		jFrame.add(jPanel);
	}
}
