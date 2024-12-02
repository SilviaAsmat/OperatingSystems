
import java.util.*;

public class OPTAlgorithm implements PageReplacementAlgorithm {

    private int pageFaults;

    @Override
    public void applyAlgorithm(List<Integer> pageReferenceString, int numberOfFrames) {
        pageFaults = 0;
        Set<Integer> frames = new HashSet<>(numberOfFrames);

        for (int i = 0; i < pageReferenceString.size(); i++) {
            int page = pageReferenceString.get(i);
            if (!frames.contains(page)) {
                if (frames.size() == numberOfFrames) {
                    int farthest = i;
                    int pageToRemove = -1;
                    for (int frame : frames) {
                        int nextUse = pageReferenceString.subList(i, pageReferenceString.size()).indexOf(frame);
                        if (nextUse == -1) {
                            pageToRemove = frame;
                            break;
                        } else if (nextUse > farthest) {
                            farthest = nextUse;
                            pageToRemove = frame;
                        }
                    }
                    frames.remove(pageToRemove);
                }
                frames.add(page);
                pageFaults++;
            }
        }
    }

    @Override
    public int getPageFaults() {
        return pageFaults;
    }
}
