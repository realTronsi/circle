package com.example;

import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {   
        double mode = 1; // 0 = show true values, 1 = show differences
        double[] labels = { 82, 130 };

        double[][] data = {
            {
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
            },
            {
                531, 449,
                555, 426,
                531, 426,
                555, 449,
                589, 424,
                622, 405,
                589, 405,
                622, 424,
                666, 411,
                702, 399,
                666, 399,
                702, 411,
                744, 403,
                777, 421,
                744, 421,
                777, 403,
                809, 420,
                831, 442,
                809, 442,
                831, 420
            }
        };
        Vision vision = new Vision(45);

        System.out.println("\n\n=======START========");

        for(int i = 0; i < data.length; i++) {
            Test newTest = new Test(data[i]);
            double[] ret = vision.runTestCase(newTest);
            if(mode == 0) {
                System.out.println("EXPECTED DISTANCE TO TARGET IN INCHES: " + labels[i]);
                System.out.println("FLOOR DISTANCE TO TARGET IN INCHES: " + Math.sqrt(
                ret[0]*ret[0]+ret[1]*ret[1]
                ));
                System.out.println("(EXPECTED 24) RADIUS OF TARGET IN INCHES: " + ret[2]);
                System.out.println("-------------------");
            } else {
                System.out.println("(+/-) DISTANCE TO TARGET INCHES: " + (Math.sqrt(
                    ret[0]*ret[0]+ret[1]*ret[1]
                    ) - labels[i]));
                System.out.println("(+/-) RADIUS IN INCHES: " + (ret[2] - 24));
                System.out.println("-------------------");
            }
        }

        System.out.println("=========END========");
    }
}
