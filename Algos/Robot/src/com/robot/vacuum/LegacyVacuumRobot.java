package com.robot.vacuum;

import com.robot.exceptions.ObstacleHitException;

public interface LegacyVacuumRobot 
{
    void rotateRight(int degrees);
    void moveForward(int inches) throws ObstacleHitException;
}
