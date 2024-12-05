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
        int victimFrameIndex;
        this.pageReferenceString = pageReferenceString;
        // Initialize frames
        frames = new ArrayList<>(numberOfFrames);
        // Fill initial frames and increment pageFaults
        for(int i = 0; i < numberOfFrames; i++)
        {
            frames.add(pageReferenceString.get(i));
            pageFaults++;
            System.out.println("Page Faults: " + pageFaults);
            System.out.println("Frames: " + frames.toString());
        }
        // Iterate through the rest of the pageReferenceString
        for(int i = numberOfFrames; i < pageReferenceString.size(); i++)
        {
            if(!frames.contains(pageReferenceString.get(i)))
            {
                System.err.println("==================================================================");
                System.out.println("current page: " + pageReferenceString.get(i));
                System.out.println("Page Faults: " + pageFaults);
                System.out.println("Frames: " + frames.toString());
                victimFrameIndex = findVictim(i);
                replaceFrame(victimFrameIndex, i);
                pageFaults++;
                System.out.println("Page Faults: " + pageFaults);
                System.out.println("Frames: " + frames.toString());
                System.err.println("==================================================================");
            }
        }
    }

    public void replaceFrame(int indexOfVictim, int indexOfNewFrame)
    {
        // out of bounds
        // String frameToReplace = pageReferenceString.get(indexOfFurthestFrame);
        frames.remove(indexOfVictim);
        frames.add(pageReferenceString.get(indexOfNewFrame));
    }
    // Find the frame that will not be used for the longest time
    public int findVictim(int currentIndexOfPage)
    {
        int indexOfVictimInFrames;
        List<Integer> indexOfFramesNextOccurenceInReferenceString = new ArrayList<>();
        List<Integer> notSearchedPageReferenceSubString = pageReferenceString.subList(currentIndexOfPage, pageReferenceString.size());
        // Find the next occurrence of each frame in the notSearchedPageReferenceSubString
        for (int i = 0; i < frames.size(); i++) 
        {
            indexOfFramesNextOccurenceInReferenceString.add(notSearchedPageReferenceSubString.indexOf(frames.get(i)));
        }
        // indexOf returns -1 if the element is not found
        // Find the first frame that was not found
        if (indexOfFramesNextOccurenceInReferenceString.contains(-1)) 
        {
            //Find the first frame that was not found in the pageReferenceString
            indexOfVictimInFrames = indexOfFramesNextOccurenceInReferenceString.indexOf(-1);
        } 
        else 
        {
            // Find the frame that will not be used for the longest time, by finding the index in indexOfFramesNextOccurenceInReferenceString that holds the maximum value 
            indexOfVictimInFrames = indexOfFramesNextOccurenceInReferenceString.indexOf(Collections.max(indexOfFramesNextOccurenceInReferenceString));
        }
        System.err.println("Frame to replace: " + frames.get(indexOfVictimInFrames));
        return indexOfVictimInFrames;
    }

    @Override
    public int getPageFaults() 
    {
        return pageFaults;
    }
}
