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
//  Description:   This class implements the Priority scheduling algorithm.
//
//********************************************************************
import java.util.*;
import java.util.stream.Collectors;

public class Priority implements Algorithm{
    private List<Task> queue;
    private double startTime = 0;
    private double waitingTime = 0;
    private double turnaroundTime = 0;
    private double avgTurnaroundTime = 0;
    private double avgWaitingTime = 0;
    private int numOfTasks = 0;
    //***********************************************************************
    //
    //  Method:       Priority
    //
    //  Description:  Constructor that initializes the queue.
    //
    //  Parameters:   List<Task> queue
    //
    //  Returns:      None
    //
    //***********************************************************************
    public Priority(List<Task> queue) {
        this.queue = queue.stream()
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
        numOfTasks = queue.size();
        while((task = pickNextTask()) != null)
        {
            CPU.run(task, task.getBurst());
            turnaroundTime = task.getBurst() + startTime;
            waitingTime = turnaroundTime - task.getBurst();
            startTime += task.getBurst();
            printTaskStats(task);
            avgTurnaroundTime += turnaroundTime;
            avgWaitingTime += waitingTime;
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
    public void printTasksAverages(int numOfTasks){
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
    public Task pickNextTask() {
        Task currentTask;
        if(queue.isEmpty())
        {
            currentTask = null;
        }
        else
        {
            currentTask = queue.get(0);
            for(Task task : queue)
            {
                if(task.getPriority() > currentTask.getPriority())
                {
                    currentTask = task;
                }
            }
            queue.remove(currentTask);
        }
        
        return currentTask;
    }
}
