//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Four
//
//  File Name:     Worker.java
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
//  Description:   This class is the Worker class that implements the
//                 Runnable interface. It generates points and checks
//                 if they are in the circle. If they are, it increments
//                 the pointsInCircle array.
//
//********************************************************************
import java.util.concurrent.locks.ReentrantLock;

public class Worker implements Runnable 
{
    private int numPoints;
    private int[] pointsInCircle;
    private ReentrantLock lock;
    //***************************************************************
    //
    //  Method:       Worker
    //
    //  Description:  Constructor for the Worker class
    //
    //  Parameters:   int numPoints, int[] pointsInCircle, ReentrantLock lock
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public Worker(int numPoints, int[] pointsInCircle, ReentrantLock lock) 
    {
        this.numPoints = numPoints;
        this.pointsInCircle = pointsInCircle;
        this.lock = lock;
    }
    //***************************************************************
    //
    //  Method:       run
    //
    //  Description:  The run method of the Worker class
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    @Override
    public void run() 
    {
        PointGenerator generator = new PointGenerator();
        for (int i = 0; i < numPoints; i++) 
        {
            if (generator.isPointInCircle()) 
            {
                lock.lock();
                try{
                    pointsInCircle[0]++;
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}