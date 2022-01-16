package com.kamar.lockedme.logic;

import com.kamar.lockedme.Constants;
import com.kamar.lockedme.enums.ConsoleColors;
import com.kamar.lockedme.enums.Screens;
import com.kamar.lockedme.io.Directory;
import com.kamar.lockedme.utils.Printer;

public class Initializer {
    public static void Init(){
        try{
            Directory.checkAppDirctory();
            Constants.CURRENT_SCREEN = Screens.WelcomeScreen;
            UserInterfaceManager.showWelcomeScreen(true);
            UserInputManager.handleUserInput();
        }catch (Exception e){
            Printer.print(ConsoleColors.RED, "Something went wrong: \n" + e.getMessage());
            handleLoop();
        }
    }

    public static void handleLoop(){
        UserInterfaceManager.showCorrespondingScreen();
        UserInputManager.handleUserInput();
    }
}
