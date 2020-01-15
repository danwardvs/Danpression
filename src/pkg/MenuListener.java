package pkg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuListener implements ActionListener{

	private String componentID;//your own unique way of identifying components that will be attached
	//to the A05Listener objects. This way you will be able to distinguish that which component has
	//invoked the action handler.
	private static Menu menuReference;
	
	/**
	 * Set reference to the main window so the actionlistener can call methods from there
	 * @param newHub reference to main window
	 */
	public static void setMenuReference(Menu newMenu) {
		menuReference = newMenu;
	}
	/**
	 * Constructor for A05Listener
	 * @param arg title of button to see what button is pressed
	 */
	public MenuListener(String arg) {//DO NOT modify this line
		componentID = arg;//DO NOT modify this line
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(componentID.equals("sourceButton")) {
			menuReference.loadSource();
		}
		if(componentID.equals("destButton")) {
			menuReference.loadDest();
		}
		if(componentID.equals("encodeButton")) {
			menuReference.encodeFile();
		}
		if(componentID.equals("viewButton")) {
			menuReference.viewImage();
		}
	}
}
