package com.example.mockito;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class InjectMock {

  @Mock List<String> mockList;
  @InjectMocks Employee mockEmployee;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void employeeMockTest() {
    when(mockList.get(0)).thenReturn("Deepak");
    when(mockList.size()).thenReturn(1);

    assertEquals("Deepak", mockList.get(0));
    assertEquals(1, mockList.size());

    assertEquals("Deepak", mockEmployee.getEmpName().get(0));
    assertEquals(1, mockEmployee.getEmpName().size());

    mockList.add("Hitesh");
    System.out.println(mockList.get(1));
  }
}

@Getter
@Setter
class Employee {
  List<String> empName;
}
