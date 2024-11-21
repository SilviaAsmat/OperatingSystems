//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Three
//
//  File Name:     Priority.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      10/20/2024
//
//  Java Version:  17.0.12
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       Chapter 5
//
//  Description:   This class implements the Priority Round Robin scheduling algorithm.
//
//********************************************************************
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PriorityRoundRobin implements Algorithm{
    private List<Task> taskList;
    private int timeQuantum = 5;
    private double timePassed = 0;
    private double avgTurnaroundTime = 0;
    private double avgWaitingTime = 0;
    private double waitingTime = 0;
    private HashMap<Integer, Integer> taskBurstTimes = new HashMap<>();
    //***********************************************************************
    //
    //  Method:       PriorityRoundRobin
    //
    //  Description:  Constructor that initializes the queue.
    //
    //  Parameters:   List<Task> queue
    //
    //  Returns:      None
    //
    //***********************************************************************
    public PriorityRoundRobin(List<Task> queue) {
        taskList = queue.stream()
                        .filter(task -> task.getPriority() <= 8 && task.getPriority() >= 1)
                        .collect(Collectors.toList());
    }
    //***********************************************************************
    //
    //  Method:       schedule
    //
    //  Description:  This method schedules and runs the tasks in the queue.
    //
    //  Parameters:   None
    //
    //  Returns:      None
    //
    //***********************************************************************
    @Override
    public void schedule() {
        Task task = null;
        sortTasksByPriority(taskList);
        int numOfTasks = taskList.size();
        while((task = pickNextTask()) != null) 
        {
            CPU.run(task, task.getBurst());
            // Check if round robin is needed and update burst time
            if (taskList.size() == 0 || task.getPriority() != taskList.get(0).getPriority())
            {
                // Process entire task
                timePassed += task.getBurst();
                taskBurstTimes.put(task.getTid(), taskBurstTimes.getOrDefault(task.getTid(), 0) + task.getBurst());
                task.setBurst(0);
            }
            else
            {
                // Process via round robin
                if(task.getBurst() > timeQuantum)
                {
                    timePassed += timeQuantum;
                    taskBurstTimes.put(task.getTid(), taskBurstTimes.getOrDefault(task.getTid(), 0) + timeQuantum);
                    task.setBurst(task.getBurst() - timeQuantum);
                }
                else
                {
                    timePassed += task.getBurst();
                    taskBurstTimes.put(task.getTid(), taskBurstTimes.getOrDefault(task.getTid(), 0) + task.getBurst());
                    task.setBurst(0);
                }
            }
            if(task.getBurst() == 0)
            {
                waitingTime = timePassed - taskBurstTimes.get(task.getTid());
                avgTurnaroundTime += timePassed;
                avgWaitingTime += waitingTime;
                printTaskStats(task);
            }
        }
        printTasksAverages(numOfTasks);
    }
    //***********************************************************************
    //
    //  Method:       printTaskStats
    //
    //  Description:  This method prints the waiting and turnaround times of the task.
    // 
    //  Parameters:   Task task
    //
    //  Returns:      None
    //
    //***********************************************************************
    public void printTaskStats(Task task){
        System.out.println("Task " + task.getTid() + " has finished executing.");
        System.out.println("Waiting time is " + waitingTime);
        System.out.println("Turnaround time is " + timePassed + "\n");
    }
    //***********************************************************************
    //
    //  Method:       printTasksAverages
    //
    //  Description:  This method calculaates and prints the 
    //                average waiting and turnaround times.
    //
    //  Parameters:   int numOfTasks
    //
    //  Returns:      None  
    //
    //***********************************************************************
    public void printTasksAverages(int numOfTasks){
        System.out.println("\nAverage waiting time is " + avgWaitingTime/numOfTasks);
        System.out.println("Average turnaround time is " + (avgTurnaroundTime/numOfTasks) + "\n");
    }
    //***********************************************************************
    //
    //  Method:       pickNextTask
    //
    //  Description:  This method picks the next task to be executed.
    //
    //  Parameters:   None
    //
    //  Returns:      Task
    //
    //***********************************************************************
    @Override
    public Task pickNextTask() {
        Task currentTask;
        if(taskList.isEmpty())
        {
            currentTask = null;
        }
        else if(taskList.size() == 1)
        {
            currentTask = taskList.get(0);
            taskList.remove(0);
        }
        else
        {
            // Check if there is one instance of the priority at 0
            if(taskList.get(0).getPriority() != taskList.get(1).getPriority())
            {
                currentTask = taskList.get(0);
                 taskList.remove(currentTask);
            }
            // More than one instance of same priority, Round Robin
            else
            {
                currentTask = taskList.remove(0);
                if(currentTask.getBurst() > timeQuantum)
                {
                    taskList.add(findNextPriorityIndex(currentTask.getPriority()),currentTask);
                } 
            }   
        }      
        return currentTask;
    }// end of pickNextTask
    //***********************************************************************
    //
    //  Method:       sortTasksByPriority
    //
    //  Description:  This method sorts the tasks by priority.
    //
    //  Parameters:   List<Task> taskList
    //
    //  Returns:      None
    //
    //***********************************************************************
    public void sortTasksByPriority(List<Task> taskList)
    {
        Collections.sort(taskList, new Comparator<Task>() 
        {
            @Override
            public int compare(Task t1, Task t2) 
            {
                int result = 0;
                if(t1.getPriority() < t2.getPriority())
                {
                    result = 1;
                }
                else if(t1.getPriority() > t2.getPriority())
                {
                    result = -1;
                }
                else
                {
                    result = 0;
                }
                return result;
            }// end of compare
        });
    }
    //***********************************************************************
    //
    //  Method:       findNextPriorityIndex
    //
    //  Description:  This method finds the index of the next priority.
    //
    //  Parameters:   int priority
    //
    //  Returns:      int
    //
    //***********************************************************************
    public int findNextPriorityIndex(int priority)
    {
        int index = 1;
        while(index < taskList.size() && taskList.get(index).getPriority() == priority)
        {
            index++;
        }
        return index;
    }
}// end of class

