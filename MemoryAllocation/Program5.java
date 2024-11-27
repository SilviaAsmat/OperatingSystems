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
        System.out.print("Enter the initial memory size in MB: ");
        long initialMemoryBytes = getIntFromUser() * 1048576;
        allocator = new MemoryAllocator(initialMemoryBytes, "Unused");
        String input = "";
        while (!input.equalsIgnoreCase("X")) 
        {
            System.out.print("allocator> ");
            if(scanner.hasNextLine())
            {
                input = scanner.nextLine();
                String[] columns = input.trim().split("[\\s,]+");
                // System.out.println("Columns:" + java.util.Arrays.toString(columns));
                // String input = "RQ P0 1000 F";
                try {
                    // System.out.println("Input: '" + input + "'");
                    // String[] columns = input.trim().split("[\\s,]+");
                    
                    processMemoryOption(columns);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Input Error: Invalid Option");
                }
            }
            else
            {
                System.out.println("Invalid input.");
            }
            
        }// end while
    }// end runProgram

    public void processMemoryOption(String[] userInput)
    {
        switch (userInput[0]) 
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
}