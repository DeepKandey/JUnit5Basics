package com.example.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MockAnnotation {

  @Mock List<String> mockList;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void mockListTest() {
    when(mockList.get(0)).thenReturn("Deepak Automation");
    assertEquals("Deepak Automation", mockList.get(0));
  }
}
