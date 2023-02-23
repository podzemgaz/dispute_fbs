package com.my.main;

import com.my.main.exception.TextParseEcxeption;

public class DisputeTextParser {

	// getting an array of individual text snippet, each contains its order
	public String[] getPostingDonuts(String dispute) {
		String clearDispute = cleanDisputeText(dispute);

		return clearDispute.split("(\r?\n){2,}");
	}

	// prepare text for next parsing
	private String cleanDisputeText(String dispute) {

		String result = dispute.replaceAll("\\t+", "");
		result = result.replaceAll("\\*", "");
		result = result.replaceAll("((?<=\n)|^) +", "");
		return result;
	}
	
	public String[] splitDonut(String donut) throws TextParseEcxeption {
		String[] split = donut.split("\r?\n");
		
		if (split.length != 3) {
			throw new TextParseEcxeption("Check out: " + split[0]);
		}
		
		return split;
	}
	
	public String getValue(String line) {
		return line.split(": ")[1];
	}
	
	public String[][] getPostingValues(String dispute) throws TextParseEcxeption {
		String[] donuts = getPostingDonuts(dispute);
		String[][] values = new String[donuts.length][3];
		String[] split;
		for (int i = 0; i < donuts.length; i++) {
			split = splitDonut(donuts[i]);
			for (int j = 0; j < split.length; j++) {
				values[i][j] = getValue(split[j]);
			}
		}
		
		return values;
	}
}
