package com.cg.ssp.repository;

import java.util.List;

/**
 * @author Amit Bhalerao
 * Friend Repository
 */

public interface FriendRepository {
	
	public List<String> getFriendsList(String from);
	
	public int checkExistance(String friend1, String friend2,int operation);
	
	public int createFriendRequest(String from, String to);
	
	public List<String> getCommonFriends(String from ,String to);
	
	public int subscribeForUpdates(String requestor, String target);
	
	public int blockForUpdates(String requestor, String target);
}
