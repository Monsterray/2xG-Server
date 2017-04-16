package server.model.players;


public class QuickPrayer {

	/**
	 * @author
	 */

	public static final int MAX_PRAYERS = 26;
	boolean headIcon;
	final int[] defPrayId = {67050, 67055, 67063, 67074, 67075};
	final int[] strPrayId = {67051, 67056, 67064, 67074, 67075};
	final int[] atkPrayId = {67052, 67057, 67065, 67074, 67075};
	final int[] rangePrayId = {67053, 67061, 67069};
	final int[] magePrayId = {67054, 67062, 67070};
	final int[] headIconsId = {67066, 67067, 67068, 67071, 67072, 67073};
	final int[] defPray = {0, 5, 13, 24, 25};
	final int[] strPray = {1, 6, 14, 24, 25};
	final int[] atkPray = {2, 7, 15, 24, 25};
	final int[] rangePray = {3, 11, 19};
	final int[] magePray = {4, 12, 20};
	final int[] headIcons = {16, 17, 18, 21, 22, 23};
	private Client c;
	
	public QuickPrayer(Client c) {
		this.c = c;
	}

	public void clickPray(int actionId) {
		canBeSelected(actionId);
		switch (actionId) {
			case 67050:
				if (!c.quickPrayers2[0])
					c.quickPrayers2[0] = true;
				else
					c.quickPrayers2[0] = false;
			break;

			case 67051:
				if (!c.quickPrayers2[1])
					c.quickPrayers2[1] = true;
				else
					c.quickPrayers2[1] = false;
			break;

			case 67052:
				if (!c.quickPrayers2[2])
					c.quickPrayers2[2] = true;
				else
					c.quickPrayers2[2] = false;
			break;

			case 67053:
				if (!c.quickPrayers2[3])
					c.quickPrayers2[3] = true;
				else
					c.quickPrayers2[3] = false;
			break;

			case 67054:
				if (!c.quickPrayers2[4])
					c.quickPrayers2[4] = true;
				else
					c.quickPrayers2[4] = false;
			break;

			case 67055:
				if (!c.quickPrayers2[5])
					c.quickPrayers2[5] = true;
				else
					c.quickPrayers2[5] = false;
			break;

			case 67056:
				if (!c.quickPrayers2[6])
					c.quickPrayers2[6] = true;
				else
					c.quickPrayers2[6] = false;
			break;

			case 67057:
				if (!c.quickPrayers2[7])
					c.quickPrayers2[7] = true;
				else
					c.quickPrayers2[7] = false;
			break;

			case 67058:
				if (!c.quickPrayers2[8])
					c.quickPrayers2[8] = true;
				else
					c.quickPrayers2[8] = false;
			break;

			case 67059:
				if (!c.quickPrayers2[9])
					c.quickPrayers2[9] = true;
				else
					c.quickPrayers2[9] = false;
			break;

			case 67060:
				if (!c.quickPrayers2[10])
					c.quickPrayers2[10] = true;
				else
					c.quickPrayers2[10] = false;	
			break;

			case 67061:
				if (!c.quickPrayers2[11])
					c.quickPrayers2[11] = true;
				else
					c.quickPrayers2[11] = false;	
			break;

			case 67062:
				if (!c.quickPrayers2[12])
					c.quickPrayers2[12] = true;
				else
					c.quickPrayers2[12] = false;
			break;

			case 67063:
				if (!c.quickPrayers2[13])
					c.quickPrayers2[13] = true;
				else
					c.quickPrayers2[13] = false;
			break;

			case 67064:
				if (!c.quickPrayers2[14])
					c.quickPrayers2[14] = true;
				else
					c.quickPrayers2[14] = false;
			break;

			case 67065:
				if (!c.quickPrayers2[15])
					c.quickPrayers2[15] = true;
				else
					c.quickPrayers2[15] = false;
			break;

			case 67066:
				if (!c.quickPrayers2[16])
					c.quickPrayers2[16] = true;
				else
					c.quickPrayers2[16] = false;
			break;

			case 67067:
				if (!c.quickPrayers2[17])
					c.quickPrayers2[17] = true;
				else
					c.quickPrayers2[17] = false;
			break;

			case 67068:
				if (!c.quickPrayers2[18])
					c.quickPrayers2[18] = true;
				else
					c.quickPrayers2[18] = false;
			break;

			case 67069:
				if (!c.quickPrayers2[19])
					c.quickPrayers2[19] = true;
				else
					c.quickPrayers2[19] = false;	
			break;

			case 67070:
				if (!c.quickPrayers2[20])
					c.quickPrayers2[20] = true;
				else
					c.quickPrayers2[20] = false;	
			break;

			case 67071:
				if (!c.quickPrayers2[21])
					c.quickPrayers2[21] = true;
				else
					c.quickPrayers2[21] = false;	
			break;

			case 67072:
				if (!c.quickPrayers2[22])
					c.quickPrayers2[22] = true;
				else
					c.quickPrayers2[22] = false;	
			break;

			case 67073:
				if (!c.quickPrayers2[23])
					c.quickPrayers2[23] = true;
				else
					c.quickPrayers2[23] = false;	
			break;

			case 67074:
				if (!c.quickPrayers2[24])
					c.quickPrayers2[24] = true;
				else
					c.quickPrayers2[24] = false;	
			break;

			case 67075:
				if (!c.quickPrayers2[25])
					c.quickPrayers2[25] = true;
				else
					c.quickPrayers2[25] = false;	
			break;
		}
	}

	public void canBeSelected(int actionId) {
		switch (actionId) {
			case 67050:
			case 67055:
			case 67063:
				for (int j = 0; j < defPrayId.length; j++) {
					if (defPrayId[j] != actionId) {
						c.quickPrayers2[defPray[j]] = false;
					}								
				}
			break;

			case 67051:
			case 67056:
			case 67064:
				for (int j = 0; j < strPray.length; j++) {
					if (strPrayId[j] != actionId) {
						c.quickPrayers2[strPray[j]] = false;
					}								
				}
				for (int j = 0; j < rangePray.length; j++) {
					if (rangePrayId[j] != actionId) {
						c.quickPrayers2[rangePray[j]] = false;
					}								
				}
				for (int j = 0; j < magePray.length; j++) {
					if (magePrayId[j] != actionId) {
						c.quickPrayers2[magePray[j]] = false;
					}								
				}
			break;

			case 67052:
			case 67057:
			case 67065:
				for (int j = 0; j < atkPray.length; j++) {
					if (atkPrayId[j] != actionId) {
						c.quickPrayers2[atkPray[j]] = false;
					}
				}
				for (int j = 0; j < rangePray.length; j++) {
					if (rangePrayId[j] != actionId) {
						c.quickPrayers2[rangePray[j]] = false;
					}
				}
				for (int j = 0; j < magePray.length; j++) {
					if (magePrayId[j] != actionId) {
						c.quickPrayers2[magePray[j]] = false;
					}							
				}
			break;

			case 67053:
			case 67061:
			case 67069:
				for (int j = 0; j < atkPray.length; j++) {
					if (atkPrayId[j] != actionId) {
						c.quickPrayers2[atkPray[j]] = false;
					}								
				}
				for (int j = 0; j < strPray.length; j++) {
					if (strPrayId[j] != actionId) {
						c.quickPrayers2[strPray[j]] = false;
					}								
				}
				for (int j = 0; j < rangePray.length; j++) {
					if (rangePrayId[j] != actionId) {
						c.quickPrayers2[rangePray[j]] = false;
					}								
				}
				for (int j = 0; j < magePray.length; j++) {
					if (magePrayId[j] != actionId) {
						c.quickPrayers2[magePray[j]] = false;
					}								
				}
			break;

			case 67054:
			case 67062:
			case 67070:
				for (int j = 0; j < atkPray.length; j++) {
					if (atkPrayId[j] != actionId) {
						c.quickPrayers2[atkPray[j]] = false;
					}								
				}
				for (int j = 0; j < strPray.length; j++) {
					if (strPrayId[j] != actionId) {
						c.quickPrayers2[strPray[j]] = false;
					}								
				}
				for (int j = 0; j < rangePray.length; j++) {
					if (rangePrayId[j] != actionId) {
						c.quickPrayers2[rangePray[j]] = false;
					}								
				}
				for (int j = 0; j < magePray.length; j++) {
					if (magePrayId[j] != actionId) {
						c.quickPrayers2[magePray[j]] = false;
					}								
				}
			break;

			case 67066:
			case 67067:
			case 67068:
			case 67071:
			case 67072:
			case 67073:
				for (int j = 0; j < headIcons.length; j++) {
					if (headIconsId[j] != actionId) {
						c.quickPrayers2[headIcons[j]] = false;
					}								
				}		
			break;

			case 67074:
				for (int j = 0; j < atkPray.length; j++) {
					if (atkPrayId[j] != actionId) {
						c.quickPrayers2[atkPray[j]] = false;
					}								
				}
				for (int j = 0; j < strPray.length; j++) {
					if (strPrayId[j] != actionId) {
						c.quickPrayers2[strPray[j]] = false;
					}								
				}
				for (int j = 0; j < rangePray.length; j++) {
					if (rangePrayId[j] != actionId) {
						c.quickPrayers2[rangePray[j]] = false;
					}								
				}
				for (int j = 0; j < magePray.length; j++) {
					if (magePrayId[j] != actionId) {
						c.quickPrayers2[magePray[j]] = false;
					}								
				}
				for (int j = 0; j < defPray.length; j++) {
					if (defPrayId[j] != actionId) {
						c.quickPrayers2[defPray[j]] = false;
					}								
				}
			break;

			case 67075:
				for (int j = 0; j < atkPray.length; j++) {
					if (atkPrayId[j] != actionId) {
						c.quickPrayers2[atkPray[j]] = false;
					}								
				}
				for (int j = 0; j < strPray.length; j++) {
					if (strPrayId[j] != actionId) {
						c.quickPrayers2[strPray[j]] = false;
					}								
				}
				for (int j = 0; j < rangePray.length; j++) {
					if (rangePrayId[j] != actionId) {
						c.quickPrayers2[rangePray[j]] = false;
					}								
				}
				for (int j = 0; j < magePray.length; j++) {
					if (magePrayId[j] != actionId) {
						c.quickPrayers2[magePray[j]] = false;
					}								
				}
				for (int j = 0; j < defPray.length; j++) {
					if (defPrayId[j] != actionId) {
						c.quickPrayers2[defPray[j]] = false;
					}								
				}
			break;
		}
	}
}
