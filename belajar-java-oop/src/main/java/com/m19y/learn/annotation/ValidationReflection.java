package com.m19y.learn.annotation;

import com.m19y.learn.model.exception.BlankException;

import java.lang.reflect.Field;

public class ValidationReflection {


  public static void validate(Object object){

    Class aClass = object.getClass();
    Field[] fields = aClass.getDeclaredFields();

    for(Field field: fields){
      field.setAccessible(true);
      if(field.getAnnotation(NotBlank.class) != null){
        
        try{
          String value  = (String) field.get(object);
          if(value == null || value.isBlank()){
            throw new BlankException(String.format("Field %s must be not null or blank", field.getName()));
          }
        }catch (IllegalAccessException e){
          System.out.println("Tidak bisa mengakses field: " + field.getName());
        }
      }
    }

  }
}
