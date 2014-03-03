package pl.gauee.daytoday;

import java.util.Calendar;
import java.util.Date;

public class PolishWeekDay {
    private final Calendar calendar = Calendar.getInstance();

    public PolishWeekDay(Date day) {
        calendar.setTime(day != null ? day : new Date());
    }

    public String getWeekDay() {
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        PolishDays dayPL = new PolishDays(day);
        return dayPL.toString();
    }

    static class PolishDays {

        private static String[] days = new String[]{
                "Niedziela",
                "Poniedziałek",
                "Wtorek",
                "Środa",
                "Czwartek",
                "Piątek",
                "Sobota"
        };

        private int dayInWeek = 0;

        protected PolishDays(int value) {
            this.dayInWeek = value;
        }

        @Override
        public String toString() {
            return days[dayInWeek - 1];
        }
    }
}

