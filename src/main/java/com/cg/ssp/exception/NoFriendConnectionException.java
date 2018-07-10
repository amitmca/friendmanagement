package com.cg.ssp.exception;

public class NoFriendConnectionException extends Exception {
	
	private static final long serialVersionUID = -9079454849611061074L;

	public NoFriendConnectionException() {
		super();
	}
	
	public NoFriendConnectionException(String message) {
			super(message);
	}
}
