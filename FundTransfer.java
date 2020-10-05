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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
 
@SuppressWarnings("serial")
public class FundTransfer extends JFrame implements ActionListener
{
	JLabel name,l1,l2,l3;
	JTextField tf,tf1,tf2;
	JButton b,b1;
	
	public void fund()
	{
		super.setBounds(10, 10, 400, 400);
		super.setTitle("Fund Transfer");
		
		name = new JLabel("Hello,"+Login.name);
		name.setBounds(40, 11, 300, 25);
		name.setFont(new Font("Times New Roman", Font.BOLD, 14));
		name.setForeground(Color.BLUE);
		super.add(name);
		
		
		l1=new JLabel("From #Account no.");l1.setBounds(10, 60, 300, 30);super.add(l1);
		
		tf =new JTextField();tf.setBounds(120,60, 240, 30);
		tf.setText(String.valueOf(Menu.account_no));
		tf.setEditable(false);super.add(tf);
		
		l2=new JLabel("To #Account no.");l2.setBounds(10, 110, 300, 30);super.add(l2);
		
		tf1=new JTextField();tf1.setBounds(120,110, 240, 30);super.add(tf1);
		
		l3=new JLabel("Amount in Rs.");l3.setBounds(10, 160, 300, 30);super.add(l3);
		
		tf2=new JTextField();tf2.setBounds(120,160, 240, 30);super.add(tf2);
		
		b=new JButton(" >>Transfer<< ");b.setBounds(130, 220, 180, 30);super.add(b);b.addActionListener(this);
		
		b1=new JButton("Back to Home");b1.setBounds(130, 280, 180, 30);super.add(b1);b1.addActionListener(this);
		
		
		super.setLayout(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) 
	{
		FundTransfer f=new FundTransfer();
		f.fund();

	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource()==b1)
		{
			Menu m =new Menu();
			m.menuu();
			super.dispose();
		}
		
		if(e.getSource()==b)
		{		
		 if(Integer.parseInt(tf2.getText()) < 100)
		{
			JOptionPane.showMessageDialog(null, "Amount of transffer must be greater than 100 rupees.");
		}
		else
		{
			
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","root");
					Statement stmt1 = con.createStatement();
					
					ResultSet rs1 = stmt1.executeQuery("select balance from account_details where Account_no = '"+Menu.account_no+"'");  
					if(rs1.next())
					{
						int balance1 = rs1.getInt(1);
						if(Integer.parseInt(tf2.getText()) > balance1)
						{
							JOptionPane.showMessageDialog(null, "Your account balance is "+balance1+" rupees. You cannot transffer greater than your balance.");
						}
						else
						{
							balance1 = balance1-Integer.parseInt(tf2.getText());
							Statement stmt2 = con.createStatement();
							ResultSet rs2 = stmt2.executeQuery("select Balance from account_details where Account_no = '"+tf1.getText()+"'");
							
							if(rs2.next())
							{
								int balance2 = rs2.getInt(1)+Integer.parseInt(tf2.getText());
								stmt2.executeUpdate("update account_details SET Balance = '"+balance1+"' where Account_no = '"+Menu.account_no+"'");
								JOptionPane.showMessageDialog(null, "Amount "+tf2.getText()+" rupees has been transffered from your account. Your updated account balance is "+balance1+" rupees.");
								
								stmt2.executeUpdate("update account_details SET Balance = '"+balance2+"' where Account_no = '"+tf1.getText()+"'");
								
								String transactionId = "TBT";
								Random r = new Random();
								for(int i = 1; i <= 12; i++)
								{
									transactionId += r.nextInt(9);
								}
								
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("dd'-'MMM'-'YYYY hh':'mm a");
								
								stmt2.executeUpdate("insert into Transaction_details values('"+transactionId+"','"+Menu.account_no+"','"+Integer.parseInt(tf2.getText())+"','Debit','"+sdf.format(date)+"')");
								stmt2.executeUpdate("insert into Transaction_details values('"+transactionId+"','"+tf1.getText()+"','"+Integer.parseInt(tf2.getText())+"','Credit','"+sdf.format(date)+"')");
								
								Menu m =new Menu();
								m.menuu();
								super.dispose();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Receiver's account no. not found. Please check and try again.");
							}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Receiver's account no. not found. Please check and try again.");
					}
				} catch (NumberFormatException | HeadlessException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}
	
		
	
}}