package address;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

import javax.swing.*;



public class Delete extends JFrame implements ActionListener{
	
	private JLabel lb1,lb2,lb3,lb4,lb5,lb6;
	private JTextField tf1,tf2,tf3,tf4,tf5,tf6;
	private JButton Del,cancel;
	
	private int rows = 0;
	private	int total = 0;

	private int recCount = 0;
 
	
	 private String records[][] = new String [500][12];
	 //String Type Array use to Save Records into File.
	 private String saves[][] = new String [500][12];
	 
	 private FileInputStream fis;
	private DataInputStream dis;

	public Delete(){
		super("DeleteForm");
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
		lb6=new JLabel("Address");
		lb6.setBounds(70, 280, 70, 25);
		lb6.setForeground(Color.gray);
		lb6.setFont(new java.awt.Font("Tahoma",1,13));
		add(lb6);
		
		tf1=new JTextField();
		tf1.setBounds(180, 130, 200, 28);
		add(tf1);
		tf2=new JTextField();
		tf2.setBounds(180, 160, 200, 28);
		tf2.setEnabled(false);
		add(tf2);
		tf3=new JTextField();
		tf3.setBounds(180, 190, 200, 28);
		tf3.setEnabled(false);
		add(tf3);
		
		tf4=new JTextField();
		tf4.setBounds(180, 220, 200, 28);
		tf4.setEnabled(false);
		add(tf4);
		tf6=new JTextField();
		tf6.setBounds(180, 250, 200, 28);
		tf6.setEnabled(false);
		add(tf6); 
		tf5=new JTextField();
		tf5.setBounds(180, 280, 250, 28);
		tf5.setEnabled(false);
		add(tf5);
		
		Icon f=new ImageIcon(getClass().getResource("image/del1.png"));
		Del=new JButton(f);
		Del.setBounds(180, 360, 70, 70);
		Del.setToolTipText("Delete");
		Del.addActionListener(this);
		add(Del);
		
		Icon c=new ImageIcon(getClass().getResource("image/clear.png"));
		cancel=new JButton(c);
		cancel.setBounds(250,360, 70, 70);
		cancel.setToolTipText("clear");
		cancel.addActionListener(new clearHandler());
		add(cancel);
	 
	Icon tab=new ImageIcon(getClass().getResource("image/deleteTab.png"));
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
		tf1.addFocusListener (new FocusListener () {
			public void focusGained (FocusEvent e) { }
			public void focusLost (FocusEvent fe) {
				if (tf1.getText().equals ("")) { }
				else {
					rows = 0;
					populateArray ();	
					findRec ();		
				}
			}});
		
		
		 
		
		  
		setVisible(true);
		populateArray ();
		}
	public void actionPerformed (ActionEvent ae) {

		Object obj = ae.getSource();

		if (obj == Del) {
			if (tf1.getText().equals("")) {
				JOptionPane.showMessageDialog (this, "Please! Provide Id of Customer.",
						"AddressBook - EmptyField", JOptionPane.PLAIN_MESSAGE);
				tf1.requestFocus();
			}
			else {
				deletion ();	//Confirm Deletion of Current Record.
			}
		}
		if (obj == cancel) {
			txtClear ();
			setVisible (false);
			dispose();
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
			if (total == 0) {
				JOptionPane.showMessageDialog (null, "Records File is Empty.\nEnter Records First to Display.",
					"AddressBook - EmptyFile", JOptionPane.PLAIN_MESSAGE);
				btnEnable ();
			}
			else {
				try {
					dis.close();
					fis.close();
				}
				catch (Exception exp) { }
			}
		}

	}
	private class clearHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			 txtClear();
			
		}}
		void btnEnable () {
			 
			tf1.setEnabled (false);
			Del.setEnabled (false);
			cancel.setEnabled(false);
			

		}
		void txtClear () {

			tf1.setText(""); 
		    tf2.setText ("");
		    tf3.setText("");
		   
		    tf4.setText("");
		    tf5.setText("");
		    tf6.setText("");
		 
			
			tf1.requestFocus ();

		}
		void deletion () {

			try {
				//Show a Confirmation Dialog.
			    	int reply = JOptionPane.showConfirmDialog (this,
						"Are you Sure you want to Delete\n" + tf1.getText () + " Record From AddressBook?",
						"AddressBook - Delete", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				//Check the User Selection.
				if (reply == JOptionPane.YES_OPTION) {
					delRec ();	//Delete the Selected Contents of Array.
				}
				else if (reply == JOptionPane.NO_OPTION) { }
			} 

			catch (Exception e) {}

		}

		//Function use to Delete an Element from the Array.
		void delRec () {

			try {
				if (records != null) {
					for(int i = recCount; i < total; i++) {
						for (int r = 0; r < 8; r++) {
							records[i][r] = records[i+1][r];				
							if (records[i][r] == null) break;
						}
					}
					total = total - 1;
					deleteFile ();
				}
			}
			catch (ArrayIndexOutOfBoundsException ex) { }

		}

		//Function use to Save Records to File After Deleting the Record of User Choice.
		void deleteFile () {

			try {
				FileOutputStream fos = new FileOutputStream ("Address.dat");
				DataOutputStream dos = new DataOutputStream (fos);
				if (records != null) {
					for (int i = 0; i < total; i++) {
						for (int r = 0; r < 8; r++) {
							dos.writeUTF (records[i][r]);
							if (records[i][r] == null) break;
						}
					}
					JOptionPane.showMessageDialog (this, "Record has been Deleted Successfuly.",
						"AddressBook- Record Deleted", JOptionPane.PLAIN_MESSAGE);
					txtClear ();
				}
				else { }
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
					showRec (x);
					break;
				}
			}
			if (found == false) {
				String str = tf1.getText ();
				txtClear ();
				JOptionPane.showMessageDialog (this, "User " + str + " doesn't Exist.",
						"AddressBook- wrong name", JOptionPane.PLAIN_MESSAGE);
			}

		}
		public void showRec (int intRec) {

			tf1.setText (records[intRec][0]); 
		    tf2.setText (records[intRec][1]);
		    tf3.setText (records[intRec][2]);
		    tf4.setText (records[intRec][3]);
		    tf5.setText (records[intRec][4]);
		     
		    tf6.setText (records[intRec][5] + ", " + records[intRec][6] + ", " + records[intRec][7]);
		     
			recCount = intRec;
		

		
}
}
	 