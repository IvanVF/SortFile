package com.fprojects.filehandlers;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

/**
 *Считывние данных из файла
 */
public class MyFileReader {

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
