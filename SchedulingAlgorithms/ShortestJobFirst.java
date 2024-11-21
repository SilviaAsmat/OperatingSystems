//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     Three
//
//  File Name:     ShortestJobFirst.java
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
//  Description:   This class implements the Shortest-Job-First (SJF) scheduling algorithm.
//
//********************************************************************
import java.util.List;

public class ShortestJobFirst implements Algorithm{
    private List<Task> queue;
    private double turnaroundTime = 0;
    private double waitingTime = 0;
    private int timePassed = 0;
    private double avgTurnaroundTime = 0;
    private double avgWaitingTime = 0;
    private int numOfTasks;
    //***********************************************************************
    //
    //  Method:       ShortestJobFirst
    //
    //  Description:  Constructor that initializes the queue.
    //
    //  Parameters:   List<Task> queue
    //
    //  Returns:      None
    //
    //***********************************************************************
    public ShortestJobFirst(List<Task> queue)
    {
        this.queue = queue;
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
        numOfTasks = queue.size();
        while((task = pickNextTask()) != null)
        {
            CPU.run(task, task.getBurst());
            turnaroundTime = task.getBurst() + timePassed;
            waitingTime = timePassed;
            timePassed += task.getBurst();
            avgTurnaroundTime += turnaroundTime;
            avgWaitingTime += waitingTime;
            printTaskStats(task);
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
        System.out.println("Task " + task.getTid() + " has finished executing.");
        System.out.println("Waiting time is " + waitingTime);
        System.out.println("Turnaround time is " + turnaroundTime + "\n");
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
    //***************************************************************
    //
    //  Method:       pickNextTask
    //
    //  Description:  This method selects the next task to run
    //
    //  Parameters:   None
    //
    //  Returns:      Task
    //
    //***************************************************************
    @Override
    public Task pickNextTask()
    {
        if(queue.isEmpty())
        {
            return null;
        }
        Task shortestJob = queue.get(0);
        for(Task task : queue)
        {
            if(task.getBurst() < shortestJob.getBurst())
            {
                shortestJob = task;
            }
        }
        queue.remove(shortestJob);
        return shortestJob;
    }
}

