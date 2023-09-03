package address;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

 public class Editdetails extends JFrame implements ActionListener{
	 private JTextField tf1,tf2,tf3,tf4,tf5,tf6;
	 private JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7;
	 private JButton find,cancel,save;
	    
	 private int rows = 0;
	 private	int total = 0;
	 private int recCount = 0;
	 private String records[][] = new String [500][8];
	 private FileInputStream fis;
	 private DataInputStream dis;
	 
	 public Editdetails(){
		 super("editDetails");
		 setBounds(410,150,500,480);
		 setResizable(false);
		 setVisible(true);
		 
		
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
			add(tf2);
			tf3=new JTextField();
			tf3.setBounds(180, 190, 200, 28);
			add(tf3);
			tf4=new JTextField();
			tf4.setBounds(180, 220, 200, 28);
			add(tf4);
			tf6=new JTextField();
			tf6.setBounds(180, 250, 200, 28);
			add(tf6); 
			tf5=new JTextField();
			tf5.setBounds(180, 280, 250, 28);
			add(tf5);
			
			Icon f=new ImageIcon(getClass().getResource("image/search.png"));
			find=new JButton(f);
			find.setBounds(180, 360, 70, 70);
			find.setToolTipText("Search");
			find.addActionListener(this);
			//add(find);
			
			Icon fo=new ImageIcon(getClass().getResource("image/save.png"));
			save=new JButton(fo);
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
			
			
		 
		Icon tab=new ImageIcon(getClass().getResource("image/editTab.png"));
		lb1=new JLabel(tab);
		lb1.setBounds(0, 5, 500, 450);
		add(lb1);
		getContentPane().setLayout(null);
		
	
		
		 
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
		populateArray();
		}
	 
	 private class clearHandler implements ActionListener{
			public void actionPerformed(ActionEvent e){
				txtClear();
				
			}
		}
	 public void actionPerformed(ActionEvent ae) {
		 
			Object obj = ae.getSource();

			if (obj == save) {
				if (tf1.getText().equals("")) {
					JOptionPane.showMessageDialog (this, "Please! Provide Name.",
							"AddressBook - EmptyField", JOptionPane.PLAIN_MESSAGE);
					tf1.requestFocus();
				}
				 
				
				else {
					 editRec();	//Finding if Account No. Already Exist or Not.
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
								"Address- Details", JOptionPane.PLAIN_MESSAGE);
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
	 void findRec () {

			boolean found = false;
			for (int x = 0; x < total; x++) {
				if (records[x][0].equalsIgnoreCase (tf1.getText())) {
					found = true;
					showRec (x);
					break;
				}
			}
			if (found == false) {
				JOptionPane.showMessageDialog (this, "Name " + tf1.getText () + " doesn't Exist.",
								" Address - WrongNo", JOptionPane.PLAIN_MESSAGE);
				txtClear ();
			}

		}
	 public void showRec (int intRec) {

			tf1.setText (records[intRec][0]);
			tf2.setText (records[intRec][1]);
			tf3.setText (records[intRec][2]);
			tf4.setText (records[intRec][3]);
			tf5.setText (records[intRec][4]);
			tf6.setText (records[intRec][5] + ", " + records[intRec][6] + ", " + records[intRec][7]);
			

		}

		//Function use to Clear all TextFields of Window.
		void txtClear () {

			tf1.setText ("");
			tf2.setText ("");
			tf3.setText ("");
			tf4.setText ("");
			tf5.setText ("");
			tf6.setText ("");
			tf1.requestFocus ();

		}
		
		public void editRec () {

			//deposit = Integer.parseInt (txtDeposit.getText ());
			
			records[recCount][0] = tf1.getText (); 
			records[recCount][1] = tf2.getText ();
			records[recCount][2] = tf3.getText ();
			records[recCount][3] = tf4.getText ();
			records[recCount][4] = tf5.getText ();
			records[recCount][5] = tf6.getText ();
			
			
			//records[recCount][8] = "" + (curr + deposit);
			editFile ();	//Save This Array to File.
		
		}

		//Function use to Save Records to File After editing the Record of User Choice.
		public void editFile () {

			try {
				FileOutputStream fos = new FileOutputStream ("Address.dat");
				DataOutputStream dos = new DataOutputStream (fos);
				if (records != null) {
					for (int i = 0; i < total; i++) {
						for (int c = 0; c < 8; c++) {
							dos.writeUTF (records[i][c]);
							if (records[i][c] == null) break;
						}
					}
					JOptionPane.showMessageDialog (this, "The File is Updated Successfully",
							"AddressBook - Record Saved", JOptionPane.PLAIN_MESSAGE);
					txtClear ();
					dos.close();
					fos.close();
				}
			}
			catch (IOException ioe) {
				JOptionPane.showMessageDialog (this, "There are Some Problem with File",
							"BankSystem - Problem", JOptionPane.PLAIN_MESSAGE);
			}
		
		}


		//Function use to Lock Controls of Window.
		void btnEnable () {

			tf1.setEnabled (false);
			
			
			 save.setEnabled (false);
			 cancel.setEnabled(false);

		}

	 
	 
 }