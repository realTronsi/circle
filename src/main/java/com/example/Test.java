package com.example;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private List<TargetCorner> testPoints = new ArrayList<>();
    
    Test(double[] coords) {
        for(int i = 0; i < coords.length; i+=2) {
            testPoints.add(new TargetCorner(coords[i], coords[i+1]));
        }
    }

    public List<TargetCorner> getParsedCorners() {
        List<TargetCorner> ret = new ArrayList<>();

        // assumes target contours are rectangles
        for (int i = 0; i < testPoints.size(); i+=4) {
            TargetCorner p1 = testPoints.get(i);
            TargetCorner p2 = testPoints.get(i+1);
            TargetCorner p3 = testPoints.get(i+2);
            TargetCorner p4 = testPoints.get(i+3);

            // following canvas axises (down is larger)
            double midY = (p1.y + p2.y + p3.y + p4.y) / 4.0;

            if (p1.y < midY) ret.add(p1);
            if (p2.y < midY) ret.add(p2);
            if (p3.y < midY) ret.add(p3);
            if (p4.y < midY) ret.add(p4);
        }

        return ret;
    }
}
