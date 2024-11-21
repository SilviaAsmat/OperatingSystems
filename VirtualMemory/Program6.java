import java.util.*;

public class Program6 {
    public static void main(String[] args) {
        Program6 program = new Program6();
        program.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the page-reference string (comma or space separated) or 0 to exit:");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            List<Integer> pageReferenceString = parsePageReferenceString(input);
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
        scanner.close();
    }

    private List<Integer> parsePageReferenceString(String input) {
        String[] tokens = input.split("[,\\s]+");
        List<Integer> pageReferenceString = new ArrayList<>();
        for (String token : tokens) {
            pageReferenceString.add(Integer.parseInt(token));
        }
        return pageReferenceString;
    }
}