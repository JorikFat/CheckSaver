package dev.kirillbalanov.check_sample.pojo;

import static dev.jorik.checksaver.core.Utils.dateFormat;
import static dev.jorik.checksaver.core.Utils.timeFormat;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Calendar;

@Entity(tableName = "checks")
public class Check implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "total")
    private String total;
    @ColumnInfo(name = "data")
    private Long dataCalendarInLong;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }

    public long getDataCalendarInLong() {
        return dataCalendarInLong;
    }
    public void setDataCalendarInLong(long dataCalendarInLong) {
        this.dataCalendarInLong = dataCalendarInLong;
    }

    public Check(long id, String total, long calendar) {
        this.id = id;
        this.total = total;
        dataCalendarInLong = calendar;
    }

    public boolean isValid() {
        return !getTotal().isEmpty() && !dateFormat.format(longToCalendar(dataCalendarInLong).getTime()).isEmpty()
                && !dateFormat.format(longToCalendar(dataCalendarInLong).getTime()).isEmpty();
    }

    @NonNull
    @Override
    public String toString() {
        return "Summary: " + getTotal() + " Date: " + dateFormat.format(longToCalendar(dataCalendarInLong).getTime())
                + " Time: " + timeFormat.format(longToCalendar(dataCalendarInLong).getTime());
    }

    public static Calendar longToCalendar(Long time) {
        Calendar value = null;
        if (time != null) {
            value = Calendar.getInstance();
            value.setTimeInMillis(time);
        }
        return value;
    }

    public Check(){}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(total);
        parcel.writeLong(dataCalendarInLong);
    }

    protected Check(Parcel in) {
        id = in.readLong();
        total = in.readString();
        dataCalendarInLong = in.readLong();
    }

    public static final Creator<Check> CREATOR = new Creator<Check>() {
        @Override
        public Check createFromParcel(Parcel in) {
            return new Check(in);
        }
        @Override
        public Check[] newArray(int size) {
            return new Check[size];
        }
    };
}