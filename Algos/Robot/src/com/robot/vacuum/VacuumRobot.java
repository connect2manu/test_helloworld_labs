package com.robot.vacuum;

import com.robot.exceptions.ObstacleHitException;

public interface VacuumRobot 
{
    void rotateRight(int degrees);
    void rotateLeft(int degrees);
    void moveForward(int inches) throws ObstacleHitException;
    void moveBackward(int inches) throws ObstacleHitException;
}
