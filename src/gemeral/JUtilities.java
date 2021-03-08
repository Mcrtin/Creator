package gemeral;

import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class JUtilities {
	public static JLabel createText(String text, int x, int y, int width, int height) {
		JLabel textField = new JLabel(text);
		textField.setBounds(x, y, width, height);
		return textField;
	}
	public static JTextField createTextField(int x, int y, int width, int height, String text) {
		JTextField textField = new JTextField(text);
		textField.setBounds(x, y, width, height);
		return textField;
	}
	public static JButton createButton(String text, int x, int y, int width, int height, ActionListener listener) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		button.addActionListener(listener);
		return button;
	}
	public static JButton createButton(String text, int x, int y, int width, int height, ActionListener listener, Font font) {
		JButton button = new JButton(text);
		button.setBounds(x, y, width, height);
		button.setFont(font);
		button.addActionListener(listener);
		return button;
	}
	/**
	 * Fensterinhalt
	 * @param name Name vom Fenster.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static JFrame createJFrame(String name, int x, int y, int width, int height) {
		JFrame jFrame = new JFrame(name);
		jFrame.setBounds(x, y, width, height);
		jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jFrame.setVisible(true);
		return jFrame; 
	}
	/**
	 * Erstelle einen neuen Container.
	 * @param x 
	 * @param y
	 * @param width
	 * @param height
	 * @param jButtons
	 * @return
	 */
	public static JPanel createJPanel(int x, int y, int width, int height, Component...components) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(x, y, width, height);
		for(Component component : components) {
			jPanel.add(component);
		}
		return jPanel;
	}
	/**
	 * Erstelle einen neuen Container.
	 * @param x 
	 * @param y
	 * @param width
	 * @param height
	 * @param jButtons
	 * @return
	 */
	public static JPanel createJPanel(int x, int y, int width, int height, LayoutManager layout, Component... components) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(x, y, width, height);
		for(Component component : components)
			jPanel.add(component);
		
		jPanel.setLayout(layout);
		return jPanel;
	}
	/**
	 * Erstelle einen neuen Container.
	 * @param x 
	 * @param y
	 * @param width
	 * @param height
	 * @param jButton
	 * @return
	 */
	public static JPanel createJPanel(int x, int y, int width, int height, JButton jButton) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(x, y, width, height);
		jPanel.add(jButton);
		return jPanel;
	}
	
	public static JFileChooser createJFileChooser() {
		JFileChooser jFileChooser = new JFileChooser();
		return jFileChooser;
	}
	public static JMenuBar createJMenuBar(JMenu...jMenus) {
		JMenuBar jMenuBar = new JMenuBar();
		for (JMenu jMenu : jMenus)
			jMenuBar.add(jMenu);
		return jMenuBar;
	}
	public static JMenuBar createJMenuBar(JMenu jMenu) {
		JMenuBar jMenuBar = new JMenuBar();
		jMenuBar.add(jMenu);
		jMenuBar.setVisible(true);
		return jMenuBar;
	}
	public static JMenu createJMenu(String text, JMenuItem...jMenuItems) {
		JMenu jMenu = new JMenu(text);
		for (JMenuItem jMenuItem : jMenuItems)
			jMenu.add(jMenuItem);
		return jMenu;
	}
	public static JMenu createJMenu(String text, JMenuItem jMenuItem) {
		JMenu jMenu = new JMenu(text);
		jMenu.add(jMenuItem);
		return jMenu;
	}
	public static JMenuItem createJMenuItem(String text, ActionListener performAction) {
		JMenuItem jMenuItem = new JMenuItem(text);
		jMenuItem.addActionListener(performAction);
		return jMenuItem;
	}
	/*public static JTable createJTable(int rows, int columns) {
		JTable jTable = new JTable(rows, columns);
		return jTable;
	}*/
	public static JTable createJTable(Object[][] data, Object[] columnNames, int x, int y, int width, int height) {
		JTable jTable = new JTable(data, columnNames);
		jTable.setBounds(x, y, width, height);
		jTable.setVisible(true);
		
		return jTable;
	}
	public static JList<JButton> createJList(JButton[] data, int x, int y, int width, int height) {
		JList<JButton> jList = new JList<JButton>(data);
		jList.setBounds(x, y, width, height);
		jList.setVisible(true);
		
		return jList;
	}
	
}
