package com.fprojects;

import com.fprojects.inputwindows.*;
import com.fprojects.sortingalgorithms.*;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Program started successful");

		InputWindows inputWindows = new InputWindows();
		SortAlgorithm sortAlgorithm = new SortAlgorithm();

		inputWindows.collectInputInformation();
		sortAlgorithm.sortFiles(inputWindows);

		System.out.println("End of program");
	}
}