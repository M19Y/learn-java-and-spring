package com.m19y.belajar;

public class DaemonApp {

  public static void main(String[] args) {


    Thread thread = new Thread(() -> {
      try {
        Thread.sleep(3000);
        System.out.println("Run thread");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    // by default thread diatas adalah 'user thread'
    // program kita akan menunggu sebuah 'user thread' selesai berjalan, lalu program di selesaikan.
    // beda halnya dengan 'daemon thread'.
    // program kita tidak akan menunggu daemon thread dijalankan atau tidak, jika program java akan berhenti

    // menjadikan user thread -> daemon thread
//    thread.setDaemon(true); // jika dimatikan akan menjadi user thread
    thread.start();
    System.out.println("program selesai");


  }
}
