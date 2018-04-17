package app.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.stereotype.Component;

@Component
public class DateConverter {
  public LocalDateTime getLocalDateTime(long date) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault());
  }

  public LocalDate getLocalDate(long date) {
    return Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public long getMilis(LocalDateTime dateTime) {
    ZonedDateTime zdt = dateTime.atZone(ZoneId.systemDefault());
    return zdt.toInstant().toEpochMilli();
  }

  public long getMilis(LocalDate date) {
    return date.toEpochDay();
  }
}
