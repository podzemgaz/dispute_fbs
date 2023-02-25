package com.my.main;

import java.util.Scanner;

public class Consoler {
	private Scanner scanner;

	public Consoler() {
		this.scanner = new Scanner(System.in);
	}

	public String getLine() {
		return scanner.nextLine();
	}

	public String getTextWithinBrackets() {
		String line;

		StringBuilder sb = new StringBuilder();

		while (scanner.hasNext()) {
			line = scanner.nextLine();
			sb.append(line);
			if (line.contains("]")) {
				break;
			} else {
				sb.append(System.lineSeparator());
			}
		}

		String s = sb.toString();

		if (!s.contains("[")) {
			return "";
		}
		
		return s.substring(s.indexOf("[") + 1, s.indexOf("]")).trim();
	}

	public void close() {
		this.scanner.close();
	}
	
	public void  printLikeBoss(String s) {
		for (int i = 0; i < s.length(); i++) {
			System.out.print(s.charAt(i));
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void printLnLB(String s) {
		printLikeBoss(s + "\n");
	}
	
	public void  printErrLikeBoss(String s) {
		for (int i = 0; i < s.length(); i++) {
			System.err.print(s.charAt(i));
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void printLnErrLB(String s) {
		printErrLikeBoss(s + "\n");
	}
}
