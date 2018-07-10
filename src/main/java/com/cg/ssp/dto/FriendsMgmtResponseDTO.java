package com.cg.ssp.dto;

/**
 * @author Amit Bhalerao
 * Friend Response Data Trasfer Object 
 */

public class FriendsMgmtResponseDTO {

	String friends [];
	boolean success;
	int count;
	
	public FriendsMgmtResponseDTO(boolean success) {
		this.success = success;
	}
	
	public FriendsMgmtResponseDTO() {
	}

	public String[] getFriends() {
		return friends;
	}
	public void setFriends(String[] friends) {
		this.friends = friends;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
