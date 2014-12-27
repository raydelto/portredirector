package org.raydelto.redirector.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.raydelto.redirector.net.PortRedirector;

public class MainWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel lblLocalPort;
	private JLabel lblRedirectPort;
	
	private JTextField txtLocalPort;
	private JTextField txtRedirectPort;
	private JButton btnRedirect;
	private boolean redirecting;
	private PortRedirector pr;
	
	public MainWindow(){
		super("Port Redirector v1.0 by Raydelto");		
		setSize(600,75);
		setResizable(false);
		setLayout(new FlowLayout());
		lblLocalPort = new JLabel("Local Port");
		lblRedirectPort = new JLabel("Redirect to Port");
		txtLocalPort = new JTextField(10);
		txtLocalPort.setPreferredSize(new Dimension(60,20));		
		txtRedirectPort = new JTextField(10);
		txtRedirectPort.setPreferredSize(new Dimension(60,20));
		txtRedirectPort.setSize(200, 10);
		btnRedirect = new JButton("Redirect");
		btnRedirect.setBackground(Color.gray);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		NumericKeyListener numericListener = new NumericKeyListener();		
		txtLocalPort.addKeyListener(numericListener);
		txtRedirectPort.addKeyListener(numericListener);

		btnRedirect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!redirecting){
					pr = new PortRedirector(Integer.parseInt(txtLocalPort.getText()), Integer.parseInt(txtRedirectPort.getText()));					
					redirecting = true;
					btnRedirect.setText("redirecting");
					btnRedirect.setBackground(Color.green);
					txtLocalPort.setEditable(false);
					txtRedirectPort.setEditable(false);					
					pr.start();
					setTitle("Port Redirector v1.0 by Raydelto: Redirecting port " +txtLocalPort.getText() + " to " + txtRedirectPort.getText());
				}else{					
					if (JOptionPane.showConfirmDialog(MainWindow.this, "Do you confirm you want to stop redirecting?") == 0){
						redirecting = false;
						pr.setDisabled(true);
						btnRedirect.setBackground(Color.gray);
						btnRedirect.setText("Redirect");
						txtLocalPort.setEditable(true);
						txtRedirectPort.setEditable(true);	
						setTitle("Port Redirector v1.0 by Raydelto");
					}
				}				
			}			
		});	
		add(lblLocalPort);
		add(txtLocalPort);
		add(lblRedirectPort);		
		add(txtRedirectPort);
		add(btnRedirect);
		setVisible(true);
	}
}
