package com.checkr.climate.entities;

public class IntensitySub {
    private DisasterIntensity fillKey;
    private int count;

    public IntensitySub(DisasterIntensity fillkey, int count) {
        this.fillKey = fillkey;
        this.count = count;
    }

    public DisasterIntensity getFillKey() {
        return fillKey;
    }

    public void setFillKey(DisasterIntensity fillkey) {
        this.fillKey = fillkey;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "IntensitySub{" +
                "fillkey=" + fillKey +
                ", count=" + count +
                '}';
    }
}
