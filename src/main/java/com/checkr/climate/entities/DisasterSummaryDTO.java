package com.checkr.climate.entities;

/**
 * DTO for output from SQL query to convert it to the format required to show the final visualization
 */
public class DisasterSummaryDTO {
    private DisasterIntensity fillKey;
    private int count;

    public DisasterSummaryDTO(DisasterIntensity fillkey, int count) {
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
        return "DisasterSummaryDTO{" +
                "fillkey=" + fillKey +
                ", count=" + count +
                '}';
    }
}
