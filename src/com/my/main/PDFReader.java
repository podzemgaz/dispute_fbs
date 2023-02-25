package com.my.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFReader {

	public String pdfToString(String path) throws FileNotFoundException {
		String result = null;
		File file = new File(path);
		FileInputStream fis = null;

		fis = new FileInputStream(file);

		PDDocument pdfDoc = null;
		try {
			pdfDoc = PDDocument.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PDFTextStripper pdfStrip = null;
		try {
			pdfStrip = new PDFTextStripper();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			result = pdfStrip.getText(pdfDoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pdfDoc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}
