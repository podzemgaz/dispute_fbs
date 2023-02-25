package com.my.main.actinfo;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.my.main.PDFReader;

public class ActTextParser {

	private ActInfo accept;
	private ActInfo mismatch;
	private String warehouse;

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public ActInfo getAccept() {
		return accept;
	}

	public ActInfo getMismatch() {
		return mismatch;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public ActInfo testParse(String textAct) throws FileNotFoundException {

		String lowText = textAct.toLowerCase();

		ActInfo act;

		if (lowText.contains("акт приема-передачи")) {
			act = new ActInfoAccept();
		} else if (lowText.contains("акты о расхождениях")) {
			act = new ActInfoMismatch();
		} else {
			return null;
		}

		if (warehouse == null || warehouse.isEmpty()) {
			warehouse = getWarehouseFromAct(textAct);
		}

		Map<String, Integer> codeNum = new HashMap<>();
		Pattern p = Pattern.compile("\\d+ (.*)?\\d+-\\d+-\\d");
		Matcher m = p.matcher(textAct);

		String line;
		String[] split;

		while (m.find()) {
			line = textAct.substring(m.start(), m.end());
			line = line.replaceAll("[^\\d- ]", "");
			line = line.replaceAll(" +", " ");
			split = line.split(" ");
			codeNum.put(split[1], Integer.parseInt(split[0]));
		}

		act.setCodeNum(codeNum);

		if (act.getClass().getSimpleName().equals("ActInfoAccept")) {
			accept = act;
		} else {
			mismatch = act;
		}
		
		return act;
	}

	public String getWarehouseFromAct(String actText) {
		String result = "";

		Pattern p = Pattern.compile("([А-Я])+_([А-Я])+_([а-яА-Я])+(_[а-яА-Я]+\\r?\\n[а-яА-Я]+)?");
		Matcher m = p.matcher(actText);

		if (m.find()) {
			result = actText.substring(m.start(), m.end());
		}

		result = result.replaceAll("\r?\n", "");

		return result;
	}
}
