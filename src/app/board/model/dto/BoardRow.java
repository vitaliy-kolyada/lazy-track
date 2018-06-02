package app.board.model.dto;

import java.util.HashMap;

public class BoardRow {
  private HashMap<String, String> fields = new HashMap<>();

  public String getValue(String fieldName) {
    return fields.get(fieldName);
  }

  public void setValue(String fieldName, String fieldValue) {
    fields.put(fieldName, fieldValue);
  }
}
