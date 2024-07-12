package com.example.Socketproject.controllers;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Socketproject.domain.User;
import com.example.Socketproject.services.UserService;


@RestController
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
    private UserService userService;
    @GetMapping("/health-check")
  public String   healthCheck(){
return "OK";
    }

    @PostMapping("/users")
    public ResponseEntity<?>  createUser(@RequestBody User user)  throws Exception{
    	user.setId(UUID.randomUUID().toString());
      //  return userService.createUser(user);
        return ResponseEntity.ok(userService.createUser(user));
    	
    }
    @PostMapping("/findMatch")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> findMatch(@RequestBody User user)  throws Exception{
    	try {
    		  System.out.println("UserId to find match for "+user.getId());
    		  return ResponseEntity.ok(userService.findMatch(user));
    	}
catch(Exception e ) {
	 e.printStackTrace();

     // Return an error response to the client
     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error finding match: " + e.getMessage());
}


    	
    }
    

}
