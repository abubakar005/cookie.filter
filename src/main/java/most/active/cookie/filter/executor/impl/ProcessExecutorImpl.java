package most.active.cookie.filter.executor.impl;

import lombok.extern.slf4j.Slf4j;
import most.active.cookie.filter.executor.ProcessExecutor;
import most.active.cookie.filter.filter.CookieFilter;
import most.active.cookie.filter.dto.CommandLineInput;
import most.active.cookie.filter.parser.LogParser;
import most.active.cookie.filter.util.ProcessStatus;

@Slf4j
public class ProcessExecutorImpl implements ProcessExecutor {

  private CookieFilter cookieFilter;

  public ProcessExecutorImpl(CookieFilter cookieFilter) {
    this.cookieFilter = cookieFilter;
  }

  @Override
  public int executeProcess(String[] args) {

    CommandLineInput commandLineInput;

    try {

      commandLineInput = LogParser.parseCommandInput(args);
      cookieFilter.filterMostActiveCookies(commandLineInput);
      return ProcessStatus.SUCCESS.getValue();

    } catch (Exception e) {
      log.error("Process failed!", e);
    }

    return ProcessStatus.PROCESS_FAILED.getValue();
  }
}
