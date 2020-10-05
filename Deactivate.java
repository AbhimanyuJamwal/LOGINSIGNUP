package LOGINSIGNUP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Deactivate extends JFrame implements ActionListener{
	JLabel l,l1,l2;
	JTextField tf,tf1;
	JButton  b,b1;
	
	public void deact()
	{
		super.setBounds(10, 10, 400, 300);
		super.setTitle("De-Activate Account");
	
		l=new JLabel("Enter Account details");l.setBounds(130, 10, 160, 30);super.add(l);
		
		l1=new JLabel("#Account no.");l1.setBounds(10, 50, 100, 30);super.add(l1);
		
		tf=new JTextField();tf.setBounds(100, 50, 250, 30);super.add(tf);
		
		l2=new JLabel("#OTP");l2.setBounds(10, 90, 100, 30);super.add(l2);
		
		tf1 =new JTextField();tf1.setBounds(100, 90, 250, 30);super.add(tf1);
		
		b=new JButton("DeActivate");b.setBounds(140, 140, 150, 30);super.add(b);b.addActionListener(this);
		
		b1=new JButton("Back to Home");b1.setBounds(140, 180, 150, 30);super.add(b1);b1.addActionListener(this);
		super.setLayout(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) 
	{
		Deactivate d=new Deactivate();
		d.deact();

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
			JOptionPane.showMessageDialog(null, "Your account no. " + account + "  has been deactivated...");
			
			Menu m =new Menu();
			m.menuu();
			super.dispose();
		}
		
	}

}
