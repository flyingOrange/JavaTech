package common.apache.commons.timeUtils;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

/*
org.apache.commons.lang.time。这个包里面包含了如下5个类：
DateFormatUtils – 提供格式化日期和时间的功能及相关常量；
DateUtils – 在Calendar和Date的基础上提供更方便的访问；
DurationFormatUtils – 提供格式化时间跨度的功能及相关常量；
FastDateFormat – 为java.text.SimpleDateFormat提供一个的线程安全的替代类；
StopWatch – 是一个方便的计时器。
 * */
public class DateUtil {

    @Test
    public void dateUtil() {
        System.out.println(StringUtils.center(" demoDateUtils ", 30, "="));
        Date date = new Date();

        String isoDateTime = DateFormatUtils.ISO_DATETIME_FORMAT.format(date);
        String isoTime = DateFormatUtils.ISO_TIME_NO_T_FORMAT.format(date);
        FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM");
        String customDateTime = fdf.format(date);
        System.out.println("ISO_DATETIME_FORMAT: " + isoDateTime);
        System.out.println("ISO_TIME_NO_T_FORMAT: " + isoTime);
        System.out.println("Custom FastDateFormat: " + customDateTime);
        System.out.println("Default format: " + date);
        System.out.println("Round HOUR: " + DateUtils.round(date, Calendar.HOUR));
        System.out.println("Truncate HOUR: " + DateUtils.truncate(date, Calendar.HOUR));

    }

    @Test
    public void stopWatch() {
        System.out.println(StringUtils.center(" demoStopWatch ", 30, "="));
        StopWatch sw = new StopWatch();
        sw.start();
        try {
            Thread.sleep(999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        sw.stop();
        System.out.println("operationA used " + sw.getTime() + " milliseconds.");

    }

}
