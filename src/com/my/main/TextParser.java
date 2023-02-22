package com.my.main;

public class TextParser {

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
}
