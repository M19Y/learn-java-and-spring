package com.m19y.learn;

import com.m19y.learn.data.Author;
import com.m19y.learn.data.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class ParameterizedTest {


  @Test
  void getMethodGenericTest() throws NoSuchMethodException {

    Class<Book> bookClass = Book.class;

    Method getAuthors = bookClass.getMethod("getAuthors");

    Type type = getAuthors.getGenericReturnType();


    if (type instanceof ParameterizedType parameterizedType){

      Assertions.assertEquals(List.class, parameterizedType.getRawType());
      Assertions.assertNull(parameterizedType.getOwnerType());
      Assertions.assertEquals(Author.class, parameterizedType.getActualTypeArguments()[0]);
      System.out.println(Arrays.toString(parameterizedType.getActualTypeArguments()));
    }

    Assertions.assertInstanceOf(ParameterizedType.class, type);
  }

  @Test
  void getMethodParameterGenericTest() throws NoSuchMethodException {

    Class<Book> bookClass = Book.class;

    Method setAuthors = bookClass.getMethod("setAuthors", List.class);

    Type[] genericParameterTypes = setAuthors.getGenericParameterTypes();
    for (Type type : genericParameterTypes) {

      if (type instanceof ParameterizedType parameterizedType){
        System.out.println(parameterizedType.getRawType());
        System.out.println(parameterizedType.getOwnerType());
        System.out.println(Arrays.toString(parameterizedType.getActualTypeArguments()));
      }
    }
  }

  @Test
  void fieldGenericTest() throws NoSuchFieldException {

    Class<Book> bookClass = Book.class;
    Field authors = bookClass.getDeclaredField("authors");
    authors.setAccessible(true);

    Assertions.assertEquals(List.class, authors.getType());
    Type type = authors.getGenericType();

    if(type instanceof ParameterizedType parameterizedType){
      Assertions.assertEquals(List.class, parameterizedType.getRawType());
      Assertions.assertEquals(Author.class, parameterizedType.getActualTypeArguments()[0]);
    }
  }
}
