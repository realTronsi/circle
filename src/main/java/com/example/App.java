package com.example;

import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        double[] data = {
            395, 357,
            421, 333,
            421, 357,
            395, 333,
            464, 331,
            503, 311,
            464, 311,
            503, 331,
            554, 307,
            595, 321,
            554, 321,
            595, 307,
            643, 317,
            679, 340,
            643, 340,
            679, 317,
            714, 344,
            734, 367,
            714, 367,
            734, 344
        };
        Test newtest = new Test(data);
        Vision vision = new Vision();
        double[] ret = vision.runTestCase(newtest);
        ret[0] /= 12;
        ret[1] /= 12;
        System.out.println("FLOOR DISTANCE TO TARGET IN FEET: " + Math.sqrt(
            ret[0]*ret[0]+ret[1]*ret[1]
        ));
        System.out.println("RADIUS OF TARGET IN FEET: " + ret[2]/12);
    }
}
