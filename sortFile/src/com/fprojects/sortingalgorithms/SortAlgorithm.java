package com.fprojects.sortingalgorithms;

import com.fprojects.filehandlers.*;
import com.fprojects.inputwindows.*;

import java.io.*;
import java.util.*;

/**
 *Построковая сортировка и запись в фойл
 */
public class SortAlgorithm {

	public void sortFiles(InputWindows inputWindows) throws IOException {

		ArrayList<Integer> massiv = new ArrayList(); //TODO заменить на запись в файл
		MyFileWriter myFileWriter = new MyFileWriter();
		myFileWriter.createFileWriter(inputWindows);

		ArrayList<MyFileReader> listOfFiles = new ArrayList<>(); //Список с параметрами каждого файла и ридером
		ArrayList<Integer> countLinesInFiles = new ArrayList<>(); //Список счётчиков номера текущей линии (строки) в каждом файле
		for (int i = 0; i < inputWindows.getNumberOfInputFiles(); i++) {
			MyFileReader myFileReader = new MyFileReader();
			myFileReader.crtReader((String)inputWindows.getInputFileNames().get(i));
			if (myFileReader.getFileReader() != null) {
				listOfFiles.add(myFileReader);
				countLinesInFiles.add(0);
			}
		}

		int[] valueOfLines = new int[listOfFiles.size()];
		for (int i = 0; i < listOfFiles.size(); i++) { //Считываем значения первых строк файлов
			try {
				valueOfLines[i] = Integer.parseInt(listOfFiles.get(i).readFileLine());
			} catch (NumberFormatException e) {
				System.out.println("Не удаётся привести строку к целочисленному значению. Описание ошибки: " + e.getMessage());
			}
		}

		while (true) {
			int numberOfEndedFiles = 0; //Количество файлов с кончившимися строками
			for (int i = 0; i < listOfFiles.size(); i++) {
				if (countLinesInFiles.get(i) >= listOfFiles.get(i).getNumberOfLines()) {
					numberOfEndedFiles++;
				}
			}
			if (numberOfEndedFiles == listOfFiles.size())
				break; //Если количество файлов с кончившимися строками равно общему кол-ву файлов - прерываем

			int minOrMax = valueOfLines[0];
			int fileNumber = 0; //Номер файла с минимальным/максимальным значением
			if ("-d".equals(inputWindows.getSortType())) { //Для сортировки по убыванию
				for (int i = 0; i < listOfFiles.size(); i++) { //Выясняем номер файла с максимальным значением
					if (minOrMax < valueOfLines[i]) {
						minOrMax = valueOfLines[i];
						fileNumber = i;
					}
				}
			} else { //Для сортировки по возрастанию
				for (int i = 0; i < listOfFiles.size(); i++) { //Выясняем номер файла с минимальным значением
					if (minOrMax > valueOfLines[i]) {
						minOrMax = valueOfLines[i];
						fileNumber = i;
					}
				}
			}

			massiv.add(minOrMax);//TODO заменить на запись в файл
			myFileWriter.writeOneLineInFile(Integer.toString(minOrMax));
			try {
				countLinesInFiles.set(fileNumber, countLinesInFiles.get(fileNumber) + 1);
				if (countLinesInFiles.get(fileNumber) == listOfFiles.get(fileNumber).getNumberOfLines()) { //Если счётчик номера строки равен количеству строк в файле присваеваем строке макс возможное значение
					if ("-d".equals(inputWindows.getSortType())) {
						valueOfLines[fileNumber] = Integer.MIN_VALUE;
					} else {
						valueOfLines[fileNumber] = Integer.MAX_VALUE;
					}
				}
				valueOfLines[fileNumber] = Integer.parseInt(listOfFiles.get(fileNumber).readFileLine());
			} catch (NumberFormatException e) {
				System.out.println("Не удаётся привести строку к целочисленному значению. Описание ошибки: " + e.getMessage());
			}

		}
		System.out.println(massiv);
	}

}
