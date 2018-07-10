package com.cg.ssp.exception;

public class FriendAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = -9079454849611061074L;

	public FriendAlreadyExistsException() {
		super();
	}
	
	public FriendAlreadyExistsException(String message) {
			super(message);
	}
}
