package server.model.players.skills;

import server.Config;
import server.model.players.Client;

public class SkillHandler {

	public static final int WOODCUTTING_XP = Config.WOODCUTTING_EXPERIENCE;
	public static final int FIREMAKING_XP = Config.FIREMAKING_EXPERIENCE;
	public static final int COOKING_XP = Config.COOKING_EXPERIENCE;
	public static final int MINING_XP = Config.MINING_EXPERIENCE;
	public static final int FLETCHING_XP = Config.FLETCHING_EXPERIENCE;

	public static void resetPlayerSkillVariables(Client c) {
		if (c.playerIsWoodcutting) {
			Woodcutting.resetWoodcutting(c);
		} else if (c.playerIsMining) {
			for (int i = 0; i < 2; i++) {
				c.miningProp[i] = -1;
			}
		} else if (c.playerIsCooking) {
			for (int i2 = 0; i2 < 6; i2++) {
				c.cookingProp[i2] = -1;
			}
		}
	}

	public static boolean noInventorySpace(Client c, String skill) {
		if (c.getItems().freeSlots() == 0) {
			c.sendMessage("You haven't got enough inventory space to continue "+ skill + "!");
			c.getPA().sendStatement("You haven't got enough inventory space to continue "+ skill + "!");
			return false;
		}
		return true;
	}

	public static boolean hasRequiredLevel(final Client c, int id, int lvlReq,
			String skill, String event) {
		if (c.playerLevel[id] < lvlReq) {
			c.sendMessage("You dont't have a high enough " + skill + " level to " + event + "");
			c.sendMessage("You at least need the " + skill + " level of "+ lvlReq + ".");
			c.getPA().sendStatement("You haven't got high enough " + skill + " level to "+ event + "!");
			return false;
		}
		return true;
	}

	public static void deleteTime(Client c) {
		c.doAmount--;
	}
}