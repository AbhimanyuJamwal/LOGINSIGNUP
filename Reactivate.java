package LOGINSIGNUP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Reactivate extends JFrame implements ActionListener{
	JLabel l,l1,l2;
	JTextField tf,tf1;
	JButton b,b1;
	public void react()
	{
		super.setBounds(10, 10, 400, 300);
		super.setTitle("Re-Activate Account");
	
		l=new JLabel("Enter Details of Account");l.setBounds(130, 10, 160, 30);super.add(l);
		
		l1=new JLabel("#Account no.");l1.setBounds(10, 50, 100, 30);super.add(l1);
		
		tf=new JTextField();tf.setBounds(100, 50, 250, 30);super.add(tf);
		
		l2=new JLabel("#OTP");l2.setBounds(10, 90, 100, 30);super.add(l2);
		
		tf1 =new JTextField();tf1.setBounds(100, 90, 250, 30);super.add(tf1);
		
		b=new JButton("ReActivate");b.setBounds(120, 140, 200, 30);super.add(b);b.addActionListener(this);
		
		b1=new JButton("Back to Home");b1.setBounds(120, 180, 200, 30);super.add(b1);b1.addActionListener(this);
				
		super.setLayout(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) 
	{
	Reactivate r=new Reactivate();
	r.react();
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
			JOptionPane.showMessageDialog(null, "Your account has been reactivated");
			
			Menu m =new Menu();
			m.menuu();
			super.dispose();
		}
	}
}