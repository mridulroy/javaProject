package mridul_personal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Personal_GmbH {

	private JFrame LoginPage;
	private JTextField un;
	private JPasswordField pass;
	
	Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Personal_GmbH window = new Personal_GmbH();
					window.LoginPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Personal_GmbH() {
		initialize();
		connection = SqliteAdmin.dbconnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LoginPage = new JFrame();
		LoginPage.setTitle("Mridul Personal GmbH --> Login");
		LoginPage.setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Learning Java\\New Java GUI Projects\\Images\\Bangladesh-Flag-icon.png"));
		LoginPage.setBounds(100, 100, 450, 300);
		LoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginPage.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setIcon(new ImageIcon("G:\\Learning Java\\New Java GUI Projects\\Images\\ok-icon.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					String query = "select * from AdminInfo where username=? and password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, un.getText());
					pst.setString(2, pass.getText());
					ResultSet rs = pst.executeQuery();
					boolean success = false;
					while (rs.next()) {
						success = true;
					}
					if (success) {
						LoginPage.dispose();
						EmployeeInfo empinfo = new EmployeeInfo();
						empinfo.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Wrong Username or Password !!!");

					}
					
					pst.close();
					rs.close();
						
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e);
				}	
				
			
			}
		});
		btnNewButton.setBounds(272, 174, 123, 57);
		LoginPage.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("G:\\Learning Java\\New Java GUI Projects\\Images\\Login-icon.png"));
		label.setBounds(21, 49, 141, 145);
		LoginPage.getContentPane().add(label);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsername.setBounds(172, 92, 69, 23);
		LoginPage.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(172, 126, 69, 23);
		LoginPage.getContentPane().add(lblPassword);
		
		un = new JTextField();
		un.setFont(new Font("Tahoma", Font.BOLD, 13));
		un.setBounds(263, 94, 132, 20);
		LoginPage.getContentPane().add(un);
		un.setColumns(10);
		
		pass = new JPasswordField();
		pass.setFont(new Font("Tahoma", Font.BOLD, 13));
		pass.setBounds(263, 127, 132, 23);
		LoginPage.getContentPane().add(pass);
	}
}
