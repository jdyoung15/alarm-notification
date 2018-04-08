package com.example.alarmnotification;

import com.example.alarmnotification.temporal.parsers.input.DateInputParser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.Assert.assertEquals;

public class DateParserTest {

  DateInputParser parser;

  @Before
  public void setUp() {
    parser = new DateInputParser();
  }

  @After
  public void tearDown() {
    parser = null;
  }
  
  @Test
  public void parseSlash1() {
    assertEquals(LocalDate.of(2018, 1, 1), parser.parse("1/1/2018"));
  }

  @Test
  public void parseSlash2() {
    assertEquals(LocalDate.of(2018, 1, 1), parser.parse("01/01/2018"));
  }

  @Test
  public void parseSlash3() {
    assertEquals(LocalDate.of(2018, 1, 1), parser.parse("1/1/18"));
  }

  @Test
  public void parseSlash4() {
    assertEquals(LocalDate.of(2018, 12, 31), parser.parse("12/31"));
  }

}