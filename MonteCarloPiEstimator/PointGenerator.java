//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Four
//
//  File Name:     PointGenerator.java
//
//  Course:        COSC 4302 - Operating Systems
//
//  Due Date:      11/17/2024
//
//  Java Version:  23.0.1
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       Chapter 6 & 7
//
//  Description:   This class is the PointGenerator class. It generates
//                 random points and determines if they are inside the
//                 circle or not.
//
//********************************************************************
import java.util.Random;

public class PointGenerator 
{
    private Random random;
    //***************************************************************
    //
    //  Method:       PointGenerator
    //
    //  Description:  Constructor for the PointGenerator class
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public PointGenerator() 
    {
        this.random = new Random();
    }
    //***************************************************************
    //
    //  Method:       isPointInCircle
    //
    //  Description:  Determines if a point is inside the circle
    //
    //  Parameters:   None
    //
    //  Returns:      boolean
    //
    //**************************************************************
    public boolean isPointInCircle() 
    {
        double x = random.nextDouble();
        double y = random.nextDouble();
        return (x * x + y * y) < 1.0;
    }
}