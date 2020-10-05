package LOGINSIGNUP;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {
	JLabel l, l1, l2, l3;
	JButton b, b1;
	JTextField tf, tf1, tf2;
	static String name,mail,mobile;
	String Password;

	public void log() {
		super.setBounds(10, 10, 500, 500);
		super.setTitle(" Login ");
		super.setResizable(false);

		l = new JLabel("E-mail");
		l.setBounds(30, 10, 100, 80);
		super.add(l);

		tf = new JTextField();
		tf.setBounds(100, 30, 200, 40);
		super.add(tf);

		l1 = new JLabel("OR");
		l1.setBounds(180, 60, 100, 80);
		super.add(l1);

		l2 = new JLabel("Mobile ");
		l2.setBounds(30, 100, 100, 80);
		super.add(l2);

		tf1 = new JTextField();
		tf1.setBounds(100, 120, 200, 40);
		super.add(tf1);

		l3 = new JLabel("Password");
		l3.setBounds(30, 180, 100, 80);
		super.add(l3);

		tf2 = new JTextField();
		tf2.setBounds(100, 200, 200, 40);
		super.add(tf2);

		b = new JButton("login");
		b.setBounds(100, 250, 200, 60);
		super.add(b);
		b.addActionListener(this);

		b1 = new JButton(" Homepage ");
		b1.setBounds(100, 370, 200, 60);
		super.add(b1);
		b1.addActionListener(this);

		super.setLayout(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws Exception {
		Login l = new Login();
		l.log();
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==b1)
		{
			Asn ref =new Asn();
			ref.as();
			super.dispose();
		}
		String email2 = tf.getText(), mob = tf1.getText(), pass = tf2.getText();
		if (e.getSource() == b) {
			if ((!email2.isEmpty()) && (!mob.isEmpty())) {
				JOptionPane.showMessageDialog(null, "Enter Email or mobile");
			} else if (pass.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Enter password");
			}

			else {
				String email1 = tf.getText(), mobile1 = tf1.getText(), password1 = tf2.getText();

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register", "root","root");
					Statement st = con.createStatement();
					st.executeQuery("use register");
					String sql = "select * from login WHERE email='" + email1 + "' OR mobile= '"+mobile1+"'  AND password='" + password1 + "'";
					//String sql2 = "select * from login WHERE mobile='" + mobile1 + " ' AND password ='" + password1	+ "'";

					
					ResultSet rs = st.executeQuery(sql);
						//ResultSet rs1=st.executeQuery(sql2);
					
					while (rs.next())
					{
						name=rs.getString(1);
						mail=rs.getString(2);
						mobile=rs.getString(3);
						System.out.println("matching data" );
						if (((email1.equals(rs.getString("email"))) ||(mobile1.equals(rs.getString("mobile")))&&password1.equals(rs.getString("password"))))
								{
							System.out.println(" Data Matched ");

							JOptionPane.showMessageDialog(null, "logged in successfully...your login details are " + "\n" +"Name:" + rs.getString("name")+"\n"+
									"Email:"+rs.getString("email")+ "\n"+"Address:"+ rs.getString("address")+ "Mobile:" + rs.getString("mobile") );
												
							Menu m =new Menu();
							m.menuu();
							super.dispose();
							
						} 
						
						else {
							JOptionPane.showMessageDialog(null, "Invalid details");
						}
					}
				} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		}
	}