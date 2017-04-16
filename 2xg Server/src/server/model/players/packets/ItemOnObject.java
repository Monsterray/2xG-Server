package server.model.players.packets;

import server.model.items.UseItem;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.skills.Cooking;

/**
 * @author Monsterray
 */

public class ItemOnObject implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		/**
		 * Im not sure what this is for
		 */
		int a = c.getInStream().readUnsignedWord();
		int objectId = c.getInStream().readSignedWordBigEndian();
		int objectY = c.getInStream().readSignedWordBigEndianA();
		/**
		 * Im not sure what this is for
		 */
		int b = c.getInStream().readUnsignedWord();
		int objectX = c.getInStream().readSignedWordBigEndianA();
		int itemId = c.getInStream().readUnsignedWord();
		UseItem.ItemonObject(c, objectId, objectX, objectY, itemId); //this is called and has other stuff in it
		c.turnPlayerTo(objectX, objectY);
		c.cookingCoords[0] = objectX;
		c.cookingCoords[1] = objectY;
		if(c.getClue().getClueLevel(itemId) != 0){
			c.getClue().isCorrectChoice(objectId, itemId);
			return;
		}
		switch (objectId) {
			case 2142: // HERBLORE QUEST
				if(itemId == 2134){
					c.startAnimation(1670, 0);
					c.sendMessage("You dip the meat... It turns all blue?");
					c.getItems().deleteItem(2134, 1);
					c.getItems().addItem(523, 1);
				}else if(itemId == 2136){
					c.startAnimation(1670, 0);
					c.sendMessage("You dip the meat... It turns all blue?");
					c.getItems().deleteItem(2136, 1);
					c.getItems().addItem(524, 1);
				}else if(itemId == 2138){
					c.startAnimation(1670, 0);
					c.sendMessage("You dip the meat... It turns all blue?");
					c.getItems().deleteItem(2138, 1);
					c.getItems().addItem(525, 1);
				}else{
				}
			break;
			
			case 2452: // Air Alter
				if(itemId == 1438){
					c.startAnimation(1670, 0);
					c.sendMessage("A mysterious force grabs hold of you.");
					c.getPA().movePlayer(2841, 4829, 0);
				}else{
				}
			break;
			
			case 43: //boss water stuff
				if(itemId == 229){
					c.startAnimation(827, 0);
					c.sendMessage("You Fill Your Vial Full Of Boss Water");
					c.getItems().deleteItem(229, 1);
					c.getItems().addItem(17413, 1);
				}else if(itemId == 4151){
					c.sendMessage("Try Using A vial of BoSS Water");
				}else{
				}
			break;
			
			case 2455: // Earth Alter
				if(itemId == 1440){
					c.startAnimation(1670, 0);
					c.sendMessage("A mysterious force grabs hold of you.");
					c.getPA().movePlayer(2655, 4830, 0);
				}else{
				}
			break;
			 
			case 3312: // Fire Alter
				if(itemId == 1442){
					c.startAnimation(1670, 0);
					c.sendMessage("A mysterious force grabs hold of you.");
					c.getPA().movePlayer(2574, 4848, 0);
				}else{
				}
			break;
			
			case 3184: // Water Alter
				if(itemId == 1444){
					c.startAnimation(1670, 0);
					c.sendMessage("A mysterious force grabs hold of you.");
					c.getPA().movePlayer(2727, 4833, 0);
				}else{
				}
			break;
			
			case 3052: // Body Alter
				if(itemId == 1446){
					c.startAnimation(1670, 0);
					c.sendMessage("A mysterious force grabs hold of you.");
					c.getPA().movePlayer(2522, 4825, 0);
				}else{
				}
			break;
			
			case 2981: // Mind Alter
				if(itemId == 1448){
					c.startAnimation(1670, 0);
					c.sendMessage("A mysterious force grabs hold of you.");
					c.getPA().movePlayer(2792, 4827, 0);
				}else{
				}
			break;
			
			case 12269:
			case 2732:
			case 114:
			case 9374:
			case 2728:
			case 25465:
			case 11404:
			case 11405:
			case 11406:
				Cooking.cookThisFood(c, itemId, objectId); 
			break;
			
			default:
				c.sendMessage("Doesn't Have a case please post on the forum the line below");
				c.sendMessage("Item ID:"+ itemId +"  Object ID:"+ objectId +"     ItemOnObject");
			break;
		}
	}
}
