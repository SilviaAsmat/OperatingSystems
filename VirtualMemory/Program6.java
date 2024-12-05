//Sunday, December 8, 2024
import java.util.*;

public class Program6 {
    private List<Integer> pageReferenceString = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) 
    {
        Program6 program = new Program6();
        program.run();
    }

    public void run() 
    {
            String input;
            input = getUserReferenceString();
            while (!input.equals("0")) 
            {
                if(input.equals("random")) 
                {
                    pageReferenceString = generateRandomPageReferenceString();
                    System.out.println("Random page-reference string: " + pageReferenceString);
                } 
                else 
                {
                    pageReferenceString = parsePageReferenceString(input);
                }
                System.out.println("Enter the number of page frames:");
                int numberOfFrames = getUserIntegerInput();
                runPageReplacementAlgorithm(numberOfFrames);
                pageReferenceString.clear();
                input = getUserReferenceString();
            }//end while
        
    }

    private void runPageReplacementAlgorithm(int numberOfFrames) 
    {
        List<PageReplacementAlgorithm> algorithms = Arrays.asList(
            new FIFOAlgorithm(),
            new LRUAlgorithm(),
            new LFUAlgorithm(),
            new OPTAlgorithm()
        );
        for (PageReplacementAlgorithm algorithm : algorithms) 
        {
            algorithm.applyAlgorithm(pageReferenceString, numberOfFrames);
            System.out.println(algorithm.getClass().getSimpleName() + " page faults: " + algorithm.getPageFaults());
        }
    }

    private String getUserReferenceString()
    {
        String input = "";
        while(input.isEmpty())
        {
            System.out.println("""
                Enter the page-reference string (comma or space separated)
                Enter 'random' to have a random string generated
                Enter 0 to exit:
                """);
            input = scanner.nextLine();
            if(!input.matches("[0-9,\\s]+") && !input.equals("random"))
            {
                System.out.println("Integers or random only. Please enter a valid page-reference string.");
                input = "";
            }
        }
        return input;
    }

    private Integer getUserIntegerInput() {
        int input = 0;
        while (input == 0) {
            try {
                input = scanner.nextInt();
                scanner.nextLine(); // consume the newline
                if (input <= 0) {
                    System.out.println("Please enter a number greater than 0.");
                    input = 0;
                } else if (input > pageReferenceString.size()) { // Set a reasonable upper limit
                    System.out.println("Please enter a frame size smaller or equal to the page-reference string.");
                    input = 0;
                }
            } catch (InputMismatchException e) {
                System.out.println("Integers only. Please enter a valid number.");
                input = 0;
                scanner.nextLine(); // consume the invalid input
            }
        }
        return input;
    }

    private List<Integer> parsePageReferenceString(String input) {
        String[] tokens = input.split("[,\\s]+");
        for (String token : tokens) {
            pageReferenceString.add(Integer.valueOf(token));
        }
        return pageReferenceString;
    }
    // let the program generate 
    // one, randomly, with the numbers ranging from 0 to 9.
    public List<Integer> generateRandomPageReferenceString() {
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            pageReferenceString.add(random.nextInt(10));
        }
        return pageReferenceString;
    }
}
