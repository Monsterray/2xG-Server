import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import server.model.players.Client;
import server.model.players.Player;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Future;
import java.net.URL;
import java.net.MalformedURLException;
import server.Connection;
import server.model.players.packets.ClickItem;
import java.io.InputStreamReader;
import server.util.SQL;
import server.util.MadTurnipConnection;
import server.model.players.*;
import server.model.players.skills.Dungeoneering;
import java.io.BufferedReader;
import server.model.players.Content.*;
import server.model.players.skills.Prayer;
import server.model.minigames.CastleWars;
import java.io.IOException;
import server.model.minigames.*;
import org.apache.mina.common.IoSession;
import server.Config;
import server.Server;
import java.net.URL;
import server.model.npcs.*;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import server.model.items.Banking;
import java.io.IOException;
import server.model.players.BankPin;
import server.model.items.ItemAssistant;
import server.model.shops.ShopAssistant;
import server.net.HostList;
import server.net.Packet;
import server.net.StaticPacketBuilder;
import server.util.Misc;
import server.model.players.skills.Summoning;
import server.util.Stream;
import server.model.players.Content.RequestHelp;
import server.model.players.skills.*;
import server.model.players.skills.FlaxStringer;
import server.model.players.PlayerSave;
import server.model.players.PlayerHandler;
import server.model.minigames.WarriorsGuild;
import server.model.minigames.PestControl;
import server.model.minigames.Gambling;
import server.model.minigames.Nomad;
import server.model.minigames.Elvarg;
import server.model.players.QuickCurses;
import server.model.players.QuickPrayer;
import server.task.Task;

public class EconomyReset{

	private static File charDir = new File("Data/characters/");

	public static void main(String[] args){
		if(charDir.exists() && charDir.isDirectory()){
			File[] charFiles = charDir.listFiles();
			for(int i = 0; i < charFiles.length; i++){
				resetEcoChar(charFiles[i]);
				System.out.println("Reset the Player Economy for: "+charFiles[i].toString());
			}
		}
	}

	private static void resetEcoChar(File charFile){
		try{
			String cheatStatus, tempData, tempAdd = ""; int curEquip = 0, curItem = 0, curBank = 0;
			File tempCharFile = new File(charDir.toString()+"ECOBOOST$TEMP");
			DataInputStream fileStream = new DataInputStream(new FileInputStream(charFile));
			BufferedWriter tempOut = new BufferedWriter(new FileWriter(tempCharFile));
			while((tempData = fileStream.readLine()) != null){
				if((!tempData.trim().startsWith("character-item =")) && (!tempData.trim().startsWith("character-bank ="))){
					tempAdd = tempData.trim();
					if(tempData.trim().startsWith("character-equip =")) {
						tempAdd = "character-equip = "+curEquip+"\t-1\t0";
						curEquip++;
					}
					tempOut.write(tempAdd); tempOut.newLine();
				}
			}
			fileStream.close(); tempOut.close();
			charFile.delete();
			tempCharFile.renameTo(charFile);
		}catch(Exception e) { 
			e.printStackTrace(); 
		}
	}
}