package com.fprojects.filehandlers;

import com.fprojects.inputwindows.*;
import lombok.*;

import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 *Считывние данных из файла
 */
@Data
public class MyFileReader {

	private File file;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private int numberOfLines = 0;

	/**
	 *Создание bufferedReader и подсчёт кол-ва линий (строк) в файле
	 */
	public void crtReader(String path) {
		try {
			file = new File(path);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			while (bufferedReader.readLine() !=null) numberOfLines++;
			bufferedReader.close();
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Не удалось обнаружить указанный файл " + path +  " Описание ошибки: " + e.getMessage());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Не удалось прочитать указанный файл " + path + " Описание ошибки: " + e.getMessage());
		}


	}

	/**
	 *Чтение следующей строки
	 */
	public String readFileLine() throws IOException, NullPointerException {
		String line = bufferedReader.readLine();
		return line;
	}

	/**
	 *Создание списка файлов с их параметрами
	 */
	public ArrayList<MyFileReader> crtListOfFiles(InputWindows inputWindows) {
		ArrayList<MyFileReader> listOfFiles = new ArrayList<>();
		for (int i = 0; i < inputWindows.getNumberOfInputFiles(); i++) {
			MyFileReader myFileReader = new MyFileReader();
			myFileReader.crtReader((String)inputWindows.getInputFileNames().get(i));
			if (myFileReader.getFileReader() != null) {
				listOfFiles.add(myFileReader);
			}
		}
		return listOfFiles;
	}

}
