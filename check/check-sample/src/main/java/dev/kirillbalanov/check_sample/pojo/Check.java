package dev.kirillbalanov.check_sample.pojo;

import static dev.jorik.checksaver.core.Utils.dateFormat;
import static dev.jorik.checksaver.core.Utils.timeFormat;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.Calendar;

@Entity(tableName = "checks")
public class Check {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "total")
    private Float total;
    @ColumnInfo(name = "data")
    private Long dataCalendarInLong;
    @Ignore
    private Calendar calendar;

    public Calendar getCalendar() {
        return longToCalendar(getDataCalendarInLong());
    }
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
        dataCalendarInLong = calendar.getTimeInMillis();
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

    public long getDataCalendarInLong() {
        return calendar.getTimeInMillis();
    }
    public void setDataCalendarInLong(long dataCalendarInLong) {
        this.dataCalendarInLong = dataCalendarInLong;
    }

    public Check(long id, Float total, Long dataCalendarInLong) {
        this.id = id;
        this.total = total;
        this.calendar = longToCalendar(dataCalendarInLong);
        this.dataCalendarInLong = dataCalendarInLong;
    }

    public boolean isValid() {
        return !getTotal().toString().isEmpty() && !dateFormat.format(longToCalendar(dataCalendarInLong).getTime()).isEmpty()
                && !timeFormat.format(longToCalendar(dataCalendarInLong).getTime()).isEmpty();
    }

    @NonNull
    @Override
    public String toString() {
        return "Summary: " + getTotal() + " Date: " + dateFormat.format(longToCalendar(dataCalendarInLong).getTime())
                + " Time: " + timeFormat.format(longToCalendar(dataCalendarInLong).getTime());
    }

    public Calendar longToCalendar(Long time) {
        Calendar value = null;
        if (time != null) {
            value = Calendar.getInstance();
            value.setTimeInMillis(time);
        }
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Check check = (Check) o;

        if (id != check.id) return false;
        if (total != null ? !total.equals(check.total) : check.total != null) return false;
        if (dataCalendarInLong != null ? !dataCalendarInLong.equals(check.dataCalendarInLong) : check.dataCalendarInLong != null)
            return false;
        return calendar != null ? calendar.equals(check.calendar) : check.calendar == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (dataCalendarInLong != null ? dataCalendarInLong.hashCode() : 0);
        result = 31 * result + (calendar != null ? calendar.hashCode() : 0);
        return result;
    }
}