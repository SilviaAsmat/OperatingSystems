//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Three
//
//  File Name:     RoundRobin.java
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
//  Description:   This class implements the Round Robin scheduling algorithm.
//
//********************************************************************
import java.util.HashMap;
import java.util.List;
public class RoundRobin implements Algorithm
{
    private List<Task> taskList;
    private int timeQuantum = 5;
    private double waitingTime = 0;
    private double turnaroundTime = 0;
    private double avgTurnaroundTime = 0;
    private double avgWaitingTime = 0;
    private int numOfTasks;
    private int count = 0;
    private HashMap<Integer, Integer> taskOccurences = new HashMap<>();
    //***********************************************************************
    //
    //  Method:       RoundRobin
    //
    //  Description:  Constructor that initializes the queue.
    //
    //  Parameters:   List<Task> queue
    //
    //  Returns:      None
    //
    //***********************************************************************
    public RoundRobin(List<Task> queue) 
    {
        this.taskList = queue;
    }
    //***********************************************************************
    //
    //  Method:       schedule
    //
    //  Description:  This method runs the tasks in the queue as retrieved by pickNextTask.
    //
    //  Parameters:   None
    //
    //  Returns:      None
    //
    //***********************************************************************
    @Override
    public void schedule() 
    {
        Task task = null;
        numOfTasks = taskList.size();
        while((task = pickNextTask()) != null)
        {
            int runTime = timeQuantum;
            if(task.getBurst() < timeQuantum)
            {
                runTime = task.getBurst();
            }
            taskOccurences.put(task.getTid(), taskOccurences.getOrDefault(task.getTid(), 0) + 1);
            CPU.run(task, runTime);
            count++;
            if(task.getBurst() == 0 )
            {
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
    public void printTaskStats(Task task)
    {
        turnaroundTime = count * timeQuantum + task.getBurst();
        waitingTime = turnaroundTime - (taskOccurences.get(task.getTid()) * timeQuantum) + task.getBurst();
        System.out.println("Task " + task.getTid() + " has finished executing.");
        System.out.println("Waiting time is " + waitingTime);
        System.out.println("Turnaround time is " + turnaroundTime + "\n");
        avgTurnaroundTime += turnaroundTime;
        avgWaitingTime += waitingTime;
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
    public void printTasksAverages(int numOfTasks)
    {
        System.out.println("\nAverage waiting time is " + (avgWaitingTime / numOfTasks));
        System.out.println("Average turnaround time is " + (avgTurnaroundTime / numOfTasks) + "\n");
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
    public Task pickNextTask() 
    {
        Task currentTask;
        Task tempTask;
        if(taskList.isEmpty()) 
        {
            currentTask = null;
        }
        else
        {
            currentTask = taskList.get(0);
            tempTask = currentTask; 
            taskList.remove(0);
            if(currentTask.getBurst() > timeQuantum)
            {
                tempTask.setBurst(tempTask.getBurst() - timeQuantum);
                taskList.add(tempTask);
            }
            else
            {
                currentTask.setBurst(0);
            }
        }
        return currentTask;
    }
}
