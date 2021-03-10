package gemeral;

public enum ItemType {
	SWORD, PICKAXE, AXE, SHOWEL, HOE, BOOTS, LEGGINGS, CHESTPLATE, HELMET, BOW, UNKNOWN, ANY;
	
	public static String[] getNames() {
		String[] typeStrings = new String[ItemType.values().length];
		for (int i = 0; i < typeStrings.length; i++) {
			typeStrings[i] = ItemType.values()[i].name();
		}
		return typeStrings;
	}
}
