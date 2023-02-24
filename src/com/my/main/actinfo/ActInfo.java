package com.my.main.actinfo;

import java.util.Map;

public abstract class ActInfo {
	private Map<String, Integer> codeNum;

	public Map<String, Integer> getCodeNum() {
		return codeNum;
	}

	public void setCodeNum(Map<String, Integer> codeNum) {
		this.codeNum = codeNum;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Integer> entry : codeNum.entrySet()) {
			String key = entry.getKey();
			Integer val = entry.getValue();
			sb.append(val).append(" ").append(key).append(System.lineSeparator());
		}
		
		return sb.toString();
	}
}
