package address;
import java.awt.*;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Viewdetails extends JFrame{
	
	private DefaultTableModel dtmCustomer;
	private JTable tbAddress;
	private JScrollPane jspTable;
	private JLabel b;
 
	
	 
	private int row = 0;
	private	int total = 0;
	private String rowData[][];
	
	private FileInputStream fis;
	private DataInputStream dis;
	public Viewdetails(){
		super("ViewDetails");
		setBounds(410,150,800,520);
		setVisible(true);
		setResizable(false);
		setLayout(null);
		
		populateArray();
		tbAddress = makeTable ();
		jspTable = new JScrollPane (tbAddress);
		jspTable.setBounds (70, 110, 650, 320);
		add(jspTable);
		
		Icon tab=new ImageIcon(getClass().getResource("image/viewtab.png"));
		b=new JLabel(tab);
		b.setBounds(5, 0, 800, 500);
		add(b);
		getContentPane().setLayout(null);
		
		
	 
		 
		
		
	}
	
	void populateArray () {

		//String Type Array use to Load Records into File.
		String rows[][] = new String [500][8];
		try {
			fis = new FileInputStream ("Address.dat");
			dis = new DataInputStream (fis);
			//Loop to Populate the Array.
			while (true) {
				for (int i = 0; i < 8; i++) {
					rows[row][i] = dis.readUTF ();
				}
				row++;
			}
		}
		catch (Exception ex) {
			total = row;
			rowData = new String [total][8];
			if (total == 0) {
				JOptionPane.showMessageDialog (null, "Records File is Empty.\nEnter Records to Display.",
							"AddressBook - EmptyFile", JOptionPane.PLAIN_MESSAGE);
			}
			else {
				for (int i = 0; i < total; i++) {
					rowData[i][0] = rows[i][0];
					rowData[i][1] = rows[i][1];
					rowData[i][2] = rows[i][2];
					rowData[i][3] = rows[i][3];
					rowData[i][4] = rows[i][4];
					rowData[i][5] = rows[i][5]+ ", " + rows[i][6] + ", " +rows[i][6];
					  
					 
				}
				try {
					dis.close();
					fis.close();
				}
				catch (Exception exp) { }
			}
		}

	}
	private JTable makeTable(){
	
		String cols[]={"Name","PhoneNum","LanNum","Email Id","Address","DateOfBirth"};
		
		dtmCustomer  = new DefaultTableModel (rowData, cols);
		 
		tbAddress = new JTable (dtmCustomer) {
			public boolean isCellEditable (int iRow, int iCol) {
				return false;	//Disable All Columns of Table.
			}
		};
		(tbAddress.getColumnModel().getColumn(0)).setPreferredWidth (150);
		(tbAddress.getColumnModel().getColumn(1)).setPreferredWidth (150);
		(tbAddress.getColumnModel().getColumn(2)).setPreferredWidth (200);
		(tbAddress.getColumnModel().getColumn(3)).setPreferredWidth (200);
		(tbAddress.getColumnModel().getColumn(4)).setPreferredWidth (200);
		(tbAddress.getColumnModel().getColumn(5)).setPreferredWidth (150);
		 
		
		tbAddress.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbAddress.setRowHeight (20);
		tbAddress.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
		return tbAddress;
		}
	 
		
		 

	 
	 
	 
	 
	 
	 
	 
	
	
	
	 
}
