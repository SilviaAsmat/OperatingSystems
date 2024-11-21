//********************************************************************
//
//  Author:        Instructor
//
//  Program #:     Three
//
//  File Name:     Program3.java
//
//  Course:        COSC 4302 Operating Systems
//
//  Due Date:      10/20/2024
//
//  Instructor:    Prof. Fred Kumi
//
//  Java Version:  Java 17.0.12
//
//  Chapter:       5
//
//  Description:   Demonstrates different CPU scheduling algorithms
//                 FCFS, SJF, PRI, RR, and PRI-RR
//
//                 You are allowed to modify only the following lines:
//                 11, 15, 278 and 281.
//                 You will not receive credit for this Program if
//                 you modify any other part. 
//
//********************************************************************

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program3
{
	private static BufferedReader inFile = null;
	private static Scanner input = null;
	
	//***************************************************************
    //
    //  Method:       main
    // 
    //  Description:  The main method of the program
    //
    //  Parameters:   String array
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    public static void main(String[] args)
	{		
	    Program3 obj = new Program3();
        
		input = new Scanner(System.in);

		obj.developerInfo();
		obj.mainlineProcess();
	}
    
	//***************************************************************
    //
    //  Method:       mainlineProcess
    // 
    //  Description:  This method starts the program
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void mainlineProcess()
    {
	    String scheduleName = null;
	    String algorithmFilename = null;
	    
	    displayMessage();
		scheduleName = getUserInput("Enter the algorithm type: ");
		while (!scheduleName.equalsIgnoreCase("End"))
		{ 
		   algorithmFilename = getUserInput("Enter the schedule filename: ");
		
 	       openAlgorithmFile(algorithmFilename);
	       List<Task> queue = createPopulateQueue();
	       processAlgorithm(scheduleName, queue);

		   try {
		      if (inFile != null)
		         inFile.close();
		   }
		   catch (IOException exp)
		   {
	           System.err.println("Error - Input/Output Exception. Good Bye!");
	           System.exit(1);
		   }
		   catch (Exception exp)
		   {
	           System.err.println("Error - Unknown Exception. Good Bye!");
	           System.exit(1);
		   }
		   
		   displayMessage();
		   scheduleName = getUserInput("Enter the algorithm type or \"End\" to exit: ");
		}
		
        System.out.println("\nThanks for using the CPU scheduler - Bye!");
    }
    
	//***************************************************************
    //
    //  Method:       getUserInput
    // 
    //  Description:  This method gets the user input data
    //
    //  Parameters:   String prompt
    //
    //  Returns:      String
    //
    //**************************************************************
	public String getUserInput(String prompt)
	{
		System.out.print(prompt);
		
		String inputData = input.nextLine();

		return inputData;
	}
	
	//***************************************************************
    //
    //  Method:       openAlgorithmFile
    // 
    //  Description:  This opens the algorithm file
    //
    //  Parameters:   String input (File)
    //
    //  Returns:      N/A
    //
    //**************************************************************
	public void openAlgorithmFile(String input)
	{
        try {
			inFile = new BufferedReader(new FileReader(input));
		}
        catch (FileNotFoundException exp)
        {
            System.err.println("Error - The Schedule file was not found. Good Bye!");
            System.exit(1);
		}
    }
	
	//***************************************************************
    //
    //  Method:       openAlgorithmFile
    // 
    //  Description:  This method reads in the tasks and populates
	//                the ready queue 
    //
    //  Parameters:   None
    //
    //  Returns:      List<Task>
    //
    //**************************************************************
	public List<Task> createPopulateQueue()
	{
        // create the queue of tasks
        List<Task> queue = new ArrayList<>();
		
        String scheduleRecord;
		
        // read in the tasks and populate the ready queue   
        try {
			while ((scheduleRecord = inFile.readLine()) != null)
			{
			    String[] tokens = scheduleRecord.split(",\\s*");
			    queue.add(new Task(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
			}
		}
        catch (IOException exp)
        {
            System.err.println("Error - Input/Output Exception. Good Bye!");
            System.exit(1);
	    }
        catch (NumberFormatException exp)
        {
            System.err.println("Error - Number Format Exception. Good Bye!");
            System.exit(1);
	    }
        catch (Exception exp)
        {
            System.err.println("Error - Unknown Exception. Good Bye!");
            System.exit(1);
	    }
		
		return queue;
    }
    
	//***************************************************************
    //
    //  Method:       processAlgorithm
    // 
    //  Description:  A method to call the CPU schedule algorithms
    //
    //  Parameters:   String choice
	//                List<Task> queue
    //
    //  Returns:      N/A
    //
    //**************************************************************
	public void processAlgorithm(String choice, List<Task> queue)
	{
        Algorithm scheduler = null;

        switch(choice)
		{
            case "FCFS":
                scheduler = new FirstComeFirstServed(queue);
                break;
            case "SJF":
                scheduler = new ShortestJobFirst(queue);
                break;
            case "PRI":
                scheduler = new Priority(queue);
                break;
            case "RR":
                scheduler = new RoundRobin(queue);
                break;
            case "PRI-RR":
                scheduler = new PriorityRoundRobin(queue);
                break;
            default:
                System.err.println("Invalid algorithm. Good Bye!!");
                System.exit(0);
        }

        // start the scheduler
        scheduler.schedule();
    }
	
    //***************************************************************
    //
    //  Method:       displayMessage
    //
    //  Description:  This method displays a message to the screen
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //***************************************************************
    public void displayMessage()
    {
    	System.out.println("Scheduling Algorithms");
    	System.out.println("FCFS   - First-come, first-served scheduling");
    	System.out.println("SJF    - Shortest-job-first scheduling");
    	System.out.println("PRI    - Priority scheduling");
    	System.out.println("RR     - Round-robin scheduling");
    	System.out.println("PRI-RR - Priority with round-robin scheduling\n");
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
       System.out.println("Program:  Three");
	   System.out.println("Due Date: 10/20/2024\n");

    } // End of the developerInfo method
}
