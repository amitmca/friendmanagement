package com.cg.ssp.exception;

public class NoCommonFriendExistsException extends Exception {
	
	private static final long serialVersionUID = -9079454849611061074L;

	public NoCommonFriendExistsException() {
		super();
	}
	
	public NoCommonFriendExistsException(String message) {
			super(message);
	}
}
