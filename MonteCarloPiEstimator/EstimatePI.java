//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Four
//
//  File Name:     EstimatePI.java
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
//  Description:   This class estimates the value of π using the Monte
//                 Carlo method. The class uses multiple threads to
//                 generate random points and count the number of points
//                 that fall within a circle. The class then calculates
//                 the value of π based on the number of points in the
//                 circle and the total number of points generated.
//
//********************************************************************
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class EstimatePI {
    private int numPoints;
    private int numThreads;
    private int[] pointsInCircle;
    private ReentrantLock lock;
    //********************************************************************
    //
    //  Method:        EstimatePI
    //
    //  Description:   Constructor for the EstimatePI class. Initializes
    //                 the number of points to generate and the number of
    //                 threads to use.
    //
    //  Parameters:    int, int
    //
    //  Returns:      N/A
    //
    //********************************************************************
    public EstimatePI(int numPoints, int numThreads) 
    {
        this.numPoints = numPoints;
        this.numThreads = numThreads;
        this.pointsInCircle = new int[1];
        this.lock = new ReentrantLock();
    }
    //********************************************************************
    //
    //  Method:        estimatePi
    //
    //  Description:   Estimates π using the Monte Carlo method with
    //                 multiple threads. Each thread generates a portion
    //                 of the total points. The method waits for all
    //                 threads to complete and calculates π based on the
    //                 points inside the circle.
    //
    //  Parameters:    None
    //
    //  Returns:       double
    //
    //********************************************************************
    public double estimatePi() 
    {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        int pointsPerThread = numPoints / numThreads;
        for (int i = 0; i < numThreads; i++) 
        {
            executor.execute(new Worker(pointsPerThread, pointsInCircle, lock));
        }
        executor.shutdown();
        while (!executor.isTerminated()) 
        {
            // Wait for all threads to finish
        }

        return calculatePi();
    }
    //********************************************************************
    //
    //  Method:        calculatePi
    //
    //  Description:   Calculates the value of π based on the number of
    //                 points inside the circle and the total number of
    //                 points generated.
    //
    //  Parameters:    None
    //
    //  Returns:       double
    //
    //********************************************************************
    private double calculatePi() 
    {
        return 4.0 * pointsInCircle[0] / numPoints;
    }
}