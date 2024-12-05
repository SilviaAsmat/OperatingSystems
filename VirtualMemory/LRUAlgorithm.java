
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LRUAlgorithm implements PageReplacementAlgorithm 
{
    private int pageFaults;
    private Set<Integer> frames;
    private final List<Integer> pageReferenceString;
    private final int numberOfFrames;
    private final Map<Integer, Integer> lruMap = new HashMap<>();

    LRUAlgorithm(List<Integer> pageReferenceString, int numberOfFrames) 
    {
        this.pageReferenceString = pageReferenceString;
        this.numberOfFrames = numberOfFrames;
        pageFaults = 0;
    }
    @Override
    public void applyAlgorithm() 
    {
        frames = new HashSet<>(numberOfFrames);
        for(int i = 0; i < pageReferenceString.size(); i++)
        {
            replaceFrame(i);
        }
    }

    public void replaceFrame(int indexOfNewFrame)
    {
        int page = pageReferenceString.get(indexOfNewFrame);
        if (!frames.contains(page)) 
        {
                if (frames.size() == numberOfFrames) 
                {
                    int victimFrame = Collections.min(lruMap.entrySet(), Map.Entry.comparingByValue()).getKey();
                    frames.remove(victimFrame);
                    lruMap.remove(victimFrame);
                }
                frames.add(page);
                pageFaults++;
            }
            lruMap.put(page, indexOfNewFrame);
    }

    @Override
    public int getPageFaults() 
    {
        return pageFaults;
    }
}


