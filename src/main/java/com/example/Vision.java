package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vision {
    private double cameraWidthInches = 1280.0;
    private double cameraHeightInches = 720.0;
    private double diagonalFovRadians = 68.5 * Math.PI / 180.0;
    private double aspectRatio = 16.0/9.0;

    private double horizontalFov = Math.atan(Math.tan(diagonalFovRadians / 2.0) * (aspectRatio)) * 2.0;
    private double verticalFov = Math.atan(Math.tan(horizontalFov / 2.0) * (aspectRatio)) * 2.0;

    private double vph = 2.0 * Math.tan(horizontalFov / 2.0);
    private double vpw = 2.0 * Math.tan(verticalFov / 2.0);

    public double[] runTestCase(Test test) {
        System.out.println(horizontalFov);
        System.out.println(verticalFov);

        List<TargetCorner> corners = test.getParsedCorners();

        List<GlobalPoint> globalPoints = new ArrayList<>();
        for (TargetCorner corner : corners) {
            // + 0.5 for 1 unit pixel plane
            double nx = (1.0 / cameraWidthInches) * (corner.x - cameraWidthInches + 0.5);
            double ny = (1.0 / cameraHeightInches) * (cameraHeightInches - corner.y + 0.5);
            // coordinates on imaginary view plane
            double x = vpw / 2.0 * nx;
            double y = vph / 2.0 * ny;

            double pitch = Math.atan2(1.0, x);
            double yaw = Math.atan2(1.0, y);
            globalPoints.add(new GlobalPoint(yaw, pitch + Math.toRadians(60)));
        }
        return CircleFitter.calculateCircle(globalPoints);
    }
}
