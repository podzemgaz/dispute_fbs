package com.my.main;

import java.util.Map;

import com.my.main.exception.NoAcceptCodeNumException;
import com.my.main.exception.NoMismatchCodeNumException;
import com.my.main.exception.NoValuesException;
import com.my.main.exception.NoWarehouseException;

public class Expert {
	private String[][] values; // values of parsing of the dispute text
	private Map<String, Integer> codeNumAccepted; // values of parsing act of accepting pdf
	private Map<String, Integer> codeNumMismatch; // values of parsing act of mismatch pdf
	private String warehouse; // value from parsing of act pdf
	public String[][] getValues() {
		return values;
	}
	public void setValues(String[][] values) {
		this.values = values;
	}
	public Map<String, Integer> getCodeNumAccepted() {
		return codeNumAccepted;
	}
	public void setCodeNumAccepted(Map<String, Integer> codeNumAccepted) {
		this.codeNumAccepted = codeNumAccepted;
	}
	public Map<String, Integer> getCodeNumMismatch() {
		return codeNumMismatch;
	}
	public void setCodeNumMismatch(Map<String, Integer> codeNumMismatch) {
		this.codeNumMismatch = codeNumMismatch;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	
	public Posting[] getPostings() throws NoValuesException, NoAcceptCodeNumException, NoMismatchCodeNumException, NoWarehouseException {
		
		// if values == null it needs to make a request
		
		if (values == null) {
			throw new NoValuesException();
		}
		
		if (codeNumAccepted == null) {
			throw new NoAcceptCodeNumException();
		}
		
		if (codeNumMismatch == null) {
			throw new NoMismatchCodeNumException();
		}
		
		if (warehouse == null || warehouse.isEmpty()) {
			throw new NoWarehouseException();
		}
		
		Posting[] postings = new Posting[values.length];
		Posting p;
		String code;
	

		for (int i = 0; i < postings.length; i++) {
			p = new Posting();
			code = values[i][0];
			p.setCode(code);
			p.setSum(values[i][1]);
			p.setDate(values[i][2]);
			p.setNumAct(codeNumAccepted.get(code));
			p.setNumMistch(codeNumMismatch.get(code));
			p.setWarehouse(warehouse);
			postings[i] = p;
		}
		
		return postings;
	}
	
	
}
