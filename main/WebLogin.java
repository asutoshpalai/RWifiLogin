package main;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * @version 1.0
 * @author Asutosh
 */
public class WebLogin extends javax.swing.JFrame {

	private web webr;
	private Thread th;

	public WebLogin() {
		initComponents();
	}

	TrayIcon trayIcon;
	SystemTray tray;
	public void setHideToSystemTray(){
		System.out.println("setting up systemtray");

		if(SystemTray.isSupported()){
			System.out.println("system tray supported");
			tray=SystemTray.getSystemTray();

			Image image=Toolkit.getDefaultToolkit().getImage("wifi.png");
			ActionListener exitListener=new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Exiting....");
					System.exit(0);
				}
			};
			PopupMenu popup=new PopupMenu();
			MenuItem defaultItem=new MenuItem("Exit");
			defaultItem.addActionListener(exitListener);
			popup.add(defaultItem);
			defaultItem=new MenuItem("Open");
			defaultItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(true);
					setExtendedState(JFrame.NORMAL);
				}
			});
			popup.add(defaultItem);
			trayIcon=new TrayIcon(image, "Wifi WebLogin", popup);
			trayIcon.setImageAutoSize(true);
		}else{
			System.out.println("system tray not supported");
		}

		trayIcon.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(true);
				setExtendedState(JFrame.NORMAL);
			}
		});
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				if(e.getNewState()==ICONIFIED){
					try {
						tray.add(trayIcon);
						setVisible(false);
						System.out.println("added to SystemTray");
					} catch (AWTException ex) {
						System.out.println("unable to add to tray");
					}
				}
				if(e.getNewState()==7){
					try{
						tray.add(trayIcon);
						setVisible(false);
						System.out.println("added to SystemTray");
					}catch(AWTException ex){
						System.out.println("unable to add to system tray");
					}
				}
				if(e.getNewState()==MAXIMIZED_BOTH){
					tray.remove(trayIcon);
					setVisible(true);
					System.out.println("Tray icon removed");
				}
				if(e.getNewState()==NORMAL){
					tray.remove(trayIcon);
					setVisible(true);
					System.out.println("Tray icon removed");
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage("wifi.png"));


	}


	
	@SuppressWarnings("unchecked")
	                         
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jTextField3 = new javax.swing.JTextField();
		jTextField4 = new javax.swing.JTextField();
		webr = new web( this);
		th = webr;
		th.start();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


		jTextField1.setEditable(false);
		jTextField2.setEditable(false);
		jTextField3.setText( webr.username);
		jTextField4.setText(webr.password);

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel1.setText("Rajendra Wifi Login");

		jLabel2.setText("Internet Status");

		jLabel3.setText("Program Status");

		jButton1.setText("Pause");
		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				webr.suspend();;
				//webr.run = false;


				webr.istatus = "";
				webr.pstatus = "paused";
				update();
				jButton1.setEnabled(false);
				jButton2.setEnabled(true);

			}
		});

		jButton2.setText("Start");
		jButton2.setEnabled(false);
		jButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				webr.run =true;
				webr.resume();;
				jButton2.setEnabled(false);
				jButton1.setEnabled(true);

			}
		});


		jButton3.setText("Close");
		jButton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				webr.run = false;
				th.stop();
				WebLogin.this.setVisible(false);
				WebLogin.this.dispose();
				System.exit(0);

			}
		});

		jButton4.setText("Change");
		jButton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				webr.password = jTextField4.getText();
				webr.username= jTextField3.getText(); 
				jTextField3.setText( webr.username);
				jTextField4.setText(webr.password);

			}
		});

		jLabel4.setText("User Name");

		jLabel5.setText("Password");

		setHideToSystemTray();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGap(112, 112, 112)
										.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addGap(46, 46, 46)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(jButton1)
														.addComponent(jLabel2)
														.addComponent(jLabel3)
														.addComponent(jLabel4)
														.addComponent(jLabel5))
														.addGap(29, 29, 29)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																.addGroup(layout.createSequentialGroup()
																		.addComponent(jButton2)
																		.addGap(18, 18, 18)
																		.addComponent(jButton3))
																		.addComponent(jTextField1)
																		.addComponent(jTextField4)
																		.addComponent(jTextField3)
																		.addComponent(jTextField2))
																		.addGap(18, 18, 18)
																		.addComponent(jButton4)))
																		.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(28, 28, 28)
						.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel2)
								.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel3)
										.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup()
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(jLabel4)
																.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(jLabel5)
																		.addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(40, 40, 40))
																		.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																				.addComponent(jButton4)
																				.addGap(53, 53, 53)))
																				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(jButton1)
																						.addComponent(jButton2)
																						.addComponent(jButton3))
																						.addContainerGap(53, Short.MAX_VALUE))
				);


		pack();
	}                 

	

	public void update()
		{
			jTextField2.setText( webr.pstatus);
			jTextField1.setText(webr.istatus);
				

	}

	public static void main(String args[]) {
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(WebLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(WebLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(WebLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(WebLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new WebLogin().setVisible(true);
			}
		});
	}
	}

	// Variables declaration - do not modify                     
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	// End of variables declaration                   
}
