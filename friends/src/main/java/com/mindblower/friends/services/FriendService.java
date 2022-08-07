package com.mindblower.friends.services;

import java.util.HashMap;
import java.util.List;

import com.mindblower.friends.entities.User;

public interface FriendService {

	HashMap<String, String> addFriend(User user,User friend);
	
	HashMap<String, String> addFollower(User user,User friend);
	
	HashMap<String, String> UnFriend(User user,User friend);
	
	HashMap<String, String> UnFollow(User user,User friend);
	
	HashMap<String, String> addRequestedFriend(User user,User friend);
	
	HashMap<String, String> cancelRequestedFriend(User user,User friend);
	
	HashMap<String, String> deleteSentRequest(User user,User friend);
	
	List<User> getAllFriend(User user);
	
	List<User> getAllGetRequests(User user);
	
	List<User> getAllSendRequests(User user);
	
	List<User> getAllActiveFriends(User user);
	
}
