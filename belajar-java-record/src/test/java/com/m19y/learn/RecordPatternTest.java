package com.m19y.learn;

import org.junit.jupiter.api.Test;

public class RecordPatternTest {

  @Test
  void beforeRecordPattern() {
    printObjectBefore(new Point(1, 2));
  }

  @Test
  void afterRecordPattern() {
    printObjectAfter(new Point(1, 2));
  }

  @Test
  void nestedRecordPattern() {
    printObjectNested(new Line(new Point(1,2), new Point(3, 4)));
  }

  void printObjectBefore(Object object){
    if(object instanceof Point){
      Point point = (Point) object;
      System.out.println(point.x());
      System.out.println(point.y());
      return;
    }
    System.out.println(object);
  }

  void printObjectAfter(Object object){
    if(object instanceof Point(int x, int y)){
      System.out.println(x);
      System.out.println(y);
      return;
    }

    System.out.println(object);
  }

  void printObjectNested(Object object){
    if(object instanceof Line(Point(int startX, int startY), Point end)){
      System.out.println(startX);
      System.out.println(startY);
      System.out.println(end);
      return;
    }
    System.out.println(object);
  }
}
