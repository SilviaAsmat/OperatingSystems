// import java.util.Arrays;
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
//         Scanner scanner = new Scanner(System.in);
//         int numberOfIntegers;

//         do {
//             // Get number of integers to sort
//             System.out.print("Enter the number of integers to sort (0 to quit): ");
//             numberOfIntegers = scanner.nextInt();

//             if (numberOfIntegers == 0) {
//                 System.out.println("Exiting the program.");
//                 break;
//             }

//             // Get integers or generate random numbers
//             System.out.print("Enter integers (comma or space separated) or type 'random': ");
//             String input = scanner.next();

//             if (input.equalsIgnoreCase("random")) {
//                 generateRandomNumbers(numberOfIntegers);
//             } else {
//                 unsortedArray = Arrays.stream(input.split("[, ]+"))
//                         .mapToInt(Integer::parseInt)
//                         .toArray();
//             }

//             // Initialize the sorted array
//             sortedArray = new int[unsortedArray.length];

//             // Sort using multiple threads
//             sortWithThreads();

//             // Output the final sorted array
//             System.out.println("Sorted Array: " + Arrays.toString(sortedArray));

//         } while (numberOfIntegers != 0);

//         scanner.close();
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
//         Thread thread1 = new Thread(new SelectionSort(0, mid));
//         Thread thread2 = new Thread(new SelectionSort(mid, unsortedArray.length));

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

//     // Selection Sort implemented in a thread
//     class SelectionSort implements Runnable {
//         private int start;
//         private int end;

//         public SelectionSort(int start, int end) {
//             this.start = start;
//             this.end = end;
//         }

//         @Override
//         public void run() {
//             for (int i = start; i < end - 1; i++) {
//                 int minIndex = i;
//                 for (int j = i + 1; j < end; j++) {
//                     if (unsortedArray[j] < unsortedArray[minIndex]) {
//                         minIndex = j;
//                     }
//                 }
//                 // Swap the minimum element with the current element
//                 int temp = unsortedArray[minIndex];
//                 unsortedArray[minIndex] = unsortedArray[i];
//                 unsortedArray[i] = temp;
//             }
//         }
//     }
// }
