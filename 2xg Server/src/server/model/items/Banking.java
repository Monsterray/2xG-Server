package server.model.items;

import server.Config;
import server.model.players.Client;
import server.model.players.skills.Summoning;

public class Banking {

	private Client c;
	
	public Banking(Client c) {
		this.c = c;
	}
	
	public void resetTempItems(){
		synchronized(c) {
			int itemCount = 0;
			for (int i = 0; i < c.playerItems.length; i++) {
				if (c.playerItems[i] > -1) {
					itemCount=i;
				}
			}
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(5064);
			c.getOutStream().writeWord(itemCount+1); 
			for (int i = 0; i < itemCount+1; i++) {
				if (c.playerItemsN[i] > 254) {
					c.getOutStream().writeByte(255); 						
					c.getOutStream().writeDWord_v2(c.playerItemsN[i]);
				} else {
					c.getOutStream().writeByte(c.playerItemsN[i]);
				}
				if (c.playerItems[i] > Config.ITEM_LIMIT || c.playerItems[i] < 0) {
					c.playerItems[i] = Config.ITEM_LIMIT;
				}
				c.getOutStream().writeWordBigEndianA(c.playerItems[i]); 
			}
			c.getOutStream().endFrameVarSizeWord();	
			c.flushOutStream();
		}
	}
	
	
	public boolean bankItem(int itemID, int fromSlot, int amount){
			if(c.InDung){
			c.sendMessage("You can't bank in dungeoneering fucking idiot");
			return false;
		}
		if(c.inTrade) {
				c.sendMessage("You can't bank items while trading!");
				return false;
			}
				if(c.inWild()){
			c.sendMessage("You can't bank in Wildy fucking idiot");
			return false;
		}
		
                
		if (c.playerItems[fromSlot]-1 != itemID)
			return false;
		if (c.playerItemsN[fromSlot] <= 0){
			return false;
		}
		if (c.inWild() && c.hasFollower != 3594) {
			return false;
		}
                
                if(c.storing) {
                    Summoning.storeItem(c,itemID,fromSlot,amount);
                    return false;
                }
               
		if (!Item.itemIsNote[c.playerItems[fromSlot]-1]) {
			if (c.playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[c.playerItems[fromSlot]-1] || c.playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i< Config.BANK_SIZE; i++) {
						if (c.bankItems[i] == c.playerItems[fromSlot]) {
							if (c.playerItemsN[fromSlot]<amount)
									amount = c.playerItemsN[fromSlot];
							alreadyInBank = true;
							toBankSlot = i;
							i=Config.BANK_SIZE+1;
						}
				}

				if (!alreadyInBank && freeBankSlots() > 0) {
						for (int i=0; i<Config.BANK_SIZE; i++) {
							if (c.bankItems[i] <= 0) {
									toBankSlot = i;
									i=Config.BANK_SIZE+1;
							}
						}
						c.bankItems[toBankSlot] = c.playerItems[fromSlot];
						if (c.playerItemsN[fromSlot]<amount){
							amount = c.playerItemsN[fromSlot];
						}
						if ((c.bankItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT && (c.bankItemsN[toBankSlot] + amount) > -1) {
							c.bankItemsN[toBankSlot] += amount;
						} else {
							c.sendMessage("Bank full!");
							return false;
						}
						c.getItems().deleteItem((c.playerItems[fromSlot]-1), fromSlot, amount);
						resetTempItems();
						resetBank();
						return true;
				}
				else if (alreadyInBank) {
						if ((c.bankItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT && (c.bankItemsN[toBankSlot] + amount) > -1) {
							c.bankItemsN[toBankSlot] += amount;
						} else {
							c.sendMessage("Bank full!");
							return false;
						}
						c.getItems().deleteItem((c.playerItems[fromSlot]-1), fromSlot, amount);
						resetTempItems();
						resetBank();
						return true;
				} else {
						c.sendMessage("Bank full!");
						return false;
				}
			} else {
				itemID = c.playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i<Config.BANK_SIZE; i++) {
						if (c.bankItems[i] == c.playerItems[fromSlot]) {
							alreadyInBank = true;
							toBankSlot = i;
							i=Config.BANK_SIZE+1;
						}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
			       	for (int i=0; i<Config.BANK_SIZE; i++) {
						if (c.bankItems[i] <= 0) {
								toBankSlot = i;
								i=Config.BANK_SIZE+1;
						}
					}
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0) {
							itemExists = false;
							for (int i=firstPossibleSlot; i<c.playerItems.length; i++) {
									if ((c.playerItems[i]) == itemID) {
										firstPossibleSlot = i;
										itemExists = true;
										i=30;
									}
							}
							if (itemExists) {
									c.bankItems[toBankSlot] = c.playerItems[firstPossibleSlot];
									c.bankItemsN[toBankSlot] += 1;
									c.getItems().deleteItem((c.playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							} else {
									amount=0;
							}
						}
						resetTempItems();
						resetBank();
						return true;
				} else if (alreadyInBank) {
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0) {
							itemExists = false;
							for (int i=firstPossibleSlot; i<c.playerItems.length; i++) {
								if ((c.playerItems[i]) == itemID) {
									firstPossibleSlot = i;
									itemExists = true;
									i=30;
								}
							}
							if (itemExists) {
									c.bankItemsN[toBankSlot] += 1;
									c.getItems().deleteItem((c.playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							} else {
									amount=0;
							}
						}
						resetTempItems();
						resetBank();
						return true;
				} else {
						c.sendMessage("Bank full!");
						return false;
				}
			}
		}
		else if (Item.itemIsNote[c.playerItems[fromSlot]-1] && !Item.itemIsNote[c.playerItems[fromSlot]-2]) {
			if (c.playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[c.playerItems[fromSlot]-1] || c.playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i<Config.BANK_SIZE; i++) {
						if (c.bankItems[i] == (c.playerItems[fromSlot]-1)) {
							if (c.playerItemsN[fromSlot]<amount)
									amount = c.playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i=Config.BANK_SIZE+1;
						}
				}

				if (!alreadyInBank && freeBankSlots() > 0) {
			       	for (int i=0; i<Config.BANK_SIZE; i++) {
						if (c.bankItems[i] <= 0) {
								toBankSlot = i;
								i=Config.BANK_SIZE+1;
						}
					}
					c.bankItems[toBankSlot] = (c.playerItems[fromSlot]-1);
					if (c.playerItemsN[fromSlot]<amount){
						amount = c.playerItemsN[fromSlot];
					}
					if ((c.bankItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT && (c.bankItemsN[toBankSlot] + amount) > -1) {
						c.bankItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					c.getItems().deleteItem((c.playerItems[fromSlot]-1), fromSlot, amount);
					resetTempItems();
					resetBank();
					return true;
				}
				else if (alreadyInBank) {
					if ((c.bankItemsN[toBankSlot] + amount) <= Config.MAXITEM_AMOUNT && (c.bankItemsN[toBankSlot] + amount) > -1) {
						c.bankItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					c.getItems().deleteItem((c.playerItems[fromSlot]-1), fromSlot, amount);
					resetTempItems();
					resetBank();
					return true;
				} else {
						c.sendMessage("Bank full!");
						return false;
				}
			} else {
				itemID = c.playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i<Config.BANK_SIZE; i++) {
					if (c.bankItems[i] == (c.playerItems[fromSlot]-1)) {
						alreadyInBank = true;
						toBankSlot = i;
						i=Config.BANK_SIZE+1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0) {
			       	for (int i=0; i<Config.BANK_SIZE; i++) {
						if (c.bankItems[i] <= 0){
								toBankSlot = i;
								i=Config.BANK_SIZE+1;
						}
					}
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0) {
							itemExists = false;
							for (int i=firstPossibleSlot; i<c.playerItems.length; i++) {
								if ((c.playerItems[i]) == itemID) {
									firstPossibleSlot = i;
									itemExists = true;
									i=30;
								}
							}
							if (itemExists) {
									c.bankItems[toBankSlot] = (c.playerItems[firstPossibleSlot]-1);
									c.bankItemsN[toBankSlot] += 1;
									c.getItems().deleteItem((c.playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							} else {
									amount=0;
							}
						}
						resetTempItems();
						resetBank();
						return true;
				}
				else if (alreadyInBank) {
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0) {
							itemExists = false;
							for (int i=firstPossibleSlot; i<c.playerItems.length; i++) {
								if ((c.playerItems[i]) == itemID) {
									firstPossibleSlot = i;
									itemExists = true;
									i=30;
								}
							}
							if (itemExists) {
									c.bankItemsN[toBankSlot] += 1;
									c.getItems().deleteItem((c.playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							} else {
									amount=0;
							}
						}
						resetTempItems();
						resetBank();
						return true;
				} else {
						c.sendMessage("Bank full!");
						return false;
				}
			}
		} else {
			c.sendMessage("Item not supported "+(c.playerItems[fromSlot]-1));
			return false;
		}
	}
	
	
	public int freeBankSlots(){
		int freeS=0;
        for (int i=0; i < Config.BANK_SIZE; i++) {
			if (c.bankItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}
	
	
	public void fromBank(int itemID, int fromSlot, int amount) {
if (!c.isBanking) {
               c.getPA().closeAllWindows();
              return;
          }
		if (c.inWild()) {
			return;
		}
		if (amount > 0) {
		  if (c.bankItems[fromSlot] > 0) {
			if (!c.takeAsNote) {
			  if (Item.itemStackable[c.bankItems[fromSlot]-1]) {
				if (c.bankItemsN[fromSlot] > amount) {
				  if (c.getItems().addItem((c.bankItems[fromSlot]-1), amount)) {
					c.bankItemsN[fromSlot] -= amount;
					resetBank();
					c.getItems().resetItems(5064);
				  }
				} else {
				  if (c.getItems().addItem((c.bankItems[fromSlot]-1), c.bankItemsN[fromSlot])) {
					c.bankItems[fromSlot] = 0;
					c.bankItemsN[fromSlot] = 0;
					resetBank();
					c.getItems().resetItems(5064);
				  }
				}
			  } else {
				while (amount > 0) {
				  if (c.bankItemsN[fromSlot] > 0) {
					if (c.getItems().addItem((c.bankItems[fromSlot]-1), 1)) {
					  c.bankItemsN[fromSlot] += -1;
					  amount--;
					} else {
					  amount = 0;
					}
				  } else {
					amount = 0;
				  }
				}
				resetBank();
				c.getItems().resetItems(5064);
			  }
			} else if (c.takeAsNote && Item.itemIsNote[c.bankItems[fromSlot]]) {
				if (c.bankItemsN[fromSlot] > amount) {
					if (c.getItems().addItem(c.bankItems[fromSlot], amount)) {
						c.bankItemsN[fromSlot] -= amount;
						resetBank();
						c.getItems().resetItems(5064);
					}
				} else {
					if (c.getItems().addItem(c.bankItems[fromSlot], c.bankItemsN[fromSlot])) {
						c.bankItems[fromSlot] = 0;
						c.bankItemsN[fromSlot] = 0;
						resetBank();
						c.getItems().resetItems(5064);
					}
				}
			} else {
			  c.sendMessage("This item can't be withdrawn as a note.");
			  if (Item.itemStackable[c.bankItems[fromSlot]-1]) {
				if (c.bankItemsN[fromSlot] > amount) {
				  if (c.getItems().addItem((c.bankItems[fromSlot]-1), amount)) {
					c.bankItemsN[fromSlot] -= amount;
					resetBank();
					c.getItems().resetItems(5064);
				  }
				} else {
				  if (c.getItems().addItem((c.bankItems[fromSlot]-1), c.bankItemsN[fromSlot])) {
					c.bankItems[fromSlot] = 0;
					c.bankItemsN[fromSlot] = 0;
					resetBank();
					c.getItems().resetItems(5064);
				  }
				}
			  } else {
				while (amount > 0) {
				  if (c.bankItemsN[fromSlot] > 0) {
					if (c.getItems().addItem((c.bankItems[fromSlot]-1), 1)) {
					  c.bankItemsN[fromSlot] += -1;
					  amount--;
					} else {
					  amount = 0;
					}
				  } else {
					amount = 0;
				  }
				}
				resetBank();
				c.getItems().resetItems(5064);
			  }
			}
		  }
		}
	}
	
	
	
	public void rearrangeBank() {
		int totalItems = 0;
		int highestSlot = 0;
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (c.bankItems[i] != 0) { 
				totalItems ++;
				if (highestSlot <= i) {	
					highestSlot = i;
				}
			}  
		}
		
		for (int i = 0; i <= highestSlot; i++) {
			if (c.bankItems[i] == 0) {
				boolean stop = false;
			
			for (int k = i; k <= highestSlot; k++) {
				if (c.bankItems[k] != 0 && !stop) {
					int spots = k - i;
						for (int j = k; j <= highestSlot; j++) {
							c.bankItems[j-spots] = c.bankItems[j];
							c.bankItemsN[j-spots] = c.bankItemsN[j];
							stop = true;
							c.bankItems[j] = 0; c.bankItemsN[j] = 0; 
						}
					}
				}					
			}
		}
		
		int totalItemsAfter = 0;
		for (int i = 0; i < Config.BANK_SIZE; i++) {
			if (c.bankItems[i] != 0) { 
			totalItemsAfter ++; 
			} 
		}
			
		if (totalItems != totalItemsAfter) 
			c.disconnected = true;
	}
	
	public void resetBank(){
		synchronized(c) {
			c.getOutStream().createFrameVarSizeWord(53);
			c.getOutStream().writeWord(5382); // bank
			c.getOutStream().writeWord(Config.BANK_SIZE);
			for (int i=0; i<Config.BANK_SIZE; i++){
				if (c.bankItemsN[i] > 254){
					c.getOutStream().writeByte(255);
					c.getOutStream().writeDWord_v2(c.bankItemsN[i]);
				} else {
					c.getOutStream().writeByte(c.bankItemsN[i]); 	
				}
				if (c.bankItemsN[i] < 1) {
					c.bankItems[i] = 0;
				}
				if (c.bankItems[i] > Config.ITEM_LIMIT || c.bankItems[i] < 0) {
					c.bankItems[i] = Config.ITEM_LIMIT;
				}
				c.getOutStream().writeWordBigEndianA(c.bankItems[i]); 
			}
			c.getOutStream().endFrameVarSizeWord();
			c.flushOutStream();
		}
	}
	
}
