package most.active.cookie.filter.util;

public enum ProcessStatus {

  SUCCESS(0),
  PROCESS_FAILED(1);

  private final int value;

  ProcessStatus(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
