
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OPTAlgorithm implements PageReplacementAlgorithm 
{
    private int pageFaults;
    private final List<Integer> frames = new ArrayList<>();
    private final List<Integer> pageReferenceString;
    private final int maxNumberOfFrames;

    OPTAlgorithm(List<Integer> pageReferenceString, int numberOfFrames) 
    {
        this.pageReferenceString = pageReferenceString;
        this.maxNumberOfFrames = numberOfFrames;
        pageFaults = 0;
    }
    @Override
    public void applyAlgorithm() 
    {
        for(int i = 0; i < pageReferenceString.size(); i++)
        {
            if(!frames.contains(pageReferenceString.get(i)))
            {
                onFault(i);
            }
        }
    }

    public Integer findVictim(int newFrameIndex)
    {
        int indexOfVictim;
        List<Integer> nextOccurencesList = new ArrayList<>();

        List<Integer> futureSubstring = pageReferenceString.subList(newFrameIndex + 1, pageReferenceString.size());  
        for (Integer frame : frames) 
        {
            int nextOccurenceIndex = futureSubstring.indexOf(frame);
            nextOccurencesList.add(nextOccurenceIndex);
        }
        
        if(nextOccurencesList.contains(-1))
        {
            indexOfVictim = nextOccurencesList.indexOf(-1);
        }
        else
        {
            int furthestOccurenceIndex = Collections.max(nextOccurencesList);
            
            indexOfVictim = nextOccurencesList.indexOf(furthestOccurenceIndex);
        }
        return indexOfVictim;

    }

    public void onFault(int indexOfNewFrame)
    {
        if (frames.size() == maxNumberOfFrames) 
        {
            int indexOfVictim = findVictim(indexOfNewFrame);
            frames.remove(indexOfVictim);
        }
        frames.add(pageReferenceString.get(indexOfNewFrame));
        pageFaults++;
    }

    @Override
    public int getPageFaults() 
    {
        return pageFaults;
    }
}
