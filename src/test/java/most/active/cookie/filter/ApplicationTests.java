package most.active.cookie.filter;

import most.active.cookie.filter.dto.CommandLineInput;
import most.active.cookie.filter.exception.LogParsingException;
import most.active.cookie.filter.filter.impl.CookieFilterImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.time.LocalDate.parse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@ExtendWith(OutputCaptureExtension.class)
class ApplicationTests {

	private final String VALID_FILE_PATH = "src/main/resources/cookie_log.csv";
	private final String INVALID_FILE_PATH = "src/main/resources/cookie_log_not_exist.csv";

	private CookieFilterImpl cookieFilter;

	@BeforeEach
	public void setUp() {
		cookieFilter = new CookieFilterImpl();
	}

	@Test
	@DisplayName("When file path and date are valid, output the most active cookie")
	public void filterMostActiveCookies_ValidFilePathAndDate(CapturedOutput output) throws LogParsingException {
		CommandLineInput commandLineInput = new CommandLineInput(VALID_FILE_PATH, parse("2018-12-09"));
		cookieFilter.filterMostActiveCookies(commandLineInput);
		assertThat(output).contains("AtY0laUfhglK3lC7");
	}

	@Test
	@DisplayName("When file path and date are valid and has more than one most active cookies")
	public void filterMostActiveCookies_HasMoreThanOneMostActiveCookies(CapturedOutput output) throws LogParsingException {
		CommandLineInput commandLineInput = new CommandLineInput(VALID_FILE_PATH, parse("2018-12-06"));
		cookieFilter.filterMostActiveCookies(commandLineInput);
		assertThat(output).contains("8xYHIASHaBa79xzf");
		assertThat(output).contains("1dSLJdsaDJLDsdSd");
	}

	@Test
	@DisplayName("When file path is invalid, throw exception")
	public void filterMostActiveCookies_InvalidFilePath_ThrowException() {
		CommandLineInput commandLineInput = new CommandLineInput(INVALID_FILE_PATH, parse("2018-12-09"));
		assertThatThrownBy(() -> cookieFilter.filterMostActiveCookies(commandLineInput))
				.isInstanceOf(LogParsingException.class);
	}

	@Test
	@DisplayName("When date is not exist, output nothing")
	public void filterMostActiveCookies_InvalidDate_Print_Nothing(CapturedOutput output) throws LogParsingException {
		CommandLineInput commandLineInput = new CommandLineInput(VALID_FILE_PATH, parse("2021-12-09"));
		cookieFilter.filterMostActiveCookies(commandLineInput);
		assertThat(output.toString().isEmpty());
	}
}
