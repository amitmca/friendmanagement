package com.cg.ssp.exception;

public class FriendNotFoundException extends Exception {
	
	private static final long serialVersionUID = -9079454849611061074L;

	public FriendNotFoundException() {
		super();
	}
	
	public FriendNotFoundException(String message) {
			super(message);
	}
}
