package LOGINSIGNUP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.mail.Transport;
@SuppressWarnings("serial")
public class OpenAccount extends JFrame implements ActionListener
{
	
	JComboBox <String> c;
	JLabel l,l1,l2,l3,l4;
	JTextField tf;
	JButton b,b1;
	
	@SuppressWarnings({ })
	public void open()
	{
		super.setBounds(10, 10, 400, 400);
		super.setTitle("Open Account");
		
		l=new JLabel("Welcome to your account.....");l.setBounds(80, 10, 200, 30);super.add(l);
		
		l1=new JLabel("Bank ");l1.setBounds(20, 60, 200, 40);super.add(l1);
		
		String s[]= {"SBI","PNB","KMB","HDFC"};
		c =new JComboBox<String>(s);c.setBounds(80, 60, 200, 30);super.add(c);c.addActionListener(this);
		
		l2=new JLabel("Amount");l2.setBounds(20, 110, 200, 40);super.add(l2);
		
		l3=new JLabel("");l3.setBounds(300, 60, 300, 40);super.add(l3);
		
		tf =new JTextField("");tf.setBounds(80, 105, 200, 30);super.add(tf);

		l4=new JLabel("");l4.setBounds(280, 130, 300, 40);super.add(l4);
		b=new JButton("Open Account");b.setBounds(100, 160, 150, 30);super.add(b);b.addActionListener(this);
		
		b1=new JButton("Back");b1.setBounds(100, 200, 150, 30);super.add(b1);b1.addActionListener(this);
		super.setLayout(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) throws IOException
	{
		OpenAccount oa =new OpenAccount();
		oa.open();
	}
	
	class BabyAuthenticator extends Authenticator
	{
		public PasswordAuthentication getPasswordAuthentication()
		{
			
				PasswordAuthentication pa = new PasswordAuthentication("bankapplication2020@gmail.com", "Bank@2020");
				return pa;
			
		}
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
		
		if(e.getSource() == c) 
		        { 
		       l3.setText(c.getSelectedItem() + " selected"); 
		        }
		        
				
		else if(e.getSource()==b)
				{						
				String amount=tf.getText();	
			
					
					if(c.getSelectedItem().equals(c.getItemAt(0)))
					{
					if(Integer.parseInt(amount)<1000)
					{
						JOptionPane.showMessageDialog(null, "To open account with SBI minimum Opening Balance must be Rs.1000");
						Menu m =new Menu();
						m.menuu();
						super.dispose();
					
					}
					}
					
										
					if(c.getSelectedItem().equals(c.getItemAt(1)))
					{
						if(Integer.parseInt(amount)<=500)
						{
					JOptionPane.showMessageDialog(null, "To open account with PNB minimum Opening Balance must be Rs.500");
					Menu m =new Menu();
					m.menuu();
					super.dispose();
						
						}
					}
					
					if(c.getSelectedItem().equals(c.getItemAt(2)))
					{
						if(Integer.parseInt(amount)<=2000)
						{
						JOptionPane.showMessageDialog(null, "To open account with KMB minimum Opening Balance must be Rs.2000");
						Menu m =new Menu();
						m.menuu();
						super.dispose();
						
						}
					}
			
					if(c.getSelectedItem().equals(c.getItemAt(3)))
					{
						if(Integer.parseInt(amount)<=5000)
						{
						JOptionPane.showMessageDialog(null, "To open account with HDFC minimum Opening Balance must be Rs.5000");
						Menu m =new Menu();
						m.menuu();
						super.dispose();
						}					
					}		
					        
			else
			{
				try {
					
					
					
					Random r = new Random();
					Menu.account_no = String.valueOf(r.nextInt(100000000)+100000000);
					
					
						
							Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","root");
							
							Statement stmt = con.createStatement();
							
							stmt.executeUpdate("insert into account_details values('"+Menu.account_no+"','"+c.getSelectedItem()+"' ,'"+Integer.parseInt(amount)+"','"+Login.mail+"','Active')");
							
							String transactionId = "TOA";
							for(int a = 1; a <= 12; a++)
							{
								transactionId += r.nextInt(9);
							}
							
							Date date = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("dd'-'MMM'-'YYYY hh':'mm a");
							
							stmt.executeUpdate("insert into transaction_details values('"+transactionId+"','"+Menu.account_no+"','"+Integer.parseInt(amount)+"','Credit','"+sdf.format(date)+"')");
							
							Properties p = new Properties();
							p.put("mail.smtp.host","smtp.gmail.com");
							p.put("mail.smtp.port","587");
							p.put("mail.smtp.starttls.enable","true");
							p.put("mail.smtp.auth","true");
							p.put("mail.debug","true");
							p.put("mail.smtp.ssl.trust", "smtp.gmail.com");

							Session session = Session.getInstance(p, new BabyAuthenticator());

								MimeMessage message = new MimeMessage(session);
								
								InternetAddress adrs = new InternetAddress(Login.mail);


									message.setSubject("Account Details");
									message.addRecipient(RecipientType.TO, adrs);
									MimeBodyPart body1 = new MimeBodyPart();
									
									body1.setContent("<p style='color : black; font-size : 36;'>Hello! "+Login.name
											+".<br>Your bank account has been generated. Details are given below :-</p>"
											+"<br><p style='color : blue; font-size : 36;'>"
											+ "<br>Account No. - "+Menu.account_no
											+ "<br>Bank name   - "+c.getSelectedItem()
											+ "<br>Balance     - "+tf.getText()+"</p>","text/html");
									

									MimeMultipart multipart = new MimeMultipart();
									
									multipart.addBodyPart(body1);
									
									message.setContent(multipart);
									
									Transport.send(message);
					
									System.out.println("Email sent to mail server");
					
									javax.mail.Transport.send(message);
									
									JOptionPane.showMessageDialog(null, "Hello "+Login.name+", Your account has been generated with amount "+tf.getText()+" rupees. Your account number is "+Menu.account_no+". We have send full details of your account to your registered email '"+Login.mail+"'.");
									
									Menu m =new Menu();
									m.menuu();
									super.dispose();
					
					
						} 
				
						catch (NumberFormatException |ClassNotFoundException | SQLException| MessagingException e1) 
						{
							e1.printStackTrace();
						}
					}
		        }
}		
}