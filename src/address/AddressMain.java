package address;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

public class AddressMain extends JFrame {
	private JButton b1, b2, b3, b4, b5;
	private JLabel lb1, lb2, lb3;

	public AddressMain() {
		super("AdressBook");
		setBounds(210, 80, 830, 620);
		setLayout(null);
		getContentPane().setBackground(Color.yellow);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

	
		Icon lab = new ImageIcon(getClass().getResource("image/mainlabel.png"));
		lb1 = new JLabel(lab, SwingConstants.LEADING);
		lb1.setBounds(0, 0, 840, 120);
		add(lb1);

		Icon lab1 = new ImageIcon(getClass().getResource("image/main.png"));
		lb2 = new JLabel(lab1, SwingConstants.LEADING);
		lb2.setBounds(200, 120, 840, 400);
		add(lb2);
		Icon bug = new ImageIcon(getClass().getResource("image/lable.png"));
		lb3 = new JLabel(bug, SwingConstants.LEADING);
		lb3.setBounds(440, 350, 550, 300);
		add(lb3);

		Icon search = new ImageIcon(getClass().getResource("image/books.png"));
		b1 = new JButton(search);
		b1.setToolTipText("Search");
		b1.setBounds(20, 130, 80, 80);
		add(b1);

		Icon save = new ImageIcon(getClass().getResource("image/save.png"));
		b2 = new JButton(save);
		b2.setToolTipText("Save");
		b2.setBounds(20, 220, 80, 80);
		add(b2);

		Icon edit = new ImageIcon(getClass().getResource("image/edit.png"));
		b3 = new JButton(edit);
		b3.setToolTipText("Edit");
		b3.setBounds(20, 310, 80, 80);
		add(b3);

		Icon del = new ImageIcon(getClass().getResource("image/del.png"));
		b4 = new JButton(del);
		b4.setToolTipText("Delete");
		b4.setBounds(20, 400, 80, 80);
		add(b4);

		Icon can = new ImageIcon(getClass().getResource("image/info.png"));
		b5 = new JButton(can);
		b5.setToolTipText("about Addressbook");
		b5.setBounds(20, 490, 80, 80);
		// b5.addActionListener(new cancelHandler());
		add(b5);

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Viewdetails();

			}
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddressBook();
			}
		});
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Editdetails();
			}
		});
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Delete();
			}
		});
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new About();

			}
		});
		repaint();

	}

}
