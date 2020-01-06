package main;

import java.util.Random;

import database.QueryUserDB;

public class CreateIDs {
	
	public static String createID() {
		String id = "";		
		boolean existID; // if true there is this ID, if false there is not this ID
		
		do  { // stop when the id there isn't (false)
			
			Random r = new Random();

			for (int i = 0; i < 10; i++) {
				char c = (char) (r.nextInt(26) + 'a');
				id += c;
			}			
		
			existID = QueryUserDB.existUserID(id);			
		
		} while (existID);
		
		return id;
	}

}
