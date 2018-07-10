package com.cg.ssp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ssp.exception.FriendNotFoundException;
import com.cg.ssp.model.FriendsMgmtRequestDO;
import com.cg.ssp.model.FriendsMgmtResponseDO;
import com.cg.ssp.service.UserService;

@RestController
@RequestMapping("/api/")
public class FriendsManagementController {
	
	@Autowired
	UserService userService;
	
	/* API to create a friend connection between two email addresses */
	@RequestMapping(value = "/friends",method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public FriendsMgmtResponseDO createFriend(@RequestBody FriendsMgmtRequestDO friendMgmtDO) throws FriendNotFoundException {
		FriendsMgmtResponseDO friendsMgmtResponseDO = new FriendsMgmtResponseDO();
		boolean found = false;
		String friends[] = friendMgmtDO.getFriends();
		int size = userService.checkExistance(friends[0],friends[1],1);
		if(size > 0)
				found = true;
		else
				found = false;
		if (!found) {
				int count = userService.createFriendRequest(friends[0],friends[1]);
				friendsMgmtResponseDO = new FriendsMgmtResponseDO(true);
				friendsMgmtResponseDO.setFriends(friendMgmtDO.getFriends());
				friendsMgmtResponseDO.setCount(friendMgmtDO.getFriends().length);
				return friendsMgmtResponseDO;
				}
		return friendsMgmtResponseDO;
	}
	
	/* API to retrieve the friends list for an email address. */
	@RequestMapping(value = "/friendlist",method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public FriendsMgmtResponseDO friendsList(@RequestBody FriendsMgmtRequestDO friendMgmtDO) {
		FriendsMgmtResponseDO friendsMgmtResponseDO = new FriendsMgmtResponseDO();
		String from=friendMgmtDO.getEmail();
		List<String> friendsMgmtList = userService.getFriendsList(from);
		if(friendsMgmtList.size() > 0) {
			friendsMgmtResponseDO.setSuccess(true);
			friendsMgmtResponseDO.setFriends(friendsMgmtList.toArray(new String[friendsMgmtList.size()]));
			friendsMgmtResponseDO.setCount(friendsMgmtList.size());
			}
		else {
			friendsMgmtResponseDO.setSuccess(false);
			friendsMgmtResponseDO.setCount(0);
		}
		
		return friendsMgmtResponseDO;
	}
	
	/* API to retrieve the common friends list between two email addresses. */
	@RequestMapping(value = "/commonfriends",method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public FriendsMgmtResponseDO getCommonfriendsList(@RequestBody FriendsMgmtRequestDO friendMgmtDO) {
		String friends[] = friendMgmtDO.getFriends();
		FriendsMgmtResponseDO friendsMgmtResponseDO = new FriendsMgmtResponseDO();
		List<String> friendsMgmtList = userService.getCommonFriends(friends[0],friends[1]);
		if(friendsMgmtList.size() > 0) {
			friendsMgmtResponseDO.setSuccess(true);
			friendsMgmtResponseDO.setFriends(friendsMgmtList.toArray(new String[friendsMgmtList.size()]));
			friendsMgmtResponseDO.setCount(friendsMgmtList.size());
		}
		else {
			friendsMgmtResponseDO.setSuccess(false);
			friendsMgmtResponseDO.setCount(0);
		}
		return friendsMgmtResponseDO;
	}
	
	/* API to subscribe to updates from an email address.*/
	@RequestMapping(value = "/subscribe",method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public FriendsMgmtResponseDO subscribeForUpdates(@RequestBody FriendsMgmtRequestDO friendMgmtDO) {
		FriendsMgmtResponseDO friendsMgmtResponseDO = new FriendsMgmtResponseDO();
		
		boolean found = false;
		String requestor = friendMgmtDO.getRequestor();
		String target = friendMgmtDO.getTarget();
		
		int size = userService.checkExistance(requestor,target,4);
		if(size > 0)
				found = true;
		else
				found = false;
		
		if (!found) {
			int subscribeDone = userService.subscribeForUpdates(requestor,target);
			if(subscribeDone > 0)
				friendsMgmtResponseDO.setSuccess(true);
			}
		return friendsMgmtResponseDO;
	}
	
	/* API to block updates from an email address.*/
	@RequestMapping(value = "/block",method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public FriendsMgmtResponseDO blockForUpdates(@RequestBody FriendsMgmtRequestDO friendMgmtDO) {
		FriendsMgmtResponseDO friendsMgmtResponseDO = new FriendsMgmtResponseDO();
		String requestor = friendMgmtDO.getRequestor();
		String target = friendMgmtDO.getTarget();
		
		int areTheyFriends = userService.checkExistance(requestor,target,1);  // check if they are connected as friends
		if(areTheyFriends > 0) {
			int alreadyBlocked = userService.checkExistance(requestor,target,3);  // check if not blocked already
			if(alreadyBlocked == 0) {
				int blockDone = userService.blockForUpdates(requestor,target);	// block updates
				if(blockDone > 0)
				friendsMgmtResponseDO.setSuccess(true);
			}
		}
		return friendsMgmtResponseDO;
	}
}