package com.robot.vacuum.adapter;

import com.robot.exceptions.ObstacleHitException;
import com.robot.vacuum.LegacyVacuumRobot;
import com.robot.vacuum.VacuumRobot;

/**
 * Adapter class to let legacy Vacuum Robot still be used with new efficient clients.
 *
 */
public class LegacyVacuumAdapter implements VacuumRobot {
	
	private LegacyVacuumRobot legacyRobot;
	
	public LegacyVacuumAdapter(LegacyVacuumRobot legacyRobot) {
		this.legacyRobot = legacyRobot;
	}

	@Override
	public void rotateRight(int degrees) {
		legacyRobot.rotateRight(degrees);
	}

	/** 
	 * Empty implementation
	 */
	@Override
	public void rotateLeft(int degrees) {
		throw new UnsupportedOperationException("Legacy Robot doesn't supports - rotateLeft. Should never occur!");
	}

	@Override
	public void moveForward(int inches) throws ObstacleHitException {
		legacyRobot.moveForward(inches);
	}

	/** 
	 * Empty implementation
	 */
	@Override
	public void moveBackward(int inches) throws ObstacleHitException {
		throw new UnsupportedOperationException("Legacy Robot doesn't supports - moveBackward. This should never occur!");
	}

}
