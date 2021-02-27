package com.fprojects;

import com.fprojects.filehandlers.*;
import com.fprojects.inputwindows.*;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Program started successful");

		InputWindows inputWindows = new InputWindows();
		inputWindows.collectInputInformation();

		ArrayList<Integer> massiv = new ArrayList(); // заменить на вывод в файл

		ArrayList<MyFileReader> listOfFiles = new ArrayList<>(); //Список с параметрами каждого файла и ридером
		ArrayList<Integer> countLinesInFiles = new ArrayList<>(); //Список счётчиков номера текущей линии (строки) в каждом файле
		for (int i = 0; i < inputWindows.getNumberOfInputFiles(); i++ ) {
			MyFileReader myFileReader = new MyFileReader();
			myFileReader.crtReader((String)inputWindows.getInputFileNames().get(i));
			listOfFiles.add(myFileReader);

			countLinesInFiles.add(0);
		}

		int[] valueOfLines = new int[inputWindows.getNumberOfInputFiles()];
		for (int i = 0; i < inputWindows.getNumberOfInputFiles(); i++) { //Считываем значения первых строк файлов
			try {
				valueOfLines[i] = Integer.parseInt(listOfFiles.get(i).readFileLine());
			} catch (NumberFormatException e) {
				System.out.println("Не удаётся привести строку к целочисленному значению. Описание ошибки: " + e.getMessage());
			}
		}

		while (true) {
			int numberOfEndedFiles = 0; //Количество файлов с кончившимися строками
			for (int i = 0; i < inputWindows.getNumberOfInputFiles(); i++) {
				if (countLinesInFiles.get(i) >= listOfFiles.get(i).getNumberOfLines()) {
					numberOfEndedFiles++;
				}
			}
			if (numberOfEndedFiles == inputWindows.getNumberOfInputFiles()) break; //Если количество файлов с кончившимися строками равно общему кол-ву файлов - прерываем

			int min = valueOfLines[0];
			int fileNumber = 0; //Номер файла с минимальным значением
			for (int i = 0; i < inputWindows.getNumberOfInputFiles(); i++) { //Выясняем номер файла с минимальным значением
				if (min > valueOfLines[i]) {
					min = valueOfLines[i];
					fileNumber = i;
				}
			}
			massiv.add(min);
			try {
				countLinesInFiles.set(fileNumber, countLinesInFiles.get(fileNumber) + 1);
				if (countLinesInFiles.get(fileNumber) == listOfFiles.get(fileNumber).getNumberOfLines()) { //Если счётчик номера строки равен количеству строк в файле присваеваем строке макс возможное значение
					valueOfLines[fileNumber] = Integer.MAX_VALUE;
				}
				valueOfLines[fileNumber] = Integer.parseInt(listOfFiles.get(fileNumber).readFileLine());
			} catch (NumberFormatException e) {
				System.out.println("Не удаётся привести строку к целочисленному значению. Описание ошибки: " + e.getMessage());
			}

		}

		System.out.println(massiv);
		System.out.println("End of program");
	}
}