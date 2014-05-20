package com.carengine.car;

import com.carengine.CarUtils;
import com.carengine.car.Car;
import com.carengine.car.Engine;

public class Boat implements Car {
	
	private static final int MAX_ROWBOAT_SPEED = 5; // Can be configurable.
	private Engine engine = null;
	
	public Boat(Engine engine) {
		this.engine = engine;
	}
	
	/**
	 * Boat w/o an Engine i.e. a RowBoat.
	 */
	public Boat() {
		
	}

	@Override
	public int getTopSpeed() {
		if(hasEngine()) {
			return CarUtils.getTopSpeedByEngineType(engine.getType());
		} else {
			return MAX_ROWBOAT_SPEED;
		}
	}

	private boolean hasEngine() {
		return (engine != null);
	}

}
