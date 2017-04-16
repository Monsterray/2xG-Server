package server.model.players.packets;

import server.Config;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;

/**
 * Drop Item
 **/
public class DropItem implements PacketType {

	public void dat(Client c) {
		int itemId = c.getInStream().readUnsignedWordA();
		c.getInStream().readUnsignedByte();
		c.getInStream().readUnsignedByte();
		int slot = c.getInStream().readUnsignedWordA();
		c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
		c.SaveGame();
	}

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int itemId = c.getInStream().readUnsignedWordA();
		c.getInStream().readUnsignedByte();
		c.getInStream().readUnsignedByte();
		int slot = c.getInStream().readUnsignedWordA();
		if(itemId == 1560) {
			if (!c.hasNpc) {
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getPA().followPlayer();
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.hasNpc = true;
				//c.summonId = 2;
			} else
				return;
		}
		if(itemId == 1559) {
			if (!c.hasNpc) {
				c.sendMessage("You drop your Kitten");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				c.hasNpc = true;
				//c.summonId = 3;
			} else
				return;
		}
		if(itemId == 1558) {
			if (!c.hasNpc) {
				c.sendMessage("You drop your Kitten");
				//c.summonId = 4;
				c.hasNpc = true;
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
			} else
				return;
		}
		if(itemId == 1557) {
			if (!c.hasNpc) {
				c.hasNpc = true;
				c.sendMessage("You drop your Kitten");
				//c.summonId = 5;
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
			} else
				return;
		}
		if(itemId == 1556) {
			if (!c.hasNpc) {
				c.hasNpc = true;
				c.sendMessage("You drop your Kitten");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				//c.summonId = 6;
			} else
				return;
		}
		if(itemId == 1555) {
			if (!c.hasNpc) {
				c.hasNpc = true;
				c.sendMessage("You drop your Kitten");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				//c.summonId = 7;
			} else
				return;
		}
		if(itemId == 1561) {
			if (!c.hasNpc) {
				c.hasNpc = true;
				c.sendMessage("You drop your Cat");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				//c.summonId = 8;
			} else
				return;
		}
		if(itemId == 1562) {
			if (!c.hasNpc) {
				c.hasNpc = true;
				c.sendMessage("You drop your Cat");
				//c.summonId = 9;
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
			} else
				return;
		}
		if(itemId == 1563 && !c.hasNpc) {
			if (!c.hasNpc) {
				c.hasNpc = true;
				c.sendMessage("You drop your Cat");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				//c.summonId = 10;
			} else
				return;
		}
		if(itemId == 1564) {
			if (!c.hasNpc) {
				c.hasNpc = true;
				c.sendMessage("You drop your Cat");
				//c.summonId = 11;
					Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
			} else
				return;
		}
		if(itemId == 1565) {
			if (!c.hasNpc) {
				c.hasNpc = true;
				c.sendMessage("You drop your Cat");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				//c.summonId = 12;
			} else
					return;
		}
		if(itemId == 7583) {
			if (!c.hasNpc) {
				c.hasNpc = true;
				c.sendMessage("You drop your Hell Kitten");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				//c.summonId = 12;
			} else
					return;
		}
		if(itemId == 1566) {
			if (!c.hasNpc) {
					c.hasNpc = true;
					c.sendMessage("You drop your Cat");
							Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
					c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
					c.getPA().followPlayer();
					//c.summonId = 13;
				} else
					return;
		}
		if(itemId == 7585) {
			if (!c.hasNpc) {
				c.hasNpc = true;
				c.sendMessage("You drop your Hellcat");
				//c.summonId = 14;
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
			} else
				return;
		}
		if(itemId == 7584) {
			if (!c.hasNpc) {
				c.hasNpc = true;
				c.sendMessage("You drop your Lazy Hellcat");
				//c.summonId = 15;
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
			} else 
				return;
		}
		if(itemId == 12007) {
			if (!c.hasNpc) {
				c.hasNpc = true;
				c.sendMessage("You drop your Spirit Terrorbird");
				//c.summonId = 16;
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
			} else 
				return;
		}
		if(itemId == 12470) {
			if (!c.hasNpc) {
				c.sendMessage("You drop your Dragon");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				c.hasNpc = true;
				//c.summonId = 17;
			} else
				return;
		}
		if(itemId == 12472) {
			if (!c.hasNpc) {
				c.sendMessage("You drop your Dragon");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				c.hasNpc = true;
				//c.summonId = 18;
			} else
				return;
		}
		if(itemId == 12474) {
			if (!c.hasNpc) {
				c.sendMessage("You drop your Dragon");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				c.hasNpc = true;
				//c.summonId = 19;
			} else
				return;
		}
		if(itemId == 12476) {
			if (!c.hasNpc) {
				c.sendMessage("You drop your Dragon");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				c.hasNpc = true;
				//c.summonId = 20;
			} else
				return;
		}
		if(itemId == 12513) {
			if (!c.hasNpc) {
				c.sendMessage("You drop your Dog");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				c.hasNpc = true;
				//c.summonId = 21;
			} else
				return;
		}
		if(itemId == 12515) {
			if (!c.hasNpc) {
				c.sendMessage("You drop your Dog");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				c.hasNpc = true;
				//c.summonId = 22;
			} else
				return;
		}
		if(itemId == 12517) {
			if (!c.hasNpc) {
				c.sendMessage("You drop your Dog");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				c.hasNpc = true;
				//c.summonId = 23;
			} else
				return;
		}
		if(itemId == 12519) {
			if (!c.hasNpc) {
				c.sendMessage("You drop your Dog");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				c.hasNpc = true;
				//c.summonId = 24;
			} else
				return;
		}
		if(itemId == 12521) {
			if (!c.hasNpc) {
				c.sendMessage("You drop your Dog");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				c.hasNpc = true;
				//c.summonId = 25;
			} else
				return;
		}
		if(itemId == 12523) {
			if (!c.hasNpc) {
				c.sendMessage("You drop your Dog");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				c.hasNpc = true;
				//c.summonId = 26;
			} else
				return;
		}
		if(itemId == 12482) {
			if (!c.hasNpc) {
				c.sendMessage("You drop your Penguin");
				Server.npcHandler.spawnNpc3(c, Server.npcHandler.summonItemId(itemId), c.absX, c.absY-1, c.heightLevel, 0, 120, 25, 200, 200, true, false, true);
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
				c.getPA().followPlayer();
				c.hasNpc = true;
				//c.summonId = 27;
			} else
				return;
		}
		if(c.arenas()) {
			c.sendMessage("You can't drop items inside the arena!");
			return;
		}
		if (c.inTrade) {
			c.sendMessage("You cannot drop items in the trade screen.");
			return;
		}
		if(c.InDung()) {
			c.sendMessage("You can't drop inside the dungeoneering floors!");
			return;
		}
		if(c.tradeStatus == 1) {
			c.getTradeAndDuel().declineTrade();
			c.sendMessage("AntiDupe: You tried to drop an item, duel closed.");
			return;
		}
		if(c.duelStatus == 1) {
			Client o = (Client) Server.playerHandler.players[c.duelingWith];
			c.getTradeAndDuel().declineDuel();
			o.getTradeAndDuel().declineDuel();
			o.sendMessage("Duel closed - other player dropped an item.");
			c.sendMessage("AntiDupe: You tried to drop an item, duel closed.");
			return;
		}
		if (c.isShopping) {
			c.sendMessage("You cannot drop items while shopping! - Walk then try.");
			return;
		}
		if(c.InDung == false && c.IsIDung == 1) {
			c.getPA().resetDung();
		}
		if (c.underAttackBy > 0) {
			c.sendMessage("AntiDupe: Cannot drop item, errorcode: You can't drop items in combat.");
			return;
		}
		if(c.playerItemsN[slot] != 0 && itemId != -1 && c.playerItems[slot] == itemId + 1) {
			if(!c.getItems().playerHasItem(itemId,1,slot)) {
				c.sendMessage("ANTI DUPE: STOPPED!");
				return;
			}
		}
		if(c.playerRights == 2) {
			c.getItems().deleteItem(itemId, slot, 1);
			c.sendMessage("Since your admin, your item dissapears as you drop it.");
			return;
		}
		if(c.playerRights == 5) {
			c.getItems().deleteItem(itemId, slot, 1);
			c.sendMessage("Since your Owner, your item dissapears as you drop it.");
			return;
		}
		
		c.getPA().closeAllWindows();
		boolean droppable = true;
		for (int i : Config.CAT_ITEMS) {
			if (i == itemId) {
				if(c.hasNpc == true) {
					c.sendMessage("Sorry, You Already Have A Npc Playing Dismiss to use.");
					droppable = false;
					break;
				}
			}
		}
		for (int i : Config.UNDROPPABLE_ITEMS) {
			if (i == itemId) {
				c.sendMessage("<shad=15695415>Failed to drop, errorcode: Undropable item!");
				droppable = false;
				break;
			}
		}
		for (int i : Config.ITEM_SELLABLE) {
			if (i == itemId) {
				droppable = false;
				c.sendMessage("<shad=15695415>Failed to drop, errorcode: Untsellable item!");
				break;
			}
		}
		if(c.inRFD()){
			c.sendMessage("<shad=15695415>You may not drop in the RFD Minigame.");
			return;	
		}
		if(c.playerItemsN[slot] != 0 && itemId != -1 && c.playerItems[slot] == itemId + 1) {
			if(droppable) {
				if (c.underAttackBy > 0) {
					if (c.getShops().getItemShopValue(itemId) > 10000) {
						c.sendMessage("You may not drop items worth more than 10,000gp while in combat.");
						return;
					}
				}
				Server.itemHandler.createGroundItem(c, itemId, c.getX(), c.getY(), c.playerItemsN[slot], c.getId());
				c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
			} else {
				c.sendMessage("This item cannot be dropped.");
			}
		}
		if(c.playerItemsN[slot] != 0 && itemId != -1 && c.playerItems[slot] == itemId + 1) {
			if(!c.getItems().playerHasItem(itemId,1,slot)) {
				c.sendMessage("Stop cheating!");
				return;
			}
		}
	}
}