package com.example;

public class GlobalPoint {
    // Everything relative to camera right now
    double z = 104.0 - 31.25; // INCHES

    double x;
    double y;

    public GlobalPoint(double yaw, double pitch) {
        double d = z / (Math.tan(pitch) * Math.cos(yaw));

        x = Math.cos(yaw) * d;
        y = Math.sin(yaw) * d;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
