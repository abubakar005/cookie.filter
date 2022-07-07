package most.active.cookie.filter.parser;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import most.active.cookie.filter.dto.CommandLineInput;
import most.active.cookie.filter.exception.LogParsingException;
import most.active.cookie.filter.util.Constants;
import org.apache.commons.cli.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;


@Slf4j
public class LogParser {

  /** Parsing command line inputs */
  public static CommandLineInput parseCommandInput(String[] args) throws LogParsingException {

    CommandLineInput commandLineInput = new CommandLineInput();
    CommandLineParser commandLineParser = new DefaultParser();
    Options options = parseCommandOption();

    try {
      CommandLine commandLine = commandLineParser.parse(options, args);
      commandLineInput.setFileName(commandLine.getOptionValue(Constants.COMMAND_OPTION_FILE));
      commandLineInput.setSelectedDate(LocalDate.parse(commandLine.getOptionValue(Constants.COMMAND_OPTION_DATE)));
      return commandLineInput;
    } catch (ParseException e) {
      log.error(e.getMessage());
      printCommandHelp(options);
      throw new LogParsingException(e);
    }
  }

  /** Parsing CSV log file content and returning list of cookies with date */
  public static List<LogEntry> parseLog(String fileName) throws LogParsingException {
    try {
      return new CsvToBeanBuilder<LogEntry>(Files.newBufferedReader(Paths.get(fileName)))
              .withType(LogEntry.class)
              .withFilter(line -> !line[0].contains(Constants.FILE_HEADER_COOKIE))  // this line will skip the header row
              .build()
              .parse();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new LogParsingException(e);
    }
  }

  /** Expected command line options (file name and selected date) */
  private static Options parseCommandOption() {

    Options argsOptions = new Options();

    // File name of cookie logs
    Option fileName = new Option("f", "file", true, "The path of cookie log file");
    fileName.setRequired(true);
    argsOptions.addOption(fileName);

    // Selected date to get the most active cookie
    Option selectedDate = new Option("d", "date", true, "The specific date to get most active cookie");
    selectedDate.setRequired(true);
    argsOptions.addOption(selectedDate);

    return argsOptions;
  }

  /** Help message for command line options for this process */
  private static void printCommandHelp(Options options) {
    HelpFormatter helpFormatter = new HelpFormatter();
    helpFormatter.printHelp("Cookie Log Filter", options);
  }
}
