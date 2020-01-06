package users;

import javax.swing.JRadioButton;

public class ChooseUserCategory {

	public static String UserCategory(JRadioButton rb1,JRadioButton rb2)
	{
		String catecory = "";
		if(rb1.isSelected())
			catecory = UserCatecory.categories.seller.toString();
		if(rb2.isSelected())
			catecory = UserCatecory.categories.customer.toString();
		
		return catecory;
	}
	
}
