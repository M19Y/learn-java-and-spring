package com.m19y.learn.validation;

import com.m19y.learn.annotation.NotBlank;

import java.lang.reflect.Field;

public class Validation {

  public static void validationReflection(Object object) {
    Class<?> aClass = object.getClass();
    Field[] fields = aClass.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);

      NotBlank notBlankAnnotation = field.getAnnotation(NotBlank.class);

      // apakah filed mempunyai @NotBlank?
      if(notBlankAnnotation != null){
        try {
          String fieldValue = (String) field.get(object);

          // apakah @NotBlank allowEmptyString itu default ? !false (true) jika tidak maka !true (false)
          if (!notBlankAnnotation.allowEmptyString() && fieldValue != null) {
            fieldValue = fieldValue.trim();
          }

          if(fieldValue == null || fieldValue.isEmpty()){
            throw new RuntimeException("Field " + field.getName() + " Must be not blank!");
          }
        } catch (IllegalAccessException e) {
          System.err.println("Tidak bisa mengakeses filed: " + field.getName());
        }
      }
    }

  }

  public static void validateNotBlankFields(Object target) {
    Class<?> clazz = target.getClass();
    Field[] fields = clazz.getDeclaredFields();

    for (Field field : fields) {
      if (!field.isAnnotationPresent(NotBlank.class)) {
        continue;
      }

      field.setAccessible(true);

      try {
        Object value = field.get(target);

        if (!(value instanceof String text)) {
          continue; // Lewati jika bukan String
        }

        NotBlank notBlank = field.getAnnotation(NotBlank.class);

        assert notBlank != null;
        if (!notBlank.allowEmptyString()) {
          text = text.trim();
        }

        if (text.isEmpty()) {
          throw new RuntimeException("Field '" + field.getName() + "' must not be blank!");
        }

      } catch (IllegalAccessException e) {
        System.err.println("Tidak bisa mengakses field: " + field.getName());
      }
    }
  }

}
