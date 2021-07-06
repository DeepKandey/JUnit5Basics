package com.example.mockito;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockList {

  @Test
  public void mockListTest() {
    List<String> mockList = mock(List.class);
    when(mockList.size()).thenReturn(5);
    assertEquals(5, mockList.size());
  }
}
