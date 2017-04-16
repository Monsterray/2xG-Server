package server.model.players.saving;

//import java.io.FileOutputStream;

public class PvpItemSaving {
	
/*	public static void loadItemStats(Client c) {
		try {
			File f = new File("./deps/Data/characters/items/" + c.playerName);
			FileChannel fc = new FileInputStream(f).getChannel();
			ByteBuffer b = ByteBuffer.allocate((int) fc.size());
			fc.read(b);
			b.flip();
			for (int i = 0; i < c.pvpHelms.length; i++) {
				c.pvpHelms[i] = b.getInt();
			}
			for (int i = 0; i < c.pvpChests.length; i++) {
				c.pvpChests[i] = b.getInt();
			}
			for (int i = 0; i < c.pvpLegs.length; i++) {
				c.pvpLegs[i] = b.getInt();
			}
			for (int i = 0; i < c.pvpWeapons.length; i++) {
				c.pvpWeapons[i] = b.getInt();
			}
		} catch (FileNotFoundException fnfe){
			loadDefaults(c);
		} catch (IOException ioe) {loadDefaults(c);ioe.printStackTrace();}
	}
	
	public static void saveItemStats(Client c) {
		try {
			int def = ArmourDegradation.DEFAULT;
			boolean saving = false;
			for (int i = 0; i < c.pvpChests.length; i++) {
				if (c.pvpChests[i] != def || c.pvpHelms[i] != def || c.pvpLegs[i] != def || c.pvpWeapons[i] != def) {
					saving = true;
					break;
				}
			}
			if (!saving)
				return;
			File f = new File("./deps/Data/characters/items/" + c.playerName);
			FileChannel fc = new FileOutputStream(f).getChannel();
			ByteBuffer b = ByteBuffer.allocate(1000);
			for (int i = 0; i < c.pvpHelms.length; i++) {
				b.putInt(c.pvpHelms[i]);
			}
			for (int i = 0; i < c.pvpChests.length; i++) {
				b.putInt(c.pvpChests[i]);
			}
			for (int i = 0; i < c.pvpLegs.length; i++) {
				b.putInt(c.pvpLegs[i]);
			}
			for (int i = 0; i < c.pvpWeapons.length; i++) {
				b.putInt(c.pvpWeapons[i]);
			}
			b.flip();
			fc.write(b);
			fc.close();
		} catch (FileNotFoundException fnfe){
		} catch (IOException ioe) {loadDefaults(c);}
	}
	
	public static void loadDefaults(Client c) {
		int DEFAULT = ArmourDegradation.DEFAULT;
		c.pvpChests = new int[]{DEFAULT,DEFAULT,DEFAULT,DEFAULT};
		c.pvpHelms = new int[]{DEFAULT,DEFAULT,DEFAULT,DEFAULT};
		c.pvpLegs = new int[]{DEFAULT,DEFAULT,DEFAULT,DEFAULT};
		c.pvpWeapons = new int[]{DEFAULT,DEFAULT,DEFAULT,DEFAULT};
	}*/
	
	
}
