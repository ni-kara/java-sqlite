package pages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import users.User;
import users.UserSubmit;

public class LoginPage {


	public static void Login() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();		
		
			
		frame.setSize(250, 300); // Size windows
		//Position windows
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frame.getBounds().width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height - frame.getBounds().height)/2);
		frame.setResizable(false); // No resize windows
		frame.setTitle("Login"); // Title
		
		//Panel Settings
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);	
	
		JLabel lLogin = new JLabel();
		lLogin.setText("Login");
		lLogin.setFont(new Font("Arial", Font.BOLD, 14));
		lLogin.setSize(40,15);
		lLogin.setLocation((frame.getWidth()-lLogin.getWidth())/2,10);     			

		JLabel lUsername = new JLabel();
		lUsername.setText("Username:");
      	lUsername.setFont(new Font("Arial", Font.PLAIN, 12));
      	lUsername.setSize(65,15);
      	lUsername.setLocation((frame.getWidth()-lUsername.getWidth()) / 2, lLogin.getLocation().y + lLogin.getSize().height + 15);
      
      	JTextField tfUsername = new JTextField();
		tfUsername.setFont(new Font("Arial", Font.PLAIN, 11));
      	tfUsername.setDocument(new JTextFieldLimit(25));
      	tfUsername.setSize(90,20);
      	tfUsername.setLocation((frame.getWidth()-tfUsername.getWidth())/2, lUsername.getLocation().y + lUsername.getSize().height);
      	
      	JLabel lPassword = new JLabel();
		lPassword.setText("Password:");
      	lPassword.setFont(new Font("Arial", Font.PLAIN, 12));
      	lPassword.setSize(60,15);
      	lPassword.setLocation((frame.getWidth()-lPassword.getWidth())/2, tfUsername.getLocation().y + tfUsername.getSize().height + 15);
      
      	JPasswordField tfPassword = new JPasswordField();		
		tfPassword.setFont(new Font("Arial", Font.PLAIN, 11));
        tfPassword.setSize(90,20);
      	tfPassword.setLocation((frame.getWidth()-tfPassword.getWidth())/2, lPassword.getLocation().y + lPassword.getSize().height);
      	
      	JButton btnLogin = new JButton();
		btnLogin.setText("Login");
      	btnLogin.setFont(new Font("Arial", Font.PLAIN, 10));
      	btnLogin.setBackground(Color.LIGHT_GRAY);
      	btnLogin.setSize(90,20);
      	btnLogin.setLocation((frame.getWidth()-tfPassword.getWidth())/2, tfPassword.getLocation().y + tfPassword.getSize().height + 30);
      	btnLogin.addActionListener(new ActionListener() {
      		
      		public  void actionPerformed(ActionEvent e) {
      			List<Object> listOfObject= null;
      			String errors ="";
      			
      			listOfObject = UserSubmit.loginUser(tfUsername.getText(), String.valueOf(tfPassword.getPassword()));
      			
      			errors =  (String) listOfObject.get(0);
      			if(errors.isEmpty()) {
      				SellerPage.Seller((User) listOfObject.get(1));
					frame.dispose();
      			}
      			else
      				JOptionPane.showMessageDialog(frame, errors, "Errors", JOptionPane.ERROR_MESSAGE);
      		}
      	});
		
      	JButton btnCrAcc = new JButton();
		btnCrAcc.setText("Create new account");
      	btnCrAcc.setFont(new Font("Arial", Font.PLAIN, 9));
      	btnCrAcc.setBackground(Color.LIGHT_GRAY);
      	btnCrAcc.setSize(120,20);
      	btnCrAcc.setLocation((frame.getWidth()-btnCrAcc.getWidth())/2, btnLogin.getLocation().y + btnLogin.getSize().height + 30);
      	btnCrAcc.addActionListener(new ActionListener() {
      		
      		public void actionPerformed(ActionEvent e) {
      			RegistrationPage.Registration();
      			frame.dispose();
      			
      		}
      	});
      	
		panel.add(lLogin);
		
		panel.add(lUsername);
		panel.add(tfUsername);

  		panel.add(lPassword);
		panel.add(tfPassword);
  		
		panel.add(btnLogin);
		panel.add(btnCrAcc);
		
		frame.add(panel);		
		frame.setVisible(true); // Visibility ON			
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
}
