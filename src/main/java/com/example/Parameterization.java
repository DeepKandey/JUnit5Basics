package com.example;

public class Parameterization {

  public boolean isOdd(int number) {
    return number % 2 != 0;
  }

  public boolean isBlank(String input){
    return input==null || input.isBlank();
  }
}
