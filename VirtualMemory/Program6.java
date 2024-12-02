
import java.util.*;

public class Program6 {
    private List<Integer> pageReferenceString = new ArrayList<>();
    public static void main(String[] args) {
        Program6 program = new Program6();
        program.run();
    }

    public void run() 
    {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter the page-reference string (comma or space separated)\nEnter 'random' to have a random string generated\nEnter 0 to exit:");
                String input = scanner.nextLine();
                if (input.equals("0")) {
                    break;
                }
                if(input.equals("random")) {
                    System.out.println("Enter the length of the page-reference string:");
                    int length = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    pageReferenceString = generateRandomPageReferenceString(length);
                    System.out.println("Random page-reference string: " + pageReferenceString);
                } 
                else 
                {
                    pageReferenceString = parsePageReferenceString(input);
                }
                System.out.println("Enter the number of page frames:");
                int numberOfFrames = scanner.nextInt();
                scanner.nextLine(); // consume the newline
                    
                List<PageReplacementAlgorithm> algorithms = Arrays.asList(
                        new FIFOAlgorithm(),
                        new LRUAlgorithm(),
                        new LFUAlgorithm(),
                        new OPTAlgorithm()
                );

                for (PageReplacementAlgorithm algorithm : algorithms) {
                    algorithm.applyAlgorithm(pageReferenceString, numberOfFrames);
                    System.out.println(algorithm.getClass().getSimpleName() + " page faults: " + algorithm.getPageFaults());
                }
            }
        }
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
    public List<Integer> generateRandomPageReferenceString(int length) {
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            pageReferenceString.add(random.nextInt(10));
        }
        return pageReferenceString;
    }
}
