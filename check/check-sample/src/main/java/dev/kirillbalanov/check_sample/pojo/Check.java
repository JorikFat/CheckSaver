package dev.kirillbalanov.check_sample.pojo;

import static dev.jorik.checksaver.core.Utils.dateFormat;
import static dev.jorik.checksaver.core.Utils.timeFormat;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Calendar;

@Entity(tableName = "checks")
public class Check {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "total")
    private Float total;
    @ColumnInfo(name = "data")
    @TypeConverters({CalendarLongConvert.class})
    private Calendar calendar;

    public Calendar getCalendar() {
        return calendar;
    }
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Float getTotal() {
        return total;
    }
    public void setTotal(Float total) {
        this.total = total;
    }

    public Check(long id, Float total, Calendar calendar) {
        this.id = id;
        this.total = total;
        this.calendar = calendar;
    }

    public boolean isValid() {
        return !getTotal().toString().isEmpty() && !dateFormat.format(calendar.getTime()).isEmpty()
                && !timeFormat.format(calendar.getTime()).isEmpty();
    }

    @NonNull
    @Override
    public String toString() {
        return "Summary: " + getTotal() + " Date: " + dateFormat.format(calendar.getTime())
                + " Time: " + timeFormat.format(calendar.getTime());
    }

    public static class CalendarLongConvert{
        @TypeConverter
        public static Calendar longToCalendar(Long time) {
            Calendar value = null;
            if (time!= null) {
                value = Calendar.getInstance();
                value.setTimeInMillis(time);
            }
            return value;
        }
        @TypeConverter
        public Long calendarToLong(Calendar calendar){
            if(calendar!=null){
                return calendar.getTimeInMillis();
            } else return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Check check = (Check) o;
        if (id != check.id) return false;
        if (total != null ? !total.equals(check.total) : check.total != null) return false;
        return calendar != null ? calendar.equals(check.calendar) : check.calendar == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (calendar != null ? calendar.hashCode() : 0);
        return result;
    }
}