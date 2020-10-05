package LOGINSIGNUP;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Menu extends JFrame implements ActionListener
{
	JButton b,b1,b2,b3,b4,b5,b6,b7,b8;
	static String account_no;
	JLabel hello;
	public void menuu()
	{
		super.setBounds(10, 10, 500, 620);
		super.setTitle("Dashboard");
	
		hello = new JLabel("Hello, "+Login.name);
		hello.setBounds(40, 11, 300, 23);
		hello.setFont(new Font("Times New Roman", Font.BOLD, 18));
		hello.setForeground(Color.BLUE);
		super.add(hello);
		
		b =new JButton("Open Account");b.setBounds(30, 30, 400, 50);super.add(b);b.addActionListener(this);
		
		b1 =new JButton("Deposit");b1.setBounds(30, 90, 400, 50);super.add(b1);b1.addActionListener(this);
		
		b2 =new JButton("Withdraw");b2.setBounds(30, 150, 400, 50);super.add(b2);b2.addActionListener(this);
		
		b3 =new JButton("Check Balance");b3.setBounds(30, 210, 400, 50);super.add(b3);b3.addActionListener(this);
		
		b4 =new JButton("Fund Transfer");b4.setBounds(30, 270, 400, 50);super.add(b4);b4.addActionListener(this);
		
		b5 =new JButton("Deactivate Account");b5.setBounds(30, 330, 400, 50);super.add(b5);b5.addActionListener(this);
		
		b6 =new JButton("Reactivate Account");b6.setBounds(30, 390, 400, 50);super.add(b6);b6.addActionListener(this);
		
		b7 =new JButton("Statement");b7.setBounds(30, 450, 400, 50);super.add(b7);b7.addActionListener(this);
		
		b8 =new JButton("LogOut");b8.setBounds(30, 510, 400, 50);super.add(b8);b8.addActionListener(this);
		super.setLayout(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args)
	{
	Menu m =new Menu();
	m.menuu();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
	
			
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","root");
					Statement stmt = con.createStatement();
					stmt.executeQuery("use register");
	stmt.executeUpdate("create table if not exists register.account_details(account_no varchar(255),bank_name varchar(255) NOT NULL,balance int(100) NOT NULL,email varchar(255) UNIQUE NOT NULL,status varchar(255) NOT NULL,PRIMARY KEY(Account_no))");
					
	stmt.executeUpdate("create table if not exists register.transaction_details(transaction_id varchar(255) NOT NULL,account_no varchar(255) NOT NULL,amount int(100) NOT NULL,type varchar(255) NOT NULL,date varchar(255) NOT NULL)");
					
					ResultSet rs = stmt.executeQuery("select Account_no,Balance,Status from account_details where Email = '"+Login.mail+"'");
if(e.getSource()==b) 
{
					if(rs.next())
					{
						JOptionPane.showMessageDialog(null, "You can't open another account.");
					}
					else
					{
					OpenAccount oa =new OpenAccount();
					oa.open();
					super.dispose();			
					}
}	
else if(e.getSource() == b1 || e.getSource() == b2 || e.getSource() == b3 || e.getSource() == b4 || e.getSource() == b5 || e.getSource() == b6 || e.getSource() == b7 || e.getSource() == b8)
{
if(rs.next())
{
account_no=rs.getString(1);
if(e.getSource()==b1 ||e.getSource()==b2||e.getSource()==b4)
{
if(rs.getString(3).equalsIgnoreCase("Deactive"))
{
					JOptionPane.showMessageDialog(null, "You account is deactivated. Please activate your account.");
}
else
{
						if(e.getSource()==b1)
						{
							Deposit d =new Deposit();
							d.dep();
							super.dispose();
						}

						else if(e.getSource()==b2)
						{
							Withdraw w=new Withdraw();
							w.draw();
							super.dispose();
						}


						else if(e.getSource()==b4)
						{
							FundTransfer f=new FundTransfer();
							f.fund();
							super.dispose();
						}
}
}
else if(e.getSource()==b3)
{
					JOptionPane.showMessageDialog(null, "Your account balance is "+rs.getInt(2)+" rupees.");
}
else if(e.getSource()==b6)
{
					if(rs.getString(3).equalsIgnoreCase("Deactive"))
					{
						stmt.executeUpdate("update account_details SET Status = 'Active' where Email = '"+Login.mail+"'");
						JOptionPane.showMessageDialog(null, "Your account is activated.");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Your account is already activated.");
					}
}

else if(e.getSource()==b5)
{
					if(rs.getString(3).equalsIgnoreCase("Active"))
					{
						stmt.executeUpdate("update account_details SET Status = 'Deactive' where Email = '"+Login.mail+"'");
						JOptionPane.showMessageDialog(null, "Your account is deactivated.");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Your account is already deactivated.");
					}
}

else if(e.getSource()==b7)
{
					State st=new State();
					st.stat();
					super.dispose();
}

else if(e.getSource()==b8)
{
	Login l = new Login();
	l.log();
	super.dispose();
}
}
}


else
{
JOptionPane.showMessageDialog(null, "You have to open an account first.");
}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
}
}