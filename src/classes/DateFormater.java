package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class DateFormater {
  public static String getDataEmFormatoTexto(LocalDateTime data) {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    return data.format(dateFormatter);
  }

  public static String getHorarioEmFormatoTexto(LocalDateTime data) {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm");
    return data.format(dateFormatter);
  }
}
