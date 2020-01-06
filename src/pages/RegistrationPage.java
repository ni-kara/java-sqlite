package pages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import users.ChooseUserCategory;
import users.UserSubmit;

public class RegistrationPage {

	public static void Registration() {

		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		frame.setSize(250, 400); // Size windows
		// Position windows
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frame.getBounds().width) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - frame.getBounds().height) / 2);
		frame.setResizable(false); // No resize windows
		frame.setTitle("Registration"); // Title

		// Panel Settings
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);

		JLabel lRegistration = new JLabel();
		lRegistration.setText("Registration");
		lRegistration.setFont(new Font("Arial", Font.BOLD, 14));
		lRegistration.setSize(90, 15);
		lRegistration.setLocation((frame.getWidth() - lRegistration.getWidth()) / 2, 10);
		
		JLabel lFirstName = new JLabel();
		lFirstName.setText("First Name:");
		lFirstName.setFont(new Font("Arial", Font.PLAIN, 12));
		lFirstName.setSize(70, 15);
		lFirstName.setLocation((frame.getWidth() - lFirstName.getWidth()) / 2, lRegistration.getLocation().y + lRegistration.getSize().height + 15);

		JTextField tfFirstName = new JTextField();
		tfFirstName.setFont(new Font("Arial", Font.PLAIN, 11));
		tfFirstName.setDocument(new JTextFieldLimit(25));
		tfFirstName.setSize(90, 20);
		tfFirstName.setLocation((frame.getWidth() - tfFirstName.getWidth()) / 2, lFirstName.getLocation().y + lFirstName.getSize().height);

		JLabel lLastName = new JLabel();
		lLastName.setText("Last Name:");
		lLastName.setFont(new Font("Arial", Font.PLAIN, 12));
		lLastName.setSize(70, 15);
		lLastName.setLocation((frame.getWidth() - lLastName.getWidth()) / 2, tfFirstName.getLocation().y + tfFirstName.getSize().height + 15);

		JTextField tfLastName = new JTextField();
		tfLastName.setFont(new Font("Arial", Font.PLAIN, 11));
		tfLastName.setDocument(new JTextFieldLimit(25));
		tfLastName.setSize(90, 20);
		tfLastName.setLocation((frame.getWidth() - tfLastName.getWidth()) / 2, lLastName.getLocation().y + lLastName.getSize().height);

		JLabel lUsername = new JLabel();
		lUsername.setText("Username:");
		lUsername.setFont(new Font("Arial", Font.PLAIN, 12));
		lUsername.setSize(65, 15);
		lUsername.setLocation((frame.getWidth() - lUsername.getWidth()) / 2, tfLastName.getLocation().y + tfLastName.getSize().height + 15);

		JTextField tfUsername = new JTextField();
		tfUsername.setFont(new Font("Arial", Font.PLAIN, 11));
		tfUsername.setDocument(new JTextFieldLimit(25));
		tfUsername.setSize(90, 20);
		tfUsername.setLocation((frame.getWidth() - tfUsername.getWidth()) / 2, lUsername.getLocation().y + lUsername.getSize().height);

		JLabel lPassword = new JLabel();
		lPassword.setText("Password:");
		lPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		lPassword.setSize(60, 15);
		lPassword.setLocation((frame.getWidth() - lPassword.getWidth()) / 2, tfUsername.getLocation().y + tfUsername.getSize().height + 15);

		JPasswordField tfPassword = new JPasswordField();
		tfPassword.setFont(new Font("Arial", Font.PLAIN, 11));
		tfPassword.setSize(90, 20);
		tfPassword.setLocation((frame.getWidth() - tfPassword.getWidth()) / 2, lPassword.getLocation().y + lPassword.getSize().height);

		JLabel lCategory = new JLabel();
		lCategory.setText("Category:");
		lCategory.setFont(new Font("Arial", Font.PLAIN, 12));
		lCategory.setSize(60, 15);
		lCategory.setLocation((frame.getWidth() - lCategory.getWidth()) / 2, tfPassword.getLocation().y + tfPassword.getSize().height + 15);

		JRadioButton JBseller = new JRadioButton();
		JBseller.setText("Seller");
		JBseller.setFont(new Font("Arial", Font.PLAIN, 11));
		JBseller.setBackground(panel.getBackground());
		JBseller.setSize(60, 15);
		JBseller.setLocation(((frame.getWidth() - JBseller.getWidth()) / 2) - JBseller.getWidth() / 2, lCategory.getLocation().y + lCategory.getSize().height);

		JRadioButton JBcustomer = new JRadioButton();
		JBcustomer.setText("Customer");
		JBcustomer.setFont(new Font("Arial", Font.PLAIN, 11));
		JBcustomer.setBackground(panel.getBackground());
		JBcustomer.setSize(75, 15);
		JBcustomer.setLocation(((frame.getWidth() - JBcustomer.getWidth()) / 2) + JBseller.getWidth() / 2, lCategory.getLocation().y + lCategory.getSize().height);

		ButtonGroup bg = new ButtonGroup();
		bg.add(JBseller);
		bg.add(JBcustomer);

		JButton btnSubmit = new JButton();
		btnSubmit.setText("Submit");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 10));
		btnSubmit.setBackground(Color.LIGHT_GRAY);
		btnSubmit.setSize(90, 20);
		btnSubmit.setLocation((frame.getWidth() - tfPassword.getWidth()) / 2, JBcustomer.getLocation().y + JBcustomer.getSize().height + 20);
		btnSubmit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String errors = "";

				errors = UserSubmit.addUser(tfFirstName.getText(), tfLastName.getText(), tfUsername.getText(), String.valueOf(tfPassword.getPassword()), ChooseUserCategory.UserCategory(JBseller, JBcustomer));

				if (errors.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Succefull Registration", "Succefull",	JOptionPane.INFORMATION_MESSAGE);
					LoginPage.Login();
					frame.dispose();
				} else
					JOptionPane.showMessageDialog(frame, errors, "Errors", JOptionPane.ERROR_MESSAGE);
			}
		});

		JButton btnIHaveAcc = new JButton();
		btnIHaveAcc.setText("I have account");
		btnIHaveAcc.setFont(new Font("Arial", Font.PLAIN, 8));
		btnIHaveAcc.setBackground(Color.LIGHT_GRAY);
		btnIHaveAcc.setSize(90, 20);
		btnIHaveAcc.setLocation((frame.getWidth() - tfPassword.getWidth()) / 2, btnSubmit.getLocation().y + btnSubmit.getSize().height + 20);
		btnIHaveAcc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				LoginPage.Login();
				frame.dispose();
			}
		});

		panel.add(lRegistration);

		panel.add(lFirstName);
		panel.add(tfFirstName);

		panel.add(lLastName);
		panel.add(tfLastName);

		panel.add(lUsername);
		panel.add(tfUsername);

		panel.add(lPassword);
		panel.add(tfPassword);

		panel.add(lCategory);
		panel.add(JBseller);
		panel.add(JBcustomer);

		panel.add(btnSubmit);
		panel.add(btnIHaveAcc);

		frame.add(panel);
		frame.setVisible(true); // Visibility ON
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
