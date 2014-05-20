package com.robot.vacuum.impl;

import com.robot.exceptions.ObstacleHitException;
import com.robot.vacuum.LegacyVacuumRobot;

/**
 * @author manu.mehrotra
 *
 */
public class LegacyVacuumRobotImpl implements LegacyVacuumRobot {

	@Override
	public void rotateRight(int degrees) {
		// Some legacy rotateRight() implementation
		
	}

	@Override
	public void moveForward(int inches) throws ObstacleHitException {
		// Some legacy moveForward() implementation
	}

}
