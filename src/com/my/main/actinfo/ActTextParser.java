package com.my.main.actinfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.my.main.Consoler;
import com.my.main.PDFReader;

public class ActTextParser {

	private Consoler cnslr;
	private String path;
	private String warehouse;
	private ActType type;
	private Map<String, Integer> codeNumAccept;
	private Map<String, Integer> codeNumMismatch;

	public ActTextParser(Consoler cnslr) {
		this.cnslr = cnslr;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getWarehouse() {
		return warehouse;
	}
	
	
	public Map<String, Integer> getCodeNumAccept() {
		return codeNumAccept;
	}
	public Map<String, Integer> getCodeNumMismatch() {
		return codeNumMismatch;
	}
	
	public ActType getActType() {
		return type;
	}

	public void testParse(File pdfFile) throws FileNotFoundException {
		
		path = pdfFile.getAbsolutePath();

		PDFReader reader = new PDFReader(cnslr);

		String textAct = reader.pdfToString(pdfFile);

		String lowText = textAct.toLowerCase();
		if (lowText.contains("акт приема-передачи")) {
			type = ActType.ACCEPT;
		} else if (lowText.contains("акты о расхождениях")) {
			type = ActType.MISMATCH;
		} else {
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
		
		if (type == ActType.ACCEPT) {
			codeNumAccept = codeNum;
		} else if (type == ActType.MISMATCH) {
			codeNumMismatch = codeNum;
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
	
	public void print() {
		cnslr.printLnLB("Путь к файлу: " + path);
		cnslr.printLnLB("Тип акта: " + type.getName());
	}
}
