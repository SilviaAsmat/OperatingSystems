import java.util.List;

public interface PageReplacementAlgorithm {

    void applyAlgorithm(List<Integer> pageReferenceString, int numberOfFrames);

    int getPageFaults();
    
    
}
