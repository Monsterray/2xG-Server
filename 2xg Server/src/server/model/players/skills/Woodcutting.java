package server.model.players.skills;

import server.Config;
import server.Server;
import server.model.players.Client;
import server.model.players.PlayerHandler;
import server.task.Task;
import server.util.Misc;
 
public class Woodcutting extends SkillHandler {

	public static void attemptData(final Client c, final int tree, final int obX, final int obY) {
		c.playerIsWoodcutting = false;
		if (!noInventorySpace(c, "woodcutting")){
			resetWoodcutting(c);
			return;
		}
		if (hasAxe(c) && !canUseAxe(c)){
		c.playerIsWoodcutting = false;
			c.sendMessage("Your Woodcutting level is too low to use this axe.");
			return;
		}
		if (!hasAxe(c)) {
		c.playerIsWoodcutting = false;
			c.sendMessage("You need a Woodcutting axe to chop this.");
			return;
		}
		if(c.playerIsWoodcutting) {
		c.playerIsWoodcutting = false;
			return;
		}		
		c.sendMessage("You swing your axe at the tree.");
		c.playerIsWoodcutting = true;
		c.stopPlayerSkill = true;
		c.playerSkillProp[8][1] = getRespawnTime(c, tree); //RESPAWN TIME
		c.playerSkillProp[8][2] = getRequiredLevel(c, tree); //LEVELREQ
		c.playerSkillProp[8][3] = recieveXP(c, tree); //XP
		c.playerSkillProp[8][4] = getAnimId(c); //ANIM
		c.playerSkillProp[8][5] = getRespawnTime(c, tree); //RESPAWN TIME
		c.playerSkillProp[8][6] = getTreeLog(c, tree); //LOG
		c.woodcuttingTree = obX + obY;
		c.doAmount = 1 + Misc.random(25);
		if(normalTree(c, tree)) {
			c.doAmount = 1;
		}
		if(!hasRequiredLevel(c, 8, c.playerSkillProp[8][2], "woodcutting", "cut this tree")) {
			resetWoodcutting(c);
			return;
		}
		c.startAnimation(c.playerSkillProp[8][4]);
		for(int i = 0; i < trees.length; i++) {
			for(int l = 0; l < 6; l++) 
				if(tree == trees[l][i]) {
					Server.getTaskScheduler().addEvent(new Task(getTimer(c), false) {
						public void execute() {
							if(c.playerSkillProp[8][6] > 0) {
								c.getItems().addItem(c.playerSkillProp[8][6], 1);
							}
							if(c.playerSkillProp[8][3] > 0) {
								c.getPA().addSkillXP(c.playerSkillProp[8][3] * WOODCUTTING_XP, 8);
							}
							if(c.playerSkillProp[8][6] > 0) {
								c.sendMessage("You get some logs.");
							}
							if(Misc.random(100) == 0) {
								recieveBirdsNest(c);
							}
							deleteTime(c);
							if (!hasAxe(c)) {
								c.sendMessage("You need a Woodcutting axe which you need a Woodcutting level to use.");
								resetWoodcutting(c);
								this.stop();
							}
							if (!noInventorySpace(c, "woodcutting")){
								resetWoodcutting(c);
								this.stop();
							}
							if(!c.stopPlayerSkill) {
								resetWoodcutting(c);
								this.stop();
							}
							if(!c.playerIsWoodcutting) {
								this.stop();
							}
							if(c.doAmount <= 0 && c.playerIsWoodcutting) {
								createStump(c, tree, obX, obY);
								resetWoodcutting(c);
								this.stop();
							}
						}
					});
					Server.getTaskScheduler().addEvent(new Task(4, false) {
						public void execute() {
							if(c.playerSkillProp[8][4] > 0) {
								c.startAnimation(c.playerSkillProp[8][4]);
							} else {
								resetWoodcutting(c);
							}
							if(!c.stopPlayerSkill) {
								this.stop();
							}
						}
						
					});
				}
		}
	}

	public static boolean playerTrees(Client c, int tree) {
		boolean trees2 = false;
		for(int i = 0; i < trees.length; i++){
			for(int i1 = 0; i1 < 6; i1++) {
				if(tree == trees[i1][i]){
					trees2 = true;
				}
			}
		}
		return trees2;
	}

	private static void treeLocated(Client c, int obX, int obY) {
		for(int i = 0; i < Config.MAX_PLAYERS; i++) {
			if(PlayerHandler.players[i] != null) {
				Client person = (Client)PlayerHandler.players[i];
				if(person != null) {
					Client p = (Client)person;
					if(p.distanceToPoint(c.absX, c.absY) <= 10){
						if(c.woodcuttingTree == p.woodcuttingTree) {
							p.woodcuttingTree = -1;
							Woodcutting.resetWoodcutting(p);
						}
					}
				}
			}
		}
	}

	private static int getTimer(Client c) {
		return (Misc.random(5) + getAxeTime(c) + playerWoodcuttingLevel(c));
	}

	private static void recieveBirdsNest(Client c) {
		Server.itemHandler.createGroundItem(c, 5070 + Misc.random(4), c.getX(), c.getY(), 1, c.getId());
		c.sendMessage("A bird's nest falls out of the tree!");
	}

	private static void createStump(final Client c, final int i, final int obX, final int obY) {
		Server.objectHandler.createAnObject(c, 1341, obX, obY);
		treeLocated(c, obX, obY);
		       Server.getTaskScheduler().addEvent(new Task(getRespawnTime(c,i), false) {
		public void execute() {
				Server.objectHandler.createAnObject(c, i, obX, obY);
				this.stop();
			}
			
		});
	}

	public static void resetWoodcutting(Client c) {
		c.startAnimation(65535);
		c.getPA().removeAllWindows();
		c.playerIsWoodcutting = false;
		c.doAmount = 0;
		for(int i = 0; i < 9; i++) {
			c.playerSkillProp[8][i] = -1;
		}
	}

	private static boolean hasAxe(Client c) {
		boolean has = false;
		for(int i = 0; i < axes.length; i++) {
			if(c.getItems().playerHasItem(axes[i][0]) || c.playerEquipment[3] == axes[i][0]) {
				has = true;
			}
		}
		return has;
	}

	private static int playerWoodcuttingLevel(Client c) {
		return (10 - (int)Math.floor(c.playerLevel[8] / 10));
	}

	private static int getTreeLog(Client c, int l) {
		c.playerSkillProp[8][6] = -1;
		for(int i = 0; i < trees.length; i++) {
 			if(l== trees[5][i]) {
				c.playerSkillProp[8][6] = 1513;
			} else if(l == trees[4][i]) {
				c.playerSkillProp[8][6] = 1515;
			} else if(l == trees[3][i]) {
				c.playerSkillProp[8][6] = 1517;
			} else if(l == trees[2][i]) {
				c.playerSkillProp[8][6] = 1519;
			} else if(l == trees[1][i]) {
				c.playerSkillProp[8][6] = 1521;
			} else if(l == trees[0][i]) {
				c.playerSkillProp[8][6] = 1511;
			}
		}
		return c.playerSkillProp[8][6];
	}

	private static int getAxeTime(Client c) {
		int axe = -1;
		for(int i = 0; i < axes.length; i++) {
			if(c.playerLevel[8] >= axes[i][1]) {
				if(c.getItems().playerHasItem(axes[i][0]) || c.playerEquipment[3] == axes[i][0]) {
					axe = axes[i][3];
				}
			}
		}
		return axe;
	}

	private static int getAnimId(Client c) {
		int anim = -1;
		for(int i = 0; i < axes.length; i++) {
			if(c.playerLevel[8] >= axes[i][1]) {
				if(c.getItems().playerHasItem(axes[i][0]) || c.playerEquipment[3] == axes[i][0]) {
					anim = axes[i][2];
				}
			}
		}
		return anim;
	}

	private static int recieveXP(Client c, int treep) {
		c.playerSkillProp[8][3] = -1;
		for(int i = 0; i < trees.length; i++) {
			if(treep == trees[5][i]) {
				c.playerSkillProp[8][3] = 250;
			} else if(treep == trees[4][i]) {
				c.playerSkillProp[8][3] = 175;
			} else if(treep == trees[3][i]) {
				c.playerSkillProp[8][3] = 100;
			} else if(treep == trees[2][i]) {
				c.playerSkillProp[8][3] = 68;
			} else if(treep == trees[1][i]) {
				c.playerSkillProp[8][3] = 38;
			} else if(treep == trees[0][i]) {
				c.playerSkillProp[8][3] = 25;
			}
		}
		return c.playerSkillProp[8][3];
	}

	private static int getRespawnTime(Client c, int treep) {
		int respawn = -1;
		for(int i = 0; i < trees.length; i++) {
			if(treep == trees[5][i]) {
					respawn = 100;	//1 Minute
			} else if(treep == trees[4][i]) {
					respawn = 50;	//30
			} else if(treep == trees[3][i]) {
					respawn = 25;	//15
			} else if(treep == trees[2][i]) {
					respawn = 25;	//15
			} else if(treep == trees[1][i]) {
					respawn = 15;	//9
			} else if(treep == trees[0][i]) {
					respawn = 10;	//6
			}
		}
		return respawn;
	}

	private static boolean normalTree(Client c, int treep) {
		boolean normal = false;
		for(int i = 0; i < trees.length; i++) {
			if(treep == trees[0][i]) {
				normal = true;
			}
		}
		return normal;
	}

	private static boolean canUseAxe(Client c) {
		boolean axe = false;
		if(performCheck(c, 1349, 1) || performCheck(c, 1351, 1) 
			|| performCheck(c, 1353, 6) || performCheck(c, 1361, 6) 
			|| performCheck(c, 1355, 21) || performCheck(c, 1357, 31)
			|| performCheck(c, 1359, 41) || performCheck(c, 6739, 61)) {
			axe = true;
		}
		return axe;
	}

	private static boolean performCheck(Client c, int i, int l) {
		return (c.getItems().playerHasItem(i) || c.playerEquipment[3] == i) && c.playerLevel[8] >= l;
	}

	private static int getRequiredLevel(Client c, int treep) {
		int respawn = -1;
		for(int i = 0; i < trees.length; i++) {
			if(treep == trees[5][i]) {
					respawn = 75;
			} else if(treep == trees[4][i]) {
					respawn = 60;
			} else if(treep == trees[3][i]) {
					respawn = 45;
			} else if(treep == trees[2][i]) {
					respawn = 30;
			} else if(treep == trees[1][i]) {
					respawn = 15;
			} else if(treep == trees[0][i]) {
					respawn = 1;
			}
		}
		return respawn;
	}

	private static int[][] axes = {
		{1351, 1, 879, 10},
		{1349, 1, 877, 10},
		{1353, 6, 875, 10},
		{1361, 6, 873, 9},
		{1355, 21, 871, 8},
		{1357, 31, 869, 7},
		{1359, 41, 867, 5},
		{6739, 61, 2846, 4},
	};

	private static int[][] trees = {
		{	//	NORMAL
			1276, 1277, 1278, 1279, 1280, 1282, 
			1283, 1284, 1285, 1286, 1287, 1288, 
			1289, 1290, 1291, 1301, 1303, 1304, 
			1305, 1318, 1319, 1315, 1316, 1330, 
			1331, 1332, 1333, 1383, 1384, 2409, 
			2447, 2448, 3033, 3034, 3035, 3036, 
			3879, 3881, 3883, 3893, 3885, 3886, 
			3887, 3888, 3892, 3889, 3890, 3891, 
			3928, 3967, 3968, 4048, 4049, 4050, 
			4051, 4052, 4053, 4054, 4060, 5004, 
			5005, 5045, 5902, 5903, 5904, 8973, 
			8974, 10041, 10081, 10082, 10664, 11112, 
			11510, 12559, 12560, 12732, 12895, 12896, 
			13412, 13411, 13419, 13843, 13844, 13845, 
			13847, 13848, 13849, 13850, 14308, 14309, 
			14513, 14514, 14515, 14521, 14564, 14565, 
			14566, 14593, 14594, 14595, 14600, 14635, 
			14636, 14637, 14642, 14664, 14665, 14666, 
			14693, 14694, 14695, 14696, 14701, 14738, 
			14796, 14797, 14798, 14799, 14800, 14801, 
			14802, 14803, 14804, 14805, 14806, 14807, 
			15489, 15776, 15777, 16264, 16265, 19165, 
			19166, 19167, 23381,
			
		}, {	//	OAK
			1281, 3037, 8462, 8463, 8464, 8465,
			8466, 8467, 10083, 13413, 13420,
		}, {	//	WILLOW
			1308, 5551, 5552, 5553, 8481, 8482, 8483,
			8484, 8485, 8486, 8487, 8488, 8496, 8497,
			8498, 8499, 8500, 8501, 13414, 13421,
		}, {	//	MAPLE
			1307, 4674, 8435, 8436, 8437, 8438, 8439,
			8440, 8441, 8442, 8443, 8444, 8454, 8455,
			8456, 8457, 8458, 8459, 8460, 8461, 13415,
			13423,
		}, {	//	YEW
			1309, 8503, 8504, 8505, 8506, 8507, 8508,
			8509, 8510, 8511, 8512, 8513, 13416, 13422,
		}, {	//	MAGIC
			1306, 1292, 8396, 8397, 8398, 8399, 8400, 8401,
			8402, 8403, 8404, 8405, 8406, 8407, 8408,
			8409, 13417, 13424,
		}
	};

}