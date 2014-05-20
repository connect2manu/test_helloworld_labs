package com.robot.exceptions;

public class ObstacleHitException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ObstacleHitException(String errorMsg) {
		super(errorMsg);
	}

}
