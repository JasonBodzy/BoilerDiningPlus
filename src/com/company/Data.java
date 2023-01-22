package com.company;

public class Data {
    int swipes;
    int crowded;
    String timeStamp;
    String location;
    int wait;
    boolean isOpen;
    Data(int swipes, int crowded, String timeStamp, String location, boolean isOpen) {
        this.swipes = swipes;
        this.crowded = crowded;
        this.timeStamp = timeStamp;
        this.location = location;
        this.isOpen = isOpen;
        this.wait = calculateWait(this.swipes, this.crowded);
    }

    private int calculateWait(int swipes, int crowded) {
        if (swipes == 0) {
            return 0;
        }
        return (crowded / swipes);
    }

    @Override
    public String toString() {
        return "Data{" +
                "swipes=" + swipes +
                ", crowded=" + crowded +
                ", timeStamp='" + timeStamp + '\'' +
                ", location='" + location + '\'' +
                ", wait=" + wait +
                ", isOpen=" + isOpen +
                '}';
    }
}
