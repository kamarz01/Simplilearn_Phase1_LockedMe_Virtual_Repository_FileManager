package com.kamar.lockedme.io;

import com.kamar.lockedme.Constants;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Directory {

    public static void checkAppDirctory(){
        File folderPath = new File(Constants.MAIN_FOLDER_NAME);
        try{
            if (!folderPath.exists()){
                folderPath.mkdir();
            }
        }catch (Exception e){
            System.out.println("Can not create the main folder because of: \n" + e.getMessage());
            System.exit(0);
        }
    }

    public static File[] getAllFilesInDirectoryASC(){
        checkAppDirctory();
        return new File(Constants.MAIN_FOLDER_NAME).listFiles();
    }
}
