package windows;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import gemeral.JSONObjects;
import gemeral.JUtilities;

public class OpenFileWindow {

	public static void openFile(File file, int value, JPanel jPanel) {
		JButton[] buttons = {MainWindow.createItems, MainWindow.createRecepies, MainWindow.createMobs};
		JSONObject jsonObject = JSONObjects.read(file);
		if (jsonObject == null) {
			return;
		}
		if (jsonObject.get("items") != null && (value == 0 || value == 1)) {
			MainWindow.items = file;
			JSONObject[] jsonObjects = JSONObjects.readArray((JSONArray) jsonObject.get("items"));
			MainWindow.mainWindow.remove(jPanel);
			if (jPanel.getComponentCount()/3 < jsonObjects.length + 2) {
				jPanel.remove(buttons[0]);
				jPanel.remove(buttons[1]);
				jPanel.remove(buttons[2]);
				
				for (int i = 0; i < jsonObjects.length; i++)
					jPanel = setComponent(i+2, 1, JUtilities.createButton((String) jsonObjects[i].get("name"), 0, 0, 0, 0, editItem), jPanel);
				jPanel = setComponent(jsonObjects.length+2, 1, buttons[0], jPanel);
				jPanel = setComponent(jsonObjects.length+2, 2, buttons[1], jPanel);
				jPanel = setComponent(jsonObjects.length+2, 3, buttons[2], jPanel);
				
			}
			jPanel.remove(jPanel.getComponentCount()-1);
			MainWindow.mainWindow.add(jPanel);
			MainWindow.mainWindowPanel = jPanel;
			MainWindow.mainWindow.setVisible(true);
		}
		if (jsonObject.get("recepies") != null && (value == 0 || value == 2)) {
			MainWindow.recepies = file;
			JSONObject[] jsonObjects = JSONObjects.readArray((JSONArray) jsonObject.get("recepies"));
			MainWindow.mainWindow.remove(jPanel);
			if (jPanel.getComponentCount()/3 < jsonObjects.length + 2) {
				jPanel.remove(buttons[0]);
				jPanel.remove(buttons[1]);
				jPanel.remove(buttons[2]);
				
				for (int i = 0; i < jsonObjects.length; i++)
					jPanel = setComponent(i+2, 1, JUtilities.createButton((String) jsonObjects[i].get("name"), 0, 0, 0, 0, editItem), jPanel);
				jPanel = setComponent(jsonObjects.length+2, 1, buttons[0], jPanel);
				jPanel = setComponent(jsonObjects.length+2, 2, buttons[1], jPanel);
				jPanel = setComponent(jsonObjects.length+2, 3, buttons[2], jPanel);
				
			}
			jPanel.remove(jPanel.getComponentCount()-1);
			MainWindow.mainWindow.add(jPanel);
			MainWindow.mainWindow.setVisible(true);
		}
		if (jsonObject.get("mobs") != null && (value == 0 || value == 3)) {
			MainWindow.mobs = file;
			JSONObject[] jsonObjects = JSONObjects.readArray((JSONArray) jsonObject.get("mobs"));
			MainWindow.mainWindow.remove(jPanel);
			if (jPanel.getComponentCount()/3 < jsonObjects.length + 2) {
				jPanel.remove(buttons[0]);
				jPanel.remove(buttons[1]);
				jPanel.remove(buttons[2]);
				
				for (int i = 0; i < jsonObjects.length; i++)
					jPanel = setComponent(i+2, 1, JUtilities.createButton((String) jsonObjects[i].get("name"), 0, 0, 0, 0, editItem), jPanel);
				jPanel = setComponent(jsonObjects.length+2, 1, buttons[0], jPanel);
				jPanel = setComponent(jsonObjects.length+2, 2, buttons[1], jPanel);
				jPanel = setComponent(jsonObjects.length+2, 3, buttons[2], jPanel);
				
			}
			jPanel.remove(jPanel.getComponentCount()-1);
			MainWindow.mainWindow.add(jPanel);
			MainWindow.mainWindow.setVisible(true);
		
		}
		
	}
	
	static ActionListener editItem = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new Item(getJsonObjectWithName(((JButton)e.getSource()).getText(), MainWindow.items, "items"));
		}
	};
	public static JSONObject getJsonObjectWithName(String name, File file, String type) {
		JSONObject jsonObjects = JSONObjects.read(file);
		JSONArray jsonArray = ((JSONArray) jsonObjects.get(type));
		for (JSONObject jsonObject : JSONObjects.readArray(jsonArray))
			if (name.equals(jsonObject.get("name")))
				return jsonObject;
		System.err.println("ein fehler ist unterlaufen");
		return null;
	}
	
	public static JPanel setComponent(int row, int collum, Component data, JPanel jPanel) {
		for (int i = jPanel.getComponentCount(); i <= (row-1)*3+collum; i++) {
			jPanel.add(JUtilities.createText(" ", 0, 0, 0, 0));
		}
		
		jPanel.remove((row-1)*3+collum-1);
		jPanel.add(data, (row-1)*3+collum-1);
			
		return jPanel;
	}
	
}