package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vision {
    private double cameraWidthInches = 1280;
    private double cameraHeightInches = 720;
    private double diagonalFovRadians = 68.5 * Math.PI / 180;

    private double horizontalFov = Math.atan(Math.tan(diagonalFovRadians / 2) * (16/9)) * 2;
    private double verticalFov = Math.atan(Math.tan(horizontalFov / 2) * (16/9)) * 2;

    private double vph = 2 * Math.tan(horizontalFov / 2);
    private double vpw = 2 * Math.tan(verticalFov / 2);

    public double[] runTestCase(Test test) {
        List<TargetCorner> corners = test.getParsedCorners();

        List<GlobalPoint> globalPoints = new ArrayList<>();
        for (TargetCorner corner : corners) {
            // + 0.5 for 1 unit pixel plane
            double nx = (1.0 / 1280.0) * (corner.x - 1280.0 + 0.5);
            double ny = (1.0 / 720.0) * (720.0 - corner.y + 0.5);
            // coordinates on imaginary view plane
            double x = vpw / 2 * nx;
            double y = vph / 2 * ny;

            double pitch = Math.atan2(1, x);
            double yaw = Math.atan2(1, y);
            globalPoints.add(new GlobalPoint(yaw, pitch + Math.toRadians(60)));
        }
        return CircleFitter.calculateCircle(globalPoints);
    }
}
