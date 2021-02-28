package com.fprojects;

import com.fprojects.inputwindows.*;
import com.fprojects.sortingalgorithms.*;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Program started successful");

		InputWindows inputWindows = new InputWindows();
		SortNumbersAlgorithm sortNumbersAlgorithm = new SortNumbersAlgorithm();
		SortStringsAlgorithm sortStringsAlgorithm = new SortStringsAlgorithm();

		inputWindows.collectInputInformation();
		if ("-i".equals(inputWindows.getDataType())) {
			sortNumbersAlgorithm.sortFiles(inputWindows);
		} else {
			sortStringsAlgorithm.sortFiles(inputWindows);
		}


		System.out.println("End of program");
	}
}