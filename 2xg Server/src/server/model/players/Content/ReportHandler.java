package server.model.players.Content;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import server.model.players.Client;
import server.util.Misc;

/**
 * 
 * @author
 * 
 */
public class ReportHandler {

	public static String[] savedNames = new String[500];
	public static String[] savedSpeach = new String[500];
	public static String[] savedTimes = new String[500];

	public static final String[] reportNames = { "Offensive language",
			"Item scamming", "Password scamming", "Bug abuse",
			"Staff impersonation", "Other", "Macroing", "Duping",
			"Encouraging others to break the rules", "Yell abuse",
			"Advertising", "Possible duped items" };

	public static void addText(String name, byte[] data, int dataLength) {
		for (int i = 499; i > 0; i--) {
			savedNames[i] = savedNames[i - 1];
			savedSpeach[i] = savedSpeach[i - 1];
			savedTimes[i] = savedTimes[i - 1];
		}
		savedNames[0] = name;
		savedSpeach[0] = Misc.textUnpack(data, dataLength);
		String minute = new SimpleDateFormat("mm").format(new Date());
		String second = new SimpleDateFormat("ss").format(new Date());
		String hour = new SimpleDateFormat("hh").format(new Date());
		savedTimes[0] = hour + ":" + minute + ":" + second;
	}

	public static void handleReport(Client c) throws Exception {
		String player = Misc.longToReportPlayerName(c.inStream.readQWord2());
		player = player.replaceAll("_", " ");
		byte rule = (byte) c.inStream.readUnsignedByte();
		if (c.lastReported.equalsIgnoreCase(player)
				&& (System.currentTimeMillis() - c.lastReport) < 60000) {
			c.sendMessage("You can only report a player once every 60 seconds.");
			return;
		}
		if (c.playerName.equalsIgnoreCase(player)) {
			c.sendMessage("You cannot report yourself!");
			return;
		}
		if (hasSpoke(player)) {
			String sendText = "";

			for (int i = 499; i > 0; i--) {
				if (savedNames[i] != null) {
					if (savedNames[i].equalsIgnoreCase(c.playerName)
							|| savedNames[i].equalsIgnoreCase(player)) {
						sendText += " -[" + savedTimes[i] + ": "
								+ savedNames[i] + "]: " + savedSpeach[i]
								+ "\r\n";
					}
				}
			}

			sendText = sendText.replaceAll("'", " ");
			String month = getMonth(new SimpleDateFormat("MM")
					.format(new Date()));
			String day = new SimpleDateFormat("dd").format(new Date());
			// Statement statement = Server.conn.createStatement();
			writeReport(player + " was reported by " + c.playerName + ", "
					+ reportNames[rule] + ", " + month + ", " + day + "",
					sendText + ".", reportNames[rule]);
			c.sendMessage("Thank you, your report has been received and will be reviewed.");
			c.lastReported = player;
			c.lastReport = System.currentTimeMillis();
			return;
		} else {
			c.sendMessage("You can only report someone who has spoken in the last 60 seconds.");
			return;
		}
	}

	public static void writeReport(String data, String text, String file) {
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("./data/reports/" + file
					+ ".txt", true));
			bw.write(data);
			bw.newLine();
			bw.write(text);
			bw.newLine();
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					System.out.println("Error writing system log.");
					ioe2.printStackTrace();
				}
		}

	}

	public static void writeLog(String text, String file, String dir) {
		// used for bans/mutes/chatlogs etc. -bakatool
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(dir + file + ".txt", true));
			bw.write(text);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					System.out.println("Error writing system log.");
					// ioe2.printStackTrace();
				}
		}
	}

	public static boolean hasSpoke(String s) {
		for (int i = 0; i < 500; i++) {
			if (savedNames[i] != null) {
				if (savedNames[i].equalsIgnoreCase(s))
					return true;
			}
		}
		return false;
	}

	public static String getMonth(String s) {
		try {
			int i = Integer.parseInt(s);
			String[] months = { "", "January", "February", "March", "April",
					"May", "June", "July", "August", "September", "October",
					"November", "December" };
			return months[i];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Unknown";
	}
}