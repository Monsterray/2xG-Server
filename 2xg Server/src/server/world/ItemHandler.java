package server.world;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import server.Config;
import server.Server;
import server.model.items.GroundItem;
import server.model.items.ItemList;
import server.model.players.Client;
import server.model.players.Player;
import server.util.Misc;

/**
* Handles ground items
**/

public class ItemHandler {

	public List<GroundItem> items = new ArrayList<GroundItem>();
	public static final int HIDE_TICKS = 100;
	
	
	public ItemHandler() {			
		for(int i = 0; i < Config.ITEM_LIMIT; i++) {
			ItemList[i] = null;
		}
		loadItemList("item.cfg");
		loadItemPrices("prices.txt");
	}
	public void reloadAllItems() {			
		for(int i = 0; i < Config.ITEM_LIMIT; i++) {
			ItemList[i] = null;
		}
		loadItemList("item.cfg");
		loadItemPrices("prices.txt");
	}
	
	/**
	* Adds item to list
	**/	
	public void addItem(GroundItem item) {
		items.add(item);
	}
	
	/**
	* Removes item from list
	**/	
	public void removeItem(GroundItem item) {
		items.remove(item);
	}
	
	/**
	* Item amount
	**/	
	public int itemAmount(int itemId, int itemX, int itemY) {
		for(GroundItem i : items) {
			if(i.getItemId() == itemId && i.getItemX() == itemX && i.getItemY() == itemY) {
				return i.getItemAmount();
			}
		}
		return 0;
	}
	
	
	/**
	* Item exists
	**/	
	public boolean itemExists(int itemId, int itemX, int itemY) {
		for(GroundItem i : items) {
			if(i.getItemId() == itemId && i.getItemX() == itemX && i.getItemY() == itemY) {
				return true;
			}
		}
		return false;
	}
	
	/**
	* Reloads any items if you enter a new region
	**/
	public void reloadItems(Client c) {
		for(GroundItem i : items) {
			if(c != null){
				if (c.getItems().tradeable(i.getItemId()) || i.getName().equalsIgnoreCase(c.playerName)) {
					if (c.distanceToPoint(i.getItemX(), i.getItemY()) <= 60) {
						if(i.hideTicks > 0 && i.getName().equalsIgnoreCase(c.playerName)) {
							c.getItems().removeGroundItem(i.getItemId(), i.getItemX(), i.getItemY(), i.getItemAmount());
							c.getItems().createGroundItem(i.getItemId(), i.getItemX(), i.getItemY(), i.getItemAmount());
						}
						if(i.hideTicks == 0) {
							c.getItems().removeGroundItem(i.getItemId(), i.getItemX(), i.getItemY(), i.getItemAmount());
							c.getItems().createGroundItem(i.getItemId(), i.getItemX(), i.getItemY(), i.getItemAmount());
						}
					}
				}	
			}
		}
	}
	
	public void process() {
		ArrayList<GroundItem> toRemove = new ArrayList<GroundItem>();
		for (int j = 0; j < items.size(); j++) {			
			if (items.get(j) != null) {
				GroundItem i = items.get(j);
				if(i.hideTicks > 0) {
					i.hideTicks--;
				}
				if(i.hideTicks == 1) { // item can now be seen by others
					i.hideTicks = 0;
					createGlobalItem(i);
					i.removeTicks = HIDE_TICKS;
				}
				if(i.removeTicks > 0) {
					i.removeTicks--;
				}
				if(i.removeTicks == 1) {
					i.removeTicks = 0;
					toRemove.add(i);
					//removeGlobalItem(i, i.getItemId(), i.getItemX(), i.getItemY(), i.getItemAmount());
				}
			
			}
		
		}
		
		for (int j = 0; j < toRemove.size(); j++) {
			GroundItem i = toRemove.get(j);
			removeGlobalItem(i, i.getItemId(), i.getItemX(), i.getItemY(), i.getItemAmount());	
		}
		/*for(GroundItem i : items) {
			if(i.hideTicks > 0) {
				i.hideTicks--;
			}
			if(i.hideTicks == 1) { // item can now be seen by others
				i.hideTicks = 0;
				createGlobalItem(i);
				i.removeTicks = HIDE_TICKS;
			}
			if(i.removeTicks > 0) {
				i.removeTicks--;
			}
			if(i.removeTicks == 1) {
				i.removeTicks = 0;
				removeGlobalItem(i, i.getItemId(), i.getItemX(), i.getItemY(), i.getItemAmount());
			}
		}*/
	}
	
	
	
	/**
	* Creates the ground item 
	**/
	public int[][] brokenBarrows = {{4708,4860},{4710,4866},{4712,4872},{4714,4878},{4716,4884},
	{4720,4896},{4718,4890},{4720,4896},{4722,4902},{4732,4932},{4734,4938},{4736,4944},{4738,4950},
	{4724,4908},{4726,4914},{4728,4920},{4730,4926},{4745,4956},{4747,4926},{4749,4968},{4751,4994},
	{4753,4980},{4755,4986},{4757,4992},{4759,4998}};
//	private static int[][] xEP = {{14882,1},{14881,1},{14877,1},{14876,1},{892,12},{1099,1},{1165,1},{1351,1},{1319,1},{1333,1},{1359,1},{1149,1},{1185,1},{1704,1},{157,3},{145,2},{175,4},{995,14700},{995,22500},{6731,1},{6733,1},{4712,1},{4714,1},{4716,1},{4718,1},{4720,1},{4722,1},{4736,1},{4738,1},{4749,1},{4751,1},{892,100},{4675,1},{4091,1},{4093,1},{4095,1},{4097,1},{4101,1},{4103,1},{4105,1},{892,100},{1768,1},{892,12},{892,12},{4107,1},{4111,1},{4113,1},{4115,1},{4117,1},{4131,1},{1079,1},{14877,1},{1127,1},
//		{14883,1},{14884,1},{14885,1},{14886,1},{14887,1},{892,12},{14888,1},{14890,1},{14891,1},{14892,1},{4716,1},{4734,1},{1319,1},{1333,1},{1359,1},{995,1000000},{892,100},{995,5815},{995,10000000},{1085,1},{1089,1},{1351,1},{14885,1},{14886,1},{14887,1},{14888,1},{14889,1},{14890,1},{14891,1}};	// Said it wasn't used 4/14/17
	public void createGroundItem(Client c, int itemId, int itemX, int itemY, int itemAmount, int playerId) {
//		Client o = (Client) Server.playerHandler.players[c.killerId];	// Said it wasn't used 4/14/17
//		Client pl = (Client) Server.playerHandler.players[c.killerId];	// Said it wasn't used 4/14/17
		if(itemId > 0) {
			if (itemId >= 2412 && itemId <= 2414) {
				c.sendMessage("The cape vanishes as it touches the ground.");
				return;
			}
			if (itemId > 4705 && itemId < 4760) {
				for (int j = 0; j < brokenBarrows.length; j++) {
					if (brokenBarrows[j][0] == itemId) {
						itemId = brokenBarrows[j][1];
						break;
					}
				}
			}
			if (!server.model.items.Item.itemStackable[itemId] && itemAmount > 0) {
				for (int j = 0; j < itemAmount; j++) {
					c.getItems().createGroundItem(itemId, itemX, itemY, 1);
					GroundItem item = new GroundItem(itemId, itemX, itemY, 1, c.playerId, HIDE_TICKS, Server.playerHandler.players[playerId].playerName);
					addItem(item);
				}	
			} else {
				c.getItems().createGroundItem(itemId, itemX, itemY, itemAmount);
				GroundItem item = new GroundItem(itemId, itemX, itemY, itemAmount, c.playerId, HIDE_TICKS, Server.playerHandler.players[playerId].playerName);
				addItem(item);
			}
		}
	}
	
	
	/**
	* Shows items for everyone who is within 60 squares
	**/
	public void createGlobalItem(GroundItem i) {
		for (Player p : Server.playerHandler.players){
			if(p != null) {
			Client person = (Client)p;
				if(person != null){
					if(person.playerId != i.getItemController()) {
						if (!person.getItems().tradeable(i.getItemId()) && person.playerId != i.getItemController())
							continue;
						if (person.distanceToPoint(i.getItemX(), i.getItemY()) <= 60) {
							person.getItems().createGroundItem(i.getItemId(), i.getItemX(), i.getItemY(), i.getItemAmount());
						}
					}
				}
			}
		}
	}
			

			
	/**
	* Removing the ground item
	**/
	
	public void removeGroundItem(Client c, int itemId, int itemX, int itemY, boolean add){
		for(GroundItem i : items) {
			if(i.getItemId() == itemId && i.getItemX() == itemX && i.getItemY() == itemY) {
				if(i.hideTicks > 0 && i.getName().equalsIgnoreCase(c.playerName)) {
					if(add) {
						if (!c.getItems().specialCase(itemId)) {
							if(c.getItems().addItem(i.getItemId(), i.getItemAmount())) {   
								removeControllersItem(i, c, i.getItemId(), i.getItemX(), i.getItemY(), i.getItemAmount());
								break;
							}
						} else {
							c.getItems().handleSpecialPickup(itemId);
							removeControllersItem(i, c, i.getItemId(), i.getItemX(), i.getItemY(), i.getItemAmount());
							break;
						}
					} else {
						removeControllersItem(i, c, i.getItemId(), i.getItemX(), i.getItemY(), i.getItemAmount());
						break;
					}
				} else if (i.hideTicks <= 0) {
					if(add) {
						if(c.getItems().addItem(i.getItemId(), i.getItemAmount())) {  
							removeGlobalItem(i, i.getItemId(), i.getItemX(), i.getItemY(), i.getItemAmount());
							break;
						}
					} else {
						removeGlobalItem(i, i.getItemId(), i.getItemX(), i.getItemY(), i.getItemAmount());
						break;
					}
				}
			}
		}
	}
	
	/**
	* Remove item for just the item controller (item not global yet)
	**/
	
	public void removeControllersItem(GroundItem i, Client c, int itemId, int itemX, int itemY, int itemAmount) {
		c.getItems().removeGroundItem(itemId, itemX, itemY, itemAmount);
		removeItem(i);
	}
	
	/**
	* Remove item for everyone within 60 squares
	**/
	
	public void removeGlobalItem(GroundItem i, int itemId, int itemX, int itemY, int itemAmount) {
		for (Player p : Server.playerHandler.players){
			if(p != null) {
			Client person = (Client)p;
				if(person != null){
					if (person.distanceToPoint(itemX, itemY) <= 60) {
						person.getItems().removeGroundItem(itemId, itemX, itemY, itemAmount);
					}
				}
			}
		}
		removeItem(i);
	}
		
	

	/**
	*Item List
	**/
	
	public ItemList ItemList[] = new ItemList[Config.ITEM_LIMIT];
	

	public void newItemList(int ItemId, String ItemName, String ItemDescription, double ShopValue, double LowAlch, double HighAlch, int Bonuses[]) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 0; i < 20082; i++) {
			if (ItemList[i] == null) {
				slot = i;
				break;
			}
		}

		if(slot == -1) return;		// no free slot found
		ItemList newItemList = new ItemList(ItemId);
		newItemList.itemName = ItemName;
		newItemList.itemDescription = ItemDescription;
		newItemList.ShopValue = ShopValue;
		newItemList.LowAlch = LowAlch;
		newItemList.HighAlch = HighAlch;
		newItemList.Bonuses = Bonuses;
		ItemList[slot] = newItemList;
	}
	
	public void loadItemPrices(String filename) {	//TODO deps stuff
		try {
			Scanner s = new Scanner(new File("./deps/Data/cfg/" + filename));
			while (s.hasNextLine()) {
				String[] line = s.nextLine().split(" ");
				ItemList temp = getItemList(Integer.parseInt(line[0]));
				if (temp != null)
					temp.ShopValue = Integer.parseInt(line[1]);			
			}
			s.close();
		} catch (IOException e) {
			e.printStackTrace();		
		}
	}
	
	public ItemList getItemList(int i) {
		for (int j = 0; j < ItemList.length; j++) {
			if (ItemList[j] != null) {
				if (ItemList[j].itemId == i) {
					return ItemList[j];				
				}		
			}		
		}
		return null;
	}
	
	public boolean loadItemList(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
//		int ReadMode = 0;	// Said it wasn't used 4/14/17
		BufferedReader characterfile = null;
		try {	//TODO deps stuff
			characterfile = new BufferedReader(new FileReader("./deps/Data/cfg/"+FileName));
		} catch(FileNotFoundException fileex) {
			Misc.println(FileName+": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			Misc.println(FileName+": error loading file.");
			try {
				characterfile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("item")) {
					int[] Bonuses = new int[12];
					for (int i = 0; i < 12; i++) {
						if (token3[(6 + i)] != null) {
							Bonuses[i] = Integer.parseInt(token3[(6 + i)]);
						} else {
							break;
						}
					}
					newItemList(Integer.parseInt(token3[0]), token3[1].replaceAll("_", " "), token3[2].replaceAll("_", " "), Double.parseDouble(token3[4]), Double.parseDouble(token3[4]), Double.parseDouble(token3[6]), Bonuses);
				}
			} else {
				if (line.equals("[ENDOFITEMLIST]")) {
					try {
						characterfile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { 
			characterfile.close(); 
		} catch(IOException ioexception) { 
			
		}
		return false;
	}
}
