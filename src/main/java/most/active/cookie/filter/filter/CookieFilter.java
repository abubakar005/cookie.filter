package most.active.cookie.filter.filter;

import most.active.cookie.filter.exception.LogParsingException;
import most.active.cookie.filter.dto.CommandLineInput;

public interface CookieFilter {

  void filterMostActiveCookies(CommandLineInput commandLineInput) throws LogParsingException;
}
