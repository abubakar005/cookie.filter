package most.active.cookie.filter.config;

import most.active.cookie.filter.executor.ProcessExecutor;
import most.active.cookie.filter.executor.impl.ProcessExecutorImpl;
import most.active.cookie.filter.command.runner.ProcessCommandLineRunner;
import most.active.cookie.filter.filter.CookieFilter;
import most.active.cookie.filter.filter.impl.CookieFilterImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessConfig {

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext applicationContext, ProcessExecutor processExecutor) {
    return new ProcessCommandLineRunner(applicationContext, processExecutor);
  }

  @Bean
  public CookieFilter cookieFilter() {
    return new CookieFilterImpl();
  }

  @Bean
  public ProcessExecutor processExecutor(CookieFilter cookieFilter) {
    return new ProcessExecutorImpl(cookieFilter);
  }
}
