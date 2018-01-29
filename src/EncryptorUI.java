import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class EncryptorUI implements ActionListener {
	
	private JLabel keyLbl, encryptedTxtLbl, plainTextLbl;
	private JTextArea encryptedTxtTxtArea, plainTextTxtArea;
	private JButton encryptBtn, decryptBtn, clear, about;
	private JTextField pubKeyTxt;
	private Bridge bridge;
	
	
	public EncryptorUI() {
		init();
	
	}
	
	
	private void init() {
		try {
			UIManager.setLookAndFeel( "com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			JFrame frame = new JFrame();
			frame.setTitle("TheDarth Crypter");
			frame.setResizable(false);
			JFrame.setDefaultLookAndFeelDecorated(true);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gUIForm(frame.getContentPane());
			frame.pack();
			frame.setBounds(400, 200, 800, 450);
			frame.setVisible(true);
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
	
		}
		
		
	}
	
	private void gUIForm(Container pane){
		
		pane.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		keyLbl = new JLabel();
		keyLbl.setText("Enter Key : ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(keyLbl, c);
		
		pubKeyTxt  = new JTextField();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.1;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(pubKeyTxt, c);
		
		
		encryptedTxtLbl = new JLabel();
		encryptedTxtLbl.setText("Enter / View Plain Text : ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10, 5, 0, 0);
		pane.add(encryptedTxtLbl, c); 
		

		encryptedTxtTxtArea = new JTextArea(15, 10);
		encryptedTxtTxtArea.setSize(15, 10);
		encryptedTxtTxtArea.setLineWrap(true);
		encryptedTxtTxtArea.setWrapStyleWord(false);
		encryptedTxtTxtArea.setAutoscrolls(true);
		JScrollPane jScrollPane2 = new JScrollPane(encryptedTxtTxtArea);

		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.insets = new Insets(10, 5, 0, 10);
		pane.add(jScrollPane2, c); 
		
		
		plainTextLbl = new JLabel();
		plainTextLbl.setText("Enter / View Encrypted Text : ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		pane.add(plainTextLbl, c); 
		

		plainTextTxtArea = new JTextArea(15, 10);
		plainTextTxtArea.setSize(15, 10);
		plainTextTxtArea.setLineWrap(true);
		plainTextTxtArea.setWrapStyleWord(false);
		plainTextTxtArea.setAutoscrolls(true);
		JScrollPane jScrollPane1 = new JScrollPane(plainTextTxtArea);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 2;
		pane.add(jScrollPane1, c); 
		
		
		encryptBtn = new JButton("Encrypt");
		encryptBtn.addActionListener(this);
		c.fill = GridBagConstraints.WEST;
		c.ipady = 0;       //reset to default
	//	c.weighty = 1.0;   //request any extra vertical space
		//c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 0;       //aligned with button 2
		//c.gridwidth = 1;   //2 columns wide
		c.gridy = 5;       //third row
		pane.add(encryptBtn, c);
		

		decryptBtn = new JButton("Decrypt");
		decryptBtn.addActionListener(this);
		c.fill = GridBagConstraints.WEST;
		c.ipady = 0;       //reset to default
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 2;       //aligned with button 2
		c.gridy = 5;       //third row
		pane.add(decryptBtn, c);
		
		clear = new JButton("Clear All");
		clear.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 0;       //aligned with button 2
		c.gridwidth = 4;   //2 columns wide
		c.gridy = 6;       //third row
		
		pane.add(clear, c);
		
		about = new JButton("About");
		about.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 0;       //aligned with button 2
		c.gridwidth = 4;   //2 columns wide
		c.gridy = 7;       //third row
		
		pane.add(about, c);

		
		
	}

	public void actionPerformed(ActionEvent ae){
		String pText = encryptedTxtTxtArea.getText();
		String key = pubKeyTxt.getText().trim();
		
		String cyText = plainTextTxtArea.getText();

		if(ae.getSource() == encryptBtn){
				if(pText.length() < 4){

					JOptionPane.showMessageDialog(null, "Minimum 4 charector needed to encrypt");
					
				}else if(key.length() > 32 ){
					JOptionPane.showMessageDialog(null, "Only maximum 32 charectors allowed for Key");
					
				}else{
					bridge = new Bridge();
					String cText = bridge.encryptText(pText, key);
				
					plainTextTxtArea.setText(cText);
				}
		}
		if(ae.getSource() == decryptBtn){
			if(key.length() > 32 ){
				JOptionPane.showMessageDialog(null, "Only maximum 32 charectors allowed for Key");
				
			}else{
				bridge = new Bridge();
				String plText = bridge.decryptText(cyText, key);
				encryptedTxtTxtArea.setText(plText);
			}
			
		}
		
		if(ae.getSource() == clear){
			encryptedTxtTxtArea.setText("");
			plainTextTxtArea.setText("");
			pubKeyTxt.setText("");
		}
	
		if(ae.getSource() == about)
			aboutUI();
		
	}
	
	
	private void aboutUI(){
		
		try {
			UIManager.setLookAndFeel( "com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			JFrame frame = new JFrame();
			frame.setTitle("TheDarth Crypter : About");
			JFrame.setDefaultLookAndFeelDecorated(true);
			frame.setResizable(false);
			
			frame.setLayout(new GridBagLayout());
			
			GridBagConstraints c = new GridBagConstraints();
			
			keyLbl = new JLabel();
			keyLbl.setText("TheDarth Crypter - Credits");
			c.fill = GridBagConstraints.CENTER;
			c.gridx = 0;
			c.gridy = 0;
			frame.add(keyLbl, c);
			

			keyLbl = new JLabel();
			keyLbl.setText("Fahiz Mohamed");
			c.fill = GridBagConstraints.CENTER;
			c.gridx = 0;
			c.gridy = 1;
			frame.add(keyLbl, c);

			keyLbl = new JLabel();
			keyLbl.setText("Janani Harischandra");
			c.fill = GridBagConstraints.CENTER;
			c.gridx = 0;
			c.gridy = 2;
			frame.add(keyLbl, c);

			keyLbl = new JLabel();
			keyLbl.setText("Chathura Disanayake");
			c.fill = GridBagConstraints.CENTER;
			c.gridx = 0;
			c.gridy = 3;
			frame.add(keyLbl, c);

			keyLbl = new JLabel();
			keyLbl.setText("David Edmund");
			c.fill = GridBagConstraints.CENTER;
			c.gridx = 0;
			c.gridy = 4;
			frame.add(keyLbl, c);

			//gUIForm(frame.getContentPane());
			frame.pack();
			frame.setBounds(700, 290, 200, 250);
			frame.setVisible(true);
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
	
		}
		
	}

	
	public static void main(String arg[]) {
		new EncryptorUI();
		
	}

}
