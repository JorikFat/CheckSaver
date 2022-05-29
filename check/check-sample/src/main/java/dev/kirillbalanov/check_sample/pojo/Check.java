package dev.kirillbalanov.check_sample.pojo;

public class Check {

    private long id;
    private final String total;
    private final String date;
    private final String time;

    public Check(long id, String total, String date, String time) {
        this.id = id;
        this.total = total;
        this.date = date;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public String getTotal() {
        return total;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
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
}