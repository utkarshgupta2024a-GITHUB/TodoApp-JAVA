import java.io.*;
import java.util.*;

public class TodoApp {
    private static final String FILE_PATH = "data/tasks.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> tasks = loadTasks();

        while (true) {
            System.out.println("\n--- TO-DO LIST ---");
            System.out.println("1. View Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Remove Task");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewTasks(tasks);
                case 2 -> addTask(scanner, tasks);
                case 3 -> removeTask(scanner, tasks);
                case 4 -> {
                    saveTasks(tasks);
                    System.out.println("Tasks saved. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static List<String> loadTasks() {
        List<String> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String task;
            while ((task = br.readLine()) != null) {
                tasks.add(task);
            }
        } catch (IOException ignored) {}
        return tasks;
    }

    private static void saveTasks(List<String> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String task : tasks) {
                bw.write(task);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static void viewTasks(List<String> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void addTask(Scanner scanner, List<String> tasks) {
        System.out.print("Enter task: ");
        String task = scanner.nextLine();
        tasks.add(task);
        System.out.println("Task added!");
    }

    private static void removeTask(Scanner scanner, List<String> tasks) {
        viewTasks(tasks);
        if (!tasks.isEmpty()) {
            System.out.print("Enter task number to remove: ");
            int num = scanner.nextInt();
            scanner.nextLine();
            if (num > 0 && num <= tasks.size()) {
                tasks.remove(num - 1);
                System.out.println("Task removed!");
            } else {
                System.out.println("Invalid task number.");
            }
        }
    }
}

