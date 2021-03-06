package com.extension;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


public class TestResultLoggerExtension implements TestWatcher, AfterAllCallback {

  private List<TestResultStatus> testResultsStatus = new ArrayList<>();

  private enum TestResultStatus {
    SUCCESSFUL,
    ABORTED,
    FAILED,
    DISABLED
  }

  @Override
  public void afterAll(ExtensionContext extensionContext) {
    Map<TestResultStatus, Long> summary =
        testResultsStatus.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    System.out.println(
        "Test result summary for " + extensionContext.getDisplayName() + " " + summary.toString());
  }

  @Override
  public void testDisabled(ExtensionContext context, Optional<String> reason) {

    System.out.println(
        "Test Disabled for test "
            + context.getDisplayName()
            + " with reason :- "
            + reason.orElse("No reason"));
    testResultsStatus.add(TestResultStatus.DISABLED);
  }

  @Override
  public void testSuccessful(ExtensionContext context) {
    System.out.println("Test successful for test " + context.getDisplayName());
    testResultsStatus.add(TestResultStatus.SUCCESSFUL);
  }

  @Override
  public void testAborted(ExtensionContext context, Throwable cause) {
    System.out.println("Test Aborted for test " + context.getDisplayName());
    testResultsStatus.add(TestResultStatus.ABORTED);
  }

  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
    System.out.println("Test Failed for test " + context.getDisplayName());
    testResultsStatus.add(TestResultStatus.FAILED);
  }
}
