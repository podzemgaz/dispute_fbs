package com.my.main;

import java.util.Map;

public class Expert {
	private String[][] values; // values of parsing of the dispute text
	private Map<Integer, String> codeNumAccepted; // values of parsing act of accepting pdf
	private Map<Integer, String> codeNumMismatch; // values of parsing act of mismatch pdf
	private String warehouse; // value from parsing of act pdf
	public String[][] getValues() {
		return values;
	}
	public void setValues(String[][] values) {
		this.values = values;
	}
	public Map<Integer, String> getCodeNumAccepted() {
		return codeNumAccepted;
	}
	public void setCodeNumAccepted(Map<Integer, String> codeNumAccepted) {
		this.codeNumAccepted = codeNumAccepted;
	}
	public Map<Integer, String> getCodeNumMismatch() {
		return codeNumMismatch;
	}
	public void setCodeNumMismatch(Map<Integer, String> codeNumMismatch) {
		this.codeNumMismatch = codeNumMismatch;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	
	
}
