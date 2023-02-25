package com.my.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import com.my.main.actinfo.ActTextParser;
import com.my.main.actinfo.ActType;
import com.my.main.exception.TextParseEcxeption;

public class MainConsole {
	public static void main(String[] args) {
		System.out.println("Welcome!");

		Consoler cnsler = new Consoler();

		Expert expert = new Expert();
		DisputeTextParser dtp = new DisputeTextParser();
		ActTextParser acttp = new ActTextParser(cnsler);

		File pdfFile;

		while (expert.getValues() == null) {
			System.out.println("Enter dispute");
			String dispute = cnsler.getTextWithinBrackets();
			try {
				dtp.parse(dispute);
			} catch (TextParseEcxeption e) {
				e.printStackTrace();
			}
			expert.setValues(dtp.getValues());
		}

		while (expert.getCodeNumAccepted() == null || expert.getCodeNumMismatch() == null) {

			System.out.println("Enter path");
			String path = cnsler.getLine();
			// check path
			if (!path.endsWith(".pdf")) {
				cnsler.printLnErrLB("Неверный формат файла, должен быть pdf");
				continue;
			}

			pdfFile = new File(path);

			try {
				acttp.testParse(pdfFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			if (acttp.getActType() == ActType.ACCEPT) {
				expert.setCodeNumAccepted(acttp.getCodeNum());
			} else if (acttp.getActType() == ActType.MISMATCH) {
				expert.setCodeNumMismatch(acttp.getCodeNum());
			} else {
				continue;
			}

			acttp.print();

		}
		
		expert.setWarehouse(acttp.getWarehouse());
		
		//=======================================
		
		String[][] values = expert.getValues();
		Map<String, Integer> acceptCodeNum = expert.getCodeNumAccepted();
		Map<String, Integer> mismatchCodeNum = expert.getCodeNumMismatch();
		String warehouse = expert.getWarehouse();

		if (warehouse == null || warehouse.isEmpty()) {
			System.out.println("Enter warehouse");
			warehouse = cnsler.getLine();
			expert.setWarehouse(warehouse);
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

		cnsler.printLikeBoss(System.lineSeparator());

		for (int i = 0; i < postings.length; i++) {
			cnsler.printLnLB(postings[i].toString() + "\n");
		}

		cnsler.close();
	}
}
