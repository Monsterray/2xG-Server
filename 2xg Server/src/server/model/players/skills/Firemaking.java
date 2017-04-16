package server.model.players.skills;

import server.Config;
import server.Server;
import server.clip.region.Region;
import server.model.players.Client;
import server.task.Task;
import server.world.Tiles;

public class Firemaking {

	Client c;

	public Firemaking(Client c) {
		this.c = c;
	}

	private static Tiles currentTile;

	private static int[][] data = {
		{1511, 1,  40,  2732},		//	LOG
		{7406, 1,  250, 11406},		//	RED LOG
		{7405, 1,  250, 11405},		//	BLUE LOG
		{7404, 1,  250, 11404},		//	RED LOG
		{2862, 1,  40,  2732},		//	ACHEY
		{1521, 15, 60,  2732},		//	OAK
		{1519, 30, 105, 2732},		//	WILLOW
		{6333, 35, 105, 2732},		//	TEAK
		{1517, 45, 135, 2732},		//	MAPLE
		{10810,45, 135, 2732},		//	ARTIC PINE
		{6332, 50, 158, 2732},		//	MAHOGANY
		{1515, 60, 203, 2732},		//	YEW
		{1513, 75, 304, 2732},		//	MAGIC
	};

	public static boolean playerLogs(Client c, int i, int l) {
		boolean flag = false;
		for(int kl = 0; kl < data.length; kl++) {
			if((i == data[kl][0] && requiredItem(c, l)) || (requiredItem(c, i) && l == data[kl][0])) {
				flag = true;
			}
		}
		return flag;
	}

	private static int getAnimation(Client c, int item, int item1) {
		int[][] data = {
				{841, 6714}, {843, 6715}, {849, 6716}, {853, 6717},
				{857, 6718}, {861, 6719},
		};
		for(int i = 0; i < data.length; i++) {
			if(item == data[i][0] || item1 == data[i][0]) {
				return data[i][1];
			}
		}
		return 733;
	}

	private static boolean requiredItem(Client c, int i) {
		int[] data = {
				841, 843, 849, 853, 857, 861, 590
		};
		for(int l = 0; l < data.length; l++) {
			if(i == data[l]) {
				return true;
			}
		}
		return false;
	}

	public static void grabData(final Client c, final int useWith, final int withUse) {
		final int[] coords = new int[2];
		coords[0] = c.absX;
		coords[1] = c.absY;
		if(c.playerIsWoodcutting) {
			Woodcutting.resetWoodcutting(c);
		}
			/*	if(!c.inFM()) {
			c.sendMessage("You can only create fires in the FM skilling-area");
			return;
		}*/
		for(int i = 0; i < data.length; i++) {
			if((requiredItem(c, useWith) && withUse == data[i][0] || useWith == data[i][0] && requiredItem(c, withUse))) {
				if(c.playerLevel[11] < data[i][1]) {
					c.sendMessage("You don't have the correct Firemaking level to light this log!");
					c.sendMessage("You need the Firemaking level of at least "+ data[i][1] +".");
					return;
				}
				if (System.currentTimeMillis() - c.lastFire > 1200) {

					if(c.playerIsFiremaking) {
						return;
					}

					final int[] time = new int[3];
					final int log = data[i][0];
					final int fire = data[i][3];
					if(System.currentTimeMillis() - c.lastFire > 3000) {
						c.startAnimation(getAnimation(c, useWith, withUse));
						time[0] = 4;
						time[1] = 3;
					} else {
						time[0] = 1;
						time[1] = 2;
					}

					c.playerIsFiremaking = true;

					Server.itemHandler.createGroundItem(c, log, coords[0], coords[1], 1, c.getId());

					       Server.getTaskScheduler().addEvent(new Task(time[0], false) {
		public void execute() {
							Server.objectHandler.createAnObject(c, fire, coords[0], coords[1]);
							Server.itemHandler.removeGroundItem(c, log, coords[0], coords[1], false);
							c.playerIsFiremaking = false;
							this.stop();
						}
						
					});

					currentTile = new Tiles(c.absX - 1, c.absY, c.heightLevel);

					if (Region.getClipping(c.getX() - 1, c.getY(), c.heightLevel, -1, 0)) {
							c.getPA().walkTo(-1, 0);
						} else if (Region.getClipping(c.getX() + 1, c.getY(), c.heightLevel, 1, 0)) {
							c.getPA().walkTo(1, 0);
						} else if (Region.getClipping(c.getX(), c.getY() - 1, c.heightLevel, 0, -1)) {
							c.getPA().walkTo(0, -1);
						} else if (Region.getClipping(c.getX(), c.getY() + 1, c.heightLevel, 0, 1)) {
							c.getPA().walkTo(0, 1);
						}


					c.sendMessage("You light the logs.");

					       Server.getTaskScheduler().addEvent(new Task(time[1], false) {
		public void execute() {
							c.startAnimation(65535);
							this.stop();
						}
					
					});

       Server.getTaskScheduler().addEvent(new Task(20, false) {
		public void execute() {
							Server.objectHandler.createAnObject(c, -1, coords[0], coords[1]);
							Server.itemHandler.createGroundItem(c, 592, coords[0], coords[1], 1, c.getId());
							this.stop();
						}

					});

					c.getPA().addSkillXP(data[i][2] * Config.FIREMAKING_EXPERIENCE, 11);
					c.turnPlayerTo(c.absX+1, c.absY);
					c.getItems().deleteItem(data[i][0], c.getItems().getItemSlot(data[i][0]), 1);
					c.lastFire = System.currentTimeMillis();
				}
			}
		}
	}
}