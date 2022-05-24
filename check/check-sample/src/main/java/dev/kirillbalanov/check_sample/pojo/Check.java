package dev.kirillbalanov.check_sample.pojo;

public class Check {
    private long id;
    private String mainText;

    public Check(long id, String mainText) {
        this.id = id;
        this.mainText = mainText;
    }

    public long getId() {
        return id;
    }

    public String getMainText() {
        return mainText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Check check = (Check) o;

        if (id != check.id) return false;
        return mainText.equals(check.mainText);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + mainText.hashCode();
        return result;
    }
}