package server;

import server.Config;
import server.clip.region.Region;
import server.clip.region.ObjectDef;
import server.util.log.Logger;
import server.util.ShutDownHook;
import server.util.SimpleTimer;
import server.util.MadTurnipConnection;
import server.util.FileLoader;
//import server.util.SQL;
//import server.util.LinkedList;
import server.net.ConnectionHandler;
import server.net.ConnectionThrottleFilter;
//import server.world.InfractionManager;
import server.world.map.*;
import server.world.ItemHandler;
import server.world.ObjectHandler;
import server.world.ObjectManager;
import server.world.ShopHandler;
import server.world.ClanChatHandler;
import server.world.WorldMap;
import server.model.minigames.*;
import server.model.npcs.NPCHandler;
import server.model.objects.Objects;
import server.model.players.Client;
import server.model.players.PlayerSave;
import server.model.players.PlayerHandler;
import server.model.players.Player;
import server.model.shops.*;
import server.task.Task;
import server.task.TaskScheduler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.DecimalFormat;

import org.apache.mina.common.IoAcceptor;
import org.apache.mina.transport.socket.nio.SocketAcceptor;
import org.apache.mina.transport.socket.nio.SocketAcceptorConfig;

/**
 * Server.java
 *
 * @author 
 *
 */

public class Server {
//Monsterrays Variables
	public static long serverStarted = System.currentTimeMillis(); //needs to stay all the way at the top!!!!
	public static GELoader geLoader = new GELoader();
	public static FileLoader fileLoader = new FileLoader();
	
	public static boolean sleeping;
	public static int cycleRate;
	public static boolean UpdateServer = false;
	public static long lastMassSave = System.currentTimeMillis();
	public static IoAcceptor acceptor;
	public static SocketAcceptorConfig sac = new SocketAcceptorConfig();
	public static ConnectionHandler connectionHandler;
	private static ConnectionThrottleFilter throttleFilter;
	public static SimpleTimer engineTimer, debugTimer;
	public static long cycleTime, cycles, totalCycleTime, sleepTime;
	private static DecimalFormat debugPercentFormat;
	public static boolean shutdownServer = false;		
	public static boolean shutdownClientHandler;
	public static ItemHandler itemHandler = new ItemHandler();
	//public static LinkedList singleStarter = new LinkedList();
	public static PlayerHandler playerHandler = new PlayerHandler();
	public static BarbarianDefence barbDefence = new BarbarianDefence();
	public static NPCHandler npcHandler = new NPCHandler();
	public static ShopHandler shopHandler = new ShopHandler();
	public static ObjectHandler objectHandler = new ObjectHandler();
	//public static InfractionManager infractionManager = new InfractionManager();
	public static ObjectManager objectManager = new ObjectManager();
	public static CastleWars castleWars = new CastleWars();
	public static FightPits fightPits = new FightPits();
	public static PestControl pestControl = new PestControl();
	public static int days, hours, minutes, secundes;
	public static ClanChatHandler clanChat = new ClanChatHandler();
	public static FightCaves fightCaves = new FightCaves();
	public static HardCaves hardCaves = new HardCaves();
	public static Nomad Nomad = new Nomad();
	public static Elvarg Elvarg = new Elvarg();
	public static Goblin Goblin = new Goblin();
	public static RFD rfd = new RFD();
	//public static WorldMap worldMap = new WorldMap();
	public static long[] TIMES = new long[5];
	
	public static void shutdown() {
		shutdownServer = true;
		System.exit(0);
    }
     /**
	 * The task scheduler.
	 */
	private static final TaskScheduler scheduler = new TaskScheduler();

	/**
	 * Gets the task scheduler.
	 * @return The task scheduler.
	 */
	public static TaskScheduler getTaskScheduler() {
		return scheduler;
	}
	//private static final WorkerThread engine = new WorkerThread();
	
	static {
		cycleRate = 474;
		shutdownServer = false;
		engineTimer = new SimpleTimer();
		debugTimer = new SimpleTimer();
		sleepTime = 0;
		debugPercentFormat = new DecimalFormat("0.0#%");
	}
	
	//height,absX,absY,toAbsX,toAbsY,type
    /*public static final boolean checkPos(int height,int absX,int absY,int toAbsX,int toAbsY,int type)
    {
        return I.I(height,absX,absY,toAbsX,toAbsY,type);
    }*/
	
	public static void main(java.lang.String args[]) throws NullPointerException, IOException {
		System.setOut(new Logger(System.out));
		System.setErr(new Logger(System.err, true));
		acceptor = new SocketAcceptor();
		connectionHandler = new ConnectionHandler();
		sac.getSessionConfig().setTcpNoDelay(false);
		sac.setReuseAddress(true);
		sac.setBacklog(100);
		throttleFilter = new ConnectionThrottleFilter(Config.CONNECTION_DELAY);
		sac.getFilterChain().addFirst("throttleFilter", throttleFilter);
		acceptor.bind(new InetSocketAddress(Config.serverlistenerPort), connectionHandler, sac);
		System.out.println("[Server] Listening on port: "+ Config.serverlistenerPort);
		Connection.initialize();
		CastleWars.process();
		ObjectDef.loadConfig();
		Region.load();
		System.gc();
		
		/**
			* Main Server Tick
		 */
		System.out.println("[Server] Loaded in: "+ (System.currentTimeMillis() - serverStarted) +" Milliseconds");
		System.out.println("[Server] Loaded in: "+ ((System.currentTimeMillis() - serverStarted) / 1000) +" Seconds");
		//System.out.println("[Server] Loaded in: "+ (float)(((System.currentTimeMillis() - serverStarted) / 1000) / 60) +" Minutes");
		final GameEngine engine = new GameEngine();
		scheduler.schedule(new Task() {
			@Override
			public void execute() {
				engine.run();
			}
			public void stop(){
				engine.stop();
			}
		});
		while (Server.shutdownServer) {
			pln("Inside the server while");
			acceptor = null;
			connectionHandler = null;
			sac = null;
			System.exit(0);
			break;
		}
	}
	
	public static boolean playerExecuted = false;
	
	public static void debug() {
		if (debugTimer.elapsed() > 600*1000 || playerExecuted) {
			long averageCycleTime = totalCycleTime / cycles;
			System.out.println("Average Cycle Time: " + averageCycleTime + "ms");
			double engineLoad = ((double) averageCycleTime / (double) cycleRate);
			System.out.println("Players online: " + PlayerHandler.getPlayerCount()+ ", engine load: "+ debugPercentFormat.format(engineLoad));
			totalCycleTime = 0;
			cycles = 0;
			System.gc();
			System.runFinalization();
			debugTimer.reset();
			playerExecuted = false;
		}
	}
	
	public static void debug2() {
		long averageCycleTime = totalCycleTime / cycles;
		System.out.println("Average Cycle Time: " + averageCycleTime + "ms");
		double engineLoad = ((double) averageCycleTime / (double) cycleRate);
		System.out.println("Players online: " + PlayerHandler.getPlayerCount()+ ", engine load: "+ debugPercentFormat.format(engineLoad));
		totalCycleTime = 0;
		cycles = 0;
		System.gc();
		System.runFinalization();
		debugTimer.reset();
		playerExecuted = false;
	}
	
	public static long getSleepTimer() {
		return sleepTime;
	}
	
	public static void printErr(String input){
		System.err.println(input);
	}
	
	public static void pln(String input){
		System.out.println(input);
	}
}
