package prm3101.group_assignment.ulti;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ASUS on 25/10/2017.
 */

public class AppUtils {

    public String getDayFull(){
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy");
        String todayAsString = dateFormat.format(today);
        return todayAsString;
    }

    public String getMonth(){
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM yyyy");
        String todayAsString = dateFormat.format(today);
        return todayAsString;
    }

    public String getWeek(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, cal.get(Calendar.WEEK_OF_YEAR));
        Date yourDate = cal.getTime();
        cal.setTime(yourDate);//Set specific Date of which start and end you want
        Date start,end;
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        start = cal.getTime();//Date of Monday of current week
        DateFormat dayFormat = new SimpleDateFormat("d");
        String dayStart = dayFormat.format(start);
        cal.add(Calendar.DATE, 6);//Add 6 days to get Sunday of next week
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        end = cal.getTime();//Date of Sunday of current week
        String dayEnd = dayFormat.format(end);
        Date today = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("MMM, yyyy");
        String month = dateFormat.format(today);
        return dayStart + " - " + dayEnd + " " + month;
    }

    public String getStartDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, cal.get(Calendar.WEEK_OF_YEAR));
        Date yourDate = cal.getTime();
        cal.setTime(yourDate);//Set specific Date of which start and end you want
        Date start;
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        start = cal.getTime();//Date of Monday of current week
        DateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dayStart = dayFormat.format(start);
        return dayStart;
    }

    public String getEndDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, cal.get(Calendar.WEEK_OF_YEAR));
        Date yourDate = cal.getTime();
        cal.setTime(yourDate);//Set specific Date of which start and end you want
        Date end;
        DateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DATE, 6);//Add 6 days to get Sunday of next week
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        end = cal.getTime();//Date of Sunday of current week
        String dayEnd = dayFormat.format(end);
        return dayEnd;
    }

    public String getToday(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 0);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayAsString = dateFormat.format(tomorrow);
        return todayAsString;
    }


    public String getTommorrow(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy");
        String todayAsString = dateFormat.format(tomorrow);
        return todayAsString;
    }

    public String getTommorrow_v2(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayAsString = dateFormat.format(tomorrow);
        return todayAsString;
    }

    public Calendar getCalendarForSchedule(){
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("EEEE");
        String date = dateFormat.format(today);
        switch (date){
            case "Monday":
                calendar.add(Calendar.DAY_OF_YEAR, 0);
                break;
            case "Tuesday":
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                break;
            case "Wednesday":
                calendar.add(Calendar.DAY_OF_YEAR, -2);
                break;
            case "Thursday":
                calendar.add(Calendar.DAY_OF_YEAR, -3);
                break;
            case "Friday":
                calendar.add(Calendar.DAY_OF_YEAR, -4);
                break;
            case "Saturday":
                calendar.add(Calendar.DAY_OF_YEAR, -5);
                break;
            case "Sunday":
                calendar.add(Calendar.DAY_OF_YEAR, -6);
                break;
        }
        return calendar;
    }

}
