package com.my.main;

public class MainConsole {
	public static void main(String[] args) {
		System.out.println("Welcome!");
		System.out.println("Enter dispute");

		Consoler cslr = new Consoler();

		/*
		 * String path = cslr.getLine();
		 * 
		 * PDFWorker pdfw = new PDFWorker();
		 * 
		 * String pdfContent = pdfw.pdfToString(path);
		 * 
		 * System.out.println(pdfContent);
		 */

		String dispute = cslr.getTextWithinBrackets();

		TextParser tp = new TextParser();

		String[] donuts = tp.getPostingDonuts(dispute);

		for (int i = 0; i < donuts.length; i++) {
			System.out.println(donuts[i] + "\n");
		}

		/*
		 * String clean = tp.cleanDisputeText(dispute);
		 * 
		 * System.out.println(clean);
		 */

		cslr.close();
	}
}
