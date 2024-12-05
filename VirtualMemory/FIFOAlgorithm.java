
import java.util.ArrayList;
import java.util.List;


public class FIFOAlgorithm implements PageReplacementAlgorithm 
{
    private int pageFaults;
    private List<Integer> frames;
    private List<Integer> pageReferenceString;

    FIFOAlgorithm() 
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
            if(!frames.contains(pageReferenceString.get(i)))
            {
                replaceFrame(i);
                pageFaults++;
            }
        }
    }

    public void replaceFrame(int indexOfNewFrame)
    {
        // FIFO
        frames.remove(0);
        frames.add(pageReferenceString.get(indexOfNewFrame));
    }

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
