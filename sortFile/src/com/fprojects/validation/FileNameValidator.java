package com.fprojects.validation;

import javax.validation.*;

/**
 * Валидатор названия файла
 */
public class FileNameValidator implements ConstraintValidator<FileName, String> {

	@Override
	public boolean isValid(String fileName, ConstraintValidatorContext cxt) {
		return fileName.matches("[a-zA-Zа-яА-ЯёЁ0-9_-]+");
	}

}
