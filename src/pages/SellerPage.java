package pages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import main.CreateIDs;
import products.Product;
import products.ProductSubmit;
import users.User;

public class SellerPage {

	public static void Seller(User user) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		frame.setSize(750, 325); // Size windows
		// Position windows
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frame.getBounds().width) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - frame.getBounds().height) / 2);
		frame.setResizable(false); // No resize windows
		frame.setTitle("Product Manager"); // Title

		// Panel Settings
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);

		JLabel lProductManager = new JLabel();
		lProductManager.setText("Product Manager");
		lProductManager.setFont(new Font("Arial", Font.BOLD, 14));
		lProductManager.setSize(130, 15);
		lProductManager.setLocation((frame.getWidth() - lProductManager.getWidth()) / 2, 10);

		JLabel lUsername = new JLabel();
		lUsername.setText("Hi: " + user.getUsername());
		lUsername.setFont(new Font("Arial", Font.PLAIN, 12));
		lUsername.setSize(80, 15);
		lUsername.setLocation(15,10);

		
		addProduct(user, frame,panel);		
		
		updateProduct(user, frame, panel);
		
		deleteProduct(user, frame,panel);
		
		JPanel line1 = new JPanel();
		line1.setSize(5, 750);
		line1.setBackground(Color.black);
		line1.setLocation(2*frame.getWidth()/3, 0);
	
		JPanel line2 = new JPanel();		
		line2.setSize(5, 750);
		line2.setBackground(Color.black);
		line2.setLocation(frame.getWidth()/3, 0);
	
		panel.add(lProductManager);
		panel.add(lUsername);
		
		panel.add(line1);
		panel.add(line2);

		frame.add(panel);
		
		frame.setVisible(true); // Visibility ON
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private static void addProduct(User user, JFrame frame, JPanel panel) {

		JLabel lTitle = new JLabel();
		lTitle.setText("Add Product");
		lTitle.setFont(new Font("Arial", Font.BOLD, 12));
		lTitle.setSize(85, 15);
		lTitle.setLocation((frame.getWidth() / 6) - lTitle.getWidth()/2, 30);

		JLabel lProductName = new JLabel();
		lProductName.setText("Username:");
		lProductName.setFont(new Font("Arial", Font.PLAIN, 12));
		lProductName.setSize(65, 15);
		lProductName.setLocation((frame.getWidth()  / 6) - lProductName.getWidth()/2, lTitle.getLocation().y + lTitle.getSize().height + 15);

		// TextField Username
		JTextField tfProductName = new JTextField();	
		tfProductName.setFont(new Font("Arial", Font.PLAIN, 11));
		tfProductName.setDocument(new JTextFieldLimit(25));
		tfProductName.setSize(90, 20);
		tfProductName.setLocation(frame.getWidth() / 6 - tfProductName.getWidth()/2, lProductName.getLocation().y + lProductName.getSize().height);

		JLabel lQuantity = new JLabel();
		lQuantity.setText("Quantity:");
		lQuantity.setFont(new Font("Arial", Font.PLAIN, 12));
		lQuantity.setSize(50, 15);
		lQuantity.setLocation(frame.getWidth() /6 - lQuantity.getWidth(),  tfProductName.getLocation().y + tfProductName.getSize().height + 20);

		SpinnerNumberModel spinnerNum = new SpinnerNumberModel(1, 1, 30, 1);
		JSpinner sQuantity = new JSpinner(spinnerNum);
		sQuantity.setSize(35, 30);
		sQuantity.setLocation(frame.getWidth()/6 + sQuantity.getWidth() / 2, tfProductName.getLocation().y + tfProductName.getSize().height + 10);

		JLabel lDescription = new JLabel();
		lDescription.setText("Description Product:");
		lDescription.setFont(new Font("Arial", Font.PLAIN, 12));
		lDescription.setSize(120, 15);
		lDescription.setLocation(frame.getWidth() / 6 - lDescription.getWidth() / 2,  sQuantity.getLocation().y + sQuantity.getSize().height + 15);

		JTextArea tfDescription = new JTextArea();
		JLabel lDescriptLength = new JLabel();
		
		tfDescription.setFont(new Font("Arial", Font.PLAIN, 11));
		tfDescription.setDocument(new JTextFieldLimit(150));
		tfDescription.setLineWrap(true);
		tfDescription.setSize(200, 70);
		tfDescription.setLocation(frame.getWidth() / 6 - tfDescription.getWidth() / 2, lDescription.getLocation().y + lDescription.getSize().height);

		tfDescription.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				lDescriptLength.setText("Length is: " + tfDescription.getText().length() + "/150");
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		lDescriptLength.setText("Length is: 0/150");
		lDescriptLength.setFont(new Font("Arial", Font.ITALIC, 10));
		lDescriptLength.setSize(90, 15);
		lDescriptLength.setLocation(frame.getWidth() / 6, tfDescription.getLocation().y + tfDescription.getSize().height);

		JButton btnSubmit = new JButton();
		btnSubmit.setText("Add");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 10));
		btnSubmit.setBackground(Color.LIGHT_GRAY);
		btnSubmit.setSize(90, 20);
		btnSubmit.setLocation(frame.getWidth()/6 - btnSubmit.getWidth() / 2, lDescriptLength.getLocation().y + lDescriptLength.getSize().height + 10);
		btnSubmit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String errors = "";
				
				errors = ProductSubmit.addProduct(user, tfProductName.getText(), CreateIDs.createID(), (int)sQuantity.getValue() , tfDescription.getText());
				
				if (errors.isEmpty()) 			
					JOptionPane.showMessageDialog(frame, "Succefull", "Login", JOptionPane.INFORMATION_MESSAGE);
				 else
					JOptionPane.showMessageDialog(frame, errors, "Errors", JOptionPane.ERROR_MESSAGE);
			}
		});

		panel.add(lTitle);
		panel.add(lProductName);
		panel.add(tfProductName);
		panel.add(lQuantity);
		panel.add(sQuantity);
		panel.add(lDescription);
		panel.add(tfDescription);
		panel.add(lDescriptLength);
		panel.add(btnSubmit);

	}
	
	private static void updateProduct(User user, JFrame frame, JPanel panel) {
		JLabel lTitle = new JLabel();
		lTitle.setText("Update Product");
		lTitle.setFont(new Font("Arial", Font.BOLD, 12));
		lTitle.setSize(95, 15);
		lTitle.setLocation(frame.getWidth()/2 - lTitle.getWidth() / 2, 30);
		
		JLabel lProductName = new JLabel();
		lProductName.setText("Username:");
		lProductName.setFont(new Font("Arial", Font.PLAIN, 12));
		lProductName.setSize(65, 15);
		lProductName.setLocation(frame.getWidth() / 2 - lProductName.getWidth() / 2,  lTitle.getLocation().y + lTitle.getSize().height + 15);

		// TextField Username
		JTextField tfProductName = new JTextField();	
		tfProductName.setFont(new Font("Arial", Font.PLAIN, 11));
		tfProductName.setDocument(new JTextFieldLimit(25));
		tfProductName.setSize(90, 20);
		tfProductName.setLocation((frame.getWidth() - tfProductName.getWidth()) / 2, lProductName.getLocation().y + lProductName.getSize().height);

		JLabel lQuantity = new JLabel();
		lQuantity.setText("Quantity:");
		lQuantity.setFont(new Font("Arial", Font.PLAIN, 12));
		lQuantity.setSize(50, 15);
		lQuantity.setLocation(frame.getWidth() /2 - lQuantity.getWidth(), tfProductName.getLocation().y + tfProductName.getSize().height + 20);

		SpinnerNumberModel spinnerNum = new SpinnerNumberModel(1, 1, 30, 1);
		JSpinner sQuantity = new JSpinner(spinnerNum);
		sQuantity.setSize(35, 30);
		sQuantity.setLocation(frame.getWidth()/2 + sQuantity.getWidth() / 2, tfProductName.getLocation().y + tfProductName.getSize().height + 10);

		JLabel lDescription = new JLabel();
		lDescription.setText("New Description Product:");
		lDescription.setFont(new Font("Arial", Font.PLAIN, 12));
		lDescription.setSize(140, 15);
		lDescription.setLocation(frame.getWidth() / 2 - lDescription.getWidth() / 2, sQuantity.getLocation().y + sQuantity.getSize().height + 15);

		JTextArea tfDescription = new JTextArea();
		JLabel lDescriptLength = new JLabel();
		
		tfDescription.setFont(new Font("Arial", Font.PLAIN, 11));
		tfDescription.setDocument(new JTextFieldLimit(150));
		tfDescription.setLineWrap(true);
		tfDescription.setSize(200, 70);
		tfDescription.setLocation(frame.getWidth() / 2 - tfDescription.getWidth() / 2, lDescription.getLocation().y + lDescription.getSize().height);

		tfDescription.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				lDescriptLength.setText("Length is: " + tfDescription.getText().length() + "/150");
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		lDescriptLength.setText("Length is: 0/150");
		lDescriptLength.setFont(new Font("Arial", Font.ITALIC, 10));
		lDescriptLength.setSize(90, 15);
		lDescriptLength.setLocation(frame.getWidth() / 2, tfDescription.getLocation().y + tfDescription.getSize().height);

		JButton btnUpdate = new JButton();
		btnUpdate.setText("Update");
		btnUpdate.setFont(new Font("Arial", Font.PLAIN, 10));
		btnUpdate.setBackground(Color.LIGHT_GRAY);
		btnUpdate.setSize(90, 20);
		btnUpdate.setLocation(frame.getWidth()/2 - btnUpdate.getWidth() / 2,  lDescriptLength.getLocation().y + lDescriptLength.getSize().height + 10);
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String errors = "";
				
				errors = ProductSubmit.updateProduct(user, tfProductName.getText(), (int)sQuantity.getValue() , tfDescription.getText());
				
				if (errors.isEmpty()) 			
					JOptionPane.showMessageDialog(frame, "Succefull Update Product", "Update", JOptionPane.INFORMATION_MESSAGE);
				 else
					JOptionPane.showMessageDialog(frame, errors, "Errors", JOptionPane.ERROR_MESSAGE);
			}
		});

		panel.add(lTitle);
		panel.add(lProductName);
		panel.add(tfProductName);
		panel.add(lQuantity);
		panel.add(sQuantity);
		panel.add(lDescription);
		panel.add(tfDescription);
		panel.add(lDescriptLength);
		panel.add(btnUpdate);
	}
	
	static List<Product>  listOfProducts= new ArrayList<Product>();
	
	private static void deleteProduct(User user, JFrame frame, JPanel panel) {
		JLabel lTitle = new JLabel();
		lTitle.setText("Delete Product");
		lTitle.setFont(new Font("Arial", Font.BOLD, 12));
		lTitle.setSize(85, 15);
		lTitle.setLocation(5 * (frame.getWidth() / 6) - lTitle.getWidth() / 2, 30);
	
		JButton btnViewProduct = new JButton();
		btnViewProduct.setText("View Product");
		btnViewProduct.setFont(new Font("Arial", Font.PLAIN, 10));
		btnViewProduct.setBackground(Color.LIGHT_GRAY);
		btnViewProduct.setSize(120, 20);
		btnViewProduct.setLocation(5* frame.getWidth() / 6 - btnViewProduct.getWidth() / 2, lTitle.getLocation().y + lTitle.getSize().height + 15);
		
			
		List<String> listView = new ArrayList<String>();
		JList<String> lproduct = new JList<String>(listView.toArray(new String[listView.size()]));
		JScrollPane scrollPane = new JScrollPane();
		
		btnViewProduct.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String errors = "";
				listView.clear();
				listOfProducts = ProductSubmit.viewProduct(user);
				if (!listOfProducts.isEmpty()) { 
					for(Product p : listOfProducts) {
						listView.add(p.getProduct_name() +" - "+ p.getQuantity() + " - "+ p.getDescription());
					}
					
					lproduct.setListData(listView.toArray(new String[listView.size()]));
					scrollPane.setViewportView(lproduct);
				}
				else
					errors += "You have not any product \n Continue with add product";
				
				if(!errors.isEmpty())					
					JOptionPane.showMessageDialog(frame, errors, "Errors", JOptionPane.ERROR_MESSAGE);
				
			}
		});		
		
		
		scrollPane.setSize(200, 100);
		scrollPane.setLocation(5 * frame.getWidth() / 6 - scrollPane.getWidth() / 2, btnViewProduct.getLocation().y + btnViewProduct.getSize().height + 15);
	
		JButton btnDelete = new JButton();
		btnDelete.setText("Delete");
		btnDelete.setFont(new Font("Arial", Font.PLAIN, 10));
		btnDelete.setBackground(Color.LIGHT_GRAY);
		btnDelete.setSize(90, 20);
		btnDelete.setLocation(5 * frame.getWidth() / 6 - btnDelete.getWidth() / 2,  scrollPane.getLocation().y + scrollPane.getSize().height + 30);
		btnDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String errors ="";
				if(lproduct.getSelectedIndex() >= 0)
					errors =ProductSubmit.deleteProduct(user, listOfProducts.get(lproduct.getSelectedIndex()));
				else
					errors +="You do not select some product";
				
				if (errors.isEmpty()) 			
					JOptionPane.showMessageDialog(frame, "Succefull Delete Product", "Delete", JOptionPane.INFORMATION_MESSAGE);
				 else
					JOptionPane.showMessageDialog(frame, errors, "Errors", JOptionPane.ERROR_MESSAGE);
		
			}
		});
		
		
		
		panel.add(lTitle);
		panel.add(btnViewProduct);
		panel.add(btnDelete);
		panel.add(scrollPane);
		panel.repaint();
		
	}
}
