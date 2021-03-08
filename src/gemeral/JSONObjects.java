package gemeral;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONObjects {
	public static JSONObject read(File file) {
            try {
				return (JSONObject) new JSONParser().parse(new FileReader(file));
			} catch (IOException | ParseException e) {
				JOptionPane.showMessageDialog(null, e.getStackTrace(), "Ein Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
			}
			return null;
	}
	public static JSONObject[] readArray(JSONArray jsonArray) {
		JSONObject[] jos = new JSONObject[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			jos[i] = (JSONObject) jsonArray.get(i);
		}
		return jos;
	}
	@SuppressWarnings("resource")
	public static void write(File file, JSONObject jo) {
		try {
			new FileOutputStream(file).write(jo.toJSONString().getBytes());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getStackTrace(), "Ein Fehler ist aufgetreten", JOptionPane.ERROR_MESSAGE);
		}
	}
}
