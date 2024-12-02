
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OPTAlgorithm implements PageReplacementAlgorithm 
{
    private int pageFaults;
    private List<Integer> frames;
    private List<Integer> pageReferenceString;

    OPTAlgorithm() 
    {
        pageFaults = 0;
    }


    @Override
    public void applyAlgorithm(List<Integer> pageReferenceString, int numberOfFrames) 
    {
        int indexOfFurthestFrame;
        this.pageReferenceString = pageReferenceString;
        frames = new ArrayList<>(numberOfFrames);
        for(int i = 0; i < numberOfFrames; i++)
        {
            frames.add(pageReferenceString.get(i));
            pageFaults++;
        }
        for(int i = numberOfFrames; i < pageReferenceString.size(); i++)
        {
            if(!frames.contains(pageReferenceString.get(i)))
            {
                indexOfFurthestFrame = findFurtherestOccurrence();
                replaceFrame(indexOfFurthestFrame, i);
                pageFaults++;
            }
        }
    }

    public void replaceFrame(int indexOfFurthestFrame, int indexOfNewFrame)
    {
        frames.set(indexOfFurthestFrame, pageReferenceString.get(indexOfNewFrame));
    }

    public int findFurtherestOccurrence()
    {
        // for(int i = numberOfFrames; i < pageReferenceString.size(); i++)
        // {
        //     int lastOccurrence = pageReferenceString.subList(i, -1).lastIndexOf(pageReferenceString.get(i));
            
        // }
        int frameToReplace;
        List<Integer> indicesOfLastOccurrence = new ArrayList<>(frames.size());
        for (int i = 0; i < frames.size(); i++) 
        {
            indicesOfLastOccurrence.add(pageReferenceString.lastIndexOf(frames.get(i)));
        }
        if (indicesOfLastOccurrence.contains(-1)) 
        {
            frameToReplace = indicesOfLastOccurrence.indexOf(-1);
        } 
        else 
        {
            frameToReplace = indicesOfLastOccurrence.indexOf(Collections.max(indicesOfLastOccurrence));
        }
        return frames.get(frameToReplace);
    }

    @Override
    public int getPageFaults() 
    {
        return pageFaults;
    }
}
