package com.my.main;

import java.io.FileNotFoundException;
import java.util.Map;


import com.my.main.exception.TextParseEcxeption;

public class MainConsole {
	public static void main(String[] args) {
		System.out.println("Welcome!");

		Consoler cslr = new Consoler();

		System.out.println("Enter dispute");

		String dispute = cslr.getTextWithinBrackets();

		DisputeTextParser dtp = new DisputeTextParser();

		String[][] values = null;
		
		try {
			values = dtp.getPostingValues(dispute);
		} catch (TextParseEcxeption e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		PDFTextParser pdftp = new PDFTextParser();

		while (pdftp.getAccept() == null || pdftp.getMismatch() == null) {

			System.out.println("Enter path");
			String path = cslr.getLine();

			try {
				pdftp.testParse(path);
			} catch (FileNotFoundException e) {
				System.err.println("Неверно указан путь к файлу");
			}
		}
		
		
		Map<String, Integer> acceptCodeNum = pdftp.getAccept().getCodeNum();
		Map<String, Integer> mismatchCodeNum = pdftp.getMismatch().getCodeNum();
		String warehouse = pdftp.getWarehouse();
		
		if (warehouse == null || warehouse.isEmpty()) {
			System.out.println("Enter warehouse");
			warehouse = cslr.getLine();
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
			p.setNumAct(acceptCodeNum.get(code));
			p.setNumMistch(mismatchCodeNum.get(code));
			p.setWarehouse(warehouse);
			postings[i] = p;
		}
		
		for (int i = 0; i < postings.length; i++) {
			System.out.println(postings[i] + "\n");
		}

		cslr.close();
	}
}
