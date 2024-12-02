//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Six
//
//  File Name:     MemoryBlock.java
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
//  Description:   This class will contain the logic for a memory block.
//                 It will contain the start address, size, whether it is free,
//                 and the process id.
//
//********************************************************************  
public class MemoryBlock 
{
    private long startAddress;
    private long size;
    private boolean isFree;
    private String processId;
    //***************************************************************
    //
    //  Method:       MemoryBlock
    //
    //  Description:  The constructor of the MemoryBlock class
    //
    //  Parameters:   long, long, boolean, String
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public MemoryBlock(long startAddress, long size, boolean isFree, String processId) 
    {
        this.startAddress = startAddress;
        this.size = size;
        this.isFree = isFree;
        this.processId = processId;
    }
    //***************************************************************
    //
    //  Method:       getStartAddress
    //
    //  Description:  This method will return the start address of the memory block
    //
    //  Parameters:   None
    //
    //  Returns:      long
    //
    //**************************************************************
    public long getStartAddress() 
    {
        return startAddress;
    }
    //***************************************************************
    //
    //  Method:       setStartAddress
    //
    //  Description:  This method will set the start address of the memory block
    //
    //  Parameters:   long
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void setStartAddress(long startAddress) 
    {
        this.startAddress = startAddress;
    }
    //***************************************************************
    //
    //  Method:       getSize
    //
    //  Description:  This method will return the size of the memory block
    //
    //  Parameters:   None
    //
    //  Returns:      long
    //
    //**************************************************************
    public long getSize() 
    {
        return size;
    }
    //***************************************************************
    //
    //  Method:       setSize
    //
    //  Description:  This method will set the size of the memory block
    //
    //  Parameters:   long
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void setSize(long size) 
    {
        this.size = size;
    }
    //***************************************************************
    //
    //  Method:       isFree
    //
    //  Description:  This method will return whether the memory block is free
    //
    //  Parameters:   None
    //
    //  Returns:      boolean
    //
    //**************************************************************
    public boolean isFree() 
    {
        return isFree;
    }
    //***************************************************************
    //
    //  Method:       setFree
    //
    //  Description:  This method will set whether the memory block is free
    //
    //  Parameters:   boolean
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void setFree(boolean free) 
    {
        isFree = free;
    }
    //***************************************************************
    //
    //  Method:       getProcessId
    //
    //  Description:  This method will return the process id of the memory block
    //
    //  Parameters:   None
    //
    //  Returns:      String
    //
    //**************************************************************
    public String getProcessId() 
    {
        return processId;
    }
    //***************************************************************
    //
    //  Method:       setProcessId
    //
    //  Description:  This method will set the process id of the memory block
    //
    //  Parameters:   String
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void setProcessId(String processId) 
    {
        this.processId = processId;
    }
}