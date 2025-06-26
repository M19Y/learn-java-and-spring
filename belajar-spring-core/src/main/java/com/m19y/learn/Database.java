package com.m19y.learn;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Database {

  private static Database database;

  // jika databasenya belum terbuat maka, buat baru!
  // apabila databasenya sudah terbuat makan ambil yang suda dibuat sebelumnya
  public static Database getInstance(){
    if(database == null){
      log.info("Create new database object");
      database = new Database();
    }
    return database;
  }

  // agar tidak bisa di instansiasi
  private Database(){}
}
