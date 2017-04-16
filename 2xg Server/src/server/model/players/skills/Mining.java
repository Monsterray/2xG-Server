package server.model.players.skills;

import server.Server;
import server.model.players.Client;
import server.task.Task;

/**
* Class Mining
**/

public class Mining extends SkillHandler {

	public static void mineEss(final Client c, final int object) {
		if(!noInventorySpace(c, "mining")) {
			resetMining(c);
			return;
		}
		if(!hasPickaxe(c)) {
			c.sendMessage("You need a Mining pickaxe which you need a Mining level to use.");
			return;
		}
		if(c.playerSkilling[14]) {
			return;
		}

		c.playerSkilling[14] = true;
		c.stopPlayerSkill = true;
		c.startAnimation(getAnimation(c));
       Server.getTaskScheduler().addEvent(new Task(2, false) {
		public void execute() {
				c.getItems().addItem(1436, 1);
				c.sendMessage("You manage to mine some "+ c.getItems().getItemName(1436).toLowerCase()+".");
				c.getPA().addSkillXP(5 * MINING_XP, c.playerMining);
				c.startAnimation(getAnimation(c));
				if(!hasPickaxe(c)) {
					c.sendMessage("You need a pickaxe to mine this rock.");
					resetMining(c);
					this.stop();
				}
				if(!c.stopPlayerSkill) {
					resetMining(c);
					this.stop();
				}
				if(!noInventorySpace(c, "mining")) {
					resetMining(c);
					this.stop();
				}
			}

		});
	}

	public static void attemptData(final Client c, final int object, final int obX, final int obY) {
	c.turnPlayerTo(c.objectX, c.objectY);
		if(!noInventorySpace(c, "mining")) {
			resetMining(c);
			return;
		}
		if(!hasRequiredLevel(c, 14, getLevelReq(c, object), "mining", "mine here")) {
			return;
		}
		if(!hasPickaxe(c)) {
			c.sendMessage("You need a pickaxe to mine this rock.");
			return;
		}
		c.sendMessage("You swing your pick at the rock.");
		if(c.playerSkilling[14]) {
			return;
		}
		c.playerSkilling[14] = true;
		c.stopPlayerSkill = true;
		c.startAnimation(getAnimation(c));
		for(int i = 0; i < data.length; i++) {
			if(object == data[i][0]) {
				c.playerSkillProp[14][0] = data[i][1];
				c.playerSkillProp[14][1] = data[i][3];
				c.startAnimation(getAnimation(c));
				Server.getTaskScheduler().addEvent(new Task(getTimer(c, object), false) {
					public void execute() {
						if(c.playerSkillProp[14][0] > 0) {
							c.getItems().addItem(c.playerSkillProp[14][0], 1);
							c.sendMessage("You manage to mine some "+ c.getItems().getItemName(c.playerSkillProp[14][0]).toLowerCase()+".");
						}
						if(c.playerSkillProp[14][1] > 0) {
							c.getPA().addSkillXP(c.playerSkillProp[14][1] * MINING_XP, c.playerMining);
							Server.objectHandler.createAnObject(c, 451, obX, obY);
						}
						if(!hasPickaxe(c)) {
							c.sendMessage("You need a pickaxe to mine this rock.");
							resetMining(c);
							this.stop();
						}
						if(!c.stopPlayerSkill) {
							resetMining(c);
							this.stop();
						}
						if(!noInventorySpace(c, "mining")) {
							resetMining(c);
							this.stop();
						}

						resetMining(c);
						this.stop();
					}
					
				});
				Server.getTaskScheduler().addEvent(new Task(getTimer(c, object) + getRespawnTime(c, object), false) {
					public void execute() {
						Server.objectHandler.createAnObject(c, object, obX, obY);
						this.stop();
					}
					
				});
				Server.getTaskScheduler().addEvent(new Task(15, false) {
					public void execute() {
						if(c.playerSkilling[14]) {
							c.startAnimation(getAnimation(c));
						}
						if(!c.stopPlayerSkill || !c.playerSkilling[14]) {
							resetMining(c);
							this.stop();
						}
					}
				});
			}
		}
	}

	private static int getTimer(Client c, int i) {
		return (getMineTime(c, i) + getTime(c) + playerMiningLevel(c));
	}

	private static int getMineTime(Client c, int object) {
		for(int i = 0; i < data.length; i++) {
			if(object == data[i][0]) {
				return data[i][4];
			}
		}
		return -1;
	}

	private static int playerMiningLevel(Client c) {
		return (10 - (int)Math.floor(c.playerLevel[14] / 10));
	}

	private static int getTime(Client c) {
		for(int i = 0; i < pickaxe.length; i++) {
			if(c.getItems().playerHasItem(pickaxe[i][0]) || c.playerEquipment[3] == pickaxe[i][0]) {
				if(c.playerLevel[c.playerMining] >= pickaxe[i][1]) {
					return pickaxe[i][2];
				}
			}
		}
		return 10;
	}

	public static void resetMining(Client c) {
		c.playerSkilling[14] = false;
		c.stopPlayerSkill = false;
		for(int i = 0; i < 2; i++) {
			c.playerSkillProp[14][i] = -1;
		}
		c.startAnimation(65535);
	}

	public static boolean miningRocks(Client c, int object) {
		for(int i = 0; i < data.length; i++) {
			if(object == data[i][0]) {
				return true;
			}
		}
		return false;
	}

	private static int getRespawnTime(Client c, int object) {
		for(int i = 0; i < data.length; i++) {
			if(object == data[i][0]) {
				return data[i][5];
			}
		}
		return -1;
	}

	private static int getLevelReq(Client c, int object) {
		for(int i = 0; i < data.length; i++) {
			if(object == data[i][0]) {
				return data[i][2];
			}
		}
		return -1;
	}

	private static boolean hasPickaxe(Client c) {
		for(int i = 0; i < animation.length; i++) {
			if(c.getItems().playerHasItem(animation[i][0]) || c.playerEquipment[3] == animation[i][0]) {
				return true;
			}
		}
		return false;
	}

	private static int getAnimation(Client c) {
		for(int i = 0; i < animation.length; i++) {
			if(c.getItems().playerHasItem(animation[i][0]) || c.playerEquipment[3] == animation[i][0]) {
				return animation[i][1];
			}
		}
		return -1;
	}

	private static int[][] animation = {
		{1275, 6746}, {1271, 6750}, {1273, 6751}, {1269, 6749},
		{1267, 6748}, {1265, 6747},
	};

	private static int[][] pickaxe = {
		{1275, 41, 0}, 		//RUNE
		{1271, 31, 1},		//ADDY
		{1273, 21, 2},		//MITH
		{1269, 6, 3},		//STEEL
		{1267, 1, 3},		//IRON
		{1265, 1, 4},		//BRONZE
	};

	private static int[][] data = {
		{2091, 436, 1, 18, 1, 5},	//COPPER
		{2090, 436, 1, 18, 1, 5},	//COPPER
		{11936, 436, 1, 18, 1, 5},	//COPPER 508
		{2094, 438, 1, 18, 1, 5},	//TIN
		{2095, 438, 1, 18, 1, 5},	//TIN
		{11933, 438, 1, 18, 1, 5},	//TIN 508
		{2093, 440, 15, 35, 2, 5},	//IRON
		{2092, 440, 15, 35, 2, 5},	//IRON
		{2092, 440, 15, 35, 2, 5},	//IRON 508
		{2097, 453, 30, 50, 3, 8},	//COAL
		{2096, 453, 30, 50, 3, 8},	//COAL
		{11930, 453, 30, 50, 3, 8},	//COAL 508
		{2098, 444, 40, 65, 3, 10},	//GOLD
		{2099, 444, 40, 65, 3, 10},	//GOLD
		{2103, 447, 55, 80, 5, 20},	//MITH
		{2102, 447, 55, 80, 5, 20},	//MITH
		{11942, 447, 55, 80, 5, 20},	//MITH 508
		{2104, 449, 70, 95, 7, 50},	//ADDY
		{2105, 449, 70, 95, 7, 50},	//ADDY
		{11941, 449, 70, 95, 7, 50},	//ADDY 508
		{2100, 442, 20, 40, 5, 5},	//SILVER
		{2101, 442, 20, 40, 5, 5},	//SILVER
		{14859, 451, 85, 125, 40, 100},//RUNE
		{14860, 451, 85, 125, 40, 100},//RUNE
	};

	public static void prospectRock(final Client c, final String itemName) {
		c.sendMessage("You examine the rock for ores...");
       Server.getTaskScheduler().addEvent(new Task(4, false) {
		public void execute() {
				c.sendMessage("This rock contains "+itemName+".");
				this.stop();
			}

		});
	}
	public static void prospectNothing(final Client c) {
		c.sendMessage("You examine the rock for ores...");
       Server.getTaskScheduler().addEvent(new Task(2, false) {
		public void execute() {
				c.sendMessage("There is no ore left in this rock.");
				this.stop();
			}

		});
	}
}
/*

package server.model.players.skills;

import server.model.players.*;
import server.Config;
import server.util.Misc;

/**
* @author Monsterray : REDID BY GABBE
*

public class Mining {
	
	Client c;
	
	private final int VALID_PICK[] = {1265,1267,1269,1273,1271,1275,13661};
	private final int[] PICK_REQS = {1,1,6,6,21,31,41,61,80};
	private final int[] RANDOM_GEMS = {1623,1621,1619,1617,1631};
	private int 
		oreType,
		exp,
		levelReq,
		pickType,
		mineanim = 1;
	
	public Mining(Client c) {
		this.c = c;
	}
	
	
	public void startMining(int oreType, int levelReq, int exp) {
			if (c.getItems().freeSlots() < 1) {
			c.sendMessage("You do not have enough inventory slots to do that.");
			return;
		}
		c.turnPlayerTo(c.objectX, c.objectY);
		if (goodPick() > 0) {
			if (c.playerLevel[c.playerMining] >= levelReq) {
				for (int id : VALID_PICK) {
					if(id == c.playerEquipment[c.playerWeapon] && canminewithpick(c.playerEquipment[c.playerWeapon], c) || c.getItems().playerHasItem(id, 1) && canminewithpick(id, c)) {
						if (id == 1265) {
							mineanim = 625;
						}
						if (id == 1267) {
							mineanim = 626;
						}
						if (id == 1269) {
							mineanim = 627;
						}
						if (id == 1271) {
							mineanim = 628;
						}
						if (id == 1273) {
							mineanim = 629;
						}
						if (id == 1275) {
							mineanim = 624;
						}
					}
				}
				this.oreType = oreType;
				this.exp = exp;
				this.levelReq = levelReq;
				this.pickType = goodPick();
				c.sendMessage("You swing your pick at the rock.");
				c.miningTimer = getMiningTimer(oreType);
				c.startAnimation(mineanim);
			} else {
				resetMining();
				c.sendMessage("You need a mining level of " + levelReq + " to mine this rock.");
				mineanim = 1;
			}		
		} else {
			resetMining();
			c.sendMessage("You need a pickaxe to mine this rock.");
			mineanim = 1;
			c.getPA().resetVariables();
		}
	}
	
		public static void resetMining(Client c) {
		c.playerSkilling[14] = false;
		c.stopPlayerSkill = false;
		for(int i = 0; i < 2; i++) {
			c.playerSkillProp[14][i] = -1;
		}
		c.startAnimation(65535);
	}
	
		public static void mineEss(final Client c, final int object) {
		if(!noInventorySpace(c, "mining")) {
			resetMining(c);
			return;
		}
				for (int id : VALID_PICK) {
					if(id == c.playerEquipment[c.playerWeapon] && canminewithpick(c.playerEquipment[c.playerWeapon], c) || c.getItems().playerHasItem(id, 1) && canminewithpick(id, c)) {
					c.sendMessage("You don't have a Pickaxe compatible with you're level!");
					}
					}
		if(c.playerSkilling[14]) {
			return;
		}

		c.playerSkilling[14] = true;
		c.stopPlayerSkill = true;
		c.startAnimation(getAnimation(c));
		CycleEventHandler.getSingleton().addEvent(c, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				c.getItems().addItem(1436, 1);
				c.sendMessage("You manage to mine some "+ c.getItems().getItemName(1436).toLowerCase()+".");
				c.getPA().addSkillXP(5 * MINING_XP, c.playerMining);
				c.startAnimation(getAnimation(c));
				if(!hasPickaxe(c)) {
					c.sendMessage("You need a pickaxe to mine this rock.");
					resetMining(c);
					container.stop();
				}
				if(!c.stopPlayerSkill) {
					resetMining(c);
					container.stop();
				}
				if(!noInventorySpace(c, "mining")) {
					resetMining(c);
					container.stop();
				}
			}
			@Override
			public void stop() {

			}
		}, 2);
	}
	
		public static void prospectRock(final Client c, final String itemName) {
		c.sendMessage("You examine the rock for ores...");
		EventManager.getSingleton().addEvent(new Event() {

			@Override
			public void execute(EventContainer container) {
				c.sendMessage("This rock contains "+itemName+".");
				container.stop();
			}

		}, 2500);
	}
	public static void prospectNothing(final Client c) {
		c.sendMessage("You examine the rock for ores...");
		EventManager.getSingleton().addEvent(new Event() {

			@Override
			public void execute(EventContainer container) {
				c.sendMessage("There is no ore left in this rock.");
				container.stop();
			}

		}, 1000);
	}

	
	public void mineOre() {
		if (c.getItems().addItem(oreType,1)) {
			c.startAnimation(mineanim);
			//c.sendMessage("You manage to mine some ore.");
			c.getPA().addSkillXP(exp * Config.MINING_EXPERIENCE, c.playerMining);
			c.getPA().refreshSkill(c.playerMining);
			c.miningTimer = getMiningTimer(oreType);
			if (Misc.random(25) == 10) {
				c.getItems().addItem(RANDOM_GEMS[(int)(RANDOM_GEMS.length * Math.random())], 1);
				c.sendMessage("You find a gem!");
			}
		} else {
			c.getPA().resetVariables();
			mineanim = 1;
		}
	}
	
	public void resetMining() {
		this.oreType = -1;
		this.exp = -1;
		this.levelReq = -1;
		this.pickType = -1;
	}
	
	public int goodPick() {
		for (int j = VALID_PICK.length - 1; j >= 0; j--) {
			if (c.playerEquipment[c.playerWeapon] == VALID_PICK[j]) {
				if (c.playerLevel[c.playerMining] >= PICK_REQS[j])
					return VALID_PICK[j];
			}		
		}
		for (int i = 0; i < c.playerItems.length; i++) {
			for (int j = VALID_PICK.length - 1; j >= 0; j--) {
				if (c.playerItems[i] == VALID_PICK[j] + 1) {
					if (c.playerLevel[c.playerMining] >= PICK_REQS[j])
						return VALID_PICK[j];
				}
			}		
		}
		return - 1;
	}

	private boolean canminewithpick(int i, Client c) {
		switch (i) {
		case 1265:
		case 1267:
			if (c.playerLevel[14] >= 1)
				return true;
			break;
		case 1269:
			if (c.playerLevel[14] >= 6)
				return true;
			break;
		case 1273:
			if (c.playerLevel[14] >= 21)
				return true;
			break;
		case 1271:
			if (c.playerLevel[14] >= 31)
				return true;
			break;
		case 1275:
			if (c.playerLevel[14] >= 41)
				return true;
		break;
		default:
			return false;
			
		}
		return false;
	}
	
	public int getMiningTimer(int ore) {
		int time = Misc.random(5);
		if (ore == 451) {
			time += 4;
		}
		return time;
	}
	
}*/