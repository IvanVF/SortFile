package com.fprojects.inputwindows;

import lombok.*;

import javax.swing.*;
import java.util.*;

/**
 * Сбор информации пользователя посредством модальных окон
 */
@Data
public class InputWindows {

	private String sortType = "-a";
	private String dataType = "";
	private String outputFileName = "";
	private String numOFiles = "";
	private int numberOfInputFiles;
	private List inputFileNames = new ArrayList<String>();

	public void collectInputInformation() {

		/**
		 *Ввод режима сортировки
		 */
		sortType = JOptionPane.showInputDialog("Введите режим сортировки: -a по возрастанию, -d по убыванию");
		if (!sortType.equals("-a") && !sortType.equals("-d")) {
			JOptionPane.showMessageDialog(null, "Режим сортировки указан неверно. Будет выполнена сортировка по возрастанию");
		}

		/**
		 *Ввод типа данных во входных файлах
		 */
		while (!dataType.equals("-s") && !dataType.equals("-i")) {
			dataType = JOptionPane.showInputDialog("Введите тип данных: -s строковый, -i целочисленный");
		}

		/**
		 *Ввод имени выходного файла
		 */
		while (outputFileName.equals("")) {
			outputFileName = JOptionPane.showInputDialog("Введите имя выходного файла");
			if (!outputFileName.matches("[a-zA-Zа-яА-ЯёЁ0-9_-]+")) {
				JOptionPane.showMessageDialog(
					null, "Имя файла должно содержать буквы русского и латинского алфавита, цифры, символы '-', '_' и не содержать спецсимволов");
				outputFileName = "";
			}
		}

		/**
		 *Ввод количества входных файлов
		 */
		while (numOFiles.equals("")) {
			numOFiles = JOptionPane.showInputDialog("Введите количество входных файлов не меньше 1");
			if (numOFiles.equals("0") || !numOFiles.matches("[0-9]+")) {
				JOptionPane.showMessageDialog(null, "Количество входных файлов должно быть целочисленным и не меньше 1");
				numOFiles = "";
			} else {
				try {
					numberOfInputFiles = Integer.parseInt(numOFiles);
				} catch (ClassCastException e) {
					JOptionPane.showMessageDialog(null, "Не удалось преобразовать введённое значение в целое число. " + e.getMessage());
				}
			}
		}

		/**
		 *Ввод имён входных файлов
		 */
		for (int i = 0; i < numberOfInputFiles; i++) {
			String inpFName;
			inpFName = JOptionPane.showInputDialog("Введите имя " + (i + 1) + " входного файла, или путь к файлу. Формат файла должен быть .txt");
			if (inpFName.matches("[/a-zA-Zа-яА-ЯёЁ0-9_-]+") && !inpFName.isEmpty()) {
				inputFileNames.add(inpFName + ".txt");
			} else {
				JOptionPane.showMessageDialog(
					null, "Имя файла или путь к нему должны содержать буквы русского и латинского алфавита, цифры, символы '-', '_', '/' и не содержать спецсимволов.");
				i--;
			}

		}

	}

}
