package com.robot;

import com.robot.vacuum.LegacyVacuumRobot;
import com.robot.vacuum.VacuumRobot;
import com.robot.vacuum.adapter.LegacyVacuumAdapter;
import com.robot.vacuum.impl.LegacyVacuumRobotImpl;
import com.robot.vacuum.impl.VacuumRobotImpl;

/**
 * Shows usage of efficient vacuumRoom algo for legacy Vacuum Robot.
 *
 */
public class RoboClient {

	public static void main(String[] args) {
		VacuumRobot vacuumRobot = new VacuumRobotImpl();
		LegacyVacuumRobot legacyVacuumRobot = new LegacyVacuumRobotImpl();
		
		/*
		 * vacuumRoom() using new VacuumRobot. 
		 */
		VacuumRoom.vacuumRoom(vacuumRobot);
		
		/*
		 * vacuumRoom() using new Legacy VacuumRobot through LegacyVacuumAdapter.
		 */
		VacuumRoom.vacuumRoom(new LegacyVacuumAdapter(legacyVacuumRobot));
	}

}
