//********************************************************************
//
//  Author:        Textbook Authors
//
//  Program #:     Three
//
//  File Name:     CPU.java
//
//  Course:        COSC 4302 Operating Systems
//
//  Instructor:    Prof. Fred Kumi
//
//  Chapter:       5
//
//  Description:   "Virtual" CPU
//
//******************************************************************** 
 
public class CPU
{
    /**
     * Run the specified task for the specified slice of time.
     */
    public static void run(Task task, int slice)
	{
        System.out.println("Will run " + task);
    }
}
