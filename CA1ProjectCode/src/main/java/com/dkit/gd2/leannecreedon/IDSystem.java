package com.dkit.gd2.leannecreedon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Help from CA1 starter code on Moodle
 */
public class IDSystem {

    /* Attributes */
    private static IDSystem idSystem;
    private int nextId;
    private final String fileName;

    private IDSystem(String fileName)
    {
        this.fileName = fileName;
        try
        {
            File file = new File(fileName);
            Scanner keyboard = new Scanner(file);

            while (keyboard.hasNext())
            {
                nextId = keyboard.nextInt();
            }

            keyboard.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error reading nextId from file: idSystem.txt");
            e.printStackTrace();
        }
    }

    // Get instance of the class
    public static IDSystem getInstance(String fileName)
    {
        if (idSystem == null)
        {
            idSystem = new IDSystem(fileName);
        }
        return idSystem;
    }

    // Returning ID and saving it to file
    public int getNextId()
    {
        File file = new File(fileName);
        FileWriter fWriter = null;
        try
        {
            fWriter = new FileWriter(file);
            fWriter.write(Integer.toString(nextId+1));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fWriter.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return nextId++;
    }

    public static IDSystem getIdSystem() {
        return idSystem;
    }

    public String getFileName() {
        return fileName;
    }
}
