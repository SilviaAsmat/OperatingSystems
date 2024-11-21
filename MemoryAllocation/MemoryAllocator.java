import java.util.*;

public class MemoryAllocator {
    private List<MemoryBlock> memoryBlocks;
    private int totalMemory;

    public MemoryAllocator(int totalMemory) {
        this.totalMemory = totalMemory;
        this.memoryBlocks = new ArrayList<>();
        // Initialize with one large free block
        memoryBlocks.add(new MemoryBlock(0, totalMemory, true, null));
    }

    public void requestMemory(String processId, int size, char strategy) {
        // Implement memory request logic based on strategy
    }

    public void releaseMemory(String processId) {
        // Implement memory release logic
    }

    public void compactMemory() {
        // Implement memory compaction logic
    }

    public void reportStatus() {
        // Implement status reporting logic
    }
}