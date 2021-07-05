package com.example;

public class Person {

  String firstName;
  String middleName;
  String lastName;

  // constructor
  public Person(String firstName, String middleName, String lastName) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
  }

  public String fullName() {
    if (middleName == null || middleName.trim().isEmpty()) {
      return String.format("%s %s", firstName, lastName);
    }

    return String.format("%s %s %s", firstName, middleName, lastName);
  }
}
