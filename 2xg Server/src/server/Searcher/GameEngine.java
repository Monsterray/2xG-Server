package server;

import server.model.players.Client;
import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.PlayerSave;
import server.model.players.Content.RequestHelp;
import server.model.players.Content.LoginMessages;

public class GameEngine extends Server {
	public static boolean ServerRunning = true;
	
	/**
	 * Constructor for GameEngine, doesnt do anything but notify that has been started.
	 */
	public GameEngine() {
		//Server.pln("Initialized GameEngine");
	}
	
	/**
	 * Method that is running all the time that the server is on
	 */
	public void run() {
		try {
			if (sleepTime > 0){
				Thread.sleep(sleepTime);
			}
			/*if(logoutRefreshIn == 0){
			
			}else{
				if(System.currentTimeMillis() - logoutRefreshIn > 5000){
					for(Player p : PlayerHandler.players) {
						if(p == null)
							continue;						
						RequestHelp.sendOnlineStaff((Client)p);
						LoginMessages.tabLogout((Client)p);
					}
				}
			}*/
			engineTimer.reset();
			itemHandler.process();
			playerHandler.process();	
			npcHandler.process();
			shopHandler.process();
			objectManager.process();
			Server.pestControl.gameProcess();
			cycleTime = engineTimer.elapsed();
			if(cycleTime < 575){
				sleepTime = cycleRate - cycleTime;
			}else{
				sleepTime = 0;
			}
			totalCycleTime += cycleTime;
			cycles++;
			debug();
			if(Config.SERVER_DEBUG)
				secundes++;
			if(secundes == 120){
				minutes++;
				secundes = 0;
			}
			if(minutes == 60){
				hours++;
				minutes = 0;
			}
			if(hours == 24){
				days++;
				hours = 0;
			}
			if(hours == 24 && minutes == 0 && secundes == 20){
				PlayerHandler.updateSeconds = 3600;
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}
			while (Server.shutdownServer) {
				if(Config.DEBUG)
				Server.acceptor = null;
				Server.connectionHandler = null;
				Server.sac = null;
				stop();
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			for(Player p : PlayerHandler.players) {
				if(p == null)
					continue;						
				PlayerSave.saveGame((Client)p);
				System.out.println("Saved game for " + p.playerName + ".");
			}
			Server.shutdownServer = true;
		}
	}
	
	/**
	 * Method that is called when the server is stoped.
	 */
	public static void stop(){
		if(Config.DEBUG)
			System.out.println("Stop Method");
		for(Player p : PlayerHandler.players) {
			if(p == null)
				continue;						
			PlayerSave.saveGame((Client)p);
			System.out.println("Saved game for " + p.playerName + ".");
		}
		System.exit(0);
	}
}