
import java.util.*;

public class FIFOAlgorithm implements PageReplacementAlgorithm {

    private int pageFaults;

    FIFOAlgorithm() {
        pageFaults = 0;
    }

    @Override
    public void applyAlgorithm(List<Integer> pageReferenceString, int numberOfFrames) {
        Set<Integer> frames = new HashSet<>(numberOfFrames);
        Queue<Integer> queue = new LinkedList<>();

        for (int page : pageReferenceString) {
            if (!frames.contains(page)) {
                if (frames.size() == numberOfFrames) {
                    int removed = queue.poll();
                    frames.remove(removed);
                }
                frames.add(page);
                queue.add(page);
                pageFaults++;
            }
        }
    }

    @Override
    public int getPageFaults() {
        return pageFaults;
    }
}
