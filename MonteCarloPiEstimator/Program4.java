//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Four
//
//  File Name:     Program4.java
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
//  Description:   This class is the main class of the program. It
//                 prompts the user to enter the number of points to
//                 generate and then estimates the value of π using
//                 the Monte Carlo method. The program continues to
//                 prompt the user for input until the user enters 0.
//
//********************************************************************
import java.util.Scanner;

public class Program4 
{
    //***************************************************************
    //
    //  Method:       main
    //
    //  Description:  The main method of the program
    //
    //  Parameters:   String[] args
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public static void main(String[] args) 
    {
        Program4 program = new Program4();
        program.developerInfo();
        program.startProgram();
    }
    //***************************************************************
    //
    //  Method:       startProgram
    //
    //  Description:  The starting point of the program, calls the
    //                getUserInput method to get the number of points
    //                to generate and then creates an instance of the
    //                EstimatePI class to estimate the value of π.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    private void startProgram() 
    {
        int numThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of threads being used: " + numThreads);
        int numPoints;
        Scanner scanner = new Scanner(System.in);
        numPoints = getUserInput(scanner);
        while (numPoints != 0) 
        {
            EstimatePI estimator = new EstimatePI(numPoints, numThreads);
            double piEstimate = estimator.estimatePi();
            System.out.println("Estimated value of π: " + piEstimate);
            numPoints = getUserInput(scanner);
        }
        scanner.close();
    }
    //***************************************************************
    //
    //  Method:       getUserInput
    //
    //  Description:  Prompts the user to enter the number of points
    //                to generate and validates the input. The method
    //                will continue to prompt the user until a valid
    //                input is entered.
    //
    //  Parameters:   Scanner scanner
    //
    //  Returns:      int
    //
    //**************************************************************

    private int getUserInput(Scanner scanner) 
    {
        int numPoints = -1;
        while (numPoints <= 0 && numPoints != 0) 
        {
            System.out.print("Enter the number of points to generate (0 to exit): ");
            if (!scanner.hasNextInt()) 
            {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            } 
            else if((numPoints = scanner.nextInt()) < 0) 
            {
                System.out.println("Invalid input. Please enter a positive integer.");
            } else if (numPoints == 0) 
            {
                System.out.println("Exiting program.");
            }
        }
        return numPoints;
    }
    //***************************************************************
    //
    //  Method:       developerInfo
    // 
    //  Description:  The developer information method of the program
	//                This method and comments must be included in all
	//                programming assignments.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    public void developerInfo()
    {
       System.out.println("Name:   Silvia Asmat");
       System.out.println("Course:   COSC 4302 - Operating Systems");
       System.out.println("Program:  Four");
	   System.out.println("Due Date: 11/17/2024\n");

    } // End of the developerInfo method
}