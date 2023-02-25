package com.my.main;

public enum Status {

	ARBITRAGE("арбитраж"), DELIVERED("доставляется");

	private final String name;

	// private Enum constructor
	private Status(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}