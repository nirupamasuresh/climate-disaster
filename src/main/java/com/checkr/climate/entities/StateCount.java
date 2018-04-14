package com.checkr.climate.entities;

public class StateCount {
    private String state;
    private Long count;

    public StateCount(String state, Long count) {
        this.state = state;
        this.count = count;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "StateCount{" +
                "state='" + state + '\'' +
                ", count=" + count +
                '}';
    }
}
