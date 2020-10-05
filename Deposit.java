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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Deposit extends JFrame implements ActionListener
{
	JLabel l,l1,l2,l3,timeLabel,hello;
	JTextField tf,tf1,tf2;
	JButton b,b1;
	
	public void dep()
	{
		super.setBounds(10, 10, 450, 350);
		super.setTitle("Deposit");
		
		
		
		hello = new JLabel("Hello, "+Login.name);
		hello.setBounds(130, 11, 300, 23);
		hello.setFont(new Font("Times New Roman", Font.BOLD, 20));
		hello.setForeground(Color.BLUE);
		super.add(hello);
		
		tf =new JTextField();tf.setBounds(120,75, 250, 20);
		tf.setText(String.valueOf(Menu.account_no));
		tf.setEditable(false);super.add(tf);
		
		
		l1=new JLabel("#Account no. ");l1.setBounds(30, 60, 300, 50);super.add(l1);
		
		l2=new JLabel("  #Amount ");l2.setBounds(22, 110, 300, 50);super.add(l2);
		
		tf1 =new JTextField("");tf1.setBounds(120,125, 250, 20);
		super.add(tf1);
		
		l3=new JLabel();l3.setBounds(22, 10, 300, 50);super.add(l3);
		
		timeLabel =new JLabel();timeLabel.setBounds(320, 2, 100, 550);
		final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        ActionListener timerListener = new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Date date = new Date();
                String time = timeFormat.format(date);
                timeLabel.setText("Time::" + time);
            }
        };
        Timer timer = new Timer(1000, timerListener);
        // to make sure it doesn't wait one second at the start
        timer.setInitialDelay(0);
        timer.start();
        timeLabel.setFont(new Font("Courier New", Font.BOLD, 12));
        super.add(timeLabel);
		
		b =new JButton("Deposit");b.setBounds(130, 170, 100, 50);super.add(b);b.addActionListener(this);
		
		b1 =new JButton("Cancel");b1.setBounds(250, 170, 100, 50);super.add(b1);b1.addActionListener(this);
		
		super.setLayout(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) 
	{
		Deposit d =new Deposit();
		d.dep();
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
			String account=tf.getText();
			String amount =tf1.getText();
			
						
			if(!account.isEmpty() && !amount.isEmpty())
			{
				if(Integer.parseInt(tf1.getText()) < 100)
				{
					JOptionPane.showMessageDialog(null, "Amount of deposit must be greater than 100 rupees.");
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
								int balance = rs.getInt(1)+Integer.parseInt(tf1.getText());
								stmt.executeUpdate("update account_details SET Balance = '"+balance+"' where Account_no = '"+Menu.account_no+"'");
								
								String transactionId = "TBD";
								Random r = new Random();
								for(int i = 1; i <= 12; i++)
								{
									transactionId += r.nextInt(9);
								}
								
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("dd'-'MMM'-'YYYY hh':'mm a");
								
								stmt.executeUpdate("insert into transaction_details values('"+transactionId+"','"+Menu.account_no+"','"+Integer.parseInt(tf1.getText())+"','Credit','"+sdf.format(date)+"')");
								
								JOptionPane.showMessageDialog(null, "Amount "+tf1.getText()+" rupees has been deposited in your account. Your updated account balance is "+balance+" rupees.");
								
								super.dispose();
								Menu m =new Menu();
								m.menuu();
								

}
else
{
JOptionPane.showMessageDialog(null, "please fill required fields..click ok to continue");
Menu m =new Menu();
m.menuu();
super.dispose();

}
						} catch (NumberFormatException | HeadlessException | ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
				}
		}
	}
}
