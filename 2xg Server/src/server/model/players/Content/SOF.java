package server.model.players.Content;

import server.Config;
import server.model.minigames.Event;
import server.model.minigames.EventContainer;
import server.model.minigames.EventManager;
import server.model.players.Client;
import server.util.Misc;

public class SOF {
/**
	** 2xG Squeal of fortune!!
**/

	public static boolean hasGottenReward = false;
	public static boolean hasTakenReward = false;
	public static int SofRewards[] = {4087,11383,10828, 1345, 1347, 1349, 1540, 1540, 4131, 4129, 4123, 4156, 18830, 14479, 14479, 14479, 11337, 1147,892, 1052, 1052, 2528, 2528, 2528, 1093, 1127, 1201,1275, 1303, 1319, 1333, 11686, 4151, 861, 2581, 1127, 1147, 1163, 1185, 1275, 1303, 1319, 1333, 1359, 1373, 2491, 2497, 2503, 861, 859, 2581, 2651};
	public static int[] SOFActionIds = {65064, 189118, 189121, 64123, 64223, 64229, 64226, 64220};
	
	public static boolean isSOFButton(int ActionId){
		for(int i = 0; SOFActionIds.length-1 >= i; i++){
			if(ActionId == SOFActionIds[i])
				return true;
		}
		return false;
	}
	
	public static void HandleSquealButtons(Client c, int packetType, int packetSize) { // Used for not spamming up clickingbuttons with SOF
		int actionButtonId = Misc.hexToInt(c.getInStream().buffer, 0, packetSize);	
		switch (actionButtonId) {
			case 65064:
				c.getPA().closeAllWindows();
			break;
			
			case 189118:// SOF setsidebarinterface, play SOF
				if(c.spinsLe == 0) {
					c.sendMessage("You don't have any spins.");
					c.isSpinningDaSof = false;
					return;
				}
				if(c.item2 > 0 && c.amount2 > 0 || c.claimLater == true) {
					displayReward(c, c.item2, c.amount2); 
					return;
				} 
				if(c.spinsLe > 0 && c.antiFuckUp == false) {
					c.getPA().closeAllWindows();
					c.getPA().showInterface(16500);
					c.antiFuckUp = false;
					c.antiFuckUp2 = false;
					c.getPA().sendFrame126(""+c.spinsLe+"", 48508);
					c.getPA().sendFrame126(""+c.spinsLe+"", 16510);
				}
			break;
			
			case 189121: // SOF sidebarinterface, buy spins
				c.getPA().sendFrame126("http://2xGng.iftopic.com/donate", 12000);
			break;
			
			case 64123: // Start spin button red one
				if(c.item2 > 0 || c.amount2 > 0) {
					c.sendMessage("Please relog before doing this.");
				return;
				}
				hasGottenReward = false;
				hasTakenReward = false;
				c.isSpinningDaSof = false;
				c.antiFuckUp2 = false;
				startSpin(c);
			break;
			
			case 64223: // play again button
				if(hasGottenReward == true && hasTakenReward == false) { // Play again button
					c.sendMessage("You must decide if you want to claim or discard the item first.");
					c.antiFuckUp = false;
					return;
				}
			break;
			
			case 64229: // claim later button
				if(hasGottenReward == true && hasTakenReward == false) {
					c.claimLater = true;
					c.antiFuckUp = false;
					c.SaveGame();
					c.isSpinningDaSof = false;
					c.getPA().closeAllWindows();
					c.antiFuckUp2 = false;
				}
			break;	
			
			case 64226: // discard button
				if(hasGottenReward == true && hasTakenReward == false) {
					if(Config.DEBUG_SOF_REWARDS) {
						System.out.println("Player "+ Misc.optimizeText(c.playerName) +" Discarded "+c.getItems().getItemName(c.item2)+" from SOF!");
					}
				c.antiFuckUp = false;
				c.isSpinningDaSof = false;
				c.claimLater = false;
				c.getPA().closeAllWindows();
				hasGottenReward = false;
				c.antiFuckUp2 = false;
				c.item2 = 0;
				c.amount2 = 0;
				}
			break;
			
			case 64220: // Claim item button
				if(hasGottenReward == true && hasTakenReward == false) {
					c.getItems().addItem(c.item2, c.amount2);
					if(Config.DEBUG_SOF_REWARDS) {
						System.out.println("Player "+ Misc.optimizeText(c.playerName) +" got "+c.getItems().getItemName(c.item2)+" from SOF!");
					}
					hasTakenReward = true;
					hasGottenReward = false;
					c.isSpinningDaSof = false;
					c.sendMessage("Enjoy your reward, "+c.getItems().getItemName(c.item2)+"!");
					c.claimLater = false;
					c.antiFuckUp = false;
					c.claimLater = false;
					c.antiFuckUp2 = false;
					c.getPA().closeAllWindows();
					c.item2 = 0;
					c.amount2 = 0;
				}
			break;
		}
	}
		
	public static void startSpin(final Client c) {
		c.getPA().sendFrame126(""+c.spinsLe+"", 16510);
		if(c.amount2 != 0 && c.item2 != 0) {
			c.sendMessage("Please relog before doing this.");
			return;
		}
		if(c.spinsLe > 0 && c.isSpinningDaSof == false && hasGottenReward == false && c.antiFuckUp == false && c.claimLater == false && c.item2 == 0 && c.amount2 == 0) {
			c.getPA().sendFrame126(""+c.spinsLe+"", 16510);
			c.isSpinningDaSof = true;
			hasGottenReward = false;
			c.claimLater = false;
			c.amount2 = 0;
			c.item2 = 0;
			c.antiFuckUp = true;
			c.getPA().closeAllWindows();
			c.getPA().showInterface(16500);
			c.antiFuckUp2 = true;
			c.spinsLe -= 1;
			c.getPA().sendFrame126(""+c.spinsLe+"", 48508);
			c.getPA().sendFrame126(""+c.spinsLe+"", 16510);
			c.sendMessage("<shad=15695415>Spinning Squeal, Good Luck!");
			setDaVariables();
			EventManager.getSingleton().addEvent(new Event() {
				public void execute(EventContainer e) {
					c.delay -= 1;	
					if(c.delay == 22) {
						c.getPA().closeAllWindows();
						handleReward(c);
						c.delay = 24;
						e.stop();
					}	
//					if(c == null) {	// Said it wasn't used 4/14/17
//						e.stop();	// Said it wasn't used 4/14/17
//					}
					if(c.delay < 21) {
						c.delay = 24;
						e.stop();
					}
				}
//				public void stop() {	// Said it wasn't used 4/14/17
//				
//				}
			}, 1);
		}
	}
	
	public static void handleReward(Client c) {
		c.getPA().closeAllWindows();
		c.isSpinningDaSof = true;
		displayReward(c, SofRewards[Misc.random(50)], 1);
		c.antiFuckUp2 = true;
		c.SaveGame();
	}
		
	public static void displayReward(Client c, int item, int amount) {
		int[] items = {item	};
		int[] amounts = {amount};
		if(item == 0 && amount == 0) {
			c.getPA().closeAllWindows();
			c.sendMessage("[2xG - Null Reward] Randomly Adding New Reward...");
			displayReward(c, SofRewards[Misc.random(50)], 1);
			return;
		}
		if(c != null && item > -2 && amount > -2 && item != 0 && amount != 0) {
			c.outStream.createFrameVarSizeWord(53);
			c.outStream.writeWord(16602);
			c.outStream.writeWord(items.length);
			for(int i = 0; i < items.length; i++) {
				if(c.playerItemsN[i] > 254) {
					c.outStream.writeByte(255);
					c.outStream.writeDWord_v2(amounts[i]);
				} else {
					c.outStream.writeByte(amounts[i]);
				}
				if(items[i] > 0) {
					c.outStream.writeWordBigEndianA(items[i] + 1);
				} else {
					c.outStream.writeWordBigEndianA(0);
				}
			}
			c.outStream.endFrameVarSizeWord();
			c.flushOutStream();
			c.item2 = item;
			c.amount2 = amount;
			c.SaveGame();
			hasGottenReward = true;
			hasTakenReward = false;
			if(item == 16425 || item == 18349 || item == 17720 || item == 18353) {
				c.getPA().sendFrame126("Wow! You won: "+c.getItems().getItemName(c.item2)+"!", 16619);
			} else {
				if(item == 2528) {
					c.getPA().sendFrame126("Nice! You won: XP Lamp!", 16619);
				} else {
					c.getPA().sendFrame126("Not bad! You won: "+c.getItems().getItemName(c.item2)+"", 16619);
				}
			}
			c.getPA().sendFrame126(""+c.spinsLe+"", 16620);
			c.getPA().showInterface(16600);
			c.isSpinningDaSof = true;
			c.antiFuckUp2 = true;
		}
	}
	
	public static void setDaVariables() {
		hasGottenReward = false;
		hasTakenReward = false;
	}
}