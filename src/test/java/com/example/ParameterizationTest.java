package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.*;

import java.time.Month;
import java.util.EnumSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterizationTest {

  Parameterization parameterization;

  @BeforeEach
  public void beforeEach() {
    parameterization = new Parameterization();
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5, -3, 15, 18, Integer.MAX_VALUE})
  public void isOdd_ShouldReturnTrueForOddNumbers(int number) {
    assertTrue(parameterization.isOdd(number), number + " is not a odd number");
  }

  @ParameterizedTest
  @NullSource
  @EmptySource
  @ValueSource(strings = {"", "  "})
  public void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input) {
    assertTrue(parameterization.isBlank(input), input + " is not null or blank string");
  }

  @ParameterizedTest
  @EnumSource(Month.class)
  public void getValueForMonth_IsAlwaysBetweenOneAndTwelve(Month month) {
    int monthNumber = month.getValue();
    assertTrue(monthNumber >= 1 && monthNumber <= 12, "Expected does not match actual");
  }

  @ParameterizedTest(name = "{index} {0} is 30 days long")
  @EnumSource(
      value = Month.class,
      names = {"APRIL", "JUNE", "SEPTEMBER", "OCTOBER"})
  public void someMonths_Are30DaysLong(Month month) {
    final boolean isALeapYear = false;
    assertEquals(30, month.length(isALeapYear), month + " is not 30 days long");
  }

  @ParameterizedTest
  @EnumSource(
      value = Month.class,
      names = {"APRIL", "JUNE", "SEPTEMBER", "OCTOBER"},
      mode = EnumSource.Mode.EXCLUDE)
  public void exceptFiveMonths_OthersAre31Days(Month month) {
    final boolean isALeapYear = false;
    assertEquals(31, month.length(isALeapYear), month + " is not 31 days long");
  }

  @ParameterizedTest
  @EnumSource(value = Month.class, names = ".+BER", mode = EnumSource.Mode.MATCH_ANY)
  public void fourMonths_AreEndingWithBer(Month month) {
    EnumSet<Month> months =
        EnumSet.of(Month.DECEMBER, Month.NOVEMBER, Month.OCTOBER, Month.SEPTEMBER);
    assertTrue(months.contains(month), "Not ending with Ber: " + month);
  }

  @ParameterizedTest
  @CsvSource({"test,TEST", "tEst,TEST", "Java,JAVA"})
  public void toUpperCase_ShouldGenerateTheExpectedUpperCaseValue(String input, String expected) {
    String actualValue = input.toUpperCase();
    assertEquals(expected, actualValue);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
  void toUpperCase_ShouldGenerateTheExpectedUppercaseValueCSVFile(String input, String expected) {
    String actualValue = input.toUpperCase();
    assertEquals(expected, actualValue);
  }

  @ParameterizedTest
  @MethodSource("provideStringsForIsBlank")
  public void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input, boolean expected) {
    assertEquals(expected, input.isBlank());
  }

  private static Stream<Arguments> provideStringsForIsBlank() {
    return Stream.of(
        Arguments.of(null, true),
        Arguments.of("", true),
        Arguments.arguments("  ", true),
        Arguments.of("not blank", false));
  }

  @ParameterizedTest
  @MethodSource // hmm, no method name ...
  void isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument(String input) {
    assertTrue(input.isBlank());
  }

  private static Stream<String> isBlank_ShouldReturnTrueForNullOrBlankStringsOneArgument() {
    return Stream.of(null, "", "  ");
  }

  @ParameterizedTest
  @MethodSource("com.example.StringParams#blankString")
  void isBlank_ShouldReturnTrueForNullOrBlankStringsExternalSource(String input) {
    assertTrue(input.isBlank());
  }

  @ParameterizedTest
  @ArgumentsSource(BlankStringsArgumentsProvider.class)
  public void isBlank_ShouldReturnTrueForNullOrBlankStringsArgProvider(String input) {
    assertTrue(input.isBlank());
  }

  @ParameterizedTest
  @CsvSource({"Issac,,Newton,Issac Newton", "Charles,Robert,Darwin,Charles Robert Darwin"})
  public void fullName_ShouldGenerateTheExpectedFullName(ArgumentsAccessor argumentsAccessor) {
    String firstName = argumentsAccessor.getString(0);
    String middleNme = argumentsAccessor.getString(1);
    String lastName = argumentsAccessor.get(2, String.class);
    String expectedFullName = argumentsAccessor.getString(3);

    Person person = new Person(firstName, middleNme, lastName);
    assertEquals(expectedFullName, person.fullName());
  }
}

class StringParams {
  static Stream<String> blankString() {
    return Stream.of(null, "", "  ");
  }
}
