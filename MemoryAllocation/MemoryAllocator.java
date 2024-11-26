import java.util.*;

public class MemoryAllocator 
{
    private final List<MemoryBlock> memoryBlocks;
    private final long totalMemory;

    public MemoryAllocator(long initialSize) 
    {
        this.totalMemory = initialSize;
        this.memoryBlocks = new ArrayList<>();
        // Initialize with one large free block
        memoryBlocks.add(new MemoryBlock(0, initialSize, true, null));
    }

    public void requestMemory(String processId, int memorySize, char strategy) 
    {
        // Implement memory request logic based on strategy
        //  F - First fit;B - Best fit;W - Worst fit
        // The first parameter to the RQ command is the new process that requires the memory, 
        // followed  by  the  amount  of  memory  being  requested,  and  finally  the  strategy.
        switch (strategy) 
        {
            case 'F' -> firstFit(processId, memorySize);
            case 'B' -> bestFit(processId, memorySize);
            case 'W' -> worstFit(processId, memorySize);
            default -> System.out.println("Invalid strategy.");
        }
    }

    // Change to return boolean?
    public void firstFit(String processId, int newBlockSize) 
    {
        // Implement first fit logic
        // First fit. Allocate the first hole that is big enough. Searching can start either
        // at the beginning of the set of holes or at the location where the previous
        // first-fit search ended. We can stop searching as soon as we find a free hole
        // that is large enough.
        // int indexOfFirstFit = -1;
        // for (int i  = 0; i < memoryBlocks.size(); i++) 
        // {
        //     MemoryBlock block = memoryBlocks.get(i);
        //     if (block.isFree() && block.getSize() >= size && indexOfFirstFit == -1) 
        //     {
        //         indexOfFirstFit = i;

        //     }
        // }
        // if(indexOfFirstFit != -1)
        // {
        //     insertBlockAtSpace(size, indexOfFirstFit, processId);
        // }

        memoryBlocks.stream()
            .filter(x -> x.isFree() && x.getSize() >= newBlockSize)
            .findFirst()
            .ifPresent(block -> insertBlockAtSpace(newBlockSize, memoryBlocks.indexOf(block), processId));
        
    }
    public void insertBlockAtSpace(long newBlockSize, int insertIndex, String processId)
    {
        // This gets a block that exists at this index
        MemoryBlock targetBlock = memoryBlocks.get(insertIndex);
        MemoryBlock newBlock = new MemoryBlock(targetBlock.getStartAddress(), newBlockSize, false, processId);
        
        if(targetBlock.getSize() >= newBlockSize )
        {
            memoryBlocks.add(insertIndex, newBlock);
            targetBlock.setSize(targetBlock.getSize() - newBlockSize);
            targetBlock.setStartAddress(targetBlock.getStartAddress() + newBlockSize);
        } 
        // Change to own method, removeBlock
        // else if (targetBlock.getSize() == newBlockSize)
        // {
        //     memoryBlocks.add(insertIndex, newBlock);
        //     //doesn't work, incorrect index
        //     memoryBlocks.remove(insertIndex + 1);
        //     System.err.println("Used all the space of empty block - removed block ");
        // }
        // else
        // {
        //     System.out.print("Error: Couldn't insert block\n");
        // }
        // Print out memoryBlocks
        System.out.println("Current memory blocks:");
        for (MemoryBlock block : memoryBlocks) 
        {
            int endAddress = (int) (block.getStartAddress() + block.getSize());
            System.out.println(block.getProcessId() + "Address: " + block.getStartAddress() + ":" + endAddress  + " - " + block.getSize()
                                + " bytes" + (block.isFree() ? " (Free)" : " (In Use)"));
        }
    }

    public void bestFit(String processId, int newBlockSize) 
    {
        // Implement best fit logic
        // • Best fit . Allocate the smallest hole that is big enough. We must search the
        // entire list, unless the list is ordered by size. This strategy produces the
        // smallest leftover hole.
        // int indexSmallestFreeBlock = -1;
        // long sizeOfSmallestFreeBlock = -1;
        // for (int i  = 0; i < memoryBlocks.size(); i++) 
        // {
        //     MemoryBlock block = memoryBlocks.get(i);
        //     if(block.isFree() && block.getSize() >= newBlockSize)
        //     {
        //         if(indexSmallestFreeBlock == -1)
        //         {
        //             indexSmallestFreeBlock = i;
        //             sizeOfSmallestFreeBlock = block.getSize();
        //         } 
        //         else
        //         {
        //             if (sizeOfSmallestFreeBlock > block.getSize())
        //             {
        //                 sizeOfSmallestFreeBlock = block.getSize();
        //                 indexSmallestFreeBlock = i;
        //             }
        //         }
        //     }
        // }
        // if (indexSmallestFreeBlock == -1) 
        // {
        //     System.err.println("Error: Unable to find empty block");
        // } 
        // else 
        // {
        //     insertBlockAtSpace(newBlockSize, indexSmallestFreeBlock, processId);
        // }
        memoryBlocks.stream()
            .filter(x -> x.isFree() && x.getSize() >= newBlockSize)
            .min(Comparator.comparing(MemoryBlock::getSize))
            .ifPresent(block -> insertBlockAtSpace(newBlockSize, memoryBlocks.indexOf(block), processId));
    }

    public void worstFit(String processId, int newBlockSize) 
    {
        // Implement worst fit logic
        // • Worst fit. Allocate the largest hole. Again, we must search the entire list,
        // unless it is sorted by size. This strategy produces the largest leftover hole,
        // which may be more useful than the smaller leftover hole from a best-fit
        // approach.
        // int indexLargestFreeBlock = -1;
        // long sizeOfLargestFreeBlock = -1;
        // for (int i  = 0; i < memoryBlocks.size(); i++) 
        // {
        //     MemoryBlock block = memoryBlocks.get(i);
        //     if(block.isFree() && block.getSize() <= newBlockSize)
        //     {
        //         if(indexLargestFreeBlock == -1)
        //         {
        //             indexLargestFreeBlock = i;
        //             sizeOfLargestFreeBlock = block.getSize();
        //         } 
        //         else
        //         {
        //             if (sizeOfLargestFreeBlock < block.getSize())
        //             {
        //                 sizeOfLargestFreeBlock = block.getSize();
        //                 indexLargestFreeBlock = i;
        //             }
        //         }
        //     }
        // }
        // if (indexLargestFreeBlock == -1) 
        // {
        //     System.err.println("Error: Unable to find empty block");

        // } 
        // else 
        // {
        //     insertBlockAtSpace(newBlockSize, indexLargestFreeBlock, processId);
        // }
        memoryBlocks.stream()
            .filter(x -> x.isFree() && x.getSize() >= newBlockSize)
            .max(Comparator.comparing(MemoryBlock::getSize))
            .ifPresent(block -> insertBlockAtSpace(newBlockSize, memoryBlocks.indexOf(block), processId));

    }
    // if  a  partition  being  released  is  adjacent  to  an 
    // existing hole, be sure to combine the two holes into a single hole.
    public void releaseMemory(String processId) 
    {
        // Implement memory release logic
        int index = memoryBlocks.stream()
            .filter(x -> x.getProcessId().equals(processId))
            .findFirst()
            .map(memoryBlocks::indexOf)
            .orElse(-1);
        if (index != -1)
        {
            if (index + 1 < memoryBlocks.size() && memoryBlocks.get(index + 1).isFree()) 
            {
                memoryBlocks.get(index).setSize(memoryBlocks.get(index).getSize() + memoryBlocks.get(index + 1).getSize());
                memoryBlocks.remove(index + 1);
            }

            if (index - 1 < memoryBlocks.size() && memoryBlocks.get(index - 1).isFree()) 
            {
                memoryBlocks.get(index).setSize(memoryBlocks.get(index).getSize() + memoryBlocks.get(index - 1).getSize());
                memoryBlocks.remove(index + 1);
            }
        }
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
        for (MemoryBlock block : memoryBlocks) 
        {
            if (block.isFree()) 
            {
                // Move free block to the end of memoryBlocks
                // Passing in the memoryBlocks.size() as the index will move the block to the end
                // of the list
                insertBlockAtSpace(block.getSize(), memoryBlocks.size(), null);
            }
        }
        System.out.println("Memory compacted.");
    }

    public void reportStatus() 
    {
        System.out.println("Memory Status:");
        for (MemoryBlock block : memoryBlocks) 
        {
            System.out.println(block.getProcessId() + " - " + block.getSize() + " bytes" + (block.isFree() ? " (Free)" : "(In Use)"));
        }
    }

    public void removeBlockAt(Long startAddress) 
    {
        memoryBlocks.removeIf(x -> x.getStartAddress() == startAddress);
    }   

    
}