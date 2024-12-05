
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OPTAlgorithm implements PageReplacementAlgorithm 
{
    private int pageFaults;
    private List<Integer> frames;
    private final List<Integer> pageReferenceString;
    private final int numberOfFrames;

    OPTAlgorithm(List<Integer> pageReferenceString, int numberOfFrames) 
    {
        this.pageReferenceString = pageReferenceString;
        this.numberOfFrames = numberOfFrames;
        pageFaults = 0;
        frames = new ArrayList<>(numberOfFrames);
    }
    @Override
    public void applyAlgorithm() 
    {
        frames = new ArrayList<>(numberOfFrames);
        for(int i = 0; i < pageReferenceString.size(); i++)
        {
            if(!frames.contains(pageReferenceString.get(i)))
            {
                replaceFrame(i);
            }
        }
    }

    public Integer findVictim(int newFrameIndex)
    {
        int indexOfVictim;
        List<Integer> maxDistance = new ArrayList<>();

        List<Integer> notSearchedPageReferenceSubString = pageReferenceString.subList(newFrameIndex, pageReferenceString.size());  
        for (Integer frame : frames) 
        {
            maxDistance.add(notSearchedPageReferenceSubString.indexOf(frame));
        }
        
        if(maxDistance.contains(-1))
        {
            indexOfVictim = maxDistance.indexOf(-1);
        }
        else
        {
            indexOfVictim = maxDistance.indexOf(Collections.max(maxDistance));
        }
        return indexOfVictim;

    }

    public void replaceFrame(int indexOfNewFrame)
    {
        int page = pageReferenceString.get(indexOfNewFrame);
        if (frames.size() == numberOfFrames) 
        {
            frames.remove(findVictim(indexOfNewFrame));
        }
        frames.add(page);
        pageFaults++;
    }

    @Override
    public int getPageFaults() 
    {
        return pageFaults;
    }
}



// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;

// public class OPTAlgorithm implements PageReplacementAlgorithm 
// {
//     private int pageFaults;
//     private List<Integer> frames;
//     private final List<Integer> pageReferenceString;
//     private final int numberOfFrames;

//     OPTAlgorithm(List<Integer> pageReferenceString, int numberOfFrames) 
//     {
//         this.pageReferenceString = pageReferenceString;
//         this.numberOfFrames = numberOfFrames;
//         pageFaults = 0;
//     }

//     @Override
//     public void applyAlgorithm() 
//     {
//         int victimFrameIndex;
//         // Initialize frames
//         frames = new ArrayList<>(numberOfFrames);
//         // Fill initial frames and increment pageFaults
//         // for(int i = 0; i < numberOfFrames; i++)
//         // {
//         //     frames.add(pageReferenceString.get(i));
//         //     pageFaults++;
//         // }
//         // Iterate through the rest of the pageReferenceString
//         for(int i = 0; i < pageReferenceString.size(); i++)
//         {
//             if(!frames.contains(pageReferenceString.get(i)))
//             {
//                 victimFrameIndex = findVictim(i);
//                 replaceFrame(victimFrameIndex, i);
//                 pageFaults++;
//             }
//         }
//     }

//     public void replaceFrame(int indexOfVictim, int indexOfNewFrame)
//     {
//         if(frames.size() == numberOfFrames)
//         {
//             frames.remove(indexOfVictim);   
//         }
//         frames.add(pageReferenceString.get(indexOfNewFrame));
//     }
//     // Find the frame that will not be used for the longest time
//     public int findVictim(int currentIndexOfPage)
//     {
//         int indexOfVictimInFrames;
//         List<Integer> indexOfFramesNextOccurenceInReferenceString = new ArrayList<>();
//         List<Integer> notSearchedPageReferenceSubString = pageReferenceString.subList(currentIndexOfPage, pageReferenceString.size());
//         // Find the next occurrence of each frame in the notSearchedPageReferenceSubString
//         for (int i = 0; i < frames.size(); i++) 
//         {
//             indexOfFramesNextOccurenceInReferenceString.add(notSearchedPageReferenceSubString.indexOf(frames.get(i)));
//         }
//         // indexOf returns -1 if the element is not found
//         // Find the first frame that was not found
//         if (indexOfFramesNextOccurenceInReferenceString.contains(-1)) 
//         {
//             //Find the first frame that was not found in the pageReferenceString
//             indexOfVictimInFrames = indexOfFramesNextOccurenceInReferenceString.indexOf(-1);
//         } 
//         else 
//         {
//             // Find the frame that will not be used for the longest time, by finding the index in indexOfFramesNextOccurenceInReferenceString that holds the maximum value 
//             indexOfVictimInFrames = indexOfFramesNextOccurenceInReferenceString.indexOf(Collections.max(indexOfFramesNextOccurenceInReferenceString));
//         }
//         return indexOfVictimInFrames;
//     }

//     @Override
//     public int getPageFaults() 
//     {
//         return pageFaults;
//     }
// }
