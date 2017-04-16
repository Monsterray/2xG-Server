package server.model.players;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import server.Server;
import server.util.Misc;

public class PlayerSave{
	/**
	*Loading
	**/
	public static int loadGame(Client p, String playerName, String playerPass) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		boolean File1 = false;
		
		try {
			characterfile = new BufferedReader(new FileReader("./deps/data/characters/"+playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		
		if (File1) {
			//new File ("./deps/data/characters/"+playerName+".txt");
		} else {
			Misc.println(playerName+": character file not found.");
			p.newPlayer = false;
			return 0;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			Misc.println(playerName+": error loading file.");
			return 3;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
					case 1:
						 if (token.equals("character-password")) {
							if (playerPass.equalsIgnoreCase(token2) || Misc.basicEncrypt(playerPass).equals(token2)) {
								playerPass = token2;
							} else {
								return 3;
							}
						}
						break;
					case 2:
						if (token.equals("character-height")) {
							p.heightLevel = Integer.parseInt(token2);
						} else if (token.equals("character-posx")) {
							p.teleportToX = (Integer.parseInt(token2) <= 0 ? 3210 : Integer.parseInt(token2));
						} else if (token.equals("character-posy")) {
							p.teleportToY = (Integer.parseInt(token2) <= 0 ? 3424 : Integer.parseInt(token2));
						} else if (token.equals("character-rights")) {
							p.playerRights = Integer.parseInt(token2);
						} else if (line.startsWith("Email")) {
							p.Email = token2;
						} else if (token.equals("summonTime")) {
							p.summonTime = Integer.parseInt(token2);
						} else if (token.equals("character-title")) {
							p.playerTitle = Integer.parseInt(token2);
						} else if (token.equals("loyalty-Points")) {
							p.loyaltypoints = Integer.parseInt(token2);
						} else if (token.equals("dungPoints")) {
							p.dungPoints = Integer.parseInt(token2);
						} else if (token.equals("Wheel")) {
							p.Wheel = Integer.parseInt(token2);
						} else if (token.equals("spinsLe")) {
							p.spinsLe = Integer.parseInt(token2);
						} else if (token.equals("AuraEquiped")) {
							p.AuraEquiped = Integer.parseInt(token2);
						} else if (token.equals("votingPoints")) {
							p.votingPoints = Integer.parseInt(token2);
						} else if (token.equals("agilityPoints")) {
							p.agilitypoints = Integer.parseInt(token2);
						} else if (token.equals("skull-timer")) {
							p.skullTimer = Integer.parseInt(token2);
						} else if (token.equals("MoneyOrb")) {
							p.MoneyCash = Integer.parseInt(token2);
						} else if (token.equals("LoyaltyPoints")) {
							p.LoyaltyPoints = Integer.parseInt(token2);			
						} else if (token.equals("LoyaltyScore")) {
							p.LoyaltyScore = Integer.parseInt(token2);	
						} else if (token.equals("cw-games")) {
							p.cwGames = Integer.parseInt(token2);
						} else if (token.equals("summonId")) {
							p.summonId = Integer.parseInt(token2);
						} else if (token.equals("has-npc")) {
							p.hasNpc = Boolean.parseBoolean(token2);
						} else if(token.equals("tempid")) {
							p.tempid = Integer.parseInt(token2);
						} else if(token.equals("tempamt")) {
							p.tempamt = Integer.parseInt(token2);
						}  else if(token.equals("tempprice")) {
							p.tempprice = Integer.parseInt(token2);
						} else if (token.equals("EP")) {
							p.earningPotential = Integer.parseInt(token2);
						} else if (token.equals("bankPin1")) {
							p.bankPin1 = Integer.parseInt(token2);
						} else if (token.equals("bankPin2")) {
							p.bankPin2 = Integer.parseInt(token2);
						} else if (token.equals("bankPin3")) {
							p.bankPin3 = Integer.parseInt(token2);
						} else if (token.equals("bankPin4")) {
							p.bankPin4 = Integer.parseInt(token2);
						} else if (token.equals("hasBankPin")) {
							p.hasBankPin = Boolean.parseBoolean(token2);
						} else if (token.equals("VL")) {
							p.vlsLeft2 = Integer.parseInt(token2);
						} else if (token.equals("AGP")) {
							p.spoints = Integer.parseInt(token2);
						} else if (token.equals("kingQuest")) {
							p.kingQuest = Integer.parseInt(token2);
						} else if (token.equals("character-longsword")) {
							p.vlsLeft = Integer.parseInt(token2);
						} else if (token.equals("character-warhammer")) {
							p.statLeft = Integer.parseInt(token2);
						} else if (token.equals("character-spear")) {
							p.vSpearLeft = Integer.parseInt(token2);
						} else if (token.equals("character-chainbody")) {
							p.vTopLeft = Integer.parseInt(token2);
						} else if (token.equals("shopcollect")) {
							p.playerCollect = Integer.parseInt(token2);
						} else if (token.equals("santaPrize")) {
							p.santaPrize = Integer.parseInt(token2);
						} else if (token.equals("character-chainskirt")) {
							p.vLegsLeft = Integer.parseInt(token2);
						} else if (token.equals("character-full helm")) {
							p.sHelmLeft = Integer.parseInt(token2);
						} else if (token.equals("character-platebody")) {
							p.sTopLeft = Integer.parseInt(token2);
						} else if (token.equals("character-platelegs")) {
							p.sLegsLeft = Integer.parseInt(token2);
						} else if (token.equals("character-hood")) {
							p.zHoodLeft = Integer.parseInt(token2);
						} else if (token.equals("character-staff")) {
							p.zStaffLeft = Integer.parseInt(token2);
						} else if (token.equals("character-robe top")) {
							p.zTopLeft = Integer.parseInt(token2);
						} else if (token.equals("character-robe bottom")) {
							p.zBottomLeft = Integer.parseInt(token2);
						} else if (token.equals("character-leather body")) {
							p.mBodyLeft = Integer.parseInt(token2);
						} else if (token.equals("character-chaps")) {
							p.mChapsLeft = Integer.parseInt(token2);
						} else if (token.equals("magic-book")) {
							p.playerMagicBook = Integer.parseInt(token2);
						} else if (token.equals("xpLock")) {
							p.xpLock = Boolean.parseBoolean(token2);
						} else if (token.equals("Jailed")) {
							p.Jail = Boolean.parseBoolean(token2);
						} else if (token.equals("hasChoosenDung")) {
							p.hasChoosenDung = Boolean.parseBoolean(token2);
						} else if (token.equals("Agrith")) {
							p.Agrith = Boolean.parseBoolean(token2);
						} else if (token.equals("degrade")) {
							p.degradeTime = Integer.parseInt(token2); 
						} else if (token.equals("Flambeed")) {
							p.Flambeed = Boolean.parseBoolean(token2);
						} else if (token.equals("Karamel")) {
							p.Karamel = Boolean.parseBoolean(token2);
						} else if (token.equals("Dessourt")) {
							p.Dessourt = Boolean.parseBoolean(token2);
						} else if (token.equals("culin")) {
							p.Culin = Boolean.parseBoolean(token2);	
						} else if (token.equals("Nomad")) {
							p.Nomad = Boolean.parseBoolean(token2);	
						} else if (token.equals("Goblin")) {
							p.Goblin = Boolean.parseBoolean(token2);
						} else if (token.equals("sir")) {
							p.sir = Boolean.parseBoolean(token2);						
						} else if (token.equals("vote")) {
							p.vote = Integer.parseInt(token2);						
						} else if (token.equals("brother-info")) {
							p.barrowsNpcs[Integer.parseInt(token3[0])][1] = Integer.parseInt(token3[1]);
						} else if (token.equals("special-amount")) {
							p.specAmount = Double.parseDouble(token2);	
						} else if (token.equals("selected-coffin")) {
							p.randomCoffin = Integer.parseInt(token2);	
						} else if (token.equals("barrows-killcount")) {
							p.pkPoints = Integer.parseInt(token2);							
						} else if (token.equals("teleblock-length")) {
							p.teleBlockDelay = System.currentTimeMillis();
							p.teleBlockLength = Integer.parseInt(token2);							
						} else if (token.equals("pc-points")) {
							p.pcPoints = Integer.parseInt(token2);	
						} else if (token.equals("gwdelay")) {
							p.gwdelay = Integer.parseInt(token2);
						} else if (token.equals("dungRest")) {
							p.dungRest = Integer.parseInt(token2);
						} else if (token.equals("Altar")) {
							p.altarPrayed = Integer.parseInt(token2);
						} else if (token.equals("Arma-KC")) {
							p.Arma = Integer.parseInt(token2);	
						} else if (token.equals("Band-KC")) {
							p.Band = Integer.parseInt(token2);	
						} else if (token.equals("Zammy-KC")) {
							p.Zammy = Integer.parseInt(token2);	
						} else if (token.equals("Sara-KC")) {
							p.Sara = Integer.parseInt(token2);	
						} else if (token.equals("isDonator")) {
							p.isDonator = Integer.parseInt(token2);
						} else if (token.equals("issDonator")) {
							p.issDonator = Integer.parseInt(token2);
						} else if (token.equals("donatorChest")) {
							p.donatorChest = Integer.parseInt(token2);
						} else if (token.equals("usedxplock")) {
							p.usedxplock = Boolean.parseBoolean(token2);						
						} else if (token.equals("slayerTask")) {
							p.slayerTask = Integer.parseInt(token2);					
						} else if (token.equals("taskAmount")) {
							p.taskAmount = Integer.parseInt(token2);					
						} else if (token.equals("magePoints")) {
							p.magePoints = Integer.parseInt(token2);
						} else if (line.startsWith("totalstored")) {
							p.totalstored = Integer.parseInt(token2);
						} else if (token.equals("autoRet")) {
							p.autoRet = Integer.parseInt(token2);
						} else if (token.equals("barbPoints")) {
							p.barbPoints = Integer.parseInt(token2);
						} else if (token.equals("trade11")) {
							p.trade11 = Integer.parseInt(token2);
						} else if (token.equals("SpeDelay")) {
							p.SpecialDelay = Integer.parseInt(token2);
						} else if (token.equals("barrowskillcount")) {
							p.barrowsKillCount = Integer.parseInt(token2);
						} else if (token.equals("isShopping")) {
							p.isShopping = Boolean.parseBoolean(token2);
						} else if (token.equals("spoints")) {
							p.spoints = Integer.parseInt(token2);
						} else if (token.equals("wave")) {
							p.waveId = Integer.parseInt(token2);
						} else if (token.equals("dfs-charges")) {
							p.dfsCount = Integer.parseInt(token2);
						} else if (token.equals("hasFollower")) {
							p.hasFollower = Integer.parseInt(token2);
						} else if (token.equals("summoningnpcid")) {
							p.summoningnpcid = Integer.parseInt(token2);
						} else if (token.equals("void")) {
							for (int j = 0; j < token3.length; j++) {
								p.voidStatus[j] = Integer.parseInt(token3[j]);						
							}
						} else if (token.equals("fightMode")) {
							p.fightMode = Integer.parseInt(token2);
						} else if (token.equals("COaltar")) {
							p.COaltar = Integer.parseInt(token2);
						} else if (token.equals("IsIDung")) {
							p.IsIDung = Integer.parseInt(token2);	
						} else if (token.equals("DUfood1")) {
							p.DUfood1 = Integer.parseInt(token2);
						} else if (token.equals("DUfood2")) {
							p.DUfood2 = Integer.parseInt(token2);
						} else if (token.equals("DUfood3")) {
							p.DUfood3 = Integer.parseInt(token2);
						} else if (token.equals("DUfood4")) {
							p.DUfood4 = Integer.parseInt(token2);
						} else if (token.equals("OWdungn")) {
							p.dungn = Integer.parseInt(token2);
						} else if (token.equals("COchest")) {
							p.COchest = Integer.parseInt(token2);
						} else if (token.equals("CObeddy")) {
							p.CObeddy = Integer.parseInt(token2);
						} else if (token.equals("COtreee")) {
							p.COtreee = Integer.parseInt(token2);
						} else if (token.equals("COLectt")) {
							p.COLectt = Integer.parseInt(token2);
						} else if (token.equals("COtelee")) {
							p.COtelee = Integer.parseInt(token2);
						} else if (token.equals("COcryst")) {
							p.COcryst = Integer.parseInt(token2);
						}
						break;
					case 3:
						if (token.equals("character-equip")) {
							p.playerEquipment[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							p.playerEquipmentN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 4:
						if (token.equals("character-look")) {
							p.playerAppearance[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						} 
						break;
					case 5:
						if (token.equals("character-skill")) {
							p.playerLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							p.playerXP[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 6:
						if (token.equals("character-item")) {
							p.playerItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							p.playerItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 7:
						if (token.equals("character-bank")) {
							p.bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							p.bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 8:
						 if (token.equals("character-friend")) {
							p.friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
						} 
						break;
						
					case 9:
					break;
					
					case 20:
						if (token.equals("stored")) {
							p.storeditems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						}
					break;

					case 21:
						 if (token.equals("occupy")) {
							p.occupied[Integer.parseInt(token3[0])] = Boolean.parseBoolean(token3[1]);
						} 
					break;
					
					case 10:
						if (token.equals("character-shop")) {
							p.playerShop[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							p.playerShopP[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
							p.playerShopN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[3]);
						} 
					break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {			ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {ReadMode = 3;
				} else if (line.equals("[LOOK]")) {		ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {	ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {	ReadMode = 6;
				} else if (line.equals("[BANK]")) {		ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {	ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {	ReadMode = 9;
				} else if (line.equals("[STORED]")) {	ReadMode = 20;
				} else if (line.equals("[OCCUPY]")) {	ReadMode = 21;
				} else if (line.equals("[SHOP]")) {		ReadMode = 10;
				} else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return 13;
	}
	
	
	/**
	*Saving
	**/
	public static boolean saveGame(Client p) {
		if(!p.saveFile || p.newPlayer || !p.saveCharacter) {
			//System.out.println("first");
			return false;
		}
		if(p.playerName == null || Server.playerHandler.players[p.playerId] == null) {
			//System.out.println("second");
			return false;
		}
		p.playerName = p.playerName2;
		int tbTime = (int)(p.teleBlockDelay - System.currentTimeMillis() + p.teleBlockLength);
		if(tbTime > 300000 || tbTime < 0){
			tbTime = 0;
		}
		
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./deps/Data/characters/"+p.playerName+".txt"));
			
		/*ACCOUNT*/
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(p.playerName, 0, p.playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(p.playerPass, 0, p.playerPass.length());
			characterfile.newLine();
			characterfile.newLine();
			
		/*CHARACTER*/
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(p.heightLevel), 0, Integer.toString(p.heightLevel).length());
			characterfile.newLine();

			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(p.absX), 0, Integer.toString(p.absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(p.absY), 0, Integer.toString(p.absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(p.playerRights), 0, Integer.toString(p.playerRights).length());
			characterfile.newLine();
			if(p.Email != null){
				characterfile.write("Email = ", 0, 8);
				characterfile.write(p.Email, 0, p.Email.length());
				characterfile.newLine();
			}
			characterfile.write("summonTime = ", 0, 13);
			characterfile.write(Integer.toString(p.summonTime), 0, Integer.toString(p.summonTime).length());
			characterfile.newLine();
			characterfile.write("hasHousee = ", 0, 9);
			characterfile.write(Boolean.toString(p.hasHousee), 0, Boolean.toString(p.hasHousee).length());
			characterfile.newLine();
			characterfile.write("character-title = ", 0, 18);
			characterfile.write(Integer.toString(p.playerTitle), 0, Integer
				.toString(p.playerTitle).length());
			characterfile.newLine();
			characterfile.write("loyaltypoints = ", 0, 16);
			characterfile.write(Integer.toString(p.loyaltypoints), 0, Integer.toString(p.loyaltypoints).length());
			characterfile.newLine();
			characterfile.write("shopcollect = ", 0, 14);
			characterfile.write(Integer.toString(p.playerCollect), 0, Integer.toString(p.playerCollect).length());
			characterfile.newLine();
			characterfile.write("santaPrize = ", 0, 12);
			characterfile.write(Integer.toString(p.santaPrize), 0, Integer.toString(p.santaPrize).length());
			characterfile.newLine();
			characterfile.write("dungPoints = ", 0, 12);
			characterfile.write(Integer.toString(p.dungPoints), 0, Integer.toString(p.dungPoints).length());
			characterfile.newLine();
			characterfile.write("agilitypoints = ", 0, 16);
			characterfile.write(Integer.toString(p.agilitypoints), 0, Integer.toString(p.agilitypoints).length());
			characterfile.newLine();
			characterfile.write("Wheel = ", 0, 8);
			characterfile.write(Integer.toString(p.Wheel), 0, Integer.toString(p.Wheel).length());
			characterfile.newLine();	
			characterfile.write("spinsLe = ", 0, 10);
			characterfile.write(Integer.toString(p.spinsLe), 0, Integer.toString(p.spinsLe).length());
			characterfile.newLine();
			characterfile.write("Aura = ", 0, 7);
			characterfile.write(Integer.toString(p.AuraEquiped), 0, Integer.toString(p.AuraEquiped).length());
			characterfile.newLine();
			characterfile.write("votingPoints = ", 0, 15);			
			characterfile.write(Integer.toString(p.votingPoints), 0, Integer.toString(p.votingPoints).length());
			characterfile.newLine();
			characterfile.write("crystal-bow-shots = ", 0, 20);
			characterfile.write(Integer.toString(p.crystalBowArrowCount), 0, Integer.toString(p.crystalBowArrowCount).length());
			characterfile.newLine();
			characterfile.write("VLS-hits = ", 0, 11);
			characterfile.write(Integer.toString(p.degradeTime), 0, Integer.toString(p.degradeTime).length());
			characterfile.newLine(); 
			characterfile.write("skull-timer = ", 0, 14);
			characterfile.write(Integer.toString(p.skullTimer), 0, Integer.toString(p.skullTimer).length());
			characterfile.newLine();
			characterfile.write("MoneyOrb = ", 0, 11);
			characterfile.write(Integer.toString(p.MoneyCash), 0, Integer.toString(p.MoneyCash).length());
			characterfile.newLine();	
			characterfile.write("LoyaltyScore = ", 0, 15);
			characterfile.write(Integer.toString(p.LoyaltyScore), 0, Integer.toString(p.LoyaltyScore).length());
			characterfile.newLine();	
			characterfile.write("LoyaltyPoints = ", 0, 16);
			characterfile.write(Integer.toString(p.LoyaltyPoints), 0, Integer.toString(p.LoyaltyPoints).length());
			characterfile.newLine();
			characterfile.write("cw-games = ", 0, 11);
			characterfile.write(Integer.toString(p.cwGames), 0, Integer.toString(p.cwGames).length());
			characterfile.newLine();
			characterfile.write("has-npc = ", 0, 10);
			characterfile.write(Boolean.toString(p.hasNpc), 0, Boolean.toString(p.hasNpc).length());
			characterfile.newLine();
			characterfile.write("summonId = ", 0, 11);
			characterfile.write(Integer.toString(p.summonId), 0, Integer.toString(p.summonId).length());
			characterfile.newLine();
			characterfile.write("tempid=", 0, 7);
			characterfile.write(Integer.toString(p.tempid), 0, Integer.toString(p.tempid).length());
			characterfile.newLine();
			characterfile.write("tempamt=", 0, 8);
			characterfile.write(Integer.toString(p.tempamt), 0, Integer.toString(p.tempamt).length());
			characterfile.newLine();
			characterfile.write("tempprice=", 0, 10);
			characterfile.write(Integer.toString(p.tempprice), 0, Integer.toString(p.tempprice).length());
			characterfile.newLine();
			characterfile.write("EP = ", 0, 5);
			characterfile.write(Integer.toString(p.earningPotential), 0, Integer.toString(p.earningPotential).length());
			characterfile.newLine();
			characterfile.write("VL = ", 0, 5);
			characterfile.write(Integer.toString(p.vlsLeft2), 0, Integer.toString(p.vlsLeft2).length());
			characterfile.newLine();	
			characterfile.write("AP = ", 0, 5);
			characterfile.write(Integer.toString(p.spoints), 0, Integer.toString(p.spoints).length());
			characterfile.newLine();
			characterfile.write("character-longsword = ", 0, 22);
			characterfile.write(Integer.toString(p.vlsLeft), 0, Integer.toString(p.vlsLeft).length());
			characterfile.newLine();
			characterfile.write("character-warhammer = ", 0, 22);
			characterfile.write(Integer.toString(p.statLeft), 0, Integer.toString(p.statLeft).length());
			characterfile.newLine();
			characterfile.write("character-spear = ", 0, 18);
			characterfile.write(Integer.toString(p.vSpearLeft), 0, Integer.toString(p.vSpearLeft).length());
			characterfile.newLine();
			characterfile.write("character-chainbody = ", 0, 22);
			characterfile.write(Integer.toString(p.vTopLeft), 0, Integer.toString(p.vTopLeft).length());
			characterfile.newLine();
			characterfile.write("character-chainskirt = ", 0, 23);
			characterfile.write(Integer.toString(p.vLegsLeft), 0, Integer.toString(p.vLegsLeft).length());
			characterfile.newLine();
			characterfile.write("character-full helm = ", 0, 22);
			characterfile.write(Integer.toString(p.sHelmLeft), 0, Integer.toString(p.sHelmLeft).length());
			characterfile.newLine();
			characterfile.write("character-platebody = ", 0, 22);
			characterfile.write(Integer.toString(p.sTopLeft), 0, Integer.toString(p.sTopLeft).length());
			characterfile.newLine();
			characterfile.write("character-platelegs = ", 0, 22);
			characterfile.write(Integer.toString(p.sLegsLeft), 0, Integer.toString(p.sLegsLeft).length());
			characterfile.newLine();
			characterfile.write("character-hood = ", 0, 17);
			characterfile.write(Integer.toString(p.zHoodLeft), 0, Integer.toString(p.zHoodLeft).length());
			characterfile.newLine();
			characterfile.write("character-staff = ", 0, 18);
			characterfile.write(Integer.toString(p.zStaffLeft), 0, Integer.toString(p.zStaffLeft).length());
			characterfile.newLine();
			characterfile.write("character-robe top = ", 0, 21);
			characterfile.write(Integer.toString(p.zTopLeft), 0, Integer.toString(p.zTopLeft).length());
			characterfile.newLine();
			characterfile.write("character-robe bottom = ", 0, 24);
			characterfile.write(Integer.toString(p.zBottomLeft), 0, Integer.toString(p.zBottomLeft).length());
			characterfile.newLine();
			characterfile.write("character-leather body = ", 0, 25);
			characterfile.write(Integer.toString(p.mBodyLeft), 0, Integer.toString(p.mBodyLeft).length());
			characterfile.newLine();
			characterfile.write("character-chaps = ", 0, 18);
			characterfile.write(Integer.toString(p.mChapsLeft), 0, Integer.toString(p.mChapsLeft).length());
			characterfile.newLine();
			characterfile.write("magic-book = ", 0, 13);
			characterfile.write(Integer.toString(p.playerMagicBook), 0, Integer.toString(p.playerMagicBook).length());
			characterfile.newLine();
			for (int b = 0; b < p.barrowsNpcs.length; b++) {
				characterfile.write("brother-info = ", 0, 15);
				characterfile.write(Integer.toString(b), 0, Integer.toString(b).length());
				characterfile.write("	", 0, 1);
				characterfile.write(p.barrowsNpcs[b][1] <= 1 ? Integer.toString(0) : Integer.toString(p.barrowsNpcs[b][1]), 0, Integer.toString(p.barrowsNpcs[b][1]).length());
				characterfile.newLine();
			}	
			characterfile.write("special-amount = ", 0, 17);
			characterfile.write(Double.toString(p.specAmount), 0, Double.toString(p.specAmount).length());
			characterfile.newLine();
			characterfile.write("selected-coffin = ", 0, 18);
			characterfile.write(Integer.toString(p.randomCoffin), 0, Integer.toString(p.randomCoffin).length());
			characterfile.newLine();
			characterfile.write("barrows-killcount = ", 0, 20);
			characterfile.write(Integer.toString(p.barrowsKillCount), 0, Integer.toString(p.barrowsKillCount).length());
			characterfile.newLine();
			characterfile.write("teleblock-length = ", 0, 19);
			characterfile.write(Integer.toString(tbTime), 0, Integer.toString(tbTime).length());
			characterfile.newLine();
			characterfile.write("pc-points = ", 0, 12);
			characterfile.write(Integer.toString(p.pcPoints), 0, Integer.toString(p.pcPoints).length());
			characterfile.newLine();
			characterfile.write("barbPoints = ", 0, 12);
			characterfile.write(Integer.toString(p.barbPoints), 0, Integer.toString(p.barbPoints).length());
			characterfile.newLine();
			characterfile.write("gwdelay = ", 0, 10);
			characterfile.write(Integer.toString(p.gwdelay), 0, Integer.toString(p.gwdelay).length());
			characterfile.newLine();
			characterfile.write("dungRest = ", 0, 10);
			characterfile.write(Integer.toString(p.dungRest), 0, Integer.toString(p.dungRest).length());
			characterfile.newLine();
			characterfile.write("Altar = ", 0, 8);
			characterfile.write(Integer.toString(p.altarPrayed), 0, Integer.toString(p.altarPrayed).length());
			characterfile.newLine();
			characterfile.write("Arma-KC = ", 0, 10);
			characterfile.write(Integer.toString(p.Arma), 0, Integer.toString(p.Arma).length());
			characterfile.newLine();
			characterfile.write("Band-KC = ", 0, 10);
			characterfile.write(Integer.toString(p.Band), 0, Integer.toString(p.Band).length());
			characterfile.newLine();
			characterfile.write("Zammy-KC = ", 0, 11);
			characterfile.write(Integer.toString(p.Zammy), 0, Integer.toString(p.Zammy).length());
			characterfile.newLine();
			characterfile.write("Sara-KC = ", 0, 10);
			characterfile.write(Integer.toString(p.Sara), 0, Integer.toString(p.Sara).length());
			characterfile.newLine();
			characterfile.newLine();
			characterfile.write("isDonator = ", 0, 12);
			characterfile.write(Integer.toString(p.isDonator), 0, Integer.toString(p.isDonator).length());
			characterfile.newLine();
			characterfile.write("issDonator = ", 0, 13);
			characterfile.write(Integer.toString(p.issDonator), 0, Integer.toString(p.issDonator).length());
			characterfile.newLine();
			characterfile.write("donatorChest = ", 0, 15);
			characterfile.write(Integer.toString(p.donatorChest), 0, Integer.toString(p.donatorChest).length());
			characterfile.newLine();
			characterfile.write("usedxplock = ", 0, 13);
			characterfile.write(Boolean.toString(p.usedxplock), 0, Boolean.toString(p.usedxplock).length());
			characterfile.newLine();
			characterfile.write("slayerTask = ", 0, 13);
			characterfile.write(Integer.toString(p.slayerTask), 0, Integer.toString(p.slayerTask).length());
			characterfile.newLine(); 
			characterfile.write("xpLock = ", 0, 9);
			characterfile.write(Boolean.toString(p.xpLock), 0, Boolean.toString(p.xpLock).length());
			characterfile.newLine();
			characterfile.write("Agrith = ", 0, 9);
			characterfile.write(Boolean.toString(p.Agrith), 0, Boolean.toString(p.Agrith).length());
			characterfile.newLine();
			characterfile.write("Flambeed = ", 0, 11);
			characterfile.write(Boolean.toString(p.Flambeed), 0, Boolean.toString(p.Flambeed).length());
			characterfile.newLine();
			characterfile.write("Karamel = ", 0, 10);
			characterfile.write(Boolean.toString(p.Karamel), 0, Boolean.toString(p.Karamel).length());
			characterfile.newLine();
			characterfile.write("Dessourt = ", 0, 11);
			characterfile.write(Boolean.toString(p.Dessourt), 0, Boolean.toString(p.Dessourt).length());
			characterfile.newLine();
			characterfile.write("culin = ", 0, 8);
			characterfile.write(Boolean.toString(p.Culin), 0, Boolean.toString(p.Culin).length());
			characterfile.newLine();
			characterfile.write("Nomad = ", 0, 8);
			characterfile.write(Boolean.toString(p.Nomad), 0, Boolean.toString(p.Nomad).length());
			characterfile.newLine();
			characterfile.write("Goblin = ", 0, 8);
			characterfile.write(Boolean.toString(p.Goblin), 0, Boolean.toString(p.Goblin).length());
			characterfile.newLine();
			characterfile.write("sir = ", 0, 6);
			characterfile.write(Boolean.toString(p.sir), 0, Boolean.toString(p.sir).length());
			characterfile.newLine();
			characterfile.write("vote = ", 0, 7);
			characterfile.write(Integer.toString(p.vote), 0, Integer.toString(p.vote).length());
			characterfile.newLine();
			characterfile.write("hasBankPin = ", 0, 13);
			characterfile.write(Boolean.toString(p.hasBankPin), 0, Boolean.toString(p.hasBankPin).length());
			characterfile.newLine();
			characterfile.write("bankPin1 = ", 0, 11);
			characterfile.write(Integer.toString(p.bankPin1), 0, Integer.toString(p.bankPin1).length());
			characterfile.newLine();
			characterfile.write("bankPin2 = ", 0, 11);
			characterfile.write(Integer.toString(p.bankPin2), 0, Integer.toString(p.bankPin2).length());
			characterfile.newLine();
			characterfile.write("bankPin3 = ", 0, 11);
			characterfile.write(Integer.toString(p.bankPin3), 0, Integer.toString(p.bankPin3).length());
			characterfile.newLine();
			characterfile.write("bankPin4 = ", 0, 11);
			characterfile.write(Integer.toString(p.bankPin4), 0, Integer.toString(p.bankPin4).length());
			characterfile.newLine();
			characterfile.write("taskAmount = ", 0, 13);
			characterfile.write(Integer.toString(p.taskAmount), 0, Integer.toString(p.taskAmount).length());
			characterfile.newLine();
			characterfile.write("magePoints = ", 0, 13);
			characterfile.write(Integer.toString(p.magePoints), 0, Integer.toString(p.magePoints).length());
			characterfile.newLine();
			characterfile.write("kingQuest = ", 0, 12);
			characterfile.write(Integer.toString(p.kingQuest), 0, Integer.toString(p.kingQuest).length());
			characterfile.newLine();
			characterfile.write("totalstored = ", 0, 14);
			characterfile.write(Integer.toString(p.totalstored), 0, Integer.toString(p.totalstored).length());
			characterfile.newLine();
			characterfile.write("autoRet = ", 0, 10);
			characterfile.write(Integer.toString(p.autoRet), 0, Integer.toString(p.autoRet).length());
			characterfile.newLine();
			characterfile.write("trade11 = ", 0, 10);
			characterfile.write(Integer.toString(p.trade11), 0, Integer.toString(p.trade11).length());
			characterfile.newLine();
			characterfile.write("SpeDelay = ", 0, 11);
			characterfile.write(Long.toString(p.SpecialDelay), 0, Long.toString(p.SpecialDelay).length());
			characterfile.newLine();
			characterfile.write("barrowskillcount = ", 0, 19);
			characterfile.write(Integer.toString(p.barrowsKillCount), 0, Integer.toString(p.barrowsKillCount).length());
			characterfile.newLine();
			characterfile.write("shopping = ", 0, 11);
			characterfile.write(Boolean.toString(p.isShopping), 0, Boolean.toString(p.isShopping).length());
			characterfile.newLine();
			characterfile.write("Jailed = ", 0, 9);
			characterfile.write(Boolean.toString(p.Jail), 0, Boolean.toString(p.Jail).length());
			characterfile.newLine();
			characterfile.write("hasChoosenDung = ", 0, 9);
			characterfile.write(Boolean.toString(p.hasChoosenDung), 0, Boolean.toString(p.hasChoosenDung).length());
			characterfile.newLine();
			characterfile.write("wave = ", 0, 7);
			characterfile.write(Integer.toString(p.waveId), 0, Integer.toString(p.waveId).length());
			characterfile.newLine();
			characterfile.write("dfs-charges = ", 0, 14);
			characterfile.write(Integer.toString(p.dfsCount), 0, Integer.toString(p.dfsCount).length());
			characterfile.newLine();
			characterfile.write("hasFollower = ", 0, 13);
			characterfile.write(Integer.toString(p.hasFollower), 0, Integer.toString(p.hasFollower).length());
			characterfile.newLine();
			characterfile.write("summoningnpcid = ", 0, 17);
			characterfile.write(Integer.toString(p.summoningnpcid), 0, Integer.toString(p.summoningnpcid).length());
			characterfile.newLine();
			characterfile.write("fightMode = ", 0, 12);
			characterfile.write(Integer.toString(p.fightMode), 0, Integer.toString(p.fightMode).length());
			characterfile.newLine();
			characterfile.write("COaltar = ", 0, 10);
			characterfile.write(Integer.toString(p.COaltar), 0, Integer.toString(p.COaltar).length());
			characterfile.newLine();
			characterfile.write("IsIDung = ", 0, 10);
			characterfile.write(Integer.toString(p.IsIDung), 0, Integer.toString(p.IsIDung).length());
			characterfile.newLine();
			characterfile.write("DUfood1 = ", 0, 10);
			characterfile.write(Integer.toString(p.DUfood1), 0, Integer.toString(p.DUfood1).length());
			characterfile.newLine();
			characterfile.write("DUfood2 = ", 0, 10);
			characterfile.write(Integer.toString(p.DUfood2), 0, Integer.toString(p.DUfood2).length());
			characterfile.newLine();
			characterfile.write("DUfood3 = ", 0, 10);
			characterfile.write(Integer.toString(p.DUfood3), 0, Integer.toString(p.DUfood3).length());
			characterfile.newLine();
			characterfile.write("DUfood4 = ", 0, 10);
			characterfile.write(Integer.toString(p.DUfood4), 0, Integer.toString(p.DUfood4).length());
			characterfile.newLine();
			characterfile.write("OWdungn = ", 0, 10);
			characterfile.write(Integer.toString(p.dungn), 0, Integer.toString(p.dungn).length());
			characterfile.newLine();
			characterfile.write("COchest = ", 0, 10);
			characterfile.write(Integer.toString(p.COchest), 0, Integer.toString(p.COchest).length());
			characterfile.newLine();
			characterfile.write("COtreee = ", 0, 10);
			characterfile.write(Integer.toString(p.COtreee), 0, Integer.toString(p.COtreee).length());
			characterfile.newLine();
			characterfile.write("COLectt = ", 0, 10);
			characterfile.write(Integer.toString(p.COLectt), 0, Integer.toString(p.COLectt).length());
			characterfile.newLine();
			characterfile.write("COtelee = ", 0, 10);
			characterfile.write(Integer.toString(p.COtelee), 0, Integer.toString(p.COtelee).length());
			characterfile.newLine();
			characterfile.write("COcryst = ", 0, 10);
			characterfile.write(Integer.toString(p.COcryst), 0, Integer.toString(p.COcryst).length());
			characterfile.newLine();
			characterfile.write("CObeddy = ", 0, 10);
			characterfile.write(Integer.toString(p.CObeddy), 0, Integer.toString(p.CObeddy).length());
			characterfile.newLine();
			characterfile.write("dungPoints = ", 0, 12);
			characterfile.write(Integer.toString(p.dungPoints), 0, Integer.toString(p.dungPoints).length());
			characterfile.newLine();
			characterfile.newLine();
			characterfile.newLine();
			
		/*EQUIPMENT*/
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < p.playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerEquipment[i]), 0, Integer.toString(p.playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerEquipmentN[i]), 0, Integer.toString(p.playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();
			
		/*LOOK*/
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < p.playerAppearance.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerAppearance[i]), 0, Integer.toString(p.playerAppearance[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			
		/*SKILLS*/
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < p.playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerLevel[i]), 0, Integer.toString(p.playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(p.playerXP[i]), 0, Integer.toString(p.playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			
		/*ITEMS*/
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < p.playerItems.length; i++) {
				if (p.playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.playerItems[i]), 0, Integer.toString(p.playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.playerItemsN[i]), 0, Integer.toString(p.playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			
		/*BANK*/
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < p.bankItems.length; i++) {
				if (p.bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItems[i]), 0, Integer.toString(p.bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.bankItemsN[i]), 0, Integer.toString(p.bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			
		/*FRIENDS*/
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < p.friends.length; i++) {
				if (p.friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write("" + p.friends[i]);
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			
		/*Storeditems*/
			characterfile.write("[STORED]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < p.storeditems.length; i++) {
					characterfile.write("stored = ", 0, 9);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.storeditems[i]), 0, Integer.toString(p.storeditems[i]).length());
					characterfile.newLine();
			}
			characterfile.newLine();

		/*Storeditems*/
			characterfile.write("[OCCUPY]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < p.occupied.length; i++) {
				characterfile.write("occupy = ", 0, 9);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Boolean.toString(p.occupied[i]), 0, Boolean.toString(p.occupied[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();


		/*SHOP*/
			characterfile.write("[SHOP]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < p.playerShop.length; i++) {
				if (p.playerShop[i] > 0) {
					characterfile.write("character-shop = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.playerShop[i]), 0, Integer.toString(p.playerShop[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.playerShopP[i]), 0, Integer.toString(p.playerShopP[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(p.playerShopN[i]), 0, Integer.toString(p.playerShopN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();

			
			
		/*IGNORES*/
			/*characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();*/
		/*EOF*/
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch(IOException ioexception) {
			Misc.println(p.playerName+": error writing file.");
			return false;
		}
		return true;
	}
}