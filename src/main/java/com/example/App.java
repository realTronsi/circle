package com.example;

import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {   
        double mode = 1; // 0 = show true values, 1 = show differences
        double[] labels = { 82, 130, 106 };

        double[][] data = {
            {
                459, 235,
                491, 201,
                459, 201,
                491, 235,

                538, 193,
                584, 167,
                538, 167,
                584, 193,

                644, 159,
                696, 174,
                644, 174,
                696, 159,

                752, 168,
                797, 196,
                752, 196,
                797, 168,

                838, 203,
                853, 224,
                838, 224,
                853, 203
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
            },
            {
                422, 333,
                394, 358,
                394, 333,
                422, 358,

                503, 309,
                464, 330,
                464, 309,
                503, 330,

                554, 307,
                595, 321,
                554, 321,
                595, 307,

                643, 317,
                679, 339,
                643, 339,
                679, 317,

                714, 344,
                735, 368,
                714, 368,
                735, 344
            }
        };
        Vision vision = new Vision(37);

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
