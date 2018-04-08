package com.example.alarmnotification.userInput;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import static junit.framework.Assert.assertEquals;

public class UserInputParserTest {

  UserInputParser parser;

  @Before
  public void setUp() {
    parser = new UserInputParser();
  }

  @After
  public void tearDown() {
    parser = null;
  }

  @Test
  public void parseDateTimeBothValid1() {
    ZonedDateTime dateTime = parser.parseDateTime("7/10 4pm send bday card");
    assertEquals(LocalDate.of(2018, 7, 10), dateTime.toLocalDate());
    assertEquals(LocalTime.of(16,0), dateTime.toLocalTime());
  }

  @Test
  public void parseDateTimeBothValid2() {
    ZonedDateTime dateTime = parser.parseDateTime("4pm 7/10 send bday card");
    assertEquals(LocalDate.of(2018, 7, 10), dateTime.toLocalDate());
    assertEquals(LocalTime.of(16,0), dateTime.toLocalTime());
  }

  @Test
  public void parseDateTimeBothValid3() {
    ZonedDateTime dateTime = parser.parseDateTime("4pm send bday card 7/10");
    assertEquals(LocalDate.of(2018, 7, 10), dateTime.toLocalDate());
    assertEquals(LocalTime.of(16,0), dateTime.toLocalTime());
  }

  @Test
  public void parseDateTimeDefaultTime1() {
    ZonedDateTime dateTime = parser.parseDateTime("7/10 send bday card");
    assertEquals(LocalDate.of(2018, 7, 10), dateTime.toLocalDate());
    assertEquals(LocalTime.of(20,0), dateTime.toLocalTime());
  }

  @Test
  public void parseDateTimeDefaultDate1() {
    ZonedDateTime dateTime = parser.parseDateTime("12am send bday card");
    LocalTime time = dateTime.toLocalTime();
    assertEquals(LocalTime.of(0,0), time);
    assertEqualsDefaultDate(dateTime.toLocalDate(), time);
  }

  @Test
  public void parseDateTimeDefaultDate2() {
    ZonedDateTime dateTime = parser.parseDateTime("11:59pm send bday card");
    LocalTime time = dateTime.toLocalTime();
    assertEquals(LocalTime.of(23,59), time);
    assertEqualsDefaultDate(dateTime.toLocalDate(), time);
  }

  @Test
  public void parseDateTimeDefaultBoth1() {
    ZonedDateTime dateTime = parser.parseDateTime("send bday card");
    LocalTime time = dateTime.toLocalTime();
    assertEquals(LocalTime.of(20,0), time);
    assertEqualsDefaultDate(dateTime.toLocalDate(), time);
  }

  private void assertEqualsDefaultDate(LocalDate date, LocalTime time) {
    if (time.isBefore(LocalTime.now())) {
      assertEqualsTomorrow(date);
    }
    else {
      assertEqualsToday(date);
    }
  }

  private void assertEqualsTomorrow(LocalDate date) {
    assertEquals(LocalDate.now().plusDays(1), date);
  }

  private void assertEqualsToday(LocalDate date) {
    assertEquals(LocalDate.now(), date);
  }

  @Test
  public void parseNoteDateTime1() {
    assertEquals("send bday card", parser.parseNote("7/10 4pm send bday card"));
  }

  @Test
  public void parseNoteDateTime2() {
    assertEquals("send bday card", parser.parseNote("4pm 7/10 send bday card"));
  }

  @Test
  public void parseNoteDateTime3() {
    assertEquals("send bday card", parser.parseNote("4pm send bday card 7/10"));
  }

  @Test
  public void parseNoteDateTime4() {
    assertEquals("send bday card", parser.parseNote("send 4pm bday 7/10 card"));
  }

  @Test
  public void parseNoteDateOnly1() {
    assertEquals("send bday card", parser.parseNote("7/10 send bday card"));
  }

  @Test
  public void parseNoteTimeOnly1() {
    assertEquals("send bday card", parser.parseNote("12:00am send bday card"));
  }

  @Test
  public void parseNoteTimeOnly2() {
    assertEquals("send bday card", parser.parseNote("11:59pm send bday card"));
  }

  @Test
  public void parseNoteNoDateTime1() {
    assertEquals("send bday card", parser.parseNote("send bday card"));
  }

}