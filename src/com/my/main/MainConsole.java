package com.my.main;

import java.io.FileNotFoundException;
import java.util.Map;

import com.my.main.actinfo.ActInfo;
import com.my.main.actinfo.ActTextParser;
import com.my.main.exception.TextParseEcxeption;

public class MainConsole {
	public static void main(String[] args) {
		System.out.println("Welcome!");

		Consoler cslr = new Consoler();

		System.out.println("Enter dispute");

		String dispute = cslr.getTextWithinBrackets();

		DisputeTextParser dtp = new DisputeTextParser();
		
		ActTextParser acttp = new ActTextParser();

		String[][] values = null;
		
		try {
			values = dtp.getPostingValues(dispute);
		} catch (TextParseEcxeption e) { 
			e.printStackTrace();
		}
		

		while (acttp.getAccept() == null || acttp.getMismatch() == null) {

			System.out.println("Enter path");
			String path = cslr.getLine();
			
			PDFWorker pw = new PDFWorker();
			
			try {
				String textAct = pw.pdfToString(path);
				ActInfo actInfo = acttp.testParse(textAct);
				
				if (actInfo == null) {
					System.err.println("Некорректный файл");
				} else if (actInfo.getClass().getSimpleName().equals("ActInfoAccept")) {
					System.out.println("Акт приёма-передачи: " + path);
					
				} else {
					System.out.println("Акт о расхождениях: " + path);
				}
				
			} catch (FileNotFoundException e1) {
				System.err.println("Неверно указан путь к файлу");
			}

		}
		
		Map<String, Integer> acceptCodeNum = acttp.getAccept().getCodeNum();
		Map<String, Integer> mismatchCodeNum = acttp.getMismatch().getCodeNum();
		
		String warehouse = acttp.getWarehouse();
		
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
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < postings.length; i++) {
			sb.append(postings[i]).append(System.lineSeparator()).append(System.lineSeparator());
		}
		
		for (int i = 0; i < sb.length(); i++) {
			System.out.print(sb.charAt(i));
			try {
				Thread.sleep(6);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		cslr.close();
	}
}
