package gov.iti.jets.entities;

public class StatisticEntity {
    String data;
    int count;

    public StatisticEntity(String data, int count) {
        this.data = data;
        this.count = count;
    }
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "StatisticEntity{" +
                "data='" + data + '\'' +
                ", count=" + count +
                '}';
    }

}
