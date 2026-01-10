package com.tasktracker;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TaskTracker {
    private static final String FILE_PATH="tasks.json";
    private static final ObjectMapper mapper=new ObjectMapper().registerModule(new JavaTimeModule());

    public static void main(String[] args)
    {
        if(args.length==0)
        {
            System.out.println("Usage: task-cli <command> [arguments]");
            return;
        }
        String action=args[0];
        List<Task> tasks= loadTasks();

        try
        {
            switch (action)
            {
            case "add" ->
            {
            String desc = args[1];
            String status=args[2];
            addTask(tasks,desc,status);
            }
            case "update" -> updateTask(tasks,Integer.parseInt(args[1]), args[2]);
            case "delete" -> deleteTask(tasks,Integer.parseInt(args[1]));
            case "list"   -> listTasks(tasks,args.length > 1 ? args[1] : "all");
            case "mark-in-progress","mark-done" -> changeStatus(tasks,Integer.parseInt(args[1]),action);
            default       -> System.out.println("Unknown command!");
            }
        }
        catch(Exception e)
        {
            System.err.println("Error: Invalid arguments for"+ action);
        }
    }

    private static void addTask(List<Task> tasks,String desc,String status)
    {
        int id=tasks.isEmpty() ? 1: tasks.get(tasks.size()-1).getId()+1;
        tasks.add(new Task(id,desc,status));
        saveTasks(tasks);
        System.out.println("Tasks added successfully (ID: " + id + ")");
    }

    private static void listTasks(List<Task> tasks,String filter)
    {
        System.out.println("ID | Status |Description|Created At");
        System.out.println("-----------------------");
        tasks.stream()
            .filter(t -> filter.equalsIgnoreCase("all")||t.getStatus().equalsIgnoreCase(filter))
            .forEach(t ->System.out.printf("%-3d | %-11s |%s%n",t.getId(),t.getStatus(),t.getDescription()));
    }

    private static void changeStatus(List<Task> tasks,int id,String action)
    {
        String newStatus=action.replace("mark-","");
        tasks.stream().filter(t -> t.getId()==id).findFirst().ifPresent(t -> t.setStatus(newStatus));
        saveTasks(tasks);
        System.out.println("Task" + id + "updated to" + newStatus);
    }

    private static void updateTask(List<Task> tasks, int id, String newDescription) 
    {
    tasks.stream()
        .filter(t -> t.getId() == id)
        .findFirst()
        .ifPresentOrElse(
            t -> {
                t.setDescription(newDescription);
                saveTasks(tasks);
                System.out.println("Task " + id + " updated successfully.");
            },
            () -> System.out.println("Error: Task " + id + " not found.")
        );
    }

    private static void deleteTask(List<Task> tasks, int id)
    {
    boolean removed = tasks.removeIf(t -> t.getId() == id);
    if (removed)
        {
        saveTasks(tasks);
        System.out.println("Task " + id + " deleted successfully.");
        }
    else
        {
        System.out.println("Error: Task " + id + " not found.");
        }
    }

    private static void saveTasks(List<Task>tasks)
    {
        try
        {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH),tasks);
        }
        catch(Exception e)
        {
            System.err.println("Error saving tasks:" +e.getMessage());
        }
    }

    private static List<Task> loadTasks()
    {
        try
        {
            File file=new File(FILE_PATH);
            if(!file.exists())
            {
                return new ArrayList<>();
            }
                return new ArrayList<>(Arrays.asList(mapper.readValue(file,Task[].class)));
        }
        catch (Exception e)
        {
            return new ArrayList<>();
        }
    }
}

