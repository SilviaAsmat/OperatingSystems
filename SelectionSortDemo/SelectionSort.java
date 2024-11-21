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
public class SelectionSort extends Thread {
    private final int[] array;
    private final int start;
    private final int end;

    //********************************************************************
    //
    //  Method:       SelectionSort
    //
    //  Description:  Constructor to initialize the array, start, and end index
    //
    //  Parameters:   int[], int, int
    //
    //  Returns:      N/A
    //
    //********************************************************************
    public SelectionSort(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    //********************************************************************
    //
    //  Method:       run
    //
    //  Description:  The run method containing the Selection Sort logic
    //
    //  Parameters:   N/A
    //
    //  Returns:      N/A
    //
    //********************************************************************
    @Override
    public void run() {
        for (int i = start; i < end - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < end; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the minimum element with the current element
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}
