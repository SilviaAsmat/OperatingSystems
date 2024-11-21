// import java.util.Arrays;
// import java.util.InputMismatchException;
// import java.util.Random;
// import java.util.Scanner;

// public class Program2 {
//     // Global array for unsorted integers
//     private int[] unsortedArray;
//     // Global array for the sorted output
//     private int[] sortedArray;

//     public static void main(String[] args) {
//         Program2 program = new Program2();
//         program.runProgram();
//     }
//     // Method to run the program
//     public void runProgram() {
//         try (Scanner scanner = new Scanner(System.in)) {
//             int numberOfIntegers; // Start with a value other than 0
//             System.out.print("Enter the number of integers to sort (One integer to generate random numbers) (0 to quit): ");
//             numberOfIntegers = scanner.nextInt();
//             // Keep asking the user until they enter 0
//             while (numberOfIntegers != 0) {
//                 try {
//                     String input = scanner.nextLine();
//                     if (input.length() == 1) {
//                         generateRandomNumbers(numberOfIntegers);
//                     } else {
//                         unsortedArray = Arrays.stream(input.split("[\\s,]+"))
//                         .filter(s -> !s.isEmpty())  // Filter out empty strings
//                         .mapToInt(Integer::parseInt)  // Convert each valid string to an integer
//                         .toArray();
//                     }
//                     // Initialize the sorted array
//                     sortedArray = new int[unsortedArray.length];
//                     // Sort using multiple threads
//                     sortWithThreads();
//                     // Output the final sorted array
//                     System.out.println("Sorted Array: " + Arrays.toString(sortedArray));
//                 } catch (InputMismatchException e) {
//                     System.out.println("Invalid input. Please enter valid integers.");
//                     scanner.nextLine(); // Clear the invalid input
//                 }
//                 System.out.print("Enter the number of integers to sort (0 to quit): ");
//                 numberOfIntegers = scanner.nextInt();
//             }
//             System.out.println("Exiting the program.");
//         } catch (InputMismatchException e) {
//             System.out.println("Invalid input. Please restart the program and enter valid integers.");
//         }
//     }

//     // Method to generate random numbers
//     private void generateRandomNumbers(int count) {
//         unsortedArray = new int[count];
//         Random random = new Random();
//         for (int i = 0; i < count; i++) {
//             unsortedArray[i] = random.nextInt(99) + 1; // Numbers between 1 and 99
//         }
//     }

//     // Method to handle sorting using threads
//     private void sortWithThreads() {
//         int mid = unsortedArray.length / 2;
//         // Create two sorting threads
//         SelectionSort thread1 = new SelectionSort(unsortedArray, 0, mid);
//         SelectionSort thread2 = new SelectionSort(unsortedArray, mid, unsortedArray.length);
//         // Start both threads
//         thread1.start();
//         thread2.start();
//         try {
//             // Wait for both threads to finish
//             thread1.join();
//             thread2.join();
//         } catch (InterruptedException e) {
//             System.out.println("Thread interrupted: " + e.getMessage());
//         }
//         // Merge the sorted subarrays
//         mergeArrays(0, mid, unsortedArray.length);
//     }
//     // Method to merge two sorted subarrays
//     private void mergeArrays(int start, int mid, int end) {
//         int i = start, j = mid, k = 0;
//         // Merge both halves into sortedArray
//         while (i < mid && j < end) {
//             if (unsortedArray[i] < unsortedArray[j]) {
//                 sortedArray[k++] = unsortedArray[i++];
//             } else {
//                 sortedArray[k++] = unsortedArray[j++];
//             }
//         }
//         // Copy remaining elements
//         while (i < mid) {
//             sortedArray[k++] = unsortedArray[i++];
//         }
//         while (j < end) {
//             sortedArray[k++] = unsortedArray[j++];
//         }
//     }
// }
