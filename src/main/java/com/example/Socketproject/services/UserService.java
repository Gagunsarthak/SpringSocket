package com.example.Socketproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Socketproject.domain.User;
import com.example.Socketproject.repositories.UserRepo;
@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	public User createUser(User data) throws Exception {
		try {
			System.out.println("Hiotting");
			userRepo.addUser(data);
			return data;
		}
	catch(Error err) {
		throw new Exception("Some error occured");
	}
		

	}
	public User findMatch(User data)  throws Exception {
		try {
			System.out.println("Hiotting");
			return userRepo.findMatch(data);
			
		}
	catch(Error err) {
		throw err;
	}
		
	}

}
