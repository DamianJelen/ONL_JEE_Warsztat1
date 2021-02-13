package pl.coderslab.warsztat1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class firstProject {
    public static void main(String[] args) throws IOException {
        Scanner write = new Scanner(System.in);

        String[][] tasksArr = tasks("warsztat1_Dzien5/tasks.csv");
        optionsMenu();
        String tmpOption = write.next().toLowerCase();

        while (!tmpOption.equals("exit")) {

            if (tmpOption.equals("add")) {
                //dodanie zadania
                tasksArr = addTask(tasksArr);
            } else if (tmpOption.equals("remove")) {
                //usuniecie zadania
                tasksArr = remTask(tasksArr);
            } else if (tmpOption.equals("list")) {
                //pokaż listę zadań
                tasksList(tasksArr);
            } else {
                System.out.println(ConsoleColors.RED + "Select one of list options." + ConsoleColors.RESET);
            }

            optionsMenu();
            tmpOption = write.next();
        }
        savaData("warsztat1_Dzien5/tasks.csv", tasksArr);
        System.out.println(ConsoleColors.RED + "Bye, bye." + ConsoleColors.RESET);
    }

    private static void savaData(String fileName, String[][] tasksArr) throws IOException {
        FileWriter fWriter = new FileWriter(fileName, false);
        for (int i = 0; i < tasksArr.length; i++) {
            if (i < tasksArr.length - 1) {
                fWriter.append(Arrays.toString(tasksArr[i]).substring(1, Arrays.toString(tasksArr[i]).length() - 1) + "\n");
            } else {
                fWriter.append(Arrays.toString(tasksArr[i]).substring(1, Arrays.toString(tasksArr[i]).length() - 1));
            }
        }
        fWriter.close();
    }

    private static String[][] remTask(String[][] taskArr) throws InputMismatchException, NoSuchElementException, IllegalStateException {
        Scanner write = new Scanner(System.in);

        while (true) {
            System.out.print(ConsoleColors.RED + "Check position to remove from 0 to " + (taskArr.length - 1) + ": " + ConsoleColors.RESET);
            int i = write.nextInt();
            if (i >= 0 && i < taskArr.length - 1) {
                for (int j = i; j < taskArr.length - 1; j++) {
                    taskArr[j] = Arrays.copyOf(taskArr[j + 1], taskArr[j + 1].length);
                }
                return Arrays.copyOf(taskArr, taskArr.length - 1);
            } else if (i == taskArr.length - 1) {
                //usun tylko ostatni wiersz z tablicy
                return Arrays.copyOf(taskArr, taskArr.length - 1);
            } else if (!(i / 1 == i)) {
                System.out.println(ConsoleColors.RED + "It not number, please write witch position of the tasks list you want remmove." + ConsoleColors.RESET);
                tasksList(taskArr);
                System.out.print(ConsoleColors.RED + "Check position to remove from 0 to " + (taskArr.length - 1) + ": " + ConsoleColors.RESET);
            } else if (i < 0 && i >= taskArr.length) {
                System.out.println(ConsoleColors.RED + "There is no such item on the tasks list." + ConsoleColors.RESET);
                tasksList(taskArr);
                System.out.print(ConsoleColors.RED + "Check position to remove from 0 to " + (taskArr.length - 1) + ": " + ConsoleColors.RESET);
            }
        }
    }

    private static String[][] addTask(String[][] taskArr) {
        taskArr = addRowArr(taskArr);
        Scanner write = new Scanner(System.in);

        System.out.println("Pleas add task description");
        taskArr[taskArr.length - 1][0] = write.nextLine();

        System.out.println("Please add task due date");
        taskArr[taskArr.length - 1][1] = write.nextLine();

        System.out.println("Is your task is important: true/false");
        taskArr[taskArr.length - 1][2] = write.nextLine();

        return taskArr;
    }

    private static void tasksList(String[][] taskArr) {
        for (int i = 0; i < taskArr.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < taskArr[i].length; j++) {
                System.out.print(taskArr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void optionsMenu() {
        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }

    public static String[][] tasks (String fileName) throws IOException {
        String[][] tasksArr = new String[0][1];
        File plik = new File(fileName);
        Scanner nLine = new Scanner(plik);

        while (nLine.hasNext()) {
            tasksArr = Arrays.copyOf(tasksArr, tasksArr.length + 1);
            tasksArr[tasksArr.length - 1] = nLine.nextLine().split(", ");
        }

        return tasksArr;
    }

    public static String[][] addRowArr (String[][] dArr) {
//        System.out.println("Przed dodaniem wiersza: " + dArr.length + ", " + dArr[dArr.length - 1].length);
        dArr = Arrays.copyOf(dArr, (dArr.length + 1));
//        System.out.println("Po dodaniu wiersza: " + dArr.length);// + ", " + dArr[dArr.length - 1].length);
        dArr[dArr.length - 1] = new String[3];
//        System.out.println("Po dodaniu kolumn w nowym wierszu: " + dArr.length + ", " + dArr[dArr.length - 1].length);
        return dArr;
    }
}
