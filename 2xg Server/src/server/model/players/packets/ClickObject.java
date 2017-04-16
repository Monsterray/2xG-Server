package server.model.players.packets;

import server.Server;
import server.model.minigames.CastleWars;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.skills.RuneCraft;
import server.task.Task;
import server.util.Misc;

public class ClickObject implements PacketType {

	public static final int FIRST_CLICK = 132, SECOND_CLICK = 252, THIRD_CLICK = 70;	
	
	@Override
	public void processPacket(final Client c, int packetType, int packetSize) {		
		c.clickObjectType = c.objectX = c.objectId = c.objectY = 0;
		c.objectYOffset = c.objectXOffset = 0;
		c.getPA().resetFollow();
		c.getCombat().resetPlayerAttack();
		c.getPA().requestUpdates();
		switch(packetType) {
			case FIRST_CLICK:
				if(c.teleTimer > 0) {
					c.sendMessage("You cannot use objects while teleporting");
					return;
				}
				c.objectX = c.getInStream().readSignedWordBigEndianA();
				c.objectId = c.getInStream().readUnsignedWord();
				c.objectY = c.getInStream().readUnsignedWordA();
				c.objectDistance = 1;
				if(c.playerRights == 3) {
					Misc.println("objectId: "+c.objectId+"  ObjectX: "+c.objectX+ "  objectY: "+c.objectY+" Xoff: "+ (c.getX() - c.objectX)+" Yoff: "+ (c.getY() - c.objectY)); 
				// } else if (c.playerRights == 3) {
				//	c.sendMessage("objectId: " + c.objectId + " objectX: " + c.objectX + " objectY: " + c.objectY);
				}
				if (Math.abs(c.getX() - c.objectX) > 25 || Math.abs(c.getY() - c.objectY) > 25) {
					c.resetWalkingQueue();
					break;
				}
				switch(c.objectId) {
					case 3566://rope's dont work yet
						if (c.absX == 2806 && c.absY == 9587) {
							c.startAnimation(2750);
							c.getPA().movePlayer(2806, 9582, 3);
							c.teleEndAnimation = 2588;
						} else if (c.absX == 2764 && c.absY == 9569) {
							c.startAnimation(2750);
							c.getPA().movePlayer(2804, 9587, 3);
							c.teleEndAnimation = 2588;
						} else if (c.absX == 2764 && c.absY == 9569) {
							c.startAnimation(2750);
							c.getPA().movePlayer(2769, 9569, 3);
							c.teleEndAnimation = 2588;
						} else if (c.absX == 2769 && c.absY == 9567) {
							c.startAnimation(2750);
							c.getPA().movePlayer(2764, 9567, 3);
							c.teleEndAnimation = 2588;
						} else if (c.absX == 2804 && c.absY == 9582) {
							c.startAnimation(2750);
							c.getPA().movePlayer(2804, 9587, 3);
							c.teleEndAnimation = 2588;
						}
					break;
					
					case 3565://come back to this
						if (c.absX == 2805 && c.absY == 9564) {
							c.getPA().walkTo(0,-2);
							c.startAnimation(839);
						} else if (c.absX == 2804 && c.absY == 9582) {
							c.getPA().walkTo(0,2);
							c.startAnimation(839);
						} else if (c.absX == 2779 && c.absY == 9590) {
							c.startAnimation(839);
							c.getPA().walkTo(-3,0);
						} else if (c.absX == 2805 && c.absY == 9564) {
							c.startAnimation(839);
							c.getPA().walkTo(0,3);
						} else if (c.absX == 2805 && c.absY == 9561) {
							c.startAnimation(839);
							c.getPA().walkTo(0,3);
						} else if (c.absX == 2783 && c.absY == 9561) {
							c.startAnimation(839);
							c.getPA().walkTo(0,3);
						} else if (c.absX == 2783 && c.absY == 9564) {
							c.startAnimation(839);
							c.getPA().walkTo(0,-3);
						} else if (c.absX == 2776 && c.absY == 9590) {
							c.startAnimation(839);
							c.getPA().walkTo(3,0);
						} else if (c.absX == 2779 && c.absY == 9590) {
							c.startAnimation(839);
							c.getPA().walkTo(-3,0);
						}
					case 2478: //Air alter
						//if(c.rMQ >= 8) {
						RuneCraft.craftRunes(c, 556);
						// } else {
						//c.sendMessage("You need to finnish Rune mysteries before training RuneCrafting!");
						// } 
					break;
					
                     /*
                     * CastleWars
                     */
                    case 4387:
                        CastleWars.addToWaitRoom(c, 1); //saradomin
					break;
					
                    case 4388:
                        CastleWars.addToWaitRoom(c, 2); // zamorak
					break;
					
                    case 4408:
                        CastleWars.addToWaitRoom(c, 3); //guthix
					break;
					
                    case 4389: //sara
                    case 4390: //zammy waiting room portal
                        CastleWars.leaveWaitingRoom(c);
					break;
					
					case 2479: //Mind Alter
						//if(c.rMQ >= 8) {
						RuneCraft.craftRunes(c, 558);
						//	} else {
						//c.sendMessage("You need to finnish Rune mysteries before training RuneCrafting!");
						// } 
					break;
					
					case 2480: //Water Alter
						//if(c.rMQ >= 8) {
						RuneCraft.craftRunes(c, 555);
						//					} else {
						//c.sendMessage("You need to finnish Rune mysteries before training RuneCrafting!");
						// } 
					break;
						
					case 2481: //Earth Alter
						//if(c.rMQ >= 8) {
						RuneCraft.craftRunes(c, 557);
						//					} else {
						//c.sendMessage("You need to finnish Rune mysteries before training RuneCrafting!");
						// } 
					break;
						
					case 2482: //Fire Alter
						//if(c.rMQ >= 8) {
						RuneCraft.craftRunes(c, 554);
						//					} else {
						//c.sendMessage("You need to finnish Rune mysteries before training RuneCrafting!");
						// } 
					break;
						
					case 2483: //Body Alter
						//if(c.rMQ >= 8) {
						RuneCraft.craftRunes(c, 559);
						//					} else {
						//c.sendMessage("You need to finnish Rune mysteries before training RuneCrafting!");
						// } 
					break;
						
					case 23271:
					if (c.getX() >= 3251){
						c.objectYOffset = 2;
					} else {
						c.objectYOffset = 0;
					}
									break;
									
					case 1733:
						c.objectYOffset = 2;
					break;

					case 26288:
					case 26287:
					case 26286:
					case 26289:
					case 1738:
					c.objectDistance = 3;
					break;

					case 3192:
					c.objectDistance = 7;
					break;
					
					case 4058:
					case 154:
					c.objectDistance = 7;
					break;

					case 6451:
					case 6452:
					c.objectDistance = 1;
					break;
				
					case 3044:
						c.objectDistance = 3;
					break;
					
					case 245:
						c.objectYOffset = -1;
						c.objectDistance = 0;
					break;
					
					case 272:
						c.objectYOffset = 1;
						c.objectDistance = 0;
					break;
					
					case 273:
						c.objectYOffset = 1;
						c.objectDistance = 0;
					break;
					
					case 246:
						c.objectYOffset = 1;
						c.objectDistance = 0;
					break;
					
					case 4493:
					case 4494:
					case 4496:
					case 4495:
						c.objectDistance = 5;
					break;
					
					case 10229:
					case 6522:
						c.objectDistance = 2;
					break;
					
					case 8959:
						c.objectYOffset = 1;
					break;
					
					case 4417:
						if (c.objectX == 2425 && c.objectY == 3074)
							c.objectYOffset = 2;
					break;
					
					case 4420:
						if (c.getX() >= 2383 && c.getX() <= 2385){
							c.objectYOffset = 1;
						} else {
							c.objectYOffset = -2;
						}
					case 6552:
					case 409:
						c.objectDistance = 2;
					break;
					
					case 2879:
					case 2878:
						c.objectDistance = 3;
					break;
					
					case 2558:
						c.objectDistance = 0;
						if (c.absX > c.objectX && c.objectX == 3044)
							c.objectXOffset = 1;
						if (c.absY > c.objectY)
							c.objectYOffset = 1;
						if (c.absX < c.objectX && c.objectX == 3038)
							c.objectXOffset = -1;
					break;
					
					case 9356:
						c.objectDistance = 2;
					break;
					
					case 5959:
					case 1815:
					case 5960:
					case 1816:
						c.objectDistance = 0;
					break;
					
					case 9293:
						c.objectDistance = 2;
					break;
					
					case 4418:
						if (c.objectX == 2374 && c.objectY == 3131)
							c.objectYOffset = -2;
						else if (c.objectX == 2369 && c.objectY == 3126)
							c.objectXOffset = 2;
						else if (c.objectX == 2380 && c.objectY == 3127)
							c.objectYOffset = 2;
						else if (c.objectX == 2369 && c.objectY == 3126)
							c.objectXOffset = 2;
						else if (c.objectX == 2374 && c.objectY == 3131)
							c.objectYOffset = -2;
					break;
					
					case 9706:
						c.objectDistance = 0;
						c.objectXOffset = 1;
					break;
					
					case 9707:
						c.objectDistance = 0;
						c.objectYOffset = -1;
					break;

		
					case 13999:
						c.getPA().startTeleport(3087, 3498, 0, "modern");
						c.teleportToX = 3093;
						c.teleportToY = 3487;
					break;
					
					case 4419:
					case 6707: // verac
						c.objectYOffset = 3;
					break;
					
					case 6823:
						c.objectDistance = 2;
						c.objectYOffset = 1;
					break;
					
					case 6706: // torag
						c.objectXOffset = 2;
					break;
					
					case 6772:
						c.objectDistance = 2;
						c.objectYOffset = 1;
					break;
					
					case 6705: // karils
						c.objectYOffset = -1;
					break;
					
					case 6822:
						c.objectDistance = 2;
						c.objectYOffset = 1;
					break;
					
					case 6704: // guthan stairs
						c.objectYOffset = -1;
					break;
					
					case 6773:
						c.objectDistance = 2;
						c.objectXOffset = 1;
						c.objectYOffset = 1;
					break;
					
					case 6703: // dharok stairs
						c.objectXOffset = -1;
					break;
					
					case 6771:
						c.objectDistance = 2;
						c.objectXOffset = 1;
						c.objectYOffset = 1;
					break;
					
					case 6702: // ahrim stairs
						c.objectXOffset = -1;
					break;
					
					case 6821:
						c.objectDistance = 2;
						c.objectXOffset = 1;
						c.objectYOffset = 1;
					break;
					
					case 1292: //dramen tree-gives magic logs
					case 1291: //dead tree
					case 1290: //dead tree
					case 1289: //dead tree
					case 1286: //dead tree
					case 1285: //dead tree
					case 1284: //dead tree
					case 1283: //dead tree
					case 1276:
					case 1278: //trees
					case 1281: //oak
					case 1308: //willow
					case 1307: //maple
					case 1309: //yew
					case 1306: //yew
						c.objectDistance = 3;
					break;
					
					default:
						//c.sendMessage("Doesn't Have a case please post on the forum the line below");
						//c.sendMessage("Action ID:"+ c.objectId +"     First Click ClickObject");
						c.objectDistance = 1;
						c.objectXOffset = 0;
						c.objectYOffset = 0;
					break;		
				}
				if(c.goodDistance(c.objectX+c.objectXOffset, c.objectY+c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) {
					c.getActions().firstClickObject(c.objectId, c.objectX, c.objectY);
				} else {
					c.clickObjectType = 1;
					Server.getTaskScheduler().addEvent(new Task(1, false) {
						public void execute() {
							if(c.clickObjectType == 1 && c.goodDistance(c.objectX + c.objectXOffset, c.objectY + c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) {
								c.getActions().firstClickObject(c.objectId, c.objectX, c.objectY);
								c.clickObjectType = 0;
								this.stop();
							}
							if(c.clickObjectType > 1 || c.clickObjectType == 0)
								c.clickObjectType = 0;
							this.stop();
						}
					
					});
				
				}
			break;
			
			case SECOND_CLICK:
				c.objectId = c.getInStream().readUnsignedWordBigEndianA();
				c.objectY = c.getInStream().readSignedWordBigEndian();
				c.objectX = c.getInStream().readUnsignedWordA();
				c.objectDistance = 1;
				if(c.playerRights == 3) {
					Misc.println("objectId: "+c.objectId+"  ObjectX: "+c.objectX+ "  objectY: "+c.objectY+" Xoff: "+ (c.getX() - c.objectX)+" Yoff: "+ (c.getY() - c.objectY)); 
				}
				
				switch(c.objectId) {
					case 6163:
					case 6165:
					case 6166:
					case 6164:
					case 6162:
						c.objectDistance = 2;
					break;
					
					case 26288:
					case 26287:
					case 26286:
					case 26289:
						c.objectDistance = 3;
					break;
					
					default:
						//c.sendMessage("Doesn't Have a case please post on the forum the line below");
						//c.sendMessage("Action ID:"+ c.objectId +"     Second Click ClickObject");
						c.objectDistance = 1;
						c.objectXOffset = 0;
						c.objectYOffset = 0;
					break;
					
				}
				if(c.goodDistance(c.objectX+c.objectXOffset, c.objectY+c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) { 
					c.getActions().secondClickObject(c.objectId, c.objectX, c.objectY);
				} else {
					c.clickObjectType = 2;
					Server.getTaskScheduler().addEvent(new Task(1, false) {
						public void execute() {
							if(c.clickObjectType == 2 && c.goodDistance(c.objectX + c.objectXOffset, c.objectY + c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) {
								c.getActions().secondClickObject(c.objectId, c.objectX, c.objectY);
								c.clickObjectType = 0;
								this.stop();
							}
							if(c.clickObjectType < 2 || c.clickObjectType > 2)
								c.clickObjectType = 0;
							this.stop();
						}
						
					});
				}
			break;
			
			case THIRD_CLICK:
				c.objectX = c.getInStream().readSignedWordBigEndian();
				c.objectY = c.getInStream().readUnsignedWord();
				c.objectId = c.getInStream().readUnsignedWordBigEndianA();
				
				if(c.playerRights == 3) {
					Misc.println("objectId: "+c.objectId+"  ObjectX: "+c.objectX+ "  objectY: "+c.objectY+" Xoff: "+ (c.getX() - c.objectX)+" Yoff: "+ (c.getY() - c.objectY)); 
				}
				
				switch(c.objectId) {
					default:
						//c.sendMessage("Doesn't Have a case please post on the forum the line below");
						//c.sendMessage("Action ID:"+ c.objectId +"     Third Click ClickObject");
						c.objectDistance = 1;
						c.objectXOffset = 0;
						c.objectYOffset = 0;
					break;		
				}
				if(c.goodDistance(c.objectX+c.objectXOffset, c.objectY+c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) { 
					c.getActions().thirdClickObject(c.objectId, c.objectX, c.objectY);
				} else {
					c.clickObjectType = 3;
					Server.getTaskScheduler().addEvent(new Task(1, false) {
						public void execute() {
							if(c.clickObjectType == 3 && c.goodDistance(c.objectX + c.objectXOffset, c.objectY + c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) {
								c.getActions().thirdClickObject(c.objectId, c.objectX, c.objectY);
								c.clickObjectType = 0;
								this.stop();
							}
							if(c.clickObjectType < 3)
								c.clickObjectType = 0;
							this.stop();
						}
					
					});
				}
			break;
		}

	}
	public void handleSpecialCase(Client c, int id, int x, int y) {
		c.objectX = c.getInStream().readSignedWordBigEndian();
		c.objectY = c.getInStream().readUnsignedWord();
		c.objectId = c.getInStream().readUnsignedWordBigEndianA();
		if(c.playerRights == 3) {
			Misc.println("objectId: "+c.objectId+"  ObjectX: "+c.objectX+ "  objectY: "+c.objectY+" Xoff: "+ (c.getX() - c.objectX)+" Yoff: "+ (c.getY() - c.objectY)); 
		}
		switch(c.objectId) {
			default:
				c.sendMessage("Doesn't Have a case please post on the forum the line below");
				c.sendMessage("Action ID:"+ c.objectId +"     Special ClickObject");
				c.objectDistance = 1;
				c.objectXOffset = 0;
				c.objectYOffset = 0;
			break;		
		}
		if(c.goodDistance(c.objectX+c.objectXOffset, c.objectY+c.objectYOffset, c.getX(), c.getY(), c.objectDistance)) { 
			c.getActions().secondClickObject(c.objectId, c.objectX, c.objectY);
		}
	}

}
