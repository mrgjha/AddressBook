package address;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;



 public class Mainclass extends JWindow{
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	public Mainclass () {}
	private void showSplashScreen() {
		new Thread() {
			public void run() {
				
				
				JLabel lbImage    = new JLabel (new ImageIcon(getClass().getClassLoader().getResource("address/image/splash1.png")), JLabel.CENTER) ;
				Color cl = new Color (0, 0, 0);
				lbImage.setBorder (new LineBorder (cl, 1));
				
			    getContentPane().add (lbImage, BorderLayout.CENTER);
				pack();

				setSize (475, 275);
				setLocation (d.width / 2 - getWidth() / 2, d.height / 2 - getHeight() / 2);
				show();
			}
		}.start() ;
	}
	public void ShowAddressBook() {
		new AddressMain();
		setVisible (false);

	}
	private static Object lock = new Object() ;
	
	public static void main(String[] args) {
		
        final Mainclass splash = new Mainclass() ;
		splash.showSplashScreen() ;
		synchronized (lock) {
			try {
				Thread.sleep(4000) ;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}

		splash.ShowAddressBook();
}
}