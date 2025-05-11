//Imports
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Main
{
    //Saving and Loading Tasks to and from a file
    static void saveTasksToFile()
    {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("tasks.txt"));
            for (String t : tasks) {
                bw.write(t);
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException e)
        {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    static void loadTasksFromFile()
    {
        try
        {
            File file = new File("tasks.txt");
            if (file.exists())
            {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null)
                {
                    tasks.add(line);
                }
                br.close();
            }
        }
        catch (IOException e)
        {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    //List for Tasks
    static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args)
    {
        //Loading Tasks from File
        loadTasksFromFile();

        // Welcome Message
        System.out.println("To-Do Application\n");

        //Scanner Object
        Scanner scanner = new Scanner(System.in);

        while(true) {
            menuScreen();
            int userInput = scanner.nextInt();
            // Checking for User input
            switch (userInput) {
                case 1:
                    System.out.println("You chose: Add Task");
                    scanner.nextLine();
                    System.out.println("Enter task description: ");
                    String task = scanner.nextLine();
                    tasks.add(task);
                    System.out.println("Task added: " + task);
                    saveTasksToFile();
                    break;
                case 2:
                    System.out.println("You chose: Remove Task");
                    System.out.println("Enter the number for the task you want to remove: ");
                    int removeNumber = scanner.nextInt();
                    System.out.println("Please confirm by writing CONFIRM");
                    scanner.nextLine();
                    String removeConfirm = scanner.nextLine();
                    while (!Objects.equals(removeConfirm, "CONFIRM")) {
                        System.out.println("Please try again by typing CONFIRM");
                        removeConfirm = scanner.nextLine();
                        if (Objects.equals(removeConfirm, "CONFIRM")) {
                            System.out.println("Task " + removeNumber + " was successfully removed!");
                        }
                    }
                    tasks.remove(removeNumber - 1);
                    saveTasksToFile();
                    break;
                case 3:
                    System.out.println("You chose:  Show Tasks");
                    if (tasks.isEmpty())
                        System.out.println("Currently no tasks on the list!");
                    else {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println("Task " + (i + 1) + ':' + tasks.get(i));
                        }
                    }
                    break;
                case 4:
                    System.out.println("You chose: End Session");
                    System.exit(0);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + userInput);
            }
        }
    }

    public static void menuScreen()
    {
        System.out.println("""
                Choose your desired option:
                1. Add Task
                2. Remove Task
                3. Show Tasks
                4. End Session
                """);
    }
}
