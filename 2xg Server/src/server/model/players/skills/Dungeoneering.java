package server.model.players.skills;

/**
* @author Monsterray REDONE
* Dungeoneering Minigame/Skill 
*/

import java.util.HashMap;

import server.Server;
import server.model.players.Client;
import server.task.Task;

public class Dungeoneering {

  	public static HashMap<Client, Integer> gameRoom = new HashMap<Client, Integer>();     
  
  	private Client c;
  	public Dungeoneering(Client Client) {
		this.c = Client;
  	}
	
	public static boolean hasSpawnedNPCs4 = false;// cheaphax for npcs spawning
	public static boolean hasSpawnedNPCs = false;// cheaphax for npcs spawning
	public static boolean hasSentMessage = false; // cheaphax for message spam
	public static boolean hasSpawnedNPCswave2 = false; // cheaphax for message spam
	public static boolean hasSpawnedNPCswave3 = false; // cheaphax for message spam

  	//public int[] startItems = new int[] {1725,1127,1079,5698,4587,10828,7462,6568,3105,8850};
	
	public void setDaBooleans() {
	hasSentMessage = false;
	hasSpawnedNPCs = false;
	hasSpawnedNPCswave2 = false;
	hasSpawnedNPCswave3 = false;
	hasSpawnedNPCs4 = false;
	}
	public static void spawnDungNpcWave2(final Client c) {
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
		if(!hasSpawnedNPCswave2) {
					if(c.DUfood1 < 3) {
			c.getItems().addItem(391, 1);
			c.DUfood1 += 5;
			c.SaveGame();
			}
			c.juststarted = false;
			Server.npcHandler.spawnDungNpcs(c, 4477, 3255, 9333, c.playerId * 4, 0, 140, 24, 240, 230, true, false); // mad mummy
			//c.getItems().addItem(391, 1);
				c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>Guardian's Spawned (Wave 2)!");
				hasSpawnedNPCswave2 = true;
				c.needstorelog = true;
                        this.stop();
			if(c.disconnected) {
			this.stop();
			return;
			}
                }
				}
              
                });
   }	
   public static void redostartfloor1(Client c) {
   startfloor1(c);
   }
   public static void spawnDungNpcWave4(final Client c) {
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
		if(!hasSpawnedNPCswave2) {
					if(c.DUfood3 < 3) {
			c.getItems().addItem(391, 2);
			c.DUfood3 += 5;
			c.SaveGame();
			}
			c.juststarted = false;
		//	Server.npcHandler.spawnDungNpcs(c, 4477, 3255, 9333, 4, 0, 140, 24, 240, 230, true, false); // mad mummy
				c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>Enter the portal!");
				c.dungn = 8;
				hasSpawnedNPCswave2 = true;
				c.needstorelog = true;
                        this.stop();
			if(c.disconnected) {
			this.stop();
			return;
			}
                }
				}
              
                });
   }
   public static void spawnDungNpcWave3(final Client c) {
       Server.getTaskScheduler().addEvent(new Task(1, false) {
		public void execute() {
		if(!hasSpawnedNPCswave3) {
					if(c.DUfood2 < 3) {
			c.getItems().addItem(391, 2);
			c.DUfood2 += 5;
			c.SaveGame();
			}
			Server.npcHandler.bosses();
			Server.npcHandler.spawnDungNpcs(c, 3622, 3238, 9331, c.playerId * 4, 0, 100, 5, 30, 40, true, false); // skele
Server.npcHandler.spawnDungNpcs(c, 3067, 3238, 9333, c.playerId * 4, 0, 170, 30, 230, 190, true, false); // leon
			c.juststarted = false;
				c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>Guardian's Spawned (Wave 3)!");
				hasSpawnedNPCswave3 = true;
				c.needstorelog = true;
                        this.stop();
			if(c.disconnected) {
			this.stop();
			return;
			}
                }
				}
              
                });
   }
public static void spawnDungNpcWave1(final Client c) {
Server.getTaskScheduler().addEvent(new Task(1, false) {
public void execute() {
if(!hasSpawnedNPCs) {
c.juststarted = false;
Server.npcHandler.spawnDungNpcs(c, 3622, 3239, 9326, c.playerId * 4, 0, 100, 5, 30, 40, true, false);
Server.npcHandler.spawnDungNpcs(c, 3622, 3239, 9326, c.playerId * 4, 0, 140, 5, 30, 40, true, false);
Server.npcHandler.spawnDungNpcs(c, 3622, 3243, 9327, c.playerId * 4, 0, 100, 5, 30, 40, true, false);
Server.npcHandler.spawnDungNpcs(c, 3622, 3234, 9326, c.playerId * 4, 0, 120, 5, 30, 40, true, false);
c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>Guardian's Spawned!");
hasSpawnedNPCs = true;
c.needstorelog = true;
this.stop();
if(c.disconnected) {
this.stop();
return;
}
}
}            
});
}

public static void spawnWave1CauseOfRelog(final Client c) {
Server.getTaskScheduler().addEvent(new Task(1, false) {
public void execute() {
if(!hasSpawnedNPCs) {
c.juststarted = false;
Server.npcHandler.spawnDungNpcs(c, 3622, 3239, 9326, c.playerId * 4, 0, 100, 5, 30, 40, true, false);
Server.npcHandler.spawnDungNpcs(c, 3622, 3239, 9326, c.playerId * 4, 0, 140, 5, 30, 40, true, false);
Server.npcHandler.spawnDungNpcs(c, 3622, 3243, 9327, c.playerId * 4, 0, 100, 5, 30, 40, true, false);
c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>You logged out with 1 kill, spawned 3 Guardians!");
hasSpawnedNPCs = true;
this.stop();
if(c.disconnected) {
this.stop();
return;
}
}
}            
});
}

public static void spawnWave2CauseOfRelog(final Client c) {
Server.getTaskScheduler().addEvent(new Task(1, false) {
public void execute() {
if(!hasSpawnedNPCs) {
c.juststarted = false;
Server.npcHandler.spawnDungNpcs(c, 3622, 3239, 9326, c.playerId * 4, 0, 100, 5, 30, 40, true, false);
Server.npcHandler.spawnDungNpcs(c, 3622, 3239, 9326, c.playerId * 4, 0, 140, 5, 30, 40, true, false);
c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>You logged out with 2 kills, spawned 2 Guardians!");
hasSpawnedNPCs = true;
this.stop();
if(c.disconnected) {
this.stop();
return;
}
}
}            
});
}


public static void spawnWave3CauseOfRelog(final Client c) {
Server.getTaskScheduler().addEvent(new Task(1, false) {
public void execute() {
if(!hasSpawnedNPCs) {
c.juststarted = false;
Server.npcHandler.spawnDungNpcs(c, 3622, 3239, 9326, c.playerId * 4, 0, 100, 5, 30, 40, true, false);
c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>You logged out with 3 kills, spawned 1 Guardian!");
hasSpawnedNPCs = true;
this.stop();
if(c.disconnected) {
this.stop();
return;
}
}
}            
});
}
public static void spawnDungNpcWave7CauseOfRelog(final Client c) {
Server.getTaskScheduler().addEvent(new Task(1, false) {
public void execute() {
if(!hasSpawnedNPCs) {
Server.npcHandler.bosses();
c.juststarted = false;
Server.npcHandler.spawnDungNpcs(c, 3067, 3238, 9333, c.playerId * 4, 0, 170, 30, 230, 190, true, false); // leon
c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>You logged out with 6 kills, spawned 1 Boss!");
hasSpawnedNPCs = true;
c.dungn = 6;
this.stop();
if(c.disconnected) {
this.stop();
return;
}
}
}            
});
}

public static void spawnFinalWave1(final Client c) {
Server.getTaskScheduler().addEvent(new Task(1, false) {
public void execute() {
if(!hasSpawnedNPCs4) {
Server.npcHandler.bosses();
c.juststarted = false;
Server.npcHandler.spawnDungNpcs(c, 10040, 3027, 5234, c.playerId * 4, 1, 150, 30, 240, 200, true, false); // ice bone guy
Server.npcHandler.spawnDungNpcs(c, 3200, 3016, 5234, c.playerId * 4, 1, 80, 30, 150, 200, true, false); // chaos ele
c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>Spawned Final Bosses! Kill them!");
hasSpawnedNPCs4 = true;
handleChaosEleTeleporting(c);
this.stop();
if(c.disconnected) {
this.stop();
return;
}
}
}            
});
}

public static void handleChaosEleTeleporting(final Client c) {
Server.getTaskScheduler().addEvent(new Task(8, false) {
public void execute() {
c.getPA().movePlayer(3028, 5232, c.playerId * 4);
c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>The Chaos Elemental teleports you into the middle!");
this.stop();
if(c.disconnected) {
this.stop();
return;
}
}            
});
}
public static void handleRewardEventCheck(final Client c) {
Server.getTaskScheduler().addEvent(new Task(1, false) {
public void execute() {
if(c.dungn == 10) {
c.getDungeoneering().handleReward(c);
this.stop();
if(c.disconnected) {
this.stop();
return;
}
if (!c.InDung() && !c.inDungBossRoom()) {
c.dungn = 0;
c.getPA().moveDung();
c.sendMessage("ANTI SMUGGLING: Your not in Dungeoneering!!");
this.stop();
}
}
}            
});
}

public void handleLogOut(Client c) {// Handles logging out from dung
if (c.dungn == 0 ) {
c.getPA().resetDung();
}
if (c.dungn == 1) {
c.getPA().movePlayer(3237, 9327, c.playerId * 4);
Server.npcHandler.destroyDungNpcs(c);
c.isSkulled = true;
c.getPA().refreshSkill(5);
c.InDung = true;
spawnWave1CauseOfRelog(c);
}
if (c.dungn == 2) {
c.getPA().movePlayer(3237, 9327, c.playerId * 4);
c.isSkulled = true;
c.getPA().refreshSkill(5);
c.InDung = true;
Server.npcHandler.destroyDungNpcs(c);
spawnWave2CauseOfRelog(c);
}
if (c.dungn == 3) {
c.getPA().movePlayer(3237, 9327, c.playerId * 4);
Server.npcHandler.destroyDungNpcs(c);
c.isSkulled = true;
c.getPA().refreshSkill(5);
c.InDung = true;
spawnWave3CauseOfRelog(c);
}
if (c.dungn == 4) {
c.getPA().movePlayer(3261, 9329, c.playerId * 4);
c.sendMessage("Enter the Door");
Server.npcHandler.destroyDungNpcs(c);
c.isSkulled = true;
c.getPA().refreshSkill(5);
c.InDung = true;
}
if (c.dungn == 5) {
c.getPA().movePlayer(3244, 9333, c.playerId * 4);
Server.npcHandler.destroyDungNpcs(c);
c.isSkulled = true;
c.getPA().refreshSkill(5);
c.InDung = true;
spawnDungNpcWave3(c);
}
if (c.dungn == 6) {
c.getPA().movePlayer(3233, 9332, c.playerId * 4);
Server.npcHandler.destroyDungNpcs(c);
c.isSkulled = true;
c.getPA().refreshSkill(5);
c.InDung = true;
spawnDungNpcWave7CauseOfRelog(c);
}
if (c.dungn == 7) {
c.getPA().movePlayer(3219, 9327, c.playerId * 4);
Server.npcHandler.destroyDungNpcs(c);
c.isSkulled = true;
c.getPA().refreshSkill(5);
c.InDung = true;
c.sendMessage("Enter the door!");
c.dungn = 7;
}
if (c.dungn == 8 || c.dungn == 9) {
c.getPA().movePlayer(3022, 5234, c.playerId * 4);
Server.npcHandler.destroyDungNpcs(c);
c.isSkulled = true;
c.getPA().refreshSkill(5);
c.InDung = true;
c.dungn = 7;
spawnFinalWave1(c);
}
}
	

public static void startfloor1(Client c) {
/*for (int j = 0; j < c.playerItems.length; j++) {
if(c.playerItems[j] > 0) {
c.sendMessage("Bank all your items first!!");
c.getPA().closeAllWindows();
return;
}*/
c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
c.isSkulled = true;
int slot = -1;
c.getPA().refreshSkill(5);
c.InDung = true;
hasSpawnedNPCs = false;
c.getPA().showInterface(17050);
c.DUfood1 = 0;
c.DUfood2 = 0;
c.DUfood3 = 0;
c.getPA().movePlayer(3233, 9315, c.playerId * 4);
//c.getPA().removeAllItems();
c.IsIDung = 1;
//c.getItems().deleteAllItems();
c.isChoosingDung = true;
Server.npcHandler.destroyDungNpcs(c);
System.out.println("Teled to dung");
		Client o = (Client) Server.playerHandler.players[c.duelingWith];
			c.getTradeAndDuel().declineDuel();
//			o.getTradeAndDuel().declineDuel();
if(!hasSentMessage == true) {
//c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>All items that were in your inventory have been banked.");
c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>If you get stuck in a wall, type ::enddung !!");
c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>You've entered the Lobby! Please customize your character..");
c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>To start Dungeoneering, enter the door to the north!");
hasSentMessage = true;
c.dungn = 0;
}
}

public void handleReward(Client c) {
		// spawnDungNpcs(c);
		c.dungn = 0;
		c.getPA().writeTabs();
		// c.getPA().addSkillXP((300000), 23);
		c.dungPoints += 100;
		c.antispampush = false;
		c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>All waves completed! Reward; 100 Tokens and 150K Dungeoneering XP!");
		c.playerLevel[1] = c.getPA().getLevelForXP(c.playerXP[1]);
		c.playerLevel[2] = c.getPA().getLevelForXP(c.playerXP[2]);
		c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
		c.playerLevel[4] = c.getPA().getLevelForXP(c.playerXP[4]);
		c.playerLevel[5] = c.getPA().getLevelForXP(c.playerXP[5]);
		c.playerLevel[6] = c.getPA().getLevelForXP(c.playerXP[6]);
		c.getPA().refreshSkill(1);
		c.getPA().refreshSkill(2);
		c.getPA().refreshSkill(3);
		c.getPA().refreshSkill(4);
		c.getPA().refreshSkill(5);
		c.getPA().refreshSkill(6);
		c.getPA().addSkillXP(150000, 24);
		c.getPA().refreshSkill(24);
		c.hasFollower = -1;
		c.specAmount = 10;
		c.getItems().addSpecialBar(c.playerEquipment[c.playerWeapon]);
		c.lastVeng = 0;
		c.vengOn = false;
		c.prayerId = -1;
		c.getPA().closeAllWindows();
		c.getPA().refreshSkill(5);
		c.getPA().refreshSkill(3);
		c.getItems().deleteAllItems();
		c.hasChoosenDung = false;
		c.getDungeoneering().setDaBooleans();
		c.InDung = false;
		c.getPA().movePlayer(2533, 3304, 0);
		c.needstorelog = true;
		c.getPA().resetFollowers();
		c.attackTimer = 10;
		c.getPA().closeAllWindows();
		c.getPA().removeAllItems();
		c.hasChoosenDung = false;
		hasSpawnedNPCs = false;
		return;
	}


	
	public static void startfloor2() {
	}

	public static void handleObject(Client c, int id, int x, int y) {
		switch (id) {
		case 6553:
			if (!c.InDung) {
			c.sendMessage("You cannot use theses objects, teleport home and re-do the Dungeon.");
			return;
			}
							if (c.absX  == 3246 && c.absY == 9333 && c.dungn == 5) { // third door
				c.getPA().movePlayer(3244, 9333, c.playerId * 4);
				spawnDungNpcWave3(c);	
		        } else {
				if(c.absX  == 3246 && c.absY == 9333 && c.dungn != 5) {
				c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>You need to have atleast 5 kills to enter there!");
				}
				}
			if (c.absX  == 3246 && c.absY == 9334 && c.dungn == 5) { // third door
				c.getPA().movePlayer(3244, 9334, c.playerId * 4);
				spawnDungNpcWave3(c);	
		        } else {
				if(c.absX  == 3246 && c.absY == 9334 && c.dungn != 5) {
				c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>You need to have atleast 5 kills to enter there!");
				}
				}
				if (c.absX  == 3260 && c.absY == 9329 && c.dungn == 4) { // second door
				c.getPA().movePlayer(3260, 9331, c.playerId * 4);
				spawnDungNpcWave2(c);	
		        } else {
				if(c.absX  == 3260 && c.absY == 9329 && c.dungn != 4) {
				c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>You need to have atleast 4 kills to enter there!");
				}
				}
			if (c.absX  == 3261 && c.absY == 9329 && c.dungn == 4) { // second door
				c.getPA().movePlayer(3260, 9331, c.playerId * 4);
				spawnDungNpcWave2(c);	
		        } else {
				if(c.absX  == 3261 && c.absY == 9329 && c.dungn != 4) {
				c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>You need to have atleast 4 kills to enter there!");
				}
				}
			if (c.absX  == 3234 && c.absY == 9323 && c.dungn == 0) { // first door
			c.getPA().movePlayer(3234, 9325, c.playerId * 4);
			spawnDungNpcWave1(c);		
				}
				if (c.absX  == 3219 && c.absY == 9328 && c.dungn == 7 || c.absX  == 3219 && c.absY == 9328 && c.dungn == 8) { // third door
				c.getPA().movePlayer(3217, 9328, c.playerId * 4);
				spawnDungNpcWave4(c);	
		        } else {
				if(c.absX  == 3219 && c.absY == 9328 && c.dungn != 7 || c.absX  == 3219 && c.absY == 9328 && c.dungn != 7) {
				c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>You need to have atleast 7 kills to enter there!");
				}
				}	
				if (c.absX  == 3219 && c.absY == 9327 && c.dungn == 7 || c.absX  == 3219 && c.absY == 9327 && c.dungn == 8) { // third door
				c.getPA().movePlayer(3217, 9327, c.playerId * 4);
				spawnDungNpcWave4(c);	
		        } else {
				if(c.absX  == 3219 && c.absY == 9327 && c.dungn != 7 || c.absX  == 3219 && c.absY == 9327 && c.dungn != 8) {
				c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>You need to have atleast 7 kills to enter there!");
				}
				}
			break;
			
		case 6555:
			if (!c.InDung()) {
				c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415>You cannot use theses objects, teleport home and re-do the Dungeon.");
				return;
			} else if (c.absX  == 3233 && c.absY == 9323 && c.dungn == 0 ) {
				c.getPA().movePlayer(3233, 9325, c.playerId * 4);
				spawnDungNpcWave1(c);
			}
			break;
			
            		case 6501:
            			if (c.dungn == 14) {
            			c.getDH().sendDialogues(125, 1);
            			} else {
            				c.sendMessage("Go back and kill the NPCS.");
            			}
            		break;
			default:
			
			break;
		
		}	
	}
	

	
    public static boolean Dung(Client player) {
        return gameRoom.containsKey(player);
    }

}