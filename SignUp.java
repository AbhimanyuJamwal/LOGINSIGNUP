package LOGINSIGNUP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SignUp extends JFrame implements ActionListener
	{
		JLabel lbl1,lbl2,lbl3,lbl4,lbl5;
		JTextField tf1;
		JTextField tf2;
		JTextField tf3;
		JTextField tf4;
		JTextField tf5;
		JButton button;
		
		
		public void initUI() 
		throws ClassNotFoundException, SQLException
		{
			super.setBounds(100, 50, 500, 370);
			super.setTitle("Sign-Up");
			super.setResizable(false);
			
			
			lbl1 = new JLabel("Name:");lbl1.setBounds(70, 50, 200, 30);super.add(lbl1);
			lbl2 = new JLabel("Email:");lbl2.setBounds(70, 90, 200, 30);super.add(lbl2);
			lbl3 = new JLabel("Mobile:");lbl3.setBounds(70, 130, 200, 30);super.add(lbl3);
			lbl4 = new JLabel("Address:");lbl4.setBounds(70, 170, 200, 30);super.add(lbl4);
			lbl5 = new JLabel("Password:");lbl5.setBounds(70, 210, 200, 30);super.add(lbl5);
			
			
			tf1 = new JTextField();tf1.setBounds(200, 50, 250, 30);super.add(tf1);
			tf2 = new JTextField();tf2.setBounds(200, 90, 250, 30);super.add(tf2);
			tf3 = new JTextField();tf3.setBounds(200, 130, 250, 30);super.add(tf3);
			tf4 = new JTextField();tf4.setBounds(200, 170, 250, 30);super.add(tf4);
			tf5 = new JTextField();tf5.setBounds(200, 210, 250, 30);super.add(tf5);
					
			
			button = new JButton("SignUp");button.setBounds(190, 270, 80, 30);super.add(button);
			button.addActionListener(this);	
			
			
			
			super.setLayout(null);
			super.setVisible(true);
			super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

			public static void main(String[] args)
			throws Exception {
			SignUp s=new SignUp();
			s.initUI();
			
		}
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource() == button)
			{
				String Name=tf1.getText(), Email=tf2.getText(), Mobile=tf3.getText(), Address=tf4.getText(), Password=tf5.getText();
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					//get connection
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","root");
					
					//get statement
					Statement st=con.createStatement();
			
					st.executeQuery("use register");
					
					try {
						st.executeUpdate("insert into login values('"+Name+"','"+Email+"','"+Mobile+"','"+Address+"','"+Password+"')");
					} catch (Exception E) {
						// TODO Auto-generated catch block
						E.printStackTrace();
					}
		} catch (ClassNotFoundException | SQLException E) {
			// TODO Auto-generated catch block
			E.printStackTrace();
		}
									
				if(Name.isEmpty() || Email.isEmpty() || Mobile.isEmpty() || Address.isEmpty() && Password.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Enter required field");
					Asn ref=new Asn();
					ref.as();
					super.dispose();
				}
				
				
				else
				{
				JOptionPane.showMessageDialog(null, "You have successfully registered. Now, you are redirecting to home page");
				Asn ref=new Asn();
				ref.as();
				super.dispose();
			}		
			}
			}
			}