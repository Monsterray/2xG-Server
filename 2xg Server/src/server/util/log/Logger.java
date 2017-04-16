package server.util.log;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import server.util.SimpleTimer;

/**
 * 
 * @author
 */
public class Logger extends PrintStream {

	private DateFormat dateFormat = new SimpleDateFormat();
	private Date cachedDate = new Date();
	private SimpleTimer refreshTimer = new SimpleTimer();

	public Logger(PrintStream out) {
		super(out);
	}
	
	public Logger(PrintStream out, boolean isError) {
		super(out);
	}

	@Override
	public void print(String str) {
		if (str.startsWith("debug:"))
			super.print("[" + getPrefix() + "] DEBUG: " + str.substring(6));
		else if(str.startsWith("[Server]"))
			super.print(str);
		else if(str.startsWith("[ERROR]"))
			super.print(str);
		else
			super.print("[" + getPrefix() + "]: " + str);
	}

	public String getPrefix() {
		if (refreshTimer.elapsed() > 1000) {
			refreshTimer.reset();
			cachedDate = new Date();
		}
		return dateFormat.format(cachedDate);
	}
}
