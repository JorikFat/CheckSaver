package dev.kirillbalanov.check_sample.pojo;

public class Check {

    private long id;
    private String total;
    private String date;
    private String time;

    public Check(long id, String total, String date, String time) {
        this.id = id;
        this.total = total;
        this.date = date;
        this.time = time;
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
}