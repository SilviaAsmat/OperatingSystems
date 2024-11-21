//********************************************************************
//
//  Author:        Silvia Asmat
//
//  Program #:     One
//
//  File Name:     Program1.java
//
//  Course:        COSC 4302 - Operating Systems
//
//  Due Date:      09/22/2024
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       Chapter 2,3
//
//  Description:   Copies the contents of a file to another file
//
//********************************************************************
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.IOException;
public class Program1
{
    private String sourceFileName;
    private String destinationFileName;
    //***************************************************************
    //
    //  Method:       main
    // 
    //  Description:  The main method of the program
    //
    //  Parameters:   String array
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    public static void main(String[] args)
    {  
		// Create an object of the main class and
        // use it to call the non-static methods
		Program1 obj = new Program1();
        obj.developerInfo();   
        obj.getFileNames();
        obj.copyFile();
    } // End of the main method
    //***************************************************************
    //
    //  Method:       getFileNames
    //
    //  Description:  This method prompts the user to enter the source
    //                and destination file names. It checks if the source
    //                file exists and can be read. 
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public Boolean getFileNames() 
    {
        boolean fileExists = false;
        Scanner scan = new Scanner(System.in); // Create a Scanner object        
        System.out.println("Enter source file name: ");
        sourceFileName = scan.nextLine(); // Read user input
        try (FileInputStream in = new FileInputStream(sourceFileName)) 
        {   // If no exception, the file exists and can be read
            System.out.println("Enter destination file name: ");
            destinationFileName = scan.nextLine();// Read user input
            scan.close();
            fileExists = true;
        } catch (FileNotFoundException e) 
        {
            System.out.println("Source file not found");
        } catch (IOException e) 
        {
            System.out.println("An error occurred while closing the file");
        }
        return fileExists;
    }
    //***************************************************************
    //
    //  Method:       copyFile
    //
    //  Description:  This method reads the contents of the source file
    //                and writes it to the destination file.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
    public void copyFile()
    {
        //This method reads the contents of the source file and writes it to the
        //destination file.
        try (FileInputStream sourceFile = new FileInputStream(sourceFileName);
            FileOutputStream destinationFile = new FileOutputStream(destinationFileName)) 
        {
            int byteRead;
            while ((byteRead = sourceFile.read()) != -1) 
            {
                destinationFile.write(byteRead);
            }
            System.out.println("File Copied");
        } catch (IOException e) 
        {
            System.out.println("An error occurred while reading or writing the file");
        }
    }
	//***************************************************************
    //
    //  Method:       developerInfo
    // 
    //  Description:  The developer information method of the program
	//                This method and comments must be included in all
	//                programming assignments.
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
    public void developerInfo()
    {
       System.out.println("Name:     Silvia Asmat");
       System.out.println("Course:   COSC 4302 - Operating Systems");
       System.out.println("Program:  One");
	   System.out.println("Due Date: 09/22/2024\n");
    } // End of the developerInfo method
}