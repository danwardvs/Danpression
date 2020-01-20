package pkg;

import java.awt.GridLayout;
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
	JButton viewButton;
	JFileChooser fileChooser;
	JLabel sourceLocation;
	JLabel destLocation;
	JLabel errorMsg;

	String sourcePath = "";
	String destPath = "";

	
	public static void main(String[] args) {
		
		if(args.length == 0) {
			Menu mainMenu = new Menu();
			MenuListener.setMenuReference(mainMenu);
		}else if(args.length == 2) {
			if(args[0].contentEquals("view")) {
				try {
					Loader.Load(args[1]);
					System.out.println("Viewing image at path" + args[1]);

				} catch (IOException e) {
					// TODO Auto-generated catch block

					System.out.println("Failed to view image at path " + args[1]);
				}
			}	
		}
	}
	private void showMessage(String newError) {
		errorMsg.setText(newError);
	}
	
	public Menu() {
		// TODO Auto-generated method stub
		
				
		JFrame frame = new JFrame("Danpression");
		
		JPanel mainPanel = new JPanel(new GridLayout(1,2));
		
		frame.add(mainPanel);
		
		JPanel decodePanel = new JPanel(new GridLayout(6,1));
		mainPanel.add(decodePanel);
		
		viewButton = new JButton("View .dan image");
		viewButton.addActionListener(new MenuListener("viewButton"));

		
		decodePanel.add(viewButton);


		
		JPanel encodePanel = new JPanel(new GridLayout(6,1));
		mainPanel.add(encodePanel);
		
		
		sourceButton = new JButton("Choose source image");
		sourceButton.addActionListener(new MenuListener("sourceButton"));
		
		destButton = new JButton("Choose destination");
		destButton.addActionListener(new MenuListener("destButton"));
		
		encodeButton = new JButton("Encode file");
		encodeButton.addActionListener(new MenuListener("encodeButton"));
		
		sourceLocation = new JLabel("No file loaded.");
		destLocation = new JLabel("No destination set.");
		errorMsg = new JLabel("");


		encodePanel.add(sourceButton);
		encodePanel.add(sourceLocation);
		encodePanel.add(destButton);
		encodePanel.add(destLocation);
		encodePanel.add(encodeButton);
		encodePanel.add(errorMsg);
		        
		fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,400);
		frame.setVisible(true);
		
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
			
		}
		
		
		fileChooser.setSelectedFile(new File(newSelectedFile));
		fileChooser.setFileFilter(new FileNameExtensionFilter("Danpression image","dan"));
		
		int returnValue = fileChooser.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			destPath = fileChooser.getSelectedFile().getAbsolutePath();
			destLocation.setText(destPath + ".dan");
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
				showMessage("Successfully encoded file");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				showMessage(e.getMessage());

			}
		}
	}
	
	public void viewImage() {
		
		
		fileChooser.setSelectedFile(null);
		fileChooser.setFileFilter(new FileNameExtensionFilter("Danpression image","dan"));
		
		int returnValue = fileChooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				Loader.Load(fileChooser.getSelectedFile().getAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				showMessage(e.getMessage());
			}
		}

		
	}

	
}
