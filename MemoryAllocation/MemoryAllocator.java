import java.util.*;

public class MemoryAllocator {
    private final List<MemoryBlock> memoryBlocks;
    private final int totalMemory;

    public MemoryAllocator(int totalMemory) 
    {
        this.totalMemory = totalMemory;
        this.memoryBlocks = new ArrayList<>();
        // Initialize with one large free block
        memoryBlocks.add(new MemoryBlock(0, totalMemory, true, null));
    }

// First fit. Allocate the first hole that is big enough. Searching can start either
// at the beginning of the set of holes or at the location where the previous
// first-fit search ended. We can stop searching as soon as we find a free hole
// that is large enough.
// • Best fi . Allocate the smallest hole that is big enough. We must search the
// entire list, unless the list is ordered by size. This strategy produces the
// smallest leftover hole.
// • Worst fit. Allocate the largest hole. Again, we must search the entire list,
// unless it is sorted by size. This strategy produces the largest leftover hole,
// which may be more useful than the smaller leftover hole from a best-fit
// approach.

    public void requestMemory(String processId, int size, char strategy) 
    {
        // Implement memory request logic based on strategy
        //  F - First fit;B - Best fit;W - Worst fit
        
    }

// if  a  partition  being  released  is  adjacent  to  an 
// existing hole, be sure to combine the two holes into a single hole.
    public void releaseMemory(String processId) 
    {
        // Implement memory release logic
    }
    // Compaction 
 
    // If the user enters the C command, your program will compact the set of holes into one 
    // larger hole. For example, if you have four separate holes of size 550 KB, 375 KB, 1,900 
    // KB,  and  4,500  KB,  your  program  will  combine  these  four  holes  into  one  large  hole  of 
    // size 7,325 KB.  
     
    // There are several strategies for implementing compaction, one of which is suggested in 
    // Section  9.2.3.  Be  sure  to  update  the  beginning  address  of  any  processes  that  have 
    // been affected by compaction. 
    public void compactMemory() 
    {
        // Implement memory compaction logic
    }

    public void reportStatus() 
    {
        // Implement status reporting logic
    }
}