package LOGINSIGNUP;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class State extends JFrame implements ActionListener{
	JLabel l1,name;
	JTextField tf;
	JButton b,b1;
	JTextArea statements;
	public void stat()
	{
		super.setBounds(10, 10, 560, 450);
		super.setTitle("Statement");
	
		name = new JLabel("Hello,"+Login.name);
		name.setBounds(40, 11, 300, 25);
		name.setFont(new Font("Times New Roman", Font.BOLD, 14));
		name.setForeground(Color.BLUE);
		super.add(name);
		
		l1=new JLabel("#Account no.");l1.setBounds(30, 50, 100, 30);super.add(l1);
		
		
		tf =new JTextField();tf.setBounds(130,50, 320, 30);
		tf.setText(String.valueOf(Menu.account_no));
		tf.setEditable(false);super.add(tf);
		
		statements = new JTextArea();
		Component pane = new JScrollPane(statements);
		pane.setBounds(25, 130, 505, 200);
		statements.setEditable(false);
		super.add(pane);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/register","root","root");
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("select Transaction_id,Amount,Type,Date from Transaction_details where Account_no = '"+Menu.account_no+"'");
			while(rs.next())
			{
				statements.append(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\n========================================================================\n");
			}
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		
		b1=new JButton("Click Ok ");b1.setBounds(160, 350, 150, 30);super.add(b1);b1.addActionListener(this);
		
		super.setLayout(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
		
		
		
		
	public static void main(String[] args) 
	{
	State st=new State();
	st.stat();
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
	}

}
