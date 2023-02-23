package com.my.main;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PDFTextParser {

	private Act accept;
	private Act mismatch;
	private String warehouse;

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public Act getAccept() {
		return accept;
	}

	public Act getMismatch() {
		return mismatch;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void testParse(String path) throws FileNotFoundException {

		PDFWorker pw = new PDFWorker();
		String textAct = pw.pdfToString(path);

		String lowText = textAct.toLowerCase();

		Act act;

		if (lowText.contains("акт приема-передачи")) {
			System.out.println("Акт приёма-передачи: " + path);
			act = new ActAccept();
		} else if (lowText.contains("акты о расхождениях")) {
			System.out.println("Акт о расхождениях: " + path);
			act = new ActMismatch();
		} else {
			System.out.println("Некорректный акт");
			return;
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

		if (act.getClass().getSimpleName().equals("ActAccept")) {
			accept = act;
		} else {
			mismatch = act;
		}
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
