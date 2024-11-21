//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Two
//
//  File Name:     Program2.java
//
//  Course:        COSC 4301 - Operating Systems
//
//  Due Date:      10/06/2024
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       Chapter 4
//
//  Description:   This program demonstrates the use of threads to sort an 
//                 array of integers using the Selection Sort algorithm.
//
//********************************************************************
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Program2 {
    // Global array for unsorted integers
    private int[] unsortedArray;
    // Global array for the sorted output
    private int[] sortedArray;
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
    public static void main(String[] args) {
        Program2 obj = new Program2();
        obj.developerInfo();
        obj.runProgram();
    }
    //***************************************************************
    //
    //  Method:       runProgram
    //
    //  Description:  Gets user input and calls the necessary methods
    //                to sort the array of integers.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void runProgram() {
        try (Scanner scanner = new Scanner(System.in)) 
        {
            String input;
            String checkZeroInput;
            System.out.print("Enter the number of integers to sort (One integer to generate random numbers) (0 to quit): ");
            input = scanner.nextLine();  
            checkZeroInput = input.replaceAll("^[\\s,]+", "");
            // Keep asking the user until they enter 0
            while (!checkZeroInput.equals("0"))
            {
                boolean isValid;
                isValid = parseInput(input);
                if(isValid)
                {
                    try {
                        if (unsortedArray.length == 1) 
                        {
                            generateRandomNumbers(unsortedArray[0]);
                        }
                        // Initialize the sorted array
                        sortedArray = new int[unsortedArray.length];
                        // Sort using threads
                        sortWithThreads();
                        // Output the sorted array
                        System.out.println("Sorted Array: " + Arrays.toString(sortedArray));
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter valid integers.");
                        scanner.nextLine(); // Clear the invalid input
                    }
                }
                System.out.print("Enter the number of integers to sort (One integer to generate random numbers) (0 to quit): ");
                input = scanner.nextLine();
                checkZeroInput = input.replaceAll("^[\\s,]+", "");
            }
            System.out.println("Exiting the program.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please restart the program and enter valid integers.");
        }
    }
    //***************************************************************
    //
    //  Method:       parseInput
    //
    //  Description:  Parses the input and checks if it is valid
    //
    //  Parameters:   String input
    //
    //  Returns:      Boolean
    //
    //**************************************************************
    public Boolean parseInput(String input) 
    {
        Boolean isValid;
        try {
            input = input.replaceAll("^[\\s,]+", "");
            if(!input.isEmpty())
            {
                unsortedArray = Arrays.stream(input.split("[\\s,]+"))
                .filter(s -> !s.isEmpty())  // Filter out empty strings
                .mapToInt(Integer::parseInt)  // Convert each valid string to an integer
                .toArray();
                if(unsortedArray.length == 1 && unsortedArray[0] < 100 && unsortedArray[0] > 0)
                {
                    isValid = true;
                } else if (unsortedArray.length > 1) {
                    isValid = true;
                } else {
                    System.out.println("Invalid input. Please enter valid integers.");
                    isValid = false;
                }
            } 
            else
            {
                System.out.println("Invalid input. Please enter valid integers.");
                isValid = false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid integers.");
            isValid = false;
        }
        return isValid;
    }
    //***************************************************************
    //
    //  Method:       generateRandomNumbers
    //
    //  Description:  Generates random numbers for the array
    //
    //  Parameters:   int count
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void generateRandomNumbers(int count) 
    {
        unsortedArray = new int[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) 
        {
            unsortedArray[i] = random.nextInt(99) + 1; // Numbers between 1 and 99
        }
    }
    //***************************************************************
    //
    //  Method:       sortWithThreads
    //
    //  Description:  Uses SelectionSort threads to sort the array
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void sortWithThreads() 
    {
        int mid = unsortedArray.length / 2;
        SelectionSort thread1 = new SelectionSort(unsortedArray, 0, mid);
        SelectionSort thread2 = new SelectionSort(unsortedArray, mid, unsortedArray.length);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
        mergeArrays(0, mid, unsortedArray.length);
    }
    //***************************************************************
    //
    //  Method:       mergeArrays
    //
    //  Description:  Merges two sorted subarrays
    //
    //  Parameters:   int start, int mid, int end
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void mergeArrays(int start, int mid, int end) 
    {
        int i = start;
        int j = mid;
        int k = 0;
        // Merge both halves into sortedArray
        while (i < mid && j < end) 
        {
            if (unsortedArray[i] < unsortedArray[j]) 
            {
                sortedArray[k++] = unsortedArray[i++];
            } else {
                sortedArray[k++] = unsortedArray[j++];
            }
        }
        while (i < mid) 
        {
            sortedArray[k++] = unsortedArray[i++];
        }
        while (j < end) 
        {
            sortedArray[k++] = unsortedArray[j++];
        }
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
       System.out.println("Name:     Silvia Asmat");
       System.out.println("Course:   COSC 4302 - Operating Systems");
       System.out.println("Program:  Two");
	   System.out.println("Due Date: 10/06/2024\n");
    } // End of the developerInfo method
}
