package com.example.Socketproject.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.Socketproject.domain.User;
@Repository
public class UserRepo {
	ArrayList<User> maleUserList= new ArrayList<>();
	ArrayList<User> femaleUserList= new ArrayList<>();
	public User addUser(User data) {
		System.out.println("Dat in repo is "+data.getGender());
		if(data.getGender().equals("male")) {
			System.out.println("adding to male");
			maleUserList.add(data);
			
		}else {
			femaleUserList.add(data);
		}
		System.out.println("User added is "+data.getId()+" available is "+data.getIsAvailable());
		return data;
		
	}
	public User findMatch(User user) throws Exception{
		System.out.println("Finding match");
		if(maleUserList.isEmpty() && femaleUserList.isEmpty()) {
			throw new Exception("No one is available");
		}
		User match=null;
		if(user.getInterestedIn().equals("female")) {
			  System.out.println("Female arraylist is "+this.femaleUserList);
			if(femaleUserList.size()>0) {
				int randomNum=generateRandomNumber("female");
				 match=this.femaleUserList.get(randomNum);
//				if(!match.getId().equals(user.getId()) && match.getIsAvailable()) {
//					return match;
//				}
				
			}else {
				int randomNum=generateRandomNumber("male");
				 match=this.maleUserList.get(randomNum);
//				if(!match.getId().equals(user.getId()) && match.getIsAvailable()) {
//					return match;
//				}
			}

		}else {
			if(maleUserList.size()>0) {
				  System.out.println("male arraylist is "+this.maleUserList);
				int randomNum=generateRandomNumber("male");
				 match=this.maleUserList.get(randomNum);
//				if(!match.getId().equals(user.getId()) && match.getIsAvailable()) {
//					return match;
//				}
				
			}else {
				int randomNum=generateRandomNumber("female");
				 match=this.femaleUserList.get(randomNum);

			}
			
		}
		if(match !=null && !match.getId().equals(user.getId()) && match.getIsAvailable()) {
			match.setIsAvailable(false);
			user.setIsAvailable(false);
			return match;
		}
		return findMatch(user);
	}
	
	public int generateRandomNumber(String type) {
		int min = 0;
        int max = 0;
if(type=="male") {
	max=maleUserList.size()-1;
}else {
	max=femaleUserList.size()-1;
}
        // Generate random double value between 0 (inclusive) and 1 (exclusive)
        double randomValue = Math.random();

        // Scale and shift to the desired range
        int randomNumber = (int) (randomValue * (max - min + 1)) + min;
        System.out.println("Random num generated is "+randomNumber);
        return randomNumber;
	}

}
