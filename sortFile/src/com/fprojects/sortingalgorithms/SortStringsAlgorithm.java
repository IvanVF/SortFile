package com.fprojects.sortingalgorithms;

import com.fprojects.filehandlers.*;
import com.fprojects.inputwindows.*;

import java.io.*;
import java.util.*;

/**
 *Построковая сортировка строк и запись в фойл
 */
public class SortStringsAlgorithm {

	public void sortFiles(InputWindows inputWindows) throws IOException {
		MyFileReader myFileReader = new MyFileReader();
		MyFileWriter myFileWriter = new MyFileWriter();
		myFileWriter.createFileWriter(inputWindows);
		String endedLine = UUID.randomUUID().toString(); //метка для определения последней строки в файле

		ArrayList<MyFileReader> listOfFiles = myFileReader.crtListOfFiles(inputWindows); //Список с параметрами каждого файла и ридером
		ArrayList<Integer> countLinesInFiles = new ArrayList<>(); //Список счётчиков номера текущей линии (строки) в каждом файле
		for (int i = 0; i < listOfFiles.size(); i++) {
			countLinesInFiles.add(0);
		}

		String[] valueOfLines = new String[listOfFiles.size()];
		for (int i = 0; i < listOfFiles.size(); i++) { //Считываем значения первых строк файлов
			valueOfLines[i] = listOfFiles.get(i).readFileLine();
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
			String minOrMax = "";
			int fileNumber = 0; //Номер файла с минимальным/максимальным значением
			for (int i = 0; i < listOfFiles.size(); i++) {
				if (valueOfLines[i] != null && !valueOfLines[i].equals(endedLine)) {
					minOrMax = valueOfLines[i];
					fileNumber = i;
				}
			}


			if ("-d".equals(inputWindows.getSortType())) { //Для сортировки по убыванию
				for (int i = 0; i < listOfFiles.size(); i++) { //Выясняем номер файла с максимальным значением
					if (minOrMax != null && minOrMax.length() < valueOfLines[i].length() && !endedLine.equals(valueOfLines[i])) {
						minOrMax = valueOfLines[i];
						fileNumber = i;
					}
				}
			} else { //Для сортировки по возрастанию
				for (int i = 0; i < listOfFiles.size(); i++) { //Выясняем номер файла с минимальным значением
					if (null != minOrMax && minOrMax.length() > valueOfLines[i].length() && !endedLine.equals(valueOfLines[i])) {
						minOrMax = valueOfLines[i];
						fileNumber = i;
					}
				}
			}

			myFileWriter.writeOneLineInFile(minOrMax);
			countLinesInFiles.set(fileNumber, countLinesInFiles.get(fileNumber) + 1);
			if (countLinesInFiles.get(fileNumber) == listOfFiles.get(fileNumber).getNumberOfLines()) { //Если счётчик номера строки равен количеству строк в файле присваеваем строке макс endedLine
				valueOfLines[fileNumber] = endedLine;
			}
			if (countLinesInFiles.get(fileNumber) < listOfFiles.get(fileNumber).getNumberOfLines()) {
				valueOfLines[fileNumber] = listOfFiles.get(fileNumber).readFileLine();
			}
		}

	}
}
