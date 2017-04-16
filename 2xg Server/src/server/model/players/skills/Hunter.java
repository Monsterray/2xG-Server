package server.model.players.skills;

import server.model.players.Client;
import server.util.Misc;



public class Hunter {

	Client c;
	
	
	public Hunter(Client c) {
		this.c = c;
	}


	
public void catchImpling(String npcName, int Net, int npcId, int itemId, int AmtExp, int Req, int playerId) {
//npcName = Server.npcHandler.getNpcListName(npcId);
	if (System.currentTimeMillis() - c.foodDelay >= 1500) { 
		if (c.playerLevel[21] >= Req) { 
			if (HasNet()) { 
				if (c.getItems().playerHasItem(11260,1)){
					HandleCatch(itemId, AmtExp, Req, "Impling");
				}else{ 
					c.sendMessage("you need a impling jar to catch this");
					return;			
				}	
			} else { 
				c.sendMessage("You need to equip a butterfly net!"); 
				return;
			}	
		} else {
			c.sendMessage("You need atleast "+ Req +" Hunter To catch that Impling!");
			return;
		}
			c.foodDelay = System.currentTimeMillis();
	}
}

public void catchButterFly(String npcName, int Net, int npcId, int itemId, int AmtExp, int Req, int playerId) {
//npcName = Server.npcHandler.getNpcListName(npcId);
	if (System.currentTimeMillis() - c.foodDelay > 1500) { //anti spamm
		if (c.playerLevel[21] >= Req) { //first we check if he's high enough to catch
			if (HasNet()) { 
				HandleCatch(itemId, AmtExp, Req, "Butterfly");
			} else {
				c.sendMessage("You need to be wearing a butterfly net!"); 
				return;
				}	
		} else {
			c.sendMessage("You need at least "+ Req +" Hunter To catch that Butterfly!");
			return;
		}
			c.foodDelay = System.currentTimeMillis();
	}
}


public void HandleCatch(int ID, int XP,int Req, String Name) {
	if (c.playerLevel[21] + Misc.random(10) > Misc.random(20) + Req) {
		c.sendMessage("You Cought a"+ Name +"!"); 
		c.getItems().addItem(ID, 1); 
		c.startAnimation(6999);
		c.getPA().addSkillXP(XP, 21); 
	} else {
		c.sendMessage("You Failed To Catch The "+Name+".");
		c.startAnimation(6999);
	}
}

public boolean HasNet(){
	if (c.playerEquipment[c.playerWeapon] == 10010 || c.playerEquipment[c.playerWeapon] == 11259){
		return true;
}else{ 
return false;
}
}

public void RefreshRabbits() {
	c.getPA().object(19337, 2257, 3336, 0, 10);
	c.getPA().object(19337, 2261, 3334, 1, 10);
	}
	
public void giveRabbit() {
if (Misc.random(10) < 3){
	if (Misc.random(25) == 1){
		c.getItems().addItem(10132, 1);
	}else{
		c.getItems().addItem(3226, 1);
	}
}else{
	c.sendMessage("you fail to catch the rabbit.");
}
}



}