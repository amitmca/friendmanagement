package com.cg.ssp.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cg.ssp.repository.UserRepository;

/**
 * @author Amit Bhalerao
 * Friend Service Implementation
 */

@Service("userService")
public class FriendServiceImpl implements FriendService{
	
	@Resource
	private UserRepository friendRepository;

	@Override
	public List<String> getFriendsList(String from) {
		return friendRepository.getFriendsList(from);
	}

	@Override
	public int checkExistance(String friend1, String friend2,int operation) {
		return friendRepository.checkExistance(friend1,friend2,operation);
	}

	@Override
	public int createFriendRequest(String from, String to) {
		return friendRepository.createFriendRequest(from, to);
	}

	@Override
	public List<String> getCommonFriends(String from, String to) {
		return friendRepository.getCommonFriends(from, to);
	}

	@Override
	public int subscribeForUpdates(String requestor, String target) {
		return friendRepository.subscribeForUpdates(requestor, target);
	}

	@Override
	public int blockForUpdates(String requestor, String target) {
		return friendRepository.blockForUpdates(requestor, target);
	}
}
