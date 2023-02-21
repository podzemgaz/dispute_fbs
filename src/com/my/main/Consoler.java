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

		return s.substring(s.indexOf("[") + 1, s.indexOf("]")).trim();
	}

	public void close() {
		this.scanner.close();
	}
}
