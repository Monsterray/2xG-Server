package server.model.players.packets;

import server.Config;
import server.model.players.Client;
import server.model.players.PacketType;

/**
 * Bank X Items
 **/

public class BankX2 implements PacketType {
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int Xamount = c.getInStream().readDWord();
		if(c.sellingId > 0 && c.sellingN > 0 && c.xInterfaceId == 7390){
			for (int i : Config.ITEM_TRADEABLE)  {
				if(i == c.sellingId) {
					c.sendMessage("You can't sell this item.");
					c.sellingId = 0;
					c.sellingN = 0;
					c.sellingS = 0;
					return;
				}		
			}
			if(c.sellingN > c.getItems().getItemAmount(c.sellingId))
				c.sellingN = c.getItems().getItemAmount(c.sellingId);
			int slot = -1;
			for(int x = 0; x < 10; x++){
				if(c.playerShop[x] == 0){
					slot = x;
					break;
				}
			}
			if(slot == -1){
				c.sendMessage("You can only be selling 10 items at once!");
				c.sellingId = 0;
				c.sellingN = 0;
				c.sellingS = 0;
				return;
			}
			if(c.getItems().playerHasItem(c.sellingId, c.sellingN, c.sellingS)){
				if (c.playerRights == 2 && (Config.ADMIN_CAN_SELL_ITEMS == false)) {	//  && !c.playerName.equalsIgnoreCase("Tommy17890")
				c.sendMessage("You can't sell items as an admin!");
				return;
			}
				c.getItems().deleteItem2(c.sellingId, c.sellingN);
				c.playerShop[slot] = c.sellingId;
				c.playerShopN[slot] = c.sellingN;
				c.playerShopP[slot] = Xamount;
				c.sendMessage("You put your items on sale.");
				c.sendMessage("Check your items by typing ::myshop, collect your money with ::collect");
				c.sendMessage("Before collecting make sure you've got inventory space, or you wont get shit!");
			}
			c.getShops().openPlayerShop(c);
			c.sellingId = 0;
			c.sellingN = 0;
			c.sellingS = 0;
			return;
		}
		if (Xamount < 0)// H A C K 3 R XDUPEfix (thanks gabbe :L)
		{
			Xamount = c.getItems().getItemAmount(c.xRemoveId);
		}
		if (Xamount == 0) {
			Xamount = 1;
		}
		if(c.getGamble().betting) {
			c.getGamble().playerBet = Xamount;
			c.getGamble().blackJack(c);
		}
		switch (c.xInterfaceId) {
			case 5064:
			if(c.inTrade) {
				c.sendMessage("You can't store items while trading!");
				return;
			}
			c.getItems().bankItem(c.playerItems[c.xRemoveSlot] , c.xRemoveSlot, Xamount);
			break;
				
			case 5382:
			c.getItems().fromBank(c.bankItems[c.xRemoveSlot] , c.xRemoveSlot, Xamount);
			break;

			case 3322:
			if(!c.getItems().playerHasItem(c.xRemoveId, Xamount))
			return;
			if(c.duelStatus <= 0) {
            	c.getTradeAndDuel().tradeItem(c.xRemoveId, c.xRemoveSlot, Xamount);
            } else {				
				c.getTradeAndDuel().stakeItem(c.xRemoveId, c.xRemoveSlot, Xamount);
			}  
			break;
				
			case 3415: 
			if(!c.getItems().playerHasItem(c.xRemoveId, Xamount))
			return;
			if(c.duelStatus <= 0) { 
            	c.getTradeAndDuel().fromTrade(c.xRemoveId, c.xRemoveSlot, Xamount);
			} 
			break;
				
			case 6669:
			if(!c.getItems().playerHasItem(c.xRemoveId, Xamount))
			return;
			c.getTradeAndDuel().fromDuel(c.xRemoveId, c.xRemoveSlot, Xamount);
			break;			
		}
	}
}