package server.model.players;


public class QuickCurses {

	/**
	 * @author 
	 */

	public static final int MAX_CURSES = 20;
	private Client c;
	
	public QuickCurses(Client c) {
		this.c = c;
	}

	public void clickCurse(int actionId) {
		canBeSelected(actionId);
		switch (actionId) {
			case 67050:
				if (!c.quickCurses2[0])
					c.quickCurses2[0] = true;
				else
					c.quickCurses2[0] = false;
			break;

			case 67051:
				if (!c.quickCurses2[1])
					c.quickCurses2[1] = true;
				else
					c.quickCurses2[1] = false;
			break;

			case 67052:
				if (!c.quickCurses2[2])
					c.quickCurses2[2] = true;
				else
					c.quickCurses2[2] = false;
			break;

			case 67053:
				if (!c.quickCurses2[3])
					c.quickCurses2[3] = true;
				else
					c.quickCurses2[3] = false;
			break;

			case 67054:
				if (!c.quickCurses2[4])
					c.quickCurses2[4] = true;
				else
					c.quickCurses2[4] = false;
			break;

			case 67055:
				if (!c.quickCurses2[5])
					c.quickCurses2[5] = true;
				else
					c.quickCurses2[5] = false;
			break;

			case 67056:
				if (!c.quickCurses2[6])
					c.quickCurses2[6] = false;
					//c.sendMessage("This curse currently doesn't have an effect.");
				else
					c.quickCurses2[6] = false;
			break;

			case 67057:
				if (!c.quickCurses2[7])
					c.quickCurses2[7] = true;
				else
					c.quickCurses2[7] = false;
			break;

			case 67058:
				if (!c.quickCurses2[8])
					c.quickCurses2[8] = true;
				else
					c.quickCurses2[8] = false;
			break;

			case 67059:
				if (!c.quickCurses2[9])
					c.quickCurses2[9] = true;
				else
					c.quickCurses2[9] = false;
			break;

			case 67060:
				if (!c.quickCurses2[10])
					c.quickCurses2[10] = true;
				else
					c.quickCurses2[10] = false;	
			break;

			case 67061:
				if (!c.quickCurses2[11])
					c.quickCurses2[11] = true;
				else
					c.quickCurses2[11] = false;	
			break;

			case 67062:
				if (!c.quickCurses2[12])
					c.quickCurses2[12] = true;
				else
					c.quickCurses2[12] = false;
			break;

			case 67063:
				if (!c.quickCurses2[13])
					c.quickCurses2[13] = true;
				else
					c.quickCurses2[13] = false;
			break;

			case 67064:
				if (!c.quickCurses2[14])
					c.quickCurses2[14] = true;
				else
					c.quickCurses2[14] = false;
			break;

			case 67065:
				if (!c.quickCurses2[15])
					c.quickCurses2[15] = true;
				else
					c.quickCurses2[15] = false;
			break;

			case 67066:
				if (!c.quickCurses2[16])
					c.quickCurses2[16] = true;
				else
					c.quickCurses2[16] = false;
			break;

			case 67067:
				if (!c.quickCurses2[17])
					c.quickCurses2[17] = true;
				else
					c.quickCurses2[17] = false;
			break;

			case 67068:
				if (!c.quickCurses2[18])
					c.quickCurses2[18] = true;
				else
					c.quickCurses2[18] = false;
			break;

			case 67069:
				if (!c.quickCurses2[19])
					c.quickCurses2[19] = true;
				else
					c.quickCurses2[19] = false;	
			break;
		}
	}

	public void canBeSelected(int actionId) {
		switch (actionId) {
			case 67051:
				c.quickCurses2[10] = false;
				c.quickCurses2[19] = false;
			break;

			case 67052:
				c.quickCurses2[11] = false;
				c.quickCurses2[19] = false;
			break;

			case 67053:
				c.quickCurses2[12] = false;
				c.quickCurses2[19] = false;
			break;

			case 67054:
				c.quickCurses2[16] = false;
			break;

			case 67057:
				c.quickCurses2[8] = false;
				c.quickCurses2[9] = false;
				c.quickCurses2[17] = false;
				c.quickCurses2[18] = false;
			break;

			case 67058:
				c.quickCurses2[7] = false;
				c.quickCurses2[9] = false;
				c.quickCurses2[17] = false;
				c.quickCurses2[18] = false;
			break;

			case 67059:
				c.quickCurses2[7] = false;
				c.quickCurses2[8] = false;
				c.quickCurses2[17] = false;
				c.quickCurses2[18] = false;
			break;

			case 67060:
				c.quickCurses2[1] = false;
				c.quickCurses2[19] = false;
			break;

			case 67061:
				c.quickCurses2[2] = false;
				c.quickCurses2[19] = false;
			break;

			case 67062:
				c.quickCurses2[3] = false;
				c.quickCurses2[19] = false;
			break;

			case 67063:
			case 67064:
				c.quickCurses2[19] = false;
			break;

			case 67066:
				c.quickCurses2[4] = false;
			break;

			case 67067:
				for (int i = 7; i < 10; i++)
					c.quickCurses2[i] = false;
					c.quickCurses2[18] = false;
			break;

			case 67068:
				for (int i = 7; i < 10; i++)
					c.quickCurses2[i] = false;
					c.quickCurses2[17] = false;
			break;

			case 67069:
				for (int i = 1; i < 5; i++) {
					for (int j = 10; j < 15; j++) {
						c.quickCurses2[i] = false;
						c.quickCurses2[j] = false;
					}
				}
			break;
		}
	}

	public void turnOnQuicks() {
		if (c.altarPrayed == 0) {
			for (int i = 0; i < c.quickPrayers2.length; i++) {
				if (c.quickPrayers2[i] && !c.prayerActive[i]){
					c.quickPray = true;
					c.getCombat().activatePrayer(i);
				}
				if (!c.quickPrayers2[i]) {
					c.prayerActive[i] = false;
					c.getPA().sendFrame36(c.PRAYER_GLOW[i], 0);
					c.getPA().requestUpdates();
				}
			}			
		} else {
			for (int i = 0; i < c.quickCurses2.length; i++) {
				if (c.quickCurses2[i] && !c.curseActive[i]){
					c.quickCurse = true;
					//c.getCombat().activateCurse(i);
				}
				if (!c.quickCurses2[i]) {
					c.curseActive[i] = false;
					c.getPA().sendFrame36(c.CURSE_GLOW[i], 0);
					c.getPA().requestUpdates();
				}
			}
		}
	}

	public void turnOffQuicks() {	
		c.getCombat().resetPrayers();
		c.getCombat().resetCurse();
		c.quickPray = false;
		c.quickCurse = false;
		c.headIcon = -1;
		c.getPA().requestUpdates();
	}

	public void selectQuickInterface() {
		if (c.altarPrayed == 0) {
			c.setSidebarInterface(5, 17200);
		} else {
			c.setSidebarInterface(5, 17234);
		}
	}

	public void clickConfirm() {
		if(c.altarPrayed == 0) {
			c.setSidebarInterface(5, 5608);
		} else {
			c.setSidebarInterface(5, 22500);
		}
	}
}
