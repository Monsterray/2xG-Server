package server.model.players.packets;

import server.Config;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;
import server.util.Misc;


/**
 * Clicking an item, bury bone, eat food etc
 **/
public class ClickItem implements PacketType {
                      
	public static int flower[] = {2980,2981,2982,2983,2984,2985,2986,2987};
	public static int flowerX = 0;
	public static int flowerY = 0;
	public static int flowerTime = -1;
	public static int flowers = 0;
	
	public int randomflower() {
		return flower[(int)(Math.random()*flower.length)];
	}
	
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int junk = c.getInStream().readSignedWordBigEndianA();
		int itemSlot = c.getInStream().readUnsignedWordA();
		int itemId = c.getInStream().readUnsignedWordBigEndian();
		if (itemId != c.playerItems[itemSlot] - 1) {
			return;
		}
		if (c.getHerblore().checkGrimy(itemId, 0)){
			c.getHerblore().handleHerbClick(itemId);
			return;
		}
		if (c.getFood().isFood(itemId)){
			c.getFood().eat(itemId,itemSlot);
			return;
		}
		//ScriptManager.callFunc("itemClick_"+itemId, c, itemId, itemSlot);
		if (c.getPotions().isPotion(itemId)){
			c.getPotions().handlePotion(itemId,itemSlot);
			return;
		}
		if (c.getPrayer().isBone(itemId)){
			c.getPrayer().buryBone(itemId, itemSlot);
			return;
		}
		if(c.getClue().getClueLevel(itemId) != 0){
			c.getClue().selectRandomClue(itemId);
			return;
		}
		if(c.getClue().getCasketLevel(itemId) != 0){
			c.getClue().getReward(itemId);
			return;
		}
		switch(itemId){
			case 10735:
				c.setSidebarInterface(0, 776);
			break;
			
			case 15098:
				if (System.currentTimeMillis() - c.diceDelay >= 1200) {
					if (c.playerRights == 0) {
						c.sendMessage("You need to be a donator atleast to dice!!");
						return;
					}
					if(!c.inAclan) {
						c.sendMessage("You need to be in a clan to do this.");
					} else {
						c.startAnimation(11900);
						c.gfx0(2075);
						Server.clanChat.messageToClan("<shad=15695415>"+c.playerName+" just rolled <shad=16112652>" +Misc.random(100)+ "<shad=15695415> on the percentile dice.", c.clanId);
						c.diceDelay = System.currentTimeMillis();
					}
				}
			break;
			
			case 15084:
				c.getDH().sendDialogues(106, 4289);
			break;
			
			case 6722:
				c.sendMessage("You Redeem Your Spin Token For one spin!");
				c.spinsLe += 1;
			break;
			
			case 11949:
				c.makesnow();
				c.stopMovement();
			break;
		
			case 13495:
				if(c.AuraEquiped > 1){
					c.getItems().addItem(c.AuraEquiped, 1);
				}
				c.AuraEquiped = 13495;
				c.getPA().sendFrame34(c.AuraEquiped, 0, 10794, 1);
				c.AuraEffect = 1;
				c.getItems().deleteItem(13495,c.getItems().getItemSlot(13495),1);
			break;
			
			case 6199:
				int mysteryReward = Misc.random(5); 
				if (mysteryReward == 1) {
					c.getItems().addItem(1050, 1);
					c.getItems().deleteItem(6199, 1);
					c.sendMessage("Thanks for voting!");
				}else if (mysteryReward == 2) {
					c.getItems().addItem(4083, 1);
					c.getItems().deleteItem(6199, 1);
					c.sendMessage("Thanks for voting!");
				}else if (mysteryReward == 3) {
					c.getItems().addItem(6585, 1);
					c.getItems().deleteItem(6199, 1);
					c.sendMessage("Thanks for voting!");
				}else if (mysteryReward == 4) {
					c.getItems().addItem(1038, 1);
					c.getItems().addItem(1040, 1);
					c.getItems().addItem(1042, 1);
					c.getItems().addItem(1044, 1);
					c.getItems().addItem(1046, 1);
					c.getItems().addItem(1048, 1);
					c.getItems().deleteItem(6199, 1);
					c.sendMessage("Thanks for voting!");
				}else if (mysteryReward == 5) {
					c.getItems().addItem(1055, 1);
					c.getItems().deleteItem(6199, 1);
					c.sendMessage("Thanks for voting!");
				}
			break;
			
			case 9721: //overload instructions
				c.getPA().showInterface(6965);
				c.getPA().sendFrame126("@red@~ Overload Instruction Manual ~",6968);
				c.getPA().sendFrame126("",6969);
				c.getPA().sendFrame126("@gre@Step 1: @cya@Get a Herblore level of 99.",6970);
				c.getPA().sendFrame126("@gre@Step 2: @cya@Have all Extreme Potions ",6971);
				c.getPA().sendFrame126("@cya@In your inventory, along with a",6972);
				c.getPA().sendFrame126("@gre@Step 3: @cya@Cleaned torstol ,use the",6973);
				c.getPA().sendFrame126("@cya@ Torstol on an extreme potion.",6974);
				c.getPA().sendFrame126("@gre@Step 4: @cya@You now have an overload!",6975);
				c.getPA().sendFrame126("",6976);
				c.getPA().sendFrame126("",6977);
				c.getPA().sendFrame126("",6978);
				c.getPA().sendFrame126("",6979);
				c.getPA().sendFrame126("",6980);
			break;
			
			case 299: //mithril seeds
				flowers = randomflower();
				flowerX += c.absX;
				flowerY += c.absY;
				c.getPA().object(flowers, c.absX, c.absY, 0, 10);
				c.sendMessage("You plant the seed...");
				c.getItems().deleteItem(299, 1);
				c.getPA().walkTo(-1,0); 
				c.getDH().sendDialogues(9999, -1);   
			break;
			
			case 1856:
				c.getPA().showInterface(6965);
				c.getPA().sendFrame126("@red@~ Duel Arena - Known Bugs ~",6968);
				c.getPA().sendFrame126("",6969);
				c.getPA().sendFrame126("@gre@1.@cya@Dueling is at your own risk.",6970);
				c.getPA().sendFrame126("@cya@If you loose items to players/bugs",6971);
				c.getPA().sendFrame126("@cya@ YOU WILL NOT GET YOUR ITEMS BACK.",6972);
				c.getPA().sendFrame126("@gre@2.@cya@FORFEITING DOES NOT WORK!!",6973);
				c.getPA().sendFrame126("@gre@3.@cya@FUN WEAPONS ARE DISABLED",6974);
				c.getPA().sendFrame126("@gre@4.@cya@ROCKTAILS COUNTS AS FOOD! BE CAREFUL",6975);
				c.getPA().sendFrame126("",6976);
				c.getPA().sendFrame126("",6977);
				c.getPA().sendFrame126("",6978);
				c.getPA().sendFrame126("",6979);
				c.getPA().sendFrame126("",6980);
			break;
			
			case 4447:
				c.getPA().addSkillXP(3000, 24);
				c.sendMessage("You rub the lamp and feel yourself further in the arts of dungeoneering.");
				c.getItems().deleteItem(4447, 1);	
			break;
			
			case 15262:
				c.getItems().addItem(18016, 10000);
				c.getItems().deleteItem(15262, 1);
			break;
			
			case 19775:
				c.playerLevel[0] = 99;
				c.playerLevel[2] = 99;
				c.playerLevel[3] = 99;
				c.playerLevel[4] = 99;
				c.playerLevel[6] = 99;
				c.playerXP[0] = c.getPA().getXPForLevel(100);
				c.playerXP[2] = c.getPA().getXPForLevel(100);
				c.playerXP[3] = c.getPA().getXPForLevel(100);
				c.playerXP[4] = c.getPA().getXPForLevel(100);
				c.playerXP[6] = c.getPA().getXPForLevel(100);
				c.getPA().refreshSkill(0);
				c.getPA().refreshSkill(2);
				c.getPA().refreshSkill(3);
				c.getPA().refreshSkill(4);
				c.getPA().refreshSkill(6);
				c.getItems().deleteItem(19775, 1);
				c.sendMessage("You use the MASTER combat Ring");
			break;
			
			case 15272:
				if (System.currentTimeMillis() - c.foodDelay >= 1500 && c.playerLevel[3] > 0) {
					c.getCombat().resetPlayerAttack();
					c.attackTimer += 2;
					c.startAnimation(829);
					c.getItems().deleteItem(15272, 1);
					if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
						c.playerLevel[3] += 23;
						if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
							c.playerLevel[3] = c.getLevelForXP(c.playerXP[3] + 10);
					}
					c.foodDelay = System.currentTimeMillis();
					c.getPA().refreshSkill(3);
					c.sendMessage("You eat the Rocktail.");
				}
				//c.playerLevel[3] += 10;
				if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])*1.11 + 1)) {
					c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*1.11);
				}
				c.getPA().refreshSkill(3);
			break;
			
			case 2528:
				c.lampfix = 5;
				c.sendMessage("You rub the 750K xp Lamp.");
				c.getPA().showInterface(35000);
			break;
			
			case 11850:
				c.getItems().deleteItem(11850,1);
				c.getItems().addItem(4724,1);
				c.getItems().addItem(4726,1);
				c.getItems().addItem(4728,1);
				c.getItems().addItem(4730,1);
			break;
			
			case 11852:
				c.getItems().deleteItem(11852,1);
				c.getItems().addItem(4732,1);
				c.getItems().addItem(4734,1);
				c.getItems().addItem(4736,1);
				c.getItems().addItem(4738,1);
			break;
			
			case 11854:
				c.getItems().deleteItem(11854,1);
				c.getItems().addItem(4745,1);
				c.getItems().addItem(4747,1);
				c.getItems().addItem(4749,1);
				c.getItems().addItem(4751,1);
			break;
			
			case 11856:
				c.getItems().deleteItem(11856,1);
				c.getItems().addItem(4732,1);
				c.getItems().addItem(4734,1);
				c.getItems().addItem(4736,1);
				c.getItems().addItem(4738,1);
			break;
			
			case 11848:
				c.getItems().deleteItem(11848,1);
				c.getItems().addItem(4716,1);
				c.getItems().addItem(4718,1);
				c.getItems().addItem(4720,1);
				c.getItems().addItem(4722,1);
			break;
			
			case 11846:
				c.getItems().deleteItem(11846,1);
				c.getItems().addItem(4708,1);
				c.getItems().addItem(4710,1);
				c.getItems().addItem(4712,1);
				c.getItems().addItem(4714,1);
			break;
			
			case 5070: // BIRD'S NEST
				c.getItems().addItem(995, 100000);
				c.getItems().deleteItem(5070, 1);
				c.sendMessage("The nest contains 100k coins!");
			break;
			
			case 9719:
				c.getPA().showInterface(6965);
				c.getPA().sendFrame126("@red@~ Extremes Instruction Manual ~",6968);
				c.getPA().sendFrame126("",6969);
				c.getPA().sendFrame126("@gre@Step 1: @cya@Get a Herblore level of 80.",6970);
				c.getPA().sendFrame126("@gre@Step 2: @cya@Have a super potion (3) and ",6971);
				c.getPA().sendFrame126("@cya@ the required cleaned herb.",6972);
				c.getPA().sendFrame126("@gre@Step 3: @cya@Use the herb on the super potion",6973);
				c.getPA().sendFrame126("@cya@ You will now get a extreme potion (3)",6974);
				c.getPA().sendFrame126("@gre@Step 4: @cya@Use RS Wiki for herbs req's",6975);
				c.getPA().sendFrame126("",6976);
				c.getPA().sendFrame126("",6977);
				c.getPA().sendFrame126("",6978);
				c.getPA().sendFrame126("",6979);
				c.getPA().sendFrame126("",6980);
			break;
			
			case 8013:
				if (c.inWarriorG()) {
					return;
				}
				if(c.inPits) {
					c.sendMessage("You can't teleport during Fight Pits.");
					return;
				}
				if(c.getPA().inPitsWait()) {
					c.sendMessage("You can't teleport during Fight Pits.");
					return;
				}
				if(c.Jail == true){
	                c.sendMessage("You can't teleport out of Jail, you MORON!");
				} 
				if(c.inJail() && c.Jail == true) {
	                c.sendMessage("You can't teleport out oaf Jail, you MORON!");
				} 
				if(c.duelStatus == 5) {
					c.sendMessage("You can't teleport during a duel!");
					return;
				}
				if(c.InDung) {
					c.sendMessage("You can't teleport from here");
					return;
				}
				if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
					c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
					return;
				}
				if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
					c.sendMessage("You are teleblocked and can't teleport.");
					return;
				}
				if(c.inNomad()) {
					c.sendMessage("You can't teleport during Nomad Minigame");
					return;
				}
				if(c.inGoblin()) {
					c.sendMessage("You can't teleport during Goblin Minigame");
					return;
				}
			   c.getPA().teleTabTeleport(2059, 3143, 4, "house");
			   c.sendMessage("You teleport into you're custom place..");
			   c.getItems().deleteItem(8013,c.getItems().getItemSlot(8013),1);
			break;
			
			case 15707:
				c.getPA().startTeleport(2533, 3304, 0, "dungtele");
				c.sendMessage("Your Ring of Kinship takes you to Dungeoneering.");
				//c.sendMessage("Dungeoneering is currently Disabled! We're adding new dung atm.");
			break;
			
			case 18821:
				c.getPA().startTeleport(2533, 3304, 0, "dungtele");
				c.sendMessage("Your Ring of Kinship takes you to Dungeoneering.");
				// c.sendMessage("Dungeoneering is currently Disabled! We're adding new dung atm.");
			break;
			
			case 18817:
				c.getPA().startTeleport(2533, 3304, 0, "dungtele");
				//c.sendMessage("Your Ring of Kinship takes you to Dungeoneering.");
				// c.sendMessage("Dungeoneering is currently Disabled! We're adding new dung atm.");
			break;
				 
			case 18823:
				c.getPA().startTeleport(2533, 3304, 0, "dungtele");
				//c.sendMessage("Your Ring of Kinship takes you to Dungeoneering.");
				//c.sendMessage("Dungeoneering is currently Disabled! We're adding new dung atm.");
			break;
			
			case 8007:
				if(c.inPits) {
					c.sendMessage("You can't teleport during Fight Pits.");
					return;
				}
				if(c.getPA().inPitsWait()) {
					c.sendMessage("You can't teleport during Fight Pits.");
					return;
				}
				if(c.duelStatus == 5) {
					c.sendMessage("You can't teleport during a duel!");
					return;
				}
				if(c.Jail == true){
	                c.sendMessage("You can't teleport out of Jail!");
				} 
				if(c.inJail() && c.Jail == true) {
	                c.sendMessage("You can't teleport out oaf Jail!");
				} 
				if(c.InDung) {
					c.sendMessage("You can't teleport from here moron");
					return;
				}
				if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
					c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
					return;
				}
				if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
					c.sendMessage("You are teleblocked and can't teleport.");
					return;
				}
				if(c.inNomad()) {
					c.sendMessage("You can't teleport during Nomad Minigame");
					return;
				}
				if (c.inWarriorG()) {
				return;
				}
				if(c.inGoblin()) {
					c.sendMessage("You can't teleport during Goblin Minigame");
					return;
				}
				c.getPA().teleTabTeleport(3216,3424,0, "teleTab");
				c.getItems().deleteItem(8007,c.getItems().getItemSlot(8007),1);
			break;
			
			case 8008:
				if (c.inWarriorG()) {
					return;
				}
				if(c.inPits) {
					c.sendMessage("You can't teleport during Fight Pits.");
					return;
				}
				if(c.getPA().inPitsWait()) {
					c.sendMessage("You can't teleport during Fight Pits.");
					return;
				}
				if(c.Jail == true){
	                c.sendMessage("You can't teleport out of Jail!");
				} 
				if(c.inJail() && c.Jail == true) {
	                c.sendMessage("You can't teleport out oaf Jail!");
				} 
				if(c.duelStatus == 5) {
					c.sendMessage("You can't teleport during a duel!");
					return;
				}
				if(c.InDung) {
					c.sendMessage("You can't teleport from here moron");
					return;
				}
				if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
					c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
					return;
				}
				if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
					c.sendMessage("You are teleblocked and can't teleport.");
					return;
				}
				if(c.inNomad()) {
					c.sendMessage("You can't teleport during Nomad Minigame");
					return;
				}
				if(c.inGoblin()) {
					c.sendMessage("You can't teleport during Goblin Minigame");
					return;
				}
				c.getPA().teleTabTeleport(3221, 3217, 0, "teleTab");
				c.getItems().deleteItem(8008,c.getItems().getItemSlot(8008),1);
			break;
			
			case 8009:
				if (c.inWarriorG()) {
					return;
				}
				if(c.inPits) {
					c.sendMessage("You can't teleport during Fight Pits.");
					return;
				}
				if(c.getPA().inPitsWait()) {
					c.sendMessage("You can't teleport during Fight Pits.");
					return;
				}
				if(c.Jail == true){
	                c.sendMessage("You can't teleport out of Jail!");
				} 
				if(c.inJail() && c.Jail == true) {
	                c.sendMessage("You can't teleport out oaf Jail!");
				} 
				if(c.duelStatus == 5) {
					c.sendMessage("You can't teleport during a duel!");
					return;
				}
				if(c.InDung) {
					c.sendMessage("You can't teleport from here moron");
					return;
				}
				if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
					c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
					return;
				}
				if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
					c.sendMessage("You are teleblocked and can't teleport.");
					return;
				}
				if(c.inNomad()) {
					c.sendMessage("You can't teleport during Nomad Minigame");
					return;
				}
				if(c.inGoblin()) {
					c.sendMessage("You can't teleport during Goblin Minigame");
					return;
				}
				c.getPA().teleTabTeleport(2964, 3380, 0, "teleTab");
				c.getItems().deleteItem(8009,c.getItems().getItemSlot(8009),1);
			break;
			
			case 8010:
				if (c.inWarriorG()) {
					return;
				}
				if(c.inPits) {
					c.sendMessage("You can't teleport during Fight Pits.");
					return;
				}
				if(c.getPA().inPitsWait()) {
					c.sendMessage("You can't teleport during Fight Pits.");
					return;
				}
				if(c.Jail == true){
	                c.sendMessage("You can't teleport out of Jail!");
				} 
				if(c.inJail() && c.Jail == true) {
	                c.sendMessage("You can't teleport out oaf Jail!");
				} 
				if(c.duelStatus == 5) {
					c.sendMessage("You can't teleport during a duel!");
					return;
				}
				if(c.InDung) {
					c.sendMessage("You can't teleport from here moron");
					return;
				}
				if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
					c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
					return;
				}
				if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
					c.sendMessage("You are teleblocked and can't teleport.");
					return;
				}
				if(c.inNomad()) {
					c.sendMessage("You can't teleport during Nomad Minigame");
					return;
				}
				if(c.inGoblin()) {
					c.sendMessage("You can't teleport during Goblin Minigame");
					return;
				}
				c.getPA().teleTabTeleport(2756, 3479, 0, "teleTab");
				c.getItems().deleteItem(8010,c.getItems().getItemSlot(8010),1);
			break;
			
			case 8011:
				if (c.inWarriorG()) {
					return;
				}
				if(c.inPits) {
					c.sendMessage("You can't teleport during Fight Pits.");
					return;
				}
				if(c.getPA().inPitsWait()) {
					c.sendMessage("You can't teleport during Fight Pits.");
					return;
				}
				if(c.Jail == true){
	                c.sendMessage("You can't teleport out of Jail!");
				} 
				if(c.inJail() && c.Jail == true) {
	                c.sendMessage("You can't teleport out oaf Jail!");
				} 
				if(c.duelStatus == 5) {
					c.sendMessage("You can't teleport during a duel!");
					return;
				}
				if(c.InDung) {
					c.sendMessage("You can't teleport from here moron");
					return;
				}
				if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
					c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
					return;
				}
				if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
					c.sendMessage("You are teleblocked and can't teleport.");
					return;
				}
				if(c.inNomad()) {
					c.sendMessage("You can't teleport during Nomad Minigame");
					return;
				}
				if(c.inGoblin()) {
					c.sendMessage("You can't teleport during Goblin Minigame");
					return;
				}
				c.getPA().teleTabTeleport(2661, 3306, 0, "teleTab");
				c.foodDelay = System.currentTimeMillis();
				c.getItems().deleteItem(8011,c.getItems().getItemSlot(8011),1);
			break;
			
			case 8012:
				if(c.inPits) {
					c.sendMessage("You can't teleport during Fight Pits.");
					return;
				}
				if(c.getPA().inPitsWait()) {
					c.sendMessage("You can't teleport during Fight Pits.");
					return;
				}
				if(c.Jail == true){
					c.sendMessage("You can't teleport out of Jail!");
				} 
				if(c.inJail() && c.Jail == true) {
	                c.sendMessage("You can't teleport out oaf Jail!");
				} 
				if(c.duelStatus == 5) {
					c.sendMessage("You can't teleport during a duel!");
					return;
				}
				if(c.InDung) {
					c.sendMessage("You can't teleport from here moron");
					return;
				}
				if(c.inWild() && c.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
					c.sendMessage("You can't teleport above level "+Config.NO_TELEPORT_WILD_LEVEL+" in the wilderness.");
					return;
				}
				if(System.currentTimeMillis() - c.teleBlockDelay < c.teleBlockLength) {
					c.sendMessage("You are teleblocked and can't teleport.");
					return;
				}
				if(c.inNomad()) {
					c.sendMessage("You can't teleport during Nomad Minigame");
					return;
				}
				if(c.inGoblin()) {
					c.sendMessage("You can't teleport during Goblin Minigame");
					return;
				}
				c.getPA().teleTabTeleport(2549, 3113, 0, "teleTab");
				c.getItems().deleteItem(8012,c.getItems().getItemSlot(8012),1);
			break;
			
			case 5509:
			case 5510:
			case 5511:
			case 5512:
			case 5513:
			case 5514:
				int pouch = -1;
				int a = itemId;
				if (a == 5509)
					pouch = 0;
				if (a == 5510)
					pouch = 1;
				if (a == 5512)
					pouch = 2;
				if (a == 5514)
					pouch = 3;
				c.getPA().fillPouch(pouch);
			break;
			
			case 952:
				if(c.inArea(3553, 3301, 3561, 3294)) {
					c.teleTimer = 3;
					c.newLocation = 1;
				} else if(c.inArea(3550, 3287, 3557, 3278)) {
					c.teleTimer = 3;
					c.newLocation = 2;
				} else if(c.inArea(3561, 3292, 3568, 3285)) {
					c.teleTimer = 3;
					c.newLocation = 3;
				} else if(c.inArea(3570, 3302, 3579, 3293)) {
					c.teleTimer = 3;
					c.newLocation = 4;
				} else if(c.inArea(3571, 3285, 3582, 3278)) {
					c.teleTimer = 3;
					c.newLocation = 5;
				} else if(c.inArea(3562, 3279, 3569, 3273)) {
					c.teleTimer = 3;
					c.newLocation = 6;
				} else if(c.inArea(2986, 3370, 3013, 3388)) {
					c.teleTimer = 3;
					c.newLocation = 7;
				}else
					c.sendMessage("You find exactly nothing");
			break;
			
		///~~~~~~~~~~~~~~~~~~~~~Monsterrays patches~~~~~~~~~~~~~~~~~~~~~~~~~
			//case 5305:
				
			//break;
			
			default:
				c.sendMessage("Doesn't Have a case please post on the forum the line below");
				c.sendMessage("Item ID:"+ itemId +"     ClickingItem");
			break;
		}
	}
}
/*
if (itemId >= 14876 && itemId <= 14892) {
	int a = itemId;
	String YEYAF = "<col=1532693>You Exchanged Your Artifact For</col> ";
	if (a == 14876){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,10000000);
	c.sendMessage(YEYAF + "<col=1532693>10 million Coins!</col>");
	}
	if (a == 14877){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,2000000);
	c.sendMessage(YEYAF + "<col=1532693>2 million Coins!</col>");
	}
	if (a == 14878){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,1500000);
	c.sendMessage(YEYAF + "<col=1532693>1.5 million Coins!</col>");
	}
	if (a == 14879){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,1000000);
	c.sendMessage(YEYAF + "<col=1532693>1 million Coins!</col>");
	}
	if (a == 14880){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,800000);
	c.sendMessage(YEYAF + "<col=1532693>800,000 Coins!</col>");
	}
	if (a == 14881){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,600000);
	c.sendMessage(YEYAF + "<col=1532693>600,000 Coins!</col>");
	}
	if (a == 14882){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,540000);
	c.sendMessage(YEYAF + "<col=1532693>540,000 Coins!</col>");
	}
	if (a == 14883){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,400000);
	c.sendMessage(YEYAF + "<col=1532693>400,000 Coins!</col>");
	}
	if (a == 14884){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,300000);
	c.sendMessage(YEYAF + "<col=1532693>300,000 Coins!</col>");
	}
	if (a == 14885){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,200000);
	c.sendMessage(YEYAF + "<col=1532693>200,000 Coins!</col>");
	}
	if (a == 14886){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,150000);
	c.sendMessage(YEYAF + "<col=1532693>150,000 Coins!</col>");
	}
	if (a == 14887){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,100000);
	c.sendMessage(YEYAF + "<col=1532693>100,000 Coins!</col>");
	}
	if (a == 14888){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,80000);
	c.sendMessage(YEYAF + "<col=1532693>80,000 Coins!</col>");
	}
	if (a == 14889){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,60000);
	c.sendMessage(YEYAF + "<col=1532693>60,000 Coins!</col>");
	}
	if (a == 14890){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,40000);
	c.sendMessage(YEYAF + "<col=1532693>40,000 Coins!</col>");
	}
	if (a == 14891){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,20000);
	c.sendMessage(YEYAF + "<col=1532693>20,000 Coins!</col>");
	} 
	if (a == 14892){
	c.getItems().deleteItem(a,1);
	c.getItems().addItem(995,10000);
	c.sendMessage(YEYAF + "<col=1532693>10,000 Coins!</col>");
	}
	
}
*/