package mridul_personal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class EmployeeInfo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	Connection conn = null;
	Connection conn2 = null;
	Connection conn3 = null;
	public static boolean ask = false;
	private JTextField fn;
	private JTextField ln;
	private JTextField dob;
	private JTextField mobile;
	private JTextField email;
	private JTextField address;
	private JTextField textField;
	private JLabel timelebel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeInfo frame = new EmployeeInfo();
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
	public void clock(){
		
		Thread timeclock = new Thread(){
			public void run(){
				try {
					while(true){
						
						Date date = new Date();
				        
					       // display time and date using toString()
						timelebel.setText(date.toString());
						sleep(1000);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
					// TODO: handle exception
				}
			}
		};
		timeclock.start();
		
	}
	
	public void refreshtable(){
		conn = SqliteConnection.dbconnector();
		conn2 = SqliteConnection.dbconnector();
		conn3 = SqliteConnection.dbconnector();
		String query = "select * from EmployeeInfo";
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			pst.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	
	public boolean checkEmail(String givenEmail){
		String EMAIL_REGEX = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
		return givenEmail.matches(EMAIL_REGEX);
	}
	
	public boolean checkBirthDate(String bdate){
		return bdate.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})");
		   
	}
	
	public boolean checkMobile(String givenmobile){
		return givenmobile.matches("\\d*");
		
	}
	
	public EmployeeInfo() {
		conn = SqliteConnection.dbconnector();
		conn2 = SqliteConnection.dbconnector();
		conn3 = SqliteConnection.dbconnector();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Learning Java\\New Java GUI Projects\\Images\\profile-icon.png"));
		setTitle("Mridul Personal GmbH");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 564);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmAdd = new JMenuItem("Create New Admin");
		mntmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddNewAdmin admin = new AddNewAdmin();
				admin.setVisible(true);
			}
		});
		mnFile.add(mntmAdd);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "Confirm", 1);
				
				if(option == JOptionPane.YES_OPTION ){
					System.exit(JFrame.EXIT_ON_CLOSE);
				}
			
			}
		});
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoadTable = new JButton("Load Emplyee Data");
		btnLoadTable.setIcon(new ImageIcon("G:\\Learning Java\\New Java GUI Projects\\Images\\system-software-update-icon.png"));
		btnLoadTable.setBackground(Color.CYAN);
		btnLoadTable.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshtable();
				
			}
		});
		btnLoadTable.setBounds(268, 21, 195, 34);
		contentPane.add(btnLoadTable);
		
		timelebel = new JLabel("New label");
		timelebel.setFont(new Font("Tahoma", Font.BOLD, 12));
		timelebel.setBounds(10, 22, 229, 34);
		contentPane.add(timelebel);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 824, 192);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFirstName.setBounds(30, 286, 72, 25);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLastName.setBounds(30, 336, 72, 25);
		contentPane.add(lblLastName);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSex.setBounds(30, 387, 31, 25);
		contentPane.add(lblSex);
		
		JLabel lblNationality = new JLabel("Nationality");
		lblNationality.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNationality.setBounds(360, 286, 72, 25);
		contentPane.add(lblNationality);
		
		JLabel lblOccupation = new JLabel("Occupation");
		lblOccupation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOccupation.setBounds(360, 336, 72, 25);
		contentPane.add(lblOccupation);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMobile.setBounds(360, 387, 72, 25);
		contentPane.add(lblMobile);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(360, 417, 72, 25);
		contentPane.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAddress.setBounds(360, 453, 72, 25);
		contentPane.add(lblAddress);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDateOfBirth.setBounds(30, 453, 88, 25);
		contentPane.add(lblDateOfBirth);
		
		fn = new JTextField();
		fn.setFont(new Font("Tahoma", Font.BOLD, 13));
		fn.setBounds(146, 289, 145, 22);
		contentPane.add(fn);
		fn.setColumns(10);
		
		ln = new JTextField();
		ln.setFont(new Font("Tahoma", Font.BOLD, 13));
		ln.setColumns(10);
		ln.setBounds(146, 339, 145, 22);
		contentPane.add(ln);
		
		dob = new JTextField();
		dob.setFont(new Font("Tahoma", Font.BOLD, 13));
		dob.setColumns(10);
		dob.setBounds(146, 453, 145, 24);
		contentPane.add(dob);
		
		mobile = new JTextField();
		mobile.setFont(new Font("Tahoma", Font.BOLD, 13));
		mobile.setColumns(10);
		mobile.setBounds(462, 387, 182, 22);
		contentPane.add(mobile);
		
		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.BOLD, 13));
		email.setColumns(10);
		email.setBounds(462, 419, 182, 20);
		contentPane.add(email);
		
		JComboBox sex = new JComboBox();
		sex.setFont(new Font("Tahoma", Font.BOLD, 13));
		sex.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Other"}));
		sex.setBounds(146, 387, 145, 25);
		contentPane.add(sex);
		
		JComboBox nation = new JComboBox();
		nation.setModel(new DefaultComboBoxModel(new String[] {"Abkhazia", "Afghanistan", "Akrotiri and Dhekelia", "Aland", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Ascension Island", "Australia", "Austria", "Azerbaijan", "Bahamas, The", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central Africa Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "Cote d'lvoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Polynesia", "Gabon", "Cambia, The", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guam", "Guatemala", "Guemsey", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, N", "Korea, S", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macao", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Nagorno-Karabakh", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Cyprus", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcaim Islands", "Poland", "Portugal", "Puerto Rico", "Qatar", "Romania", "Russia", "Rwanda", "Sahrawi Arab Democratic Republic", "Saint-Barthelemy", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin", "Saint Pierre and Miquelon", "Saint Vincent and Grenadines", "Samos", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "Somaliland", "South Africa", "South Ossetia", "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard", "Swaziland", "Sweden", "Switzerland", "Syria", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tokelau", "Tonga", "Transnistria", "Trinidad and Tobago", "Tristan da Cunha", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Virgin Islands, British", "Virgin Islands, U.S.", "Wallis and Futuna", "Yemen", "Zambia", "Zimbabwe"}));
		nation.setFont(new Font("Tahoma", Font.BOLD, 12));
		nation.setBounds(462, 285, 182, 26);
		contentPane.add(nation);
		
		JComboBox occupation = new JComboBox();
		occupation.setModel(new DefaultComboBoxModel(new String[] {"Student", "Doctor", "Professor", "Teacher", "Businessman", "Web Developer", "Software Developer"}));
		occupation.setFont(new Font("Tahoma", Font.BOLD, 12));
		occupation.setBounds(462, 337, 182, 22);
		contentPane.add(occupation);
		
		
		
		JButton btnSave = new JButton("Save");
		btnSave.setIcon(new ImageIcon("G:\\Learning Java\\New Java GUI Projects\\Images\\Save-icon.png"));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					
					if (fn.getText().equals("") || ln.getText().equals("") || dob.getText().equals("") || mobile.getText().equals("") || email.getText().equals("") || address.getText().equals("")){
			
						JOptionPane.showMessageDialog(null, "Field can not be empty");
						
					}
					else if(!checkBirthDate(dob.getText())){
						JOptionPane.showMessageDialog(null, "Please provide a valid format of your Birth Date");
					}
					else if(!checkMobile(mobile.getText())){
						JOptionPane.showMessageDialog(null, "Please provide a valid mobile number");
						
					}
					else if(!checkEmail(email.getText())){
						JOptionPane.showMessageDialog(null, "Please provide a valid Email address");
						
					}
					
					else{
						String find = "insert into EmployeeInfo (firstname, lastname, dateofbirth, sex, nationality, occupation, mobile, email, adress) values (?,?,?,?,?,?,?,?,?);";
						PreparedStatement preparedStatement = conn2.prepareStatement(find);
						
						// Taking Data
						preparedStatement.setString(1, fn.getText());
						preparedStatement.setString(2, ln.getText());
						preparedStatement.setString(3, dob.getText());
						preparedStatement.setString(4, (String) sex.getSelectedItem());
						preparedStatement.setString(5, (String) nation.getSelectedItem());
						preparedStatement.setString(6, (String) occupation.getSelectedItem());
						preparedStatement.setString(7, mobile.getText());
						preparedStatement.setString(8, email.getText());
						preparedStatement.setString(9, address.getText());
						
						// Saving Data
						preparedStatement.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Data Successfully Saved");
						
						preparedStatement.close();
						
						//Make the input boxes empty again 
						
						fn.setText(null);
						ln.setText(null);
						dob.setText(null);
						mobile.setText(null);
						email.setText(null);
						address.setText(null);
						
						// Refreshing data Table
						refreshtable();
						
					}
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnSave.setBounds(692, 300, 106, 37);
		contentPane.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0) {
				try {
					int selectedrow = table.getSelectedRow();
					
					
					if (selectedrow != -1){
						
							int option = JOptionPane.showConfirmDialog(null, "Are you sure to delete the selected data?", "Confirm", 1);
							
							if(option == JOptionPane.YES_OPTION ){
								
								// Deleting Data
								String name = (String) table.getValueAt(selectedrow, 0);
								String query = "delete from EmployeeInfo where firstname = '"+name+"' ";
								PreparedStatement pst = conn3.prepareStatement(query);
								pst.execute();
								JOptionPane.showMessageDialog(null, "Data Deleted !!!");
								
								// Refreshing Data table
								
								refreshtable();
							}
						
						
						}
					

					else{
						JOptionPane.showMessageDialog(null, "Please Select Row !!!");
					}
				} catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, ex);
				}
				
			}
		});
		btnDelete.setIcon(new ImageIcon("G:\\Learning Java\\New Java GUI Projects\\Images\\Editing-Delete-icon.png"));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(692, 360, 106, 37);
		contentPane.add(btnDelete);
		
		address = new JTextField();
		address.setFont(new Font("Tahoma", Font.BOLD, 12));
		address.setBounds(463, 453, 181, 25);
		contentPane.add(address);
		address.setColumns(10);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option2 = JOptionPane.showConfirmDialog(null, "Are you sure to Logout?", "Confirm", 1);
				
				if(option2 == JOptionPane.YES_OPTION ){
					System.exit(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogout.setBounds(692, 419, 106, 34);
		contentPane.add(btnLogout);
		
		JLabel lblSearchByName = new JLabel("Search By Name");
		lblSearchByName.setIcon(new ImageIcon("G:\\Learning Java\\New Java GUI Projects\\Images\\Search-icon.png"));
		lblSearchByName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSearchByName.setBounds(494, 22, 150, 34);
		contentPane.add(lblSearchByName);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String query = "select * from EmployeeInfo where firstname=?";
				try {
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textField.getText());
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField.setBounds(654, 22, 165, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDdmmyyyy = new JLabel("(dd/mm/yyyy)");
		lblDdmmyyyy.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDdmmyyyy.setBounds(30, 478, 106, 16);
		contentPane.add(lblDdmmyyyy);
		
		clock();	
		
	}
}
