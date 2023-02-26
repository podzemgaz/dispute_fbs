package com.my.main;

import java.io.InputStream;
import java.util.Scanner;

public class ScannerWrapper {
	private Scanner scanner;

	public ScannerWrapper(InputStream is) {
		this.scanner = new Scanner(is);
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
}
