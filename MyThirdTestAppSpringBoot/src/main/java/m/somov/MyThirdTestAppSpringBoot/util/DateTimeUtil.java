package m.somov.MyThirdTestAppSpringBoot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeUtil {
  public static SimpleDateFormat getCustomFormat() {
    return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
  }

  public static long getDateDifference(String date1Str, String date2Str) throws ParseException {
    var format = getCustomFormat();
    var date1 = format.parse(date1Str);
    var date2 = format.parse(date2Str);

    return Math.abs(date2.getTime() - date1.getTime());
  }
}
