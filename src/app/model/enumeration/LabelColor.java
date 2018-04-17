package app.model.enumeration;

import lombok.Getter;

@Getter
public enum LabelColor {
  ERROR("#ff0000"), OK("#0B610B");

  String code;

  LabelColor(String code) {
    this.code = code;
  }

}
