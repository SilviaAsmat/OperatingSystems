
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LFUAlgorithm implements PageReplacementAlgorithm 
{
    private int pageFaults;
    private List<Integer> frames;
    private final List<Integer> pageReferenceString;
    private final int numberOfFrames;

    LFUAlgorithm(List<Integer> pageReferenceString, int numberOfFrames) 
    {
        this.pageReferenceString = pageReferenceString;
        this.numberOfFrames = numberOfFrames;
        pageFaults = 0;
    }

    @Override
    public void applyAlgorithm() 
    {
        int victimFrameIndex;
        // Initialize frames
        frames = new ArrayList<>(numberOfFrames);
        // Fill initial frames and increment pageFaults
        for(int i = 0; i < numberOfFrames; i++)
        {
            frames.add(pageReferenceString.get(i));
            pageFaults++;
        }
        // Iterate through the rest of the pageReferenceString
        for(int i = numberOfFrames; i < pageReferenceString.size(); i++)
        {
            if(!frames.contains(pageReferenceString.get(i)))
            {
                victimFrameIndex = findVictim(i);
                replaceFrame(victimFrameIndex, i);
                pageFaults++;
            }
        }
    }

    public void replaceFrame(int indexOfVictim, int indexOfNewFrame)
    {
        frames.remove(indexOfVictim);
        frames.add(pageReferenceString.get(indexOfNewFrame));
    }
    // Find the frame that will not be used for the longest time
    public int findVictim(int currentIndexOfPage)
    {
        int indexOfVictimInFrames;
        List<Integer> countOfFramesPreviousOccurences = new ArrayList<>();
        List<Integer> usedPageReferenceString = pageReferenceString.subList(0, currentIndexOfPage);
       // System.err.println("usedPageReferenceString: " + usedPageReferenceString);
        for (int i = 0; i < frames.size(); i++) 
        {
            countOfFramesPreviousOccurences.add(Collections.frequency(usedPageReferenceString, frames.get(i)));
        }
        indexOfVictimInFrames = countOfFramesPreviousOccurences.indexOf(Collections.min(countOfFramesPreviousOccurences));
        return indexOfVictimInFrames;
    }

    @Override
    public int getPageFaults() 
    {
        return pageFaults;
    }
}