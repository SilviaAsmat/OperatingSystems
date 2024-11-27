import java.util.*;

public class MemoryAllocator 
{
    private final List<MemoryBlock> memoryBlocks;

    public MemoryAllocator(long initialSize, String processId) 
    {
        this.memoryBlocks = new ArrayList<>();
        // Initialize with one large free block
        memoryBlocks.add(new MemoryBlock(0, initialSize, true, processId));
    }

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

    // Change to return boolean?
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
    public void compactMemory() 
    {
        int lastNotFreeBlockFoundAt = -1;
        int blocksArrayLength = memoryBlocks.size();
        for (int currentIndex = 0; currentIndex < blocksArrayLength; ++currentIndex) 
        {
            // If the current element is not zero,
            if (memoryBlocks.get(currentIndex).isFree())
            {
                // Increment the lastNonZeroFoundAt.
                lastNotFreeBlockFoundAt++;
                // Swap the current element with the element at the lastNonZeroFoundAt position.
                MemoryBlock temp = memoryBlocks.get(lastNotFreeBlockFoundAt);
                memoryBlocks.set(lastNotFreeBlockFoundAt, memoryBlocks.get(currentIndex));
                memoryBlocks.set(currentIndex, temp);
            }
        }
        System.out.println("Memory compacted.");
    }

    public void bestFit(String processId, int newBlockSize) 
    {
        memoryBlocks.stream()
            .filter(x -> x.isFree() && x.getSize() >= newBlockSize)
            .min(Comparator.comparing(MemoryBlock::getSize))
            .ifPresent(block -> insertBlockAtSpace(newBlockSize, memoryBlocks.indexOf(block), processId));
    }

    public void worstFit(String processId, int newBlockSize) 
    {
        memoryBlocks.stream()
            .filter(x -> x.isFree() && x.getSize() >= newBlockSize)
            .max(Comparator.comparing(MemoryBlock::getSize))
            .ifPresent(block -> insertBlockAtSpace(newBlockSize, memoryBlocks.indexOf(block), processId));

    }
    // if  a  partition  being  released  is  adjacent  to  an 
    // existing hole, be sure to combine the two holes into a single hole.
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