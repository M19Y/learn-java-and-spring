package com.m19y.learn.model.learnstatic;


public class Country {
  private String name;
  private static int population;

  public void setName(String name){
    this.name = name;
  }
  public String getName(){
    return this.name;
  }

  public static class City {
    public String name;

    public void setName(String name){
      this.name = name;
    }
    public String getName(){
      return this.name;
    }

    // bisa mengakses fields yang static juga
    public void setPopulation(int population){
      Country.population = population;
    }

    public int getPopulations(){return Country.population;}
    // tidak bisa mengakses fields yang bukan static
//    public String getCountry(){
//      return Country.this.getName();
//    }
  }
}
