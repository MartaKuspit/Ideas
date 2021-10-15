package pl.markon.ideas.input;

import java.util.Scanner;

public class UserInputManager {
    private Scanner scanner;

    public UserInputManager() {
        scanner = new Scanner(System.in);
    }
    public UserInputCommand nextCommand() {
        return new UserInputCommand (scanner.nextLine());
    }
}
