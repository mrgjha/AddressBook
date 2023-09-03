package address;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.*;


public class AddressBook extends JFrame implements ActionListener{
	private JLabel lb1,lb2,lb3,lb4,lb5,lb6;
	private JTextField tf1,tf2,tf3,tf4,tf5;
	private JButton save,cancel;
	private JComboBox cboMonth, cboDay, cboYear;
	 
	private FileInputStream fis;
	private DataInputStream dis;
	private int count = 0;
	private int rows = 0;
	private	int total = 0;
    
    private String records[][] = new String [500][8];
   //String Type Array use to Save Records into File.
	private String saves[][] = new String [500][8];
	public AddressBook(){
		super("AddressBook");
		setBounds(410,150,500,480);
		setLayout(null);
		setResizable(false);
		 
		 
		lb1=new JLabel("Name");
		lb1.setBounds(70, 130, 70, 25);
		lb1.setForeground(Color.gray);
		lb1.setFont(new java.awt.Font("Tahoma", 1, 13));
		add(lb1);
		lb2=new JLabel("Phone Num");
		lb2.setBounds(70, 160, 100, 25);
		lb2.setForeground(Color.gray);
		lb2.setFont(new java.awt.Font("Tahoma",1,13));
		add(lb2);
		lb3=new JLabel("Lan Num");
		lb3.setBounds(70, 190, 100, 25);
		lb3.setForeground(Color.gray);
		lb3.setFont(new java.awt.Font("Tahoma",1,13));
		add(lb3);
		lb4=new JLabel("Mail ID");
		lb4.setBounds(70, 220, 100, 25);
		lb4.setForeground(Color.gray);
		lb4.setFont(new java.awt.Font("Tahoma",1,13));
		add(lb4);
		lb5=new JLabel("DateOfBirth");
		lb5.setBounds(70, 250, 100, 25);
		lb5.setForeground(Color.gray);
		lb5.setFont(new java.awt.Font("Tahoma",1,13));
		add(lb5);
		String Months[] = {"January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December"};
			cboMonth = new JComboBox (Months);
			cboDay = new JComboBox ();
			cboYear = new JComboBox ();
			for (int i = 1; i <= 31; i++) {
				String days = "" + i;
				cboDay.addItem (days);
			}
			for (int i = 1960; i <= 2012; i++) {
				String years = "" + i;
				cboYear.addItem (years);
			}
			
			//Aligning The Date Option Controls.
			cboMonth.setBounds (180, 250, 100, 25);
			cboDay.setBounds (280, 250,58, 25);
			cboYear.setBounds (340, 250, 70, 25);
			add (cboMonth);
			add (cboDay);
			add (cboYear);
		
		lb6=new JLabel("Address");
		lb6.setBounds(70, 280, 70, 25);
		lb6.setForeground(Color.gray);
		lb6.setFont(new java.awt.Font("Tahoma",1,13));
		add(lb6);
		
		tf1=new JTextField();
		tf1.setBounds(180, 130, 200, 28);
		add(tf1);
		tf3=new JTextField();
		tf3.setBounds(180, 190, 200, 28);
		add(tf3);
		tf2=new JTextField();
		tf2.setBounds(180, 160, 200, 28);
		add(tf2);
		tf4=new JTextField();
		tf4.setBounds(180, 220, 200, 28);
		add(tf4);
		  
		tf5=new JTextField();
		tf5.setBounds(180, 280, 250, 28);
		add(tf5);
		
		Icon f=new ImageIcon(getClass().getResource("image/save.png"));
		save=new JButton(f);
		save.setBounds(180, 360, 70, 70);
		save.setToolTipText("Save");
		save.addActionListener(this);
		add(save);
		
		Icon c=new ImageIcon(getClass().getResource("image/clear.png"));
		cancel=new JButton(c);
		cancel.setBounds(250,360, 70, 70);
		cancel.setToolTipText("clear");
		cancel.addActionListener(new clearHandler());
		add(cancel);
	 
	Icon tab=new ImageIcon(getClass().getResource("image/enterTab.png"));
	lb1=new JLabel(tab);
	lb1.setBounds(0, 5, 500, 450);
	add(lb1);
	getContentPane().setLayout(null);
		
			
			 
		
		 
		
		tf2.addKeyListener (new KeyAdapter() {
			public void keyTyped (KeyEvent ke) {
				char c = ke.getKeyChar ();
				if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) {
					getToolkit().beep ();
					ke.consume ();
      				}
    			}
  		});
		tf3.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke){
			char c=ke.getKeyChar();
			if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) {
				getToolkit().beep ();
				ke.consume ();
			}
			}
		});
		
		
		 
		
		 
		setVisible(true);
		populateArray ();
		
		
		
		 
	} 
	public void actionPerformed(ActionEvent ae) {
		 
		Object obj = ae.getSource();

		if (obj == save) {
			if (tf1.getText().equals("")) {
				JOptionPane.showMessageDialog (this, "Please! Provide the Name.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				tf1.requestFocus();
			}
			else if (tf2.getText().equals("")) {
				JOptionPane.showMessageDialog (this, "Please! Provide The Phone Number.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				tf2.requestFocus ();
			}
			else if (tf3.getText().equals("")) {
				JOptionPane.showMessageDialog (this, "Please! Provide  The Lan Number.",
						"BankSystem - EmptyField", JOptionPane.PLAIN_MESSAGE);
				tf3.requestFocus ();
			}
			else {
				populateArray ();
				findRec ();		
			}
		}
		if (obj == cancel) {
			txtClear ();
			setVisible (false);
			dispose();
		}

	 }
	
	private class clearHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			txtClear();
		}
	}
	
	
	
	
	
	void populateArray () {

		try {
			fis = new FileInputStream ("Address.dat");
			dis = new DataInputStream (fis);
			//Loop to Populate the Array.
			while (true) {
				for (int i = 0; i < 8; i++) {
					records[rows][i] = dis.readUTF ();
				}
				rows++;
			}
		}
		
		catch (Exception ex) {
			total = rows;
			if (total == 0) { }
			else {
				try {
					dis.close();
					fis.close();
				}
				catch (Exception exp) { }
			}
		}
	}
	
	void saveArray () {
		saves[count][0] = tf1.getText ();
		saves[count][1] = tf2.getText ();
		saves[count][2] = tf3.getText ();
		saves[count][3] = tf4.getText (); 
		saves[count][4] = tf5.getText ();
		saves[count][5] = "" + cboMonth.getSelectedItem ();
		saves[count][6] = "" + cboDay.getSelectedItem ();
		saves[count][7] = "" + cboYear.getSelectedItem ();
		 
		saveFile ();	//Save This Array to File.
		count++;

	}
	
	void saveFile () {

		try {
			FileOutputStream fos = new FileOutputStream ("Address.dat", true);
			DataOutputStream dos = new DataOutputStream (fos);
			dos.writeUTF (saves[count][0]);
			dos.writeUTF (saves[count][1]);
			dos.writeUTF (saves[count][2]);
			dos.writeUTF (saves[count][3]);
			dos.writeUTF (saves[count][4]);
			dos.writeUTF (saves[count][5]);
			dos.writeUTF (saves[count][6]);
			dos.writeUTF (saves[count][7]);
		 
			JOptionPane.showMessageDialog (this, "The Record has been Saved Successfully",
						"AddressBook - Record Saved", JOptionPane.PLAIN_MESSAGE);
			txtClear ();
			dos.close();
			fos.close();
		}
		catch (IOException ioe) {
			JOptionPane.showMessageDialog (this, "There are Some Problem with File",
						"AddressBook - Problem", JOptionPane.PLAIN_MESSAGE);
		}

	}
	void findRec () {

		boolean found = false;
		for (int x = 0; x < total; x++) {
			if (records[x][0].equals (tf1.getText())) {
				found = true;
				JOptionPane.showMessageDialog (this, "Names " + tf1.getText () + " is Already Exist.",
							"AddressBook - WrongName", JOptionPane.PLAIN_MESSAGE);
				txtClear ();
				break;
			}
		}
		if (found == false) {
			saveArray ();
		}
	}
	void txtClear () {

		tf1.setText(""); 
	    tf2.setText ("");
	    tf3.setText("");
	   
	    tf4.setText("");
	    tf5.setText("");
	 
		
		tf1.requestFocus ();

	}
	

	 
	
	   

}
