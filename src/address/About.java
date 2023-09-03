package address;


import javax.swing.*;

public class About extends JFrame{
	private JLabel lb1;
	
	public About(){
		setBounds(380,300,440,260);
		setLayout(null);
		setVisible(true);
		setResizable(false);
		
		Icon lab=new ImageIcon(getClass().getResource("image/About.png"));
		lb1=new JLabel(lab,SwingConstants.LEADING);
		lb1.setBounds(0, 0, 500,240);
		add(lb1);
		
	}

	 

}
