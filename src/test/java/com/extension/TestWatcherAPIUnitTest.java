package com.extension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(TestResultLoggerExtension.class)
public class TestWatcherAPIUnitTest {

  @Test
  void givenFalseIsTrue_whenTestAbortedThenCaptureResult() {
    Assumptions.assumeTrue(false);
  }

  @Disabled
  @Test
  void givenTrueIsTrue_whenTestDisabledThenCaptureResult() {
    Assertions.assertTrue(true);
  }

  @Test
  void givenTrueIsTrue_whenTestAbortedThenCaptureResult() {
    Assumptions.assumeTrue(true);
  }

  @Test
  void givenFailure_whenTestFailedWithReason_ThenCaptureResult() {
    fail("Not yet implemented");
  }
}
