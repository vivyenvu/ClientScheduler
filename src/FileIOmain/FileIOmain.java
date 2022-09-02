package FileIOmain;

import java.util.Scanner;

public class FileIOmain {

    public static void main(String[] args) {
        String filename = "login_activity.txt", item;

        Scanner keyboard = new Scanner(System.in);

        System.out.print("How many items do you have? ");
        int numItems = keyboard.nextInt();

        keyboard.nextLine();
    }
}
