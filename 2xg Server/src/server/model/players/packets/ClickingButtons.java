package server.model.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.model.items.GameItem;
import server.model.minigames.GnomeGlider;
import server.model.npcs.NPCHandler;
import server.model.players.Client;
import server.model.players.Cons;
import server.model.players.PacketType;
import server.model.players.PartyRoom;
import server.model.players.Pouch;
import server.model.players.SkillMenu;
import server.model.players.Content.RequestHelp;
import server.model.players.Content.SOF;
import server.model.players.skills.ConstructionEvents;
import server.model.players.skills.Cooking;
import server.util.Misc;


/**
 * Clicking most buttons
 **/
public class ClickingButtons implements PacketType {
	SkillMenu skillMenu = new SkillMenu();
	public static boolean sentBankMes = false;
	
	@Override
	public void processPacket(final Client c, int packetType, int packetSize) {
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0, packetSize);
		Pouch.makePouch(c, packetType, packetSize);
		SOF.HandleSquealButtons(c, packetType, packetSize);
		Cons.HandleConstructionInterface(c, packetType, packetSize);
		//int actionButtonId = c.getInStream().readShort();
		GnomeGlider.flightButtons(c, actionButtonId);
		if (c.isDead)
			return;
		if(c.playerRights == 3)	
			Misc.println(c.playerName+ " - actionbutton: "+actionButtonId);
		for (int i = 0; i < c.qCAB.length; i++) {
			if (actionButtonId == c.qCAB[i][0] ){
				for (int j = 0; j < c.qCS.length; j++) {
					if ( j == i ) {
						skillMenu.openInterface(c, c.qCAB[i][1]);
//						c.forcedText = c.qC+ "My " +c.qCS[j]+ " Level is " +c.getLevelForXP(c.playerXP[c.qCAB[i][1]])+ ".";
//						c.forcedChatUpdateRequired = true;
//						c.updateRequired = true;
						return;
					}
				}
			}
		}
		int[] spellIds = {4128,4130,4132,4134,4136,4139,4142,4145,4148,4151,4153,4157,4159,4161,4164,4165,4129,4133,4137,6006,6007,6026,6036,6046,6056,
			4147,6003,47005,4166,4167,4168,48157,50193,50187,50101,50061,50163,50211,50119,50081,50151,50199,50111,50071,50175,50223,50129,50091};
		for(int i=0; i < spellIds.length; i++) {
			if(actionButtonId == spellIds[i]) {
				if (c.autocasting) {
					c.autocasting = false;
					c.getPA().resetAutocast();
				} else {
						c.autocasting = true;
						c.autocastId = i;	
				}	
			}
			
		}
		switch (actionButtonId){
			case 114008: //Change beret color
				c.getPA().showInterface(29912);
			break;
			
			case 116234: //black
				if (c.LoyaltyPoints >=  150 && (c.getItems().playerHasItem(2637, 1) || c.getItems().playerHasItem(2633, 1) )) {
					c.LoyaltyPoints -=  150;
					for (int i = 0; i < c.playerItems.length; i++)  {
						if ((c.playerItems[i]+1 == 2637) || (c.playerItems[i]+1 == 2633)){
							c.playerItems[i] = 0;
							c.sendMessage("heck Yeah");
						}
					}
					c.getItems().addItem(2635, 1);
					c.sendMessage("You change the color of the beret to: Black!");
				} else if(c.LoyaltyPoints <  150) {
					c.sendMessage("Get 150 Loyalty Points.");
				}else{
					 c.sendMessage("Get a real Beret");
				}
			break;
			
			case 116237: //white
				if (c.LoyaltyPoints >=  150 && (c.getItems().playerHasItem(2635, 1) || c.getItems().playerHasItem(2633, 1) )) {
					c.LoyaltyPoints -=  150;
					for (int i = 0; i < c.playerItems.length; i++)  {
						if ((c.playerItems[i]+1 == 2635) || (c.playerItems[i]+1 == 2633)){
							c.playerItems[i] = 0;
							c.sendMessage("heck Yeah");
						}
					}
					c.getItems().addItem(2637, 1);
					c.sendMessage("You change the color of the beret to: White!");
				} else if(c.LoyaltyPoints <  150) {
					c.sendMessage("Get 150 Loyalty Points.");
				}else{
					 c.sendMessage("Get a real Beret");
				}
			break;
			
			case 116240: //blue
				if (c.LoyaltyPoints >=  150 && (c.getItems().playerHasItem(2637, 1) || c.getItems().playerHasItem(2635, 1) )) {
					c.LoyaltyPoints -=  150;
					for (int i = 0; i < c.playerItems.length; i++)  {
						if ((c.playerItems[i]+1 == 2637) || (c.playerItems[i]+1 == 2635)){
							c.playerItems[i] = 0;
							c.sendMessage("heck Yeah");
						}
					}
					c.getItems().addItem(2633, 1);
					c.sendMessage("You change the color of the beret to: Blue!");
				} else if(c.LoyaltyPoints <  150) {
					c.sendMessage("Get 150 Loyalty Points.");
				}else{
					 c.sendMessage("Get a real Beret");
				}
			break;
			
			case 114007: //Change sol color
				c.getPA().showInterface(29812);
			break;
			
			//staff of light col changer
			case 116134: //FIRST BOX
				if (c.LoyaltyPoints >=  150 && c.getItems().playerHasItem(15486, 1)) {
					c.LoyaltyPoints -=  150;
					c.getItems().deleteItem(15486, 1);
					c.getItems().addItem(13482, 1);
					c.sendMessage("You change the color of the Staff to: Yellow!");
				} else {
					c.sendMessage("Get 150 Loyalty Points and a real staff of light.");
				}
			break;
			
			case 116137: //SECOND BOX
				if (c.LoyaltyPoints >=  150 && c.getItems().playerHasItem(15486, 1)) {
					c.LoyaltyPoints -=  150;
					c.getItems().deleteItem(15486, 1);
					c.getItems().addItem(15488, 1);
					c.sendMessage("You change the color of the Staff to: Red!");
				} else {
					c.sendMessage("Get 150 Loyalty Points and a real staff of light.");
				}
			break;
			
			case 116140: // THIRD BOX
				if (c.LoyaltyPoints >=  150 && c.getItems().playerHasItem(15486, 1)) {
					c.LoyaltyPoints -=  150;
					c.getItems().deleteItem(15486, 1);
					c.getItems().addItem(13484, 1);
					c.sendMessage("You change the color of the Staff to: Green!");
				} else {
					c.sendMessage("Get 150 Loyalty Points and a real staff of light.");
				}
			break;
			
			case 116143: //LAST BOX
				if (c.LoyaltyPoints >=  150 && c.getItems().playerHasItem(15486, 1)) {
					c.LoyaltyPoints -=  150;
					c.getItems().deleteItem(15486, 1);
					c.getItems().addItem(13822, 1);
					c.sendMessage("You change the color of the Staff to: Blue!");
				} else {
					c.sendMessage("Get 150 Loyalty Points and a real staff of light.");
				}
			break;
			
			case 189121:
				c.getPA().sendFrame126(Config.FORUMS,-1);
				c.sendMessage("<col=225>Opening the Donate Page");
			break;
				
                //Monsterray Staff Tab
			case 88056:
				RequestHelp.setInterface(c);
			break;
				
			case 109114:
				RequestHelp.callForHelp(c);
			break;
				
        ///~~~~~~~~~~~~~~~~~~~~~loyalty Title~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			case 113250:
				c.playerTitle = 0;
				c.sendMessage("You Have Set Your Player Title to -NOTHING-");
				c.getPA().writeMainTab();
			break;
			
			case 113251:
				if (c.LoyaltyPoints >=  100) {
					c.playerTitle = 2;
					c.LoyaltyPoints -=  100;
					c.sendMessage("You Apply Sir, You now have "+c.LoyaltyPoints+" Loyalty Points");
					c.getPA().writeMainTab();
				} else {
					c.sendMessage("You Need 100 Loyalty Points for this (Play some more)");
				}
			break;
			
			case 113252:
				if (c.LoyaltyPoints >=  100) {
					c.playerTitle = 1;
					c.LoyaltyPoints -=  100;
					c.sendMessage("You Apply Lord, You now have "+c.LoyaltyPoints+" Loyalty Points");
					c.getPA().writeMainTab();
				} else {
					c.sendMessage("You Need 100 Loyalty Points for this (Play some more)");
				}
			break;
			
			case 113253:
				if (c.LoyaltyPoints >=  100) {
					c.playerTitle = 17;
					c.LoyaltyPoints -=  100;
					c.sendMessage("You Apply Lady, You now have "+c.LoyaltyPoints+" Loyalty Points");
					c.getPA().writeMainTab();
				} else {
					c.sendMessage("You Need 100 Loyalty Points for this (Play some more)");
				}
			break;
			
			case 113254: //Apply Overlord title
				if (c.LoyaltyPoints >=  100) {
					c.playerTitle = 10;
					c.LoyaltyPoints -=  100;
					c.sendMessage("You Apply OverLord, You now have "+c.LoyaltyPoints+" Loyalty Points");
					c.getPA().writeMainTab();
				} else {
					c.sendMessage("You Need 100 Loyalty Points for this (Play some more)");
				}
			break;
			
			case 113255:
				if (c.LoyaltyPoints >=  100) {
					c.playerTitle = 22;
					c.LoyaltyPoints -=  100;
					c.sendMessage("You Apply Overlordess, You now have "+c.LoyaltyPoints+" Loyalty Points");
					c.getPA().writeMainTab();
				} else {
					c.sendMessage("You Need 100 Loyalty Points for this (Play some more)");
				}
			break;
			
			case 114000:
				if (c.LoyaltyPoints >=  100) {
					c.playerTitle = 15;
					c.LoyaltyPoints -=  100;
					c.sendMessage("You Apply Baron, You now have "+c.LoyaltyPoints+" Loyalty Points");
					c.getPA().writeMainTab();
				} else {
					c.sendMessage("You Need 100 Loyalty Points for this (Play some more)");
				}
			break;
			
			case 114001:
				if (c.LoyaltyPoints >=  100) {
					c.playerTitle = 20;
					c.LoyaltyPoints -=  100;
					c.sendMessage("You Apply Barroness, You now have "+c.LoyaltyPoints+" Loyalty Points");
					c.getPA().writeMainTab();
				} else {
					c.sendMessage("You Need 100 Loyalty Points for this (Play some more)");
				}
			break;
			
			case 114002:
				if (c.LoyaltyPoints >=  100) {
					c.playerTitle = 12;
					c.LoyaltyPoints -=  100;
					c.sendMessage("You Apply Count, You now have "+c.LoyaltyPoints+" Loyalty Points");
					c.getPA().writeMainTab();
				} else {
					c.sendMessage("You Need 100 Loyalty Points for this (Play some more)");
				}
			break;
			
			case 114003:
				if (c.LoyaltyPoints >=  100) {
					c.playerTitle = 21;
					c.LoyaltyPoints -=  100;
					c.sendMessage("You Apply Countess, You now have "+c.LoyaltyPoints+" Loyalty Points");
					c.getPA().writeMainTab();
				} else {
					c.sendMessage("You Need 100 Loyalty Points for this (Play some more)");
				}
			break;
			
			case 114004:
				if (c.LoyaltyPoints >=  100) {
					c.playerTitle = 14;
					c.LoyaltyPoints -=  100;
					c.sendMessage("You Apply HELL RAISER, You now have "+c.LoyaltyPoints+" Loyalty Points");
					c.getPA().writeMainTab();
				} else {
					c.sendMessage("You Need 100 Loyalty Points for this (Play some more)");
				}
			break;
			
		///~~~~~~~~~~~~~~~~~~~~~Lodestone Teles~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			case 102108:
				c.getPA().startTeleport(3213, 3424, 0, "modern");
				c.sendMessage("You Teleport to Varrock"); 
				c.getPA().closeAllWindows();
			break;
			
			case 102111:
				c.getPA().startTeleport(3087, 3497, 0, "modern");
				c.sendMessage("You Teleport to EdgeVille"); 
				c.getPA().closeAllWindows();
			break;
			
			case 102093:
				c.getPA().startTeleport(3222, 3218, 0, "modern");
				c.sendMessage("You Teleport to Lumbridge"); 
				c.getPA().closeAllWindows();
			break;
			
			case 102132:
				c.getPA().startTeleport(2725, 3485, 0, "modern");
				c.sendMessage("You Teleport to Seers Village"); 
				c.getPA().closeAllWindows();
			break;
			
			case 102126:
				c.getPA().startTeleport(2808, 3433, 0, "modern");
				c.sendMessage("You Teleport to Catherby"); 
				c.getPA().closeAllWindows();
			break;
			
			case 102114:
				c.getPA().startTeleport(2964, 3380, 0, "modern");
				c.sendMessage("You Teleport to Falador"); 
				c.getPA().closeAllWindows();
			break;
			
			case 102120:
				c.getPA().startTeleport(3079, 3350, 0, "modern");
				c.sendMessage("You Teleport to Draynor Village"); 
				c.getPA().closeAllWindows();
			break;
			
			case 102096:
				c.getPA().startTeleport(2899, 3547, 0, "modern");
				c.sendMessage("You Teleport to Burthorpe"); 
				c.getPA().closeAllWindows();
			break;
			
			case 102123:
				c.getPA().startTeleport(2662, 3305, 0, "modern");
				c.sendMessage("You Teleport to Ardounge"); 
				c.getPA().closeAllWindows();
			break;
			
			case 102129:
				c.getPA().startTeleport(2606, 3093, 0, "modern");
				c.sendMessage("You Teleport to Yannile"); 
				c.getPA().closeAllWindows();
			break;
			
			case 102099:
				c.getPA().startTeleport(3176, 2987, 0, "modern");
				c.sendMessage("You Teleport to Bandit Camp"); 
				c.getPA().closeAllWindows();
			break;
			
			case 102117:
				c.getPA().startTeleport(3026, 3207, 0, "modern");
				c.sendMessage("You Teleport to Port Sarim"); 
				c.getPA().closeAllWindows();
			break;
			
			case 102105:
				c.getPA().startTeleport(3300, 3209, 0, "modern");
				c.sendMessage("You Teleport to Al Kharid"); 
				c.getPA().closeAllWindows();
			break;
			
			case 102090:
				c.getPA().startTeleport(2111, 3915, 0, "modern");
				c.sendMessage("You Teleport to Lunar Isle"); 
				c.getPA().closeAllWindows();
			break;
			
			case 102102:
				c.sendMessage("Taverly Maps Broke Atm Being Fixed."); 
				c.getPA().closeAllWindows();
			break;
			
		///~~~~~~~~~~~~~~~~~~~~~Aura~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			case 113217:
				if(c.getItems().freeSlots() < 1){
					c.sendMessage("You need atleast 1 free inventory space.");
					return;
				}
				c.getItems().addItem(c.AuraEquiped, 1);
				c.AuraEquiped = -1;
				c.getPA().sendFrame34(c.AuraEquiped, 0, 10794, 1);
			break;
			
		///~~~~~~~~~~~~~~~~~~~~~XP lamp~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			case 136191:
				c.antiqueSelect = 0; 
				c.sendMessage("<col=225>You select Attack");
			break;
			
			case 136212:
				c.antiqueSelect = 2;
				c.sendMessage("<col=225>You select Strength");
			break;
			
			case 136215:
				c.antiqueSelect = 4;
				c.sendMessage("<col=225>You select Ranged");
			break;
			
			case 136194:
				c.antiqueSelect = 6;
				c.sendMessage("<col=225>You select Magic");
			break;
			
			case 136233:
				c.antiqueSelect = 1;
				c.sendMessage("<col=225>You select Defence");
			break;
			
			case 136254:
				c.antiqueSelect = 3;
				c.sendMessage("<col=225>You select Hitpoints");
			break;
			
			case 136236:
				c.antiqueSelect = 5;
				c.sendMessage("<col=225>You select Prayer");
			break;
			
			case 137001:
				c.antiqueSelect = 24;
				c.sendMessage("<col=225>You select Dungeoneering");
			break;
			
			case 136203:
				c.antiqueSelect = 16;
				c.sendMessage("<col=225>You select Agility");
			break;
			
			case 136224:
				c.antiqueSelect = 15;
				c.sendMessage("<col=225>You select Herblore");
			break;
			
			case 136209:
				c.antiqueSelect = 17;
				c.sendMessage("<col=225>You select Thieving");
			break;
			
			case 136242:
				c.antiqueSelect = 12;
				c.sendMessage("<col=225>You select Crafting");
			break;
			
			case 137007:
				c.antiqueSelect = 20;
				c.sendMessage("<col=225>You select Runecrafting");
			break;
			
			case 136227:
				c.antiqueSelect = 18;
				c.sendMessage("<col=225>You select Slayer");
			break;
			
			case 136245:
				c.antiqueSelect = 19;
				c.sendMessage("<col=225>You select Farming");
			break;
			
			case 136197:
				c.antiqueSelect = 14;
				c.sendMessage("<col=225>You select Mining");
			break;
			
			case 136218:
				c.antiqueSelect = 13;
				c.sendMessage("<col=225>You select Smithing");
			break;
			
			case 136239:
				c.antiqueSelect = 10;
				c.sendMessage("<col=225>You select Fishing");
			break;
			
			case 137004:
				c.antiqueSelect = 7;
				c.sendMessage("<col=225>You select Cooking");
			break;
			
			case 136221:
				c.antiqueSelect = 11;
				c.sendMessage("<col=225>You select Firemaking");
			break;
			
			case 136230:
				c.antiqueSelect = 23;
				c.sendMessage("<col=225>You select Construction");
			break;
			
			case 136251:
				c.antiqueSelect = 21;
				c.sendMessage("<col=225>You select Summoning");
			break;
			
			case 136248:
				c.antiqueSelect = 22;
				c.sendMessage("<col=225>You select hunter");
			break;
			
			case 136200:
				c.antiqueSelect = 8;
				c.sendMessage("<col=225>You select Woodcutting");
			break;
			
			case 136206:
				c.antiqueSelect = 9;
				c.sendMessage("<col=225>You select Fletching");
			break;
			
			case 137013:
				if(c.lampfix == 5) {
					c.getPA().addSkillXP(750000,c.antiqueSelect);
					c.getItems().deleteItem(2528, 1);
					c.sendMessage("<col=225>750k Xp added to skill chosen! Lamp Disapeared");
					c.getPA().closeAllWindows();
					c.lampfix = 0;
				} else {
					c.sendMessage("<col=225>SORRY YOU CANT MULTICLICK OR CHEAT");
					c.getPA().closeAllWindows();
				}
	        break;
			
			case 192013:
				if(System.currentTimeMillis() - c.buryDelay > 1500) {	
					c.constructionID = 1088;
					c.getDH().sendDialogues(28168, 1);
				}
			break;
			
			case 192014:
				if(System.currentTimeMillis() - c.buryDelay > 1500) {	
					c.constructionID = 594;
					c.getDH().sendDialogues(28169, 1);
				}
			break;
			
			case 82012://search button.
				c.isSearching = true;
		    	c.isSearching2 = !c.isSearching2;
		    	if(!c.isSearching2) {
		    	    if(c.getInStream() != null & c != null) {
						c.getOutStream().createFrame(187);
						c.flushOutStream();
					}
		    	    c.isSearching = true;
		    	    c.isSearching2 = false;
		    	} else {
		    	    c.isSearching = false;
		    	    c.isSearching2 = true;
		    	}
			break;
			
			case 53152:
				Cooking.getAmount(c, 1);
			break;
				
			case 53151:
				Cooking.getAmount(c, 5);
			break;
				
			case 53150:
				Cooking.getAmount(c, 10);
			break;
				
			case 53149:
				Cooking.getAmount(c, 28);
			break;
				
			case 108005:
				c.getPA().closeAllWindows();
				c.getPA().showInterface(19148);
			break;
			
			case 8198:
				PartyRoom.accept(c);
			break;
				
			case 66122:
				switch(c.hasFollower) {
					case 6806: //thorny snail
					case 6807:
					case 6994: //spirit kalphite
					case 6995:
					case 6867: //bull ant
					case 6868:
					case 6794: //spirit terrorbird
					case 6795:
					case 6815: //war tortoise
					case 6816:
					case 6873: //pack yak
					case 3594: // yak
					case 3590: // war tortoise
					case 3596: // terrorbird
					case 6874:
						for (int i = 0; i < c.maxstore; i++) {
							if (c.storeditems[i] > 0) {
								c.getPA().removeBoBItems(i, 1);
								c.startAnimation(827);
								c.stopMovement();
							} 
						}
					break;
				}
			break;
				
			case 39014:
				c.sendMessage("You're familiar will automaticly aid you in multi.");
			break;
			
			case 39013:
				c.sendMessage("No need to do this!");
			break;
			
			case 39011:
				c.getDH().Kill();
			break;
			
			case 39010:
				c.sendMessage("Familiar called.");
			break;
			
			/*case 39012:
				//c.getPA().closeAllWindows();
				//c.getPA().showInterface(19148);
				switch(c.hasFollower) {
					case 6806: //thorny snail
					case 6807:
					case 6994: //spirit kalphite
					case 6995:
					case 6867: //bull ant
					case 6868:
					case 6794: //spirit terrorbird
					case 6795:
					case 6815: //war tortoise
					case 6816:
					case 6873: //pack yak
					case 3594: // yak
					case 3590: // war tortoise
					case 3596: // terrorbird
					case 6874:
						for (int i = 0; i < c.maxstore; i++) {
							if (c.storeditems[i] > 0) {
								c.getPA().BoBToBank(i, 1);
								//c.startAnimation(827);
								c.stopMovement();
							} 
						}
					break;
				}
			break;*/
			
			case 58074:
				c.getBankPin().closeBankPin();
			break;
				
			case 58073:
				//c.getBankPin().resetBankPin();
				c.getPA().sendFrame126(Config.FORUMS,-1);
				c.sendMessage("Opened forums, Request A new Pin on forums!");
			break;

			case 58025:
			case 58026:
			case 58027:
			case 58028:
			case 58029:
			case 58030:
			case 58031:
			case 58032:
			case 58033:
			case 58034:
				c.getBankPin().bankPinEnter(actionButtonId);
			break;
			
			//end of quests n dung tab + Monsterray LOCKXP
			//quickpray
	/*			case 67079:
					c.getQC().clickConfirm();
					c.sendMessage("Confirmed QuickPrayers/QuickCurses! Activate them by pushing the orb!");
				break;

				case 67050:
				case 67051:
				case 67052:
				case 67053:
				case 67054:
				case 67055:
				case 67056:
				case 67057:
				case 67058:
				case 67059:
				case 67060:
				case 67061:
				case 67062:
				case 67063:
				case 67064:
				case 67065:
				case 67066:
				case 67067:
				case 67068:
				case 67069:
				case 67070:
				case 67071:
				case 67072:
				case 67073:
				case 67074:
				case 67075:
					c.sendMessage("Prayer selected.");
					if (c.altarPrayed == 0)
						c.getQP().clickPray(actionButtonId);
						else
						c.getQC().clickCurse(actionButtonId);
				break;

				case 70080:
					c.sendMessage("Prayers turned to: ON");
					c.getQC().turnOnQuicks();
				break;

				case 70081:
					c.getQC().turnOffQuicks();
					c.sendMessage("Prayers turned to: OFF");
				break;

				case 70082:
					c.getQC().selectQuickInterface();
					c.getPA().sendFrame106(5);
				break;*/
				
		///~~~~~~~~~~~~~~~~~~~~~Vote Interface~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			case 74111://Pick cash
				if (c.vote == 1 && c.getItems().freeSlots() >= 1) {
					c.getItems().addItem(995,5000000);
					c.startAnimation(3543);
					c.sendMessage("Your receive 5m cash! Thank you for voting!");
					c.getPA().closeAllWindows();
					c.vote = 0;
					c.isChoosing = false;
				} else if (c.vote == 1 && c.getItems().freeSlots() <= 1) {
					Server.itemHandler.createGroundItem(c, 995, c.getX(), c.getY(), 5000000, c.getId());               				
					c.startAnimation(3543);
					c.sendMessage("Your inventory is full so the item appears on the ground.");
					c.sendMessage("Your receive 5m cash! Thank you for voting!");
					c.getPA().closeAllWindows();
					c.vote = 0;
					c.isChoosing = false;
				} else if(c.vote != 1){
					c.sendMessage("You have not voted!");
					c.getPA().closeAllWindows();
				}
			break;
			
			case 74108:
				if (c.vote == 1 && c.getItems().freeSlots() >= 1) {
					c.getItems().addItem(c.randomreward2(),1);
					c.startAnimation(3543);
					c.sendMessage("Your receive a misc reward! Thank you for voting!");
					c.getPA().closeAllWindows();
					c.vote = 0;
					c.isChoosing = false;
				} else if (c.vote == 1 && c.getItems().freeSlots() <= 1) {
					//Server.itemHandler.createGroundItem(c, c.miscpackage1(), c.getX(), c.getY(), 1, c.getId());    
					Server.itemHandler.createGroundItem(c, c.randomreward2(), c.getX(), c.getY(), 1, c.getId());          		
					c.startAnimation(3543);
					c.sendMessage("Your inventory is full so the item appears on the ground.");
					c.sendMessage("Your receive a misc reward! Thank you for voting!");
					c.getPA().closeAllWindows();
					c.vote = 0;
					c.isChoosing = false;
				} else if(c.vote != 1){
					c.sendMessage("You have not voted!");
					c.getPA().closeAllWindows();
				}
			break;
				
			case 20174:
				c.getDH().sendDialogues(119,8275);
		//crafting + fletching interface:
			//case 89223: //Deposit Inventory old client
			/*case 66117:
				switch(c.hasFollower) {
					case 6870: //wolpertinger
						if(c.getItems().playerHasItem(12437, 1)) {
							c.getItems().deleteItem(12437, 1);
							c.gfx0(1311);
							if(c.playerLevel[6] > c.getLevelForXP(c.playerXP[6]))
								c.playerLevel[6] = c.getLevelForXP(c.playerXP[6]);
							else
								c.playerLevel[6] += (c.getLevelForXP(c.playerXP[6]) * .1);
							c.getPA().refreshSkill(6);
							c.sendMessage("Your Magic bonus has increased!");
						} else
							c.sendMessage("You don't have a scroll for that NPC!");
					break;*/
			
			case 150:
				if (c.autoRet == 0)
					c.autoRet = 1;
				else 
					c.autoRet = 0;
			break;
			
			case 70146:
				if (c.playerLevel[24] > 98) {
					c.getItems().addItem(18509, 1);
				} else {
					c.sendMessage("You must be 99 Dungeoneering to Recieve This.");
				}
			break;
					
				
	/*     	case 66122:
				switch(c.npcType) {
				case 6807:
				case 6874:
				case 6868:
				case 6795:
				case 6816:
				case 6873:

				c.sendMessage("You are now storing items inside your npc");
					c.Summoning().store();
				}
							break;*/
	
			case 66127:
				c.getDH().Kill();
			break;
			case 21010:
				c.takeAsNote = true;
			break;
			
			case 21011:
				c.takeAsNote = false;
			break;
			
			case 85248:
				c.takeAsNote = !c.takeAsNote;
			break;
			
			case 68244:
				c.getPA().startTeleport(2676, 3711, 0, "modern");
			break;
			
			case 54221:
				c.getPA().startTeleport(2897, 3618, 12, "modern");
				c.sendMessage("Welcome to The God Bandos's chamber");
			break;
		
			case 54231:
				//c.getPA().startTeleport(2897, 3618, 4, "modern");
				c.getPA().startTeleport(2897, 3618, 8, "modern");
				c.sendMessage("Welcome to The God Saradomin's chamber");
			break;
		
			case 54228:
				c.getPA().startTeleport(2897, 3618, 4, "modern");
				//c.getPA().startTeleport(2897, 3618, 8, "modern");
				c.sendMessage("Welcome to The God Armadyl's chamber");
			break;
			
			case 68247:
				c.getPA().startTeleport(2884, 9798, 0, "modern");
			break;

			case 68250:
				c.getPA().startTeleport(3428, 3537, 0, "modern");
			break;

			case 68253:
				c.getPA().startTeleport(2710, 9466, 0, "modern");
			break;

			case 69000:
				c.getPA().startTeleport(2905, 9730, 0, "modern");
			break;

			case 69003:
				c.getPA().startTeleport(2908, 9694, 0, "modern");
			break;

/*			case 69006:
				if((c.playerLevel[21] < 90) && (c.playerLevel[16] < 90)) {
					c.sendMessage("You need 90 Agility And 90 Hunter to enter the Strykworm's Cave");
				} else {
					if((c.playerLevel[21] > 89) && (c.playerLevel[16] < 90)) {
						c.sendMessage("You need 90 Agility to enter the Strykworm's Cave");
					} else {
						if((c.playerLevel[21] < 90) && (c.playerLevel[16] > 89)) {
							c.sendMessage("You need 90 Hunter to enter the Strykworm's Cave");
						} else {
							if((c.playerLevel[21] > 89) && (c.playerLevel[16] >89)) {
								c.getPA().startTeleport(2515, 4632, 0, "modern");
								c.sendMessage("A sense of nervousness fills your body..");
								c.sendMessage("you find yourself in a mystery cave!");
							}
						}
					}
				}
			break;*/
		
			case 69006:
				if((c.playerLevel[21] < 90) && (c.playerLevel[16] < 90)) {
					c.sendMessage("You need 90 Agility And 90 Hunter to enter the Strykworm's Cave");
				} else {
					if((c.playerLevel[21] > 89) && (c.playerLevel[16] < 90)) {
						c.sendMessage("You need 90 Agility to enter the Strykworm's Cave");
					} else {
						if((c.playerLevel[21] < 90) && (c.playerLevel[16] > 89)) {
							c.sendMessage("You need 90 Hunter to enter the Strykworm's Cave");
						} else {
							if((c.playerLevel[21] > 89) && (c.playerLevel[16] >89)) {
								c.getPA().startTeleport(2515, 4632, 0, "modern");
								c.sendMessage("A sense of nervousness fills your body..");
								c.sendMessage("you find yourself in a mystery cave!");
							}
						}
					}
				}
			break;

			/*case 114112://melee set
				if (c.inWild() && c.isBanking) {
					c.sendMessage("You cannot do this right now");
				} else if(c.getItems().freeSlots() <= 10) {
					c.sendMessage("You need atleast 10 free slot's to use this feature.");
				} else if (c.getItems().playerHasItem(995, 350000)) {
					c.getItems().deleteItem2(995, 350000);
					c.getItems().addItem(10828, 1);
					c.getItems().addItem(1127, 1);
					c.getItems().addItem(1079, 1);
					c.getItems().addItem(3842, 1);
					c.getItems().addItem(4587, 1);
					c.getItems().addItem(1231, 1);
					c.getItems().addItem(1725, 1);
					c.getItems().addItem(3105, 1);
					c.getItems().addItem(2550, 1);
				} else {
					c.sendMessage("You need atleast 350,000 coins to use this feature.");
				}
			break;
			
			case 46230:
				c.getItems().addItem(10828, 1);
				c.getItems().addItem(10551, 1);
				c.getItems().addItem(4087, 1);
				c.getItems().addItem(11732, 1);
				c.getItems().addItem(13006, 1);
				c.getItems().addItem(1725, 1);
				c.getItems().addItem(6737, 1);
				c.getItems().addItem(8850, 1);
				c.getItems().addItem(4151, 1);
				c.getItems().addItem(995, 50000000);
                c.getPA().showInterface(3559);
				c.getPA().addSkillXP((15000000), 0);
				c.getPA().addSkillXP((15000000), 1);
				c.getPA().addSkillXP((15000000), 2);
				c.getPA().addSkillXP((15000000), 3);
				c.getPA().addSkillXP((15000000), 4);
				c.getPA().addSkillXP((15000000), 5);
				c.getPA().addSkillXP((15000000), 6);
				c.playerXP[3] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				c.getPA().refreshSkill(3);
				c.puremaster = 1;
			break;
			
			case 46234:
				c.getItems().addItem(10941, 1);
				c.getItems().addItem(10939, 1);
				c.getItems().addItem(10940, 1);
				c.getItems().addItem(10933, 1);
				c.getItems().addItem(18508, 1);
				c.getItems().addItem(2462, 1);
				c.getItems().addItem(995, 50000000);
                c.getPA().showInterface(3559);
			break;
			
			case 46227:
				c.getItems().addItem(12222, 1);
				c.getItems().addItem(6107, 1);
				c.getItems().addItem(2497, 1);
				c.getItems().addItem(3105, 1);
				c.getItems().addItem(12988, 1);
				c.getItems().addItem(10498, 1);
				c.getItems().addItem(1725, 1);
				c.getItems().addItem(861, 1);
				c.getItems().addItem(4151, 1);
				c.getItems().addItem(892, 1000);
				c.getItems().addItem(995, 50000000);
                c.getPA().showInterface(3559);
				c.getPA().addSkillXP((15000000), 0);
				c.getPA().addSkillXP((15000000), 2);
				c.getPA().addSkillXP((15000000), 3);
				c.getPA().addSkillXP((15000000), 4);
				c.getPA().addSkillXP((15000000), 6);
				c.playerXP[3] = c.getPA().getXPForLevel(99)+5;
				c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				c.getPA().refreshSkill(3);
				c.puremaster = 1;
			break;
			
			case 114113://mage set
				if (c.inWild() && c.isBanking) {
					c.sendMessage("You cannot do this right now");
				} else if(c.getItems().freeSlots() <= 7) {
					c.sendMessage("You need atleast 7 free slot's to use this feature.");
				} else if (c.getItems().playerHasItem(995, 300000)) {
					c.getItems().deleteItem2(995, 300000);
					c.getItems().addItem(4091, 1);
					c.getItems().addItem(4093, 1);
					c.getItems().addItem(3755, 1);
					c.getItems().addItem(2550, 1);
					c.getItems().addItem(1704, 1);
					c.getItems().addItem(3842, 1);
					c.getItems().addItem(4675, 1);
				} else {
					c.sendMessage("You need atleast 300,000 coins to use this feature.");
				}
			break;
			
			case 114114://range set
			 if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 13) {
				c.sendMessage("You need atleast 13 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 450000)) {
				c.getItems().deleteItem2(995, 450000);
				c.getItems().addItem(3749, 1);
				c.getItems().addItem(1704, 1);
				c.getItems().addItem(2503, 1);
				c.getItems().addItem(2497, 1);
				c.getItems().addItem(2491, 1);
				c.getItems().addItem(6328, 1);
				c.getItems().addItem(2550, 1);
				c.getItems().addItem(9185, 1);
				c.getItems().addItem(9243, 100);
				c.getItems().addItem(10499, 1);
				c.getItems().addItem(861, 1);
				c.getItems().addItem(892, 100);
			} else {
				c.sendMessage("You need atleast 450,000 coins to use this feature.");
			}
			break;
			
			case 114115://hybrid set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 14) {
				c.sendMessage("You need atleast 14 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 450000)) {
				c.getItems().deleteItem2(995, 450000);
				c.getItems().addItem(555, 300);
				c.getItems().addItem(560, 200);
				c.getItems().addItem(565, 100);
				c.getItems().addItem(4675, 1);
				c.getItems().addItem(2497, 1);
				c.getItems().addItem(2415, 1);
				c.getItems().addItem(10828, 1);
				c.getItems().addItem(3841, 1);
				c.getItems().addItem(2503, 1);
				c.getItems().addItem(7460, 1);
				c.getItems().addItem(1704, 1);
				c.getItems().addItem(2550, 1);
				c.getItems().addItem(4091, 1);
				c.getItems().addItem(4093, 1);
				c.getItems().addItem(3105, 1);
			} else {
				c.sendMessage("You need atleast 450,000 coins to use this feature.");
			}
			break;
			
						case 114118://runes set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 10) {
				c.sendMessage("You need atleast 10 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 300000)) {
				c.getItems().deleteItem2(995, 300000);
				c.getItems().addItem(560,1000);
				c.getItems().addItem(555,1000);
				c.getItems().addItem(565,1000);
				c.getItems().addItem(9075,1000);
				c.getItems().addItem(557,1000);
				c.getItems().addItem(556,1000);
				c.getItems().addItem(554,1000);
				c.getItems().addItem(562,1000);
				c.getItems().addItem(561,1000);
				c.getItems().addItem(563,1000);
			} else {
				c.sendMessage("You need atleast 300,000 coins to use this feature.");
			}
			break;
			
									case 114119://barrage set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 3) {
				c.sendMessage("You need atleast 3 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 2000000)) {
				c.getItems().deleteItem2(995, 2000000);
				c.getItems().addItem(555,6000);
				c.getItems().addItem(560,4000);
				c.getItems().addItem(565,2000);
			} else {
				c.sendMessage("You need atleast 2,000,000 coins to use this feature.");
			}
			break;
			
			case 114120://veng set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 3) {
				c.sendMessage("You need atleast 3 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 100000)) {
				c.getItems().deleteItem2(995, 100000);
				c.getItems().addItem(557,1000);
				c.getItems().addItem(560,200);
				c.getItems().addItem(9075,400);
			} else {
				c.sendMessage("You need atleast 100,000 coins to use this feature.");
			}
			break;
			
			case 114123://shark set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 100000)) {
				c.getItems().deleteItem2(995, 100000);
				c.getItems().addItem(385,1000);
			} else {
				c.sendMessage("You need atleast 100,000 coins to use this feature.");
			}
			break;
			
						case 114124://tuna pot set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 150000)) {
				c.getItems().deleteItem2(995, 150000);
				c.getItems().addItem(7060,1000);
			} else {
				c.sendMessage("You need atleast 150,000 coins to use this feature.");
			}
			break;
			
			case 114125://super set
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 80000)) {
				c.getItems().deleteItem2(995, 80000);
				c.getItems().addItem(146,100);
				c.getItems().addItem(158,100);
				c.getItems().addItem(164,100);
			} else {
				c.sendMessage("You need atleast 80,000 coins to use this feature.");
			}
			break;
			
						case 114126://super restores biatch
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 30000)) {
				c.getItems().deleteItem2(995, 30000);
				c.getItems().addItem(3025,100);
			} else {
				c.sendMessage("You need atleast 30,000 coins to use this feature.");
			}
			break;
			
									case 114127://mage pots
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 30000)) {
				c.getItems().deleteItem2(995, 30000);
				c.getItems().addItem(3041,100);
			} else {
				c.sendMessage("You need atleast 30,000 coins to use this feature.");
			}
			break;
			
									case 114128://range pots
			if (c.inWild() && c.isBanking) {
				c.sendMessage("You cannot do this right now");
			} else if(c.getItems().freeSlots() <= 1) {
				c.sendMessage("You need atleast 1 free slot's to use this feature.");
			} else if (c.getItems().playerHasItem(995, 36000)) {
				c.getItems().deleteItem2(995, 36000);
				c.getItems().addItem(2445,100);
			} else {
				c.sendMessage("You need atleast 36,000 coins to use this feature.");
			}
			break;*/
			
		case 17111://stop viewing viewing orb
                c.setSidebarInterface(10, 2449);
                c.viewingOrb = false;
                c.teleportToX = 2399;
                c.teleportToY = 5171;
                c.appearanceUpdateRequired = true;
                c.updateRequired = true;
			break;

		case 59139://viewing orb southwest
			c.viewingOrb = true;
			c.teleportToX = 2388;
			c.teleportToY = 5138;
			c.appearanceUpdateRequired = true;
			c.updateRequired = true;
		break;

		case 59138://viewing orb southeast
			c.viewingOrb = true;
			c.teleportToX = 2411;
			c.teleportToY = 5137;
			c.appearanceUpdateRequired = true;
			c.updateRequired = true;
		break;

		case 59137://viewing orb northeast
			c.viewingOrb = true;
			c.teleportToX = 2409;
			c.teleportToY = 5158;
			c.appearanceUpdateRequired = true;
			c.updateRequired = true;
		break;

		case 59136://viewing orb northwest
			c.viewingOrb = true;
			c.teleportToX = 2384;
			c.teleportToY = 5157;
			c.appearanceUpdateRequired = true;
			c.updateRequired = true;
		break;

		case 59135://viewing orb middle
			c.viewingOrb = true;
			c.teleportToX = 2398;
			c.teleportToY = 5150;
			c.appearanceUpdateRequired = true;
			c.updateRequired = true;
		break;
		
		case 107229:
			if (c.isDonator == 1 && c.slayerTask <= 0) {
				c.getDH().sendDialogues(11,8275);
				c.sendMessage("Your magical donator rank makes you able to contact Duradel!");
			} else {
				c.sendMessage("You alredy have a task!");
			} 
		break;  

		case 108003:
			if (c.isDonator == 1) {
				c.setSidebarInterface(4, 27620);
			} else {
				c.sendMessage("You must be an donator to view this tab!");
			}
		break;

		case 82020:
			if (!c.isBanking) {
				c.getPA().closeAllWindows();
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						c2.sendMessage("<shad=15695415>[Abuse]: "+Misc.optimizeText(c.playerName)+" Tried to Bank Hack - Stopped.");
					}
				}
				return;
			}	
			for(int i = 0; i < c.playerItems.length; i++) {
				c.getItems().bankItem(c.playerItems[i], i,c.playerItemsN[i]);
			}	
		break;

		case 107231:
			if (c.isDonator == 1) {
				c.getPA().spellTeleport(2524, 4777, 0);
				c.sendMessage("<img=0>You teleported to donator Island a place to chill/relax, theres also alot of benefits.<img=0>");
			} else {
				c.sendMessage("You must be an donator to teleport to the donator Island!");
				return;				
			}
		break;
		
		/*case 108006:
			if(!c.isSkulled) {	
				c.getItems().resetKeepItems();
				c.getItems().keepItem(0, false);
				c.getItems().keepItem(1, false);	
				c.getItems().keepItem(2, false);
				c.getItems().keepItem(3, false);
				c.sendMessage("You can keep three items and a fourth if you use the protect item prayer.");
			} else {
				c.getItems().resetKeepItems();
				c.getItems().keepItem(0, false);
				c.sendMessage("You are skulled and will only keep one item if you use the protect item prayer.");
			}
			c.getItems().sendItemsKept();
			c.getPA().showInterface(6960);
			c.getItems().resetKeepItems();
			break;*/
							
		case 108006: //items kept on death?
			c.StartBestItemScan();
			c.EquipStatus = 0;
			for (int k = 0; k < 4; k++)
				c.getPA().sendFrame34a(10494, -1, k, 1);
			for (int k = 0; k < 39; k++)
				c.getPA().sendFrame34a(10600, -1, k, 1);
			if(c.WillKeepItem1 > 0)
				c.getPA().sendFrame34a(10494, c.WillKeepItem1, 0, c.WillKeepAmt1);
			if(c.WillKeepItem2 > 0)
				c.getPA().sendFrame34a(10494, c.WillKeepItem2, 1, c.WillKeepAmt2);
			if(c.WillKeepItem3 > 0)
				c.getPA().sendFrame34a(10494, c.WillKeepItem3, 2, c.WillKeepAmt3);
			if(c.WillKeepItem4 > 0)
				c.getPA().sendFrame34a(10494, c.WillKeepItem4, 3, 1);
			for(int ITEM = 0; ITEM < 28; ITEM++){
				if(c.playerItems[ITEM]-1 > 0 && !(c.playerItems[ITEM]-1 == c.WillKeepItem1 && ITEM == c.WillKeepItem1Slot) && !(c.playerItems[ITEM]-1 == c.WillKeepItem2 && ITEM == c.WillKeepItem2Slot) && !(c.playerItems[ITEM]-1 == c.WillKeepItem3 && ITEM == c.WillKeepItem3Slot) && !(c.playerItems[ITEM]-1 == c.WillKeepItem4 && ITEM == c.WillKeepItem4Slot)){
					c.getPA().sendFrame34a(10600, c.playerItems[ITEM]-1, c.EquipStatus, c.playerItemsN[ITEM]);
					c.EquipStatus += 1;
				} else if(c.playerItems[ITEM]-1 > 0 && (c.playerItems[ITEM]-1 == c.WillKeepItem1 && ITEM == c.WillKeepItem1Slot) && c.playerItemsN[ITEM] > c.WillKeepAmt1){
					c.getPA().sendFrame34a(10600, c.playerItems[ITEM]-1, c.EquipStatus, c.playerItemsN[ITEM]-c.WillKeepAmt1);
					c.EquipStatus += 1;
				} else if(c.playerItems[ITEM]-1 > 0 && (c.playerItems[ITEM]-1 == c.WillKeepItem2 && ITEM == c.WillKeepItem2Slot) && c.playerItemsN[ITEM] > c.WillKeepAmt2){
					c.getPA().sendFrame34a(10600, c.playerItems[ITEM]-1, c.EquipStatus, c.playerItemsN[ITEM]-c.WillKeepAmt2);
					c.EquipStatus += 1;
				} else if(c.playerItems[ITEM]-1 > 0 && (c.playerItems[ITEM]-1 == c.WillKeepItem3 && ITEM == c.WillKeepItem3Slot) && c.playerItemsN[ITEM] > c.WillKeepAmt3){
					c.getPA().sendFrame34a(10600, c.playerItems[ITEM]-1, c.EquipStatus, c.playerItemsN[ITEM]-c.WillKeepAmt3);
					c.EquipStatus += 1;
				} else if(c.playerItems[ITEM]-1 > 0 && (c.playerItems[ITEM]-1 == c.WillKeepItem4 && ITEM == c.WillKeepItem4Slot) && c.playerItemsN[ITEM] > 1){
					c.getPA().sendFrame34a(10600, c.playerItems[ITEM]-1, c.EquipStatus, c.playerItemsN[ITEM]-1);
					c.EquipStatus += 1;
				}
			}
			for(int EQUIP = 0; EQUIP < 14; EQUIP++){
				if(c.playerEquipment[EQUIP] > 0 && !(c.playerEquipment[EQUIP] == c.WillKeepItem1 && EQUIP+28 == c.WillKeepItem1Slot)
				&& !(c.playerEquipment[EQUIP] == c.WillKeepItem2 && EQUIP+28 == c.WillKeepItem2Slot)
				&& !(c.playerEquipment[EQUIP] == c.WillKeepItem3 && EQUIP+28 == c.WillKeepItem3Slot)
				&& !(c.playerEquipment[EQUIP] == c.WillKeepItem4 && EQUIP+28 == c.WillKeepItem4Slot)){
					c.getPA().sendFrame34a(10600, c.playerEquipment[EQUIP], c.EquipStatus, c.playerEquipmentN[EQUIP]);
					c.EquipStatus += 1;
				} else if(c.playerEquipment[EQUIP] > 0 && (c.playerEquipment[EQUIP] == c.WillKeepItem1 && EQUIP+28 == c.WillKeepItem1Slot) && c.playerEquipmentN[EQUIP] > 1 && c.playerEquipmentN[EQUIP]-c.WillKeepAmt1 > 0){
					c.getPA().sendFrame34a(10600, c.playerEquipment[EQUIP], c.EquipStatus, c.playerEquipmentN[EQUIP]-c.WillKeepAmt1);
					c.EquipStatus += 1;
				} else if(c.playerEquipment[EQUIP] > 0 && (c.playerEquipment[EQUIP] == c.WillKeepItem2 && EQUIP+28 == c.WillKeepItem2Slot) && c.playerEquipmentN[EQUIP] > 1 && c.playerEquipmentN[EQUIP]-c.WillKeepAmt2 > 0){
					c.getPA().sendFrame34a(10600, c.playerEquipment[EQUIP], c.EquipStatus, c.playerEquipmentN[EQUIP]-c.WillKeepAmt2);
					c.EquipStatus += 1;
				} else if(c.playerEquipment[EQUIP] > 0 && (c.playerEquipment[EQUIP] == c.WillKeepItem3 && EQUIP+28 == c.WillKeepItem3Slot) && c.playerEquipmentN[EQUIP] > 1 && c.playerEquipmentN[EQUIP]-c.WillKeepAmt3 > 0){
					c.getPA().sendFrame34a(10600, c.playerEquipment[EQUIP], c.EquipStatus, c.playerEquipmentN[EQUIP]-c.WillKeepAmt3);
					c.EquipStatus += 1;
				} else if(c.playerEquipment[EQUIP] > 0 && (c.playerEquipment[EQUIP] == c.WillKeepItem4 && EQUIP+28 == c.WillKeepItem4Slot) && c.playerEquipmentN[EQUIP] > 1 && c.playerEquipmentN[EQUIP]-1 > 0){
					c.getPA().sendFrame34a(10600, c.playerEquipment[EQUIP], c.EquipStatus, c.playerEquipmentN[EQUIP]-1);
					c.EquipStatus += 1;
				}
			}
			c.ResetKeepItems();
			c.getPA().showInterface(17100);
		break;
		
		case 107230:
			if(c.isDonator == 0 || c.inWild()) {
				c.sendMessage("You must be outside wilderness and be a donator to use this!");
				return;
			}
			if (c.playerMagicBook == 0 && c.isDonator == 1 && !c.inWild()) {
				c.playerMagicBook = 1;
				c.setSidebarInterface(6, 12855); //ancient
				c.setSidebarInterface(0, 328);
				c.sendMessage("An ancient wisdomin fills your mind.");
				c.getPA().resetAutocast();
				return;
			}	
			if (c.playerMagicBook == 1 && c.isDonator == 1 && !c.inWild()) {
				c.playerMagicBook = 2;
				c.setSidebarInterface(0, 328);
				c.setSidebarInterface(6, 29999); //nature
				c.sendMessage("Your mind becomes stirred with thoughs of dreams.");
				c.getPA().resetAutocast();
				return;
			}
			if (c.playerMagicBook == 2 && c.isDonator == 1 && !c.inWild()) {
				c.setSidebarInterface(6, 1151); //modern
				c.playerMagicBook = 0;
				c.setSidebarInterface(0, 328);
				c.sendMessage("You feel a drain on your memory.");
				c.autocastId = -1;
				c.getPA().resetAutocast();
				return;
			}
		break;
			
		case 94142:
			if(c.hasFollower > 0) {
				c.firstslot();
				for(int i = 0; i < 29; i += 1){
					Server.itemHandler.createGroundItem(c, c.storeditems[i], NPCHandler.npcs[c.summoningnpcid].absX, NPCHandler.npcs[c.summoningnpcid].absY, 1, c.playerId);
					c.storeditems[i] = -1;
					c.occupied[i] = false;
				}
				c.hasFollower = -1;
				c.hasFollower = -1;
				c.totalstored = 0;
				c.summoningnpcid = 0;
				c.summoningslot = 0;
				c.sendMessage("Your Summon items have drop on the floor");
			} else {
				c.sendMessage("You do not have a familiar currently spawned");
			}
		break;
			//1st tele option
		case 9190:
			if (c.teleAction == 43) {
				c.getPA().startTeleport(3222, 3218, 0, "normal");
			}
			if (c.dialogueAction == 106) {
				if(c.getItems().playerHasItem(c.diceID, 1)) {
					c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
					c.getItems().addItem(15086, 1);
					c.sendMessage("You get a six-sided die out of the dice bag.");
				}
			c.getPA().closeAllWindows();
			}else if (c.dialogueAction == 50) {
				c.trade11 = 900;
				c.trade11();
				/*if (Server.singleStarter.contains(c.connectedFrom)) {
					c.sendMessage("You have already received a starter recently. You may receive another later.");
					c.selectStarter = false;
					c.getPA().closeAllWindows();
					return;
				}*/
				c.getItems().addItem(1704, 1);
				c.getItems().addItem(995,5000000);
				//Server.singleStarter.add(c.connectedFrom);
				c.getPA().closeAllWindows();
				c.getItems().addItem(6950, 1);
				c.getItems().addItem(6107, 1);
				c.getItems().addItem(6108, 1);
				c.getItems().addItem(3106, 10);
				c.getItems().addItem(4676, 10);
				c.getItems().addItem(2413, 1);
				c.getItems().addItem(11119, 10);
				c.getItems().addItem(3842, 1);
				c.getItems().addItem(2941, 10);
				c.getItems().addItem(10499, 1);
				c.getItems().addItem(5699, 10);
				c.getItems().addItem(4154, 10);
				c.getItems().addItem(555, 1000);
				c.getItems().addItem(560, 1000);
				c.getItems().addItem(565, 1000);
				c.getItems().addItem(9341, 100);
				c.getItems().addItem(386, 1500);
				c.getItems().addItem(9186, 10);
				c.getItems().addItem(2498, 10);
				c.getItems().addItem(146, 100);
				c.getItems().addItem(158, 100);
				c.getItems().addItem(2435, 100);
				c.getItems().addItem(170, 100);
				c.getItems().addItem(3041, 100);
				c.getPA().addSkillXP(3358594, 4);
				c.getPA().addSkillXP(3358594, 6);
				c.getPA().addSkillXP(3358594, 2);
				c.getPA().addSkillXP(3358594, 3);
				c.getPA().addSkillXP(106333, 0);
				c.playerLevel[3] = 85;
				c.getPA().refreshSkill(3);
				c.getPA().refreshSkill(0);
				c.getPA().refreshSkill(6);
				c.getPA().refreshSkill(4);
				c.getPA().refreshSkill(2);
				c.sendMessage("<shad=15369497>To get RFD gloves, do the Recipe for Disaster minigame south of edgeville general store!!<shad=15369497>");
				c.sendMessage("This server is one of the best.. Real summoning.. Ect.. Enjoy it!");
				c.dialogueAction = -1;
				c.getPA().requestUpdates();
				c.selectStarter = false;
				c.selectStarterr = false;
				c.getPA().showInterface(3559);
				c.canChangeAppearance = true;
			} else if (c.teleAction == 6) {
				 //kalphite queen
				 c.getPA().spellTeleport(2987, 9632, 0);
				 c.sendMessage("There are 2 forms, both use mage attacks!");
			} else if (c.dialogueAction == 107) {
				if(c.getItems().playerHasItem(c.diceID, 1)) {
					c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
					c.getItems().addItem(15092, 1);
					c.sendMessage("You get a ten-sided die out of the dice bag.");
				}
				c.getPA().closeAllWindows();
			}
			if (c.teleAction == 1) {
				//rock crabs
				c.getPA().spellTeleport(2676, 3715, 0);
			} else if (c.teleAction == 2) {
				//barrows
				c.getPA().spellTeleport(3565, 3314, 0);
			} else if (c.teleAction == 199) {
				//Assault
				c.getPA().spellTeleport(3186, 9758, 0);
				c.sendMessage("Enter the champion Stone to begin!");
				c.sendMessage("If the minigame bugs up type ::endgame, you will receive your points.");
				//c.sendMessage("DISABLED ATM DUE TO BUGS, FIXING THIS WEEKEND");
			} else if (c.teleAction == 3) {
				c.sendMessage("GodWars is under construction atm please wait untill its fixed");
			} else if (c.teleAction == 4) {
				//varrock wildy
				c.getPA().spellTeleport(2539, 4716, 0);
			} else if (c.teleAction == 5) {
				c.getDH().sendOption2("Crafting", "Hunter");
				//	c.getPA().spellTeleport(3298,3287,0);
				//	c.sendMessage("You can mine almost everything here.. Enjoy.");
			} else if (c.teleAction == 20) {
				//lum
				c.getPA().spellTeleport(3222, 3218, 0);//3222 3218 
			} else if (c.teleAction == 8) {
				c.getPA().spellTeleport(2960, 9477, 0);//sea troll queen
			}
			if (c.dialogueAction == 10) {
				c.getPA().spellTeleport(2845, 4832, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 11) {
				c.getPA().spellTeleport(2786, 4839, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 12) {
				c.getPA().spellTeleport(2398, 4841, 0);
				c.dialogueAction = -1;
			} else if (c.teleAction == 21) {
				c.getPA().spellTeleport(2480, 3437, 0);//2480 3437
				c.sendMessage("To start running again relog!");
				c.dialogueAction = -1;
			}
		break;
		//mining - 3046,9779,0
		//smithing - 3079,9502,0
	
/*		case 66122: // HANDLES THE STORE BUTTON IN SUMMON INTERFACE TAB - BY Monsterray!
			if(c.hasFollower == 3596 || c.hasFollower == 6816 || c.hasFollower == 6874 || c.hasFollower == 6795 || c.hasFollower == 6806 || c.hasFollower == 6815 || c.hasFollower == 6867 || c.hasFollower == 9469 || c.hasFollower == 6807 || c.hasFollower == 3594 || c.hasFollower == 6868 || c.hasFollower == 3590 || c.hasFollower == 6873) {
				c.sendMessage("You are now storing items inside your npc");
				c.Summoning().store();
			} else {
				c.sendMessage("You don't have an summoning NPC that can hold items.");
			}
		break;*/

		case 66126:
			if(c.hasFollower > 0){
				//c.sumTimer = 3;
				c.callFamilliar = true;
				c.sendMessage("Familliar called");
			}
			if(c.hasFollower <= 0){
				c.sendMessage("You don't have a follower");
			}
		break;
				
/*		case 66126: //Summoning Special Moves	
			if (c.summonSpec < 1){
				if (c.hasFollower == 7344 && c.getItems().playerHasItem(12825, 1)) {
					final int damage = Misc.random(20) + 6;
					if(c.npcIndex > 0) {
							NPCHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
							NPCHandler.npcs[c.npcIndex].updateRequired = true;
							NPCHandler.npcs[c.npcIndex].hitDiff2 = damage;
							NPCHandler.npcs[c.npcIndex].HP -= damage;
							c.sendMessage("Your Steel Titan Damages your Opponent.");
							c.getItems().deleteItem(12825, 1);
							c.startAnimation(1914);
					} else if(c.oldPlayerIndex > 0 || c.playerIndex > 0) {
							Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
							Server.playerHandler.players[c.playerIndex].hitDiff2 = damage;
							Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
							Server.playerHandler.players[c.playerIndex].updateRequired = true;
							//o.sendMessage("Your opponent's steal titan causes you damage.");
							c.sendMessage("Your Steel Titan Damages your Opponent.");
							c.getItems().deleteItem(12825, 1);
							c.startAnimation(1914);
					}
				} else if (c.hasFollower == 7340 && c.getItems().playerHasItem(12833, 1)) { // 12833 is scroll
					final int damage = Misc.random(18) + 5;
					if(c.npcIndex > 0) {
							NPCHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
							NPCHandler.npcs[c.npcIndex].updateRequired = true;
							NPCHandler.npcs[c.npcIndex].hitDiff2 = damage;
							NPCHandler.npcs[c.npcIndex].HP -= damage;
							c.sendMessage("Your Geyser Titan Damages your Opponent.");
							c.getItems().deleteItem(12833, 1);
							c.startAnimation(1914);
					} else if(c.oldPlayerIndex > 0 || c.playerIndex > 0) {
							Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
							Server.playerHandler.players[c.playerIndex].hitDiff2 = damage;
							Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
							Server.playerHandler.players[c.playerIndex].updateRequired = true;
							//o.sendMessage("Your opponent's steal titan causes you damage.");
							c.sendMessage("Your Geyser Titan Damages your Opponent.");
							c.getItems().deleteItem(12833, 1);
							c.startAnimation(1914);
					}
				} else if (c.hasFollower == 7356 && c.getItems().playerHasItem(12824, 1)) { // is scroll 12824
					final int damage = Misc.random(17) + 4;
					if(c.npcIndex > 0) {
							NPCHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
							NPCHandler.npcs[c.npcIndex].updateRequired = true;
							NPCHandler.npcs[c.npcIndex].hitDiff2 = damage;
							NPCHandler.npcs[c.npcIndex].HP -= damage;
							c.sendMessage("Your Fire Titan Damages your Opponent.");
							c.getItems().deleteItem(12824, 1);
							c.startAnimation(1914);
					} else if(c.oldPlayerIndex > 0 || c.playerIndex > 0) {
							Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
							Server.playerHandler.players[c.playerIndex].hitDiff2 = damage;
							Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
							Server.playerHandler.players[c.playerIndex].updateRequired = true;
							//o.sendMessage("Your opponent's steal titan causes you damage.");
							c.sendMessage("Your Fire Titan Damages your Opponent.");
							c.getItems().deleteItem(12824, 1);
							c.startAnimation(1914);
					}
				} else if (c.hasFollower == 7350 && c.getItems().playerHasItem(12827, 1)) { // 12827
					final int damage = Misc.random(16) + 3;
					if(c.npcIndex > 0) {
							NPCHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
							NPCHandler.npcs[c.npcIndex].updateRequired = true;
							NPCHandler.npcs[c.npcIndex].hitDiff2 = damage;
							NPCHandler.npcs[c.npcIndex].HP -= damage;
							c.sendMessage("Your Abyssal Titan Damages your Opponent.");
							c.getItems().deleteItem(12827, 1);
							c.startAnimation(1914);
					} else if(c.oldPlayerIndex > 0 || c.playerIndex > 0) {
							Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
							Server.playerHandler.players[c.playerIndex].hitDiff2 = damage;
							Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
							Server.playerHandler.players[c.playerIndex].updateRequired = true;
							//o.sendMessage("Your opponent's steal titan causes you damage.");
							c.sendMessage("Your Abyssal Titan Damages your Opponent.");
							c.getItems().deleteItem(12827, 1);
							c.startAnimation(1914);
					}
				} else if (c.hasFollower == 7358 && c.getItems().playerHasItem(12824, 1)) {
					final int damage = Misc.random(15) + 2;
					if(c.npcIndex > 0) {
							NPCHandler.npcs[c.npcIndex].hitUpdateRequired2 = true;
							NPCHandler.npcs[c.npcIndex].updateRequired = true;
							NPCHandler.npcs[c.npcIndex].hitDiff2 = damage;
							NPCHandler.npcs[c.npcIndex].HP -= damage;
							c.sendMessage("Your Moss Titan Damages your Opponent.");
							c.getItems().deleteItem(12824, 1);
							c.startAnimation(1914);
					} else if(c.oldPlayerIndex > 0 || c.playerIndex > 0) {
							Server.playerHandler.players[c.playerIndex].playerLevel[3] -= damage;
							Server.playerHandler.players[c.playerIndex].hitDiff2 = damage;
							Server.playerHandler.players[c.playerIndex].hitUpdateRequired2 = true;
							Server.playerHandler.players[c.playerIndex].updateRequired = true;
							//o.sendMessage("Your opponent's steal titan causes you damage.");
							c.sendMessage("Your Moss Titan Damages your Opponent.");
							c.getItems().deleteItem(12824, 1);
							c.startAnimation(1914);
					}
				} else if (c.hasFollower == 6874 && c.getItems().playerHasItem(12435, 1)) { // 12435
					c.getItems().addItem(15272, 4);
					c.sendMessage("Your Pak Yack's Special Supplys you with 4 rocktails!");
					c.sendMessage("You can receive food again in 4 minutes.");
					c.getItems().deleteItem(12435, 1);
				} else if (c.hasFollower == 6823 && c.getItems().playerHasItem(12434, 1)) { // 12435
					c.playerLevel[3] += 13;
					if(c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
					c.sendMessage("Your Unicorn's Special Heals you for 13 HP!");
					c.sendMessage("You can heal yourself again in 4 minutes.");
					c.getItems().deleteItem(12434, 1);
					c.getPA().refreshSkill(3);
				} else if (c.hasFollower == 6814 && c.getItems().playerHasItem(12438, 1)) { // 12435
					c.playerLevel[3] += 8;
					if(c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
					c.sendMessage("Your Bunyip's Special Heals you for 8 HP!");
					c.sendMessage("You can heal yourself again in 4 minutes.");
					c.getItems().deleteItem(12438, 1);
					c.getPA().refreshSkill(3);
				} else if (c.hasFollower == 6870 && c.getItems().playerHasItem(12437, 1)) { // 12435
					c.playerLevel[3] += 15;
					c.playerLevel[6] += 6;
					c.sendMessage("Your Wolpertinger's Special Heals you for 15 HP!");
					c.sendMessage("Your Wolpertinger's Increases and Restores your Magic skill!");
					if(c.playerLevel[6] > c.getLevelForXP(c.playerXP[6]))
					c.playerLevel[6] = c.getLevelForXP(c.playerXP[6])+6;
					if(c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
					c.getPA().refreshSkill(3);
					c.getPA().refreshSkill(6);
										c.getItems().deleteItem(12437, 1);
				} else {
					c.sendMessage("You have no familiar or you have no scrolls!");
				}
			c.summonSpec = 240;
			} else {
				c.sendMessage("You must wait at least 4 Minutes before using this again.");
			}
			break;*/
			
		case 154:
			if(System.currentTimeMillis() - c.logoutDelay < 8000) {
					c.sendMessage("You cannot do skillcape emotes in combat!");
			return;
			}
			if(System.currentTimeMillis() - c.lastEmote >= 7000) {
				if(c.getPA().wearingCape(c.playerEquipment[c.playerCape])) {
					c.stopMovement();
					c.gfx0(c.getPA().skillcapeGfx(c.playerEquipment[c.playerCape]));
					c.startAnimation(c.getPA().skillcapeEmote(c.playerEquipment[c.playerCape]));
				} else if(c.playerEquipment[c.playerCape] == 19710) {
						c.getPA().dungemote(c);
				} else if(c.playerEquipment[c.playerCape] == 19140) {
						c.getPA().compemote(c);
				} else if(c.playerEquipment[c.playerCape] == 19138) {
						c.getPA().compemote(c);
				} else if(c.playerEquipment[c.playerCape] == 18509) {
						c.getPA().dungemote2(c);
				} else if(c.playerEquipment[c.playerCape] == 19709) {
						c.getPA().dungemote(c);
				} else if(c.playerEquipment[c.playerCape] == 18508) {
						c.getPA().dungemote(c);
				} else {
					c.sendMessage("You must be wearing a Skillcape to do this emote.");
				}
				c.lastEmote = System.currentTimeMillis();
			}
		break;

		//2nd tele option
		case 9191:
			if (c.teleAction == 199) {
				c.getPA().spellTeleport(2724, 3500, 0);
				c.sendMessage("To start, enter the portal!");
				c.sendMessage("Everytime you kill a boss in the minigame, a pair of gloves will be unlocked.");
				c.sendMessage("To buy gloves click the chest.");
			}
			if (c.dialogueAction == 106) {
				if(c.getItems().playerHasItem(c.diceID, 1)) {
					c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
					c.getItems().addItem(15088, 1);
					c.sendMessage("You get two six-sided dice out of the dice bag.");
				}
				c.getPA().closeAllWindows();
			} else if (c.dialogueAction == 50) {
				c.trade11 = 900;
				c.trade11();
	/*			if (Server.singleStarter.contains(c.connectedFrom)) {
					c.sendMessage("You have already received a starter recently. You may receive another later.");
					c.selectStarter = false;
					c.getPA().closeAllWindows();
					return;
				}*/
				c.getItems().addItem(1704, 1);
				c.getItems().addItem(995,5000000);
			//	Server.singleStarter.add(c.connectedFrom);
				c.getPA().closeAllWindows();
			//	c.getItems().addItem(6950, 1);
				c.getItems().addItem(4588, 10);
				c.getItems().addItem(1080, 10);
				c.getItems().addItem(1128, 10);
				c.getItems().addItem(3106, 10);
				c.getItems().addItem(4676, 10);
				c.getItems().addItem(2413, 1);
				c.getItems().addItem(11119, 10);
				c.getItems().addItem(3842, 1);
				c.getItems().addItem(3752, 10);
				c.getItems().addItem(10499, 1);
				c.getItems().addItem(5699, 10);
				c.getItems().addItem(4154, 10);
				c.getItems().addItem(555, 1000);
				c.getItems().addItem(560, 1000);
				c.getItems().addItem(565, 1000);
				c.getItems().addItem(4094, 10);
				c.getItems().addItem(4092, 10);
				c.getItems().addItem(386, 1500);
				c.getItems().addItem(2498, 10);
				c.getItems().addItem(146, 100);
				c.getItems().addItem(158, 100);
				c.getItems().addItem(2435, 100);
				c.getItems().addItem(170, 100);
				c.getItems().addItem(3041, 100);
				c.getPA().addSkillXP(3358594, 4);
				c.getPA().addSkillXP(3358594, 6);
				c.getPA().addSkillXP(127660, 5);
				c.getPA().addSkillXP(277742, 0);
				c.getPA().addSkillXP(3358594, 2);
				c.getPA().addSkillXP(3358594, 3);
				c.getPA().addSkillXP(40224, 1);
				c.playerLevel[3] = 85;
				c.playerLevel[5] = 52;
				c.getPA().refreshSkill(3);
				c.getPA().refreshSkill(0);
				c.getPA().refreshSkill(6);
				c.getPA().refreshSkill(4);
				c.getPA().refreshSkill(2);
				c.getPA().refreshSkill(1);
				c.getPA().refreshSkill(5);			
				c.sendMessage("<shad=15369497>To get RFD gloves, do the Recipe for Disaster minigame south of edgeville general store!!<shad=15369497>");
				c.sendMessage("This server is one of the best.. Real summoning.. Ect.. Enjoy it!");
				c.dialogueAction = -1;
				c.getPA().requestUpdates();
				//c.getDH().sendDialogues(22, -1);
				c.selectStarter = false;
				c.selectStarterr = false;
				c.getPA().showInterface(3559);
				c.canChangeAppearance = true;
			} else if (c.dialogueAction == 107) {
				if(c.getItems().playerHasItem(c.diceID, 1)) {
					c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
					c.getItems().addItem(15094, 1);
					c.sendMessage("You get a twelve-sided die out of the dice bag.");
				}
				c.getPA().closeAllWindows();
			}
			if (c.teleAction == 1) {
				//tav dungeon
				c.getPA().spellTeleport(2884, 9798, 0);
			} else if (c.teleAction == 2) {
				//pest control
				c.getPA().spellTeleport(2662, 2650, 0);
			} else if (c.teleAction == 3) {
				//kbd
				c.getPA().spellTeleport(3007, 3849, 0);
			} else if (c.teleAction == 6) {
				//Giant Mole REDONE TO FORGOTTEN WARRIOR
				c.getPA().spellTeleport(2517, 3044, 0);
			} else if (c.teleAction == 4) {
				//graveyard
				c.getPA().spellTeleport(3243, 3517, 0);
			} else if (c.teleAction == 5) {
				//c.getPA().spellTeleport(3300, 3302,0);
				c.getDH().sendOption2("Mining", "Smithing");
				c.teleAction = 999;
			} else if (c.teleAction == 8) {
				c.getPA().spellTeleport(2984,9630,0);
				c.sendMessage("Beware: Recommended team of 5 Players or More");
			} else if (c.teleAction == 20) {
				c.getPA().spellTeleport(3210,3424,0);//3210 3424
			}
			if (c.dialogueAction == 10) {
				c.getPA().spellTeleport(2796, 4818, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 11) {
				c.getPA().spellTeleport(2527, 4833, 0);
				c.dialogueAction = -1;
			}
			if (c.teleAction == 21) { // hunter
				c.getPA().spellTeleport(3492, 3488, 0);
				c.sendMessage("<shad=6081134>For information on how to train RC talk to Aubury.");
				c.dialogueAction = -1;
			}
		break;
			//3rd tele option	


		case 9192:
			if (c.teleAction == 199) { // Nomad and angry goblin
				c.sendMessage("To fight Nomad, talk to him");
				c.sendMessage("To fight Angry Goblin talk to Ticket Goblin");
				c.getPA().spellTeleport(3078, 3505, 0);
			}
			if (c.teleAction == 21) { // Summoning
				c.getPA().spellTeleport(3450, 3515, 0);
				c.dialogueAction = -1;
			}
			if (c.dialogueAction == 106) {
				if(c.getItems().playerHasItem(c.diceID, 1)) {
					c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
					c.getItems().addItem(15100, 1);
					c.sendMessage("You get a four-sided die out of the dice bag.");
				}
				c.getPA().closeAllWindows();
			} else if (c.dialogueAction == 50) {
				c.trade11 = 900;
				c.trade11();
			/*	if (Server.singleStarter.contains(c.connectedFrom)) {
					c.sendMessage("You have already received a starter recently. You may receive another later.");
					c.selectStarter = false;
					c.getPA().closeAllWindows();
					return;
				}*/
				c.getItems().addItem(1704, 1);
				c.getItems().addItem(995,5000000);
				//Server.singleStarter.add(c.connectedFrom);
				c.getPA().closeAllWindows();
				//c.getItems().addItem(6950, 1);
				c.getItems().addItem(6329, 10);
				c.getItems().addItem(2504, 10);
				c.getItems().addItem(892, 10000);
				c.getItems().addItem(9186, 10);
				c.getItems().addItem(862, 10);
				c.getItems().addItem(9244, 300);
				c.getItems().addItem(2492, 10);
				c.getItems().addItem(3750, 10);
				c.getItems().addItem(10499, 1);
				c.getItems().addItem(9075, 1000);
				c.getItems().addItem(557, 1000);
				c.getItems().addItem(560, 1000);
				c.getItems().addItem(386, 1500);
				c.getItems().addItem(2498, 10);
				c.getItems().addItem(2435, 100);
				c.getItems().addItem(170, 100);
				c.getItems().addItem(3041, 100);
				c.getPA().addSkillXP(3368594, 4);
				c.getPA().addSkillXP(3368594, 6);
				c.getPA().addSkillXP(3368594, 3);
				c.getPA().addSkillXP(3368594, 1);
				c.getPA().addSkillXP(127660, 5);
				c.playerLevel[3] = 85;
				c.playerLevel[5] = 52;
				c.getPA().refreshSkill(3);
				c.getPA().refreshSkill(5);
				c.getPA().refreshSkill(4);
				c.getPA().refreshSkill(6);
				c.getPA().refreshSkill(1);
				c.sendMessage("<shad=15369497>To get RFD gloves, do the Recipe for Disaster minigame south of edgeville general store!!<shad=15369497>");
				c.sendMessage("This server is one of the best.. Real summoning.. Ect.. Enjoy it!");
				c.dialogueAction = -1;
				//c.getDH().sendDialogues(22, -1);
				c.getPA().requestUpdates();
				c.selectStarter = false;
				c.selectStarterr = false;
				c.getPA().showInterface(3559);
				c.canChangeAppearance = true;
			} else if (c.dialogueAction == 107) {
				if(c.getItems().playerHasItem(c.diceID, 1)) {
					c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
					c.getItems().addItem(15096, 1);
					c.sendMessage("You get a twenty-sided die out of the dice bag.");
				}
				c.getPA().closeAllWindows();
			}
			if (c.teleAction == 1) {
				//slayer tower
				c.getPA().spellTeleport(3428, 3537, 0);
			} else if (c.teleAction == 2) {
				//tzhaar
				c.getPA().spellTeleport(2438, 5168, 0);
				c.sendMessage("To fight Jad, enter the cave.");
			} else if (c.teleAction == 3) {
				//dag kings
				c.getPA().spellTeleport(1910, 4367, 0);
				c.sendMessage("Climb down the ladder to get into the lair.");
			} else if (c.teleAction == 6) {
				// MAD MUMMY
				c.getPA().spellTeleport(2962, 9631, 0);
				c.sendMessage("He mages & melees!");
			} else if (c.teleAction == 4) {
				//Lava Crossing
				//c.getPA().spellTeleport(3367, 3935, 0);
				c.sendMessage("Disabled atm due to people missclicking & going 50 wild..");			
			} else if (c.teleAction == 5) {
				c.getPA().spellTeleport(2597,3408,0);
			} else if (c.teleAction == 20) {
				c.getPA().spellTeleport(2757,3477,0);
			}
			if (c.dialogueAction == 10) {
				c.getPA().spellTeleport(2713, 4836, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 11) {
				c.getPA().spellTeleport(2162, 4833, 0);
				c.dialogueAction = -1;
			} else if (c.dialogueAction == 12) {
				c.getPA().spellTeleport(2207, 4836, 0);
				c.dialogueAction = -1;
			}
			if (c.teleAction == 8) { // Used to be the latest boss teleport option, now its gwd, was goblin/nomad
				c.sendMessage("If there is no floor/maps are gone RESTART CLIENT!");
				c.getPA().spellTeleport(2882, 5310, 2);
			/*	c.sendMessage("To fight Nomad, talk to him");
				c.sendMessage("To fight Angry Goblin talk to Ticket Goblin");
				c.getPA().spellTeleport(3078, 3505, 0);*/
			}
		break;

		case 9193:
			if (c.teleAction == 21) { // Dung by Monsterray
				c.getPA().spellTeleport(2533, 3304, 0);
				c.dialogueAction = -1;
				c.sendMessage("You Teleport to the dung area");
			}
				if (c.dialogueAction == 106) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15090, 1);
						c.sendMessage("You get an eight-sided die out of the dice bag.");
					}
					c.getPA().closeAllWindows();
					
							} else if (c.dialogueAction == 50) {
							c.trade11 = 900;
							c.trade11();
		/*	if (Server.singleStarter.contains(c.connectedFrom)) {
				c.sendMessage("You have already received a starter recently. You may receive another later.");
				c.selectStarter = false;
				c.getPA().closeAllWindows();
				return;
			}*/
			c.getItems().addItem(1704, 1);
			c.getItems().addItem(995,5000000);
			//Server.singleStarter.add(c.connectedFrom);
			c.getPA().closeAllWindows();
			//c.getItems().addItem(6950, 1);
			c.getItems().addItem(4587, 1);
			c.getItems().addItem(1079, 1);
			c.getItems().addItem(1127, 1);
			c.getItems().addItem(1540, 1);
			c.getItems().addItem(10828, 1);
			c.getItems().addItem(11732, 1);
			c.getItems().addItem(6568, 1);
			c.getItems().addItem(2497, 1);
			c.getItems().addItem(2503, 1);
			c.getItems().addItem(11118, 1);
			c.getItems().addItem(10499, 1);
			c.getItems().addItem(2570, 1);
			c.getItems().addItem(5698, 1);
			c.getItems().addItem(386, 1500);
			c.getItems().addItem(892, 1000);
			c.getItems().addItem(9185, 1);
			c.getItems().addItem(861, 1);
			c.getItems().addItem(9244, 300);
			c.getPA().addSkillXP(2000000, 0);
			c.getPA().addSkillXP(2000000, 1);
			c.getPA().addSkillXP(2000000, 2);
			c.getPA().addSkillXP(2000000, 3);
			c.getPA().addSkillXP(2000000, 4);
			c.getPA().addSkillXP(750627, 5);
			c.getPA().addSkillXP(2000000, 6);
			c.playerLevel[3] = 80;
			c.playerLevel[5] = 70;
			c.getPA().refreshSkill(3);
			c.getPA().refreshSkill(5);
			c.sendMessage("<shad=15369497>To get RFD gloves, do the Recipe for Disaster minigame south of edgeville general store!!<shad=15369497>");
			c.sendMessage("This server is one of the best.. Real summoning.. Ect.. Enjoy it!");
			c.dialogueAction = -1;
			c.getPA().requestUpdates();
			//c.getDH().sendDialogues(22, -1);
c.selectStarter = false;
c.selectStarterr = false;
							c.getPA().showInterface(3559);
				c.canChangeAppearance = true;
				} else if (c.dialogueAction == 107) {
					if(c.getItems().playerHasItem(c.diceID, 1)) {
						c.getItems().deleteItem(c.diceID, c.getItems().getItemSlot(c.diceID), 1);	
						c.getItems().addItem(15098, 1);
						c.sendMessage("You get the percentile dice out of the dice bag.");
				}
					c.getPA().closeAllWindows();
				}
				if (c.teleAction == 1) {
					//brimhaven dungeon
					//c.getPA().spellTeleport(2710, 9466, 0);
					//c.sendMessage("You teleported to brimhaven dungeon, be sure to bring antifire-shield.");
					c.sendMessage("Brimhaven is disabled at the moment.");
				} else if (c.teleAction == 2) {
					//duel arena
					//c.getPA().spellTeleport(3358, 3269, 0);   
				c.sendMessage("Duel Arena is disabled at the moment.");
				} else if (c.teleAction == 6) {
									//Barrelschest
					c.getPA().spellTeleport(2367, 4956, 0);
				// }
				} else if (c.teleAction == 3) {
					//chaos elemental
					c.getPA().spellTeleport(2717, 9805, 0);
					//c.getPA().spellTeleport(2611,3396,0);
				} else if (c.teleAction == 4) {
					//Fala
				c.getPA().spellTeleport(3211, 3422, 0);

				} else if (c.teleAction == 5) {
				c.getDH().sendOption2("WoodCutting", "FireMaking");
				c.teleAction = 22;
					//c.getPA().spellTeleport(2710,3462,0);
					//c.sendMessage("Good luck WCing!");
				}
				if (c.dialogueAction == 10) {
					c.getPA().spellTeleport(2660, 4839, 0);
					c.dialogueAction = -1;
				} else if (c.teleAction == 20) {
					c.getPA().spellTeleport(2964,3378,0);
				}
				if (c.teleAction == 8) {
					c.getPA().startTeleport(2465, 4770, 0, "ancient");
					c.sendMessage("Beware of the Snakes!.");
				}
				break;
			case 9194:
							if (c.teleAction == 21) {
					c.getDH().sendOption2("Fletching", "Thieving");
					}
				if (c.dialogueAction == 107) {
					c.getDH().sendDialogues(106, 4289);
					return;
					}
				if (c.dialogueAction == 106) {
					c.getDH().sendDialogues(107, 4289);
					return;
					}
				if (c.teleAction == 1) {
					//island
					c.getPA().spellTeleport(3117, 9847, 0);
					
					
							} else if (c.dialogueAction == 50) {
							c.trade11 = 900;
							c.trade11();
			c.getItems().addItem(1704, 1);
			c.getItems().addItem(995,5000000);
			//Server.singleStarter.add(c.connectedFrom);
			c.getPA().closeAllWindows();
			c.getItems().addItem(995, 5000000);
			c.getItems().addItem(590, 1);
			c.getItems().addItem(6739, 1);
			c.getItems().addItem(1704, 1);
			c.getItems().addItem(554,200);
			c.getItems().addItem(555,200);
			c.getItems().addItem(556,200);
			c.getItems().addItem(558,600);
			c.getItems().addItem(1381,1);
			c.getItems().addItem(1323,1);
			c.getItems().addItem(1067,1);
			c.getItems().addItem(1115,1);
			c.getItems().addItem(1153,1);
			c.getItems().addItem(3105,1);
			c.getItems().addItem(841,1);
			c.getItems().addItem(882,500);
			c.getItems().addItem(380,100);
			c.getItems().addItem(11118,1);
			c.getItems().addItem(379,5);
			Connection.addIpToStarterList1(Server.playerHandler.players[c.playerId].connectedFrom);
			Connection.addIpToStarter1(Server.playerHandler.players[c.playerId].connectedFrom);
			c.sendMessage("You have recieved 1 out of 2 starter packages on this IP address.");
			c.dialogueAction = -1;
			c.getPA().requestUpdates();
		//	c.getDH().sendDialogues(22, -1);
c.selectStarter = false;
c.selectStarterr = false;
							c.getPA().showInterface(3559);
				c.canChangeAppearance = true;
	
				} else if (c.teleAction == 2) {
					//last minigame spot
					c.getPA().spellTeleport(2865,3546,0);
					//c.getPA().closeAllWindows();
				} else if (c.teleAction == 3) {
					c.getPA().spellTeleport(3302,9372,0);
					c.sendMessage("Enter the gate to fight the mighty Corporeal Beast!");
					c.sendMessage("Note: Magic protect, Ruby bolts (e) and Diamond bolts (e) are recommended!");
									} else if (c.teleAction == 6) {
														c.sendMessage("If there is no floor/maps are gone RESTART CLIENT!");
					c.getPA().spellTeleport(2882, 5310, 2);
					//c.getPA().spellTeleport(3079,3504,0);
					//c.sendMessage("To fight Angry goblin talk to Ticket Goblin, otherwise talk to Nomad.");
					c.getPA().closeAllWindows();
				} else if (c.teleAction == 4) {
					c.getPA().spellTeleport(2980, 3617, 0);
				} else if (c.teleAction == 5) {
					c.getPA().spellTeleport(2812,3463,0);
				}
				if (c.dialogueAction == 10 || c.dialogueAction == 11) {
					c.dialogueId++;
					c.getDH().sendDialogues(c.dialogueId, 0);
				} else if (c.dialogueAction == 12) {
					c.dialogueId = 17;
					c.getDH().sendDialogues(c.dialogueId, 0);
				
				} else if (c.teleAction == 20) {
					c.getPA().spellTeleport(3493,3484,0);

                                } else if (c.teleAction == 8) {
					c.getPA().startTeleport(2916, 3628, 12, "ancient");
					c.sendMessage("The Brutal Avatar of Destruction, Good Luck!");
					c.sendMessage("He has 2x hp bars, more then 2 ppl recommended!");
				}
				break;
			
		case 71074:
			/*if (c.clanId >= 0 && Server.clanChat.clans[c.clanId].owner.equalsIgnoreCase(c.playerName)) {
				if (c.CSLS == 0) {
	if(System.currentTimeMillis() - c.lastEmote >= 1500) {
					Server.clanChat.clans[c.clanId].CS = 1;
					Server.clanChat.sendLootShareMessage(c.clanId, "LootShare has been toggled to " + (!Server.clanChat.clans[c.clanId].lootshare ? "ON" : "OFF") + " by the clan leader.");
					Server.clanChat.clans[c.clanId].lootshare = !Server.clanChat.clans[c.clanId].lootshare;
					c.CSLS = 1;
					Server.clanChat.updateClanChat(c.clanId);
		c.lastEmote = System.currentTimeMillis();
					return;
			}	
			}	
				if (c.CSLS == 1) {
	if(System.currentTimeMillis() - c.lastEmote >= 1500) {
					c.CSLS = 2;
					Server.clanChat.clans[c.clanId].CS = 2;
					Server.clanChat.updateClanChat(c.clanId);
					Server.clanChat.sendLootShareMessage(c.clanId, "LootShare has been toggled to " + (!Server.clanChat.clans[c.clanId].lootshare ? "ON" : "OFF") + " by the clan leader.");
					Server.clanChat.clans[c.clanId].lootshare = !Server.clanChat.clans[c.clanId].lootshare;
		c.lastEmote = System.currentTimeMillis();
					return;

			}	
			}	
				if (c.CSLS == 2) {
	if(System.currentTimeMillis() - c.lastEmote >= 1500) {
					if(Server.clanChat.clans[c.clanId].playerz == 1) {
					c.sendMessage("There must be atleast 2 members in the clan chat to toggle Coinshare ON.");
					c.CSLS = 0;
					Server.clanChat.clans[c.clanId].CS = 0;
					Server.clanChat.updateClanChat(c.clanId);
		c.lastEmote = System.currentTimeMillis();
					return;
					}
					c.CSLS = 3;
					Server.clanChat.clans[c.clanId].CS = 3;
					Server.clanChat.updateClanChat(c.clanId);
					Server.clanChat.sendCoinShareMessage(c.clanId, "CoinShare has been toggled to " + (!Server.clanChat.clans[c.clanId].coinshare ? "ON" : "OFF") + " by the clan leader.");
					Server.clanChat.clans[c.clanId].coinshare = !Server.clanChat.clans[c.clanId].coinshare;
					return;

			}	
			}	
				if (c.CSLS == 3) {
	if(System.currentTimeMillis() - c.lastEmote >= 1500) {
					c.CSLS = 0;
					Server.clanChat.clans[c.clanId].CS = 0;
					Server.clanChat.updateClanChat(c.clanId);
					Server.clanChat.sendCoinShareMessage(c.clanId, "CoinShare has been toggled to " + (!Server.clanChat.clans[c.clanId].coinshare ? "ON" : "OFF") + " by the clan leader.");
					Server.clanChat.clans[c.clanId].coinshare = !Server.clanChat.clans[c.clanId].coinshare;
		c.lastEmote = System.currentTimeMillis();
					return;
			}	
			}	
				} else {
					c.sendMessage("Only the owner of the clan has the power to do that.");
			}*/
			c.sendMessage("Lootshare is currently disabled, will be fixed soon.");	
		break;
		
		case 34185: case 34184: case 34183: case 34182: case 34189: case 34188: case 34187: case 34186: case 34193: case 34192: case 34191: case 34190:
			if (c.craftingLeather)
				c.getCrafting().handleCraftingClick(actionButtonId);
			if (c.getFletching().fletching)
				c.getFletching().handleFletchingClick(actionButtonId);
		break;
		
	
		
		case 15147:
			if (c.smeltInterface) {
				c.smeltType = 2349;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 15146:
			if (c.smeltInterface) {
				c.smeltType = 2349;
				c.smeltAmount = 5;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 10247:
			if (c.smeltInterface) {
				c.smeltType = 2349;
				c.smeltAmount = 10;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
									
		case 9110:
			if (c.smeltInterface) {
				c.smeltType = 2349;
				c.smeltAmount = 28;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 15151:
			if (c.smeltInterface) {
				c.smeltType = 2351;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
					
		case 15150:
			if (c.smeltInterface) {
				c.smeltType = 2351;
				c.smeltAmount = 5;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
					
		case 15149:
			if (c.smeltInterface) {
				c.smeltType = 2351;
				c.smeltAmount = 10;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
					
		case 15148:
			if (c.smeltInterface) {
				c.smeltType = 2351;
				c.smeltAmount = 28;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 15159:
			if (c.smeltInterface) {
				c.smeltType = 2353;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
	
		case 15158:
			if (c.smeltInterface) {
				c.smeltType = 2353;
				c.smeltAmount = 5;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
					
		case 15157:
			if (c.smeltInterface) {
				c.smeltType = 2353;
				c.smeltAmount = 10;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
					
		case 15156:
			if (c.smeltInterface) {
				c.smeltType = 2353;
				c.smeltAmount = 28;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 29017:
			if (c.smeltInterface) {
				c.smeltType = 2359;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 29016:
			if (c.smeltInterface) {
				c.smeltType = 2359;
				c.smeltAmount = 5;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 24253:
			if (c.smeltInterface) {
				c.smeltType = 2359;
				c.smeltAmount = 10;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 16062:
			if (c.smeltInterface) {
				c.smeltType = 2359;
				c.smeltAmount = 28;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 29022:
			if (c.smeltInterface) {
				c.smeltType = 2361;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 29020:
			if (c.smeltInterface) {
				c.smeltType = 2361;
				c.smeltAmount = 5;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 29019:
			if (c.smeltInterface) {
				c.smeltType = 2361;
				c.smeltAmount = 10;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 29018:
			if (c.smeltInterface) {
				c.smeltType = 2361;
				c.smeltAmount = 28;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 29026:
			if (c.smeltInterface) {
				c.smeltType = 2363;
				c.smeltAmount = 1;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 29025://smelt 5
			if (c.smeltInterface) {
				c.smeltType = 2363;
				c.smeltAmount = 5;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 29024://smelt 10
			if (c.smeltInterface) {
				c.smeltType = 2363;
				c.smeltAmount = 10;
				c.getSmithing().startSmelting(c.smeltType);
			}
		break;
		
		case 59004:
			c.getPA().removeAllWindows();
		break;
		
		case 70212:
			if (c.clanId > -1)
				Server.clanChat.leaveClan(c.playerId, c.clanId);
			else
				c.sendMessage("You are not in a clan.");
		break;
		
		case 62137:
			if (c.clanId >= 0) {
				c.sendMessage("You are already in a clan.");
				break;
			}
			if (c.getOutStream() != null) {
				c.getOutStream().createFrame(187);
				c.flushOutStream();
			}	
		break;
		
		case 9178:
			if(c.dialogueAction == 90) {
				sentBankMes = false;
				if((c.playerEquipment[c.playerHat] == -1) && (c.playerEquipment[c.playerRing] == -1) && (c.playerEquipment[c.playerCape] == -1) && (c.playerEquipment[c.playerHands] == -1) && (c.playerEquipment[c.playerArrows] == -1) && (c.playerEquipment[c.playerAmulet] == -1) && (c.playerEquipment[c.playerChest] == -1) && (c.playerEquipment[c.playerShield] == -1) && (c.playerEquipment[c.playerLegs] == -1) && (c.playerEquipment[c.playerHands] == -1) && (c.playerEquipment[c.playerFeet] == -1) && (c.playerEquipment[c.playerWeapon] == -1)) {
					if((c.getItems().freeSlots() == 28)) {
						//c.sendMessage("Sorry, but the Dungeoneering skill is currently under developement.");
						c.getDungeoneering().startfloor1(c);
					} else {
						if (!sentBankMes == true) {
							c.sendMessage("<shad=15695415>Bank all your items first!");
							c.getPA().closeAllWindows();
							sentBankMes = true;
						}
					}
				} else {
					c.sendMessage("<shad=15695415>Please Un-Equip all your worn items before teleporting to Dungeoneering.");
					c.getPA().removeAllWindows();
					return;
				}
			}
			int npcType = 6138;		
			if (c.teleAction == 43) {
				c.getPA().startTeleport(3085, 3491, 0, "normal");
			}
			if (c.dialogueAction == 55) {
				ConstructionEvents.conChair2(c);
				c.getPA().removeAllWindows();
			}		
			if (c.dialogueAction == 59) {
				ConstructionEvents.conBook3(c);
				c.getPA().removeAllWindows();
			}	
			if (c.dialogueAction == 60) {
				ConstructionEvents.conAle2(c);
				c.getPA().removeAllWindows();
			}	
			if (c.dialogueAction == 56) {
				ConstructionEvents.conFern2(c);
				c.getPA().removeAllWindows();
			}		
			if (c.dialogueAction == 58) {
				ConstructionEvents.conTree2(c);
				c.getPA().removeAllWindows();
			}					
			if(c.dialogueAction == 42) {            
				if (c.inWild())
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.getPA().closeAllWindows();
						c.getDH().sendDialogues(420, npcType);
						c.sendMessage("Please Take everything off");
						return;
					}
				}
				try {
					int skilld = 1;
					int leveld = 1;
					c.playerXP[skilld] = c.getPA().getXPForLevel(leveld)+5;
					c.playerLevel[skilld] = c.getPA().getLevelForXP(c.playerXP[skilld]);
					c.getPA().refreshSkill(skilld);
					//c.getPA().closeAllWindows();
					c.getDH().sendDialogues(230, npcType);
				} catch (Exception e){}
			}
			if (c.usingGlory)
				c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
			c.gdegradeNow = true;
			c.getPA().gloryDegrade();
			if (c.dialogueAction == 2)
				c.getPA().startTeleport(3428, 3538, 0, "modern");
			if (c.dialogueAction == 3)		
				c.getPA().startTeleport(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0, "modern");
			if (c.dialogueAction == 4)
				c.getPA().startTeleport(3565, 3314, 0, "modern");
			if (c.dialogueAction == 20) {
				c.getPA().startTeleport(2897, 3618, 4, "modern");
			}
			if(c.dialogueAction == 100) {
				c.getDH().sendDialogues(25, 946);
			}
		break;
		
		
		case 9179:
			if(c.dialogueAction == 90) {
				c.getPA().closeAllWindows();
				if(c.playerLevel[24] >= 49) {
					c.getPA().closeAllWindows();
					c.getPA().movePlayer(3467, 9495, 0);
					c.sendMessage("<shad=16112652>Kill NPC's here for fast Dung XP/Tokens!");
					//c.sendMessage("<shad=16112652>You are taken to a underground cave!");
					return;
				} else {
					c.sendMessage("You need atleast a level of 49 Dungeoneering to enter this cave!");
					c.getPA().closeAllWindows();
					return;
				}
			}
			npcType = 6138;
			if (c.teleAction == 43) {
				c.getPA().startTeleport(3210, 3424, 0, "normal");
			}
			if (c.dialogueAction == 55) {
				ConstructionEvents.conChair3(c);
				c.getPA().removeAllWindows();
			}
			if(c.dialogueAction == 60) { 
								ConstructionEvents.conAle3(c);
				c.getPA().removeAllWindows();
			}
				   if (c.dialogueAction == 56) {
				ConstructionEvents.conFern3(c);
				c.getPA().removeAllWindows();
			}
			if (c.dialogueAction == 59) {
				ConstructionEvents.conBook4(c);
				c.getPA().removeAllWindows();
			}
			if (c.dialogueAction == 58) {
				ConstructionEvents.conTree3(c);
				c.getPA().removeAllWindows();
			}
			if(c.dialogueAction == 42) { //prayer
				if (c.inWild())
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
					c.getPA().closeAllWindows();
						c.getDH().sendDialogues(420, npcType);
						return;
					}
				}
				try {
					int skillp = 5;
					int levelp = 1;
					c.playerXP[skillp] = c.getPA().getXPForLevel(levelp)+5;
					c.playerLevel[skillp] = c.getPA().getLevelForXP(c.playerXP[skillp]);
					c.getPA().refreshSkill(skillp);
					//c.getPA().closeAllWindows();
					c.getDH().sendDialogues(260, npcType);
				} catch (Exception e){
				}
			}
			if (c.usingGlory)
				c.getPA().startTeleport(Config.AL_KHARID_X, Config.AL_KHARID_Y, 0, "modern");
			c.gdegradeNow = true;
			c.getPA().gloryDegrade();
			if (c.dialogueAction == 2)
				c.getPA().startTeleport(2884, 3395, 0, "modern");
			if (c.dialogueAction == 3)
				c.getPA().startTeleport(3243, 3513, 0, "modern");
			if (c.dialogueAction == 4)
				c.getPA().startTeleport(2444, 5170, 0, "modern");
			if (c.dialogueAction == 20) {
				c.getPA().startTeleport(2897, 3618, 12, "modern");
			}
			if(c.dialogueAction == 101) {
				c.getDH().sendDialogues(21, 946);
			}
			if(c.dialogueAction == 100) {
				c.getGamble().gambleBlackJack(c);
			}	
		break;
		
		case 9180:
			if(c.dialogueAction == 90) {
				c.getPA().removeAllWindows();
				c.sendMessage("<shad=6081134>Your current Dungeoneering level: "+c.getPA().getLevelForXP(c.playerXP[24])+"");
				c.sendMessage("<shad=6081134>Total amount of tokens: "+c.dungPoints+"");
			}
			if (c.teleAction == 43) {
				c.getPA().startTeleport(2060, 3146, 0, "normal");
			}
			if (c.dialogueAction == 55) {
				ConstructionEvents.conChair4(c);
				c.getPA().removeAllWindows();
			}
			if (c.dialogueAction == 60) {
				ConstructionEvents.conAle4(c);
				c.getPA().removeAllWindows();
			}
			if (c.dialogueAction == 59) {
				ConstructionEvents.conBook(c);
				c.getPA().removeAllWindows();
			}
			if (c.dialogueAction == 56) {
				ConstructionEvents.conFern4(c);
				c.getPA().removeAllWindows();
			}
			if (c.dialogueAction == 58) {
				ConstructionEvents.conTree4(c);
				c.getPA().removeAllWindows();
			}
			npcType = 6138;
			if(c.dialogueAction == 42) { //attack
				if (c.inWild())
					return;
				for (int j = 0; j < c.playerEquipment.length; j++) {
					if (c.playerEquipment[j] > 0) {
						c.getPA().closeAllWindows();
						c.getDH().sendDialogues(420, npcType);
						return;
					}
				}
				try {
					int skill = 0;
					int levela = 1;
					c.playerXP[skill] = c.getPA().getXPForLevel(levela)+5;
					c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
					c.getPA().refreshSkill(skill);
									//c.getPA().closeAllWindows();
				c.getDH().sendDialogues(240, npcType);
				} catch (Exception e){
				}
			}
			if (c.usingGlory)
				c.getPA().startTeleport(Config.DRAYNOR_X, Config.DRAYNOR_Y, 0, "modern");
			c.gdegradeNow = true;
			c.getPA().gloryDegrade();
			if (c.dialogueAction == 2)
				c.getPA().startTeleport(2471,10137, 0, "modern");	
			if (c.dialogueAction == 3)
				c.getPA().startTeleport(3363, 3676, 0, "modern");
			if (c.dialogueAction == 4)
				c.getPA().startTeleport(2659, 2676, 0, "modern");
			if (c.dialogueAction == 20) {
				c.getPA().startTeleport(2897, 3618, 8, "modern");
			}
			if(c.dialogueAction == 101) {
				c.getDH().sendDialogues(23, 946);
			}
			if(c.dialogueAction == 100) {
				if(!c.getItems().playerHasItem(995, 1000000)) {
					c.sendMessage("You need at least 1M coins to play this game!");
					c.getPA().removeAllWindows();
					break;
				}
				c.getGamble().playGame(c);
			}
		break;
		
		case 9181:
				if(c.dialogueAction == 90) {
				c.sendMessage("<shad=6081134>You currently have "+c.dungPoints+" Dungeoneering Tokens.");
				c.getShops().openShop(85);
				return;
			}
				if (c.teleAction == 43) {
	   c.getPA().startTeleport(2757, 3477, 0, "normal");
			}
				if (c.dialogueAction == 55) {
				ConstructionEvents.conChair(c);
				c.getPA().removeAllWindows();
			}
				if (c.dialogueAction == 60) {
				ConstructionEvents.conAle(c);
				c.getPA().removeAllWindows();
			}
				if (c.dialogueAction == 59) {
				ConstructionEvents.conBook2(c);
				c.getPA().removeAllWindows();
			}
				if (c.dialogueAction == 56) {
				ConstructionEvents.conFern(c);
				c.getPA().removeAllWindows();
			}
				if (c.dialogueAction == 58) {
				ConstructionEvents.conTree(c);
				c.getPA().removeAllWindows();
			}
				if (c.dialogueAction == 58) {
				ConstructionEvents.conTree(c);
				c.getPA().removeAllWindows();
			}
		npcType = 6138;
		if(c.dialogueAction == 42) { //allstats
			if (c.inWild())
				return;
			for (int j = 0; j < c.playerEquipment.length; j++) {
				if (c.playerEquipment[j] > 0) {
					c.getPA().closeAllWindows();
					c.getDH().sendDialogues(420, npcType);
					return;
				}
			}
			try {
				int skill1 = 0;
				int level = 1;
				c.playerXP[skill1] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill1] = c.getPA().getLevelForXP(c.playerXP[skill1]);
				c.getPA().refreshSkill(skill1);
				int skill2 = 1;
				//	int level = 1;
				c.playerXP[skill2] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill2] = c.getPA().getLevelForXP(c.playerXP[skill2]);
				c.getPA().refreshSkill(skill2);
				int skill3 = 2;
				//	int level = 1;
				c.playerXP[skill3] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill3] = c.getPA().getLevelForXP(c.playerXP[skill3]);
				c.getPA().refreshSkill(skill3);
				int skill4 = 3;
				level = 10;
				c.playerXP[skill4] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill4] = c.getPA().getLevelForXP(c.playerXP[skill4]);
				c.getPA().refreshSkill(skill4);
				int skill5 = 4;
				level = 1;
				c.playerXP[skill5] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill5] = c.getPA().getLevelForXP(c.playerXP[skill5]);
				c.getPA().refreshSkill(skill5);
				int skill6 = 5;
				//	int level = 1;
				c.playerXP[skill6] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill6] = c.getPA().getLevelForXP(c.playerXP[skill6]);
				c.getPA().refreshSkill(skill6);
				int skill7 = 6;
				//	int level = 1;
				c.playerXP[skill7] = c.getPA().getXPForLevel(level)+5;
				c.playerLevel[skill7] = c.getPA().getLevelForXP(c.playerXP[skill7]);
				c.getPA().refreshSkill(skill7);
				//c.getPA().closeAllWindows();
				c.getDH().sendDialogues(250, npcType);
			} catch (Exception e){}
		}
							if (c.usingGlory)
				c.getPA().startTeleport(Config.MAGEBANK_X, Config.MAGEBANK_Y, 0, "modern");
								c.gdegradeNow = true;
				c.getPA().gloryDegrade();
			if (c.dialogueAction == 2)
				c.getPA().startTeleport(2669,3714, 0, "modern");
			if (c.dialogueAction == 3)	
				c.getPA().startTeleport(2540, 4716, 0, "modern");
			if (c.dialogueAction == 4) {
				//c.getPA().startTeleport(3358, 3269, 0, "modern");
				c.sendMessage("Dueling is at your own risk. Refunds will not be given for items lost due to glitches.");
				c.sendMessage("If you get a black screen or freeze just type ::train.");
}
			if (c.dialogueAction == 20) {
				//c.getPA().startTeleport(3366, 3266, 0, "modern");
				//c.killCount = 0;
				c.sendMessage("This will be added shortly");
			} else if (c.dialogueAction == 10 || c.dialogueAction == 101) {
				c.dialogueAction = 0;
				c.getPA().removeAllWindows();
			} else {
				c.getPA().removeAllWindows();
			}
			c.dialogueAction = 0;
			break;
		
		case 1093:
		case 1094:
		case 1097:
		case 15486:
			if (c.autocastId > 0) {
				c.getPA().resetAutocast();
			} else {
				if (c.playerMagicBook == 1) {
					if (c.playerEquipment[c.playerWeapon] == 4675 || c.playerEquipment[c.playerWeapon] == 15486 || c.playerEquipment[c.playerWeapon] == 18355)
						c.setSidebarInterface(0, 1689);
					else
						c.sendMessage("You can't autocast ancients without an ancient, chaotic staff or a SOL.");
				} else if (c.playerMagicBook == 0) {
					if (c.playerEquipment[c.playerWeapon] == 4170 || c.playerEquipment[c.playerWeapon] == 15486 || c.playerEquipment[c.playerWeapon] == 15040) {
						c.setSidebarInterface(0, 12050);
					} else {
						c.setSidebarInterface(0, 1829);
					}	
				}
					
			}		
		break;
case 115121:
c.getPA().closeAllWindows();
break;

		case 9157:
		if (c.dialogueAction == 1327) {
				c.getItems().deleteAllItems();
				c.getPA().closeAllWindows();
				c.getDungeoneering().redostartfloor1(c);
				}
				if (c.dialogueAction == 4445) {
				if(c.getItems().playerHasItem(995, 250000)) {
				c.getPA().closeAllWindows();
				c.getItems().deleteItem(995, 250000);
				c.getItems().addItem(15121, 1);
				c.sendMessage("You've purchased a Milestone Cape!");
				} else {
				c.sendMessage("You need atleast 250K to buy the Milestone Cape!");
				c.getPA().closeAllWindows();
				}
				}
				if (c.dialogueAction == 1000) {
				c.getItems().addItem(c.floweritem, 1);
				c.getPA().objectToRemove(ClickItem.flowerX, ClickItem.flowerY); 
				ClickItem.flowerX = 0;
				ClickItem.flowerY = 0;
				ClickItem.flowers = 0;
				c.getPA().closeAllWindows();
															}
		if (c.dialogueAction == 333) {
		c.getPA().spellTeleport(3374, 9811, 0);
		c.getPA().closeAllWindows();
	//	c.sendMessage("Do not relog!");
		}
		if (c.dialogueAction == 222) {
		c.getPA().spellTeleport(2326, 3802, 0);
	//	c.sendMessage("Do not relog!");
		}
					if (c.dialogueAction == 111) {
					c.getPA().enterCaves();
					c.sendMessage("Do not relog!");
					c.getPA().closeAllWindows();
		return;
		}						if (c.dialogueAction == 112) {
					c.getPA().enterNewCaves();
					//c.sendMessage("Disabled atm");
					c.getPA().closeAllWindows();
		return;
		}
		if (c.dialogueAction == 77) {
		if (c.hasHouse > 0) {
		c.sendMessage("You alredy have a house! Use a tablet to teleport to it!");
		return;
		}
		if (c.getItems().playerHasItem(995, 25000000)) {
		c.getDH().sendDialogues(29170, 4547);
		c.getItems().deleteItem(995, 25000000);
c.vlsLeft2 = 10;
		c.SaveGame();
		break;
		} else {
		if (!c.getItems().playerHasItem(995, 25000000)) {
		c.sendMessage("You don't have any money!");
		c.getPA().closeAllWindows();
		}
		}
		}
		
			if (c.teleAction == 966) {
				c.getPA().spellTeleport(2928, 5203, 0);
				////c.getPA().sendMp3("Music");
				}
				if (c.teleAction == 21) { // FLETCHING
				c.getPA().spellTeleport(2709, 3463, 0);
				}
				if(c.teleAction == 999) {
				c.getPA().spellTeleport(3300, 3302,0);
				c.sendMessage("Have fun Mining!");
				}
					if(c.teleAction == 5) {
						c.getPA().spellTeleport(2741, 3444, 0);
			c.sendMessage("Flax Picking & Flax Stringing perfected by Monsterray, enjoy!");
			c.sendMessage("You can also craft amulets, buy the uncut's from the Crafter!");
	}
		if(c.teleAction == 22) { // wcing woodcutting
				c.getPA().spellTeleport(2710,3462,0);
				}
						if(c.dialogueAction == 510) {
				c.getDH().sendDialogues(1055, 278);
				return;
			}
					if (c.dialogueAction == 120) { 
			if(Config.ALLOWPINS){
				if(!c.hasBankPin){
					c.getBankPin().openPin();
				} else {
					c.sendMessage("You already have a bank pin!");
				}
			} else {
				c.sendMessage("You may not set a bank pin now!");
			}
		// }
		break;
			} else if(c.dialogueAction == 1) {
				int r = 4;
				//int r = Misc.random(3);
				switch(r) {
					case 0:
						c.getPA().movePlayer(3534, 9677, 0);
						break;
					
					case 1:
						c.getPA().movePlayer(3534, 9712, 0);
						break;
					
					case 2:
						c.getPA().movePlayer(3568, 9712, 0);
						break;
					
					case 3:
						c.getPA().movePlayer(3568, 9677, 0);
						break;
					case 4:
						c.getPA().movePlayer(3551, 9694, 0);
						break;
				}
			} else if (c.dialogueAction == 2) {
				c.getPA().movePlayer(2507, 4717, 0);		
			} else if (c.dialogueAction == 5) {
				c.getSlayer().giveTask();
			} else if (c.dialogueAction == 6) {
				c.getSlayer().giveTask2();
			} else if (c.dialogueAction == 211) {
				c.sendMessage("Dialogue Closed");
			} else if (c.dialogueAction == 7) {
				c.getPA().startTeleport(3088,3933,0,"modern");
				c.sendMessage("NOTE: You are now in the wilderness...");
			} else if (c.dialogueAction == 50) {
				c.sendMessage("This is ");
			} else if (c.dialogueAction == 34) {
				c.sendMessage("Coming soon ");
			} else if (c.dialogueAction == 8) {
				c.getPA().resetBarrows();
				c.sendMessage("Your barrows have been reset.");
				} else if (c.dialogueAction == 200) {
				c.getPA().enterElvarg();
				c.sendMessage("The dragon is soon home, pot up!!");
				//c.getPA().movePlayer(2855, 9637, c.playerId * 4);
				} else if (c.dialogueAction == 555) {
				c.getDH().sendDialogues(556,553);
				c.sendMessage("You've now started Rune Mysteries Quest.");
			} else if (c.dialogueAction == 259) {
			c.getPA().removeAllWindows();
			} else if(c.nextChat == 999) {
				c.teleAction = 0;
				if(c.hasFollower > 0 && c.totalstored > -1) {
					for(int i = 0; i < 29; i++) {
						Server.itemHandler.createGroundItem(c, c.storeditems[i], c.absX, c.absY, 1, c.playerId);
						c.storeditems[i] = -1;
						c.occupied[i] = false;
						c.yak = false;
						c.hasFollower = -1;
						c.totalstored = 0;
						c.summoningnpcid = 0;
						c.summoningslot = 0;
						c.yak = false;
					}
					c.sendMessage("Your Summons items have drop on the floor");
				} else {
					c.sendMessage("You do not have a npc currently spawned");
				}

			} else if (c.dialogueAction == 27) {
				c.getPA().movePlayer(3086, 3493, 0);
				c.monkeyk0ed = 0;
				c.Jail = false;
				c.forcedText = "I swear to god that i will never break the rules anymore!";
				c.forcedChatUpdateRequired = true;
				c.updateRequired = true;
			}
			c.dialogueAction = 0;
			c.getPA().removeAllWindows();
			break;
		
			case 9158:
					if (c.dialogueAction == 222) {
					c.getPA().spellTeleport(2676, 3715, 0);
		}
								if (c.dialogueAction == 111) {
					//c.getPA().enterNewCaves();
					c.getDH().sendDialogues(2222, 111);
		return;
		}
			if(c.teleAction == 999) {
				c.getPA().spellTeleport(2857, 3431,0);
			}
			if (c.teleAction == 21) { // Thieving
				c.getPA().spellTeleport(2662, 3308, 0);
				c.sendMessage("Pickpocket the NPC's untill you can train on stalls.");
			}
			if(c.teleAction == 5) {
				c.getPA().spellTeleport(2604, 4772, 0);
				c.sendMessage("<shad=6081134>Sell the impling Jar's to the general shop for money");
				c.sendMessage("<shad=6081134>Buy a Butterfly Net from Tamayu if you dont have one");
			}
			if(c.teleAction == 22) { // Firemaking Fm tele
				//c.getPA().spellTeleport(2904,3463,0);
				c.sendMessage("You can firemake all over "+ Config.SERVER_NAME +"!");
			}
			if(c.dialogueAction == 510) {
				c.getDH().sendDialogues(1061, 278);
				return;
			}
			if (c.dialogueAction == 50) {
				c.getPA().startTeleport(2559,3089,0,"modern");
				c.sendMessage("This is PVP!");
			} else if (c.dialogueAction == 1000) {
				c.getPA().closeAllWindows();
				ClickItem.flowerTime = 20;
			} else if (c.dialogueAction == 875) {
				c.getPA().closeAllWindows();
			} else if (c.dialogueAction == 259) { // Drop item warning
				c.getPA().destroyItem(c.droppedItem);
				c.droppedItem = -1;
			} else if (c.dialogueAction == 257) { //Dung leave warning
			c.getPA().resetDung();
			c.getPA().closeAllWindows();
		} else if (c.dialogueAction == 8591) { //NOMAD DIALOGUE
			if (c.Nomad == true) {
		c.sendMessage("You have already finished this minigame!");
		return;
		}
		c.getPA().enterNomad();
		c.sendMessage("<col=255>If you do not have 2 free slots you will not get anything!</col>");
		c.sendMessage("<col=255>NOMAD WILL SPAWN WITHIN 10 SECONDS, Pot up and activate prayers</col>");
		c.sendMessage("<col=255>IF YOU DIE YOU WILL LOOSE YOUR ITEMS!</col>");
		c.sendMessage("<col=255>To escape climb the ladder! Remember - Nomad has alot of hp</col>");
c.sendMessage("Nomad Minigame Created By Monsterray");
		c.dialogueAction = -1;
					} else if (c.dialogueAction == 3664) { //ANGRY GOBLIN
			if (c.Goblin == true) {
		c.sendMessage("You have already finished this minigame!");
		return;
		}
		c.getPA().enterGoblin();
		c.sendMessage("<col=255>If you do not have 2 free slots you will not get anything!</col>");
		c.sendMessage("<col=255>The Goblin WILL SPAWN WITHIN 10 SECONDS, Pot up and activate prayers</col>");
		c.sendMessage("<col=255>If you die you will loose items! Made By Monsterray</col>");
		c.sendMessage("<col=255>Drink from couldrons to escape!</col>");
		c.dialogueAction = -1;
		} else if (c.dialogueAction == 51) {
				c.getPA().startTeleport(3243,3790,0,"modern");
		} else if (c.dialogueAction == 51) {
				c.getPA().startTeleport(3243,3790,0,"modern");

			} else if (c.dialogueAction == 13) {
				c.getPA().spellTeleport(3202, 3859, 0);
				c.dialogueAction = -1;
								} else if (c.dialogueAction == 34) {
				c.getPA().removeAllWindows();
				c.dialogueAction = -1;
				}

			if (c.dialogueAction == 8) {
				c.getPA().fixAllBarrows();
			} else {
			c.dialogueAction = 0;
			c.getPA().removeAllWindows();
			}
			break;
			
		case 9159:
			if (c.dialogueAction == 51) {
				c.getPA().startTeleport(3351,3659,0,"modern");
				}
			break;
		case 107243:
			c.setSidebarInterface(4, 1644);
			break;

		case 107215:
			c.setSidebarInterface(11, 904);
			break;
		
		/**Specials**/
		case 29188:
		c.specBarId = 7636; // the special attack text - sendframe126(S P E C I A L  A T T A C K, c.specBarId);
		c.usingSpecial = !c.usingSpecial;
		c.getItems().updateSpecialBar();
		break;
		
		case 29163:
		c.specBarId = 7611;
		c.usingSpecial = !c.usingSpecial;
		c.getItems().updateSpecialBar();
		break;
		
		case 33033:
		c.specBarId = 8505;
		c.usingSpecial = !c.usingSpecial;
		c.getItems().updateSpecialBar();
		break;
		
		case 29038:
		if(c.playerEquipment[c.playerWeapon] == 13902) {
		c.specBarId = 7486;
		c.usingSpecial = !c.usingSpecial;
		c.getItems().updateSpecialBar();
		} else {
		c.specBarId = 7486;
		/*if (c.specAmount >= 5) {
			c.attackTimer = 0;
			c.getCombat().attackPlayer(c.playerIndex);
			c.usingSpecial = true;
			c.specAmount -= 5;
		}*/
		//c.getCombat().handleGmaulPlayer();
		c.getItems().updateSpecialBar();
		}
		break;
		
		case 29063:
			if(c.getCombat().checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
				c.gfx0(246);
				c.forcedChat("Raarrrrrgggggghhhhhhh!");
				c.startAnimation(1056);
				c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]) + (c.getLevelForXP(c.playerXP[2]) * 15 / 100);
				c.getPA().refreshSkill(2);
				c.getItems().updateSpecialBar();
			} else {
				c.sendMessage("You don't have the required special energy to use this attack.");
			}
		break;
		
		case 48023:
			c.specBarId = 12335;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
		break;

		case 30108:
			c.specBarId = 7812;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
		break;
		
			case 29138:
			if(c.playerEquipment[c.playerWeapon] == 15486) {
			if(c.getCombat().checkSpecAmount(c.playerEquipment[c.playerWeapon])) {
				c.gfx0(1958);
				c.SolProtect = 120;
				c.startAnimation(10518);
				c.getItems().updateSpecialBar();
			c.usingSpecial = !c.usingSpecial;
			c.sendMessage("All damage will be split into half for 1 minute.");
			c.forcedChat("I am Protected By the Light!");
			c.getPA().sendFrame126("@bla@S P E C I A L  A T T A C K", 7562);
			} else {
				c.sendMessage("You don't have the required special energy to use this attack.");
			}	
			}			
			c.specBarId = 7586;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29113:
			c.specBarId = 7561;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			case 29238:
			c.specBarId = 7686;
			c.usingSpecial = !c.usingSpecial;
			c.getItems().updateSpecialBar();
			break;
			
			
			/**Dueling**/			
			case 26065: // no forfeit
			c.sendMessage("Forfeiting IN DUELS IS DISABLED DUE TO BUGS!!");
			c.sendMessage("Forfeiting IN DUELS IS DISABLED DUE TO BUGS!!");
			c.sendMessage("Forfeiting IN DUELS IS DISABLED DUE TO BUGS!!");
			c.sendMessage("Forfeiting IN DUELS IS DISABLED DUE TO BUGS!!");
			return;
			
			case 26040:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(0);
			break;
			
			case 26066: // no movement
			case 26048:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(1);
			break;
			
			case 26069: // no range
			case 26042:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(2);
			break;
			
			case 26070: // no melee
			case 26043:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(3);
			break;				
			
			case 26071: // no mage
			case 26041:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(4);
			break;
				
			case 26072: // no drinks
			case 26045:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(5);
			break;
			
			case 26073: // no food
			case 26046:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(6);
			break;
			
			case 26074: // no prayer
			case 26047:	
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(7);
			c.getCombat().resetPrayers();
			c.getCurse().resetCurse();
			break;
			
			case 26076: // obsticals
			case 26075:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(8);
			break;
			
			case 2158: // fun weapons
			c.sendMessage("FUN WEAPONS IN DUELS ARE DISABLED DUE TO BUGS!!");
			c.sendMessage("FUN WEAPONS IN DUELS ARE DISABLED DUE TO BUGS!!");
			c.sendMessage("FUN WEAPONS IN DUELS ARE DISABLED DUE TO BUGS!!");
			c.sendMessage("FUN WEAPONS IN DUELS ARE DISABLED DUE TO BUGS!!");
			return;
			
			case 2157:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(9);
			break;
			
			case 30136: // sp attack
			case 30137:
			c.duelSlot = -1;
			c.getTradeAndDuel().selectRule(10);
			break;	

			case 53245: //no helm
			c.duelSlot = 0;
			c.getTradeAndDuel().selectRule(11);
			break;
			
			case 53246: // no cape
			c.duelSlot = 1;
			c.getTradeAndDuel().selectRule(12);
			break;
			
			case 53247: // no ammy
			c.duelSlot = 2;
			c.getTradeAndDuel().selectRule(13);
			break;
			
			case 53249: // no weapon.
			c.duelSlot = 3;
			c.getTradeAndDuel().selectRule(14);
			break;
			
			case 53250: // no body
			c.duelSlot = 4;
			c.getTradeAndDuel().selectRule(15);
			break;
			
			case 53251: // no shield
			c.duelSlot = 5;
			c.getTradeAndDuel().selectRule(16);
			break;
			
			case 53252: // no legs
			c.duelSlot = 7;
			c.getTradeAndDuel().selectRule(17);
			break;
			
			case 53255: // no gloves
			c.duelSlot = 9;
			c.getTradeAndDuel().selectRule(18);
			break;
			
			case 53254: // no boots
			c.duelSlot = 10;
			c.getTradeAndDuel().selectRule(19);
			break;
			
			case 53253: // no rings
			c.duelSlot = 12;
			c.getTradeAndDuel().selectRule(20);
			break;
			
			case 53248: // no arrows
			c.duelSlot = 13;
			c.getTradeAndDuel().selectRule(21);
			break;
			
			case 26018:	
			Client o = (Client) Server.playerHandler.players[c.duelingWith];
			if(o == null) {
				c.getTradeAndDuel().declineDuel();
				return;
			}
			
			if(c.duelRule[2] && c.duelRule[3] && c.duelRule[4]) {
				c.sendMessage("You won't be able to attack the player with the rules you have set.");
				break;
			}
			c.duelStatus = 2;
			if(c.duelStatus == 2) {
				c.getPA().sendFrame126("Waiting for other player...", 6684);
				o.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			if(o.duelStatus == 2) {
				o.getPA().sendFrame126("Waiting for other player...", 6684);
				c.getPA().sendFrame126("Other player has accepted.", 6684);
			}
			
			if(c.duelStatus == 2 && o.duelStatus == 2) {
				c.canOffer = false;
				o.canOffer = false;
				c.duelStatus = 3;
				o.duelStatus = 3;
				c.getTradeAndDuel().confirmDuel();
				o.getTradeAndDuel().confirmDuel();
			}
			break;
			
			case 25120:
				if(c.duelStatus == 5) {
					break;
				}
				Client o1 = (Client) Server.playerHandler.players[c.duelingWith];
				if(o1 == null) {
					c.getTradeAndDuel().declineDuel();
					return;
				}
				c.duelStatus = 4;
				if(o1.duelStatus == 4 && c.duelStatus == 4) {				
					c.getTradeAndDuel().startDuel();
					o1.getTradeAndDuel().startDuel();
					o1.duelCount = 4;
					c.duelCount = 4;
					c.duelDelay = System.currentTimeMillis();
					o1.duelDelay = System.currentTimeMillis();
				} else {
					c.getPA().sendFrame126("Waiting for other player...", 6571);
					o1.getPA().sendFrame126("Other player has accepted", 6571);
				}
			break;
	
			
			case 4169: // god spell charge
				c.usingMagic = true;
				if(!c.getCombat().checkMagicReqs(48)) {
					break;
				}
				if(System.currentTimeMillis() - c.godSpellDelay < Config.GOD_SPELL_CHARGE) {
					c.sendMessage("You still feel the charge in your body!");
					break;
				}
				c.godSpellDelay	= System.currentTimeMillis();
				c.sendMessage("You feel charged with a magical power!");
				c.gfx100(c.MAGIC_SPELLS[48][3]);
				c.startAnimation(c.MAGIC_SPELLS[48][2]);
				c.usingMagic = false;
	        break;
			
			
			case 28164: // item kept on death 
			break;
			
			
			/*case 152:
				if (c.isRunning = true) {
					c.isRunning = false;
				}
				c.sendMessage("You're alredy walking..");
				return;
			case 153:
				c.isRunning = c.isRunning;
				c.isRunning = true;
			break;*/
			
			case 152:
				c.isRunning = !c.isRunning;
			break;
			
			case 153:
				c.isRunning = !c.isRunning;
			break;
				
			
			case 9154:
				c.logout();
			break;

			case 82016:
				c.takeAsNote = !c.takeAsNote;
			break;
			
					

			//home teleports
			
			case 117048:
				//c.getPA().startTeleport(3211, 3422, 0, "modern"); //varrock
				c.getPA().resetFishing();	
				//c.getPA().sendMp3("Null");
				c.getPA().showInterface(26200);
			break;		
			
			//home teleports
			case 4171:
			case 50056:
				c.getPA().showInterface(26200);
				//c.getDH().sendDialogues(4448, 1);
			break;

			case 4143:
			case 50245:
			case 117123:
				c.getDH().sendOption5("Assault", "Recipe for Disaster", "Nomad & Angry Goblin", "Suggest on forums", "Suggest on forums");
				c.teleAction = 199;
				c.setSidebarInterface(6, 45200);
			break;
			
			case 50253:
			case 117131:
			case 4146:
				c.setSidebarInterface(6, 45500);
				c.getDH().sendOption5("Kalphite Queens", "Forgotten Warrior", "Mad Mummy", "BarrelsChest", "GodWars");
				c.teleAction = 6;
			break;
			

			case 51005:
			case 117154:
			case 4150:
				c.setSidebarInterface(6, 45600);
			break;
			
			case 50235:
			case 4140:
			case 117112:
			c.setSidebarInterface(6, 11650);
			//c.getDH().sendOption5("Rock Crabs", "Taverly Dungeon", "Slayer Tower", "Brimhaven Dungeon", "-More Options-");

			//c.teleAction = 1;
			//c.teleAction = 8;
			break;
			/*
			case 4143:
			case 50245:
			case 117123:
			c.getDH().sendOption5("Barrows", "Pest Control", "TzHaar Cave", "Duel Arena", "Warrior Guild");
			c.teleAction = 2;
			break;
			
			case 50253:
			case 117131:
			case 4146:
			c.getDH().sendOption5("Godwars", "King Black Dragon (Wild)", "Dagannoth Kings", "Tormented Demons", "Corporeal Beast");
			c.teleAction = 3;
			break;
			

			case 51005:
			case 117154:
			case 4150:
			c.getDH().sendOption5("Mage Bank", "Varrock PK", "Lava Crossing (Multi)", "Edgeville", "Green Dragons");
			c.teleAction = 4;
			break;			
			
			*/case 51013:
			case 6004:	
			case 117162:
			c.getDH().sendOption5("Crafting & Hunter", "Mining & Smithing", "Fishing & Cooking", "Woodcutting & Firemaking", "Farming & Herblore");
			c.teleAction = 5;
			break;
			
			
			case 117186:	
			c.getDH().sendOption5("Sea Troll Queen", "Lakhrahnaz", "Nomad", "Giant sea Snake", "Avatar of Destruction");
			c.teleAction = 8;
			break; 
			
			
			case 51023:
			case 6005:
						c.getDH().sendOption5("Lumbridge", "Varrock", "Camelot", "Falador", "Canafis");
			c.teleAction = 20;
			break; 
			
			
			case 51031:
			case 29031:
			//c.sendMessage("Coming soon....");
			c.getDH().sendOption5("Agility", "RuneCrafting", "Summoning", "Dungeoneering", "Fletching & Thieving");
			c.teleAction = 21;
			break; 		

			/*case 72038:
			case 51039:
		//	c.getDH().sendOption5("Sea Troll Queen", "Lakhrahnaz", "Nomad & Angry Goblin", "Giant sea Snake", "Avatar of Destruction");
			//c.teleAction = 8;
			c.sendMessage("This teleport leads nowhere!");
			break;*/
			
      			case 9125: //Accurate
			case 6221: // range accurate
			case 22230: //kick (unarmed)
			case 48010: //flick (whip)
			case 21200: //spike (pickaxe)
			case 1080: //bash (staff)
			case 6168: //chop (axe)
			case 6236: //accurate (long bow)
			case 17102: //accurate (darts)
			case 8234: //stab (dagger)

			case 30088: //claws
			case 1177: //hammer
			c.fightMode = 0;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9126: //Defensive
			case 48008: //deflect (whip)
			case 22228: //punch (unarmed)
			case 21201: //block (pickaxe)
			case 1078: //focus - block (staff)
			case 6169: //block (axe)
			case 33019: //fend (hally)
			case 18078: //block (spear)
			case 8235: //block (dagger)
			case 1175: //accurate (darts)
			case 30089: //stab (dagger)
			c.fightMode = 1;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9127: // Controlled
			case 48009: //lash (whip)
			case 33018: //jab (hally)
			case 6234: //longrange (long bow)
			case 6219: //longrange
			case 18077: //lunge (spear)
			case 18080: //swipe (spear)
			case 18079: //pound (spear)
			case 17100: //longrange (darts)
			c.fightMode = 3;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;
			
			case 9128: //Aggressive
			case 6220: // range rapid
			case 22229: //block (unarmed)
			case 21203: //impale (pickaxe)
			case 21202: //smash (pickaxe)
			case 1079: //pound (staff)
			case 6171: //hack (axe)
			case 6170: //smash (axe)
			case 33020: //swipe (hally)
			case 6235: //rapid (long bow)
			case 17101: //repid (darts)
			case 8237: //lunge (dagger)
			case 30091: //claws
			case 1176: //stat hammer
			case 8236: //slash (dagger)

			case 30090: //claws
			c.fightMode = 2;
			if (c.autocasting)
				c.getPA().resetAutocast();
			break;

			/**Prayers**//*
case 87231: // thick skin
			if(c.trade11 > 1) {
			for(int p = 0; p < c.PRAYER.length; p++) { // reset prayer glows 
				c.prayerActive[p] = false;
				c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);	
			}
			c.sendMessage("You must wait 15 minutes before using this!");
			c.trade11();
			return;
			}
			c.getCurse().activateCurse(0);
			break;	
			case 87233: // burst of str
			c.getCurse().activateCurse(1);
			break;	
			case 87235: // charity of thought
			c.getCurse().activateCurse(2);
			break;	
			case 87237: // range
			c.getCurse().activateCurse(3);
			break;
			case 87239: // mage
			c.getCurse().activateCurse(4);
			break;
case 87241: // berserker
			if(c.altarPrayed == 0) {
				return;
			}
			c.getCurse().activateCurse(5);
			break;
			case 87243: // super human
			c.getCurse().activateCurse(6);
			break;
			case 87245:	// improved reflexes
			c.getCurse().activateCurse(7);
			break;
			case 87247: //hawk eye
			c.getCurse().activateCurse(8);
			break;
			case 87249:
			c.getCurse().activateCurse(9);
			break;
			case 87251: // protect Item
			c.getCurse().activateCurse(10);
			break;			
			case 87253: // 26 range
			c.getCurse().activateCurse(11);
			break;
			case 87255: // 27 mage
			c.getCurse().activateCurse(12);
			break;	
			case 88001: // steel skin
			c.getCurse().activateCurse(13);
			break;
			case 88003: // ultimate str
			c.getCurse().activateCurse(14);
			break;
			case 88005: // incredible reflex
			c.getCurse().activateCurse(15);
			break;	
			case 88007: // protect from magic
			c.getCurse().activateCurse(16);
			break;					
			case 88009: // protect from range
			c.getCurse().activateCurse(17);
			break;
			case 88011: // protect from melee
			c.getCurse().activateCurse(18);
			break;
			case 88013: // 44 range
			c.getCurse().activateCurse(19);
			break;	
			/**End of curse prayers**
			
			
			**Prayers**
			case 97168: // thick skin
			c.getCombat().activatePrayer(0);
			break;	
			case 97170: // burst of str
			c.getCombat().activatePrayer(1);
			break;	
			case 97172: // charity of thought
			c.getCombat().activatePrayer(2);
			break;	
			case 97174: // range
			c.getCombat().activatePrayer(3);
			break;
			case 97176: // mage
			c.getCombat().activatePrayer(4);
			break;
			case 97178: // rockskin
			c.getCombat().activatePrayer(5);
			break;
			case 97180: // super human
			c.getCombat().activatePrayer(6);
			break;
			case 97182:	// improved reflexes
			c.getCombat().activatePrayer(7);
			break;
			case 97184: //hawk eye
			c.getCombat().activatePrayer(8);
			break;
			case 97186:
			c.getCombat().activatePrayer(9);
			break;
			case 97188: // protect Item
			/*if(c.trade11 > 1) {
			for(int p = 0; p < c.PRAYER.length; p++) { // reset prayer glows 
				c.prayerActive[p] = false;
				c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);	
			}
			c.sendMessage("You must wait 15 minutes before using this!");
			return;
			}*
			c.getCombat().activatePrayer(10);
			break;			
			case 97190: // 26 range
			c.getCombat().activatePrayer(11);
			break;
			case 97192: // 27 mage
			c.getCombat().activatePrayer(12);
			break;	
			case 97194: // steel skin
			c.getCombat().activatePrayer(13);
			break;
			case 97196: // ultimate str
			c.getCombat().activatePrayer(14);
			break;
			case 97198: // incredible reflex
			c.getCombat().activatePrayer(15);
			break;	
			case 97200: // protect from magic
			c.getCombat().activatePrayer(16);
			break;					
			case 97202: // protect from range
			c.getCombat().activatePrayer(17);
			break;
			case 97204: // protect from melee
			c.getCombat().activatePrayer(18);
			break;
			case 97206: // 44 range
			c.getCombat().activatePrayer(19);
			break;	
			case 97208: // 45 mystic
			c.getCombat().activatePrayer(20);
			break;				
			case 97210: // retrui
			c.getCombat().activatePrayer(21);
			break;					
			case 97212: // redem
			c.getCombat().activatePrayer(22);
			break;					
			case 97214: // smite
			c.getCombat().activatePrayer(23);
			break;
			case 97216: // chiv
			c.getCombat().activatePrayer(24);
			break;
			case 97218: // piety
			c.getCombat().activatePrayer(25);
			break;*/
			
						/**CURSE Prayers**/
              		case 87231: // protect Item
			/*if(c.trade11 > 1) {
			for(int p = 0; p < c.PRAYER.length; p++) { // reset prayer glows 
				c.prayerActive[p] = false;
				c.getPA().sendFrame36(c.PRAYER_GLOW[p], 0);	
			}
			c.sendMessage("You must wait 15 minutes before using this!");
			return;
			}*/
			c.getCurse().activateCurse(0);
			break;	
			case 87233: // sap warrior
			c.getCurse().activateCurse(1);
                
			break;	
			case 87235: // sap ranger
                        c.getCurse().activateCurse(2);
 
			break;	
			case 87237: // sap mage
			c.getCurse().activateCurse(3);
			break;
			case 87239: // sap spirit
			c.getCurse().activateCurse(4);
			
			break;
			case 87241: // berserker
			c.startAnimation(12589);
			c.gfx0(2266);
			c.getCurse().activateCurse(5);
			break;
			case 87243: // deflect summoning
			c.getCurse().activateCurse(6);
			break;
			case 87245: // deflect mage
			c.getCurse().activateCurse(7);
			break;
			case 87247: //deflect range
			c.getCurse().activateCurse(8);
			break;
			case 87249: // deflect meele
			c.getCurse().activateCurse(9);
			break;
			case 87251: // Leech attack
			c.getCurse().activateCurse(10);
                   
			break;			
			case 87253: // leech range
			c.getCurse().activateCurse(11);
              	
			break;
			case 87255: // leech mage
			c.getCurse().activateCurse(12);
			break;	
			case 88001: // leech def
			c.getCurse().activateCurse(13);
		
			break;
			case 88003: // leech str
			c.getCurse().activateCurse(14);	
         	
			break;
			case 88005: // leech run
			c.getCurse().activateCurse(15);
			c.sendMessage("Everyone has unlimited energy. There is no point doing this!u dumb nub");
             c.forcedText = "Im dumb hahaha i thought i could leech unlimited run!";
			break;	
			case 88007: // leech spec
			c.getCurse().activateCurse(16);
			
			break;					
			case 88009: // Wrath
			c.getCurse().activateCurse(17);
			break;
			case 88011: // SS
			c.getCurse().activateCurse(18);
			break;
			case 88013: // turmoil
			c.getCurse().activateCurse(19);
			break;	
			/**End of curse prayers**/
			
			
			/**Prayers**/
			case 21233: // thick skin
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(0);
			break;	
			case 21234: // burst of str
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(1);
			break;	
			case 21235: // charity of thought
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(2);
			break;	
			case 70080: // range
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(3);
			break;
			case 70082: // mage
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(4);
			break;
			case 21236: // rockskin
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(5);
			break;
			case 21237: // super human
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(6);
			break;
			case 21238:	// improved reflexes
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(7);
			break;
			case 21239: //hawk eye
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(8);
			break;
			case 21240:
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(9);
			break;
			case 21241: // protect Item
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(10);
			break;			
			case 70084: // 26 range
			if(c.altarPrayed == 1) {
				return;
			}
			c.getCombat().activatePrayer(11);
			break;
			case 70086: // 27 mage
				if(c.altarPrayed == 1) {
					return;
				}
				c.getCombat().activatePrayer(12);
			break;	
			
			case 21242: // steel skin
				if(c.altarPrayed == 1) {
					return;
				}
				c.getCombat().activatePrayer(13);
			break;
			
			case 21243: // ultimate str
				if(c.altarPrayed == 1) {
					return;
				}
			c.getCombat().activatePrayer(14);
			break;
			
			case 21244: // incredible reflex
				if(c.altarPrayed == 1) {
					return;
				}
				c.getCombat().activatePrayer(15);
			break;	
			
			case 21245: // protect from magic
				if(c.altarPrayed == 1) {
					return;
				}
				c.getCombat().activatePrayer(16);
			break;	
			
			case 21246: // protect from range
				if(c.altarPrayed == 1) {
					return;
				}
				c.getCombat().activatePrayer(17);
			break;
			
			case 21247:// case 97204: // protect from melee
				c.getCombat().activatePrayer(18);
			break;
			
			case 70088:// case 97206: // 44 range
				c.getCombat().activatePrayer(19);
			break;
			
			case 70090:
			// case 97208: // 45 mystic
				c.getCombat().activatePrayer(20);
			break;
			
			// case 97210: // retrui
			case 2171:
				c.getCombat().activatePrayer(21);
			break;
			
			// case 97212: // redem
			case 2172:
				c.getCombat().activatePrayer(22);
			break;
			
			// case 97214: // smite
			case 2173:
				c.getCombat().activatePrayer(23);
			break;
			
			// case 97216: // chiv
			case 70092:
				c.getCombat().activatePrayer(24);
			break;
			
			// case 97218: // piety
			case 70094:
				c.getCombat().activatePrayer(25);
			break;

			case 13092:
				if (System.currentTimeMillis() - c.lastButton < 400) {
					c.lastButton = System.currentTimeMillis();
					break;
				} else {
					c.lastButton = System.currentTimeMillis();
				}
				Client ot = (Client) Server.playerHandler.players[c.tradeWith];
				if(ot == null) {
					c.getTradeAndDuel().declineTrade();
					c.sendMessage("Trade declined as the other player has disconnected.");
					break;
				}
				c.getPA().sendFrame126("Waiting for other player...", 3431);
				ot.getPA().sendFrame126("Other player has accepted", 3431);	
				c.goodTrade= true;
				ot.goodTrade= true;
				for (GameItem item : c.getTradeAndDuel().offeredItems) {
					if (item.id > 0) {
						if(ot.getItems().freeSlots() < c.getTradeAndDuel().offeredItems.size()) {					
							c.sendMessage(ot.playerName +" only has "+ot.getItems().freeSlots()+" free slots, please remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
							ot.sendMessage(c.playerName +" has to remove "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items or you could offer them "+(c.getTradeAndDuel().offeredItems.size() - ot.getItems().freeSlots())+" items.");
							c.goodTrade= false;
							ot.goodTrade= false;
							c.getPA().sendFrame126("Not enough inventory space...", 3431);
							ot.getPA().sendFrame126("Not enough inventory space...", 3431);
								break;
						} else {
							c.getPA().sendFrame126("Waiting for other player...", 3431);				
							ot.getPA().sendFrame126("Other player has accepted", 3431);
							c.goodTrade= true;
							ot.goodTrade= true;
						}
					}	
				}	
				if (c.inTrade && !c.tradeConfirmed && ot.goodTrade && c.goodTrade) {
					c.tradeConfirmed = true;
					if(ot.tradeConfirmed) {
						c.getTradeAndDuel().confirmScreen();
						ot.getTradeAndDuel().confirmScreen();
						break;
					}
				}
			break;
					
			case 13218:
				if (System.currentTimeMillis() - c.lastButton < 400) {
					c.lastButton = System.currentTimeMillis();
					break;
				} else {
					c.lastButton = System.currentTimeMillis();
				}
				c.tradeAccepted = true;
				Client ot1 = (Client) Server.playerHandler.players[c.tradeWith];
				if (ot1 == null) {
					c.getTradeAndDuel().declineTrade();
					c.sendMessage("Trade declined as the other player has disconnected.");
					break;
				}
				if (c.inTrade && c.tradeConfirmed && ot1.tradeConfirmed && !c.tradeConfirmed2) {
					c.tradeConfirmed2 = true;
					if(ot1.tradeConfirmed2) {	
						c.acceptedTrade = true;
						ot1.acceptedTrade = true;
						c.getTradeAndDuel().giveItems();
						ot1.getTradeAndDuel().giveItems();
						c.sendMessage("Trade accepted.");
						c.SaveGame();
						ot1.SaveGame();
						ot1.sendMessage("Trade accepted.");
						break;
					}
				ot1.getPA().sendFrame126("Other player has accepted.", 3535);
				c.getPA().sendFrame126("Waiting for other player...", 3535);
				}
			break;	
			
			/* Rules Interface Buttons */
			case 125011: //Click agree
				if(!c.ruleAgreeButton) {
					c.ruleAgreeButton = true;
					c.getPA().sendFrame36(701, 1);
				} else {
					c.ruleAgreeButton = false;
					c.getPA().sendFrame36(701, 0);
				}
			break;
			
			case 67100://Accept
				c.getPA().showInterface(3559);
				c.newPlayer = false;
				c.sendMessage("You need to click on you agree before you can continue on.");
			break;
			
			case 67103://Decline
				c.sendMessage("You have chosen to decline, Client will be disconnected from the server.");
			break;
			
			/* End Rules Interface Buttons */
			/* Player Options */
			case 74176:
				if(!c.mouseButton) {
					c.mouseButton = true;
					c.getPA().sendFrame36(500, 1);
					c.getPA().sendFrame36(170,1);
				} else if(c.mouseButton) {
					c.mouseButton = false;
					c.getPA().sendFrame36(500, 0);
					c.getPA().sendFrame36(170,0);					
				}
			break;
			
			case 74184:
				if(!c.splitChat) {
					c.splitChat = true;
					c.getPA().sendFrame36(502, 1);
					c.getPA().sendFrame36(287, 1);
				} else {
					c.splitChat = false;
					c.getPA().sendFrame36(502, 0);
					c.getPA().sendFrame36(287, 0);
				}
			break;
			
			case 100231:
				if(!c.chatEffects) {
					c.chatEffects = true;
					c.getPA().sendFrame36(501, 1);
					c.getPA().sendFrame36(171, 0);
				} else {
					c.chatEffects = false;
					c.getPA().sendFrame36(501, 0);
					c.getPA().sendFrame36(171, 1);
				}
			break;
			
			case 100237:
				if(!c.acceptAid) {
					c.acceptAid = true;
					c.getPA().sendFrame36(503, 1);
					c.getPA().sendFrame36(427, 1);
				} else {
					c.acceptAid = false;
					c.getPA().sendFrame36(503, 0);
					c.getPA().sendFrame36(427, 0);
				}
			break;
			
			case 74201://brightness1
				c.getPA().sendFrame36(505, 1);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166, 1);
			break;
			
			case 74203://brightness2
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 1);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,2);
			break;

			case 74204://brightness3
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 1);
				c.getPA().sendFrame36(508, 0);
				c.getPA().sendFrame36(166,3);
			break;

			case 74205://brightness4
				c.getPA().sendFrame36(505, 0);
				c.getPA().sendFrame36(506, 0);
				c.getPA().sendFrame36(507, 0);
				c.getPA().sendFrame36(508, 1);
				c.getPA().sendFrame36(166,4);
			break;
			
			case 74206://area1
				c.getPA().sendFrame36(509, 1);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
			break;
			
			case 74207://area2
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 1);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 0);
			break;
			
			case 74208://area3
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 1);
				c.getPA().sendFrame36(512, 0);
			break;
			
			case 74209://area4
				c.getPA().sendFrame36(509, 0);
				c.getPA().sendFrame36(510, 0);
				c.getPA().sendFrame36(511, 0);
				c.getPA().sendFrame36(512, 1);
			break;
			
			case 168:
                c.startAnimation(855);
            break;
			
            case 169:
                c.startAnimation(856);
            break;
			
            case 162:
                c.startAnimation(857);
            break;
			
            case 164:
                c.startAnimation(858);
            break;
			
            case 165:
                c.startAnimation(859);
            break;
			
            case 161:
                c.startAnimation(860);
            break;
			
            case 170:
                c.startAnimation(861);
            break;
			
            case 171:
                c.startAnimation(862);
            break;
			
            case 163:
                c.startAnimation(863);
            break;
			
            case 167:
                c.startAnimation(864);
            break;
			
            case 172:
                c.startAnimation(865);
            break;
			
            case 166:
                c.startAnimation(866);
            break;
			
            case 52050:
                c.startAnimation(2105);
            break;
			
            case 52051:
                c.startAnimation(2106);
            break;
			
            case 52052:
                c.startAnimation(2107);
            break;
			
            case 52053:
                c.startAnimation(2108);
            break;
			
            case 52054:
                c.startAnimation(2109);
            break;
			
            case 52055:
                c.startAnimation(2110);
            break;
			
            case 52056:
                c.startAnimation(2111);
            break;
			
            case 52057:
                c.startAnimation(2112);
            break;
			
            case 52058:
                c.startAnimation(2113);
            break;
			
            case 43092:
                c.startAnimation(0x558);		
				c.stopMovement();
				c.gfx0(574);
			break;
			
            case 2155:
                c.startAnimation(0x46B);
            break;
			
            case 25103:
                c.startAnimation(0x46A);
            break;
			
            case 25106:
                c.startAnimation(0x469);
            break;
			
            case 2154:
                c.startAnimation(0x468);
            break;
			
            case 52071:
                c.startAnimation(0x84F);
            break;
			
            case 52072:
                c.startAnimation(0x850);
            break;
			
            case 59062:
                c.startAnimation(4280);
            break;
			
            case 72032:
                c.startAnimation(3544);
            break;
			
            case 72033:
                c.startAnimation(4278);
            break;
			
            case 72254:
                c.startAnimation(4275);
            break;
			
			case 72255:
				c.startAnimation(6111);	
				c.stopMovement();
           	break;
			
			case 88058:
                c.startAnimation(7531);		
				c.stopMovement();
            break;
			
			case 88062:
                c.startAnimation(10530);		
				c.stopMovement();
				c.gfx0(1864);
			break;
			
			case 88063:
                c.startAnimation(11044);		
				c.stopMovement();
				c.gfx0(1973);
			break;
			
			case 88060:
				c.startAnimation(8770);
				c.stopMovement();
				c.gfx0(1553);		
           	break;
			
			case 88061:
				c.startAnimation(9990);		
				c.stopMovement();
				c.gfx0(1734);
			break;
			
			case 73004:
				c.startAnimation(7272);		
				c.stopMovement();
				c.gfx0(1244);
           	break;
			
			case 88059:
				if(System.currentTimeMillis() - c.logoutDelay < 8000) {
					c.sendMessage("You cannot do this emote in combat!");		
					return;
				}
				c.startAnimation(2414);
				c.stopMovement();
				c.gfx0(1537);
			break;
			
			case 88064: // turkey
				c.startAnimation(10994);
			break;
			
			case 73003:
				c.startAnimation(2836);
				c.stopMovement();
			break;
			
			case 73000:
				if(System.currentTimeMillis() - c.logoutDelay < 8000) {
					c.sendMessage("You cannot do skillcape emotes in combat!");
					return;
				}
                c.startAnimation(3543);		
				c.stopMovement();
			break;
			
			case 73001:
                c.startAnimation(3544);		
				c.stopMovement();
            break;	
			
			case 88066:
                c.startAnimation(12658);		
				c.gfx0(780);
				c.stopMovement();
            break;
			
			case 88065:
                c.startAnimation(11542);		
				c.gfx0(2037);
				c.stopMovement();
            break;
			
			/* END OF EMOTES */
			case 28166:
				
			break;
			
			case 118098:
				c.getPA().castVeng();
			break; 
			
			case 27211:
				skillMenu.openInterface(c, 22);
//				c.forcedText = "[QC] My Hunter level is  " + c.getPA().getLevelForXP(c.playerXP[23]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27190:
				skillMenu.openInterface(c, 0);
//				c.forcedText = "[QC] My Attack level is  " + c.getPA().getLevelForXP(c.playerXP[0]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;

			case 27196:
				skillMenu.openInterface(c, 1);
//				c.forcedText = "[QC] My Defence level is  " + c.getPA().getLevelForXP(c.playerXP[1]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27193:
				skillMenu.openInterface(c, 2);
//				c.forcedText = "[QC] My Strength level is  " + c.getPA().getLevelForXP(c.playerXP[2]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27191:
				skillMenu.openInterface(c, 3);
//				c.forcedText = "[QC] My Hitpoints level is  " + c.getPA().getLevelForXP(c.playerXP[3]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27199:
				skillMenu.openInterface(c, 4);
//				c.forcedText = "[QC] My Range level is  " + c.getPA().getLevelForXP(c.playerXP[4]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27202:
				skillMenu.openInterface(c, 5);
//				c.forcedText = "[QC] My Prayer level is  " + c.getPA().getLevelForXP(c.playerXP[5]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27205:
				skillMenu.openInterface(c, 6);
//				c.forcedText = "[QC] My Magic level is  " + c.getPA().getLevelForXP(c.playerXP[6]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27201:
				skillMenu.openInterface(c, 7);
//				c.forcedText = "[QC] My Cooking level is  " + c.getPA().getLevelForXP(c.playerXP[7]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27207:
				skillMenu.openInterface(c, 8);
//				c.forcedText = "[QC] My Woodcutting level is  " + c.getPA().getLevelForXP(c.playerXP[8]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27206:
				skillMenu.openInterface(c, 9);
//				c.forcedText = "[QC] My Fletching level is  " + c.getPA().getLevelForXP(c.playerXP[9]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27198:
				skillMenu.openInterface(c, 10);
//				c.forcedText = "[QC] My Fishing level is  " + c.getPA().getLevelForXP(c.playerXP[10]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27204:
				skillMenu.openInterface(c, 11);
//				c.forcedText = "[QC] My Firemaking level is  " + c.getPA().getLevelForXP(c.playerXP[11]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27203:
				skillMenu.openInterface(c, 12);
//				c.forcedText = "[QC] My Crafting level is  " + c.getPA().getLevelForXP(c.playerXP[12]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27195:
				skillMenu.openInterface(c, 13);
//				c.forcedText = "[QC] My Smithing level is  " + c.getPA().getLevelForXP(c.playerXP[13]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27192:
				skillMenu.openInterface(c, 14);
//				c.forcedText = "[QC] My Mining level is  " + c.getPA().getLevelForXP(c.playerXP[14]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27197:
				skillMenu.openInterface(c, 15);
//				c.forcedText = "[QC] My Herblore level is  " + c.getPA().getLevelForXP(c.playerXP[15]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27194:
				skillMenu.openInterface(c, 16);
//				c.forcedText = "[QC] My Agility level is  " + c.getPA().getLevelForXP(c.playerXP[16]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27200:
				skillMenu.openInterface(c, 17);
//				c.forcedText = "[QC] My Thieving level is  " + c.getPA().getLevelForXP(c.playerXP[17]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27209:
				skillMenu.openInterface(c, 18);
//				c.forcedText = "[QC] My Slayer level is  " + c.getPA().getLevelForXP(c.playerXP[18]) + ".";
//				c.sendMessage("I must slay another " + c.taskAmount + " " + Server.npcHandler.getNpcListName(c.slayerTask));
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27210:
				skillMenu.openInterface(c, 19);
//				c.forcedText = "[QC] My Farming level is  " + c.getPA().getLevelForXP(c.playerXP[19]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 27208:
				skillMenu.openInterface(c, 20);
//				c.forcedText = "[QC] My Runecrafting level is  " + c.getPA().getLevelForXP(c.playerXP[20]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 74240:
				skillMenu.openInterface(c, 21);
//				c.forcedText = "[Quick Chat] My Summoning level is  " + c.getPA().getLevelForXP(c.playerXP[21]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 74239:
				skillMenu.openInterface(c, 22);
//				c.forcedText = "[Quick Chat] My Hunter level is  " + c.getPA().getLevelForXP(c.playerXP[22]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 74238:
				skillMenu.openInterface(c, 23);
//				c.forcedText = "[Quick Chat] My Construction level is  " + c.getPA().getLevelForXP(c.playerXP[23]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 74241:
				skillMenu.openInterface(c, 24);
//				c.forcedText = "[Quick Chat] My Dungeoneering level is  " + c.getPA().getLevelForXP(c.playerXP[24]) + ".";
//				c.forcedChatUpdateRequired = true;
//				c.updateRequired = true;
			break;
			
			case 77036:
				if(c.hasFollower > 0 && c.totalstored > -1) {
					for(int i = 0; i < 29; i += 1) {
						Server.itemHandler.createGroundItem(c, c.storeditems[i], c.absX, c.absY, 1, c.playerId);
						c.firstslot();
						c.storeditems[i] = -1;
						c.occupied[i] = false;
						c.yak = false;
						c.hasFollower = -1;
						c.totalstored = 0;
						c.summoningnpcid = 0;
						c.summoningslot = 0;
						c.yak = false;
						c.sendMessage("Your Summon items have drop on the floor");
					}
				} else {
					c.sendMessage("You do not have a npc currently spawned");
				}
			break;

			case 66156:
				if(!c.InDung) {
					c.getPA().closeAllWindows();
					c.sendMessage("Please re-enter Dungeoneering.");
					return;
				}
				if(c.hasChoosenDung == true) {
					return;
				}
				if(c.playerLevel[6] <= 9) {
					c.sendMessage("You must be 10+ Magic To Choose Magic Class");
					return;
				}
				c.getItems().addItem(19893, 1);
				c.getItems().addItem(19892, 1);
				c.getItems().addItem(15786, 1);
				c.getItems().addItem(15797, 1);
				c.getItems().addItem(15837, 1);
				c.getItems().addItem(15892, 1);
				c.getItems().addItem(16185, 1);
				c.getItems().addItem(16153, 1);
				c.getItems().addItem(391, 3);
				c.getItems().addItem(554, 10000);
				c.getItems().addItem(555, 10000);
				c.getItems().addItem(556, 10000);
				c.getItems().addItem(557, 10000);
				c.getItems().addItem(558, 10000);
				c.getItems().addItem(559, 10000);
				c.getItems().addItem(560, 10000);
				c.needstorelog = true;
				c.getItems().addItem(561, 10000);
				c.getItems().addItem(562, 10000);
				c.getItems().addItem(563, 10000);
				c.getItems().addItem(565, 10000);
				c.getItems().addItem(564, 10000);
				c.getItems().addItem(566, 10000);
				c.getItems().addItem(2434, 1);
				c.getItems().addItem(3040, 1);
				c.playerMagicBook = 1;
				c.setSidebarInterface(6, 12855);
				c.getPA().closeAllWindows();
				c.isSkulled = true;
				c.gwdelay = 1000;
				c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415> Magic Equipment chosen.");
				c.isChoosingDung = false;
				c.hasChoosenDung = true;
			break;
			
			case 66157:
				if(!c.InDung) {
					c.getPA().closeAllWindows();
					c.sendMessage("Please re-enter Dungeoneering.");
					return;
				}
				if(c.hasChoosenDung == true) {
					return;
				}
				c.getItems().addItem(15808, 1);
				c.getItems().addItem(15914, 1);
				c.getItems().addItem(15925, 1);
				c.getItems().addItem(15936, 1);
				c.getItems().addItem(16013, 1);
				c.getItems().addItem(16035, 1);
				c.getItems().addItem(16127, 1);
				c.getItems().addItem(16262, 1);
				c.getItems().addItem(19893, 1);
				c.getItems().addItem(19892, 1);
				c.getItems().addItem(391, 3);
				c.getItems().addItem(2434, 1);
				c.getItems().addItem(2440, 1);
				c.getItems().addItem(2442, 1);
				c.getItems().addItem(2436, 1);
				c.getItems().addItem(2503, 1);
				c.getItems().addItem(2497, 1);
				c.getItems().addItem(4587, 1);
				c.getPA().closeAllWindows();
				c.needstorelog = true;
				c.isSkulled = true;
				c.gwdelay = 1000;
				c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415> Melee Equipment chosen.");
				c.isChoosingDung = false;
				c.hasChoosenDung = true;
			break;
		
			case 66158:
				if(!c.InDung) {
					c.getPA().closeAllWindows();
					c.sendMessage("Please re-enter Dungeoneering.");
					return;
				}
				if(c.hasChoosenDung == true) {
					return;
				}
				if(c.playerLevel[4] <= 9) {
					c.sendMessage("You must be 10+ Ranged To Choose Ranged Class");
					return;
				}
				c.getItems().addItem(16002, 1);
				c.getItems().addItem(16046, 1);
				c.needstorelog = true;
				c.getItems().addItem(16057, 1);
				c.getItems().addItem(16068, 1);
				c.getItems().addItem(16105, 1);
				c.getItems().addItem(19893, 1);
				c.getItems().addItem(19892, 1);
				c.getItems().addItem(861, 1);
				c.getItems().addItem(892, 10000);
				c.getItems().addItem(397, 3);
				c.getItems().addItem(3024, 1);
				c.getItems().addItem(2444, 1);
				c.getItems().addItem(2503, 1);
				c.getItems().addItem(2497, 1);
				c.isSkulled = true;
				c.gwdelay = 1000;
				c.getPA().closeAllWindows();
				c.sendMessage("<shad=6081134>[Dungeoneering] <shad=15695415> Ranged Equipment chosen.");
				c.isChoosingDung = false;
				c.hasChoosenDung = true;
			break;
			
			//Dungeoneering finish
			case 177190:
			//c.getPA().showInterface(14040);
					//godwars
					//c.sendMessage("If there is no floor/maps are gone RESTART CLIENT!");
					//c.getPA().spellTeleport(2882, 5310, 2);
					//c.getPA().spellTeleport(2909, 3613, 0);
					////c.getPA().sendMp3("Music");
					c.getDH().sendDialogues(5555, 1);
				//	c.sendMessage("Nex currently disabled / will be back soon!");
			break;
			
			case 177206:
				c.getPA().spellTeleport(3007, 3849, 0);
			break;
			
			case 177209:
				c.getPA().spellTeleport(1910, 4367, 0);
			break;
			
			case 177212:
				c.getPA().spellTeleport(2717, 9805, 0);
			break;
			
			case 177221:
				c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : c.playerMagicBook == 2 ? 29999 : 12855);
			break;
			
			case 176177:
				c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : c.playerMagicBook == 2 ? 29999 : 12855);
			break;
			
			case 178065:
				c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : c.playerMagicBook == 2 ? 29999 : 12855);
			break;
			
			case 178034:
				c.getPA().spellTeleport(2539, 4716, 0);
			break;
			
			case 178050:
				c.getPA().spellTeleport(3243, 3517, 0);
			break;
			
			case 178053:
				c.getPA().spellTeleport(3158, 3667, 0);
			break;
			
			case 178056:
				c.getPA().spellTeleport(3088, 3523, 0);
			break;
			
			case 178059:
				c.getPA().spellTeleport(3344, 3667, 0);
			break;
			
			case 176162:
				c.getPA().spellTeleport(3565, 3314, 0);
			break;
			
			case 176168:
				c.getPA().spellTeleport(2438, 5172, 0);
			break;
			
			case 176146:
				if(c.hasFollower > -1) {
					c.sendMessage("Please dismiss your familiar first!");
					return;
				}
				c.getPA().startTeleport(3358, 3269, 0, "modern");
			break;
			
			case 176165:
				c.getPA().spellTeleport(2662, 2650, 0);
			break;	
			
			case 176171:
				c.getPA().spellTeleport(2865, 3546, 0);
			break;
			
			case 176246:
				c.getPA().spellTeleport(2676, 3715, 0);
			break;
			
			// NEW MONSTER TELEPORT TAB
			
			case 45132: // crabs
				c.getDH().sendOption2("Yak's (Neitznot)", "Rock Crabs");
				c.dialogueAction = 222;
				//c.getPA().spellTeleport(2676, 3715, 0);
			break;
		
			case 45153: // stop viewing
				c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : c.playerMagicBook == 2 ? 29999 : 12855);
			break;
				
			case 45135: // taverly dung
				c.getPA().spellTeleport(2884, 9798, 0);
			break;
				
			case 45138: // slayer tower
				c.getPA().spellTeleport(3428, 3537, 0);
			break;
			
			case 45141: // brim haven dung
				c.getPA().spellTeleport(2710, 9466, 0);
			break;
			
			case 45144: // Hill giants
				c.getPA().spellTeleport(2706, 9450, 0);
			break;
			
			case 45147: // dark beasts
				c.getPA().spellTeleport(2908, 9696, 0);
			break;
			
			case 45150: // strykewyrms
				if((c.playerLevel[22] < 90) && (c.playerLevel[16] < 90)) {
					c.sendMessage("You need 90 Agility And 90 Hunter to enter the Strykworm's Cave");
				} else {
					if((c.playerLevel[22] > 89) && (c.playerLevel[16] < 90)) {
						c.sendMessage("You need 90 Hunter And Agility to enter the Strykworm's Cave");
					} else {
						if((c.playerLevel[22] < 90) && (c.playerLevel[16] > 89)) {
							c.sendMessage("You need 90 Hunter And Agility to enter the Strykworm's Cave");
						} else {
							if((c.playerLevel[22] > 89) && (c.playerLevel[16] >89)) {
								c.getPA().startTeleport(2515, 4632, 0, "modern");
								c.sendMessage("A sense of nervousness fills your body..");
								c.sendMessage("you find yourself in a mystery cave!");
							}
						}
					}
				}
			break;
				//END OF NEW MONSTER TELEPORT TAB
				
			case 177006:
				c.getPA().spellTeleport(2884, 9798, 0);
			break;
			
			case 177009:
				c.getPA().spellTeleport(2710, 9466, 0);
			break;
			
			case 177012:
				c.getPA().spellTeleport(3428, 3527, 0);
			break;
			
			case 177015:
				c.getPA().spellTeleport(3117, 9847, 0);
			break;
			
			case 177021:
				c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : c.playerMagicBook == 2 ? 29999 : 12855);
			break;
			
			case 177215:
				c.getPA().spellTeleport(3303, 9375, 0);
			break;

			case 69009:
				if(c.playerMagicBook == 0) {
					c.setSidebarInterface(6, 1151); //modern
				} else if(c.playerMagicBook == 1){
					c.setSidebarInterface(6, 12855); // ancient
				} else {
					c.setSidebarInterface(6, 29999);
				}
			break;
			
			case 24017:
				c.getPA().resetAutocast();
				//c.sendFrame246(329, 200, c.playerEquipment[c.playerWeapon]);
				c.getItems().sendWeapon(c.playerEquipment[c.playerWeapon], c.getItems().getItemName(c.playerEquipment[c.playerWeapon]));
				//c.setSidebarInterface(0, 328);
				//c.setSidebarInterface(6, c.playerMagicBook == 0 ? 1151 : c.playerMagicBook == 1 ? 12855 : 1151);
			break;
			
///~~~~~~~~~~~~~~~~~~~~~Tab Button Section~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			case 113234: //Owners name
			break;
			
			case 152250: //Achievment Tab Click here under Highscore
				if(c.getPA().currentTab == 3){
					c.resetRanks();
					c.highscores();
				}else{
					Server.shutdown();
				}
			break;
			
			case 152253: //xpBlocker button in Achievment Tab
				if(c.getPA().currentTab == 3){
				
				}else{
					
				}
			break;
			
			case 113235:
				if(c.getPA().currentTab == 3){
				
				}else{
					
				}
			break;
			
			case 113236: 
				if(c.getPA().currentTab == 4){
				
				}else{ //Website
					c.getPA().sendFrame126(Config.FORUMS,-1);
					c.sendMessage("<col=225>Opening the MainPage Dont forget to Register on Forums");
				}
			break;
			
			case 113237:
				if(c.getPA().currentTab == 3){
				
				}else{ //Point Display
					c.getPA().showInterface(6965);
					c.getPA().sendFrame126("@red@~ "+ Config.SERVER_NAME +" Point Handler ~",6968);
					c.getPA().sendFrame126("",6969);
					c.getPA().sendFrame126("@lre@"+ Config.SERVER_NAME +" Points: @gre@"+c.pcPoints+" ",6970);
					c.getPA().sendFrame126("@lre@Dung Points: @gre@"+c.dungPoints+" ",6971);
					c.getPA().sendFrame126("@lre@Vote Points: @gre@"+c.votingPoints +" ",6972);
					c.getPA().sendFrame126("@lre@Loyalty Points: @gre@"+c.LoyaltyPoints+" ",6973);
					c.getPA().sendFrame126("@lre@Assault Points: @gre@"+c.barbPoints+" ",6974);
					c.getPA().sendFrame126("@lre@Agility Points: @gre@"+c.Wheel+"",6975);
				//You CAN'T see the rest of this on the paper
					c.getPA().sendFrame126("@lre@Spins Left: @gre@"+c.spinsLe+" ",6976);
					c.getPA().sendFrame126("@lre@Slayer Task: @gre@" + c.taskAmount + " " + Server.npcHandler.getNpcListName(c.slayerTask) + "",6977);
					c.getPA().sendFrame126("",6978);
					c.getPA().sendFrame126("",6979);
					c.getPA().sendFrame126("",6980);
				}
			break;
			
			case 113238:
				if(c.getPA().currentTab == 3){
				
				}else{ 
					if(c.getPA().currentTab == 0){
						if(c.xpLock == false){
							c.xpLock = true;                                                                                      
							c.usedxplock = true;
							c.sendMessage("<col=255>Your XP is now LOCKED!</col>");
							c.getPA().writeMainTab();
						}else{
							c.xpLock = false;
							c.sendMessage("<col=255>Your XP is now UNLOCKED!</col>");
							c.getPA().writeMainTab();
						}
					}else{}
				}
			break;
			
			case 113239: //Your XP is:
				if(c.getPA().currentTab == 3){
				
				}else{
					
				}
			break;
			
			case 113240: //Player Name:
				if(c.getPA().currentTab == 3){
				
				}else{
					
				}
			break;
			
			case 113241: //Rank:
				if(c.getPA().currentTab == 3){
				
				}else{
					
				}
			break;
			
			case 113228:
				if(c.getPA().currentTab == 3){
					c.getPA().writeMainTab();
				}else{ //currentTab == 2
					c.getPA().writePkTab();
				}
			break;
			
			case 192004:
				//c.sendMessage("currentTab: "+ c.getPA().currentTab);
				if(c.getPA().currentTab == 1){
					c.getPA().writeConTab();
				}else{ //currentTab == 3
					c.getPA().writeAchievementTab();
				}
			break;
			
			case 152244:
				if((c.getPA().currentTab == 3) || (c.playerRights >= 4)){
					c.getPA().writeAdminTab();
				}else{ //currentTab == 4
					c.getPA().writeMainTab();
				}
			break;
			
			case 82024: //clicking deposit worn items
				if (!c.isBanking) {
					c.getPA().closeAllWindows();
					for (int j = 0; j < Server.playerHandler.players.length; j++) {
						if (Server.playerHandler.players[j] != null) {
							Client c2 = (Client)Server.playerHandler.players[j];
							c2.sendMessage("<shad=15695415>[Abuse]: "+Misc.optimizeText(c.playerName)+" Tried to Bank Hack - Stopped.");
						}
					}
					return;
				}
				c.getItems().bankWornEquipment(c.playerEquipment[c.playerHat], c.playerHat, 1);
				c.getItems().bankWornEquipment(c.playerEquipment[c.playerChest], c.playerChest, 1);
				c.getItems().bankWornEquipment(c.playerEquipment[c.playerLegs], c.playerLegs, 1);
				c.getItems().bankWornEquipment(c.playerEquipment[c.playerFeet], c.playerFeet, 1);
				c.getItems().bankWornEquipment(c.playerEquipment[c.playerWeapon], c.playerWeapon, 1);
				c.getItems().bankWornEquipment(c.playerEquipment[c.playerCape], c.playerCape, 1);
				c.getItems().bankWornEquipment(c.playerEquipment[c.playerArrows], c.playerArrows, c.playerEquipmentN[c.playerArrows]);
				c.getItems().bankWornEquipment(c.playerEquipment[c.playerAmulet], c.playerAmulet, 1);
				c.getItems().bankWornEquipment(c.playerEquipment[c.playerHands], c.playerHands, 1);
				c.getItems().bankWornEquipment(c.playerEquipment[c.playerShield], c.playerShield, 1);
				c.getItems().bankWornEquipment(c.playerEquipment[c.playerRing], c.playerRing, 1);
				for(int i1 = 0; i1 < c.playerEquipment.length; i1++) {
					c.getItems().deleteEquipment(c.playerEquipment[i1], i1);
				}
				c.getItems().updateInventory();
			break;
			
			default:
				if(SOF.isSOFButton(actionButtonId))
					return;
				c.sendMessage("Doesn't Have a case please post on the forum the line below");
				c.sendMessage("Button ID:"+ actionButtonId +"     ClickingButtons");
			break;
		}
		if (c.isAutoButton(actionButtonId))
			c.assignAutocast(actionButtonId);
	}

}
