package most.active.cookie.filter.command.runner;

import most.active.cookie.filter.executor.ProcessExecutor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class ProcessCommandLineRunner implements CommandLineRunner {

  private ApplicationContext applicationContext;
  private ProcessExecutor processExecutor;

  public ProcessCommandLineRunner(ApplicationContext applicationContext, ProcessExecutor processExecutor) {
    this.applicationContext = applicationContext;
    this.processExecutor = processExecutor;
  }

  @Override
  public void run(String[] args) {
    terminate(() -> processExecutor.executeProcess(args));
  }

  // It will terminate the application once working done
  private void terminate(ExitCodeGenerator exitCodeGenerator) {
    System.exit(SpringApplication.exit(applicationContext, exitCodeGenerator));
  }
}
