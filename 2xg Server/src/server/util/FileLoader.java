package server.util;

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileLoader{

	public int numberOfRewards = 1;
	//public int[] amountOfItems;
	//public int[] rewardRarity;
	public int[][] casketDrops;

	public FileLoader(){
		loadClueRewards();
		System.out.println("[FileLoader Loaded]");
	}

	public void loadClueRewards(){
		try{
			File f = new File("./deps/Data/cfg/ClueRewards.TSM");	// TODO deps stuff
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String line = s.nextLine().trim();
				if (line.startsWith("#")) //if it finds a line that begins with a # it will skip that line, its like a comment
					continue;
				StringTokenizer normalTok = new StringTokenizer(line, "\t"); //every tab it will take in the next variable
				casketDrops = new int[normalTok.countTokens()][2];
				int count = 0; //creates the int count
				while (normalTok.hasMoreTokens()) { //separates the rest of the pairs
					String[] temp = normalTok.nextToken().split(":"); //splits them
					casketDrops[count][0] = Integer.parseInt(temp[0]); //parses first one and puts it into the huge array
					casketDrops[count][1] = Integer.parseInt(temp[1]); //parses second one and puts it into the huge array
					count++; //its the i for this while loop
				}
			}
			s.close();
		} catch (Exception e) {
			Utilitys.saveStack(Utilitys.exceptionToString(e.getStackTrace()), e);
			System.out.println("debug: problem at loading clue Rewards");
		}
	}
	
	//public void loadHighscores im working on this
	
	/*public void loadClueRewards2(){ //doesnt work started never finished
		try{
			File f = new File("./deps/Data/cfg/ClueRewards.TSM");
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				if (line.startsWith("#")) //if it finds a line that begins with a # it will skip that line, its like a comment
					continue;
				else{
					numberOfRewards++;
					int count = 0;
					while (s.hasNextLine()) {
						String line = s.nextLine();
						if (line.startsWith("#")) //if it finds a line that begins with a # it will skip that line, its like a comment
							continue;
						StringTokenizer tok = new StringTokenizer(line, "\t"); //every tab it will take in the next variable
						amountOfItems[count] = tok.countTokens();
					}
				}
			}
			casketDrops = new int[numberOfRewards];
			rewardRarity = new int[numberOfRewards];
			f = new File("./deps/Data/cfg/ClueRewards.TSM");
			s = new Scanner(f);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				if (line.startsWith("#")) //if it finds a line that begins with a # it will skip that line, its like a comment
					continue;
				StringTokenizer normalTok = new StringTokenizer(line, "\t"); //every tab it will take in the next variable
				String[] information = normalTok.nextToken().split(":"); //separates first token by the : inbetween the ints
				int clueId = Integer.parseInt(information[0]); //takes the first String parses it and makes it the clueId
				rewardRarity[clueId] = Integer.parseInt(information[1]); //parses the then second String and puts it in the array
				casketDrops[clueId][0] = new int[normalTok.countTokens()][2]; //creates a new int[][] from the normal array
				int count = 0; //creates the int count
				while (normalTok.hasMoreTokens()) { //separates the rest of the pairs
					String[] temp = normalTok.nextToken().split(":"); //splits them
					casketDrops[clueId][count][0] = Integer.parseInt(temp[0]); //parses first one and puts it into the huge array
					casketDrops[clueId][count][1] = Integer.parseInt(temp[1]); //parses second one and puts it into the huge array
					count++; //its the i for this while loop
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("debug: problem at loading clue Rewards");
		}
	}*/
}