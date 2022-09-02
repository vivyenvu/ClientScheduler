package FileIOmain;

import java.io.*;
import java.util.Scanner;

public class FileIOmain {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String filename = "login_activity.txt", item;

        Scanner keyboard = new Scanner(System.in);

        System.out.print("How many items do you have? ");
        int numItems = keyboard.nextInt();

        keyboard.nextLine();

        PrintWriter outputFile = new PrintWriter(filename);

        for(int i=0; i<numItems; i++){
            System.out.println("Enter item "+ (i+1) + ": ");
            item = keyboard.nextLine();
            outputFile.println(item);
        }

        outputFile.close();
        System.out.println("File written!");
    }
}
