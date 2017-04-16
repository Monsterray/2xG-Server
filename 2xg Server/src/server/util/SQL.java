package server.util;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.model.players.Client;

public class SQL {

	public static Connection con = null;
	public static Statement stmt;
	public static boolean connectionMade;
	
	public static void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://SITENAME:3306/DATABASE","USERNAME","USERPASS");
			stmt = con.createStatement();
		} catch (Exception e) {
			String[] errorStack = exceptionToString(e.getStackTrace());
			saveStack(errorStack, e);
			System.out.println("Problem at SQL createConnection");
		}
	}
	
	public static ResultSet query(String s) throws SQLException {
		try {
			if (s.toLowerCase().startsWith("select")) {
				ResultSet rs = stmt.executeQuery(s);
				return rs;
			} else {
				stmt.executeUpdate(s);
			}
			return null;
		} catch (Exception e) {
			destroyConnection();
			createConnection();
			String[] errorStack = exceptionToString(e.getStackTrace());
			saveStack(errorStack, e);
			System.out.println("Problem at SQL query");
		}
		return null;
	}

	public static void destroyConnection() {
		try {
			stmt.close();
			con.close();
			connectionMade = false;
		} catch (Exception e) {
			String[] errorStack = exceptionToString(e.getStackTrace());
			saveStack(errorStack, e);
			System.out.println("Problem at SQL destroyConnection");
		}
	}

	public static boolean saveHighScores(Client clientToSave) {
		try {
			query("DELETE FROM `skills` WHERE playerName = '"+clientToSave.playerName+"';");
			query("DELETE FROM `skillsoverall` WHERE playerName = '"+clientToSave.playerName+"';");
			query("INSERT INTO `skills` (`playerName`,`Attacklvl`,`Attackxp`,`Defencelvl`,`Defencexp`,`Strengthlvl`,`Strengthxp`,`Hitpointslvl`,`Hitpointsxp`,`Rangelvl`,`Rangexp`,`Prayerlvl`,`Prayerxp`,`Magiclvl`,`Magicxp`,`Cookinglvl`,`Cookingxp`,`Woodcuttinglvl`,`Woodcuttingxp`,`Fletchinglvl`,`Fletchingxp`,`Fishinglvl`,`Fishingxp`,`Firemakinglvl`,`Firemakingxp`,`Craftinglvl`,`Craftingxp`,`Smithinglvl`,`Smithingxp`,`Mininglvl`,`Miningxp`,`Herblorelvl`,`Herblorexp`,`Agilitylvl`,`Agilityxp`,`Thievinglvl`,`Thievingxp`,`Slayerlvl`,`Slayerxp`,`Farminglvl`,`Farmingxp`,`Runecraftlvl`,`Runecraftxp`) VALUES ('"+clientToSave.playerName+"',"+clientToSave.playerLevel[0]+","+clientToSave.playerXP[0]+","+clientToSave.playerLevel[1]+","+clientToSave.playerXP[1]+","+clientToSave.playerLevel[2]+","+clientToSave.playerXP[2]+","+clientToSave.playerLevel[3]+","+clientToSave.playerXP[3]+","+clientToSave.playerLevel[4]+","+clientToSave.playerXP[4]+","+clientToSave.playerLevel[5]+","+clientToSave.playerXP[5]+","+clientToSave.playerLevel[6]+","+clientToSave.playerXP[6]+","+clientToSave.playerLevel[7]+","+clientToSave.playerXP[7]+","+clientToSave.playerLevel[8]+","+clientToSave.playerXP[8]+","+clientToSave.playerLevel[9]+","+clientToSave.playerXP[9]+","+clientToSave.playerLevel[10]+","+clientToSave.playerXP[10]+","+clientToSave.playerLevel[11]+","+clientToSave.playerXP[11]+","+clientToSave.playerLevel[12]+","+clientToSave.playerXP[12]+","+clientToSave.playerLevel[13]+","+clientToSave.playerXP[13]+","+clientToSave.playerLevel[14]+","+clientToSave.playerXP[14]+","+clientToSave.playerLevel[15]+","+clientToSave.playerXP[15]+","+clientToSave.playerLevel[16]+","+clientToSave.playerXP[16]+","+clientToSave.playerLevel[17]+","+clientToSave.playerXP[17]+","+clientToSave.playerLevel[18]+","+clientToSave.playerXP[18]+","+clientToSave.playerLevel[19]+","+clientToSave.playerXP[19]+","+clientToSave.playerLevel[20]+","+clientToSave.playerXP[20]+");");
			query("INSERT INTO `skillsoverall` (`playerName`,`lvl`,`xp`) VALUES ('"+clientToSave.playerName+"',"+(clientToSave.getLevelForXP(clientToSave.playerXP[0]) + clientToSave.getLevelForXP(clientToSave.playerXP[1]) + clientToSave.getLevelForXP(clientToSave.playerXP[2]) + clientToSave.getLevelForXP(clientToSave.playerXP[3]) + clientToSave.getLevelForXP(clientToSave.playerXP[4]) + clientToSave.getLevelForXP(clientToSave.playerXP[5]) + clientToSave.getLevelForXP(clientToSave.playerXP[6]) + clientToSave.getLevelForXP(clientToSave.playerXP[7]) + clientToSave.getLevelForXP(clientToSave.playerXP[8]) + clientToSave.getLevelForXP(clientToSave.playerXP[9]) + clientToSave.getLevelForXP(clientToSave.playerXP[10]) + clientToSave.getLevelForXP(clientToSave.playerXP[11]) + clientToSave.getLevelForXP(clientToSave.playerXP[12]) + clientToSave.getLevelForXP(clientToSave.playerXP[13]) + clientToSave.getLevelForXP(clientToSave.playerXP[14]) + clientToSave.getLevelForXP(clientToSave.playerXP[15]) + clientToSave.getLevelForXP(clientToSave.playerXP[16]) + clientToSave.getLevelForXP(clientToSave.playerXP[17]) + clientToSave.getLevelForXP(clientToSave.playerXP[18]) + clientToSave.getLevelForXP(clientToSave.playerXP[19]) + clientToSave.getLevelForXP(clientToSave.playerXP[20]))+","+((clientToSave.playerXP[0]) + (clientToSave.playerXP[1]) + (clientToSave.playerXP[2]) + (clientToSave.playerXP[3]) + (clientToSave.playerXP[4]) + (clientToSave.playerXP[5]) + (clientToSave.playerXP[6]) + (clientToSave.playerXP[7]) + (clientToSave.playerXP[8]) + (clientToSave.playerXP[9]) + (clientToSave.playerXP[10]) + (clientToSave.playerXP[11]) + (clientToSave.playerXP[12]) + (clientToSave.playerXP[13]) + (clientToSave.playerXP[14]) + (clientToSave.playerXP[15]) + (clientToSave.playerXP[16]) + (clientToSave.playerXP[17]) + (clientToSave.playerXP[18]) + (clientToSave.playerXP[19]) + (clientToSave.playerXP[20]))+");");
		} catch (Exception e) {
			String[] errorStack = exceptionToString(e.getStackTrace());
			saveStack(errorStack, e);
			System.out.println("Problem at SQL saveHighScores");
			return false;
		}
		return true;
	}
	
	public static String[] exceptionToString(StackTraceElement[] stack){
		String[] output = new String[stack.length];
		for(int i = 0; i <= stack.length-1; i++){
			output[i] = stack[i].toString();
		}
		return output;
	}
	
	public static void saveStack(String[] stack, Exception e1){
		//DateFormat dateFormat = new SimpleDateFormat();
		//Date cachedDate = new Date();
		//String date = dateFormat.format(cachedDate);
		BufferedWriter stackError = null;
		try {
			stackError = new BufferedWriter(new FileWriter("./errors/errors.err", true));
			for(int i = 0; i <= stack.length-1; i++){
				if(i == 0){
					try{
						stackError.write(e1.getMessage());
					}catch(Exception e){
						System.out.println("ITS NULL");
					}
				}
				stackError.newLine();
				stackError.write("\t"+ stack[i]);
			}
			stackError.write("\n");
			stackError.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[ERROR] Problem in saveing a stack trace!");
		}finally {
			//stackError.close();
		}
	}
}