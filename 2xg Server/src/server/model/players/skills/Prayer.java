package server.model.players.skills;


import server.Config;
import server.model.players.Client;
public class Prayer {

	Client c;
	public int[][] bonesExp = {{526,5},{532,15},{534,30},{536,72},{6729,125},{18830,600}, {6812, 800}};	
	
	public Prayer(Client c) {
		this.c = c;
	}
	
	public void buryBone(int id, int slot) {
		if(System.currentTimeMillis() - c.buryDelay > 1500) {
			c.getItems().deleteItem(id, slot, 1);
			c.sendMessage("You bury the bones.");
			c.getPA().addSkillXP(getExp(id)*Config.PRAYER_EXPERIENCE,5);
			c.buryDelay = System.currentTimeMillis();
			c.startAnimation(827);
		}	
	}
	
	public void bonesOnAltar(int id) {
		c.getItems().deleteItem(id, c.getItems().getItemSlot(id), 1);
		c.sendMessage("The gods are pleased with your offering.");
		c.getPA().addSkillXP(getExp(id)*2*Config.PRAYER_EXPERIENCE, 5);
	}
	
	public void bonesOnHouseAltar(int id) {
		if(c.COLectt > 3) {
			c.getItems().deleteItem(id, c.getItems().getItemSlot(id), 1);
			c.sendMessage("The gods are pleased with your offering.");
			c.sendMessage("You receive more XP from you're lecterns.");
			c.getPA().addSkillXP(getExp(id)*2*Config.PRAYER_EXPERIENCEINHOUSE, 5);
		} else {
			c.sendMessage("You need to have Lecterns built to do this.");
		}
	}
	
	public boolean isBone(int id) {
		for (int j = 0; j < bonesExp.length; j++)
			if (bonesExp[j][0] == id)
				return true;
		return false;
	}
	
	public int getExp(int id) {
		for (int j = 0; j < bonesExp.length; j++) {
			if (bonesExp[j][0] == id)
				return bonesExp[j][1];
		}
		return 0;
	}
}