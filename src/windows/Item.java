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
	public static final String NAME = "name";
	public static final String MATERIAL = "material";
	public static final String TYPE = "type";
	public static final String ATACK_DAMAGE = "atack_damage";
	public static final String HEALTH = "health";
	public static final String HEALTH_REGENERATION = "healthregen";
	public static final String ATACK_SPEED = "atack_speed";
	public static final String SPEED = "speed";
	public static final String LORE = "lore";
	
	public static final String MINIMUM_TOTAL_LEVEL = "minTotallevel";
	public static final String MINIMUM_AMORING_LEVEL = Skill.AMORING.name();
	public static final String MINIMUM_BREWING_LEVEL = Skill.BREWING.name();
	public static final String MINIMUM_ENCHANTING_LEVEL = Skill.ENCHANTING.name();
	public static final String MINIMUM_FISHING_LEVEL = Skill.FISHING.name();
	public static final String MINIMUM_SUMMONING_LEVEL = Skill.SUMMONING.name();
	public static final String MINIMUM_TOOLSMOTHING_LEVEL = Skill.TOOLSMITHING.name();
	public static final String MINIMUM_WEAPONSMITHING_LEVEL = Skill.WEAPONSMITHING.name();

	public static final String[] MATERIALS = getMaterialNames();
	public static final String[] TYPES = ItemType.getNames();
	
	public JFrame jFrame = JUtilities.createJFrame("Create Item", 0, 0, 1000, 1000);
	HashMap<String, JPanel> hashMap = new HashMap<String, JPanel>();
	
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
	
	public Item() {
		
		hashMap.put(MATERIAL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Material:"), new JComboBox<String>(MATERIALS)));
		hashMap.put(TYPE, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Type:"), new JComboBox<String>(TYPES)));
		hashMap.put(NAME, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Name:"), new JTextField(20)));
		
		hashMap.put(ATACK_DAMAGE, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Attack damage:"), textField()));
		hashMap.put(HEALTH, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("health:"), textField()));
		hashMap.put(HEALTH_REGENERATION, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("health regeneration:"), textField()));
		hashMap.put(ATACK_SPEED, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Attack speed:"), textField()));
		hashMap.put(SPEED, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("speed:"), textField()));
		hashMap.put(LORE, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("lore:"), new JTextField(20)));
		
		hashMap.put(MINIMUM_TOTAL_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min Totallevel:"), textField()));
		hashMap.put(MINIMUM_AMORING_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min toolsmithing:"), textField()));
		hashMap.put(MINIMUM_BREWING_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min weaponsmithing:"), textField()));
		hashMap.put(MINIMUM_ENCHANTING_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min enchanting:"), textField()));
		hashMap.put(MINIMUM_FISHING_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min armoring:"), textField()));
		hashMap.put(MINIMUM_SUMMONING_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min brewing:"), textField()));
		hashMap.put(MINIMUM_TOOLSMOTHING_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min fishing:"), textField()));
		hashMap.put(MINIMUM_WEAPONSMITHING_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min summoning:"), textField()));
		
		
		
		JButton jButton = JUtilities.createButton("Erstellen", 0, 0, 0, 0, create);
		
		JPanel jPanel = JUtilities.createJPanel(0, 0, 0, 0, new GridLayout(0, 2));
		for (JPanel jPanel2 : hashMap.values())
			jPanel.add(jPanel2);
		
		jFrame.add(jPanel);
		jFrame.add(jButton, BorderLayout.AFTER_LAST_LINE);
		jFrame.addWindowListener(windowAdapter);
	}
	
	public Item(JSONObject jsonObject) {
		try {

			JComboBox<String> jComboBox = new JComboBox<String>(MATERIALS);
			jComboBox.setSelectedItem(jsonObject.get(MATERIAL));
			hashMap.put(MATERIAL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Material:"), jComboBox));
			
			JComboBox<String> jComboBox2 = new JComboBox<String>(TYPES);
			jComboBox2.setSelectedItem(jsonObject.get(TYPE));
			hashMap.put(TYPE, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Type:"), jComboBox2));
			JTextField jTextField = new JTextField(20);
			jTextField.setText((String) jsonObject.get(NAME));
			System.out.println(jsonObject.get(NAME));
			hashMap.put(NAME, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Name:"), jTextField));
			
			hashMap.put(ATACK_DAMAGE, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Attack damage:"), textField((String) jsonObject.get(ATACK_DAMAGE))));
			hashMap.put(HEALTH, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("health:"), textField((String) jsonObject.get(HEALTH))));
			hashMap.put(HEALTH_REGENERATION, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("health regeneration:"), textField((String) jsonObject.get(HEALTH_REGENERATION))));
			hashMap.put(ATACK_SPEED, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("Attack speed:"), textField((String) jsonObject.get(ATACK_SPEED))));
			hashMap.put(SPEED, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("speed:"), textField((String) jsonObject.get(SPEED))));
			jTextField.setText((String) jsonObject.get("lore"));
			hashMap.put(LORE, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("lore:"), jTextField));
			
			hashMap.put(MINIMUM_TOTAL_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min Totallevel:"), textField((String) jsonObject.get(MINIMUM_TOTAL_LEVEL))));
			hashMap.put(MINIMUM_AMORING_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min toolsmithing:"), textField((String) jsonObject.get(MINIMUM_AMORING_LEVEL))));
			hashMap.put(MINIMUM_BREWING_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min weaponsmithing:"), textField((String) jsonObject.get(MINIMUM_BREWING_LEVEL))));
			hashMap.put(MINIMUM_ENCHANTING_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min enchanting:"), textField((String) jsonObject.get(MINIMUM_ENCHANTING_LEVEL))));
			hashMap.put(MINIMUM_FISHING_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min armoring:"), textField((String) jsonObject.get(MINIMUM_FISHING_LEVEL))));
			hashMap.put(MINIMUM_SUMMONING_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min brewing:"), textField((String) jsonObject.get(MINIMUM_SUMMONING_LEVEL))));
			hashMap.put(MINIMUM_TOOLSMOTHING_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min fishing:"), textField((String) jsonObject.get(MINIMUM_TOOLSMOTHING_LEVEL))));
			hashMap.put(MINIMUM_WEAPONSMITHING_LEVEL, JUtilities.createJPanel(0, 0, 0, 0, new JLabel("min summoning:"), textField((String) jsonObject.get(MINIMUM_WEAPONSMITHING_LEVEL))));
			
			
			
			JButton override = JUtilities.createButton("Überschreiben", 0, 0, 0, 0, overRide);
			
			JButton deliteButton = JUtilities.createButton("löschen", 0, 0, 0, 0, delite);
			deliteButton.setName(jsonObject.toJSONString());
			
			JPanel buttons = JUtilities.createJPanel(0, 0, 0, 0, override, deliteButton);
			JPanel jPanel = JUtilities.createJPanel(0, 0, 0, 0, new GridLayout(0, 2));
			for (JPanel jPanel2 : hashMap.values())
				jPanel.add(jPanel2);
			
			jFrame.add(jPanel);
			
			jFrame.add(buttons, BorderLayout.AFTER_LAST_LINE);
			
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
			JSONArray jsonArray = ((JSONArray) jsonObjects.get("items"));
			if (((JTextField) hashMap.get(NAME).getComponent(1)).getText() == null || ((JTextField) hashMap.get(NAME).getComponent(1)).getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "Bitte gib einen gültigen namen an!", "Ein Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
				return;
			} else
				for (Object jObject : jsonArray)
					if (((JSONObject)jObject).get("name").equals(((JTextField) hashMap.get("name").getComponent(1)).getText())) {
						JOptionPane.showMessageDialog(null, "Bitte gib einen gültigen namen an!", "Ein Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
						return;
					}
						
			for (String string : hashMap.keySet()) {
				if (hashMap.get(string).getComponent(1) instanceof JTextField)
					jsonObject.put(string, ((JTextField) hashMap.get(string).getComponent(1)).getText());
				else
					jsonObject.put(string, ((JComboBox<String>) hashMap.get(string).getComponent(1)).getSelectedItem());
			}
			
			jsonArray.add(jsonObject);
			jsonArray.remove(((JButton)e.getSource()).getName());
			jsonObjects.put("items", jsonArray);
			JSONObjects.write(MainWindow.items, jsonObjects);
			
		}
	};
	ActionListener delite = new ActionListener() {
		
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			JSONObject jsonObjects = JSONObjects.read(MainWindow.items);
			JSONArray jsonArray = ((JSONArray) jsonObjects.get("items"));
			jsonArray.remove(((JButton)e.getSource()).getName());
			jsonObjects.put("items", jsonArray);
			JSONObjects.write(MainWindow.items, jsonObjects);
			((JFrame) ((JButton)e.getSource()).getParent().getParent().getParent().getParent().getParent()).dispose();
			OpenFileWindow.openFile(MainWindow.items, 1, MainWindow.mainWindowPanel);
		}
	};
	
	JTextField textField(String string) {
		JTextField jTextField = new JTextField(10);
		jTextField.setText(string);
		jTextField.addKeyListener(keyAdapter);
		return jTextField;
	}
	private static String[] getMaterialNames() {
		String[] materialStrings = new String[Material.values().length];
		for (int i = 0; i < materialStrings.length; i++) {
			materialStrings[i] = Material.values()[i].name();
		}
		return materialStrings;
	}
	JTextField textField() {
		JTextField jTextField = new JTextField(10);
		jTextField.addKeyListener(keyAdapter);
		return jTextField;
	}

}
