package com.fprojects;

import com.fprojects.inputwindows.*;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		System.out.println("Program started successful");

		InputWindows inputWindows = new InputWindows();
		inputWindows.collectInputInformation();

		System.out.println("End of program");
	}
}

//MyFileReader myFileReader = new MyFileReader();
//ArrayHandler arrayHandler = new ArrayHandler();

//ArrayList firstArray = myFileReader.readFile("numbers.txt");
//ArrayList secondArray = myFileReader.readFile("numbers2.txt");

//ArrayList intFirstArray = arrayHandler.toIntArray(firstArray);
//ArrayList intSecondArray = arrayHandler.toIntArray(secondArray);

//System.out.println(intFirstArray);
//System.out.println(intSecondArray);
