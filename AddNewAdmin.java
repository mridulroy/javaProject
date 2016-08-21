package mridul_personal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddNewAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField un;
	private JPasswordField pass;
	private JPasswordField conpasss;
	Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewAdmin frame = new AddNewAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddNewAdmin() {
		connection = SqliteAdmin.dbconnector();
		setTitle("Create New Admin");
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Learning Java\\New Java GUI Projects\\Images\\profile-icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 237);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserName = new JLabel("Enter User Name");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUserName.setBounds(24, 21, 122, 32);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Enter Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(24, 59, 108, 32);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblConfirmPassword.setBounds(24, 97, 122, 32);
		contentPane.add(lblConfirmPassword);
		
		un = new JTextField();
		un.setFont(new Font("Tahoma", Font.BOLD, 13));
		un.setBounds(166, 26, 169, 25);
		contentPane.add(un);
		un.setColumns(10);
		
		pass = new JPasswordField();
		pass.setFont(new Font("Tahoma", Font.BOLD, 13));
		pass.setBounds(166, 64, 169, 25);
		contentPane.add(pass);
		
		conpasss = new JPasswordField();
		conpasss.setFont(new Font("Tahoma", Font.BOLD, 13));
		conpasss.setBounds(166, 104, 169, 25);
		contentPane.add(conpasss);
		
		JButton btnCreateAdmin = new JButton("Create Admin");
		btnCreateAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(!un.getText().equals("") && !pass.getText().equals("") && !conpasss.getText().equals("")){
						String query = "insert into AdminInfo (username,password) values (?,?);";
						PreparedStatement pst = connection.prepareStatement(query);
						boolean success = false;
						if(pass.getText().equals(conpasss.getText())){
							success = true;
						}
						
						if (success) {
							pst.setString(1, un.getText());
							pst.setString(2, pass.getText());
							pst.execute();
							pst.close();
							JOptionPane.showMessageDialog(null, "New Admin Created Successfully!!! The Window will close now");
							System.exit(JFrame.DISPOSE_ON_CLOSE);
						} else {
							JOptionPane.showMessageDialog(null, "Password mismatch");
	
						}
						
						
					}
					else{
						JOptionPane.showMessageDialog(null, "Field can not be empty !!!");
					}
						
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e);
				}	
			}
		});
		btnCreateAdmin.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCreateAdmin.setBounds(203, 154, 132, 34);
		contentPane.add(btnCreateAdmin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCancel.setBounds(24, 154, 108, 34);
		contentPane.add(btnCancel);
	}
}
