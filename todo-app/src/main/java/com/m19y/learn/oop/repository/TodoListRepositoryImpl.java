package com.m19y.learn.oop.repository;

import com.m19y.learn.oop.entity.Todo;

import java.util.Objects;

public class TodoListRepositoryImpl implements TodoListRepository {

  public Todo[] data = new Todo[10];

  @Override
  public Todo[] getAll() {
    return data;
  }

  public int totalItems() {
    int count = 0;
    for (Todo todo : data) {
      if (Objects.nonNull(todo)) {
        count++;
      }
    }
    return count;
  }

  public boolean isFull(){

    // cek apakah array model sudah penuh?
    boolean isFull = true;
    for (int i = 0; i < data.length; i++) {
      if(data[i] == null){
        isFull = false;
        break;
      }
    }
    return isFull;
  }

  public void resizeIfFull(){
    // jika arraynya sudah penuh maka kita akan resize arranya 2x lipat
    if(isFull()){
      Todo[] temp = data;
      data = new Todo[data.length * 2];

      System.arraycopy(temp, 0, data, 0, temp.length);

//      for (int i = 0; i < temp.length; i++) {
//        data[i] = temp[i];
//      }
    }

  }
  @Override
  public void add(Todo todo) {

    resizeIfFull();

    // tambahkan data ke array yang masih null
    for (int i = 0; i < data.length; i++) {
      if(data[i] == null){
        data[i] = todo;
        break;
      }
    }

  }

  @Override
  public boolean remove(Integer number) {
    if((number - 1 ) >= data.length){
      return false;
    }else if(data[number - 1] == null){
      return false;
    }else{
      for (int i = (number - 1); i < data.length; i++) {
        if(i == (data.length - 1)){
          data[i] = null;
        }else{
          data[i] = data[i + 1];
        }
      }
      return true;
    }
  }
}
