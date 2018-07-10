package com.cg.ssp.model;

public class FriendsMgmtResponseDO {

	String friends [];
	boolean success;
	int count;
	
	public FriendsMgmtResponseDO(boolean success) {
		this.success = success;
	}
	
	public FriendsMgmtResponseDO() {
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
