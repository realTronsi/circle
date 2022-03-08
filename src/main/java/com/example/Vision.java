package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vision {
    private double cameraWidth = 1280.0 / 2.0;
    private double cameraHeight = 720.0 / 2.0;
    // private double diagonalFovRadians = 68.5 * Math.PI / 180.0;
    // private double aspectRatio = 16.0/9.0;

    private double horizontalFov = Math.toRadians(62.8);
    private double verticalFov = Math.toRadians(37.9);

    // correct
    private double vpw = 2.0 * Math.tan(horizontalFov / 2.0);
    private double vph = 2.0 * Math.tan(verticalFov / 2.0);

    private double cpitch;

    Vision(double cpitch) {
        this.cpitch = cpitch;
    }

    public double[] runTestCase(Test test) {
        List<TargetCorner> corners = test.getParsedCorners();

        List<GlobalPoint> globalPoints = new ArrayList<>();
        for (TargetCorner corner : corners) {

            //corner.x = 1280;
            //corner.y = 360;
            // + 0.5 for 1 unit pixel plane
            //double nx = (1.0 / cameraWidth) * (corner.x - cameraWidth + 0.5);
            //double ny = (1.0 / cameraHeight) * (cameraHeight - corner.y - 0.5);

            // this normalization works
            double nx = (1/(640.0)) * (corner.x - 640);
            double ny = (1/(360.0)) * (360 - corner.y);

            //System.out.println("X: " + nx + " Y: " + ny);

            // coordinates on imaginary view plane

            //double linearlyInterpolatedAngle = 68.5 / (Math.sqrt(1280 * 1280 + 720 * 720));
            //double ang = linearlyInterpolatedAngle * (Math.sqrt(Math.pow((corner.x - 640), 2) + Math.pow((corner.y - 360), 2)));
            //System.out.println(ang);
            
            // correct
            double x = vpw / 2.0 * nx;
            double y = vph / 2.0 * ny;

            //double yaw = Math.atan2(1.0, x);
            //double pitch = Math.atan2(1.0, y);
            //double pitch = -Math.atan(x - (vpw/2.0)) + horizontalFov/2.0;
            //double yaw = -Math.atan(y - (vph/2.0)) + verticalFov/2.0;


            // double vpw2 = vpw/2.0;
            // double vph2 = vph/2.0;
            // double oppositex = x >= 0 ? vpw2 - x : -(vpw2) - x;
            // double oppositey = y >= 0 ? vph2 - y : -(vph2) - y;

            double yaw = Math.atan(x);
            double pitch = Math.atan(y);

            // System.out.println("PITCH: " + Math.toDegrees(pitch));
            // System.out.println("YAW: " + Math.toDegrees(yaw));
            globalPoints.add(new GlobalPoint(yaw, pitch + Math.toRadians(cpitch)));
        }
        for(GlobalPoint point : globalPoints) {
            // System.out.println("X: " + point.x + " Y: " + point.y);
        }
        return CircleFitter.calculateCircle(globalPoints);
    }
}
