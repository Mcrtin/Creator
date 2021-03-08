package windows;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.bukkit.Material;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import gemeral.ItemType;
import gemeral.JSONObjects;
import gemeral.JUtilities;
import gemeral.Skill;

public class Item {
	KeyAdapter keyAdapter = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			if (Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '\b')
				((JTextField)e.getComponent()).setEditable(true);
			else
				((JTextField)e.getComponent()).setEditable(false);
		}
	};
	WindowAdapter windowAdapter = new WindowAdapter() {
		@Override
		 
		public void windowClosing(WindowEvent e) {
			OpenFileWindow.openFile(MainWindow.items, 1, MainWindow.mainWindowPanel);
		 
		}
	}; 
	ActionListener create = new ActionListener() {
		
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			JSONObject jsonObjects = JSONObjects.read(MainWindow.items);
			JSONObject jsonObject = new JSONObject();
			if (((JTextField) hashMap.get("name").getComponent(1)).getText() == null) {
				JOptionPane.showMessageDialog(null, "Bitte gib einen gültigen namen an!", "Ein Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
				return;
			}
			for (String string : hashMap.keySet()) {
				if (hashMap.get(string).getComponent(1) instanceof JTextField)
					jsonObject.put(string, ((JTextField) hashMap.get(string).getComponent(1)).getText());
				else
					jsonObject.put(string, ((JComboBox<String>) hashMap.get(string).getComponent(1)).getSelectedItem());
			}
			JSONArray jsonArray = ((JSONArray) jsonObjects.get("items"));
			jsonArray.add(jsonObject);
			jsonObjects.put("items", jsonArray);
			JSONObjects.write(MainWindow.items, jsonObjects);
		}
	};
	HashMap<String, JPanel> hashMap = new HashMap<String, JPanel>();
	
	public JFrame jFrame = JUtilities.createJFrame("Create Item", 0, 0, 1000, 1000);
	public Item() {
		String[] materialStrings = new String[Material.values().length];
		for (int i = 0; i < materialStrings.length; i++) {
			materialStrings[i] = Material.values()[i].name();
		}
		String[] typeStrings = new String[ItemType.values().length];
		for (int i = 0; i < typeStrings.length; i++) {
			typeStrings[i] = ItemType.values()[i].name();
		}
		JComboBox<String> jComboBox = new JComboBox<String>(materialStrings);
		hashMap.put("material", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Material:"), jComboBox));
		
		JComboBox<String> jComboBox2 = new JComboBox<String>(typeStrings);
		hashMap.put("type", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Type:"), jComboBox2));
		
		hashMap.put("name", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Name:"), new JTextField(20)));
		
		hashMap.put("atack_damage", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Attack damage:"), textField()));
		hashMap.put("health", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("health:"), textField()));
		hashMap.put("healthregen", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("health regeneration:"), textField()));
		hashMap.put("attack_speed", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Attack speed:"), textField()));
		hashMap.put("speed", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("speed:"), textField()));
		hashMap.put("lore", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("lore:"), new JTextField(20)));
		
		hashMap.put("minTotallevel", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min Totallevel:"), textField()));
		hashMap.put(Skill.TOOLSMITHING.name(), JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min toolsmithing:"), textField()));
		hashMap.put(Skill.WEAPONSMITHING.name(), JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min weaponsmithing:"), textField()));
		hashMap.put(Skill.ENCHANTING.name(), JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min enchanting:"), textField()));
		hashMap.put(Skill.AMORING.name(), JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min armoring:"), textField()));
		hashMap.put(Skill.BREWING.name(), JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min brewing:"), textField()));
		hashMap.put(Skill.FISHING.name(), JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min fishing:"), textField()));
		hashMap.put(Skill.SUMMONING.name(), JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min summoning:"), textField()));
		
		
		
		JButton jButton = JUtilities.createButton("Erstellen", 0, 0, 0, 0, create);
		
		JPanel jPanel = JUtilities.createJPanel(0, 0, 0, 0, new GridLayout(0, 2));
		for (JPanel jPanel2 : hashMap.values())
			jPanel.add(jPanel2);
		
		jFrame.add(jPanel);
		jFrame.add(jButton, BorderLayout.AFTER_LAST_LINE);
		jFrame.addWindowListener(windowAdapter);
	}
	
	JTextField textField() {
		JTextField jTextField = new JTextField(10);
		jTextField.addKeyListener(keyAdapter);
		return jTextField;
	}
	
	
	
	
	
	
	
	
	public Item(JSONObject jsonObject) {
		try {
			JSONObject jsonObjects = JSONObjects.read(MainWindow.items);
			JSONArray jsonArray = ((JSONArray) jsonObjects.get("items"));
			jsonArray.remove(jsonObject);
			String[] materialStrings = new String[Material.values().length];
			for (int i = 0; i < materialStrings.length; i++) {
				materialStrings[i] = Material.values()[i].name();
			}
			String[] typeStrings = new String[ItemType.values().length];
			for (int i = 0; i < typeStrings.length; i++) {
				typeStrings[i] = ItemType.values()[i].name();
			}
			JComboBox<String> jComboBox = new JComboBox<String>(materialStrings);
			jComboBox.setSelectedItem(jsonObject.get("material"));
			hashMap.put("material", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Material:"), jComboBox));
			
			JComboBox<String> jComboBox2 = new JComboBox<String>(typeStrings);
			jComboBox2.setSelectedItem(jsonObject.get("type"));
			hashMap.put("type", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Type:"), jComboBox2));
			JTextField jTextField = new JTextField(20);
			jTextField.setText((String) jsonObject.get("name"));
			hashMap.put("name", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Name:"), jTextField));
			
			hashMap.put("atack_damage", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Attack damage:"), textField((String) jsonObject.get("atack_damage"))));
			hashMap.put("health", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("health:"), textField((String) jsonObject.get("health"))));
			hashMap.put("healthregen", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("health regeneration:"), textField((String) jsonObject.get("healthregen"))));
			hashMap.put("attack_speed", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Attack speed:"), textField((String) jsonObject.get("attack_speed"))));
			hashMap.put("speed", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("speed:"), textField((String) jsonObject.get("speed"))));
			jTextField.setText((String) jsonObject.get("lore"));
			hashMap.put("lore", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("lore:"), jTextField));
			
			hashMap.put("minTotallevel", JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min Totallevel:"), textField((String) jsonObject.get("minTotallevel"))));
			hashMap.put(Skill.TOOLSMITHING.name(), JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min toolsmithing:"), textField((String) jsonObject.get(Skill.TOOLSMITHING.name()))));
			hashMap.put(Skill.WEAPONSMITHING.name(), JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min weaponsmithing:"), textField((String) jsonObject.get(Skill.WEAPONSMITHING.name()))));
			hashMap.put(Skill.ENCHANTING.name(), JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min enchanting:"), textField((String) jsonObject.get(Skill.ENCHANTING.name()))));
			hashMap.put(Skill.AMORING.name(), JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min armoring:"), textField((String) jsonObject.get(Skill.AMORING.name()))));
			hashMap.put(Skill.BREWING.name(), JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min brewing:"), textField((String) jsonObject.get(Skill.BREWING.name()))));
			hashMap.put(Skill.FISHING.name(), JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min fishing:"), textField((String) jsonObject.get(Skill.FISHING.name()))));
			hashMap.put(Skill.SUMMONING.name(), JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min summoning:"), textField((String) jsonObject.get(Skill.SUMMONING.name()))));
			
			
			
			JButton override = JUtilities.createButton("Überschreiben", 0, 0, 0, 0, overRide);
			JButton exitB = JUtilities.createButton("abbrechen", 0, 0, 0, 0, exit);
			
			JPanel buttons = JUtilities.createJPanel(0, 0, 0, 0, override, exitB);
			JPanel jPanel = JUtilities.createJPanel(0, 0, 0, 0, new GridLayout(0, 2));
			for (JPanel jPanel2 : hashMap.values())
				jPanel.add(jPanel2);
			
			jFrame.add(jPanel);
			
			jFrame.add(buttons, BorderLayout.AFTER_LAST_LINE);
			jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	ActionListener overRide = new ActionListener() {
		
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			JSONObject jsonObjects = JSONObjects.read(MainWindow.items);
			JSONObject jsonObject = new JSONObject();
			if (((JTextField) hashMap.get("name").getComponent(1)).getText() == null) {
				JOptionPane.showMessageDialog(null, "Bitte gib einen gültigen namen an!", "Ein Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
				return;
			}
			for (String string : hashMap.keySet()) {
				if (hashMap.get(string).getComponent(1) instanceof JTextField)
					jsonObject.put(string, ((JTextField) hashMap.get(string).getComponent(1)).getText());
				else
					jsonObject.put(string, ((JComboBox<String>) hashMap.get(string).getComponent(1)).getSelectedItem());
			}
			JSONArray jsonArray = ((JSONArray) jsonObjects.get("items"));
			jsonArray.add(jsonObject);
			jsonObjects.put("items", jsonArray);
			JSONObjects.write(MainWindow.items, jsonObjects);
			
		}
	};
	ActionListener exit = new ActionListener() {
		
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			JSONObject jsonObjects = JSONObjects.read(MainWindow.items);
			JSONObject jsonObject = new JSONObject();
			if (((JTextField) hashMap.get("name").getComponent(1)).getText() == null) {
				JOptionPane.showMessageDialog(null, "Bitte gib einen gültigen namen an!", "Ein Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
				return;
			}
			for (String string : hashMap.keySet()) {
				if (hashMap.get(string).getComponent(1) instanceof JTextField)
					jsonObject.put(string, ((JTextField) hashMap.get(string).getComponent(1)).getText());
				else
					jsonObject.put(string, ((JComboBox<String>) hashMap.get(string).getComponent(1)).getSelectedItem());
			}
			JSONArray jsonArray = ((JSONArray) jsonObjects.get("items"));
			jsonArray.add(jsonObject);
			jsonObjects.put("items", jsonArray);
			JSONObjects.write(MainWindow.items, jsonObjects);
			
		}
	};
	
	JTextField textField(String string) {
		JTextField jTextField = new JTextField(10);
		jTextField.setText(string);
		jTextField.addKeyListener(keyAdapter);
		return jTextField;
	}
}
