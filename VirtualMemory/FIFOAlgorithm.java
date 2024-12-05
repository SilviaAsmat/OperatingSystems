
import java.util.ArrayList;
import java.util.List;


public class FIFOAlgorithm implements PageReplacementAlgorithm 
{
    private int pageFaults;
    private List<Integer> frames;
    private final List<Integer> pageReferenceString;
    private final int numberOfFrames;

    FIFOAlgorithm(List<Integer> pageReferenceString, int numberOfFrames) 
    {
        this.pageReferenceString = pageReferenceString;
        this.numberOfFrames = numberOfFrames;
        pageFaults = 0;
    }

    @Override
    public void applyAlgorithm() 
    {
        
        frames = new ArrayList<>(numberOfFrames);
        for(int i = 0; i < pageReferenceString.size(); i++)
        {
            if(!frames.contains(pageReferenceString.get(i)))
            {
                replaceFrame(i, numberOfFrames);
                pageFaults++;
            }
        }
    }

    public void replaceFrame(int indexOfNewFrame, int numberOfFrames)
    {
        // FIFO
        if(frames.size() == numberOfFrames)
        {
            frames.remove(0);
            
        }
        frames.add(pageReferenceString.get(indexOfNewFrame));
    }

    @Override
    public int getPageFaults() 
    {
        return pageFaults;
    }
}