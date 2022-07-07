package most.active.cookie.filter.filter.impl;

import most.active.cookie.filter.exception.LogParsingException;
import most.active.cookie.filter.filter.CookieFilter;
import most.active.cookie.filter.dto.CommandLineInput;
import most.active.cookie.filter.parser.LogEntry;
import most.active.cookie.filter.parser.LogParser;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;

import java.util.stream.Collectors;

public class CookieFilterImpl implements CookieFilter {

  @Override
  public void filterMostActiveCookies(CommandLineInput commandLineInput) throws LogParsingException {

    /** Parse input file and get the list of cookies */
    List<LogEntry> cookies = LogParser.parseLog(commandLineInput.getFileName());

    // Grouping cookies by provided date
    Map<String, Long> groupCookieByDate = groupCookiesBySelectedDate(commandLineInput.getSelectedDate(), cookies);

    // Calculate the frequency of most active cookies
    OptionalLong mostActiveCookieFreq = mostActiveCookieFreq(groupCookieByDate);

    // Scan through the list of cookies and output the ones which have the max frequency value
    mostActiveCookieFreq.ifPresent(maxFreq -> outputMostActiveCookies(groupCookieByDate, maxFreq));
  }

  /** Group cookies and return HashMap, <key, value> = <cookie date, number of occurrence> */
  private Map<String, Long> groupCookiesBySelectedDate(LocalDate filterDate, List<LogEntry> cookies) {
    return cookies.stream()
        .filter(log -> filterDate.isEqual(log.getTimestamp().toLocalDate()))
        .collect(Collectors.groupingBy(LogEntry::getCookie, Collectors.counting()));
  }

  /** Return the frequency of most active cookie */
  private OptionalLong mostActiveCookieFreq(Map<String, Long> groupedCookies) {
    return groupedCookies.values().stream().mapToLong(count -> count).max();
  }

  /** Output the most active cookies to the terminal */
  private void outputMostActiveCookies(Map<String, Long> groupOfCookieByDate, long mostActiveCookieFreq) {
    groupOfCookieByDate.entrySet()
            .parallelStream()
            .filter(x -> x.getValue().equals(mostActiveCookieFreq))
            .map(Map.Entry::getKey)
            .forEach(System.out::println);
  }
}
