package com.my.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.my.main.actinfo.ActTextParser;

public class PDFReader {
	private Consoler cnsler;

	public PDFReader(Consoler cnsler) {
		this.cnsler = cnsler;
	}

	public boolean checkPath(String path) {

		if (!path.endsWith(".pdf")) {
			cnsler.printLnErrLB("Неверный формат файла, должен быть pdf");
			return false;
		}

		File file = new File(path);

		if (!file.exists()) {
			cnsler.printLnErrLB("Файла не существует");
			return false;
		}

		return true;
	}

	public String pdfToString(File file) throws FileNotFoundException {
		String result = null;

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
