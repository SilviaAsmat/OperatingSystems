import java.util.Scanner;

public class Program5 
{
    private final Scanner scanner;
    private MemoryAllocator allocator;

    Program5() 
    {
        scanner = new Scanner(System.in);
    }
    public static void main(String[] args) 
    {
        Program5 program = new Program5();
        program.runProgram();
    }

    public void runProgram()
    {
        System.out.print("Enter the initial amount of memory (in MB): ");
        int initialMemoryMB = scanner.nextInt();
        long initialMemoryBytes = initialMemoryMB * 1048576;
        allocator = new MemoryAllocator(initialMemoryBytes);
        String input = "";
        while (!input.equals("X")) 
        {
            System.out.print("allocator> ");
            input = scanner.nextLine();
            // String[] columns = input.trim().split("[\\s,]+");
            // System.out.println("Columns:" + java.util.Arrays.toString(columns));
            //String input = "RQ P0 1000 F";
            try {
                System.out.println("Input: '" + input + "'");
                String[] columns = input.trim().split("[\\s,]+");
                for (String column : columns) {
                    System.out.println("Column: '" + column + "'");
                }
                processMemoryOption(columns);
            } catch (Exception e) {
                System.out.println("Error: Invalid Input");
            }
            
        }// end while
    }// end runProgram

    public void processMemoryOption(String[] userInput)
    {
        //             allocator>RQ P0 40000 W  
        // The first parameter to the RQ command is the new process that requires the memory, 
        // followed  by  the  amount  of  memory  being  requested,  and  finally  the  strategy.  (In  this 
        // situation, “W” refers to worst fit.)
        switch (userInput[0]) 
        {
            // Request memory
            case "RQ" -> 
            {
                String processId = userInput[1];
                int size = Integer.parseInt(userInput[2]);
                //Change to check if it's one char
                char[] column2 = userInput[3].toCharArray();
                char strategy = column2[0];
                allocator.requestMemory(processId, size, strategy);
            }
            // Release memory
            case "RL" -> 
            {
                String processId = scanner.next();
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
}