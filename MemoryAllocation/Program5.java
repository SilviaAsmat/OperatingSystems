//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Six
//
//  File Name:     Program5.java
//
//  Course:        COSC 4302 - Operating Systems
//
//  Due Date:      11/27/2024
//
//  Java Version:  23.0.1
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       Chapter 9
//
//  Description:   This class will serve as the main class of the program.
//                 It will call the developerInfo method and the startProgram method.
//
//********************************************************************
import java.util.Scanner;

public class Program5 
{
    private final Scanner scanner;
    private MemoryAllocator allocator;
    //***************************************************************
    //
    //  Method:       Program5
    //
    //  Description:  The constructor of the program
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    Program5() 
    {
        scanner = new Scanner(System.in);
    }
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
        Program5 program = new Program5();
        program.developerInfo();
        program.runProgram();
    }
    //***************************************************************
    //
    //  Method:       runProgram
    //
    //  Description:  Starts the program and runs the main loop
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void runProgram()
    {
        System.out.print("Enter the initial memory size in MB: ");
        long initialMemoryBytes = getIntFromUser() * 1048576;
        allocator = new MemoryAllocator(initialMemoryBytes, "Unused");
        String input = "";
        while (!input.equalsIgnoreCase("X")) 
        {
            System.out.print("==================================================\nallocator> ");
            if(scanner.hasNextLine())
            {
                input = scanner.nextLine();
                String[] columns = input.trim().split("[\\s,]+");
                processMemoryOption(columns);
            }
            else
            {
                System.out.println("Invalid input.");
            }
            
        }// end while
    }// end runProgram
    //***************************************************************
    //
    //  Method:       processMemoryOption
    //
    //  Description:  Processes the memory option selected by the user
    //                and calls the appropriate method
    //
    //  Parameters:   String array
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void processMemoryOption(String[] userInput)
    {
        switch (userInput[0].toUpperCase()) 
        {
            // Request memory
            case "RQ" -> 
            {
                if (userInput.length < 4) {
                    System.out.println("Error: Invalid Input");
                    return;
                }
                try {
                    String processId = userInput[1];
                    int size = Integer.parseInt(userInput[2]);
                    char strategy = userInput[3].charAt(0);
                    allocator.requestMemory(processId, size, strategy);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid Input");
                }
            }
            // Release memory
            case "RL" -> 
            {
                if (userInput.length < 2) {
                    System.out.println("Error: Invalid Input");
                    return;
                }
                String processId = userInput[1];
                allocator.releaseMemory(processId);
            }
            // Compact memory
            case "C" -> allocator.compactMemory();
            // Report status
            case "STAT" -> allocator.reportStatus();
            // Exit
            case "X" -> 
            {
                return;
            }
            default -> System.out.println("Invalid command.");
        }
    }
    //***************************************************************
    //
    //  Method:       getIntFromUser
    //
    //  Description:  Gets an integer from the user
    //
    //  Parameters:   None
    //
    //  Returns:      int
    //
    //**************************************************************
    private int getIntFromUser()
    {
        int number = 0;
        while(number <= 0)
        {
            if (!scanner.hasNextInt())
            {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
            else
            {
                number = scanner.nextInt();
                scanner.nextLine();
            }
        }
        return number;
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
       System.out.println("Program:  Six");
	   System.out.println("Due Date: 11/27/2024\n");

    } // End of the developerInfo method
}