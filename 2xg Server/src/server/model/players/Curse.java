package server.model.players;

import server.Config;

public class Curse {
	
	public int[] def = {13, 19};
	public int[] str = {14, 19};
	public int[] atk = {1, 10, 19};
	public int[] rng = {2, 11, 19};
	public int[] mge = {3, 12, 19};
	public int[] sprt = {4, 16};//spirit
	boolean deactive = false;
	private Client c;
	
	public Curse(Client c) {
		this.c = c;
	}
	
	public void resetCurse() {
		for(int p = 0; p < c.curseActive.length; p++) {
			c.curseActive[p] = false;
			c.getPA().sendFrame36(c.CURSE_GLOW[p], 0);
		}
		c.headIcon = -1;
		c.getPA().requestUpdates();
	}
	
	public void deactivateLeeches() {
		if(c.curseActive[13]) {
			c.curseDefence -= 5;
		}
		if(c.curseActive[10]) {
			c.curseAttack -= 5;
		}
		if(c.curseActive[12]) {
			c.curseMagic -= 5;
		}
		if(c.curseActive[11]) {
			c.curseRange -= 5;
		}
		if(c.curseActive[14]) {
			c.curseStrength -= 5;
		}
	}
	
	public void prayDrop() {
		if (c.playerLevel[5] == 0) {
			c.curseDefence = 0;
			c.curseStrength = 0;
			c.curseAttack = 0;
			c.curseMagic = 0;
			c.curseRange = 0;
			c.updateRequired = true;
			c.setAppearanceUpdateRequired(true);
		}
	}
	
	public void curseStat(int curseId) {
		switch(curseId) {
			case 19:
				if(!c.curseActive[19]) {
					turmBonus();
				}else {
					deactivateTurmoil();
				}
				//deactivateLeeches();
				c.updateRequired = true;
				c.setAppearanceUpdateRequired(true);
				prayDrop();
			break;
			
			case 10:
				deactivateTurmoil();
				if(!c.curseActive[10]) {
					c.curseAttack += 5;
				}else if (c.curseActive[10] == false) {
					c.curseAttack -= 5;
				}
				prayDrop();
				c.updateRequired = true;
				c.setAppearanceUpdateRequired(true);
			break;
				
			case 11:
				deactivateTurmoil();
				if(!c.curseActive[11]) {
					c.curseRange += 5;
				}else if (c.curseActive[11] == false) {
					c.curseRange -= 5;
				}
				prayDrop();
				c.updateRequired = true;
				c.setAppearanceUpdateRequired(true);
			break;
			
			case 12:
				deactivateTurmoil();
				if(!c.curseActive[12]) {
					c.curseMagic += 5;
				}else if (c.curseActive[12]) {
					c.curseMagic -= 5;
				}
				prayDrop();
				c.updateRequired = true;
				c.setAppearanceUpdateRequired(true);
			break;
				
			case 14:
				deactivateTurmoil();
				if(!c.curseActive[14]) {
					c.curseStrength += 5;
				}else if (c.curseActive[14]) {
					c.curseStrength -= 5;
				}
				prayDrop();
				c.updateRequired = true;
				c.setAppearanceUpdateRequired(true);
			break;
				
			case 13:
				deactivateTurmoil();
				if(!c.curseActive[13]) {
					c.curseDefence += 5;
				}else if (c.curseActive[13]) {
					c.curseDefence -= 5;
				}
				prayDrop();
				c.updateRequired = true;
				c.setAppearanceUpdateRequired(true);
			break;
			
			case 15:
			case 16:
			case 17:
			case 18:
				deactivateLeeches();
				deactivateTurmoil();
				prayDrop();
				c.updateRequired = true;
				c.setAppearanceUpdateRequired(true);
			break;
		}
	}
	
	public void turmBonus() {
		if (c.curseActive[19] == false) {
			c.curseDefence = 0;
			c.curseStrength = 0;
			c.curseAttack = 0;
			deactive = false;
		}else if (c.inCombat == true && deactive == false) {
			c.curseDefence = 30;
			c.curseStrength = 33;
			c.curseAttack = 30;
		} else if (c.inCombat == false && deactive == false) {
			c.curseDefence = 15;
			c.curseStrength = 23;
			c.curseAttack = 15;
		}
		c.updateRequired = true;
		c.setAppearanceUpdateRequired(true);
	}
	
	public void deactivateTurmoil() {
		if(c.curseActive[19]) {
			deactive = true;
			turmBonus();
		}
	}
	
	public void strCurse(int i) {
		for (int j = 0; j < str.length; j++) {
			if (str[j] != i) {
				c.curseActive[str[j]] = false;
				c.getPA().sendFrame36(c.CURSE_GLOW[str[j]], 0);
			}								
		}
	}
	
	public void atkCurse(int i) {
		for (int j = 0; j < atk.length; j++) {
			if (atk[j] != i) {
				c.curseActive[atk[j]] = false;
				c.getPA().sendFrame36(c.CURSE_GLOW[atk[j]], 0);
			}						
		}
	}
	
	public void defCurse(int i) {
		for (int j = 0; j < def.length; j++) {
			if (def[j] != i) {
				c.curseActive[def[j]] = false;
				c.getPA().sendFrame36(c.CURSE_GLOW[def[j]], 0);
			}								
		}
	}
	
	public void rngCurse(int i) {
		for (int j = 0; j < rng.length; j++) {
			if (rng[j] != i) {
				c.curseActive[rng[j]] = false;
				c.getPA().sendFrame36(c.CURSE_GLOW[rng[j]], 0);
			}								
		}
	}
	
	public void mgeCurse(int i) {
		for (int j = 0; j < mge.length; j++) {
			if (mge[j] != i) {
				c.curseActive[mge[j]] = false;
				c.getPA().sendFrame36(c.CURSE_GLOW[mge[j]], 0);
			}								
		}
	}
	
	public void sprtCurse(int i) {
		for (int j = 0; j < sprt.length; j++) {
			if (sprt[j] != i) {
				c.curseActive[sprt[j]] = false;
				c.getPA().sendFrame36(c.CURSE_GLOW[sprt[j]], 0);
			}								
		}
	}
	
	public void activateCurse(int i) {
		if(c.duelRule[7]) {
			resetCurse();
			c.sendMessage("Prayer has been disabled in this duel!");
			return;
		}
		if (c.playerLevel[1] < 30) {
			c.getPA().sendFrame36(c.CURSE_GLOW[i], 0);
			c.sendMessage("You need 30 Defence to use this prayer.");
			return;
		}
				if (c.inBarbDef) {
			c.getPA().sendFrame36(c.CURSE_GLOW[i], 0);
			c.sendMessage("The barbarians are strongly against the use of prayers!");
			return;
		}
		//0 = pItem//1 = sapWar//2 = sapRng//3 = sapMge//4 = sapSprt
		//5 = berserk//6 = defSum//7 = defMge//8 = defRng//9 = defMel
		//10 = leechAtk//11 = leechRng//12 = leechMge//13 = leechDef//14 = leechStr
		//15 = leechEnrgy//16 = leechSpec//17 = wrath//18 = soul//19 = turmoil
		if(c.playerLevel[5] > 0 || !Config.PRAYER_POINTS_REQUIRED) {
			if(c.getPA().getLevelForXP(c.playerXP[5]) >= c.CURSE_LEVEL_REQUIRED[i] || !Config.PRAYER_LEVEL_REQUIRED) {
				boolean headIcon = false;
				switch(i) {
					case 0:
						if(c.prayerActive[10] == false) {
							c.startAnimation(12567);
							c.gfx0(2213);
							c.prayerActive[10] = true;
							c.lastProtItem = System.currentTimeMillis();
						} else {
							c.prayerActive[10] = false;
						}
					break;

					//case 1:
					case 10:
						if (c.curseActive[i] == false) {
							atkCurse(i); //
						}
					break;
					
					case 2:
					case 11:
						if (c.curseActive[i] == false) {
							rngCurse(i); //
						}
					break;
					
					case 3:
					case 12:
						if (c.curseActive[i] == false) {
							mgeCurse(i); //
						}
					break;
					
					case 4:
					case 16:
						if (c.curseActive[i] == false) {
							sprtCurse(i); //
						}
					break;
					
					case 5:
						if(c.prayerActive[5] == false) {
							c.startAnimation(12589);
							c.gfx0(2266);
							c.prayerActive[5] = true;
						} else {
							c.prayerActive[5] = false;
						}
					break;
					
					case 13:
						if (c.curseActive[i] == false) {
							defCurse(i); //
						}
					break;
					
					case 14:
						if (c.curseActive[i] == false) {
							strCurse(i); //
						}
					break;
					
					case 6:	
					case 7:					
					case 8:
					case 9:
						if(System.currentTimeMillis() - c.stopPrayerDelay < 5000) {
							c.sendMessage("You have been injured and can't use this prayer!");
							c.getPA().sendFrame36(c.CURSE_GLOW[7], 0);
							c.getPA().sendFrame36(c.CURSE_GLOW[8], 0);
							c.getPA().sendFrame36(c.CURSE_GLOW[9], 0);
							return;
						}
						if (i == 7)
							c.protMageDelay = System.currentTimeMillis();
						else if (i == 8)
							c.protRangeDelay = System.currentTimeMillis();
						else if (i == 9)
							c.protMeleeDelay = System.currentTimeMillis();
					case 17:
					case 18:
						headIcon = true;		
						for(int p = 6; p < 19; p++) {
							if(i != p && p != 10 && p != 11 && p != 10 && p != 12 && p != 13 && p != 14 && p != 15 && p != 16) {
								c.curseActive[p] = false;
								c.getPA().sendFrame36(c.CURSE_GLOW[p], 0);
							}
						}
					break;
					
					case 19:
						if (c.curseActive[i] == false) {
							c.startAnimation(12565);
							c.gfx0(2226);
							strCurse(i);
							atkCurse(i);
							defCurse(i);
							mgeCurse(i);
							rngCurse(i);
						}
					break;
				}
				if(!headIcon) {
					if(c.curseActive[i] == false) {
						c.curseActive[i] = true;
						c.getPA().sendFrame36(c.CURSE_GLOW[i], 1);					
					} else {
						c.curseActive[i] = false;
						c.getPA().sendFrame36(c.CURSE_GLOW[i], 0);
					}
				} else {
					if(c.curseActive[i] == false) {
						c.curseActive[i] = true;
						c.getPA().sendFrame36(c.CURSE_GLOW[i], 1);
						c.headIcon = c.CURSE_HEAD_ICONS[i];
						c.getPA().requestUpdates();
					} else {
						c.curseActive[i] = false;
						c.getPA().sendFrame36(c.CURSE_GLOW[i], 0);
						c.headIcon = -1;
						c.getPA().requestUpdates();
					}
				}
			} else {
				c.getPA().sendFrame36(c.CURSE_GLOW[i],0);
				c.getPA().sendFrame126("You need a @blu@Prayer level of "+c.CURSE_LEVEL_REQUIRED[i]+" to use "+c.CURSE_NAME[i]+".", 357);
				c.getPA().sendFrame126("Click here to continue", 358);
				c.getPA().sendFrame164(356);
			}
		} else {
			c.getPA().sendFrame36(c.CURSE_GLOW[i],0);
			c.sendMessage("You have run out of Prayer points!");
			c.sendMessage("You can recharge at an altar.");
		}		
	}
}
