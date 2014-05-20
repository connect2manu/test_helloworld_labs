package com.carengine;

import com.carengine.car.Engine;
import com.carengine.car.Engine.EngineType;

public class CarUtils {

	/**
	 * @param type
	 * @return
	 */
	public static int getTopSpeedByEngineType(EngineType type) {
		return 10 * ((Engine.EngineType.LARGE.equals(type)) ? 20 : 10);
	}

}
