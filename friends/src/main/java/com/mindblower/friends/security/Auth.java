package com.mindblower.friends.security;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import com.mindblower.friends.Repositories.AuthTokenRepo;
import com.mindblower.friends.entities.User;

public class Auth {
	
	@Autowired
	private AuthTokenRepo authTokenRepo;

	public static String getAuthenticationToken(String userName) {

		String pass = generateRandomString(5);

		return UUID.randomUUID().toString() + pass +"-"+userName+ "-mb";
	}
	
	private static String generateRandomString(final int length) {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		String subset = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ~!@#$%^\\&*()?+/.,<>`=:;'{}\"";
		for (int i = 0; i < length; i++) {
			int index = r.nextInt(subset.length());
			char c = subset.charAt(index);
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static boolean checkpassword(String password,String hashedPassword) {
		
	return BCrypt.checkpw(password,hashedPassword);
	
	}
	
	public static String generatePassword(String password) {
		
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public User getCustomerFromToken(String userToken) {
		
		System.out.println(authTokenRepo);
//		
		return null;
			
	}
	
}
