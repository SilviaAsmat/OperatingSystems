public class MemoryBlock 
{
    private long startAddress;
    private long size;
    private boolean isFree;
    private String processId;

    public MemoryBlock(long startAddress, long size, boolean isFree, String processId) 
    {
        this.startAddress = startAddress;
        this.size = size;
        this.isFree = isFree;
        this.processId = processId;
    }

    public long getStartAddress() 
    {
        return startAddress;
    }

    public void setStartAddress(long startAddress) 
    {
        this.startAddress = startAddress;
    }

    public long getSize() 
    {
        return size;
    }

    public void setSize(long size) 
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