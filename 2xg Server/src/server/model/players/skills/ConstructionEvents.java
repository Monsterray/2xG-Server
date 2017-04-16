package server.model.players.skills;

import server.Server;
import server.model.players.Client;
import server.task.Task;
/**
 * Handles all ConstructionEvents cba spamming up client class
 *
 * @author 
 *
 **/
 
public class ConstructionEvents {
 static int conSkill = 23;
	public static void MakeIronNails(final Client c) { // creates nails - works like boss, // Gabbe
		if (!c.getItems().playerHasItem(2353,1)) {
			c.sendMessage("You do not have any steel bars to smelt..");
			return;
		}
		if (c.playerLevel[c.playerSmithing] != 30) {
			c.sendMessage("You need atleast level 30 smithing to smith nails!");
			return;
		}
		Server.getTaskScheduler().addEvent(new Task(3, false) {
			public void execute() {
				if(c.isWalking == true) {
					this.stop();
					return;
				}
				if (!c.getItems().playerHasItem(2353,1)) {
					c.sendMessage("You do not have any steel bars to smelt..");
					this.stop();
					return;
				}
				c.turnPlayerTo(c.objectX, c.objectY);
				c.startAnimation(896);
				c.getItems().deleteItem(2353, c.getItems().getItemSlot(2353), 1);
				c.getItems().addItem(1539, 19);
				c.getPA().addSkillXP(200, c.playerSmithing);
				//c.spinning = true;
				//container.stop();
			}
		});
	}
 
	/**
	*** Chairs
	/**/
	public static void conChair(final Client c) {
		if(c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if(!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if(c.playerLevel[23] < 19) {
			c.sendMessage("You need a level 19 Construction to do that.");
			return;
		}
		c.sendMessage("Starting event...Hold on.");
		Server.getTaskScheduler().addEvent(new Task(1, false) {
			public void execute() {
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					this.stop();
					return;
				}
				if(c.playerLevel[23] < 19) {
					c.sendMessage("You need a level 19 Construction to do that.");
					this.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 10) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 10);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.startAnimation(898);
					c.sendMessage("You build a Chair.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(1200, 23);
					c.getPA().checkObjectSpawn(13584, c.absX, c.absY, 1, 10);
					c.buryDelay = System.currentTimeMillis();
					this.stop();
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need a hammer, 2 Oak Planks and 10 steel nails!");
					this.stop();
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}
				
	public static void conChair2(final Client c) {
		if(c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if(!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if(c.playerLevel[23] < 19) {
			c.sendMessage("You need a level 19 Construction to do that.");
			return;
		}
		c.sendMessage("Starting event...Hold on.");
		Server.getTaskScheduler().addEvent(new Task(1, false) {
			public void execute() {
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					this.stop();
					return;
				}
				if(c.playerLevel[23] < 19) {
					c.sendMessage("You need a level 19 Construction to do that.");
					this.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 10) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 10);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.startAnimation(898);
					c.sendMessage("You build a Chair.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(1200, 23);
					c.getPA().checkObjectSpawn(13584, c.absX, c.absY, 2, 10);
					c.buryDelay = System.currentTimeMillis();
					this.stop();
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need a hammer, 2 Oak Planks and 10 steel nails!");
					this.stop();
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}
				
	public static void conChair3(final Client c) {
		if(c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if(!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if(c.playerLevel[23] < 19) {
			c.sendMessage("You need a level 19 Construction to do that.");
			return;
		}
		c.sendMessage("Starting event...Hold on.");
		Server.getTaskScheduler().addEvent(new Task(1, false) {
			public void execute() {
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					this.stop();
					return;
				}
				if(c.playerLevel[23] < 19) {
					c.sendMessage("You need a level 19 Construction to do that.");
					this.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 10) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 10);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.startAnimation(898);
					c.sendMessage("You build a Chair.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(1200, 23);
					c.getPA().checkObjectSpawn(13584, c.absX, c.absY, 3, 10);
					c.buryDelay = System.currentTimeMillis();
					this.stop();
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need a hammer, 2 Oak Planks and 10 steel nails!");
					this.stop();
				}
				if(c.disconnected) {
				this.stop();
				return;
				}
			}
		});
	}
		
	public static void conChair4(final Client c) {
		if(c.isInPrivCon()) {
			c.sendMessage("You need to be in the construction skilling area first!");
			return;
		}
		if(!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if(c.playerLevel[23] < 19) {
			c.sendMessage("You need a level 19 Construction to do that.");
			return;
		}
		c.sendMessage("Starting event...Hold on.");
		Server.getTaskScheduler().addEvent(new Task(1, false) {
			public void execute() {
				if(!c.getItems().playerHasItem(2347, 1)) {
					c.sendMessage("You need a hammer to do that.");
					this.stop();
					return;
				}
				if(c.playerLevel[23] < 19) {
					c.sendMessage("You need a level 19 Construction to do that.");
					this.stop();
					return;
				}
				if (c.getItems().playerHasItem(1539, 10) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
					c.getItems().deleteItem2(1539, 10);
					c.getItems().deleteItem2(8778, 1);
					c.getItems().deleteItem2(8778, 1);
					c.startAnimation(898);
					c.sendMessage("You build a Chair.");
					c.getPA().closeAllWindows();
					c.getPA().addSkillXP(1200, 23);
					c.getPA().checkObjectSpawn(13584, c.absX, c.absY, 4, 10);
					c.buryDelay = System.currentTimeMillis();
					this.stop();
				} else {
					c.sendMessage("You don't have the required materials.");
					c.sendMessage("You need a hammer, 2 Oak Planks and 10 steel nails!");
					this.stop();
				}
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}
				
	/**
		* Fern
	**/
		
	public static void conFern(final Client c) {
		if(c.isInPrivCon()) {
	c.sendMessage("You need to be in the construction skilling area first!");
	return;
		}
		if(!c.getItems().playerHasItem(2347, 1)) {
			c.sendMessage("You need a hammer to do that.");
			return;
		}
		if(c.playerLevel[23] < 1) {
			c.sendMessage("You need a level 1 Construction to do that.");
			return;
		}
		c.sendMessage("Hold on.. Placing Event-Request..");
		Server.getTaskScheduler().addEvent(new Task(1, false) {
			public void execute() {
				if (c.getItems().playerHasItem(249, 1) && c.getItems().playerHasItem(1511, 1)) {
					c.getItems().deleteItem2(249, 1);
					c.getItems().deleteItem2(1511, 1);
					c.sendMessage("You build a Fern.");
					c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.buryDelay = System.currentTimeMillis();
					c.getPA().addSkillXP(180, 23);
					this.stop();
					c.getPA().checkObjectSpawn(13432, c.absX, c.absY, 1, 10);
				} else {
					c.sendMessage("You don't have the required materials!");
					c.sendMessage("You need, one hammer, 1 clean Guam and 1 normal log.");
					this.stop();
				}
				this.stop();
				if(c.disconnected) {
					this.stop();
					return;
				}
			}
		});
	}

	public static void conFern2(final Client c) {
			if(c.isInPrivCon()) {
		c.sendMessage("You need to be in the construction skilling area first!");
		return;
		}
				if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[23] < 1) {
				c.sendMessage("You need a level 1 Construction to do that.");
				return;
			}
			c.sendMessage("Hold on.. Placing Event-Request..");
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
			if (c.getItems().playerHasItem(249, 1) && c.getItems().playerHasItem(1511, 1)) {
				c.getItems().deleteItem2(249, 1);
				c.getItems().deleteItem2(1511, 1);
				c.sendMessage("You build a Fern.");
				c.getPA().closeAllWindows();
				c.startAnimation(898);
				c.buryDelay = System.currentTimeMillis();
				c.getPA().addSkillXP(180, 23);
				c.getPA().checkObjectSpawn(13432, c.absX, c.absY, 2, 10);
				this.stop();
				} else {
				c.sendMessage("You don't have the required materials!");
				c.sendMessage("You need, one hammer, 1 clean Guam and 1 normal log.");
				this.stop();
				}
                        this.stop();
								if(c.disconnected) {
			this.stop();
			return;
			}
                }
              
                });
}

	public static void conFern3(final Client c) {
			if(c.isInPrivCon()) {
		c.sendMessage("You need to be in the construction skilling area first!");
		return;
		}
				if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[23] < 1) {
				c.sendMessage("You need a level 1 Construction to do that.");
				return;
			}
			c.sendMessage("Hold on.. Placing Event-Request..");
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
			if (c.getItems().playerHasItem(249, 1) && c.getItems().playerHasItem(1511, 1)) {
				c.getItems().deleteItem2(249, 1);
				c.getItems().deleteItem2(1511, 1);
				c.sendMessage("You build a Fern.");
				c.getPA().closeAllWindows();
				c.startAnimation(898);
				c.getPA().addSkillXP(180, 23);
				c.getPA().checkObjectSpawn(13432, c.absX, c.absY, 3, 10);
				c.buryDelay = System.currentTimeMillis();
				this.stop();
				} else {
				c.sendMessage("You don't have the required materials!");
				c.sendMessage("You need, one hammer, 1 clean Guam and 1 normal log.");
				this.stop();
				}
                        this.stop();
								if(c.disconnected) {
			this.stop();
			return;
			}
                }
              
                });
}

	public static void conFern4(final Client c) {
			if(c.isInPrivCon()) {
		c.sendMessage("You need to be in the construction skilling area first!");
		return;
		}
				if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[23] < 1) {
				c.sendMessage("You need a level 1 Construction to do that.");
				return;
			}
			c.sendMessage("Hold on.. Placing Event-Request..");
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
			if (c.getItems().playerHasItem(249, 1) && c.getItems().playerHasItem(1511, 1)) {
				c.getItems().deleteItem2(249, 1);
				c.getItems().deleteItem2(1511, 1);
				c.sendMessage("You build a Fern.");
				c.getPA().closeAllWindows();
				c.startAnimation(898);
				c.getPA().addSkillXP(400, 23);
				c.getPA().checkObjectSpawn(13432, c.absX, c.absY, 4, 10);
				c.buryDelay = System.currentTimeMillis();
				this.stop();
				} else {
				c.sendMessage("You don't have the required materials!");
				c.sendMessage("You need, one hammer and 3 normal logs.");
				this.stop();
				}
                        this.stop();
								if(c.disconnected) {
			this.stop();
			return;
			}
                }
              
                });
}

	public static void conTree(final Client c) {
			if(c.isInPrivCon()) {
		c.sendMessage("You need to be in the construction skilling area first!");
		return;
		}
						if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[23] < 5) {
				c.sendMessage("You need a level 5 Construction to do that.");
				return;
			}
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
							if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				this.stop();
				return;
			}
			if(c.playerLevel[23] < 5) {
				c.sendMessage("You need a level 5 Construction to do that.");
				this.stop();
				return;
			}
					if (c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(1511, 1)) {
				c.getItems().deleteItem2(1511, 1);
				c.getItems().deleteItem2(1511, 1);
				c.getItems().deleteItem2(1511, 1);
				c.sendMessage("You build a Tree.");
				c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13411, c.absX, c.absY, 1, 10);
					c.getPA().addSkillXP(400, conSkill);
					c.buryDelay = System.currentTimeMillis();
					this.stop();
									} else {
				c.sendMessage("You don't have the required materials!");
				c.sendMessage("You need, one hammer and 3 normal logs.");
				this.stop();
				}
                        this.stop();
								if(c.disconnected) {
			this.stop();
			return;
			}
                }
              
                });
   
}

	public static void conTree2(final Client c) {
			if(c.isInPrivCon()) {
		c.sendMessage("You need to be in the construction skilling area first!");
		return;
		}
						if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[23] < 5) {
				c.sendMessage("You need a level 5 Construction to do that.");
				return;
			}
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
							if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				this.stop();
				return;
			}
			if(c.playerLevel[23] < 5) {
				c.sendMessage("You need a level 5 Construction to do that.");
				this.stop();
				return;
			}
					if (c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(1511, 1)) {
				c.getItems().deleteItem2(1511, 1);
				c.getItems().deleteItem2(1511, 1);
				c.getItems().deleteItem2(1511, 1);
				c.sendMessage("You build a Tree.");
				c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13411, c.absX, c.absY, 2, 10);
					c.getPA().addSkillXP(400, conSkill);
					this.stop();
									} else {
				c.sendMessage("You don't have the required materials!");
				c.sendMessage("You need, one hammer and 3 normal logs.");
				c.buryDelay = System.currentTimeMillis();
				this.stop();
				}
								if(c.disconnected) {
			this.stop();
			return;
			}
                }
              
                });
   
}

	public static void conTree3(final Client c) {
			if(c.isInPrivCon()) {
		c.sendMessage("You need to be in the construction skilling area first!");
		return;
		}
				if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[23] < 5) {
				c.sendMessage("You need a level 5 Construction to do that.");
				return;
			}
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
					if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				this.stop();
				return;
			}
			if(c.playerLevel[23] < 5) {
				c.sendMessage("You need a level 5 Construction to do that.");
				this.stop();
				return;
			}
					if (c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(1511, 1)) {
				c.getItems().deleteItem2(1511, 1);
				c.getItems().deleteItem2(1511, 1);
				c.getItems().deleteItem2(1511, 1);
				c.sendMessage("You build a Tree.");
				c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13411, c.absX, c.absY, 3, 10);
					c.getPA().addSkillXP(400, conSkill);
					c.buryDelay = System.currentTimeMillis();
					this.stop();
									} else {
				c.sendMessage("You don't have the required materials!");
				c.sendMessage("You need, one hammer and 3 normal logs.");
				this.stop();
				}
                        this.stop();
								if(c.disconnected) {
			this.stop();
			return;
			}
                }
              
                });
   
}

	public static void conTree4(final Client c) {
			if(c.isInPrivCon()) {
		c.sendMessage("You need to be in the construction skilling area first!");
		return;
		}
						if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				return;
			}
			if(c.playerLevel[23] < 5) {
				c.sendMessage("You need a level 5 Construction to do that.");
			return;
			}
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
							if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				this.stop();
				return;
			}
			if(c.playerLevel[23] < 5) {
				c.sendMessage("You need a level 5 Construction to do that.");
				this.stop();
				return;
			}
					if (c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(1511, 1) && c.getItems().playerHasItem(1511, 1)) {
				c.getItems().deleteItem2(1511, 1);
				c.getItems().deleteItem2(1511, 1);
				c.getItems().deleteItem2(1511, 1);
				c.sendMessage("You build a Tree.");
				c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13411, c.absX, c.absY, 4, 10);
					c.getPA().addSkillXP(400, conSkill);
					c.buryDelay = System.currentTimeMillis();
					this.stop();
									} else {
				c.sendMessage("You don't have the required materials!");
				c.sendMessage("You need, one hammer and 3 normal logs.");
				this.stop();
				}
                        this.stop();
								if(c.disconnected) {
			this.stop();
			return;
			}
                }
              
                });
   
}

// BOOKCASE

	public static void conBook(final Client c) {
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
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
				if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				this.stop();
				return;
			}
			if(c.playerLevel[23] < 25) {
				c.sendMessage("You need a level 26 Construction to do that.");
				this.stop();
				return;
			}
			if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
				c.getItems().deleteItem2(1539, 15);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.sendMessage("You build a Bookcase.");
				c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13598, c.absX, c.absY, 1, 10);
					c.getPA().addSkillXP(2500, conSkill);
					c.buryDelay = System.currentTimeMillis();
					this.stop();
									} else {
				c.sendMessage("You don't have the required materials!");
				c.sendMessage("You need, one hammer, 15 steel nails and 3 oak planks.");
				this.stop();
				}
                        this.stop();
								if(c.disconnected) {
			this.stop();
			return;
			}
                }
              
                });
   
}

	public static void conBook2(final Client c) {
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
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
				if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				this.stop();
				return;
			}
			if(c.playerLevel[23] < 25) {
				c.sendMessage("You need a level 26 Construction to do that.");
				this.stop();
				return;
			}
			if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
				c.getItems().deleteItem2(1539, 15);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.sendMessage("You build a Bookcase.");
				c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13598, c.absX, c.absY, 2, 10);
					c.getPA().addSkillXP(2500, conSkill);
					c.buryDelay = System.currentTimeMillis();
					this.stop();
									} else {
				c.sendMessage("You don't have the required materials!");
				c.sendMessage("You need, one hammer, 15 steel nails and 3 oak planks.");
				this.stop();
				}
                        this.stop();
								if(c.disconnected) {
			this.stop();
			return;
			}
                }
              
                });
   
}

	public static void conBook3(final Client c) {
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
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
				if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				this.stop();
				return;
			}
			if(c.playerLevel[23] < 25) {
			c.sendMessage("You need a level 26 Construction to do that.");
				this.stop();
				return;
			}
			if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
				c.getItems().deleteItem2(1539, 15);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.sendMessage("You build a Bookcase.");
				c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13598, c.absX, c.absY, 3, 10);
					c.getPA().addSkillXP(2500, conSkill);
					c.buryDelay = System.currentTimeMillis();
					this.stop();
									} else {
				c.sendMessage("You don't have the required materials!");
				c.sendMessage("You need, one hammer, 15 steel nails and 3 oak planks.");
				this.stop();
				}
                        this.stop();
								if(c.disconnected) {
			this.stop();
			return;
			}
                }
              
                });
   
}

	public static void conBook4(final Client c) {
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
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
				if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				this.stop();
				return;
			}
			if(c.playerLevel[23] < 25) {
				c.sendMessage("You need a level 26 Construction to do that.");
				this.stop();
				return;
			}
			if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
				c.getItems().deleteItem2(1539, 15);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.sendMessage("You build a Bookcase.");
				c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13598, c.absX, c.absY, 4, 10);
					c.getPA().addSkillXP(2500, conSkill);
					c.buryDelay = System.currentTimeMillis();
					this.stop();
									} else {
				c.sendMessage("You don't have the required materials!");
				c.sendMessage("You need, one hammer, 15 steel nails and 3 oak planks.");
				this.stop();
				}
                        this.stop();
								if(c.disconnected) {
			this.stop();
			return;
			}
                }
              
                });
   
}

//GREENMANS ALE THING


	public static void conAle(final Client c) {
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
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
				if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				this.stop();
				return;
			}
			if(c.playerLevel[23] < 28) {
				c.sendMessage("You need a level 29 Construction to do that.");
				this.stop();
				return;
			}
			if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
				c.getItems().deleteItem2(1539, 15);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.sendMessage("You build a Greenman's ale.");
				c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13571, c.absX, c.absY, 1, 10);
					c.getPA().addSkillXP(3000, conSkill);
					c.buryDelay = System.currentTimeMillis();
					this.stop();
									} else {
				c.sendMessage("You don't have the required materials!");
				c.sendMessage("You need, one hammer, 15 steel nails and 2 oak planks.");
				this.stop();
				}
                        this.stop();
								if(c.disconnected) {
			this.stop();
			return;
			}
                }
              
                });
   
}

	public static void conAle2(final Client c) {
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
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
				if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				this.stop();
				return;
			}
			if(c.playerLevel[23] < 28) {
				c.sendMessage("You need a level 29 Construction to do that.");
				this.stop();
				return;
			}
			if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
				c.getItems().deleteItem2(1539, 15);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.sendMessage("You build a Greenman's ale.");
				c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13571, c.absX, c.absY, 2, 10);
					c.getPA().addSkillXP(3000, conSkill);
					c.buryDelay = System.currentTimeMillis();
					this.stop();
									} else {
				c.sendMessage("You don't have the required materials!");
				c.sendMessage("You need, one hammer, 15 steel nails and 2 oak planks.");
				this.stop();
				}
                        this.stop();
								if(c.disconnected) {
			this.stop();
			return;
			}
                }
              
                });
   
}

	public static void conAle3(final Client c) {
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
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
				if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				this.stop();
				return;
			}
			if(c.playerLevel[23] < 28) {
				c.sendMessage("You need a level 29 Construction to do that.");
				this.stop();
				return;
			}
			if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
				c.getItems().deleteItem2(1539, 15);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.sendMessage("You build a Greenman's ale.");
				c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13571, c.absX, c.absY, 3, 10);
					c.getPA().addSkillXP(3000, conSkill);
					c.buryDelay = System.currentTimeMillis();
					this.stop();
									} else {
				c.sendMessage("You don't have the required materials!");
				c.sendMessage("You need, one hammer, 15 steel nails and 2 oak planks.");
				this.stop();
				}
                        this.stop();
								if(c.disconnected) {
			this.stop();
			return;
			}
                }
              
                });
   
}

	public static void conAle4(final Client c) {
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
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
				if(!c.getItems().playerHasItem(2347, 1)) {
				c.sendMessage("You need a hammer to do that.");
				this.stop();
				return;
			}
			if(c.playerLevel[23] < 28) {
				c.sendMessage("You need a level 29 Construction to do that.");
				this.stop();
				return;
			}
			if (c.getItems().playerHasItem(1539, 15) && c.getItems().playerHasItem(8778, 1) && c.getItems().playerHasItem(8778, 1)) {
				c.getItems().deleteItem2(1539, 15);
				c.getItems().deleteItem2(8778, 1);
				c.getItems().deleteItem2(8778, 1);
				c.sendMessage("You build a Greenman's ale.");
				c.getPA().closeAllWindows();
					c.startAnimation(898);
					c.getPA().checkObjectSpawn(13571, c.absX, c.absY, 4, 10);
					c.getPA().addSkillXP(3000, conSkill);
					c.buryDelay = System.currentTimeMillis();
					this.stop();
									} else {
				c.sendMessage("You don't have the required materials!");
				c.sendMessage("You need, one hammer, 15 steel nails and 2 oak planks.");
				this.stop();
				}
                        this.stop();
								if(c.disconnected) {
			this.stop();
			return;
			}
                }
              
                });
   
}

}