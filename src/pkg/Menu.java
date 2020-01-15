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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;



public class Menu{
	
	JButton sourceButton;
	JButton destButton;
	JButton encodeButton;
	JFileChooser fileChooser;
	JLabel sourceLocation;
	JLabel destLocation;

	String sourcePath = "";
	String destPath = "";

	
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
		JPanel contentPanel = new JPanel(new GridLayout(5,1));
		frame.add(contentPanel);
		
		
		sourceButton = new JButton("Choose source image");
		sourceButton.addActionListener(new MenuListener("sourceButton"));
		
		destButton = new JButton("Choose destination");
		destButton.addActionListener(new MenuListener("destButton"));
		
		encodeButton = new JButton("Encode file");
		encodeButton.addActionListener(new MenuListener("encodeButton"));
		
		sourceLocation = new JLabel("No file loaded.");
		destLocation = new JLabel("No destination set.");

		contentPanel.add(sourceButton);
		contentPanel.add(sourceLocation);
		contentPanel.add(destButton);
		contentPanel.add(destLocation);
		contentPanel.add(encodeButton);
		        
		fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		        
		
		        
		
		
	}
	
	public void loadSource() {
		fileChooser.setSelectedFile(null);
		fileChooser.setFileFilter(null);

		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			sourcePath = fileChooser.getSelectedFile().getAbsolutePath();
			sourceLocation.setText(sourcePath);
		}
	}
	
	public void loadDest() {
		String newSelectedFile = "";
		if(!sourcePath.equals("")) {
			String[] newPath = sourcePath.split("\\.");
			newSelectedFile = newPath[0];
			newSelectedFile += ".dan";
			
		}
		
		
		fileChooser.setSelectedFile(new File(newSelectedFile));
		fileChooser.setFileFilter(new FileNameExtensionFilter("Danpression image","dan"));
		
		int returnValue = fileChooser.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			destPath = fileChooser.getSelectedFile().getAbsolutePath();
			destLocation.setText(destPath);
		}
	}
	public void encodeFile() {
		if(sourcePath.equals("")) {
			JOptionPane.showMessageDialog(null,"Source image needed for encoding.");
		}else if(destPath.equals("")){
			JOptionPane.showMessageDialog(null,"Destination file needed for encoding.");

			
		}else {
			try {
				Saver.Write(sourcePath,destPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
}
