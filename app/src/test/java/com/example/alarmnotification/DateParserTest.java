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
    assertEquals(LocalDate.of(LocalDate.now().getYear(), 12, 31), parser.parse("12/31"));
  }

  @Test
  public void parseDash1() {
    assertEquals(LocalDate.of(2018, 1, 1), parser.parse("1-1-2018"));
  }

  @Test
  public void parseDash2() {
    assertEquals(LocalDate.of(2018, 1, 1), parser.parse("01-01-2018"));
  }

  @Test
  public void parseDash3() {
    assertEquals(LocalDate.of(2018, 1, 1), parser.parse("1-1-18"));
  }

  @Test
  public void parseDash4() {
    assertEquals(LocalDate.of(LocalDate.now().getYear(), 12, 31), parser.parse("12-31"));
  }

  @Test
  public void parseSpaceSeparatedAbbrev1() {
    assertEquals(LocalDate.of(2018, 1, 1), parser.parse("Jan 1 2018"));
  }

  @Test
  public void parseSpaceSeparatedAbbrev2() {
    assertEquals(LocalDate.of(2018, 1, 1), parser.parse("jan 1 18"));
  }

  @Test
  public void parseSpaceSeparatedAbbrev3() {
    assertEquals(LocalDate.of(2018, 12, 31), parser.parse("dec 31"));
  }

  @Test
  public void parseSpaceSeparatedFull1() {
    assertEquals(LocalDate.of(2018, 1, 1), parser.parse("january 1 2018"));
  }

  @Test
  public void parseSpaceSeparatedFull2() {
    assertEquals(LocalDate.of(2018, 1, 1), parser.parse("January 1 18"));
  }

  @Test
  public void parseSpaceSeparatedFull3() {
    assertEquals(LocalDate.of(2018, 12, 31), parser.parse("December 31"));
  }

}