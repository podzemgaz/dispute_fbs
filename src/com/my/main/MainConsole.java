package com.my.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import com.my.main.actinfo.ActTextParser;
import com.my.main.exception.TextParseEcxeption;

public class MainConsole {

	public static void main(String[] args) {
		System.out.println("Добро пожаловать!");
		ScannerWrapper scw = new ScannerWrapper(System.in);
		

		DisputeTextParser dtp = new DisputeTextParser();
		ActTextParser acttp = new ActTextParser();

		MainConsole mc = new MainConsole();
		mc.prepareParsersByConsole(scw, dtp, acttp);

		PostingsBuilder psb = new PostingsBuilder();
		Posting[] postings = psb.createPostings(dtp, acttp);
		
		System.out.println("В статусе доставляется: ");
		
		
		psb.setDeliveries(scw.getTextWithinBrackets(), postings);
		
		System.out.println("В статусе отменен: ");
		psb.setCanceled(scw.getLine(), postings);
		
		Arrays.sort(postings);

		for (int i = 0; i < postings.length; i++) {
			if (postings[i].getStatus() == Status.ARBITRAGE)
				System.out.println(postings[i] + System.lineSeparator());
		}
		
		scw.close();

	}

	public void prepareParsersByConsole(ScannerWrapper scw, DisputeTextParser dtp, ActTextParser acttp) {

		// first we need to prepare dtp
		while (dtp.getValues() == null) {
			System.out.println("Введите текст спора:");
			try {
				dtp.parse(scw.getTextWithinBrackets());
			} catch (TextParseEcxeption e) {
				System.err.println(e.getMessage());
			}
		}

		// подготавливаем парсер актов
		String path;
		File file;
		while (acttp.isNotCompleteMaps()) {
			System.out.println("Введите путь к файлу:");
			path = scw.getLine();

			// если расширение файла не pdf то – пропускаем цикл
			if (!path.endsWith(".pdf")) {
				System.err.println("Расширение файла должно быть pdf");
				continue;
			}

			file = new File(path);

			// проверка файла на существование проходит внутри парсинга
			try {
				acttp.testParse(file);
			} catch (FileNotFoundException e) {
				System.err.println("Файла не существует");
				continue;
			}

			// напечатать отчёт
			System.out.println("Путь файла: " + path);
			System.out.println("Тип файла: " + acttp.getActType().getName());
		}

		// set warehouse if doesnt exist
		if (acttp.getWarehouse() == null || acttp.getWarehouse().isEmpty()) {
			acttp.setWarehouse(scw.getLine());
		}

	}
}
