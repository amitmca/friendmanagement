package com.cg.ssp.dto;

/**
 * @author Amit Bhalerao
 * Friend Request Data Trasfer Object 
 */

public class FriendsMgmtRequestDTO {
	
	private String friends [];
	private String email;
	private String requestor;
	private String target;

	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[] getFriends() {
		return friends;
	}

	public void setFriends(String[] friends) {
		this.friends = friends;
	}

}
