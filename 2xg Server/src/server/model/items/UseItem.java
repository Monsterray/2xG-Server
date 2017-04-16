package server.model.items;

import server.Config;
import server.model.players.Client;

/**
 * 
 * @author Monsterray
 *
 */

public class UseItem {

	public static void ItemonObject(Client c, int objectID, int objectX, int objectY, int itemId) {
		if (!c.getItems().playerHasItem(itemId, 1))
			return;
		switch(objectID) {
			case 2783:
				c.getSmithingInt().showSmithInterface(itemId);
			break;
			
			case 8151: 
			case 8389: 
			case 8132: 
			case 7965: //fruit tree patch
			case 8553: //allotment patch
			case 8552: //allotment patch
			case 7848: //flower patch catherby
				c.getFarming().checkItemOnObject(itemId, objectX, objectY);
			break;
			
			/*case 2728:
			case 12269:
				c.getCooking().itemOnObject(itemId);
			break;*/
			
			case 15621:
				if(c.absX == 2857 && c.absY == 3537 || c.absX == 2851 && c.absY == 3537) {
					c.getWarriorsGuild().handleArmor(c, itemId, objectX, objectY);
				}
			break;
			
			case 13191:
				if (c.getPrayer().isBone(itemId))
					c.getPrayer().bonesOnHouseAltar(itemId);
			break;
			
			case 409:
				if (c.getPrayer().isBone(itemId))
					c.getPrayer().bonesOnAltar(itemId);
			break;
			
			default:
				c.sendMessage("Doesn't Have a case please post on the forum the line below");
				c.sendMessage("Object ID:"+ objectID +"  Item ID: "+itemId +"     UseItem.ItemOnObject");
			break;
		}
	}

	public static void ItemonItem(Client c, int itemUsed, int useWith) {
		/** AUTHOR Monsterray (Fixes all fucking noted potion on un noted pot)
		*** Start of saradomin brew fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 6686 && useWith == 6685 || itemUsed == 6685 && useWith == 6686) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6686 && useWith == 6689 || itemUsed == 6689 && useWith == 6686) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6686 && useWith == 6687 || itemUsed == 6687 && useWith == 6686) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6686 && useWith == 6691 || itemUsed == 6691 && useWith == 6686) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6688 && useWith == 6686 || itemUsed == 6686 && useWith == 6688) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6688 && useWith == 6687 || itemUsed == 6687 && useWith == 6688) {
			c.sendMessage("You can't do that idiot");
			return;
		}
		if (itemUsed == 6690 && useWith == 6688 || itemUsed == 6688 && useWith == 6690) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6692 && useWith == 6688 || itemUsed == 6688 && useWith == 6692) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6692 && useWith == 6685 || itemUsed == 6685 && useWith == 6692) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6692 && useWith == 6690 || itemUsed == 6690 && useWith == 6692) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6692 && useWith == 6689 || itemUsed == 6689 && useWith == 6692) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6692 && useWith == 6691 || itemUsed == 6691 && useWith == 6692) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6692 && useWith == 6687 || itemUsed == 6687 && useWith == 6692) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6688 && useWith == 6689 || itemUsed == 6689 && useWith == 6688) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6688 && useWith == 6685 || itemUsed == 6685 && useWith == 6688) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6688 && useWith == 6691 || itemUsed == 6691 && useWith == 6688) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6686 && useWith == 6692 || itemUsed == 6692 && useWith == 6686) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6690 && useWith == 6685 || itemUsed == 6685 && useWith == 6690) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6690 && useWith == 6689 || itemUsed == 6689 && useWith == 6690) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6690 && useWith == 6691 || itemUsed == 6691 && useWith == 6690) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 6690 && useWith == 6687 || itemUsed == 6687 && useWith == 6690) {
			c.sendMessage("You can't do that!");
			return;
		}
		/**
		*** End of saradomin brew fix ( Using noted sara brew on non noted to get non noted)
		**/
		/**
		*** Start of PRAYER POT fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 2434 && useWith == 2435 || itemUsed == 2435 && useWith == 2434) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 2435 && useWith == 139 || itemUsed == 139 && useWith == 2435) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 2435 && useWith == 141 || itemUsed == 141 && useWith == 2435) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 2435 && useWith == 143 || itemUsed == 143 && useWith == 2435) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 2434 && useWith == 139 || itemUsed == 139 && useWith == 2434) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 2434 && useWith == 141 || itemUsed == 141 && useWith == 2434) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 2434 && useWith == 140 || itemUsed == 140 && useWith == 2434) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 142 && useWith == 140 || itemUsed == 140 && useWith == 142) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 141 && useWith == 140 || itemUsed == 140 && useWith == 141) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 143 && useWith == 140 || itemUsed == 140 && useWith == 143) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 142 && useWith == 2435 || itemUsed == 2435 && useWith == 142) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 144 && useWith == 2435 || itemUsed == 2435 && useWith == 144) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 144 && useWith == 140 || itemUsed == 140 && useWith == 144) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 143 && useWith == 140 || itemUsed == 140 && useWith == 143) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 139 && useWith == 140 || itemUsed == 140 && useWith == 139) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 140 && useWith == 2435 || itemUsed == 2435 && useWith == 140) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 144 && useWith == 143|| itemUsed == 143 && useWith == 144) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 2434 && useWith == 143 || itemUsed == 143 && useWith == 2434) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 2434 && useWith == 142 || itemUsed == 142 && useWith == 2434) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 2434 && useWith == 144 || itemUsed == 144 && useWith == 2434) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 139 && useWith == 142 || itemUsed == 142 && useWith == 139) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 141 && useWith == 142 || itemUsed == 142 && useWith == 141) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 142 && useWith == 143 || itemUsed == 143 && useWith == 142) {
			c.sendMessage("You can't do that!");
			return;
		}
		if (itemUsed == 142 && useWith == 139 || itemUsed == 139 && useWith == 142) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 142 && useWith == 143 || itemUsed == 143 && useWith == 142) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 144 && useWith == 142 || itemUsed == 142 && useWith == 144) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 142 && useWith == 141 || itemUsed == 141 && useWith == 142) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 144 && useWith == 139 || itemUsed == 139 && useWith == 144) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 144 && useWith == 141 || itemUsed == 141 && useWith == 144) {
		c.sendMessage("You can't do that!");
		return;
		}
		/**
		*** End of PRAYER POT fix ( Using noted sara brew on non noted to get non noted)
		**/
		/**
		*** Start of super  STRength POT fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 2441 && useWith == 2440 || itemUsed == 2440 && useWith == 2441) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2441 && useWith == 158 || itemUsed == 158 && useWith == 2441) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 158 && useWith == 160 || itemUsed == 160 && useWith == 158) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 158 && useWith == 162 || itemUsed == 162 && useWith == 158) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 162 && useWith == 160 || itemUsed == 160 && useWith == 162) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2441 && useWith == 160 || itemUsed == 160 && useWith == 2441) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 162 && useWith == 160 || itemUsed == 160 && useWith == 162) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 162 && useWith == 161 || itemUsed == 161 && useWith == 162) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 162 && useWith == 159 || itemUsed == 159 && useWith == 162) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2440 && useWith == 158 || itemUsed == 158 && useWith == 2440) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2440 && useWith == 160 || itemUsed == 160 && useWith == 2440) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2441 && useWith == 162 || itemUsed == 162 && useWith == 2441) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 160 && useWith == 159 || itemUsed == 159 && useWith == 160) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 160 && useWith == 157 || itemUsed == 157 && useWith == 160) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 160 && useWith == 161 || itemUsed == 161 && useWith == 160) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2440 && useWith == 157 || itemUsed == 157 && useWith == 2440) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 158 && useWith == 157 || itemUsed == 157 && useWith == 158) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 158 && useWith == 159 || itemUsed == 159 && useWith == 158) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 158 && useWith == 161 || itemUsed == 161 && useWith == 158) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2441 && useWith == 159 || itemUsed == 159 && useWith == 2441) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2441 && useWith == 157 || itemUsed == 157 && useWith == 2441) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2441 && useWith == 161 || itemUsed == 161 && useWith == 2441) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 162 && useWith == 2440 || itemUsed == 2440 && useWith == 162) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 162 && useWith == 157 || itemUsed == 157 && useWith == 162) {
		c.sendMessage("You can't do that!");
		return;
		}
		/**
		*** End of super STRENGTH POTS fix ( Using noted sara brew on non noted to get non noted)
		**/
		/**
		*** Start of super attack pots fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 2436 && useWith == 150 || itemUsed == 150 && useWith == 2436) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2436 && useWith == 146 || itemUsed == 146 && useWith == 2436) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2436 && useWith == 148 || itemUsed == 148 && useWith == 2436) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 150 && useWith == 145 || itemUsed == 145 && useWith == 150) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 150 && useWith == 147 || itemUsed == 147 && useWith == 150) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 150 && useWith == 149 || itemUsed == 149 && useWith == 150) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 145 && useWith == 146 || itemUsed == 146 && useWith == 145) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 145 && useWith == 148 || itemUsed == 148 && useWith == 145) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 147 && useWith == 148 || itemUsed == 148 && useWith == 147) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 149 && useWith == 148 || itemUsed == 148 && useWith == 149) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 149 && useWith == 146 || itemUsed == 146 && useWith == 149) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 147 && useWith == 146 || itemUsed == 146 && useWith == 147) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2437 && useWith == 146 || itemUsed == 146 && useWith == 2437) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2437 && useWith == 148 || itemUsed == 148 && useWith == 2437) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 148 && useWith == 146 || itemUsed == 146 && useWith == 148) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 146 && useWith == 150 || itemUsed == 150 && useWith == 146) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 148 && useWith == 150 || itemUsed == 150 && useWith == 148) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2437 && useWith == 150 || itemUsed == 150 && useWith == 2437) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2437 && useWith == 2436 || itemUsed == 2436 && useWith == 2437) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2437 && useWith == 145 || itemUsed == 145 && useWith == 2437) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2437 && useWith == 147 || itemUsed == 147 && useWith == 2437) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2437 && useWith == 149 || itemUsed == 149 && useWith == 2437) {
		c.sendMessage("You can't do that!");
		return;
		}
		/**
		*** End of super ATTACK POTS fix ( Using noted sara brew on non noted to get non noted)
		**/
		/**
		*** Start of super DEFENCE pots fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 2442 && useWith == 164 || itemUsed == 164 && useWith == 2442) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2443 && useWith == 164 || itemUsed == 164 && useWith == 2443) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2443 && useWith == 166 || itemUsed == 166 && useWith == 2443) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2443 && useWith == 168 || itemUsed == 168 && useWith == 2443) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2443 && useWith == 2442 || itemUsed == 2442 && useWith == 2443) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2443 && useWith == 163 || itemUsed == 163 && useWith == 2443) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2443 && useWith == 165 || itemUsed == 165 && useWith == 2443) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2443 && useWith == 167 || itemUsed == 167 && useWith == 2443) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 164 && useWith == 163 || itemUsed == 163 && useWith == 164) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 164 && useWith == 165 || itemUsed == 165 && useWith == 164) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 164 && useWith == 167 || itemUsed == 167 && useWith == 164) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2442 && useWith == 166 || itemUsed == 166 && useWith == 2442) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2442 && useWith == 168 || itemUsed == 168 && useWith == 2442) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 165 && useWith == 166 || itemUsed == 166 && useWith == 165) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 168 && useWith == 163 || itemUsed == 163 && useWith == 168) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 168 && useWith == 165 || itemUsed == 165 && useWith == 168) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 168 && useWith == 166 || itemUsed == 166 && useWith == 168) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 164 && useWith == 166 || itemUsed == 166 && useWith == 164) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 164 && useWith == 168 || itemUsed == 168 && useWith == 164) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 166 && useWith == 168 || itemUsed == 168 && useWith == 166) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 168 && useWith == 167 || itemUsed == 167 && useWith == 168) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 163 && useWith == 166 || itemUsed == 166 && useWith == 163) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 167 && useWith == 166 || itemUsed == 166 && useWith == 167) {
		c.sendMessage("You can't do that!");
		return;
		}
		/**
		*** End of super DEFENCE POTS fix ( Using noted sara brew on non noted to get non noted)
		**/
		/**
		*** Start of ranging pots fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 2445 && useWith == 2444 || itemUsed == 2444 && useWith == 2445) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 172 && useWith == 2444 || itemUsed == 2444 && useWith == 172) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 174 && useWith == 2444 || itemUsed == 2444 && useWith == 174) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 172 && useWith == 169 || itemUsed == 169 && useWith == 172) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 174 && useWith == 169 || itemUsed == 169 && useWith == 174) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 172 && useWith == 171 || itemUsed == 171 && useWith == 172) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 174 && useWith == 171 || itemUsed == 171 && useWith == 174) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 172 && useWith == 173 || itemUsed == 173 && useWith == 172) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 174 && useWith == 173 || itemUsed == 173 && useWith == 174) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 170 && useWith == 2444 || itemUsed == 2444 && useWith == 170) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2445 && useWith == 170 || itemUsed == 170 && useWith == 2445) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2445 && useWith == 172 || itemUsed == 172 && useWith == 2445) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 170 && useWith == 172 || itemUsed == 172 && useWith == 170) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 170 && useWith == 169 || itemUsed == 169 && useWith == 170) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 170 && useWith == 171 || itemUsed == 171 && useWith == 170) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 170 && useWith == 173 || itemUsed == 173 && useWith == 170) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 174 && useWith == 172 || itemUsed == 172 && useWith == 174) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 170 && useWith == 174 || itemUsed == 174 && useWith == 170) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2445 && useWith == 174 || itemUsed == 174 && useWith == 2445) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2445 && useWith == 169 || itemUsed == 169 && useWith == 2445) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2445 && useWith == 171 || itemUsed == 171 && useWith == 2445) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 2445 && useWith == 173 || itemUsed == 173 && useWith == 2445) {
		c.sendMessage("You can't do that!");
		return;
		}
		/**
		*** End of ranging POTS fix ( Using noted sara brew on non noted to get non noted)
		**/
		/**
		*** Start of super restore pots fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 3025 && useWith == 3024 || itemUsed == 3024 && useWith == 3025) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3027 && useWith == 3025 || itemUsed == 3025 && useWith == 3027) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3029 && useWith == 3025 || itemUsed == 3025 && useWith == 3029) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3031 && useWith == 3025 || itemUsed == 3025 && useWith == 3031) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3031 && useWith == 3027 || itemUsed == 3027 && useWith == 3031) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3031 && useWith == 3029 || itemUsed == 3029 && useWith == 3031) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3027 && useWith == 3029 || itemUsed == 3029 && useWith == 3027) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3027 && useWith == 3024 || itemUsed == 3024 && useWith == 3027) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3027 && useWith == 3026 || itemUsed == 3026 && useWith == 3027) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3027 && useWith == 3028 || itemUsed == 3028 && useWith == 3027) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3027 && useWith == 3030 || itemUsed == 3030 && useWith == 3027) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3029 && useWith == 3027 || itemUsed == 3027 && useWith == 3029) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3025 && useWith == 3026 || itemUsed == 3026 && useWith == 3025) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3025 && useWith == 3028 || itemUsed == 3028 && useWith == 3025) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3025 && useWith == 3030 || itemUsed == 3030 && useWith == 3025) {
		c.sendMessage("You can't do that!");
		return;
		}//
		if (itemUsed == 3029 && useWith == 3024 || itemUsed == 3024 && useWith == 3029) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3029 && useWith == 3026 || itemUsed == 3026 && useWith == 3029) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3029 && useWith == 3028 || itemUsed == 3028 && useWith == 3029) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3029 && useWith == 3030 || itemUsed == 3030 && useWith == 3029) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3031 && useWith == 3024 || itemUsed == 3024 && useWith == 3031) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3031 && useWith == 3026 || itemUsed == 3026 && useWith == 3031) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3031 && useWith == 3028 || itemUsed == 3028 && useWith == 3031) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3031 && useWith == 3030 || itemUsed == 3030 && useWith == 3031) {
		c.sendMessage("You can't do that!");
		return;
		}
		/**
		*** End of super restore fix ( Using noted sara brew on non noted to get non noted)
		**/
		/**
		*** Start of magic pots fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 3041 && useWith == 3043 || itemUsed == 3043 && useWith == 3041) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3041 && useWith == 3045 || itemUsed == 3045 && useWith == 3041) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3041 && useWith == 3047 || itemUsed == 3047 && useWith == 3041) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3043 && useWith == 3040 || itemUsed == 3040 && useWith == 3043) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3043 && useWith == 3046 || itemUsed == 3046 && useWith == 3043) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3043 && useWith == 3044 || itemUsed == 3044 && useWith == 3043) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3043 && useWith == 3042 || itemUsed == 3042 && useWith == 3043) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3043 && useWith == 3045 || itemUsed == 3045 && useWith == 3043) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3043 && useWith == 3047 || itemUsed == 3047 && useWith == 3043) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3045 && useWith == 3045 || itemUsed == 3045 && useWith == 3045) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3045 && useWith == 3047 || itemUsed == 3047 && useWith == 3045) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3041 && useWith == 3040 || itemUsed == 3040 && useWith == 3041) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3041 && useWith == 3042 || itemUsed == 3042 && useWith == 3041) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3041 && useWith == 3044 || itemUsed == 3044 && useWith == 3041) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3041 && useWith == 3046 || itemUsed == 3046 && useWith == 3041) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3045 && useWith == 3046 || itemUsed == 3046 && useWith == 3045) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3045 && useWith == 3044 || itemUsed == 3044 && useWith == 3045) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3045 && useWith == 3042 || itemUsed == 3042 && useWith == 3045) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3045 && useWith == 3040 || itemUsed == 3040 && useWith == 3045) {
		c.sendMessage("You can't do that!");
		return;
		} //
		if (itemUsed == 3047 && useWith == 3046 || itemUsed == 3046 && useWith == 3047) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3047 && useWith == 3044 || itemUsed == 3044 && useWith == 3047) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3047 && useWith == 3042 || itemUsed == 3042 && useWith == 3047) {
		c.sendMessage("You can't do that!");
		return;
		}
		if (itemUsed == 3047 && useWith == 3040 || itemUsed == 3040 && useWith == 3047) {
		c.sendMessage("You can't do that!");
		return;
		}
		/**
		*** End of MAGIC POT fix ( Using noted sara brew on non noted to get non noted)
		**/		
		if (itemUsed == 227 || useWith == 227)
			c.getHerblore().handlePotMaking(itemUsed,useWith);
		if (c.getHerblore().checkItem(useWith, 5) || c.getHerblore().checkItem(itemUsed, 1) || c.getHerblore().checkItem(useWith, 1) || c.getHerblore().checkItem(itemUsed, 5))
			c.getHerblore().handlePotMaking(itemUsed,useWith);
		if (c.getItems().getItemName(itemUsed).contains("(") && c.getItems().getItemName(useWith).contains("("))
			c.getPotMixing().mixPotion2(itemUsed, useWith);
		if (itemUsed == 1733 || useWith == 1733)
			c.getCrafting().handleLeather(itemUsed, useWith);
		if (itemUsed == 1755 || useWith == 1755)
			c.getCrafting().handleChisel(itemUsed,useWith);
		if (itemUsed == 946 || useWith == 946)
			c.getFletching().handleLog(itemUsed,useWith);
		if (itemUsed == 53 || useWith == 53 || itemUsed == 52 || useWith == 52)
			c.getFletching().makeArrows(itemUsed, useWith);
		if ((itemUsed == 1540 && useWith == 11286) || (itemUsed == 11286 && useWith == 1540)) {
			if (c.playerLevel[c.playerSmithing] >= 95) {
				c.getItems().deleteItem(1540, c.getItems().getItemSlot(1540), 1);
				c.getItems().deleteItem(11286, c.getItems().getItemSlot(11286), 1);
				c.getItems().addItem(11283,1);
				c.sendMessage("You combine the two materials to create a dragonfire shield.");
				c.getPA().addSkillXP(500 * Config.SMITHING_EXPERIENCE, c.playerSmithing);
			} else {
				c.sendMessage("You need a smithing level of 95 to create a dragonfire shield.");
			}
		}
		if (itemUsed == 261 && useWith == 145) {
			if (c.getItems().playerHasItem(145, 1)  && c.getItems().playerHasItem(261, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 80) {
					c.getItems().deleteItem(261, c.getItems().getItemSlot(261),1);
					c.getItems().deleteItem(145, c.getItems().getItemSlot(145),1);
					c.getItems().addItem(15309,1);
					c.sendMessage("You make an Extreme Attack (3).");
					c.getPA().addSkillXP(300 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 80 to make an Extreme potion.");
				}
			} else {
				c.sendMessage("You need a Super Attack (3) and Clean Avantoe for this potion.");
				c.sendMessage("When you've got the ingridients, use the avantoe on the Super attack pot.");
			}
		}
		if (itemUsed == 267 && useWith == 157) { // fel
			if (c.getItems().playerHasItem(157, 1)  && c.getItems().playerHasItem(267, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 80) {
					c.getItems().deleteItem(267, c.getItems().getItemSlot(267),1);
					c.getItems().deleteItem(157, c.getItems().getItemSlot(157),1);
					c.getItems().addItem(15313,1);
					c.sendMessage("You make an Extreme Strength (3).");
					c.getPA().addSkillXP(300 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 80 to make an Extreme Potion.");
				}
			} else {
				c.sendMessage("You need a Super Strength (3) and Clean Dwarf Weed for this potion.");
				c.sendMessage("When you've got the ingridients, use the dwarf weed on the Super strength potion.");
			}
		}
		if (itemUsed == 2481 && useWith == 163) { // fel
			if (c.getItems().playerHasItem(163, 1)  && c.getItems().playerHasItem(2481, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 80) {
					c.getItems().deleteItem(2481, c.getItems().getItemSlot(2481),1);
					c.getItems().deleteItem(163, c.getItems().getItemSlot(163),1);
					c.getItems().addItem(15317,1);
					c.sendMessage("You make an Extreme Defence (3).");
					c.getPA().addSkillXP(300 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 80 to make an Extreme Potion.");
				}
			} else {
				c.sendMessage("You need a Super Defence (3) and Clean Lantadyme for this potion.");
				c.sendMessage("When you've got the ingridients, use the Lantadyme on the Super Defence Pot.");
			}
		}
		if (itemUsed == 3000 && useWith == 3042) { // fel
			if (c.getItems().playerHasItem(3042, 1)  && c.getItems().playerHasItem(3000, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 80) {
					c.getItems().deleteItem(3000, c.getItems().getItemSlot(3000),1);
					c.getItems().deleteItem(3042, c.getItems().getItemSlot(3042),1);
					c.getItems().addItem(15321,1);
					c.sendMessage("You make an Extreme Magic (3).");
					c.getPA().addSkillXP(300 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 80 to make an Extreme Potion.");
				}
			} else {
				c.sendMessage("You need a Magic Potion (3) and Clean Snapdragon for this potion.");
				c.sendMessage("When you've got the ingridients, use the Snapdragon on the Magic Pot.");
			}
		}
		if (itemUsed == 259 && useWith == 169) { // rätt
			if (c.getItems().playerHasItem(169, 1)  && c.getItems().playerHasItem(259, 1)) {
				if (c.playerLevel[c.playerHerblore] >= 80) {
					c.getItems().deleteItem(259, c.getItems().getItemSlot(259),1);
					c.getItems().deleteItem(169, c.getItems().getItemSlot(169),1);
					c.getItems().addItem(15325,1);
					c.sendMessage("You make an Extreme Ranging (3).");
					c.getPA().addSkillXP(300 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
				} else {
					c.sendMessage("You need a herblore level of 80 to make an Extreme Potion.");
				}
			} else {
				c.sendMessage("You need a Ranging Potion (3) and Clean Irit for this potion.");
				c.sendMessage("When you've got the ingridients, use the Irit on the ranging potion!");
			}
		}
		/** 
		*** Start of saradomin brew fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 6686 && useWith == 6685) { // SARADOMIN BREW FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 6686 && useWith == 6689) { // SARADOMIN BREW FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 6686 && useWith == 6687) { // SARADOMIN BREW FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 6686 && useWith == 6691) { // SARADOMIN BREW FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		/**
		*** End of saradomin brew fix ( Using noted sara brew on non noted to get non noted)
		**/
		/**
		*** Start of PRAYER POT fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 2434 && useWith == 2435) { // PRAYER POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 2434 && useWith == 139) { // PRAYER POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 2434 && useWith == 141) { // PRAYER POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 2434 && useWith == 143) { // PRAYER POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		/**
		*** End of PRAYER POT fix ( Using noted sara brew on non noted to get non noted)
		**/
		/**
		*** Start of super  STRength POT fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 2441 && useWith == 2440) { // STR POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 2441 && useWith == 157) { // STR POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 2441 && useWith == 159) { // STR POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 2441 && useWith == 161) { // STR POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		/**
		*** End of super STRENGTH POTS fix ( Using noted sara brew on non noted to get non noted)
		**/
		/**
		*** Start of super attack pots fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 2437 && useWith == 2436) { // ATTK POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 2437 && useWith == 145) { // ATTK POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 2437 && useWith == 147) { // ATTK POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 2437 && useWith == 149) { // ATTK POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		/**
		*** End of super ATTACK POTS fix ( Using noted sara brew on non noted to get non noted)
		**/
		/**
		*** Start of super DEFENCE pots fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 2443 && useWith == 2442) { // SUPER DEF POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 2443 && useWith == 163) { // SUPER DEF POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 2443 && useWith == 165) { // SUPER DEF POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 2443 && useWith == 167) { // SUPER DEF POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		/**
		*** End of super DEFENCE POTS fix ( Using noted sara brew on non noted to get non noted)
		**/
		/**
		*** Start of ranging pots fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 2445 && useWith == 2444) { // raging POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 2445 && useWith == 169) { // raging POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 2445 && useWith == 171) { // raging POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 2445 && useWith == 173) { // raging POTS FIX
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		/**
		*** End of ranging POTS fix ( Using noted sara brew on non noted to get non noted)
		**/
		/**
		*** Start of super restore pots fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 3025 && useWith == 3024) { // super restore fix
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 3025 && useWith == 3026) { // super restore fix
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 3025 && useWith == 3028) { // super restore fix
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 3025 && useWith == 3030) { // super restore fix
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		/**
		*** End of super restore fix ( Using noted sara brew on non noted to get non noted)
		**/
		/**
		*** Start of magic pots fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 3041 && useWith == 3040) { // magic pot fix
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 3041 && useWith == 3042) { // magic pot fix
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 3041 && useWith == 3044) { // magic pot fix
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		if (itemUsed == 3041 && useWith == 3046) { // magic pot fix
		c.sendMessage("You can't use noted items on non noted items idiot");
		return;
		}
		/**
		*** End of MAGIC POT fix ( Using noted sara brew on non noted to get non noted)
		**/
		if (itemUsed == 269 && useWith == 15309 || itemUsed == 269 && useWith == 15313 || itemUsed == 269 && useWith == 15317 || itemUsed == 269 && useWith == 15321 || itemUsed == 269 && useWith == 15325) {
       		if (c.getItems().playerHasItem(15309, 1) && c.getItems().playerHasItem(15313, 1) && c.getItems().playerHasItem(15317, 1) && c.getItems().playerHasItem(15321, 1)  && c.getItems().playerHasItem(15325, 1)){
            	if (c.playerLevel[c.playerHerblore] >= 96) {
					c.getItems().deleteItem(269, c.getItems().getItemSlot(269),1);
					c.getItems().deleteItem(15309, c.getItems().getItemSlot(15309),1);
					c.getItems().deleteItem(15313, c.getItems().getItemSlot(15313),1);
					c.getItems().deleteItem(15317, c.getItems().getItemSlot(15317),1);
					c.getItems().deleteItem(15321, c.getItems().getItemSlot(15321),1);
					c.getItems().deleteItem(15325, c.getItems().getItemSlot(15325),1);
					c.getItems().addItem(15333,1);
					c.sendMessage("You mix a Overload Potion (3).");
					c.getPA().addSkillXP(500 * Config.HERBLORE_EXPERIENCE, c.playerHerblore);
            	} else {
					c.sendMessage("You need a herblore level of 96 to make that potion.");
            	}
			} else {
				c.sendMessage("You need a clean torstol and all extreme potions (3) to mix a overload.");
				c.sendMessage("Use the clean torstol on any extreme potion to start mixing one!");
			}
        }
		if (itemUsed == 12435 && !c.InDung) { 
			if(c.isDead) {
				c.sendMessage("You can't do this when you're 0 hp...");
				return;
			}
			if (c.hasFollower == 6874) {
				c.hasFollower = 6874;
				c.yak = true;
			} else if(!c.yak) {
				c.sendMessage("Summon a pack yak first..");
				return;
			}
			if(c.gwdelay > 1) {
				c.sendMessage("You must wait 3-4 Minutes before you can use this scroll again.");
				return;
			}
			if(c.gwdelay < 1) {
				c.getItems().bankItem(useWith, c.getItems().getItemSlot(useWith), 1);
				c.getItems().deleteItem(itemUsed, 1);
				c.sendMessage("Your Pack yak sends an item to your bank."); 
				c.gfx0(1316);
				c.startAnimation(7660);
				//c.sendMessage("You can only do this every 5 Minutes!");
				c.gwdelay = 400;
				c.Gwdelay();
			} else {
				c.sendMessage("You must wait 3-4 Minutes before you can use this scroll again.");
			}
		}
		if (itemUsed == 9142 && useWith == 9190 || itemUsed == 9190 && useWith == 9142) {
			if (c.playerLevel[c.playerFletching] >= 58) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9241, boltsMade);
				c.getPA().addSkillXP(boltsMade * 6 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 58 to fletch this item.");
			}
		}
		if (itemUsed == 9143 && useWith == 9191 || itemUsed == 9191 && useWith == 9143) {
			if (c.playerLevel[c.playerFletching] >= 63) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9242, boltsMade);
				c.getPA().addSkillXP(boltsMade * 7 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 63 to fletch this item.");
			}		
		}
		if (itemUsed == 9143 && useWith == 9192 || itemUsed == 9192 && useWith == 9143) {
			if (c.playerLevel[c.playerFletching] >= 65) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9243, boltsMade);
				c.getPA().addSkillXP(boltsMade * 7 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 65 to fletch this item.");
			}		
		}
		if (itemUsed == 9144 && useWith == 9193 || itemUsed == 9193 && useWith == 9144) {
			if (c.playerLevel[c.playerFletching] >= 71) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9244, boltsMade);
				c.getPA().addSkillXP(boltsMade * 10 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 71 to fletch this item.");
			}		
		}
		if (itemUsed == 9144 && useWith == 9194 || itemUsed == 9194 && useWith == 9144) {
			if (c.playerLevel[c.playerFletching] >= 58) {
				int boltsMade = c.getItems().getItemAmount(itemUsed) > c.getItems().getItemAmount(useWith) ? c.getItems().getItemAmount(useWith) : c.getItems().getItemAmount(itemUsed);
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith), boltsMade);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed), boltsMade);
				c.getItems().addItem(9245, boltsMade);
				c.getPA().addSkillXP(boltsMade * 13 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 58 to fletch this item.");
			}		
		}
		if (itemUsed == 1601 && useWith == 1755 || itemUsed == 1755 && useWith == 1601) {
			if (c.playerLevel[c.playerFletching] >= 63) {
				c.getItems().deleteItem(1601, c.getItems().getItemSlot(1601), 1);
				c.getItems().addItem(9192, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 63 to fletch this item.");
			}
		}
		if (itemUsed == 1607 && useWith == 1755 || itemUsed == 1755 && useWith == 1607) {
			if (c.playerLevel[c.playerFletching] >= 65) {
				c.getItems().deleteItem(1607, c.getItems().getItemSlot(1607), 1);
				c.getItems().addItem(9189, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 65 to fletch this item.");
			}
		}
		if (itemUsed == 1605 && useWith == 1755 || itemUsed == 1755 && useWith == 1605) {
			if (c.playerLevel[c.playerFletching] >= 71) {
				c.getItems().deleteItem(1605, c.getItems().getItemSlot(1605), 1);
				c.getItems().addItem(9190, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 71 to fletch this item.");
			}
		}
		if (itemUsed == 1603 && useWith == 1755 || itemUsed == 1755 && useWith == 1603) {
			if (c.playerLevel[c.playerFletching] >= 73) {
				c.getItems().deleteItem(1603, c.getItems().getItemSlot(1603), 1);
				c.getItems().addItem(9191, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 73 to fletch this item.");
			}
		}
		if (itemUsed == 1615 && useWith == 1755 || itemUsed == 1755 && useWith == 1615) {
			if (c.playerLevel[c.playerFletching] >= 73) {
				c.getItems().deleteItem(1615, c.getItems().getItemSlot(1615), 1);
				c.getItems().addItem(9193, 15);
				c.getPA().addSkillXP(8 * Config.FLETCHING_EXPERIENCE, c.playerFletching);
			} else {
				c.sendMessage("You need a fletching level of 73 to fletch this item.");
			}
		}
		if (itemUsed >= 11710 && itemUsed <= 11714 && useWith >= 11710 && useWith <= 11714) {
			if (c.getItems().hasAllShards()) {
				c.getItems().makeBlade();
			}		
		}
		if (itemUsed == 985 && useWith == 987 || itemUsed == 987 && useWith == 985) {
			c.getItems().deleteItem(985, c.getItems().getItemSlot(985),1);
			c.getItems().deleteItem(987, c.getItems().getItemSlot(987),1);
			c.getItems().addItem(989,1);
		}
		if (itemUsed == 2368 && useWith == 2366 || itemUsed == 2366 && useWith == 2368) {
			c.getItems().deleteItem(2368, c.getItems().getItemSlot(2368),1);
			c.getItems().deleteItem(2366, c.getItems().getItemSlot(2366),1);
			c.getItems().addItem(1187,1);
		}
		if (c.getItems().isHilt(itemUsed) || c.getItems().isHilt(useWith)) {
			int hilt = c.getItems().isHilt(itemUsed) ? itemUsed : useWith;
			int blade = c.getItems().isHilt(itemUsed) ? useWith : itemUsed;
			if (blade == 11690) {
				c.getItems().makeGodsword(hilt);
			}
		}
		if (itemUsed == 4151 && useWith == 10531 || itemUsed == 10531 && useWith == 4151) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15444,1);	
		}

		if (itemUsed == 4151 && useWith == 10537 || itemUsed == 10537 && useWith == 4151) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15443,1);	
		}

		if (itemUsed == 4151 && useWith == 10533 || itemUsed == 10533 && useWith == 4151) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15442,1);	
		}

		if (itemUsed == 4151 && useWith == 10534 || itemUsed == 10534 && useWith == 4151) {
			c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
			c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
			c.getItems().addItem(15441,1);	
		}

		if (itemUsed == 11235 && useWith == 10531 || itemUsed == 10531 && useWith == 11235) {
			c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
			c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
			c.getItems().addItem(15704,1);	
		}

		if (itemUsed == 11235 && useWith == 10537 || itemUsed == 10537 && useWith == 11235) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15703,1);	
		}

		if (itemUsed == 3188 && useWith == 15441 || itemUsed == 15441 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		if (itemUsed == 3188 && useWith == 15442 || itemUsed == 15442 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		if (itemUsed == 3188 && useWith == 7783 || itemUsed == 7783 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
                                           if (itemUsed == 3188 && useWith == 15443 || itemUsed == 15443 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		if (itemUsed == 3188 && useWith == 15444 || itemUsed == 15444 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(4151,1);	
		}
		if (itemUsed == 3188 && useWith == 15701 || itemUsed == 15701 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
		if (itemUsed == 3188 && useWith == 15702 || itemUsed == 15702 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
		if (itemUsed == 3188 && useWith == 15703 || itemUsed == 15703 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
		if (itemUsed == 3188 && useWith == 15704 || itemUsed == 15704 && useWith == 3188) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(11235,1);	
		}
		if (itemUsed == 11235 && useWith == 10533 || itemUsed == 10533 && useWith == 11235) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15702,1);	
		}

		if (itemUsed == 11235 && useWith == 10534 || itemUsed == 10534 && useWith == 11235) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(15701,1);	
		}

		if (itemUsed == 13736 && useWith == 13746 || itemUsed == 13746 && useWith == 13736) {
						if (c.playerLevel[c.playerPrayer] >= 90) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(13738,1);	
					} else {
				c.sendMessage("You need a Prayer level of 90 to Make a blessed spiritshield.");
			}
		}	
		if (itemUsed == 13736 && useWith == 13748 || itemUsed == 13748 && useWith == 13736) {
						if (c.playerLevel[c.playerPrayer] >= 90) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(13740,1);	
					} else {
				c.sendMessage("You need a Prayer level of 90 to Make a blessed spiritshield.");
			}
		}	
		if (itemUsed == 13736 && useWith == 13750 || itemUsed == 13750 && useWith == 13736) {
						if (c.playerLevel[c.playerPrayer] >= 90) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(13742,1);	
					} else {
				c.sendMessage("You need a Prayer level of 90 to Make a blessed spiritshield.");
			}
		}	
		if (itemUsed == 13736 && useWith == 13752 || itemUsed == 13752 && useWith == 13736) {
				if (c.playerLevel[c.playerPrayer] >= 90) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(13744,1);
					} else {
				c.sendMessage("You need a Prayer level of 90 to Make a blessed spiritshield.");
			}
		}				
		
		if (itemUsed == 13734 && useWith == 13754 || itemUsed == 13754 && useWith == 13734) {
		if (c.playerLevel[c.playerPrayer] >= 75) {
				c.getItems().deleteItem(useWith, c.getItems().getItemSlot(useWith),1);
				c.getItems().deleteItem(itemUsed, c.getItems().getItemSlot(itemUsed),1);
				c.getItems().addItem(13736,1);	
					} else {
				c.sendMessage("You need a Prayer level of 75 to Make a blessed spiritshield.");
			}
		}
		switch(itemUsed) {
			case 12093 :
				if(useWith == 12093) { // PACK YAK SCROLL MAKING
					c.getItems().deleteItem(12093, 1);
					c.getItems().addItem(12435, 2);
					c.sendMessage("You make 2 Scrolls.");
					return;
				}
			break; 

			case 17413 :
				if(useWith == 4151) { // Aqua whip
					c.getItems().deleteItem(4151, 1);
					c.getItems().deleteItem(17413, 1);
					c.getItems().addItem(7783, 1);
					c.sendMessage("You Pour the special water on the whip! BOOM, boss whip");
					c.gfx0(1316);
					return;
				}
			break; 

			case 4151 :
				if(useWith == 17413) { //Aqua making
					c.getItems().deleteItem(4151, 1);
					c.getItems().deleteItem(17413, 1);
					c.getItems().addItem(7783, 1);
					c.sendMessage("You Pour the special water on the whip! BOOM, boss whip");
					c.gfx0(1316);
					return;
				}
			break; 

			default:
				c.sendMessage("Doesn't Have a case please post on the forum the line below");
				c.sendMessage("Item used ID:"+ itemUsed +" used with ItemID: "+useWith +"     UseItem.ItemOnItem");
			break;
		}	
	}
	
	public static void ItemonNpc(Client c, int itemId, int npcId, int slot) {
		switch(itemId) {
			default:
				c.sendMessage("Doesn't Have a case please post on the forum the line below");
				c.sendMessage("Npc ID:"+ npcId +"  Item ID: "+itemId +"     UseItem.ItemOnNPC");
			break;
		}
		
	}
}
