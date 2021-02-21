package com.fprojects.validation;

import java.lang.annotation.Documented;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileNameValidator.class)
@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileName {

	/**
	 * Сообщение ошибки валидации
	 */
	String message() default "Введено не подходящее имя файла";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
