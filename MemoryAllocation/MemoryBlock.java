public class MemoryBlock {
    private int startAddress;
    private int size;
    private boolean isFree;
    private String processId;

    public MemoryBlock(int startAddress, int size, boolean isFree, String processId) 
    {
        this.startAddress = startAddress;
        this.size = size;
        this.isFree = isFree;
        this.processId = processId;
    }

    public int getStartAddress() 
    {
        return startAddress;
    }

    public void setStartAddress(int startAddress) 
    {
        this.startAddress = startAddress;
    }

    public int getSize() 
    {
        return size;
    }

    public void setSize(int size) 
    {
        this.size = size;
    }

    public boolean isFree() 
    {
        return isFree;
    }

    public void setFree(boolean free) 
    {
        isFree = free;
    }

    public String getProcessId() 
    {
        return processId;
    }

    public void setProcessId(String processId) 
    {
        this.processId = processId;
    }
}