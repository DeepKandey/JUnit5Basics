package com.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayName("When Running MathUtils")
class MathUtilsTest {

  MathUtils mathUtils;
  TestInfo testInfo;
  TestReporter testReporter;

  @BeforeEach
  public void beforeEach(TestInfo testInfo, TestReporter testReporter) {
    this.testInfo = testInfo;
    this.testReporter = testReporter;
    mathUtils = new MathUtils();
    testReporter.publishEntry(
        "Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
  }

  @Nested
  @Tag("Math")
  class AddTest {

    @Test
    @DisplayName("Testing Add method for positive")
    void addPositiveTest() {
      assertEquals(2, mathUtils.add(1, 1), "should add 2 numbers");
    }

    @Test
    @DisplayName("Testing Add method for negative")
    void addNegativeTest() {
      int expected = -2;
      int actual = mathUtils.add(-1, -1);
      assertEquals(
          expected, actual, () -> "should return sum " + expected + " but returned " + actual);
    }
  }

  @RepeatedTest(3)
  @Tag("Circle")
  void computeCircleAreaTest(RepetitionInfo repetitionInfo, TestInfo testInfo) {
    if (repetitionInfo.getCurrentRepetition() == 1) {
      System.out.println("1st repetition of " + testInfo.getTestMethod().get().getName());
    }
    assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "should return circle area");
  }

  @Test
  @DisplayName("TestingDivision")
  @Tag("Math")
  void divideTest() {
    assertThrows(
        ArithmeticException.class, () -> mathUtils.divide(1, 0), "divide by zero should throw");
  }

  @Test
  @DisplayName("TestingDisabled")
  @Disabled
  void disableTest() {
    fail("This test should be disabled");
  }

  @Test
  @DisplayName("TestingEnabledOnOS")
  @EnabledOnOs(OS.LINUX)
  void enableTest() {
    fail("This test should be enabled on LINUX only");
  }

  @Test
  @DisplayName("TestingAssumption")
  @Tag("Assumption")
  void assumeTest() {
    boolean isServerUp = false;
    assumeTrue(isServerUp, "Server was down");
  }

  @Test
  @DisplayName("Testing multiplication")
  @Tag("Math")
  void multiply() {
    assertAll(
        "should return result of multiplication",
        () -> assertEquals(4, mathUtils.multiply(2, 2)),
        () -> assertEquals(0, mathUtils.multiply(2, 0)),
        () -> assertEquals(-2, mathUtils.multiply(2, -1)));
  }
}
