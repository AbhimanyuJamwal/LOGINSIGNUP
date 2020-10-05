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
public class Withdraw extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,hello;
	JTextField tf,tf1,tf2;
	JButton b,b1;

	public void draw()
	{
		super.setBounds(10,10,400,400);
		super.setTitle("Withdraw Form");
	
		hello = new JLabel("Hello, "+Login.name);
		hello.setBounds(130, 11, 300, 23);
		hello.setFont(new Font("Times New Roman", Font.BOLD, 18));
		hello.setForeground(Color.BLUE);
		super.add(hello);
		
		l1=new JLabel("#Account no.");l1.setBounds(20, 40, 100, 40);super.add(l1);
	
		tf=new JTextField();tf.setBounds(110, 50, 220, 20);
		tf.setText(String.valueOf(Menu.account_no));
		tf.setEditable(false);super.add(tf);
		
		l2=new JLabel("#Debit Amount");l2.setBounds(20,80,100,40);super.add(l2);
		
		tf1=new JTextField();tf1.setBounds(110, 90, 220, 20);super.add(tf1);
		
		b=new JButton("Submit");b.setBounds(150, 150, 100, 40);super.add(b);b.addActionListener(this);
		
		b1=new JButton("Cancel");b1.setBounds(150,200,100,40);super.add(b1);b1.addActionListener(this);
		
		super.setLayout(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) throws Exception
	{
		Withdraw w=new Withdraw();
		w.draw();
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
		
		else if(e.getSource()==b)
		{
			String amount=tf1.getText();
			if(Integer.parseInt(amount) < 100)
			{
				JOptionPane.showMessageDialog(null, "Amount of withdraw must be greater than 100 rupees.");
			}
			else
			{
				
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","root");
						Statement stmt = con.createStatement();
						
						ResultSet rs = stmt.executeQuery("select Balance from account_details where Account_no = '"+Menu.account_no+"'");
						if(rs.next())
						{
							int balance = rs.getInt(1);
							if(Integer.parseInt(tf1.getText()) > balance)
							{
								JOptionPane.showMessageDialog(null, "Your account balance is "+balance+" rupees. You cannot withdraw amount greater than your balance.");
							}
							else
							{
								balance = rs.getInt(1)-Integer.parseInt(tf1.getText());
								stmt.executeUpdate("update account_details SET Balance = '"+balance+"' where Account_no = '"+Menu.account_no+"'");
								
								String transactionId = "TBW";
								Random r = new Random();
								for(int i = 1; i <= 12; i++)
								{
									transactionId += r.nextInt(9);
								}
								
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("dd'-'MMM'-'YYYY hh':'mm a");
								
								stmt.executeUpdate("insert into Transaction_details values('"+transactionId+"','"+Menu.account_no+"','"+Integer.parseInt(tf1.getText())+"','Debit','"+sdf.format(date)+"')");
								
								JOptionPane.showMessageDialog(null, "Amount "+tf1.getText()+" rupees has been withdrawn from your account. Your updated account balance is "+balance+" rupees.");
								
								super.dispose();
								Menu m =new Menu();
								m.menuu();
								}
						



						}
					} catch (NumberFormatException | HeadlessException | ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				}
				}
	}
}
