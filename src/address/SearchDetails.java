package address;
import javax.swing.*;

public class SearchDetails extends JFrame{
	
	private JList list;
	private JTextField tf1,tf2,tf3,tf4,tf5,tf6;
	private JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7;
	
	public SearchDetails(){
		 setBounds(410,150,750,480);
		 //setResizable(false);
		 setVisible(true);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 
		    Icon tab=new ImageIcon(getClass().getResource("image/tab1.png"));
			lb1=new JLabel(tab);
			lb1.setBounds(5, 0, 720, 480);
			add(lb1);
			getContentPane().setLayout(null);
		 
		 
	}

}
