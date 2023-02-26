package com.my.main;

import java.io.File;
import java.io.FileNotFoundException;
import com.my.main.actinfo.ActTextParser;
import com.my.main.actinfo.ActType;
import com.my.main.exception.TextParseEcxeption;

public class MainConsoleTest {
	public Posting[] getPostingsByConsole() {
		System.out.println("Welcome!");

		Consoler cnsler = new Consoler();

		Expert expert = new Expert();
		DisputeTextParser dtp = new DisputeTextParser();
		ActTextParser acttp = new ActTextParser(cnsler);

		File pdfFile;

		// check values
		while (expert.getValues() == null) {
			cnsler.printLnLB("Enter dispute text: ");
			String dispute = cnsler.getTextWithinBrackets();
			try {
				dtp.parse(dispute);
			} catch (TextParseEcxeption e) {
				e.printStackTrace();
			}
			expert.setValues(dtp.getValues());
		}

		// check number codes
		while (expert.getCodeNumAccepted() == null || expert.getCodeNumMismatch() == null) {

			System.out.println("Enter path:");
			String path = cnsler.getLine();
			// check path
			if (!path.endsWith(".pdf")) {
				cnsler.printLnErrLB("Неверный формат файла, должен быть pdf");
				continue;
			}

			pdfFile = new File(path);

			try {
				acttp.testParse(pdfFile);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
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
		
		// check warehouse
		expert.setWarehouse(acttp.getWarehouse());

		while (expert.getWarehouse() == null || expert.getWarehouse().isEmpty()) {
			cnsler.printLnLB("Enter warehouse: ");
			expert.setWarehouse(cnsler.getLine());
		}

		Posting[] postings = expert.getPostings();

		cnsler.close();
		
		return postings;
	}
}
