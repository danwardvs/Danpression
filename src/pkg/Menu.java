package pkg;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;



public class Menu{
	
	JButton encodeButton;
	JFileChooser fileChooser;
	JLabel loadedFileLocation;
	String filePath = "";
	
	public static void main(String[] args) {
		
		Menu mainMenu = new Menu();
		MenuListener.setMenuReference(mainMenu);

        
   
   
	}
	public Menu() {
		// TODO Auto-generated method stub
		
				
		JFrame frame = new JFrame("My First GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
		frame.setVisible(true);
		JPanel contentPanel = new JPanel(new GridLayout(4,1));
		frame.add(contentPanel);
		
		
		encodeButton = new JButton("Load source image");
		encodeButton.addActionListener(new MenuListener("encodeButton"));
		
		loadedFileLocation = new JLabel("No file loaded.");
		contentPanel.add(encodeButton);
		contentPanel.add(loadedFileLocation);
		        
		fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		        
		
		        
		
		
	}
	
	public void loadFile() {
		int returnValue = fileChooser.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			filePath = fileChooser.getSelectedFile().getAbsolutePath();
			loadedFileLocation.setText(filePath);
		}
	}

	
}

//File selectedFile = jfc.getSelectedFile();
//System.out.println(selectedFile.getAbsolutePath());
//try {
//	Saver.Write(selectedFile.getAbsolutePath());
//} catch (IOException e1) {
//	// TODO Auto-generated catch block
//	e1.printStackTrace();
//}
