package com.mindblower.friends.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindblower.friends.Repositories.FriendRepo;
import com.mindblower.friends.config.AppConstants;
import com.mindblower.friends.entities.Friend;
import com.mindblower.friends.entities.User;
import com.mindblower.friends.services.FriendService;
import com.mindblower.friends.services.UserService;

@Service
public class FriendServiceImpl implements FriendService {
	
	@Autowired
	private FriendRepo friendRepo;
	
	@Autowired
	private UserService userService;
	

	@Override
	public HashMap<String, String> addFriend(User user, User friend) {
	
		HashMap<String, String> response = new HashMap<>();
		
		Friend userFriend = friendRepo.findByUserId(user.getId());
		if(Objects.nonNull(userFriend))
		userFriend.getUserSendFriendRequest().add(friend);
		else {
			 userFriend = new Friend();
			 userFriend.setUserId(user.getId());
			List<User> list = new ArrayList<>();
			list.add(friend);
			userFriend.setUserSendFriendRequest(list);
			
			
		}
		friendRepo.save(userFriend);
		
		user.setNoOfSendRequest(user.getNoOfSendRequest()+1);
		userService.updateUser(user);
		
		Friend friendUser = friendRepo.findByUserId(friend.getId());
		if(Objects.nonNull(friendUser))
		friendUser.getUserGetFriendRequest().add(user);
		else {
			 friendUser = new Friend();
			friendUser.setUserId(friend.getId());
			List<User> list = new ArrayList<>();
			list.add(user);
			friendUser.setUserGetFriendRequest(list);
			
			
		}
		friendRepo.save(friendUser);
		
		friend.setNoOfGetRequest(friend.getNoOfGetRequest()+1);
		userService.updateUser(friend);
		
		response.put("status",AppConstants.STATUS_REQUEST);
		
		
		return response;
	}


	@Override
	public HashMap<String, String> addFollower(User user, User friend) {
	

		HashMap<String, String> response = new HashMap<>();
		
		Friend userFriend = friendRepo.findByUserId(friend.getId());
		if(Objects.nonNull(userFriend))
		userFriend.getFollowers().add(user);
		else {
			userFriend = new Friend();
			userFriend.setUserId(friend.getId());
			List<User> list = new ArrayList<>();
			list.add(user);
			userFriend.setFollowers(list);
		}
		friendRepo.save(userFriend);
		
		user.setNoOfFollowing(user.getNoOfFollowing()+1);
		userService.updateUser(user);
		
		friend.setNoOfFollewers(friend.getNoOfFollewers()+1);
		userService.updateUser(friend);
		
		
		
		response.put("status", AppConstants.STATUS_FOLLOWING);
		
		return response;
	}


	@Override
	public HashMap<String, String> UnFriend(User user, User friend) {
		
		HashMap<String, String> response = new HashMap<>();
		
		Friend userFriend = friendRepo.findByUserId(user.getId());
		if(Objects.nonNull(userFriend)) {
			userFriend.getFriendList().remove(friend);
			
			friendRepo.save(userFriend);
		}
		
		
		Friend friendUser = friendRepo.findByUserId(friend.getId());
		if(Objects.nonNull(friendUser)) {
			friendUser.getFriendList().remove(user);
			
			friendRepo.save(friendUser);
		}
		
		user.setNoOfFriends(user.getNoOfFriends()-1);
		userService.updateUser(user);
		
		friend.setNoOfFriends(friend.getNoOfFriends()-1);
		userService.updateUser(friend);
		
		response.put("status", AppConstants.STATUS_ADD_FRIEND);
		return response;
	}


	@Override
	public HashMap<String, String> UnFollow(User user, User friend) {
		
		HashMap<String, String> response = new HashMap<>();
		
		Friend userFriend = friendRepo.findByUserId(friend.getId());
		if(Objects.nonNull(userFriend)) {
			userFriend.getFollowers().remove(user);
			
			friendRepo.save(userFriend);
			
			user.setNoOfFollowing(user.getNoOfFollowing()-1);
			userService.updateUser(user);
			
			friend.setNoOfFollewers(friend.getNoOfFollewers()-1);
			userService.updateUser(friend);
		}
		
		
		response.put("status", AppConstants.STATUS_FOLLOW);
		return response;
	}


	@Override
	public HashMap<String, String> addRequestedFriend(User user, User friend) {
		
		HashMap<String, String> response = new HashMap<>();
		
		Friend userFriend = friendRepo.findByUserId(user.getId());
		if(Objects.nonNull(userFriend)) {
			userFriend.getUserGetFriendRequest().remove(friend);
			userFriend.getFriendList().add(friend);
			friendRepo.save(userFriend);
		}
		
		
		Friend friendUser = friendRepo.findByUserId(friend.getId());
		if(Objects.nonNull(friendUser)) {
			friendUser.getUserSendFriendRequest().remove(user);
			friendUser.getFriendList().add(user);
			friendRepo.save(friendUser);
		}
		
		user.setNoOfFriends(user.getNoOfFriends()+1);
		user.setNoOfGetRequest(user.getNoOfGetRequest()-1);
		userService.updateUser(user);
		
		friend.setNoOfFriends(friend.getNoOfFriends()+1);
		friend.setNoOfSendRequest(friend.getNoOfSendRequest()-1);
		userService.updateUser(friend);
		
		response.put("status", AppConstants.STATUS_FRIEND);
		return response;	
	}
	
	@Override
	public HashMap<String, String> cancelRequestedFriend(User user, User friend) {
		
		HashMap<String, String> response = new HashMap<>();
		
		Friend userFriend = friendRepo.findByUserId(user.getId());
		if(Objects.nonNull(userFriend)) {
			userFriend.getUserGetFriendRequest().remove(friend);
			friendRepo.save(userFriend);
		}
		
		
		Friend friendUser = friendRepo.findByUserId(friend.getId());
		if(Objects.nonNull(friendUser)) {
			friendUser.getUserSendFriendRequest().remove(user);
			friendRepo.save(friendUser);
		}
		
		user.setNoOfGetRequest(user.getNoOfGetRequest()-1);
		userService.updateUser(user);
		
		friend.setNoOfSendRequest(friend.getNoOfSendRequest()-1);
		userService.updateUser(friend);
		
		response.put("status", AppConstants.STATUS_CANCEL_REQUEST);
		return response;	
	}
	
	@Override
	public HashMap<String, String> deleteSentRequest(User user, User friend) {
		
		HashMap<String, String> response = new HashMap<>();
		
		Friend userFriend = friendRepo.findByUserId(user.getId());
		if(Objects.nonNull(userFriend)) {
			userFriend.getUserSendFriendRequest().remove(friend);
			friendRepo.save(userFriend);
		}
		
		
		Friend friendUser = friendRepo.findByUserId(friend.getId());
		if(Objects.nonNull(friendUser)) {
			friendUser.getUserGetFriendRequest().remove(user);
			friendRepo.save(friendUser);
		}

		user.setNoOfSendRequest(user.getNoOfSendRequest()-1);
		userService.updateUser(user);
		
		friend.setNoOfGetRequest(friend.getNoOfGetRequest()-1);
		userService.updateUser(friend);
		
		response.put("status", AppConstants.STATUS_CANCEL_REQUEST);
		return response;	
	}


	@Override
	public List<User> getAllFriend(User user) {
		
		List<User> list = null;
		Friend userFriend = friendRepo.findByUserId(user.getId());
		if(Objects.nonNull(userFriend)) {
			list = userFriend.getFriendList();
			return  list != null ? list : new ArrayList<User>();
		}
		
		return new ArrayList<User>();
	}


	@Override
	public List<User> getAllActiveFriends(User user) {
		
		List<User> list = null;
		Friend userFriend = friendRepo.findByUserId(user.getId());
		if(Objects.nonNull(userFriend)) {
			
			list = userFriend.getFriendList().stream().filter(user1->user1.isOnline()).collect(Collectors.toList());
			
			return list != null ? list : new ArrayList<User>();
		}
		
		return new ArrayList<User>();
	}


	@Override
	public List<User> getAllGetRequests(User user) {
		List<User> list = null;
		
		Friend userFriend = friendRepo.findByUserId(user.getId());
		
		if(Objects.nonNull(userFriend)) {
			
			list = userFriend.getUserGetFriendRequest();
			
			return list != null ? list : new ArrayList<User>();
		}
		
		return new ArrayList<User>();
	}
	
	@Override
	public List<User> getAllSendRequests(User user) {
		List<User> list = null;
		
		Friend userFriend = friendRepo.findByUserId(user.getId());
		
		if(Objects.nonNull(userFriend)) {
			
			list = userFriend.getUserSendFriendRequest();
			
			return list != null ? list : new ArrayList<User>();
		}
		
		return new ArrayList<User>();
	}

}
