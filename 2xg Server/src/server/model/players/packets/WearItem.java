package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;


/**
 * Wear Item
 **/
 
public class WearItem implements PacketType {

		@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		c.wearId = c.getInStream().readUnsignedWord();
		c.wearSlot = c.getInStream().readUnsignedWordA();
		c.interfaceId = c.getInStream().readUnsignedWordA();
		int oldCombatTimer = c.attackTimer;
		if (c.playerIndex > 0 || c.npcIndex > 0)
			c.getCombat().resetPlayerAttack();
		if(c.wearId == 4565) {
			c.basket = true;
		} else {
			c.basket = false;
        }
		switch(c.wearId){
			/*
			case 5509:
			case 5510:
			case 5511:
			case 5512:
			case 5513:
			case 5514:
			case 5515:
				int pouch = -1;
				int a = c.wearId;
				if (a == 5509)
					pouch = 0;
				if (a == 5510)
					pouch = 1;
				if (a == 5512)
					pouch = 2;
				if (a == 5514)
					pouch = 3;
				c.getPA().emptyPouch(pouch);
			break;
			
			case 15262:
				c.getItems().deleteItem(15262, 1);
				c.getItems().addItem(18016, 15000);
			break;
			
			case 13325:
				c.sendMessage("You Jump In Monsterrays Car LikeaBoss");
				c.npcId2 = 2221;
				c.isNpc = true;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			break;
			
			case 7927:
				c.sendMessage("@red@do ::return to turn back to normal!");
				c.sendMessage("As you put on the ring you turn into an egg!");
				c.npcId2 = 3689;
				c.isNpc = true;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			break;
			
			case 4024:
				c.sendMessage("@red@do ::return to turn back to normal!");
				c.sendMessage("you transformed!");
				c.npcId2 = 1459;
				c.isNpc = true;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			break;
			
			case 13899:
				c.degWep = true;
				c.sendMessage("Your now wearing a normal VLS, it might degrade!");
			break;
			
			case 4025:
				c.sendMessage("@red@do ::return to turn back to normal!");
				c.sendMessage("you transformed!");
				c.npcId2 = 1481;
				c.isNpc = true;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			break;
			
			case 4026:
				c.sendMessage("@red@do ::return to turn back to normal!");
				c.sendMessage("you transformed!");
				c.npcId2 = 1482;
				c.isNpc = true;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			break;
			
			case 4029:
				c.sendMessage("@red@do ::return to turn back to normal!");
				c.sendMessage("you transformed!");
				c.npcId2 = 1465;
				c.isNpc = true;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			break;
			
			case 295:
				c.sendMessage("@red@do ::return to turn back to normal!");
				c.sendMessage("As you put on the H'ween amulet you turn into Dracula!");
				c.npcId2 = 757;
				c.isNpc = true;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			break;
			
			case 6583:
				c.sendMessage("@red@do ::return to turn back to normal!");
				c.sendMessage("As you put on the ring you turn into a rock!");
				c.npcId2 = 2626;
				c.isNpc = true;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			break;
			*/
			default:
				c.sendMessage("Doesn't Have a case please post on the forum the line below");
				c.sendMessage("Item ID:"+ c.wearId +"     WearItem");
			break;
		}
		
		//c.attackTimer = oldCombatTimer;
		c.getItems().wearItem(c.wearId, c.wearSlot);
	}
}