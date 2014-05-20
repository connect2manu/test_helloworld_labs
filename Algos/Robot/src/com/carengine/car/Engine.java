package com.carengine.car;

public class Engine {
	
	public enum EngineType {
		LARGE, SMALL, DEFAULT;
	}
	
	private EngineType type = EngineType.DEFAULT;
	
	public Engine(EngineType type) {
		this.setType(type);
	}

	public EngineType getType() {
		return type;
	}

	public void setType(EngineType type) {
		this.type = type;
	}

}
