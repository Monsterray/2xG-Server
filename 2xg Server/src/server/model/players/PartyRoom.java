package server.model.players;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import server.Config;
import server.Server;
import server.model.items.Item;
import server.model.objects.Objects;

public class PartyRoom {
	static Random r = new Random();
	static int[] roomItems = new int[50];
	static int[] roomItemsN = new int[50];
	static long lastAnnouncment;
	static int announcmentFrequency = 1; //announcment frequency in mins
	static ArrayList<Point> coords = new ArrayList<Point>();

	public static int getAmount(){
		int amount = 0;
		for(int x = 0; x < roomItems.length; x++){
			if(roomItems[x] > 0){
				amount++;
			}
		}
		return amount;
	}

	public static void startTimer(Client c){
		if (System.currentTimeMillis() - lastAnnouncment > (1000 * 60 * announcmentFrequency)) {
			dropAll();
			lastAnnouncment = System.currentTimeMillis();
		c.sendMessage("Count Down starts at "+ lastAnnouncment +".");
		}
	}
	
	public static void dropAll(){
		int trys = 0;
		int amount = getAmount();
		if(amount < 1){
			return;
		}
		for(int x = 0; x < roomItems.length; x++){
			if(roomItemsN[x] > 0){
				Balloon b = null;
				do{
				b = Balloon.getBalloon(roomItems[x], roomItemsN[x]);
				trys++;
				} while(coords.contains(b.getCoords()) && trys < 100);
				Server.objectHandler.addObject(b);
				Server.objectHandler.placeObject(b);
			}
			if(trys > 100){
				break;
			}
			roomItems[x] = 0;
			roomItemsN[x] = 0;
		}
		trys = 0;
		for(int x = 0; x < amount*2; x++){
			Objects o;
			do{
			o = Balloon.getEmpty();
			} while(coords.contains(new Point(o.objectX, o.objectY)) && trys < 100);
			if(trys > 100){
				break;
			}
				Server.objectHandler.addObject(o);
					Server.objectHandler.placeObject(o);
		}
		coords.clear();
	}

	public static int arraySlot(int[] array, int target){
		int spare = -1;
		for(int x = 0; x < array.length; x++){
			if(array[x] == target){
				return x;
			} else if(spare == -1 && array[x] <= 0){
				spare = x;
			}
		}
		return spare;
	}

	public static void open(Client c){
		updateGlobal(c);
			updateDeposit(c);
				c.getItems().resetItems(5064);
			c.getPA().sendFrame248(2156, 5063);
		}

	public static void accept(Client c){
		for(int x = 0; x < c.party.length; x++){
			if(c.partyN[x] > 0){
				if(Item.itemStackable[c.party[x]]){
					int slot = arraySlot(roomItems, c.party[x]);
				if(slot < 0){
					c.sendMessage("Theres not enought space on party room.");
					break;
				}
				if(roomItems[slot] != c.party[x]){
					roomItems[slot] = c.party[x];
						roomItemsN[slot] = c.partyN[x];
				} else {
					roomItemsN[slot] += c.partyN[x];
				}
				c.party[x] = -1;
					c.partyN[x] = 0;
				} else {
					int left = c.partyN[x];
					for(int y = 0; y < left; y++){
						int slot = arraySlot(roomItems, -2);
						if(slot < 0){
							c.sendMessage("There's not enought space on party room.");
							break;
						}
						c.sendMessage(""+slot);
						roomItems[slot] = c.party[x];
						roomItemsN[slot] = 1;
						c.partyN[x]--;
					}
					if(c.partyN[x] <= 0)
						c.party[x] = -1;
				}
			}
		}
		updateDeposit(c);
		updateGlobal(c);
	}

	public static void updateAll(){
		for(int x = 0; x < Server.playerHandler.players.length; x++){
			updateGlobal((Client)Server.playerHandler.players[x]);
		}
	}

	public static void fix(Client c){
		for(int x = 0; x < 8; x++){
			if(c.party[x] < 0){
				c.partyN[x] = 0;
			} else if(c.partyN[x] <= 0){
				c.party[x] = 0;
			}
		}
	}

	public static void depositItem(Client c, int id, int amount){
		int slot = arraySlot(c.party, id);
			for (int i : Config.ITEM_TRADEABLE)  {
				if(i == id) {
					c.sendMessage("You can't sell this item.");
					return;
				}		
			}
		if(c.getItems().getItemAmount(id) < amount){
			amount = c.getItems().getItemAmount(id);
		}
		if(!c.getItems().playerHasItem(id, amount)){
			c.sendMessage("You don't have that many items!");
			return;
		}
		if(slot == -1){
			c.sendMessage("You can't deposit more than 8 items at once.");
			return;
		}
		c.getItems().deleteItem2(id, amount);
		if(c.party[slot] != id){
			c.party[slot] = id;
			c.partyN[slot] = amount;
		}else{
			c.party[slot] = id;
			c.partyN[slot] += amount;
		}
		updateDeposit(c);
	}

	public static void withdrawItem(Client c, int slot){
		if(c.party[slot] >= 0 && c.getItems().freeSlots() > 0){
			c.getItems().addItem(c.party[slot], c.partyN[slot]);
			c.party[slot] = 0;
			c.partyN[slot] = 0;
		}
		updateDeposit(c);
		updateGlobal(c);
	}

	public static void updateDeposit(Client c) {
		c.getItems().resetItems(5064);
       		for(int x = 0; x < 8; x++){
			if(c.partyN[x] <= 0)
				itemOnInterface(c, 2274, x, -1, 0);
			else
				itemOnInterface(c, 2274, x, c.party[x], c.partyN[x]);
		}
	}

	public static void updateGlobal(Client c) {
       		for(int x = 0; x < roomItems.length; x++){
			if(roomItemsN[x] <= 0)
				itemOnInterface(c, 2273, x, -1, 0);
			else
				itemOnInterface(c, 2273, x, roomItems[x], roomItemsN[x]);
		}
	}

	public static void itemOnInterface(Client c, int frame, int slot, int id, int amount){
		c.outStream.createFrameVarSizeWord(34);
		c.outStream.writeWord(frame);
		c.outStream.writeByte(slot);
		c.outStream.writeWord(id+1);
		c.outStream.writeByte(255);
		c.outStream.writeDWord(amount);
		c.outStream.endFrameVarSizeWord();
	}

 //2273	items on chest
 //2274 personal
}