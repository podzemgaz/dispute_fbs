package com.my.main;

import com.my.main.actinfo.ActTextParser;
import com.my.main.exception.TextParseEcxeption;

public class MainConsole {
	public void prepareParsersByConsole(DisputeTextParser dtp, ActTextParser acttp) {
		ScannerWrapper scw = new ScannerWrapper(System.in);
		System.out.println("Welcome to console getting data!");
		// first we need to prepare dtp
		while (dtp.getValues() == null) {
			try {
				dtp.parse(scw.getTextWithinBrackets());
			} catch (TextParseEcxeption e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		scw.close();
	}
}
