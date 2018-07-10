package com.cg.ssp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cg.ssp.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserRepository userRepository;

	@Override
	public List<String> getFriendsList(String from) {
		return userRepository.getFriendsList(from);
	}

	@Override
	public int checkExistance(String friend1, String friend2,int operation) {
		return userRepository.checkExistance(friend1,friend2,operation);
	}

	@Override
	public int createFriendRequest(String from, String to) {
		return userRepository.createFriendRequest(from, to);
	}

	@Override
	public List<String> getCommonFriends(String from, String to) {
		return userRepository.getCommonFriends(from, to);
	}

	@Override
	public int subscribeForUpdates(String requestor, String target) {
		return userRepository.subscribeForUpdates(requestor, target);
	}

	@Override
	public int blockForUpdates(String requestor, String target) {
		return userRepository.blockForUpdates(requestor, target);
	}
}
