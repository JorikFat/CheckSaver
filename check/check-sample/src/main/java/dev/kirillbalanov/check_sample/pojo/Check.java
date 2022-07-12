package dev.kirillbalanov.check_sample.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "checks")
public class Check implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "total")
    private String total;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time")
    private String time;

    public Check(long id, String total, String date, String time) {
        this.id = id;
        this.total = total;
        this.date = date;
        this.time = time;
    }

    protected Check(Parcel in) {
        id = in.readLong();
        total = in.readString();
        date = in.readString();
        time = in.readString();
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

    public boolean isValid(){
        return getTotal() != null && getDate() != null && getTime() != null;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Check check = (Check) o;

        if (id != check.id) return false;
        if (!total.equals(check.total)) return false;
        if (!date.equals(check.date)) return false;
        return time.equals(check.time);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + total.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }

    public String getAllValues(){
        return "Summary: " + getTotal() + " Date: " + getDate() + " Time: " + getTime();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(total);
        parcel.writeString(date);
        parcel.writeString(time);
    }
}