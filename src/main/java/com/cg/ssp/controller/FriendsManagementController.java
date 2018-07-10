package com.cg.ssp.controller;

/**
 * @author Amit Bhalerao
 * Controller for below operations ..
 * 1) Create Friend Request
 * 2) Get All Friends
 * 3) Get Common Friends
 * 4) Subscribe For Updates
 * 5) Block For Updates 
 */

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ssp.constants.MessageConstants;
import com.cg.ssp.dto.FriendsMgmtRequestDTO;
import com.cg.ssp.dto.FriendsMgmtResponseDTO;
import com.cg.ssp.exception.FriendAlreadyExistsException;
import com.cg.ssp.exception.FriendBlockUpdatesException;
import com.cg.ssp.exception.FriendNotFoundException;
import com.cg.ssp.exception.FriendSubscriptionException;
import com.cg.ssp.exception.NoCommonFriendExistsException;
import com.cg.ssp.exception.NoFriendConnectionException;
import com.cg.ssp.service.FriendService;

@RestController
@RequestMapping("/api/")
public class FriendsManagementController {
	
	@Autowired
	FriendService friendService;
	
	/* API to create a friend connection between two email addresses */
	@RequestMapping(value = "/friends",method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public FriendsMgmtResponseDTO createFriend(@RequestBody FriendsMgmtRequestDTO friendMgmtDO) throws FriendAlreadyExistsException {
		FriendsMgmtResponseDTO friendsMgmtResponseDO = new FriendsMgmtResponseDTO();
		boolean found = false;
		String friends[] = friendMgmtDO.getFriends();
		int size = friendService.checkExistance(friends[0],friends[1],1);
		if(size > 0)
				throw new FriendAlreadyExistsException(MessageConstants.FRIEND_ALREDY_EXISTS);
		else
				found = false;
		if (!found) {
				friendService.createFriendRequest(friends[0],friends[1]);
				friendsMgmtResponseDO = new FriendsMgmtResponseDTO(true);
				friendsMgmtResponseDO.setFriends(friendMgmtDO.getFriends());
				friendsMgmtResponseDO.setCount(friendMgmtDO.getFriends().length);
				return friendsMgmtResponseDO;
				}
		return friendsMgmtResponseDO;
	}
	
	/* API to retrieve the friends list for an email address. */
	@RequestMapping(value = "/friendlist",method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public FriendsMgmtResponseDTO friendsList(@RequestBody FriendsMgmtRequestDTO friendMgmtDO) throws FriendNotFoundException {
		FriendsMgmtResponseDTO friendsMgmtResponseDO = new FriendsMgmtResponseDTO();
		String from=friendMgmtDO.getEmail();
		List<String> friendsMgmtList = friendService.getFriendsList(from);
		if(friendsMgmtList.size() > 0) {
			friendsMgmtResponseDO.setSuccess(true);
			friendsMgmtResponseDO.setFriends(friendsMgmtList.toArray(new String[friendsMgmtList.size()]));
			friendsMgmtResponseDO.setCount(friendsMgmtList.size());
			}
		else {
			throw new FriendNotFoundException(MessageConstants.NO_SUCH_A_USER);
		}
		
		return friendsMgmtResponseDO;
	}
	
	/* API to retrieve the common friends list between two email addresses. */
	@RequestMapping(value = "/commonfriends",method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public FriendsMgmtResponseDTO getCommonfriendsList(@RequestBody FriendsMgmtRequestDTO friendMgmtDO) throws NoCommonFriendExistsException {
		String friends[] = friendMgmtDO.getFriends();
		FriendsMgmtResponseDTO friendsMgmtResponseDO = new FriendsMgmtResponseDTO();
		List<String> friendsMgmtList = friendService.getCommonFriends(friends[0],friends[1]);
		if(friendsMgmtList.size() > 0) {
			friendsMgmtResponseDO.setSuccess(true);
			friendsMgmtResponseDO.setFriends(friendsMgmtList.toArray(new String[friendsMgmtList.size()]));
			friendsMgmtResponseDO.setCount(friendsMgmtList.size());
		}
		else {
			throw new NoCommonFriendExistsException(MessageConstants.NO_COMMON_FRIENDS);
		}
		return friendsMgmtResponseDO;
	}
	
	/* API to subscribe to updates from an email address.*/
	@RequestMapping(value = "/subscribe",method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public FriendsMgmtResponseDTO subscribeForUpdates(@RequestBody FriendsMgmtRequestDTO friendMgmtDO) throws FriendSubscriptionException {
		FriendsMgmtResponseDTO friendsMgmtResponseDO = new FriendsMgmtResponseDTO();
		
		boolean found = false;
		String requestor = friendMgmtDO.getRequestor();
		String target = friendMgmtDO.getTarget();
		
		int size = friendService.checkExistance(requestor,target,4);
		if(size > 0)
				throw new FriendSubscriptionException(MessageConstants.SUBSCRIPTION_ALREADY_EXISTS);
		else
				found = false;
		
		if (!found) {
			int subscribeDone = friendService.subscribeForUpdates(requestor,target);
			if(subscribeDone > 0)
				friendsMgmtResponseDO.setSuccess(true);
			}
		return friendsMgmtResponseDO;
	}
	
	/* API to block updates from an email address.*/
	@RequestMapping(value = "/block",method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public FriendsMgmtResponseDTO blockForUpdates(@RequestBody FriendsMgmtRequestDTO friendMgmtDO) throws NoFriendConnectionException, FriendBlockUpdatesException {
		FriendsMgmtResponseDTO friendsMgmtResponseDO = new FriendsMgmtResponseDTO();
		String requestor = friendMgmtDO.getRequestor();
		String target = friendMgmtDO.getTarget();
		
		int areTheyFriends = friendService.checkExistance(requestor,target,1);  // check if they are connected as friends
		if(areTheyFriends > 0) {
			int alreadyBlocked = friendService.checkExistance(requestor,target,3);  // check if not blocked already
			if(alreadyBlocked == 0) {
				int blockDone = friendService.blockForUpdates(requestor,target);	// block updates
				if(blockDone > 0)
				friendsMgmtResponseDO.setSuccess(true);
			}
			else 
				throw new FriendBlockUpdatesException(MessageConstants.UPDATES_ALREADY_BLOCKED);
		}
		else 
			throw new NoFriendConnectionException(MessageConstants.NO_FRIEND_CONNECTION);
		return friendsMgmtResponseDO;
	}
}