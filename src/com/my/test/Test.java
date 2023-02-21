package com.my.test;

import com.my.main.Consoler;
import com.my.main.Posting;

public class Test {
	public static void main(String[] args) {
		Posting posting = new Posting();

		System.out.println(posting);
		
		Consoler cslr = new Consoler();
		
		System.out.println("enter text in brackets []");
		
		String textInBrack = cslr.getTextWithinBrackets();
		
		System.out.println(textInBrack);
		
		cslr.close();
	}
}
