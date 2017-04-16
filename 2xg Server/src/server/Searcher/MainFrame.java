import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.*;

public class MainFrame{
	public String path = "./";
	public SearchFrame framer;

	public static void main(String[] paramArrayOfString){
		new MainFrame(paramArrayOfString);
	}
	
	public MainFrame(){
	
	}
	
	public MainFrame(String[] args){
		framer = new SearchFrame("Search files", this);
		String s1 = JOptionPane.showInputDialog(framer, "Choose search Path (Eg: ./ for this folder)", "Search Path Input", 3);
		if((s1 != null) && (s1 != "") && (s1.equals("") != true))
			path = s1;
	}
	
	public void action(ActionEvent paramActionEvent){
		try{
			if ((paramActionEvent.getSource() == framer.searchText) || 
				(paramActionEvent.getSource() == framer.search)) {
				framer.messages.setText("");
				searchFiles(framer.searchText.getText());
			}
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
	}

	public void searchFiles(String paramString){
		File localFile;
		try{
			localFile = new File(path);
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
		String[] arrayOfString = localFile.list();
		long l1 = 0L;
		for (int i = 0; i < arrayOfString.length; i++){
			if ((!arrayOfString[i].endsWith(".zip")) && (!arrayOfString[i].endsWith(".rar")) && (!arrayOfString[i].endsWith(".gz"))  && (!arrayOfString[i].endsWith(".jar")) && (!arrayOfString[i].endsWith(".7z"))) {
				try{
					long l2 = 0L;
					BufferedReader localBufferedReader = new BufferedReader(new FileReader(arrayOfString[i]));
					BufferedReader lbr = new BufferedReader(new FileReader(arrayOfString[i]));
					String str = null;
					while ((str = localBufferedReader.readLine()) != null){
						l2 += 1L;
						if (str.contains(paramString)){
							l1 += 1L;
							framer.addMessage("[" + l1 + "] " + arrayOfString[i] + " (Line " + l2 + ")");
							framer.addMessage("    "+ str.replaceAll("\t", ""));
							framer.addMessage("");
						}
					}
				}catch (IOException e){
					System.out.println("Critical error while opening file for search!");
				}
			}
		}
		framer.addMessage("");
		framer.addMessage("");
		framer.addMessage("    " + l1 + " results for \"" + paramString + "\" at \""+ path +"\"");
	}
}