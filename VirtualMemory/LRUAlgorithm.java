
import java.util.*;

public class LRUAlgorithm implements PageReplacementAlgorithm {

    private int pageFaults;

    LRUAlgorithm() {
        pageFaults = 0;
    }
    @Override
    public void applyAlgorithm(List<Integer> pageReferenceString, int numberOfFrames) {
        Set<Integer> frames = new HashSet<>(numberOfFrames);
        Map<Integer, Integer> lruMap = new HashMap<>();

        for (int i = 0; i < pageReferenceString.size(); i++) {
            int page = pageReferenceString.get(i);
            if (!frames.contains(page)) {
                if (frames.size() == numberOfFrames) {
                    int lruPage = Collections.min(lruMap.entrySet(), Map.Entry.comparingByValue()).getKey();
                    frames.remove(lruPage);
                    lruMap.remove(lruPage);
                }
                frames.add(page);
                pageFaults++;
            }
            lruMap.put(page, i);
        }
    }

    @Override
    public int getPageFaults() {
        return pageFaults;
    }
}
