
import java.util.ArrayList;
import java.util.List;


public class LRUAlgorithm implements PageReplacementAlgorithm 
{
    private int pageFaults;
    private List<Integer> frames;
    private List<Integer> pageReferenceString;

    LRUAlgorithm() 
    {
        pageFaults = 0;
    }


    @Override
    public void applyAlgorithm(List<Integer> pageReferenceString, int numberOfFrames) 
    {
        this.pageReferenceString = pageReferenceString;
        frames = new ArrayList<>(numberOfFrames);
        for(int i = 0; i < numberOfFrames; i++)
        {
            frames.add(pageReferenceString.get(i));
            pageFaults++;
        }
        for(int i = numberOfFrames; i < pageReferenceString.size(); i++)
        {
            // indexOfFurthestFrame = findLeastRecentOccurrence();
            replaceFrame(i);
        }
    }

    public void replaceFrame(int indexOfNewFrame)
    {
        // frames.set(indexOfFurthestFrame, pageReferenceString.get(indexOfNewFrame));
        if(frames.contains(pageReferenceString.get(indexOfNewFrame)))
        {
            frames.remove(pageReferenceString.get(indexOfNewFrame));
            frames.add(pageReferenceString.get(indexOfNewFrame));
        }
        else
        {
            frames.remove(0);
            frames.add(pageReferenceString.get(indexOfNewFrame));
            pageFaults++;
        }

    }

    // public int findLeastRecentOccurrence()
    // {
    //     int frameToReplace;
    //     List<Integer> indicesOfLastOccurrence = new ArrayList<>(frames.size());
    //     for (int i = 0; i < frames.size(); i++) 
    //     {
    //         indicesOfLastOccurrence.add(pageReferenceString.lastIndexOf(frames.get(i)));
    //     }
    //     if (indicesOfLastOccurrence.contains(-1)) 
    //     {
    //         frameToReplace = indicesOfLastOccurrence.indexOf(-1);
    //     } 
    //     else 
    //     {
    //         frameToReplace = indicesOfLastOccurrence.indexOf(Collections.max(indicesOfLastOccurrence));
    //     }
    //     return frames.get(frameToReplace);
    // }

    @Override
    public int getPageFaults() 
    {
        return pageFaults;
    }
}

// import java.util.*;

// public class FIFOAlgorithm implements PageReplacementAlgorithm {

//     private int pageFaults;

//     FIFOAlgorithm() {
//         pageFaults = 0;
//     }

//     @Override
//     public void applyAlgorithm(List<Integer> pageReferenceString, int numberOfFrames) {
//         Set<Integer> frames = new HashSet<>(numberOfFrames);
//         Queue<Integer> queue = new LinkedList<>();

//         for (int page : pageReferenceString) {
//             if (!frames.contains(page)) {
//                 if (frames.size() == numberOfFrames) {
//                     int removed = queue.poll();
//                     frames.remove(removed);
//                 }
//                 frames.add(page);
//                 queue.add(page);
//                 pageFaults++;
//             }
//         }
//     }

//     @Override
//     public int getPageFaults() {
//         return pageFaults;
//     }
// }


// import java.util.*;

// public class LRUAlgorithm implements PageReplacementAlgorithm {

//     private int pageFaults;

//     LRUAlgorithm() {
//         pageFaults = 0;
//     }
//     @Override
//     public void applyAlgorithm(List<Integer> pageReferenceString, int numberOfFrames) {
//         Set<Integer> frames = new HashSet<>(numberOfFrames);
//         Map<Integer, Integer> lruMap = new HashMap<>();

//         for (int i = 0; i < pageReferenceString.size(); i++) {
//             int page = pageReferenceString.get(i);
//             if (!frames.contains(page)) {
//                 if (frames.size() == numberOfFrames) {
//                     int lruPage = Collections.min(lruMap.entrySet(), Map.Entry.comparingByValue()).getKey();
//                     frames.remove(lruPage);
//                     lruMap.remove(lruPage);
//                 }
//                 frames.add(page);
//                 pageFaults++;
//             }
//             lruMap.put(page, i);
//         }
//     }

//     @Override
//     public int getPageFaults() {
//         return pageFaults;
//     }
// }
