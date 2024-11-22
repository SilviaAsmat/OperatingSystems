import java.util.Scanner;

public class Program5 {
    private final Scanner scanner;

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
        int initialMemoryBytes = initialMemoryMB * 1024 * 1024;

        MemoryAllocator allocator = new MemoryAllocator(initialMemoryBytes);

        while (true) 
        {
            System.out.print("allocator> ");
            String command = scanner.next();
//             allocator>RQ P0 40000 W  
// The first parameter to the RQ command is the new process that requires the memory, 
// followed  by  the  amount  of  memory  being  requested,  and  finally  the  strategy.  (In  this 
// situation, “W” refers to worst fit.)
            switch (command) 
            {
                // Request memory
                case "RQ" -> 
                {
                    String processId = scanner.next();
                    int size = scanner.nextInt();
                    char strategy = scanner.next().charAt(0);
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
}