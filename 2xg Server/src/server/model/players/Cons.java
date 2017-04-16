package server.model.players;

import server.Config;
import server.Server;
import server.task.Task;
import server.util.Misc;

public class Cons {

/**
* @author 
* Made for Construction interface buttons handling.
*/

	public static int altarX = 2061; // Buildable altar
	public static int altarY = 3140; // Buildable Altar
	public static int TelePorterX = 2060; // the portal thing that makes you able to teleport everywhere
	public static int TelePorterY = 3147; // the portal thing that makes you able to teleport everywhere
	public static int CrystalBallX = 2058; // Create teleport tabs
	public static int CrystalBallY = 3145; // Create teleport tabs
	public static int bedX = 2058;// Bed obviously
	public static int bedY = 3142; // Bed obviously
	public static int TreeX = 2064;// Dead tree
	public static int TreeY = 3143; // Dead tree
	public static int Lect1X = 2059; // burners
	public static int Lect1Y = 3140; // burners
	public static int Lect2X = 2064; // burners
	public static int Lect2Y = 3140; // burners
	public static int ChestX = 2064; // bank chest
	public static int ChestY = 3147;// bank chest

	public static void SpawnTeleporter(final Client c) {
		if(!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(5, false) {
			public void execute() {
				if (c.COtelee > 3) {
					c.getPA().checkObjectSpawn(13641, TelePorterX, TelePorterY, 0, 10);
					this.stop();
				} else {
					if (c.COtelee < 2) {
						c.getPA().checkObjectSpawn(11212, TelePorterX, TelePorterY, 0, 10);
						this.stop();
					}
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}

	public static void SpawnCrystal(final Client c) {
		if(!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(5, false) {
			public void execute() {
				if (c.COcryst > 3) {
					c.getPA().checkObjectSpawn(13660, CrystalBallX, CrystalBallY, 0, 10);
					this.stop();
				} else {
					if (c.COcryst < 2) {
						c.getPA().checkObjectSpawn(11209, CrystalBallX, CrystalBallY, 0, 10);
						this.stop();
					}
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}

	public static void BuildTeleport(final Client c) {
		if(!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (c.playerLevel[23] < 87) {
			c.sendMessage("You need level 87 construction to build that!");
			return;
		}
		if(!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to build this!");
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(5, false) {
			public void execute() {
				if (c.playerLevel[23] < 87) {
					c.sendMessage("You need level 87 construction to build that!");
					this.stop();
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to build this!");
					this.stop();
					return;
				}
				if (c.playerLevel[23] > 67) {
					if (c.getItems().playerHasItem(1539, 300) && c.getItems().playerHasItem(561, 100) && c.getItems().playerHasItem(556, 100) && c.getItems().playerHasItem(554, 100) && c.getItems().playerHasItem(555, 100) && c.getItems().playerHasItem(557, 100)) {
						c.startAnimation(898);
						c.getItems().deleteItem2(1539, 300);
						c.getItems().deleteItem2(561, 100);
						c.getItems().deleteItem2(556, 100);
						c.getItems().deleteItem2(554, 100);
						c.getItems().deleteItem2(555, 100);
						c.getItems().deleteItem2(557, 100);
						c.COcryst += 5;
						c.getPA().closeAllWindows();
						c.getPA().addSkillXP(1000, 23);
						c.getPA().checkObjectSpawn(13641, TelePorterX, TelePorterY, 0, 10);
						this.stop();
					} else {
						c.sendMessage("You don't have the required materials.");
						c.sendMessage("You need 300 Steel Nails, 1 hammer, 100 Air, Nature, Water, Fire and Earth runes.");
						this.stop();
					}
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}

	public static void BuildCrystal(final Client c) {
		if(!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (c.playerLevel[23] < 92) {
			c.sendMessage("You need level 92 construction to build that!");
			return;
		}
		if(!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to build this!");
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(5, false) {
			public void execute() {
				if (c.playerLevel[23] < 92) {
					c.sendMessage("You need level 92 construction to build that!");
					this.stop();
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to build this!");
					this.stop();
					return;
				}
				if (c.playerLevel[23] > 67) {
					if (c.getItems().playerHasItem(1539, 80) && c.getItems().playerHasItem(8007, 100) && c.getItems().playerHasItem(8009, 100) && c.getItems().playerHasItem(8010, 100)) {
						c.startAnimation(898);
						c.getItems().deleteItem2(1539, 80);
						c.getItems().deleteItem2(8007, 100);
						c.getItems().deleteItem2(8010, 100);
						c.getItems().deleteItem2(8009, 100);
						c.COcryst += 5;
						c.getPA().closeAllWindows();
						c.getPA().addSkillXP(1000, 23);
						c.getPA().checkObjectSpawn(13660, CrystalBallX, CrystalBallY, 0, 10);
						this.stop();
					} else {
						c.sendMessage("You don't have the required materials.");
						c.sendMessage("You need 80 Steel Nails, 1 hammer, 100 Varrock/Falador/Camelot teleport tabs.");
						this.stop();
					}
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}

	public static void BuildLecter(final Client c) {
		if(!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (c.playerLevel[23] < 67) {
			c.sendMessage("You need level 67 construction to build that!");
			return;
		}
		if(!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to build this!");
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(5, false) {
			public void execute() {
				if (c.playerLevel[23] < 67) {
					c.sendMessage("You need level 67 construction to build that!");
					this.stop();
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to build this!");
					this.stop();
					return;
				}
				if (c.playerLevel[23] > 67) {
					if (c.getItems().playerHasItem(1539, 54) && c.getItems().playerHasItem(561, 27) && c.getItems().playerHasItem(2351, 4)) {
						c.startAnimation(898);
						c.getItems().deleteItem2(1539, 54);
						c.getItems().deleteItem2(561, 27);
						c.getItems().deleteItem2(2351, 4);
						c.COLectt += 5;
						c.getPA().closeAllWindows();
						c.getPA().addSkillXP(1500, 23);
						c.getPA().checkObjectSpawn(13648, Lect1X, Lect1Y, c.heightLevel, 10);
						c.getPA().checkObjectSpawn(13648, Lect2X, Lect2Y, c.heightLevel, 10);
						this.stop();
					} else {
						c.sendMessage("You don't have the required materials.");
						c.sendMessage("You need 54 Steel Nails, 27 Nature runes, 4 Iron Bars, and one hammer!");
						this.stop();
					}
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}
	
	public static void BuildTree(final Client c) {
		if(!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if(c.playerLevel[23] < 4) {
			c.sendMessage("You need a level of 5 Construction to do that.");
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(5, false) {
			public void execute() {
	 			if(c.playerLevel[23] < 4) {
					c.sendMessage("You need a level of 5 Construction to do that.");
					this.stop();
					return;
				}
				if (c.playerLevel[23] > 4) {
					if (c.getItems().playerHasItem(1511, 3) && c.getItems().playerHasItem(5343, 1)) {
						c.startAnimation(2291);
						c.COtreee += 5;
						c.getItems().deleteItem2(1511, 3);
						c.sendMessage("You create a tree!");
						c.getPA().closeAllWindows();
						c.getPA().addSkillXP(1000, 23);
						c.getPA().checkObjectSpawn(13411, TreeX, TreeY, c.heightLevel, 10);
						this.stop();
					} else {
						c.sendMessage("You don't have the required materials.");
						c.sendMessage("You need 3 Normal Logs and a Seed Dibber to do that!");
						this.stop();
					}
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}

	public static void BuildChest(final Client c) {
		if(!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (c.playerLevel[23] < 95) {
			c.sendMessage("You need level 95 construction to build that!");
			return;
		}
		if(!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to build this!");
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(5, false) {
			public void execute() {
				if (c.playerLevel[23] < 95) {
					c.sendMessage("You need level 95 construction to build that!");
					this.stop();
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to build this!");
					this.stop();
					return;
				}
				if (c.playerLevel[23] > 94) {
					if (c.getItems().playerHasItem(1539, 40) && c.getItems().playerHasItem(8782, 2) && c.getItems().playerHasItem(2351, 1)) {
						c.startAnimation(898);
						c.COchest += 5;
						c.getItems().deleteItem2(1539, 40);
						c.getItems().deleteItem2(8782, 1);
						c.getItems().deleteItem2(8782, 1);
						c.getItems().deleteItem2(2351, 1);
						c.sendMessage("You build a Bank chest.");
						c.getPA().closeAllWindows();
						c.getPA().addSkillXP(3000, 23);
						c.getPA().checkObjectSpawn(3193, ChestX, ChestY, 2, 10);
						this.stop();
					} else {
						c.sendMessage("You don't have the required materials.");
						c.sendMessage("You need 40 Steel nails, 1 iron bar and 2 Mahogany Planks!");
						this.stop();
					}
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}

	public static void BuildBed(final Client c) {
		if(!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if(!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if(c.playerLevel[23] < 40) {
			c.sendMessage("You need a level 40 Construction to do that.");
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(5, false) {
			public void execute() {
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					this.stop();
					return;
				}
				if(c.playerLevel[23] < 40) {
					c.sendMessage("You need a level 40 Construction to do that.");
					this.stop();
					return;
				}
				if (c.playerLevel[23] > 40) {
					if (c.getItems().playerHasItem(1539, 20) && c.getItems().playerHasItem(8778, 3)) {
						c.getItems().deleteItem2(1539, 20);
						c.getItems().deleteItem2(8778, 3);
						c.startAnimation(898);
						c.CObeddy += 5;
						c.sendMessage("You build a Bed.");
						c.getPA().closeAllWindows();
						c.getPA().addSkillXP(910, 23);
						c.getPA().checkObjectSpawn(13151, bedX, bedY, c.heightLevel, 10);
						this.stop();
					} else {
						c.sendMessage("You don't have the required materials.");
						c.sendMessage("You need 20 Steel Nails, And 3 Oak Planks.");
						this.stop();
					}
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}

	public static void BuildAltar(final Client c) {
		if(!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if (c.playerLevel[23] < 63) {
			c.sendMessage("You need level 64 construction to build that!");
			return;
		}
		if(!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to build this!");
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(5, false) {
			public void execute() {
				if (c.playerLevel[23] < 63) {
					c.sendMessage("You need level 64 construction to build that!");
					this.stop();
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to build this!");
					this.stop();
					return;
				}
				if (c.playerLevel[23] > 63) {
					if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(8782, 1) && c.getItems().playerHasItem(2351, 1)) {
						c.startAnimation(898);
						c.getItems().deleteItem2(1539, 15);
						c.getItems().deleteItem2(8782, 1);
						c.getItems().deleteItem2(8782, 1);
						c.getItems().deleteItem2(2351, 1);
						c.COaltar += 5;
						c.sendMessage("You build an Altar.");
						c.getPA().closeAllWindows();
						c.getPA().addSkillXP(1500, 23);
						c.getPA().checkObjectSpawn(13191, altarX, altarY, c.heightLevel, 10);
						this.stop();
					} else {
						c.sendMessage("You don't have the required materials.");
						c.sendMessage("You need 15 Steel Nails, 2 Mahogany Planks and 1 Iron Bar!");
						this.stop();
					}
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}
	
	public static void SpawnBankChest(final Client c) {
		if(!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(5, false) {
			public void execute() {
				if (c.COchest > 3) {
					c.getPA().checkObjectSpawn(3193, ChestX, ChestY, 2, 10);
					this.stop();
				} else {
					if (c.COchest < 2) {
						c.getPA().checkObjectSpawn(11219, ChestX, ChestY, 0, 10);
						this.stop();
					}
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}

	public static void SpawnLecta(final Client c) {
		if(!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(5, false) {
			public void execute() {
				if (c.COLectt > 3) {
					c.getPA().checkObjectSpawn(13648, Lect1X, Lect1Y, 0, 10);
					c.getPA().checkObjectSpawn(13648, Lect2X, Lect2Y, 0, 10);
					this.stop();
				} else {
					if (c.COLectt < 2) {
						c.getPA().checkObjectSpawn(11220, Lect1X, Lect1Y, 0, 10);
						c.getPA().checkObjectSpawn(11220, Lect2X, Lect2Y, 0, 10);
						this.stop();
					}
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}

	public static void SpawnTree(final Client c) {
		if(!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(5, false) {
			public void execute() {
				if (c.COtreee > 3) {
					c.getPA().checkObjectSpawn(13411, TreeX, TreeY, 0, 10);
					this.stop();
				} else {
					if (c.COtreee < 2) {
						c.getPA().checkObjectSpawn(11217, TreeX, TreeY, 0, 10);
						this.stop();
					}
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}
	
	public static void SpawnBed(final Client c) {
		if(!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(5, false) {
			public void execute() {
				if (c.CObeddy > 3) {
					c.getPA().checkObjectSpawn(13151, bedX, bedY, 2, 10);
					this.stop();
				} else {
					if (c.CObeddy < 2) {
						c.getPA().checkObjectSpawn(11216, bedX, bedY, 0, 10);
						this.stop();
					}
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}	
	
	public static void SpawnAltar(final Client c) {
		if(!c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(5, false) {
			public void execute() {
				if (c.COaltar > 3) {
					c.getPA().checkObjectSpawn(13191, altarX, altarY, 2, 10);
					this.stop();
				} else {
					if (c.COaltar < 2) {
						c.getPA().checkObjectSpawn(11215, altarX, altarY, 0, 10);
						this.stop();
					}
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}
	
	public static void HandleConstructionInterface(final Client c, int packetType, int packetSize) {
	//CONSTRUCTION INTERFACES
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0, packetSize);
		switch (actionButtonId){ // 7002 
			case 122099://public
				c.getPA().startTeleport2(2060, 3261, 0);
				c.sendMessage("You teleported to the public Construction zone.");
				c.sendMessage("This is the place to train Construction with others!");
				c.sendMessage("The private zone is just to make ur place lookbaws, not to train!");
			break;

			case 122102://private
				c.getPA().startTeleport2(2062, 3143, c.playerId * 4);
				c.sendMessage("You teleported you're own house.");
				c.sendMessage("Here you can build tons of custom objects!");
				SpawnAltar(c);
				SpawnTree(c);
				SpawnBankChest(c);
				SpawnBed(c);
				SpawnLecta(c);
				SpawnTeleporter(c);
			break;
			
			case 122019://fern
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 0) {
					c.sendMessage("You need a level 1 Construction to do that.");
					return;
				}
				if (!c.getItems().playerHasItem(249, 1) && !c.getItems().playerHasItem(1511, 1)) {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer, 1 clean Guam and 1 normal log.");
					return;
				}
				if(System.currentTimeMillis() - c.buryDelay > 1500) {	
					c.constructionID = 13432;
					c.getDH().sendDialogues(28178, 1);
				}
			break;

			case 122022://tree
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 4) {
					c.sendMessage("You need a level 5 Construction to do that.");
					return;
				}
				if (!c.getItems().playerHasItem(1511, 1) && !c.getItems().playerHasItem(1511, 1) && !c.getItems().playerHasItem(1511, 1)) {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer and 3 normal logs.");
					return;
				}
				if(System.currentTimeMillis() - c.buryDelay > 1500) {	
					c.constructionID = 13411;
					c.getDH().sendDialogues(29111, 1);
				}
			break;

			case 122025: // chair
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 18) {
					c.sendMessage("You need a level 19 Construction to do that.");
					return;
				}
				if (!c.getItems().playerHasItem(1539, 10) && !c.getItems().playerHasItem(8778, 1) && !c.getItems().playerHasItem(8778, 1)) {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need a hammer, 2 Oak Planks and 10 iron nails!");
					return;
				}
				if(System.currentTimeMillis() - c.buryDelay > 1500) {	
					c.constructionID = 13584;
					c.getDH().sendDialogues(29112, 1);
				}
			break;
			
			case 122028://bookcase
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 25) {
					c.sendMessage("You need a level 26 Construction to do that.");
					return;
				}
				if (!c.getItems().playerHasItem(1539, 15) && !c.getItems().playerHasItem(8778, 1) && !c.getItems().playerHasItem(8778, 1) && !c.getItems().playerHasItem(8778, 1)) {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer, 15 iron nails and 3 oak planks.");
					return;
				}
				if(System.currentTimeMillis() - c.buryDelay > 1500) {	
					c.constructionID = 13598;
					c.getDH().sendDialogues(29113, 1);
				}
			break;

			case 122031://greenman's ale
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 28) {
					c.sendMessage("You need a level 29 Construction to do that.");
					return;
				}
				if (!c.getItems().playerHasItem(1539, 15) && !c.getItems().playerHasItem(8778, 1) && !c.getItems().playerHasItem(8778, 1)) {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer, 15 iron nails and 2 oak planks.");
					return;
				}
				if(System.currentTimeMillis() - c.buryDelay > 1500) {	
					c.constructionID = 13571;
					c.getDH().sendDialogues(29114, 1);
				}
			break;

			case 122034://small oven
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 24) {
					c.sendMessage("You need a level 24 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(2351, 3)) {
					c.getItems().deleteItem2(2351, 3);
					c.startAnimation(898);
					c.sendMessage("You build a Small oven.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(600, 23);
					c.getPA().checkObjectSpawn(13533, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 3 iron bars and one hammer!");
				}
			break;

			case 122037://carved oak bench
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 31) {
					c.sendMessage("You need a level 31 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8778, 3)) {
					c.getItems().deleteItem2(1539, 15);
					c.getItems().deleteItem2(8778, 3);
					c.sendMessage("You build a Carved oak bench.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().addSkillXP(900, 23);
					c.getPA().checkObjectSpawn(13302, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 3 oak planks, one hammer and 15 steel nails!");
				}
			break;

			case 122040://painting stand
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 41) {
					c.sendMessage("You need a level 41 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(1539, 20) && c.getItems().playerHasItem(8778, 2)) {
					c.getItems().deleteItem2(1539, 20);
					c.getItems().deleteItem2(8778, 2);
					c.startAnimation(898);
					c.sendMessage("You build a Painting stand.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(3700, 23);
					c.getPA().checkObjectSpawn(13717, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 2 oak planks and 20 steel nails!");
				}
			break;

			case 122043://bed
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 40) {
					c.sendMessage("You need a level 40 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(1539, 20) && c.getItems().playerHasItem(8778, 3)) {
					c.getItems().deleteItem2(1539, 20);
					c.getItems().deleteItem2(8778, 3);
					c.startAnimation(898);
					c.sendMessage("You build a Bed.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(3300, 23);
					c.getPA().checkObjectSpawn(13151, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 3 oak planks, 1 hammer and 20 steel nails!");
				}
			break;

			case 122046://teak drawers
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 51) {
					c.sendMessage("You need a level 51 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(1539, 20) && c.getItems().playerHasItem(8780, 1) && c.getItems().playerHasItem(8780, 1)) {
					c.getItems().deleteItem2(1539, 20);
					c.getItems().deleteItem2(8780, 1);
					c.startAnimation(898);
					c.getItems().deleteItem2(8780, 1);
					c.sendMessage("You build a Teak drawers.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(4000, 23);
					c.getPA().checkObjectSpawn(13158, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 20 steel nails, one hammer and one teak plank.");
				}
			break;

			case 122049://mithril armour
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 28) {
					c.sendMessage("You need a level 28 Construction to do that.");
					return;
				}
				if(c.playerLevel[13] < 68) {
					c.sendMessage("You need a level 68 Smithing to do that.");
					return;
				}
				if (c.getItems().playerHasItem(1159, 1) && c.getItems().playerHasItem(1121, 1) && c.getItems().playerHasItem(1071, 1)) {
					c.getItems().deleteItem2(1159, 1);
					c.getItems().deleteItem2(1121, 1);
					c.getItems().deleteItem2(1071, 1);
					c.sendMessage("You build a Mithril armour.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(135, 23);
					c.getPA().addSkillXP(25 * Config.SMITHING_EXPERIENCE, 13);
					c.getPA().checkObjectSpawn(13491, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 1 of each: mithril full-helm/platelegs/platebody and a hammer.");
				}
			break;

			case 122052://adamant armour
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 28) {
					c.sendMessage("You need a level 28 Construction to do that.");
					return;
				}
				if(c.playerLevel[13] < 88) {
					c.sendMessage("You need a level 88 Smithing to do that.");
					return;
				}
				if (c.getItems().playerHasItem(1161, 1) && c.getItems().playerHasItem(1123, 1) && c.getItems().playerHasItem(1073, 1)) {
					c.getItems().deleteItem2(1161, 1);
					c.getItems().deleteItem2(1123, 1);
					c.getItems().deleteItem2(1073, 1);
					c.sendMessage("You build a Adamant armour.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(150, 23);
					c.getPA().addSkillXP(25 * Config.SMITHING_EXPERIENCE, 13);
					c.getPA().checkObjectSpawn(13492, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 1 of each: Adamant full-helm/platelegs/platebody and a hammer.");
				}
			break;

			case 122055://rune armour
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 28) {
					c.sendMessage("You need a level 28 Construction to do that.");
					return;
				}
				if(c.playerLevel[13] < 99) {
					c.sendMessage("You need a level 99 Smithing to do that.");
					return;
				}
				if (c.getItems().playerHasItem(1163, 1) && c.getItems().playerHasItem(1127, 1) && c.getItems().playerHasItem(1079, 1)) {
					c.getItems().deleteItem2(1163, 1);
					c.getItems().deleteItem2(1127, 1);
					c.getItems().deleteItem2(1079, 1);
					c.sendMessage("You build a Rune armour.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(165, 23);
					c.getPA().addSkillXP(25 * Config.SMITHING_EXPERIENCE, 13);
					c.getPA().checkObjectSpawn(13493, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 1 of each: Rune full-helm/platelegs/platebody and a hammer.");
				}
			break;


			case 122058://rune display case
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 28) {
					c.sendMessage("You need a level 28 Construction to do that.");
					return;
				}
				if(c.playerLevel[20] < 44) {
					c.sendMessage("You need a level 44 Runecrafting to do that.");
					return;
				}
				if (c.getItems().playerHasItem(563, 100) && c.getItems().playerHasItem(561, 100) && c.getItems().playerHasItem(8780, 1)) {
					c.getItems().deleteItem2(563, 100);
					c.getItems().deleteItem2(561, 1);
					c.getItems().deleteItem2(8780, 1);
					c.sendMessage("You build a Rune display case.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(212, 23);
					c.getPA().addSkillXP(44 * Config.RUNECRAFTING_EXPERIENCE, 20);
					c.getPA().checkObjectSpawn(13508, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 100 law runes, one nature rune and 1 hammer & teak plank!");
				}
			break;

			case 122061://archery target
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 81) {
					c.sendMessage("You need a level 81 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(1539, 25) && c.getItems().playerHasItem(8780, 3)) {
					c.getItems().deleteItem2(1539, 25);
					c.getItems().deleteItem2(8780, 3);
					c.sendMessage("You build an Archery target.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(7000, 23);
					c.getPA().checkObjectSpawn(13402, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 3 teak planks, one hammer and 25 steel nails!");
				}
			break;

			case 122064://combat stone
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 59) {
					c.sendMessage("You need a level 59 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(2351, 4)) {
					c.getItems().deleteItem2(2351, 4);
					c.startAnimation(4800);
					c.sendMessage("You build a Combat stone.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(455, 23);
					c.getPA().checkObjectSpawn(-1, c.absX, c.absY, c.heightLevel, 10);
					Server.npcHandler.spawnNpc(c, 4162, c.absX, c.absY, c.heightLevel, 0, 100, 5, 50, 50, false, true);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 4 iron bars and one hammer!");
				}
			break;

			case 122067://elemental balance
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 77) {
					c.sendMessage("You need a level 77 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(2351, 5) && c.getItems().playerHasItem(1539, 49)) {
					c.getItems().deleteItem2(2351, 5);
					c.sendMessage("You build an Elemental balance.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(6700, 23);
					c.getPA().checkObjectSpawn(-1, c.absX, c.absY, c.heightLevel, 10);
					Server.npcHandler.spawnNpc(c, 4095, c.absX, c.absY, c.heightLevel, 0, 100, 5, 50, 50, false, true);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need one hammer, 49 Steel nails and 5 iron bars!");
				}
			break;

			case 122070://mahogany prize chest
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 54) {
					c.sendMessage("You need a level 54 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(1539, 20) && c.getItems().playerHasItem(8782, 4)) {
					c.getItems().deleteItem2(1539, 20);
					c.getItems().deleteItem2(8782, 4);
					c.sendMessage("You build a Mahogany prize chest.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(4000, 23);
					c.getPA().checkObjectSpawn(13389, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 4 Mahogany planks, one hammer and 20 steel nails!");
				}
			break;

			case 122073://lectern
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 67) {
					c.sendMessage("You need a level 67 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(555, 50) && c.getItems().playerHasItem(560, 50)) {
					c.getItems().deleteItem2(8782, 2);
					c.getItems().deleteItem2(555, 50);
					c.startAnimation(898);
					c.getItems().deleteItem2(560, 50);
					c.sendMessage("You build a Lectern.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(4900, 23);
					c.getPA().checkObjectSpawn(13648, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 50 Water runes, 50 death runes, 2 Mahogany Planks and one hammer!");
				}
			break;

			case 122076://crystal of power
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 66) {
					c.sendMessage("You need a level 66 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(565, 30) && c.getItems().playerHasItem(8782, 2) && c.getItems().playerHasItem(2351, 1)) {
					c.getItems().deleteItem2(1539, 15);
					c.getItems().deleteItem2(8782, 2);
					c.getItems().deleteItem2(565, 30);
					c.startAnimation(898);
					c.getItems().deleteItem2(2351, 1);
					c.sendMessage("You build a Crystal of power.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(4650, 23);
					c.getPA().checkObjectSpawn(13661, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 1 hammer, 2 Mahogany planks, 30 blood runes, 15 steel nails, 1 iron bar!");
				}
			break;

			case 122079://altar
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 64) {
					c.sendMessage("You need a level 64 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(1539, 55) && c.getItems().playerHasItem(561, 100) && c.getItems().playerHasItem(536, 3) && c.getItems().playerHasItem(8782, 2)) {
					c.getItems().deleteItem2(1539, 55);
					c.getItems().deleteItem2(536, 3);
					c.startAnimation(898);
					c.getItems().deleteItem2(8782, 2);
					c.getItems().deleteItem2(561, 100);
					c.sendMessage("You build an Altar.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(4500, 23);
					c.getPA().checkObjectSpawn(13191, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 3 Dragon Bones, 100 nature runes, 2 Mahogany Planks, 1 hammer, 55 steel nails!");
				}
			break;

			case 122082://intense burners
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 61) {
					c.sendMessage("You need a level 61 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(1539, 27) && c.getItems().playerHasItem(8782, 2) && c.getItems().playerHasItem(564, 40) && c.getItems().playerHasItem(263, 1)) {
					c.getItems().deleteItem2(1539, 27);
					c.getItems().deleteItem2(8782, 2);
					c.getItems().deleteItem2(564, 40);
					c.getItems().deleteItem2(263, 1);
					c.startAnimation(898);
					c.sendMessage("You build an Intense burner.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(4000, 23);
					c.getPA().checkObjectSpawn(13210, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 2 Mahogany Planks, 1 Clean Kwuarm, 1 Hammer, 27 Steel Nails and 40 Cosmic Runes!");
				}
			break;

			case 122085://hedge
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 80) {
					c.sendMessage("You need a level 80 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(1511, 2) && c.getItems().playerHasItem(561, 11) && c.getItems().playerHasItem(265, 2)) {
					c.getItems().deleteItem2(1511, 2);
					c.getItems().deleteItem2(561, 11);
					c.getItems().deleteItem2(265, 2);
					c.startAnimation(898);
					c.sendMessage("You build a Hedge.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(6400, 23);
					c.getPA().checkObjectSpawn(13476, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 2 Clean Cadantine, 11 Nature Runes, 2 Normal logs and 1 hammer");
				}
			break;

			case 122088://rocnar
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 83) {
					c.sendMessage("You need a level 83 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(2361, 2) && c.getItems().playerHasItem(269, 2) && c.getItems().playerHasItem(562, 10)) {
					c.getItems().deleteItem2(2361, 2);
					c.getItems().deleteItem2(269, 2);
					c.getItems().deleteItem2(562, 10);
					c.startAnimation(898);
					c.sendMessage("You build a Rocnar.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(7000, 23);
					c.getPA().checkObjectSpawn(13373, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 2 Clean Torstol's, 1 hammer, 2 Adamant Bars, 10 Chaos runes.");
				}
			break;

			case 122091://bank chest
				if(c.isInPrivCon()) {
					c.sendMessage("You need to be in the construction skilling area first!");
					return;
				}
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					return;
				}
				if(c.playerLevel[23] < 92) {
					c.sendMessage("You need a level 92 Construction to do that.");
					return;
				}
				if (c.getItems().playerHasItem(1539, 40) && c.getItems().playerHasItem(8782, 2) && c.getItems().playerHasItem(2351, 1)) {
					c.startAnimation(898);
					c.getItems().deleteItem2(1539, 40);
					c.getItems().deleteItem2(8782, 2);
					c.getItems().deleteItem2(2347, 1);
					c.getItems().deleteItem2(2351, 1);
					c.sendMessage("You build a Bank chest.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(9000, 23);
					c.sendMessage("You're Hammer breaks!");
					c.getPA().checkObjectSpawn(3193, c.absX, c.absY, c.heightLevel, 10);
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need 2 Mahogany planks, 40 Steel nails, 1 hammer, and one iron bar!");
				}
			break;
		}
	}	
}