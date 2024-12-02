
import java.util.*;

public class LFUAlgorithm implements PageReplacementAlgorithm {

    private int pageFaults;

    LFUAlgorithm() {
        pageFaults = 0;
    }

    @Override
    public void applyAlgorithm(List<Integer> pageReferenceString, int numberOfFrames) {
        Set<Integer> frames = new HashSet<>(numberOfFrames);
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int page : pageReferenceString) {
            if (!frames.contains(page)) {
                if (frames.size() == numberOfFrames) {
                    int lfuPage = Collections.min(frequencyMap.entrySet(), Map.Entry.comparingByValue()).getKey();
                    frames.remove(lfuPage);
                    frequencyMap.remove(lfuPage);
                }
                frames.add(page);
                pageFaults++;
            }
            frequencyMap.put(page, frequencyMap.getOrDefault(page, 0) + 1);
        }
    }

    @Override
    public int getPageFaults() {
        return pageFaults;
    }
}
