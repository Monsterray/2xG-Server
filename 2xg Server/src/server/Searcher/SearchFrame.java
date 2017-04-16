import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SearchFrame extends JFrame implements ActionListener{
	private MainFrame mFrame;
	public JMenuBar toolBar;
	
	public JFrame jframe;
	public JTextArea messages;
	public JTextField searchText;
	public JButton search;
	
	public JPanel layout;
	public JPanel layoutU;
	public JPanel layoutUL;
	public JPanel layoutD;
	public JLabel searchLabel;
	
	public JScrollPane scroll;

	public SearchFrame(String title, MainFrame mFramer){
		mFrame = mFramer;
		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe = new JFrame(title);
		jframe.setDefaultCloseOperation(3);
		setComponets();
		setComponetSizes();
		setComponetExtras();
		addComponetListeners();
		addComponets();
		jframe.pack();
		jframe.setVisible(true);
	}

	public JButton createButton(String buttonName, String buttonCommandName){
		JButton button = new JButton(buttonName);
		button.setActionCommand(buttonCommandName);
		button.addActionListener(this);
		return button;
	}
	
	public JButton createButton(String buttonName){
		JButton button = new JButton(buttonName);
		button.setActionCommand(buttonName);
		button.addActionListener(this);
		return button;
	}
	
	public JMenu createMenuTab(String tabName, String[] subTabNames){
		try {
			JMenu Menu = new JMenu(tabName);
			for (String name : subTabNames) {
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-"))
					Menu.addSeparator();
				else {
					menuItem.addActionListener(this);
					Menu.add(menuItem);
				}
			}
			return Menu;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setComponets(){
		toolBar = new JMenuBar();
		layout = new JPanel(new BorderLayout());
		layoutU = new JPanel(new BorderLayout());
		layoutUL = new JPanel(new BorderLayout());
		layoutD = new JPanel(new BorderLayout());
		
		searchLabel = new JLabel(" Search for: ");
		searchText = new JTextField();
		search = new JButton(" Search ");
		
		JButton pathButton = createButton("Change Path", "pathButton");
		toolBar.add(pathButton);
		
		messages = new JTextArea(20, 30);
		scroll = new JScrollPane(messages, 22, 31);
	}

	public void setComponetSizes(){
		searchText.setPreferredSize(new Dimension(500, 15));
		scroll.setPreferredSize(new Dimension(750, 450));
	}

	public void setComponetExtras(){
		scroll.setHorizontalScrollBarPolicy(32);
		scroll.setVerticalScrollBarPolicy(22);
		
		messages.setFont(new Font("Arial Bold", 0, 14));
		messages.setRows(7);
		messages.setForeground(new Color(100, 100, 255));
		messages.setBackground(new Color(0, 0, 0));
	}

	public void addComponetListeners(){
		searchText.addActionListener(this);
		search.addActionListener(this);
	}

	public void addComponets(){
		jframe.add(layout);
		jframe.add(toolBar, "North");
		
		layout.add(layoutU, "North");
		
		layoutU.add(layoutUL, "West");
		
		layoutUL.add(searchLabel, "West");
		layoutUL.add(searchText, "Center");
		layoutUL.add(search, "East");
		
		layout.add(layoutD, "South");
		
		layoutD.add(scroll, "South");
	}

	public void addMessage(String paramString){
		messages.append(paramString + "\n");
		scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
	}

	public void actionPerformed(ActionEvent evt){
		mFrame.action(evt);
		String cmd = evt.getActionCommand();
		switch(cmd){
			case "pathButton":
				String input = JOptionPane.showInputDialog(mFrame.framer, "Choose search Path (Eg: ./ for this folder)", "Search Path Input", 3);
				if(input != null)
					mFrame.path = input;
			break;
			
			default:
				System.out.println("You clicked: "+ cmd);
			break;
		}
	}
}