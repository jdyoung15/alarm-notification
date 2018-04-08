package com.example.alarmnotification.temporal.parsers.input;

import com.example.alarmnotification.temporal.parsers.pattern.TemporalPatternParser;
import com.example.alarmnotification.temporal.parsers.TemporalParser;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class TemporalInputParser implements TemporalParser {

  private TemporalPatternParser[] patterns;

  public TemporalInputParser(TemporalPatternParser[] patterns) {
    this.patterns = patterns;
  }

  public Temporal parse(String text) {
    List<String> words = preParse(text);
    for (TemporalPatternParser pattern : patterns) {
      int numWords = pattern.numWordsToParse();
      for (int i = 0; i + numWords <= words.size(); i++) {
        String phrase = createPhrase(words, i, i + numWords);
        Temporal temporal = pattern.parse(phrase);
        if (temporal != null) {
          return temporal;
        }
      }
    }
    return null;
  }

  public List<Integer> parsedPositions(String text) {
    List<String> words = preParse(text);
    for (TemporalPatternParser pattern : patterns) {
      int numWords = pattern.numWordsToParse();
      for (int i = 0; i + numWords <= words.size(); i++) {
        String phrase = createPhrase(words, i, i + numWords);
        Temporal temporal = pattern.parse(phrase);
        if (temporal != null) {
          return findIndices(i, i + numWords);
        }
      }
    }
    return new ArrayList<>();
  }

  private List<String> preParse(String text) {
    String[] words = text.trim().split("\\s+");
    List<String> wordList = Arrays.asList(words);
    return wordList.stream().map(w -> format(w)).collect(Collectors.toList());
  }

  abstract String format(String word);

  private String createPhrase(List<String> words, int from, int to) {
    return String.join(" ", words.subList(from, to));
  }

  private List<Integer> findIndices(int from, int to) {
    return IntStream.range(from, to).boxed().collect(Collectors.toList());
  }

}
