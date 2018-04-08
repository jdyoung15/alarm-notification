package com.example.alarmnotification;

import com.example.alarmnotification.temporal.parsers.input.TimeInputParser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class TimeParserTest {

  TimeInputParser parser;

  @Before
  public void setUp() {
    parser = new TimeInputParser();
  }

  @After
  public void tearDown() {
    parser = null;
  }

  @Test
  public void parseAmPmCombined1() {
    assertEquals(LocalTime.of(16, 0), parser.parse("4PM"));
  }

  @Test
  public void parseAmPmCombined2() {
    assertEquals(LocalTime.of(16, 0), parser.parse("4pm"));
  }

  @Test
  public void parseAmPmCombined3() {
    assertEquals(LocalTime.of(4, 0), parser.parse("4am"));
  }

  @Test
  public void parseAmPmCombined4() {
    assertEquals(LocalTime.of(16, 0), parser.parse("4Pm"));
  }

  @Test
  public void parseAmPmCombined5() {
    assertEquals(LocalTime.of(16, 0), parser.parse("4P.M."));
  }

  @Test
  public void parseAmPmCombined6() {
    assertEquals(LocalTime.of(4, 0), parser.parse("4a.m."));
  }

  @Test
  public void parseAmPmCombined7() {
    assertEquals(LocalTime.of(16, 0), parser.parse("4p"));
  }

  @Test
  public void parseAmPmCombined8() {
    assertEquals(LocalTime.of(4, 0), parser.parse("4A"));
  }

  @Test
  public void parseAmPmCombined9() {
    assertEquals(LocalTime.of(4, 30), parser.parse("4:30A"));
  }

  @Test
  public void parseAmPmCombined10() {
    assertEquals(LocalTime.of(16, 0), parser.parse("4.00p.m."));
  }

  @Test
  public void parseAmPmCombined11() {
    assertEquals(LocalTime.of(4, 30), parser.parse("430Am"));
  }

  @Test
  public void parseAmPmSeparate1() {
    assertEquals(LocalTime.of(16, 0), parser.parse("4 PM"));
  }

  @Test
  public void parseAmPmSeparate2() {
    assertEquals(LocalTime.of(16, 0), parser.parse("4 pm"));
  }

  @Test
  public void parseAmPmSeparate3() {
    assertEquals(LocalTime.of(4, 0), parser.parse("4 am"));
  }

  @Test
  public void parseAmPmSeparate4() {
    assertEquals(LocalTime.of(16, 0), parser.parse("4 Pm"));
  }

  @Test
  public void parseAmPmSeparate5() {
    assertEquals(LocalTime.of(16, 0), parser.parse("4 P.M."));
  }

  @Test
  public void parseAmPmSeparate6() {
    assertEquals(LocalTime.of(4, 0), parser.parse("4 a.m."));
  }

  @Test
  public void parseAmPmSeparate7() {
    assertEquals(LocalTime.of(16, 0), parser.parse("4 p"));
  }

  @Test
  public void parseAmPmSeparate8() {
    assertEquals(LocalTime.of(4, 0), parser.parse("4 A"));
  }

  @Test
  public void parseAmPmSeparate9() {
    assertEquals(LocalTime.of(4, 30), parser.parse("430 Am"));
  }

  @Test
  public void parseAmPmSeparate10() {
    assertEquals(LocalTime.of(16, 0), parser.parse("400 p"));
  }

  @Test
  public void parseAmPmSeparate11() {
    assertEquals(LocalTime.of(4, 30), parser.parse("4.30 a.m."));
  }

  @Test
  public void parseNoAmPm1() {
    assertEquals(LocalTime.of(4, 0), parser.parse("4"));
  }

  @Test
  public void parseNoAmPm2() {
    assertEquals(LocalTime.of(4, 30), parser.parse("4:30"));
  }

  @Test
  public void parseNoAmPm3() {
    assertEquals(LocalTime.of(4, 30), parser.parse("4.30"));
  }

  @Test
  public void parseNoAmPm4() {
    assertEquals(LocalTime.of(4, 30), parser.parse("430"));
  }

  @Test
  public void parseInvalid1() {
    assertEquals(null, parser.parse(""));
  }

  @Test
  public void parseInvalid2() {
    assertEquals(null, parser.parse("am"));
  }

  @Test
  public void parseInvalid3() {
    assertEquals(null, parser.parse("40am"));
  }

  @Test
  public void parseInvalid4() {
    assertEquals(null, parser.parse("five am"));
  }

  @Test
  public void parseInvalid5() {
    assertEquals(null, parser.parse("4:3"));
  }

}