package server.model.shops;

import server.Config;
import server.Server;
import server.model.items.Item;
import server.model.players.Client;
import server.world.ShopHandler;

public class ShopAssistant {

	private Client c;
	
	public ShopAssistant(Client client) {
		this.c = client;
	}
	
	/**
	*Shops
	**/
	
	public void openShop(int ShopID){
		c.getItems().resetItems(3823);
		resetShop(ShopID);
		c.isShopping = true;
		c.myShopId = ShopID;
		c.getPA().sendFrame248(3824, 3822);
		c.getPA().sendFrame126(ShopHandler.ShopName[ShopID], 3901);
	}

	public boolean shopSellsItem(int itemID) {
		for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
			if(itemID == (ShopHandler.ShopItems[c.myShopId][i] - 1)) {
				return true;
			}
		}
		return false;
	}
	
	public void updatePlayerShop() {
		for (int i = 1; i < Config.MAX_PLAYERS; i++) {
			if (Server.playerHandler.players[i] != null) {
				if (Server.playerHandler.players[i].isShopping == true && Server.playerHandler.players[i].myShopId == c.myShopId && i != c.playerId) {
					Server.playerHandler.players[i].updateShop = true;
				}
			}
		}
	}
	
	public void updateshop(int i){
		resetShop(i);
	}
	
	public void resetShop(int ShopID) {
		synchronized(c) {
			int TotalItems = 0;
			for (int i = 0; i < ShopHandler.MaxShopItems; i++) {
				if (ShopHandler.ShopItems[ShopID][i] > 0) {
					TotalItems++;
				}
			}
			if (TotalItems > ShopHandler.MaxShopItems) {
				TotalItems = ShopHandler.MaxShopItems;
			}
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(3900);
			c.getOutStream().writeWord(TotalItems);
 			int TotalCount = 0;
			for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
				if (ShopHandler.ShopItems[ShopID][i] > 0 || i <= ShopHandler.ShopItemsStandard[ShopID]) {
					if (ShopHandler.ShopItemsN[ShopID][i] > 254) {
						c.getOutStream().writeByte(255); 					
						c.getOutStream().writeDWord_v2(ShopHandler.ShopItemsN[ShopID][i]);	
					} else {
						c.getOutStream().writeByte(ShopHandler.ShopItemsN[ShopID][i]);
					}
					if (ShopHandler.ShopItems[ShopID][i] > Config.ITEM_LIMIT || ShopHandler.ShopItems[ShopID][i] < 0) {
						ShopHandler.ShopItems[ShopID][i] = Config.ITEM_LIMIT;
					}
					c.getOutStream().writeWordBigEndianA(ShopHandler.ShopItems[ShopID][i]);
					TotalCount++;
				}
				if (TotalCount > TotalItems) {
					break;
				}
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();	
		}
	}
	
	public double getItemShopValue(int ItemID, int Type, int fromSlot) {
		if(c.myShopId == 7390){
			return c.myShopClient.playerShopP[fromSlot];
		}
		double ShopValue = 1;
//		double Overstock = 0;	// Said it wasn't used 4/14/17
		double TotPrice = 0;
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == ItemID) {
					ShopValue = Server.itemHandler.ItemList[i].ShopValue;
				}
			}
		}
		
		TotPrice = ShopValue;

		if (ShopHandler.ShopBModifier[c.myShopId] == 1) {
			TotPrice *= 1; 
			TotPrice *= 1;
			if (Type == 1) {
				TotPrice *= 1; 
			}
		} else if (Type == 1) {
			TotPrice *= 1; 
		}
		return TotPrice;
	}
	
	public void openPlayerShop(Client o){	
		if(o == null || o.properLogout)
			return;	
		c.getItems().resetItems(3823);
		resetShop(o);
		c.myShopClient = o;
		c.myShopId = 7390;
		c.isShopping = true;
		c.getPA().sendFrame248(3824, 3822);
		c.getPA().sendFrame126(o.playerName+"'s Shop!", 3901);
	}
	
	public int[] fixArray(int[] array){
		int arrayPos = 0;
		int[] newArray = new int[array.length];
		for(int x = 0; x < array.length; x++){
			if(array[x] != 0){
				newArray[arrayPos] = array[x];
				arrayPos++;
			}
		}
		return newArray;
	}

	public void fixShop(Client o){
		o.playerShop = fixArray(o.playerShop);
		o.playerShopN = fixArray(o.playerShopN);
		o.playerShopP = fixArray(o.playerShopP);	
	}

	public void resetShop(Client o) {
		synchronized(c) {
			fixShop(o);
			for (int x = 0; x < 10; x++) {
				if (o.playerShopN[x] <= 0) {
					o.playerShop[x] = 0;
				}
			}
			int TotalItems = 0;
			for (int i = 0; i < 10; i++) {
				if (o.playerShop[i] > 0) {
					TotalItems++;
				}
			}
			if (TotalItems > 10) {
				TotalItems = 10;
			}
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(3900);
			c.getOutStream().writeWord(TotalItems);
 			int TotalCount = 0;
			for (int i = 0; i < o.playerShop.length; i++) {
				if (o.playerShop[i] > 0) {
					if (o.playerShopN[i] > 254) {
						c.getOutStream().writeByte(255); 					
						c.getOutStream().writeDWord_v2(o.playerShopN[i]);	
					} else {
						c.getOutStream().writeByte(o.playerShopN[i]);
					}
					c.getOutStream().writeWordBigEndianA((o.playerShop[i]+1));
					TotalCount++;
				}
				if (TotalCount > TotalItems) {
					break;
				}
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();	
		}
	}
	
	public int getItemShopValue(int itemId) {
		for (int i = 0; i < Config.ITEM_LIMIT; i++) {
			if (Server.itemHandler.ItemList[i] != null) {
				if (Server.itemHandler.ItemList[i].itemId == itemId) {
					return (int)Server.itemHandler.ItemList[i].ShopValue;
				}
			}	
		}
		return 0;
	}
	
	/**
	*buy item from shop (Shop Price)
	**/
	
	public void buyFromShopPrice(int removeId, int removeSlot){
		int ShopValue = (int)Math.floor(getItemShopValue(removeId, 0, removeSlot));
		ShopValue *= 1;
		String ShopAdd = "";
		if (c.myShopId == 7390 && c.myShopClient != null && !c.myShopClient.playerName.equals(c.playerName)) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + c.myShopClient.playerShopP[removeSlot] + " coins.");
			return;
		}else if (c.myShopId == 7390 && c.myShopClient != null && c.myShopClient.playerName.equals(c.playerName)) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + c.playerShopP[removeSlot] + " coins.");
			return;
		}
        if (c.myShopId == 54) {
            c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " Vote Points");
            return;
        }
		if (c.myShopId == 20) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " 2xG Points!");
			return;
		}
		if (c.myShopId == 18) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " 2xG Points!");
			return;
		}
		if (c.myShopId == 84) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " Dungeoneering Tokens.");
			return;
		}
		if (c.myShopId == 16) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " Agility Points.");
			return;
		}
				if (c.myShopId == 100) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " Assault Points.");
			return;
		}
		if (c.myShopId == 85) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " Dungeoneering Tokens.");
			return;
		}
		if (c.myShopId == 17) {
			c.sendMessage(c.getItems().getItemName(removeId)+": currently costs " + getSpecialItemValue(removeId) + " mage Points.");
			return;
		}
		if (c.myShopId == 15) {
			c.sendMessage("This item current costs " + c.getItems().getUntradePrice(removeId) + " coins.");
			return;
		}
		if (ShopValue >= 1000 && ShopValue < 1000000) {
			ShopAdd = " (" + (ShopValue / 1000) + "K)";
		} else if (ShopValue >= 1000000) {
			ShopAdd = " (" + (ShopValue / 1000000) + " million)";
		}
		c.sendMessage(c.getItems().getItemName(removeId)+": currently costs "+ShopValue+" coins"+ShopAdd);
	}
	
	public int getSpecialItemValue(int id) {
		switch (id) {
	        //Vote Point Shop
	        case 13362:
	        	return 10;
	        	
	        case 13358:
	        	return 10;
	        	
	        case 13360:
	        	return 10;
	        	
	        case 14001:
	        	return 2;
	        	
	        case 4566:
	        	return 2;
	        	
	        case 14002:
	        	return 2;
	        	
			case 10550: // range hat
				return 65;
				
			case 10548: // fight hat
				return 65;
			
			case 10551: // torso
				return 75;
			
			case 11730: // sara sword
				return 75;
			
			case 1897:
            	return 75;
			
			case 13344: // ancient blah blah helm
				return 135;
			
			case 13342:
				return 200;
			
			case 13340:
				return 150;
			
					case 10552: // torso
			return 75;
		
		
		//END OF ASSAULT
		
			case 1767:
			case 1765:	
			case 1771:
			case 5559:
				return 30;
				
			case 15486:
				return 250;
				
			case 6889:
				return 45;
				
			case 6916:
			case 6918:
			case 6920:
			case 6922:
			case 6924:
			return 70;
			
			case 19790:
			return 140;
			
			case 11663:
			case 11664:
			case 11665:
			case 8842:
			return 40;
			case 17020:
			return 70;
			case 17004:
			case 17003:
			case 17005:
			case 17002:
			return 50;
			case 15308:
			case 15312:
			case 15316:
			case 15320:
			case 15324:
			return 4;
			case 11696:
			case 11698:
			case 11700:
			return 600;
			case 11694:
			return 800;
			case 18357:
			return 2200;
			case 10499:
			return 30;
			case 13887:
			case 13893:
			return 580;
			case 18017:
			case 18018:
			case 18019:
			case 18020:
			return 3;
			case 14508:
			return 250;
			case 8845:
			return 10;
			case 8846:
			return 10;
			case 8847:
			return 15;
			case 8848:
			return 20;
			case 8849:
			case 8850:
			return 25;
			case 6570:
			return 75;
                        case 10887:
                        return 250;
			case 11851:
			case 12210:
			case 12213:
			case 12216:
			case 12219:
			case 12222:
			return 100;
			case 11724:
			return 1200;
			case 11728:
			return 250;
			case 11720:
			return 1000;
			case 11722:
			return 900;
			case 11726:
			return 1100;
			case 15126:
			return 200;
			case 13263:
			return 200;
			case 18335:
			return 700;
			case 19780:
			return 1000;
			case 6585:
			return 15;
			case 16713:
			return 400;
                        case 15017:
                        case 15018:
                        return 850;
			case 13870:
			case 13873:
			return 300;
			case 13876:
			return 250;
			case 15055:
			return 5;
                        case 15031:
                        return 600;
                        case 15007:
                        case 15006:
                        return 700;
                        case 15005:
                        case 15004:
                        return 850;
                        case 15003:
                        case 15002:
			case 15001:				
       			case 15000:
                        return 300;
			case 16689:
			return 1000;
			case 17259:
			return 1000;
		        case 17361: 
			return 1000;
			case 16711:
			return 900;
			case 11821:
			return 1000;
			case 11822:
			return 1000;
			case 11820:
			return 800;
			case 15022:
			case 15026:
                        return 750;
			case 15021:
			case 15023:
			return 850;
			case 13350:
			return 2200;
			case 15332:
			return 7;
			case 15220:
			return 150;
			case 13351:
			return 2200;
                        case 15025:
			return 230;
                        case 15024:
                        return 170;
                        case 15043:
			case 15042:
                        return 790;
                      	case 15041:
                        case 15040:
			return 650;
                        case 15051:
                        return 500;
                        case 15050:
                        return 700;
			case 18355:
			return 1500;
			case 19785:
			return 160;
			case 19786:
			return 140;
			case 13858:
			case 13861:
			return 300;
			case 1555:
			case 7584:
			case 1556:
			case 1557:
			case 1558:
			return 150;
			case 13738:
			case 13742:
			case 13744:
			return 500;
						case 13740:
			return 650;
			case 18351:
                        case 18353:
                        case 18349:
                        return 3000;
			case 13890:
			case 13884:
			return 400;
			case 13899:
			return 600;
			case 13896:
			return 350;
			case 13902:
			return 550;
			case 15505:
			return 45;
			case 15507:
			return 45;
			case 15509:
			case 15511:
			return 35;
			case 15073:
			return 55;
			case 15074:
			return 55;
			case 17025:
			return 35;
			case 17018:
			return 90;
			case 17019:
			return 80;
			case 19784:
			return 1000;
			case 15054:
			return 200;
			case 17271:
			return 150;
			case 14936:
			return 300;
			case 14938:
			return 250;
			case 15090:
			case 15091:
			case 15092:
			case 15093:
			case 15094:
			case 15095:
			case 15096:
			case 15097:
			case 15098:
			return 350;
			case 15085:
			return 1300;
			case 15081:
			return 1150;
			case 15080:
			return 1150;
 			case 15083:
			return 600;
			case 13352:
			case 13353:
			case 13354:
			return 700;
			// START OF AGILITY STORE
			case 10400: // ELEGANT TOP
			case 10432: // green
			case 10428:
			case 10404:
			return 300;
			case 10402: // ELEGANT SKIRT
			case 10430: // blue skirt
			case 10434:
			case 10406:
			return 250;
			case 10766: // BLACK BOATER
			case 10764: // blue boater
			case 10758: // redboater
			case 10762: // green boater
			return 220;
			case 13661: // adze
			return 600; // adze
			case 16222:
			return 1100;
                        case 14497:
                        case 14501:
                        return 150;
                        case 14499:
                        return 60;
                        case 15060:
                        case 15062:
                        return 150;
                        case 15061:
                        return 70;
                        case 15020:
                        return 700;
                        case 2577:
                        case 2581:
                        return 40;
                        case 6914:
                        case 6890:
                        return 45;

		}
		return 0;
	}
	
	/**
	*Sell item to shop (Shop Price)
	**/
	public void sellToShopPrice(int removeId, int removeSlot) {
		if (c.playerRights == 2 && Config.ADMIN_CAN_SELL_ITEMS == false) {
			c.sendMessage("You can't sell "+c.getItems().getItemName(removeId).toLowerCase()+".");
			return;
		}
		for (int i : Config.ITEM_SELLABLE) {
			if(c.myShopId == 7390){
				if (c.playerRights == 2 && Config.ADMIN_CAN_SELL_ITEMS == false && !c.playerName.equalsIgnoreCase("Tommy17890")) {
					c.sendMessage("You can't sell items u dumb fuck.");
					return;
				}
				c.sendMessage("Choose your price by pushing sell on the item.");
				return;
			}
			if (i == removeId) {
				c.sendMessage("You can't sell "+c.getItems().getItemName(removeId).toLowerCase()+".");
				return;
			}
		}
		boolean IsIn = false;
		if (ShopHandler.ShopSModifier[c.myShopId] > 1) {
			for (int j = 0; j <= ShopHandler.ShopItemsStandard[c.myShopId]; j++) {
				if (removeId == (ShopHandler.ShopItems[c.myShopId][j] - 1)) {
					IsIn = true;
					break;
				}
			}
		} else {
			IsIn = true;
		}
		if (IsIn == false) {
			c.sendMessage("You can't sell "+c.getItems().getItemName(removeId).toLowerCase()+" to this store.");
		} else {
			int ShopValue = (int)Math.floor(getItemShopValue(removeId, 1, removeSlot));
			String ShopAdd = "";
			if (ShopValue >= 1000 && ShopValue < 1000000) {
				ShopAdd = " (" + (ShopValue / 1000) + "K)";
			} else if (ShopValue >= 1000000) {
				ShopAdd = " (" + (ShopValue / 1000000) + " million)";
			}
			c.sendMessage(c.getItems().getItemName(removeId)+": shop will buy for "+ShopValue+" coins"+ShopAdd);
		}
	}
	
	public boolean sellItem(int itemID, int fromSlot, int amount) {
			if(c.inTrade) {
            		c.sendMessage("You cant sell items to the shop while your in trade!");
           		return false;
            		}
			if (c.playerRights == 2 && Config.ADMIN_CAN_SELL_ITEMS == false && !c.playerName.equalsIgnoreCase("Tommy17890")) {
				c.sendMessage("You can't sell "+c.getItems().getItemName(itemID).toLowerCase()+".");
				return false;
			}
			if(c.myShopId == 7390){
			for (int i : Config.ITEM_TRADEABLE)  {
				if(i == itemID) {
					c.sendMessage("You can't sell this item.");
					return false;
				}		
			}
			if(c.playerName.equals(c.myShopClient.playerName)){
			c.sellingId = itemID;
			c.sellingN = amount;
			c.sellingS = fromSlot;
			c.xInterfaceId = 7390;
			c.outStream.createFrame(27);
			}else{
				c.sendMessage("You can only sell items on your own store.");
			}
			return true;
		}
		if (c.myShopId == 14)
			return false;
		for (int i : Config.ITEM_SELLABLE) {
			if (i == itemID) {
				c.sendMessage("You can't sell "+c.getItems().getItemName(itemID).toLowerCase()+".");
				return false;
			} 
		}
		
		if (amount > 0 && itemID == (c.playerItems[fromSlot] - 1)) {
			if (ShopHandler.ShopSModifier[c.myShopId] > 1) {
				boolean IsIn = false;
				for (int i = 0; i <= ShopHandler.ShopItemsStandard[c.myShopId]; i++) {
					if (itemID == (ShopHandler.ShopItems[c.myShopId][i] - 1)) {
						IsIn = true;
						break;
					}
				}
				if (IsIn == false) {
					c.sendMessage("You can't sell "+c.getItems().getItemName(itemID).toLowerCase()+" to this store.");
					return false;
				}
			}

			if (amount > c.playerItemsN[fromSlot] && (Item.itemIsNote[(c.playerItems[fromSlot] - 1)] == true || Item.itemStackable[(c.playerItems[fromSlot] - 1)] == true)) {
				amount = c.playerItemsN[fromSlot];
			} else if (amount > c.getItems().getItemAmount(itemID) && Item.itemIsNote[(c.playerItems[fromSlot] - 1)] == false && Item.itemStackable[(c.playerItems[fromSlot] - 1)] == false) {
				amount = c.getItems().getItemAmount(itemID);
			}
			//double ShopValue;
			//double TotPrice;
			int TotPrice2 = 0;
			//int Overstock;
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int)Math.floor(getItemShopValue(itemID, 1, fromSlot));
				if (c.getItems().freeSlots() > 0 || c.getItems().playerHasItem(995)) {
					if (Item.itemIsNote[itemID] == false) {
						c.getItems().deleteItem(itemID, c.getItems().getItemSlot(itemID), 1);
					} else {
						c.getItems().deleteItem(itemID, fromSlot, 1);
					}
					c.getItems().addItem(995, TotPrice2);
					addShopItem(itemID, 1);
				} else {
					c.sendMessage("You don't have enough space in your inventory.");
					break;
				}
			}
			c.getItems().resetItems(3823);
			resetShop(c.myShopId);
			updatePlayerShop();
			return true;
		}
		return true;
	}
	
	public boolean addShopItem(int itemID, int amount) {
		boolean Added = false;
		if (amount <= 0) {
			return false;
		}
		if (Item.itemIsNote[itemID] == true) {
			itemID = c.getItems().getUnnotedItem(itemID);
		}
		for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
			if ((ShopHandler.ShopItems[c.myShopId][i] - 1) == itemID) {
				ShopHandler.ShopItemsN[c.myShopId][i] += amount;
				Added = true;
			}
		}
		if (Added == false) {
			for (int i = 0; i < ShopHandler.ShopItems.length; i++) {
				if (ShopHandler.ShopItems[c.myShopId][i] == 0) {
					ShopHandler.ShopItems[c.myShopId][i] = (itemID + 1);
					ShopHandler.ShopItemsN[c.myShopId][i] = amount;
					ShopHandler.ShopItemsDelay[c.myShopId][i] = 0;
					break;
				}
			}
		}
		return true;
	}
	
	public long buyDelay;
	
	public boolean buyItem(int itemID, int fromSlot, int amount) {
		if(System.currentTimeMillis() - buyDelay < 1500) {
			return false;
		}
		if(c.myShopId == 7390 && c.myShopClient != null && !c.myShopClient.properLogout && !c.playerName.equals(c.myShopClient.playerName)){
						if(c.playerRights == 2) {
				c.sendMessage("Sorry but buying as admin is disabled :/..");
			return false;
		}
		int bought = 0;
			int price = c.myShopClient.playerShopP[fromSlot];
			if(amount > c.myShopClient.playerShopN[fromSlot])
				amount = c.myShopClient.playerShopN[fromSlot];
			for(int x = 0; x < amount; x++){
				if(c.getItems().playerHasItem(995, c.myShopClient.playerShopP[fromSlot]) && c.getItems().freeSlots() > 0){
					c.getItems().deleteItem2(995, c.myShopClient.playerShopP[fromSlot]);
					c.getItems().addItem(c.myShopClient.playerShop[fromSlot], 1);
					c.myShopClient.playerShopN[fromSlot]--;
					c.myShopClient.playerCollect += c.myShopClient.playerShopP[fromSlot];
					if(c.myShopClient.playerShopN[fromSlot] == 0){
						c.myShopClient.playerShop[fromSlot] = 0;
						c.myShopClient.playerShopP[fromSlot] = 0;
					}
					bought++;
				}else{
					c.sendMessage("Not enought space or money.");
					break;
				}
			}
			if(bought > 0){
							if(c.playerRights == 2) {
				c.sendMessage("Sorry but buying as admin is disabled :/..");
			return false;
		}
			resetShop(c.myShopClient);
			c.getItems().resetItems(3823);;
			c.sendMessage("You just bought "+bought+" "+c.getItems().getItemName(itemID)+" for "+ (bought*price));
			c.myShopClient.sendMessage(c.playerName+" has bought "+bought+" "+c.getItems().getItemName(itemID)+" from you!");
			c.myShopClient.sendMessage("You now have "+c.myShopClient.playerCollect+" coins to collect (::collect)");
			}
			return false;
		}else if(c.myShopId == 7390 && c.myShopClient != null && !c.myShopClient.properLogout && c.playerName.equals(c.myShopClient.playerName)){
							if(c.playerRights == 2) {
				c.sendMessage("Sorry but buying as admin is disabled :/..");
			return false;
		}
			if(amount > c.myShopClient.playerShopN[fromSlot])
				amount = c.myShopClient.playerShopN[fromSlot];
			for(int x = 0; x < amount; x++){
				if(c.getItems().freeSlots() > 0){
					c.getItems().addItem(c.myShopClient.playerShop[fromSlot], 1);
					c.myShopClient.playerShopN[fromSlot]--;
					if(c.myShopClient.playerShopN[fromSlot] == 0){
						c.myShopClient.playerShop[fromSlot] = 0;
						c.myShopClient.playerShopP[fromSlot] = 0;
						fixShop(c);
					}
				}else{
					c.sendMessage("Not enought space.");
					break;
				}
			}
			resetShop(c.myShopClient);
			c.getItems().resetItems(3823);
			return false;
		}else if(c.myShopId == 7390){
			return false;
		}
		if (ShopHandler.ShopItems[c.myShopId][fromSlot]-1 != itemID && c.myShopId != 14 && c.myShopId != 1 && c.myShopId != 7390) {
			c.sendMessage("Stop trying to cheat.");
			return false;
		}

		if (c.myShopId == 14) {
			skillBuy(itemID);
			return false;

		} else if (c.myShopId == 15) {
			buyVoid(itemID);
			return false;		
		
		} else if (c.myShopId == 1) {
			buyVoid(itemID);
			return false;
                }
		if(itemID != itemID) {
			c.sendMessage("Don't dupe or you will be IP Banned");
			return false;
		}

		if(!shopSellsItem(itemID))
			return false;

		if (amount > 0) {
			if (amount > ShopHandler.ShopItemsN[c.myShopId][fromSlot]) {
				amount = ShopHandler.ShopItemsN[c.myShopId][fromSlot];
			}
			//double ShopValue;
			//double TotPrice;
			int TotPrice2 = 0;
			//int Overstock;
			int Slot = 0;
			int Slot1 = 0;//Tokkul
//			int Slot2 = 0;//Pking Points	// Said it wasn't used 4/14/17
			int Slot3 = 0;//Donator Gold

			if (c.myShopId == 18) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 21) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 16) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 20) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 100) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 17) {
				handleOtherShop(itemID);
				return false;
			}
            if (c.myShopId == 54) {
				handleOtherShop(itemID);
				return false;
			}	
			if (c.myShopId == 84) {
				handleOtherShop(itemID);
				return false;
			}
			if (c.myShopId == 85) {
				handleOtherShop(itemID);
				return false;
			}
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int)Math.floor(getItemShopValue(itemID, 0, fromSlot));
				Slot = c.getItems().getItemSlot(995);
				Slot1 = c.getItems().getItemSlot(6529);
				Slot3 = c.getItems().getItemSlot(5555);
				if (Slot == -1 && c.myShopId != 11 && c.myShopId != 29 && c.myShopId != 30 && c.myShopId != 31 && c.myShopId != 84 && c.myShopId != 85 && c.myShopId != 100) {
					c.sendMessage("You don't have enough coins.");
					break;
				}
				if(Slot1 == -1 && c.myShopId == 29 || c.myShopId == 30) {
					c.sendMessage("You don't have enough tokkul.");
					break;
				}
				if(Slot3 == -1 && c.myShopId == 353) {
					c.sendMessage("You don't have enough donator gold.");
					break;
				}
			
                if(TotPrice2 <= 1) {
                	TotPrice2 = (int)Math.floor(getItemShopValue(itemID, 0, fromSlot));
                	TotPrice2 *= 1.66;
                }
                else if(c.myShopId == 54) {                    
                        if (c.votingPoints >= TotPrice2) {
                        if (c.getItems().freeSlots() > 0) {
                            buyDelay = System.currentTimeMillis();
                            c.votingPoints -= TotPrice2;
                            c.getItems().addItem(itemID, 1);
                            ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
                            ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
                            if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
                                ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
                            }
                        } else {
                            c.sendMessage("You don't have enough space in your inventory.");
                            break;
                        }
                    } else {
                        c.sendMessage("You don't have enough points!");
                        break;
                    }

                }
                if(c.myShopId == 29 || c.myShopId == 30) {
                	if (c.playerItemsN[Slot1] >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.getItems().deleteItem(6529, c.getItems().getItemSlot(6529), TotPrice2);
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough tokkul.");
						break;
					}
                }
				else if(c.myShopId == 16) {
                	if (c.Wheel >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.Wheel -= TotPrice2;
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough Agility Points.");
						break;
					}
                }
								else if(c.myShopId == 100) {
                	if (c.barbPoints >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.barbPoints -= TotPrice2;
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough Points.");
						break;
					}
                }
				else if(c.myShopId == 84) {
                	if (c.dungPoints >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.dungPoints -= TotPrice2;
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough Dungeoneering Tokens.");
						break;
					}
                }
				else if(c.myShopId == 85) {
                	if (c.dungPoints >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.dungPoints -= TotPrice2;
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough Dungeoneering Tokens.");
						break;
					}
                }
                else if(c.myShopId == 11) {
                	if (c.playerItemsN[Slot3] >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.getItems().deleteItem(5555, c.getItems().getItemSlot(5555), TotPrice2);
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough donator gold.");
						break;
					}
                }
                else if(c.myShopId != 11 && c.myShopId != 29 || c.myShopId != 30 || c.myShopId != 31 || c.myShopId != 84 || c.myShopId != 85 || c.myShopId != 16) {
					if (c.playerItemsN[Slot] >= TotPrice2) {
						if (c.getItems().freeSlots() > 0) {
							buyDelay = System.currentTimeMillis();
							c.getItems().deleteItem(995, c.getItems().getItemSlot(995), TotPrice2);
							c.getItems().addItem(itemID, 1);
							ShopHandler.ShopItemsN[c.myShopId][fromSlot] -= 1;
							ShopHandler.ShopItemsDelay[c.myShopId][fromSlot] = 0;
							if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[c.myShopId]) {
								ShopHandler.ShopItems[c.myShopId][fromSlot] = 0;
							}
						} else {
							c.sendMessage("You don't have enough space in your inventory.");
							break;
						}
					} else {
						c.sendMessage("You don't have enough coins.");
						break;
					}
                }
			}
			c.getItems().resetItems(3823);
			resetShop(c.myShopId);
			updatePlayerShop();
			return true;
		}
		return false;
	}	
	
	public void handleOtherShop(int itemID) {
		if (c.myShopId == 20) {
			if (c.pcPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0){
					c.pcPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID,1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough 2xG Points to buy this item.");			
			}
		} else if (c.myShopId == 18) {
			if (c.pcPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0){
					c.pcPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID,1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough 2xG Points to buy this item.");			
				}
                    } else if (c.myShopId == 54) {
			if (c.votingPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0){
					c.votingPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID,1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Vote Points to buy this item.");			
				}
			} else if (c.myShopId == 85) {
			if (c.dungPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0){
					c.dungPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID,1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Dungeoneering Tokens.");			
			}
			} else if (c.myShopId == 84) {
			if (c.dungPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0){
					c.dungPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID,1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Dungeoneering Tokens to buy this item.");			
			}
		} else if (c.myShopId == 16) {
			if (c.Wheel >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0){
					c.Wheel -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID,1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough Agility Points to buy this item.");			
			}

		
					} else if (c.myShopId == 100) {
			if (c.barbPoints >= getSpecialItemValue(itemID)) {
				if (c.getItems().freeSlots() > 0){
					c.barbPoints -= getSpecialItemValue(itemID);
					c.getItems().addItem(itemID,1);
					c.getItems().resetItems(3823);
				}
			} else {
				c.sendMessage("You do not have enough points to buy this item.");			
			}

		}
	}
		
	public void openSkillCape() {
		int capes = get99Count();
		if (capes > 1)
			capes = 1;
		else
			capes = 0;
		c.myShopId = 15;
		setupSkillCapes(capes, get99Count());		
	}
		
	public int[] skillCapes = {9747,9753,9750,9768,9756,9759,9762,9801,9807,9783,9798,9804,9780,9795,9792,9774,9771,9777,9786,9810,9765};
	
	public int get99Count() {
		int count = 0;
		for (int j = 0; j < skillCapes.length; j++) {
			if (c.getLevelForXP(c.playerXP[j]) >= 99) {
				count++;				
			}			
		}		
		return count;
	}
	
	public void setupSkillCapes(int capes, int capes2) {
		synchronized(c) {
			c.getItems().resetItems(3823);
			c.isShopping = true;
			c.myShopId = 14;
			c.getPA().sendFrame248(3824, 3822);
			c.getPA().sendFrame126("Skillcape Shop", 3901);
			
			int TotalItems = 0;
			TotalItems = capes2;
			if (TotalItems > ShopHandler.MaxShopItems) {
				TotalItems = ShopHandler.MaxShopItems;
			}
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(3900);
			c.getOutStream().writeWord(TotalItems);
//			int TotalCount = 0;	// Said it wasn't used 4/14/17
			int off = (capes > 1 ? 2 : 0);
			for (int i = 0; i < skillCapes.length; i++) {
				if (c.getLevelForXP(c.playerXP[i]) < 99)
					continue;
				c.getOutStream().writeByte(1);
				c.getOutStream().writeWordBigEndianA(skillCapes[i] + off); // why the +2? uhh this is just from the original nrpk? i think
//				TotalCount++;	// Said it wasn't used 4/14/17
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();	
		}
	}
	
	public void skillBuy(int item) {
		int nn = get99Count();
		if (nn > 1)
			nn = 1;
		else
			nn = 0;			
		for (int j = 0; j < skillCapes.length; j++) {
			if (skillCapes[j] == item || skillCapes[j]+1 == item) {
				if (c.getItems().freeSlots() > 1) {
					if (c.getItems().playerHasItem(995,99000)) {
						if (c.getLevelForXP(c.playerXP[j]) >= 99) {
							c.getItems().deleteItem(995, c.getItems().getItemSlot(995), 99000);
							c.getItems().addItem(skillCapes[j] + nn,1);
							c.getItems().addItem(skillCapes[j] + 2,1);
						} else {
							c.sendMessage("You must have 99 in the skill of the cape you're trying to buy.");
						}
					} else {
						c.sendMessage("You need 99k to buy this item.");
					}
				} else {
					c.sendMessage("You must have at least 1 inventory spaces to buy this item.");					
				}				
			}
			/*if (skillCapes[j][1 + nn] == item) {
				if (c.getItems().freeSlots() >= 1) {
					if (c.getItems().playerHasItem(995,99000)) {
						if (c.getLevelForXP(c.playerXP[j]) >= 99) {
							c.getItems().deleteItem(995, c.getItems().getItemSlot(995), 99000);
							c.getItems().addItem(skillCapes[j] + nn,1);
							c.getItems().addItem(skillCapes[j] + 2,1);
						} else {
							c.sendMessage("You must have 99 in the skill of the cape you're trying to buy.");
						}
					} else {
						c.sendMessage("You need 99k to buy this item.");
					}
				} else {
					c.sendMessage("You must have at least 1 inventory spaces to buy this item.");					
				}
				break;				
			}*/			
		}
		c.getItems().resetItems(3823);			
	}
	
	public void openVoid() {
		/*synchronized(c) {
			c.getItems().resetItems(3823);
			c.isShopping = true;
			c.myShopId = 15;
			c.getPA().sendFrame248(3824, 3822);
			c.getPA().sendFrame126("Void Recovery", 3901);
			
			int TotalItems = 5;
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(3900);
			c.getOutStream().writeWord(TotalItems);
			for (int i = 0; i < c.voidStatus.length; i++) {
				c.getOutStream().writeByte(c.voidStatus[i]);
				c.getOutStream().writeWordBigEndianA(2519 + i * 2);
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();	
		}*/		
	}

	public void buyVoid(int item) {
		/*if (item > 2527 || item < 2518)
			return;
		//c.sendMessage("" + item);
		if (c.voidStatus[(item-2518)/2] > 0) {
			if (c.getItems().freeSlots() >= 1) {
				if (c.getItems().playerHasItem(995,c.getItems().getUntradePrice(item))) {
					c.voidStatus[(item-2518)/2]--;
					c.getItems().deleteItem(995,c.getItems().getItemSlot(995), c.getItems().getUntradePrice(item));
					c.getItems().addItem(item,1);
					openVoid();
				} else {
					c.sendMessage("This item costs " + c.getItems().getUntradePrice(item) + " coins to rebuy.");				
				}
			} else {
				c.sendMessage("I should have a free inventory space.");
			}
		} else {
			c.sendMessage("I don't need to recover this item from the void knights.");
		}*/
	}


}