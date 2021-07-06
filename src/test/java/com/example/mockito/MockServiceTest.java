package com.example.mockito;

import com.example.mockito.ServiceProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class MockServiceTest {

  @Test
  public void testMock() {
    ServiceProvider serviceProvider = Mockito.mock(ServiceProvider.class);
    Mockito.when(serviceProvider.add(30, 40)).thenReturn(70);
    assertEquals(70, serviceProvider.add(30, 40));
  }
}
