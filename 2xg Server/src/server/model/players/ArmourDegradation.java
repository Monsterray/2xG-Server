package server.model.players;

public class ArmourDegradation {
	
	public static final int DEFAULT = 500;
	
	private static final int[] PVP_HELMS = {13896,13876,13864,15000/*novesta thing*/};
	private static final int[] PVP_CHESTS = {13884,13870,13858,13887};
	private static final int[] PVP_LEGS = {13890,13873,13861,13893};
	private static final int[] PVP_WEAPONS = {13902,13905,13867,13899,13900};
	
	
	public static void sendDegradeValue(Client c, int item) {
		for (int i = 0; i < PVP_HELMS.length; i++) {
			if (c.playerEquipment[c.playerHat] == PVP_HELMS[i]) {
				c.sendMessage("There are: " + c.pvpHelms[i] + " charges left on this item.");
				return;			
			}
		}
		for (int i = 0; i < PVP_CHESTS.length; i++) {
			if (c.playerEquipment[c.playerChest] == PVP_CHESTS[i]) {
				c.sendMessage("There are: " + c.pvpChests[i] + " charges left on this item.");
				return;
			}
		}
		for (int i = 0; i < PVP_LEGS.length; i++) {
			if (c.playerEquipment[c.playerLegs] == PVP_LEGS[i]) {
				c.sendMessage("There are: " + c.pvpLegs[i] + " charges left on this item.");
				return;
			}
		}
		for (int i = 0; i < PVP_WEAPONS.length; i++) {
			if (c.playerEquipment[c.playerWeapon] == PVP_WEAPONS[i]) {
				c.sendMessage("There are: " + c.pvpWeapons[i] + " charges left on this item.");
				return;
			}
		}
	}
	
	public static void degradeArmour(Client c) {
		for (int i = 0; i < PVP_HELMS.length; i++) {
			if (c.playerEquipment[c.playerHat] == PVP_HELMS[i]) {
				if (--c.pvpHelms[i] == 0) {
					c.pvpHelms[i] = DEFAULT;
					c.getItems().deleteEquipment(PVP_HELMS[i], c.playerHat);
					c.sendMessage("Your helmet crumbles to dust.");
				}				
			}
		}
		for (int i = 0; i < PVP_CHESTS.length; i++) {
			if (c.playerEquipment[c.playerChest] == PVP_CHESTS[i]) {
				if (--c.pvpChests[i] == 0) {
					c.pvpChests[i] = DEFAULT;
					c.getItems().deleteEquipment(PVP_CHESTS[i], c.playerChest);
					c.sendMessage("Your chest armour crumbles to dust.");
				}
			}
		}
		for (int i = 0; i < PVP_LEGS.length; i++) {
			if (c.playerEquipment[c.playerLegs] == PVP_LEGS[i]) {
				if (--c.pvpLegs[i] == 0) {
					c.pvpLegs[i] = DEFAULT;
					c.getItems().deleteEquipment(PVP_LEGS[i], c.playerLegs);
					c.sendMessage("Your leggings crumble to dust.");
				}
			}
		}
	}
	
	public static void degradeWeapon(Client c) {
		for (int i = 0; i < PVP_WEAPONS.length; i++) {
			if (c.playerEquipment[c.playerWeapon] == PVP_WEAPONS[i]) {
				if (--c.pvpWeapons[i] == 0) {
					c.pvpWeapons[i] = DEFAULT;
					c.getItems().deleteEquipment(PVP_WEAPONS[i], c.playerWeapon);
					c.sendMessage("Your weapon crumbles to dust.");
				}
			}
		}
	}
	
	
	
}
