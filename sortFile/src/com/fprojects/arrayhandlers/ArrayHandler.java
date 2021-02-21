package com.fprojects.arrayhandlers;

import java.util.*;

/**
 *Обработка массивов, преобразование их в численные
 */
public class ArrayHandler {

	public ArrayList toIntArray(ArrayList arrayList) {
		ArrayList intArray = new ArrayList<Integer>();

		for (Object i : arrayList) {
			try {
				intArray.add(Integer.parseInt((String)i));
			} catch (Exception e) {
				System.out.println("Cant convert " + i + " to int. Error description: " + e.getMessage());
			}
		}

		return intArray;
	}

}
