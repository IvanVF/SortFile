package com.fprojects.filehandlers;

import com.fprojects.inputwindows.*;

import javax.swing.*;
import java.io.*;

/**
 *Запись в файл
 */
public class MyFileWriter {

	private String fileName;
	private FileWriter fileWriter;

	public void createFileWriter(InputWindows inputWindows) {
		try {
			fileName = inputWindows.getOutputFileName() + ".txt";
			fileWriter = new FileWriter(fileName, true);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Не удалось создать файл с текущим названием. Описание ошибки: " + e.getMessage());
		}
	}

	public void writeOneLineInFile(String line) {
		try {
			fileWriter.write(line);
			fileWriter.append("\r\n");
			fileWriter.flush();
		} catch (IOException e) {
			System.out.println("Не удалось записать данные в файл. Описание ошибки: " + e.getMessage());
		}
	}
}
