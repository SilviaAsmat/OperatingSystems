//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Six
//
//  File Name:     MemoryAllocator.java
//
//  Course:        COSC 4302 - Operating Systems
//
//  Due Date:      11/27/2024
//
//  Java Version:  23.0.1
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       Chapter 9
//
//  Description:   This class will contain the logic for the memory allocator.
//                 It will contain methods to request memory, release memory,
//                 compact memory, and report status.
//
//********************************************************************  
import java.util.*;

public class MemoryAllocator 
{
    private final List<MemoryBlock> memoryBlocks;
    //***************************************************************
    //
    //  Method:       MemoryAllocator
    //
    //  Description:  The constructor of the MemoryAllocator class
    //
    //  Parameters:   long, String
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public MemoryAllocator(long initialSize, String processId) 
    {
        this.memoryBlocks = new ArrayList<>();
        // Initialize with one large free block
        memoryBlocks.add(new MemoryBlock(0, initialSize, true, processId));
    }
    //***************************************************************
    //
    //  Method:       requestMemory
    //
    //  Description:  This method will request memory based on the strategy
    //
    //  Parameters:   String, int, char
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void requestMemory(String processId, int memorySize, char strategy) 
    {
        // Implement memory request logic based on strategy
        //  F - First fit;B - Best fit;W - Worst fit
        // The first parameter to the RQ command is the new process that requires the memory, 
        // followed  by  the  amount  of  memory  being  requested,  and  finally  the  strategy.
        if(memoryBlocks.stream().anyMatch(x -> x.getProcessId().equalsIgnoreCase(processId)))
        {
            System.err.println("Error: Process already exists.");
        }
        else
        {
            switch (strategy) 
                {
                    case 'F', 'f' -> firstFit(processId, memorySize);
                    case 'B', 'b' -> bestFit(processId, memorySize);
                    case 'W', 'w' -> worstFit(processId, memorySize);
                    default -> System.out.println("Invalid strategy.");
                }
        }
        
    }
    //***************************************************************
    //
    //  Method:       firstFit
    //
    //  Description:  This method will implement the first fit strategy
    //
    //  Parameters:   String, int
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void firstFit(String processId, int newBlockSize) 
    {
        memoryBlocks.stream()
            .filter(x -> x.isFree() && x.getSize() >= newBlockSize)
            .findFirst()
            .ifPresentOrElse(
                block -> insertBlockAtSpace(newBlockSize, memoryBlocks.indexOf(block), processId),
                () -> System.err.println("Error: No space available.")
            );
    }
    //***************************************************************
    //
    //  Method:       insertBlockAtSpace
    //
    //  Description:  This method will insert a block at a specific index
    //
    //  Parameters:   long, int, String
    //
    //  Returns:      N/A
    //
    //**************************************************************
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
    }
    //***************************************************************
    //
    //  Method:       compactMemory
    //
    //  Description:  This method will compact the memory blocks, 
    //                moving all the free blocks to one end of the list
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void compactMemory() 
    {
        int lastNotFreeBlockFoundAt = -1;
        int blocksArrayLength = memoryBlocks.size();
        for (int currentIndex = 0; currentIndex < blocksArrayLength; ++currentIndex) 
        {
            // If the current index is not free,
            if (memoryBlocks.get(currentIndex).isFree())
            {
                // Increment the lastNotFreeBlockFoundAt.
                lastNotFreeBlockFoundAt++;
                // Swap the current element with the element at the lastNotFreeBlockFoundAt position.
                MemoryBlock temp = memoryBlocks.get(lastNotFreeBlockFoundAt);
                memoryBlocks.set(lastNotFreeBlockFoundAt, memoryBlocks.get(currentIndex));
                memoryBlocks.set(currentIndex, temp);
            }
        }
        System.out.println("Memory compacted.");
    }
    //***************************************************************
    //
    //  Method:       bestFit
    //
    //  Description:  This method will implement the best fit strategy
    //                to allocate memory, searching for the smallest block
    //                that fits the process
    //
    //  Parameters:   String, int
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void bestFit(String processId, int newBlockSize) 
    {
        memoryBlocks.stream()
            .filter(x -> x.isFree() && x.getSize() >= newBlockSize)
            .min(Comparator.comparing(MemoryBlock::getSize))
            .ifPresent(block -> insertBlockAtSpace(newBlockSize, memoryBlocks.indexOf(block), processId));
    }
    //***************************************************************
    //
    //  Method:       worstFit
    //
    //  Description:  This method will implement the worst fit strategy
    //                to allocate memory, searching for the largest block
    //                that fits the process
    //
    //  Parameters:   String, int
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void worstFit(String processId, int newBlockSize) 
    {
        memoryBlocks.stream()
            .filter(x -> x.isFree() && x.getSize() >= newBlockSize)
            .max(Comparator.comparing(MemoryBlock::getSize))
            .ifPresent(block -> insertBlockAtSpace(newBlockSize, memoryBlocks.indexOf(block), processId));

    }
    //***************************************************************
    //
    //  Method:       releaseMemory
    //
    //  Description:  This method will release memory for a process
    //
    //  Parameters:   String
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void releaseMemory(String processId) 
    {
        int index = memoryBlocks.stream()
            .filter(x -> x.getProcessId().equalsIgnoreCase(processId))
            .findFirst()
            .map(memoryBlocks::indexOf)
            .orElse(-1);
        if (index != -1)
        {
            memoryBlocks.get(index).setFree(true);
            memoryBlocks.get(index).setProcessId("Unused");
            combineBlocks(index);   
        }
        else
        {
            System.err.println("Error: Process not found.");
        }
    }
    //***************************************************************
    //
    //  Method:       combineBlocks
    //
    //  Description:  This method will combine two blocks if they are free
    //
    //  Parameters:   int
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void combineBlocks(int index)
    {
        if (memoryBlocks.get(index + 1).isFree()) 
        {
            memoryBlocks.get(index).setSize(memoryBlocks.get(index).getSize() + memoryBlocks.get(index + 1).getSize());
            memoryBlocks.remove(index + 1);
        }
        if (memoryBlocks.get(index - 1).isFree()) 
        {
            memoryBlocks.get(index).setSize(memoryBlocks.get(index).getSize() + memoryBlocks.get(index - 1).getSize());
            memoryBlocks.remove(index - 1);
        }
    }
    //***************************************************************
    //
    //  Method:       reportStatus
    //
    //  Description:  This method will report the status of the memory
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void reportStatus() 
    {
        System.out.println("Total amount of blocks: " + memoryBlocks.size() + " blocks");
        System.out.println("Current memory blocks:");
        for (MemoryBlock block : memoryBlocks) 
        {
            int endAddress = (int) (block.getStartAddress() + block.getSize() - 1);
            System.out.println("Addresses: [" + block.getStartAddress() + "]:[" + endAddress + "] " + (block.isFree() ? "Process " : "") + block.getProcessId().toUpperCase());
        }
    }  

    
}