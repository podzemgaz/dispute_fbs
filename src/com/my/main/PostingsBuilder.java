package com.my.main;

import com.my.main.actinfo.ActTextParser;

public class PostingsBuilder {
	public Posting[] createPostings(DisputeTextParser dtp, ActTextParser acttp) {
		String[][] values = dtp.getValues();
		Posting[] postings = new Posting[values.length];
		Posting p;
		String code;

		for (int i = 0; i < postings.length; i++) {
			p = new Posting();
			code = values[i][0];
			p.setCode(code);
			p.setSum(values[i][1]);
			p.setDate(values[i][2]);
			p.setNumAct(acttp.getCodeNumAccept().get(code));
			p.setNumMistch(acttp.getCodeNumMismatch().get(code));
			p.setWarehouse(acttp.getWarehouse());
			postings[i] = p;
		}

		return postings;
	}

	public void setDeliveries(String deliveries, Posting[] postings) {
		for (int i = 0; i < postings.length; i++) {
			if (deliveries.contains(postings[i].getCode())) {
				postings[i].setStatus(Status.DELIVERED);
			}
		}
	}

	public void setCanceled(String canceled, Posting[] postings) {
		for (int i = 0; i < postings.length; i++) {
			if (canceled.contains(postings[i].getCode())) {
				postings[i].setStatus(Status.CANCELED);
			}
		}
	}
}
