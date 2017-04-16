package server.model.players.skills;

import server.Config;
import server.model.players.Client;

/**
* Herblore.java
* @author 

**/

public class Herblore {
		
		
	private Client c;
	
	private static int vial = 227;
	
	private static int[][] herbClean = { {199,249,1,3},{201,251,5,4},{203,253,11,5},{205,255,20,6},{207,257,25,8},
									{3049,2998,30,8},{209,259,40,9},{211,261,48,10},{213,263,54,11},{3051,3000,59,12},
									{215,265,65,13},{2485,2481,67,13},{217,267,70,14},{219,269,75,15} };
						
	private static int[][] makePotion = { {249,91,121,1,25,221}, {251,93,175,5,38,235}, {253,95,115,12,50,225}, {255,97,127,22,63,223}, 
								   {257,99,139,38,88,231}, {259,101,145,45,100,221}, {261,103,2448,40,100,235}, 
								   {263,105,157,55,125,225}, {265,107,163,66,155,239},  {267,109,169,72,163,245}, 
								   {269,111,189,78,175,247}, {2481,2483,2454,69,158,4621}, {2998,3002,6687,81,180,6693}, 
								   {3000,3004,3026,63,143,223} };
										/**{cleanHerbId 0,unfPotion 1,3dosePot 2,levelreq 3,exp 4,ingredient 5}**/
										
	
	public Herblore(Client c) {
		this.c = c;	
	}
	private int getSlot(int calledItem, int slot) {
		for (int j = 0; j < makePotion.length; j++)
			if (makePotion[j][slot] == calledItem)
				return j;
		return -1;	
	}
	
	public boolean checkItem(int item, int slot) {
		for (int j = 0; j < makePotion.length; j++)
			if (makePotion[j][slot] == item)
				return true;
		return false;
	}
	
	public boolean checkGrimy(int item, int slot) {
		for (int j = 0; j < herbClean.length; j++)
			if (herbClean[j][slot] == item)
				return true;
		return false;
	}
	public void handlePotMaking(int itemUsed, int usedWith) {
	int slot;
		if (itemUsed == vial && checkItem(usedWith, 0)) {
			makeUnfPot(usedWith);
		} else if (usedWith == vial && checkItem(itemUsed, 0)) {
			makeUnfPot(itemUsed);	
							// unf pot                  ingredient
		} else if (checkItem(itemUsed, 1) && checkItem(usedWith, 5)) {
			slot = getSlot(itemUsed, 1);
			finishPot(itemUsed, usedWith, slot);
							// ingredient				unf pot
		} else if (checkItem(itemUsed, 5) && checkItem(usedWith, 1)) {
			slot = getSlot(usedWith, 1);
			finishPot(itemUsed, usedWith, slot);
		}
	}
	
	public void finishPot(int itemUsed, int usedWith, int slot) {
		if (c.playerLevel[c.playerHerblore] >= makePotion[slot][3]) {
			if (c.getItems().playerHasItem(itemUsed) && c.getItems().playerHasItem(usedWith)) {
				c.getItems().deleteItem(itemUsed,c.getItems().getItemSlot(itemUsed),1);
				c.getItems().deleteItem(usedWith,c.getItems().getItemSlot(usedWith),1);
				c.getItems().addItem(makePotion[slot][2],1);
				c.sendMessage("You make a " + c.getItems().getItemName(makePotion[slot][2]) + ".");
				c.getPA().addSkillXP(makePotion[slot][4] * Config.HERBLORE_EXPERIENCE,c.playerHerblore);
				c.startAnimation(363);
			} else {
				c.sendMessage("You do not have all the items for this potion!");
			}
		} else {
			c.sendMessage("You need a herblore level of " + makePotion[slot][3] + " to make this potion.");
		}
	}
	
	public void makeUnfPot(int herbId) {
		if (c.getItems().playerHasItem(vial) && c.getItems().playerHasItem(herbId)) {
			int slot = getSlot(herbId, 0);
			if (c.playerLevel[c.playerHerblore] >= makePotion[slot][3]) {
				c.getItems().deleteItem(herbId,c.getItems().getItemSlot(herbId),1);
				c.getItems().deleteItem(vial,c.getItems().getItemSlot(vial),1);
				c.getItems().addItem(makePotion[slot][1],1);
				c.sendMessage("You make a " + c.getItems().getItemName(makePotion[slot][1]) + ".");
				c.startAnimation(363);
				//c.getPA().addSkillXP(makePotion[slot][4] * Config.HERBLORE_EXPERIENCE,c.playerHerblore);
			} else {
					c.sendMessage("You need a herblore level of " + makePotion[slot][3] + " to make this potion.");
			}				
		} else{
			c.sendMessage("need all items");
		}
	}
	
	private void idHerb(int slot) {
		if (c.getItems().playerHasItem(herbClean[slot][0])) {
			if (c.playerLevel[c.playerHerblore] >= herbClean[slot][2]) {
					c.getItems().deleteItem(herbClean[slot][0],c.getItems().getItemSlot(herbClean[slot][0]),1);
					c.getItems().addItem(herbClean[slot][1],1);
					c.getPA().addSkillXP(herbClean[slot][3] * Config.HERBLORE_EXPERIENCE,c.playerHerblore);
					//c.sendMessage("You identify the herb as a " + c.getItems().getItemName(herbClean[slot][1]) + "."); // If you want to show the name
					c.sendMessage("You clean the dirt off the herb.");
			} else {
				c.sendMessage("You need a herblore level of " + herbClean[slot][2] + " to clean this herb.");
			}		
		}
	}
	
	
	
	public void handleHerbClick(int herbId) {
		for (int j = 0; j < herbClean.length; j++){
			if (herbClean[j][0] == herbId)
				idHerb(j);
		}	
	}
	
}