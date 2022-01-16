package com.kamar.lockedme.logic;

import com.kamar.lockedme.Constants;
import com.kamar.lockedme.enums.ConsoleColors;
import com.kamar.lockedme.enums.Screens;
import com.kamar.lockedme.utils.Printer;

public class UserInterfaceManager {

    public static void showWelcomeScreen(boolean showHeader){
        String seperator = "##################################################";
        String appDetails = String.format("AppName: %s \nCompanyName: %s \nDeveloper: %s", Constants.APP_NAME,Constants.COMPANY_NAME,Constants.DEVELOPER_NAME);
        String welcomeText = "Welcome to LockedMe app, where you can handle all of your file(s) operations.";

        String appScreenOneOptions =
                String.format("#### Select the option number from below then press Enter #### \n\n1. %s\n2. %s\n3. %s",
                        Constants.WELCOME_OPTION_1,
                        Constants.WELCOME_OPTION_2,
                        Constants.EXIT_OPTION);


        if (showHeader){
            Printer.print(ConsoleColors.GREEN,String.format("%s\n%s\n%s\n",seperator,appDetails,seperator));
            Printer.print(ConsoleColors.YELLOW,appScreenOneOptions);

        }else{
            Printer.print(ConsoleColors.YELLOW,"\n"+appScreenOneOptions);
        }

    }

    public static void showCorrespondingScreen(){
        switch (Constants.CURRENT_SCREEN){
            case WelcomeScreen:
                showWelcomeScreen(false);
                break;
            case MoreOptionsScreen:
                showMoreOptionsScreen();
                break;
            default:
                break;
        }
    }

    private static void showMoreOptionsScreen() {
        String appScreenTwoOptions =
                String.format("#### Select the option number from below then press Enter #### \n\n1. %s\n2. %s\n3. %s\n4. %s\n5. %s",
                        Constants.MORE_OPTION_1,
                        Constants.MORE_OPTION_2,
                        Constants.MORE_OPTION_3,
                        Constants.BACK_TO_HOME,
                        Constants.EXIT_OPTION);

        Printer.print(ConsoleColors.YELLOW,appScreenTwoOptions);

    }
}
