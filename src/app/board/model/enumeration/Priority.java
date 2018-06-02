package app.board.model.enumeration;

import java.util.ArrayList;
import java.util.stream.Stream;
import lombok.Getter;

public enum Priority {
  HIGH("High"), MIDDLE("Middle"), LOW("Low");

  @Getter
  private String name;

  Priority(String name) {
    this.name = name;
  }

  public static ArrayList<String> names() {
    ArrayList<String> names = new ArrayList<>();
    Stream.of(Priority.values()).forEach(priority -> names.add(priority.getName()));
    return names;
  }

  public static Priority of(String name) {
    switch (name) {
      case "High":
        return Priority.HIGH;
      case "Middle":
        return Priority.MIDDLE;
      case "Low":
        return Priority.LOW;
      case "Normal":
      default:
        throw new IllegalArgumentException("Not valid priority name.");
    }
  }
}
