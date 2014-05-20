package com.carengine.car;

import com.carengine.CarUtils;

public class CarX implements Car {
	
	private Engine engine = new Engine(Engine.EngineType.SMALL);

	@Override
	public int getTopSpeed() {
		return CarUtils.getTopSpeedByEngineType(engine.getType());
	}

}
