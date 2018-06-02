package app.board.model.enumeration;

import java.util.ArrayList;
import java.util.stream.Stream;
import lombok.Getter;

public enum Severity {
  SHOW_STOPPER("Show stopper"),
  CRITICAL("Critical"),
  MAJOR("Major"),
  NORMAL("Normal"),
  MINOR("Minor");

  @Getter
  private String name;

  Severity(String name) {
    this.name = name;
  }

  public static ArrayList<String> names() {
    ArrayList<String> names = new ArrayList<>();
    Stream.of(Severity.values()).forEach(severity -> names.add(severity.getName()));
    return names;
  }

  public static Severity of(String name) {
    switch (name) {
      case "Show stopper":
        return Severity.SHOW_STOPPER;
      case "Critical":
        return Severity.CRITICAL;
      case "Major":
        return Severity.MAJOR;
      case "Normal":
        return Severity.NORMAL;
      case "Minor":
        return Severity.MINOR;
      default:
        throw new IllegalArgumentException("Not valid severity name.");
    }
  }
}
