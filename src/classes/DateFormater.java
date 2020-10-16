package classes;

import java.util.Calendar;

abstract class DateFormater {
  public static String getDataEmFormatoTexto(Calendar calendario) {
    return String.valueOf(calendario.get(Calendar.DAY_OF_MONTH))
        + '/'
        + (calendario.get(Calendar.MONTH) + 1)
        + '/'
        + calendario.get(Calendar.YEAR) % 100;
  }

  public static String getHorarioEmFormatoTexto(Calendar calendario) {
    return String.valueOf(calendario.get(Calendar.HOUR_OF_DAY))
        + ':'
        + (calendario.get(Calendar.MINUTE));
  }
}
