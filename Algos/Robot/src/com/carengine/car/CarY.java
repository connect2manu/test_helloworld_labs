package com.carengine.car;

import com.carengine.CarUtils;

public class CarY implements Car {
	
	private Engine engine = new Engine(Engine.EngineType.LARGE);

	@Override
	public int getTopSpeed() {
		return CarUtils.getTopSpeedByEngineType(engine.getType());
	}
}
