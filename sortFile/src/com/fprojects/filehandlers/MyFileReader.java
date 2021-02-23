package com.fprojects.filehandlers;

import lombok.*;

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

	public void crtReader(String path) throws IOException, FileNotFoundException {
		file = new File(path);
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(fileReader);
		while (bufferedReader.readLine() !=null) numberOfLines++;
		bufferedReader.close();
		fileReader = new FileReader(file);
		bufferedReader = new BufferedReader(fileReader);



	}

	public String readFileLine() throws IOException {
		String line = bufferedReader.readLine();
		return line;
	}

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
