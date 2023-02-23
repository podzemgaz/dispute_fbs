package com.my.main;

import java.io.FileNotFoundException;
import java.util.Arrays;

import com.my.main.exception.TextParseEcxeption;

public class MainConsole {
	public static void main(String[] args) {
		System.out.println("Welcome!");

		Consoler cslr = new Consoler();
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
		
		System.out.println(pdftp.getWarehouse());

		System.out.println(pdftp.getAccept());
		
		System.out.println(pdftp.getMismatch());
		
		
//		System.out.println(pdfContent);

		/*
		 * String dispute = cslr.getTextWithinBrackets();
		 * 
		 * TextParser tp = new TextParser();
		 * 
		 * String[][] values = null; try { values = tp.getPostingValues(dispute); }
		 * catch (TextParseEcxeption e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * for (int i = 0; i < values.length; i++) {
		 * System.out.println(Arrays.toString(values[i]) + "\n"); }
		 */

		/*
		 * String clean = tp.cleanDisputeText(dispute);
		 * 
		 * System.out.println(clean);
		 */

		cslr.close();
	}
}
