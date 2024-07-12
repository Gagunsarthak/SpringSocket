package com.example.Socketproject.controllers;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import pojo.ChatMessage;



@Controller
public class ChatController {
	@Autowired
    private  SimpMessagingTemplate messagingTemplate ;
    private final Queue<String> waitingUsers = new ConcurrentLinkedQueue<>();

    @MessageMapping("/chat.sendMessage/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage sendMessage( @DestinationVariable String roomId, @Payload ChatMessage chatMessage) {
        System.out.println("Message received: " + chatMessage + " sent to room: "+roomId );
        chatMessage.setContent(chatMessage.getContent()+"Gagun");
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/1")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
    	System.out.println("Adding new user "+chatMessage+" sent to ");
    	chatMessage.setContent(chatMessage.getContent()+"Gagun");
       headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
    
    @MessageMapping("/findMatch")
    public void findMatch(String userId) {
    	System.out.println("FindSocket match called for "+userId);
       // String matchedUser = waitingUsers.poll();
//        if (matchedUser == null) {
//            waitingUsers.add(userId);
//        } else {
          //  messagingTemplate.convertAndSendToUser(matchedUser, "/queue/match", "Matched with: " + userId);
            messagingTemplate.convertAndSendToUser("7c1a2e7c-7157-4160-96a4-7e93d409c1a6", "/queue/match", "Matched with: " );
            System.out.println(userId + " added to waiting queue");
            // }
    	
    }
}
