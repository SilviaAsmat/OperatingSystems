public class Process {
    private String processId;
    private int memorySize;

    public Process(String processId, int memorySize) {
        this.processId = processId;
        this.memorySize = memorySize;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }
}