package com.my.main;

public class MyPrinter {
	public void printLikeBoss(String s) {
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

	public void printErrLikeBoss(String s) {
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
