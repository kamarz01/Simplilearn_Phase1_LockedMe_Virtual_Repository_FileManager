package com.kamar.lockedme.logic;

import com.kamar.lockedme.Constants;
import com.kamar.lockedme.enums.ConsoleColors;
import com.kamar.lockedme.enums.Screens;
import com.kamar.lockedme.io.Directory;
import com.kamar.lockedme.io.FileManager;
import com.kamar.lockedme.utils.Printer;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.util.Scanner;

public class UserInputManager {

    public static void handleUserInput(){
        UserInputManager usrInManager = new UserInputManager();
        Scanner userInputScanner = new Scanner(System.in);
        int userInput = userInputScanner.nextInt();
        switch (Constants.CURRENT_SCREEN){
            case WelcomeScreen:
                usrInManager.handleWelcomeScreen(userInput);
                break;
            case MoreOptionsScreen:
                usrInManager.handleMoreOptionsScreen(userInput);
                break;
        }
        Initializer.handleLoop();
    }

    public void handleWelcomeScreen(int userInput){
        Constants.CURRENT_SCREEN = Screens.WelcomeScreen;
        switch (userInput){
            case 1:
                FileManager.listAllFiles(Directory.getAllFilesInDirectoryASC());
                break;
            case 2:
                Constants.CURRENT_SCREEN = Screens.MoreOptionsScreen;
                break;
            case 3:
                System.out.println("Application is existing, hope to see you again :)");
                System.exit(0);
                break;
            default:
                Printer.print(ConsoleColors.RED,"Sorry, you have entered an invalid option.\nPlease try again.");
                break;
        }
    }

    public void handleMoreOptionsScreen(int userInput){
        Constants.CURRENT_SCREEN = Screens.MoreOptionsScreen;
        Scanner input = new Scanner(System.in);
        switch (userInput){
            case 1:
                Printer.print(ConsoleColors.RESET,"Please type the file name you would like to create:");
                String fileName = input.next();
                FileManager.addNewFile(fileName);
                break;
            case 2:
                Printer.print(ConsoleColors.RESET,"Please type the name of the file/folder to be deleted:");
                String fileToDelete = input.next();
                FileManager.deleteFile(fileToDelete);
                break;
            case 3:
                Printer.print(ConsoleColors.RESET,"Please type the name of the file/folder to search for:");
                String fileToSearchFor = input.next();
                FileManager.searchFiles(fileToSearchFor);
                break;
            case 4:
                Constants.CURRENT_SCREEN = Screens.WelcomeScreen;
                break;
            case 5:
                System.out.println("Application is existing, hope to see you again :)");
                System.exit(0);
                break;
            default:
                Printer.print(ConsoleColors.RED,"Sorry, you have entered an invalid option.\nPlease try again.");
                break;
        }
    }
}
