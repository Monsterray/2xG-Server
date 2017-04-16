package server.model.players;

import java.net.InetSocketAddress;

import server.Config;
import server.Server;
import server.model.npcs.NPCHandler;
import server.model.players.Content.LoginMessages;
import server.model.players.Content.RequestHelp;
import server.util.Misc;
import server.util.Stream;

public class PlayerHandler {
//Monsterrays Variables
	public static long realTimeToRefresh = 0;

	private Stream updateBlock = new Stream(new byte[Config.BUFFER_SIZE]);
	public static Player players[] = new Player[Config.MAX_PLAYERS];
	public static String messageToAll = "";
	public static boolean updateAnnounced;
	public static boolean updateRunning;
	public static int updateSeconds;
	public static long updateStartTime;
	private boolean kickAllPlayers = false;
	public static PlayerSave save;
	
	static {
		Runtime.getRuntime().addShutdownHook(new Thread());
//		PlayerSave playerSave;	// Said it wasn't used 4/14/17
//		Client cliento1;	// Said it wasn't used 4/14/17
		for(int i = 0; i < Config.MAX_PLAYERS; i++)
		if(players[i] != null)
			((Client)players[i]).save.saveGame(Client.cliento2);
	}
    
    public static void messageAllStaff(String text, boolean message) {
	    for (Player p : PlayerHandler.players) {
		    Client o = (Client) p;
		    if (o != null) {
			    if (o.playerRights == 1 || o.playerRights == 2 || o.playerRights == 3) {
				    if (message) {
					    o.sendMessage(text);
				    } else {
					    o.sendMessage("Alert##Ticket Center##" + text + "##Help");
				    }
			    }
		    }
	    }
    }

    public static Client getPlayerByName(String playerName) {
	    for(int i = 0; i < Config.MAX_PLAYERS; i++) {
		    if(players[i] != null){
			    if(players[i].playerName.equalsIgnoreCase(playerName)) {
				    return (Client) players[i];
			    }
		    }
	    }
	    return null;
    }

    public boolean newPlayerClient(Client client1){
    	int slot = -1;
    	for(int i = 1; i < Config.MAX_PLAYERS; i++) {
    		if(players[i] == null || players[i].disconnected) {
    			slot = i;
    			break;
    		}
    	}
    	if(slot == -1)
            return false;
        client1.handler = this;
        client1.playerId = slot;
        players[slot] = client1;
        players[slot].isActive = true;
        players[slot].connectedFrom = ((InetSocketAddress) client1.getSession().getRemoteAddress()).getAddress().getHostAddress();
        if(Config.SERVER_DEBUG)	
        	Misc.println("Player Slot "+slot+" slot 0 "+players[0]+" Player Hit "+players[slot]);//does nothing.... ;;players dont give the right amount of players? i am getting the right amount why not me? logout log inand u wont get the right
        return true;
    }

	public void destruct() {
		for(int i = 0; i < Config.MAX_PLAYERS; i++) {
			if(players[i] == null) 
				continue;
			players[i].destruct();
			players[i] = null;
		}
	}

	public void tickTask3() {
		if(updateRunning && !updateAnnounced) {
			updateAnnounced = true;
			Server.UpdateServer = true;
		}
		if(updateRunning && System.currentTimeMillis() - updateStartTime > (long)(updateSeconds * 1000)) {
			this.kickAllPlayers = true;
			if(Config.DEBUG)
				Server.pln("Got to first update time");
		}
		for(int i = 0; i < 1024; ++i) {
			if(players[i] != null && players[i].isActive) {
				try {
					players[i].clearUpdateFlags();
				} catch (Exception var3) {
					var3.printStackTrace();
				}
			}
		}
	}
   
	public void tickTask2() {
		for(int i = 0; i < 1024; ++i) {
			if(players[i] != null && players[i].isActive) {
				try {
					Client e;
					if(players[i].disconnected && (System.currentTimeMillis() - players[i].logoutDelay > 10000L || players[i].properLogout || this.kickAllPlayers)) {
						if(players[i].inTrade) {
							e = (Client)players[players[i].tradeWith];
							if(e != null) {
								e.getTradeAndDuel().declineTrade();
							}
						}
						if(players[i].duelStatus > 4) {
							e = (Client)players[players[i].duelingWith];
							if(e != null) {
								e.getTradeAndDuel().duelVictory();
							}
						} else if(players[i].duelStatus <= 4 && players[i].duelStatus >= 1) {
							e = (Client)players[players[i].duelingWith];
							if(e != null) {
								e.getTradeAndDuel().declineDuel();
							}
						}
						e = (Client)players[i];
						if(!PlayerSave.saveGame(e)) {
							System.out.println("Could not save for " + players[i].playerName);
						}
						this.removePlayer(players[i]);
						players[i] = null;
					} else {
						e = (Client)players[i];
						if(!players[i].initialized) {
							players[i].initialize();
						}
					}
				} catch (Exception var3) {
					var3.printStackTrace();
				}
			}
		}
	}
   
	public void tickTask() {
		int i;
		if(this.kickAllPlayers) {
			for(i = 1; i < 1024; ++i) {
				if(players[i] != null) {
					players[i].disconnected = true;
				}
			}
		}
		for(i = 0; i < 1024; ++i) {
			if(players[i] != null && players[i].isActive) {
				try {
					if(players[i].disconnected && (System.currentTimeMillis() - players[i].logoutDelay > 10000L || players[i].properLogout || this.kickAllPlayers)) {
						Client e;
						if(players[i].inTrade) {
							e = (Client)players[players[i].tradeWith];
							if(e != null) {
								e.getTradeAndDuel().declineTrade();
							}
						}
						if(players[i].duelStatus > 4) {
							e = (Client)players[players[i].duelingWith];
							if(e != null) {
								e.getTradeAndDuel().duelVictory();
							}
						} else if(players[i].duelStatus <= 4 && players[i].duelStatus >= 1) {
							e = (Client)players[players[i].duelingWith];
							if(e != null) {
								e.getTradeAndDuel().declineDuel();
								((Client)players[i]).getTradeAndDuel().declineDuel();
							}
						}
						e = (Client)players[i];
						if(!PlayerSave.saveGame(e)) {
							System.out.println("Could not save for " + players[i].playerName);
						}
						this.removePlayer(players[i]);
						players[i] = null;
					} else {
						players[i].preProcessing();
						while(players[i].processQueuedPackets()) {
							;
						}
						players[i].process();
					}
				} catch (Exception var3) {
					var3.printStackTrace();
				}
			}
		}
      //PathFindingWorker.calculatePaths();
	}
	
	public static int getPlayerCount() {
		int count = 0;
		for(int i = 0; i < players.length; i++) {
			if(players[i] != null) {
				count += 1;
			}
		}
		return count;
	}
	
    public static int getPlayerID(String playerName) {
		for(int i = 0; i < Config.MAX_PLAYERS; i++) {
            if (players[i] != null) {
                if (players[i].playerName.equalsIgnoreCase(playerName))
                    return i;
			}
        }
        return -1;
    }
	
	public static boolean isPlayerOn(String playerName) {
		//synchronized (PlayerHandler.players) {
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(players[i] != null){
					if(players[i].playerName.equalsIgnoreCase(playerName)) {
						return true;
					}
				}
			}
			return false;
		// }
	}
	
	public void process() {
		//synchronized (PlayerHandler.players) {
			long start = System.currentTimeMillis();
			if(kickAllPlayers) {
				for(int i = 1; i < Config.MAX_PLAYERS; i++) {
					if(players[i] != null) {
						players[i].disconnected = true;
					}
				}
			}
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(players[i] == null || !players[i].isActive) continue;
				try {
					if(players[i].disconnected && (System.currentTimeMillis() - players[i].logoutDelay > 10000 || players[i].properLogout || kickAllPlayers)) {
						if(players[i].inTrade) {
							Client o = (Client) Server.playerHandler.players[players[i].tradeWith];
							if(o != null) {
								o.getTradeAndDuel().declineTrade();
							}
						}
						if(players[i].duelStatus == 5) {
							Client o = (Client) Server.playerHandler.players[players[i].duelingWith];
							if(o != null) {
								o.getTradeAndDuel().duelVictory();
							}
						} else if (players[i].duelStatus <= 4 && players[i].duelStatus >= 1) {
							Client o = (Client) Server.playerHandler.players[players[i].duelingWith];
							if(o != null) {
								o.getTradeAndDuel().declineDuel();
							}
						}
						Client o = (Client) Server.playerHandler.players[i];
						if(PlayerSave.saveGame(o)) { 
							System.out.println("Game saved for player "+players[i].playerName); 
						} else { 
							System.out.println("Could not save for "+players[i].playerName); 
						}
						removePlayer(players[i]);
						players[i] = null;
						continue;
					}
					players[i].preProcessing();			
					while(players[i].processQueuedPackets());
					players[i].processPackets = 0;
					players[i].process();
					players[i].postProcessing();
					players[i].getNextPlayerMovement();
				} catch(Exception e) {
					e.printStackTrace();
					Server.pln("Problem in playerhandler.process()");
				}
			}
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(players[i] == null || !players[i].isActive) continue;
				try {
					if(players[i].disconnected && (System.currentTimeMillis() - players[i].logoutDelay > 10000 || players[i].properLogout || kickAllPlayers)) {
						if(players[i].inTrade) {
							Client o = (Client) Server.playerHandler.players[players[i].tradeWith];
							if(o != null) {
								o.getTradeAndDuel().declineTrade();
							}
						}
						if(players[i].duelStatus == 5) {
							Client o1 = (Client) Server.playerHandler.players[players[i].duelingWith];
							if(o1 != null) {
								o1.getTradeAndDuel().duelVictory();
							}
						} else if (players[i].duelStatus <= 4 && players[i].duelStatus >= 1) {
							Client o1 = (Client) Server.playerHandler.players[players[i].duelingWith];
							if(o1 != null) {
								o1.getTradeAndDuel().declineDuel();
							}
						}
						
						Client o1 = (Client) Server.playerHandler.players[i];
						if(PlayerSave.saveGame(o1)){ 
							System.out.println("Game saved for player "+players[i].playerName); 
						} else { 
							System.out.println("Could not save for "+players[i].playerName); 
						}
						removePlayer(players[i]);
						players[i] = null;
					} else {
						Client o = (Client) Server.playerHandler.players[i];
						//if(o.g) {
							if(!players[i].initialized) {
								players[i].initialize();
								players[i].initialized = true;
							}
							else {
								players[i].update();
							}
						// }
					}
				} catch(Exception e) {
					e.printStackTrace();
					Server.pln("Problem in playerhandler.process()");
				}
			}
			if(updateRunning && !updateAnnounced) {
				updateAnnounced = true;
				Server.UpdateServer = true;
			}
			if(updateRunning && (System.currentTimeMillis() - updateStartTime > (updateSeconds*1000))) {
				kickAllPlayers  = true;
				if(Config.DEBUG)
					Server.pln("Got to second update time");
				Server.shutdownServer = true;
			}
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				if(players[i] == null || !players[i].isActive) continue;
				try {
					players[i].clearUpdateFlags();
				} catch(Exception e) {
					e.printStackTrace();
				}	
			}
			for(Player lol : PlayerHandler.players) {
				if(lol == null)
					continue;
				if(((Client)lol).logoutRefreshIn == 0){
				}else{
					realTimeToRefresh = ((Client)lol).logoutRefreshIn;
				}
			}
			if(realTimeToRefresh == 0){
			}else{
				if(System.currentTimeMillis() - realTimeToRefresh > 5000){
					System.out.println("oh fuck oh fuck oh fuck oh fuck oh fuck oh fuck");
					for(Player p : PlayerHandler.players) {
						if(p == null)
							continue;						
						RequestHelp.sendOnlineStaff((Client)p);
						LoginMessages.tabLogout((Client)p);
					}
					realTimeToRefresh = 0;
				}
			}
			Server.TIMES[2] = start - System.currentTimeMillis();
		// }
	}
	
	public void updateNPC(Player plr, Stream str) {
		//synchronized(plr) {
			updateBlock.currentOffset = 0;
			str.createFrameVarSizeWord(65);
			str.initBitAccess();
			str.writeBits(8, plr.npcListSize);
			int size = plr.npcListSize;
			plr.npcListSize = 0;
			for(int i = 0; i < size; i++) {
				if(plr.RebuildNPCList == false && plr.withinDistance(plr.npcList[i]) == true) {
					plr.npcList[i].updateNPCMovement(str);
					plr.npcList[i].appendNPCUpdateBlock(updateBlock);
					plr.npcList[plr.npcListSize++] = plr.npcList[i];
				} else {
					int id = plr.npcList[i].npcId;
					plr.npcInListBitmap[id>>3] &= ~(1 << (id&7));		
					str.writeBits(1, 1);
					str.writeBits(2, 3);		
				}
			}
			for(int i = 0; i < NPCHandler.maxNPCs; i++) {
				if(NPCHandler.npcs[i] != null) {
					int id = NPCHandler.npcs[i].npcId;
					if (plr.RebuildNPCList == false && (plr.npcInListBitmap[id>>3]&(1 << (id&7))) != 0) {
						
					} else if (plr.withinDistance(NPCHandler.npcs[i]) == false) {
						
					} else {
						plr.addNewNPC(NPCHandler.npcs[i], str, updateBlock);
					}
				}
			}
			plr.RebuildNPCList = false;
			if(updateBlock.currentOffset > 0) {
				str.writeBits(14, 16383);	
				str.finishBitAccess();
				str.writeBytes(updateBlock.buffer, updateBlock.currentOffset, 0);
			} else {
				str.finishBitAccess();
			}
			str.endFrameVarSizeWord();
		// }
	}

	public void updatePlayer(Player plr, Stream str) {
		//synchronized(plr) {
			updateBlock.currentOffset = 0;
			if(updateRunning && !updateAnnounced) {
				str.createFrame(114);
				str.writeWordBigEndian(updateSeconds*50/30);
				if(Config.DEBUG)
					Server.pln("Got to third update time");
			}
			plr.updateThisPlayerMovement(str);		
			boolean saveChatTextUpdate = plr.isChatTextUpdateRequired();
			plr.setChatTextUpdateRequired(false);
			plr.appendPlayerUpdateBlock(updateBlock);
			plr.setChatTextUpdateRequired(saveChatTextUpdate);
			str.writeBits(8, plr.playerListSize);
			int size = plr.playerListSize;
			plr.playerListSize = 0;	
			for(int i = 0; i < size; i++) {			
				if(!plr.didTeleport && !plr.playerList[i].didTeleport && plr.withinDistance(plr.playerList[i])) {
					plr.playerList[i].updatePlayerMovement(str);
					plr.playerList[i].appendPlayerUpdateBlock(updateBlock);
					plr.playerList[plr.playerListSize++] = plr.playerList[i];
				} else {
					int id = plr.playerList[i].playerId;
					plr.playerInListBitmap[id>>3] &= ~(1 << (id&7));
					str.writeBits(1, 1);
					str.writeBits(2, 3);
				}
			}
			int j = 0;
			for(int i = 0; i < Config.MAX_PLAYERS; i++) {
				//if(updateBlock.currentOffset >= 4000)
				//break;
				if(plr.playerListSize >= 254) 
					break;
				if(updateBlock.currentOffset+str.currentOffset >= 4900)
					break;
				if(players[i] == null || !players[i].isActive || players[i] == plr) 
					continue;
				int id = players[i].playerId;
				if((plr.playerInListBitmap[id>>3]&(1 << (id&7))) != 0)
					continue;
				if(j >= 10) 
					break;	
				if(!plr.withinDistance(players[i])) 
					continue;		
				plr.addNewPlayer(players[i], str, updateBlock);//FIXED BY GABBE
				j++;
			}
			if(updateBlock.currentOffset > 0) {
				str.writeBits(11, 2047);	
				str.finishBitAccess();
				str.writeBytes(updateBlock.buffer, updateBlock.currentOffset, 0);
			}
			else 
				str.finishBitAccess();
			str.endFrameVarSizeWord();
	}

	private void removePlayer(Player plr) {
		if(plr.privateChat != 2) { 
			for(int i = 1; i < Config.MAX_PLAYERS; i++) {
				if (players[i] == null || players[i].isActive == false) continue;
				Client o = (Client)Server.playerHandler.players[i];
				if(o != null) {
					o.getPA().updatePM(plr.playerId, 0);
				}
			}
		}
		plr.saveCharacter = true;
		plr.destruct();
	}
}