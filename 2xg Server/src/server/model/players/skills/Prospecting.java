package server.model.players.skills;

import server.Server;
import server.model.players.Client;
import server.task.Task;

/**
* Prospecting action. Part of the mining skill.
* @author 
*/
public class Prospecting {
	
	/**
	 * Prospects the rock.
	 * @param c The client class.
	 * @param itemId The name of the item within the object.
	 */
	public void prospectRock(final Client c, final String itemName) {
		c.sendMessage("You examine the rock for ores...");
       Server.getTaskScheduler().addEvent(new Task(5, false) {
		public void execute() {
				c.sendMessage("This rock contains "+itemName+".");
				this.stop();
			}
			
		});
	}
}