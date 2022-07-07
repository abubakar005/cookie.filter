package most.active.cookie.filter.dto;

import java.time.LocalDate;

public class CommandLineInput {

  private String fileName;
  private LocalDate selectedDate;

  public CommandLineInput() {
  }

  public CommandLineInput(String fileName, LocalDate selectedDate) {
    this.fileName = fileName;
    this.selectedDate = selectedDate;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public LocalDate getSelectedDate() {
    return selectedDate;
  }

  public void setSelectedDate(LocalDate selectedDate) {
    this.selectedDate = selectedDate;
  }
}
