package com.fprojects.filehandlers;

import lombok.*;

import javax.swing.*;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

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
	 *Чтение всего файла и запись в лист
	 */
	public ArrayList readFile(String filePath) {
		ArrayList fileArray = new ArrayList();

		Path path = Paths.get(filePath);
		try (Stream<String> lines = Files.lines(path, StandardCharsets.ISO_8859_1)) {
			lines.forEach(s -> fileArray.add(s));
		} catch (IOException e) {
			System.out.println("Error: cant read the file from path: " + filePath + " Error description: " + e.getMessage());
		}

		return fileArray;
	}

}
