package server.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Utilitys{

	public Utilitys(){
		
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
						stackError.write("Unknown Reason For Failure!");
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
		} finally {
			//stackError.close();
		}
	}
}