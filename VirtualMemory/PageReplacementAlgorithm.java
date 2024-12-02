// Programming Problem 10.44 on Page P-51 

// Write  a  program  that  implements  the  FIFO,  LRU,  LFU,  and  optimal  (OPT)  page-
// replacement algorithms presented in Section 10.4. Give the user the option to enter the 
// page-reference string as commas and/or spaces separated or let the program generate 
// one, randomly, with the numbers ranging from 0 to 9. Apply the page-reference string to 
// each  algorithm  and  record  the  number  of  page  faults  incurred  by  each  algorithm.  
// Request the number of page frames from the user.  
// Write  a  public  test  class,  named  Program4.java  to  test  your  classes.    No  input, 
// processing  or  output  should  happen  in  the  main  method.  All  work  should  be 
// delegated to other non-static methods and handle all exceptions where 
// necessary. 
// Allow  the  user  to  run  the  program  as  many  times  as  possible  until  a  sentinel  value  of 
// zero (0) has been entered for the page-reference string. 
// Do  not  use  the  “break” statement or the  “continue” statement,  in  loops,  in  the 
// program.  Also,  do  not  use  the  System.exit()  method  the  program.    You  will  not 
// receive credit for the program if you do.   
// All  classes  in  this  project  must  be  public,  non-static  and  not  nested  in  other 
// classes.  
// Every  method  in  your  program  should  be  limited  to  performing  a  single,  well-
// defined  task,  and  the  name  of  the  method  should  express  that  task  effectively.  
// All  methods  should  be  non-static  unless  it  is  absolutely  necessary  for  it  to  be 
// static.
import java.util.List;

public interface PageReplacementAlgorithm {

    void applyAlgorithm(List<Integer> pageReferenceString, int numberOfFrames);

    int getPageFaults();
}
