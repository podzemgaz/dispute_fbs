package com.my.main.actinfo;

public enum ActType {
	ACCEPT("Акт приёма-передачи"), MISMATCH("Акт о расхождениях");
	
	private final String name;
	
	private ActType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
