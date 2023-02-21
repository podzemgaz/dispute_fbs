package com.my.main;

public class MainConsole {
	public static void main(String[] args) {
		System.out.println("Welcome!");
		System.out.println("Enter path");
		
		Consoler cslr = new Consoler();
		
		String path = cslr.getLine();
		
		PDFWorker pdfw = new PDFWorker();
		
		String pdfContent = pdfw.pdfToString(path);
		
		System.out.println(pdfContent);
		
		cslr.close();
	}
}
