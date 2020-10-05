package LOGINSIGNUP;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Asn extends JFrame implements ActionListener
{
	JButton b, b1;
	JLabel l;
	public void as() 
	{
	super.setBounds(100, 100, 500,500);	
	super.setVisible(true);
	super.setTitle("Banking  Application");
	super.setResizable(false);
	
	/*
	l=new JLabel("Banking Application");l.setBounds(20, 10, 150, 50);
	l.setFont(new Font("Times New Roman", Font.BOLD, 14));
	l.setForeground(Color.BLUE);super.add(l);
	*/
	
	b=new JButton("SIGNUP");b.setBounds(20, 90, 430, 150);super.add(b);
	b.addActionListener(this);
	
	b1 =new JButton("LOGIN");b1.setBounds(20,280,430,150);	super.add(b1);
	b1.addActionListener(this);
	
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{ 		
			if(e.getSource()== b)
		{
			SignUp s =new SignUp();
				
				
				try {
					s.initUI();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				super.dispose();
		}
		else if(e.getSource()==b1)
		{
			Login l =new Login();
			l.log();
			super.dispose();
			
		}
		}
		}