package com.kamar.lockedme.io;

import com.kamar.lockedme.Constants;
import com.kamar.lockedme.enums.ConsoleColors;
import com.kamar.lockedme.utils.Printer;

import javax.jws.Oneway;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {

    public static void listAllFiles(File[] fileList) {

        if (fileList.length == 0) {
            Printer.print(ConsoleColors.RED, "Sorry, There is no files/folders in the directory.");
            return;
        }

        Collections.sort(Arrays.asList(fileList));
        List<String> listOfFiles = new ArrayList<>();
        for (File file : fileList) {
            if (file.isDirectory()) {
                Printer.print(ConsoleColors.YELLOW_BRIGHT, file.getName() + " [Directory]");
                listOfFiles.add(file.getName() + " [Directory]");
                listAllFiles(file.listFiles());
            } else {
                String parent = file.getParent();
                if (parent.equals(Constants.MAIN_FOLDER_NAME))
                    Printer.print(ConsoleColors.YELLOW, "---" + file.getName());
                else
                    Printer.print(ConsoleColors.YELLOW, "  ---" + file.getName());

                listOfFiles.add(file.getName());
            }
        }


    }

    public static void addNewFile(String fileName) {
        Directory.checkAppDirctory();
        if (Objects.isNull(fileName) || fileName.length() == 0) {
            Printer.print(ConsoleColors.RED, "File name can not be empty");
            return;
        }
        try {
            Path pathOfFile = Paths.get(Constants.MAIN_FOLDER_NAME+"/"+fileName);
            Files.createDirectories(pathOfFile.getParent());
            Files.createFile(pathOfFile);

            Printer.print(ConsoleColors.GREEN, String.format("File %s created successfully",fileName));

        } catch (Exception e) {
            Printer.print(ConsoleColors.RED, "Could not create the file because of:\n" + e.getMessage());
        }


    }

    public static void deleteFile(String fileName){
        Directory.checkAppDirctory();
        if (Objects.isNull(fileName) || fileName.length() == 0) {
            Printer.print(ConsoleColors.RED, "File name can not be empty");
            return;
        }

        try {
            List<Path> result = new FileManager().searchForFiles(Paths.get(Constants.MAIN_FOLDER_NAME), fileName);

            if (result.isEmpty()) {
                Printer.print(ConsoleColors.RED, String.format("File %s could not be found", fileName));
                return;
            }

            result.forEach(x -> {
                try
                {
                    Files.deleteIfExists(x);
                    Printer.print(ConsoleColors.GREEN, String.format("File %s found and deleted successfully",fileName));
                }
                catch(NoSuchFileException e)
                {
                    Printer.print(ConsoleColors.RED, String.format("File %s could not be found", fileName));
                }
                catch(DirectoryNotEmptyException e)
                {
                    Printer.print(ConsoleColors.RED, "Directory is not empty");
                }
                catch(IOException e)
                {
                    Printer.print(ConsoleColors.RED, "Invalid Permissions");
                }

            });

        } catch (Exception e) {
            Printer.print(ConsoleColors.RED, "Could not delete the file because of:\n" + e.getMessage());
        }

    }

    public static void searchFiles(String fileName){
        Directory.checkAppDirctory();
        if (Objects.isNull(fileName) || fileName.length() == 0) {
            Printer.print(ConsoleColors.RED, "File name can not be empty");
            return;
        }

        try {
            List<Path> result = new FileManager().searchForFiles(Paths.get(Constants.MAIN_FOLDER_NAME), fileName);

            if (result.isEmpty()) {
                Printer.print(ConsoleColors.RED, String.format("File/Folder %s could not be found", fileName));
                return;
            }

            Printer.print(ConsoleColors.GREEN, "List of files found: ");
            result.forEach(x -> {
                    Printer.print(ConsoleColors.GREEN, String.format("File %s on Path: %s ",x.getFileName(),x.toAbsolutePath()));
            });

        } catch (Exception e) {
            Printer.print(ConsoleColors.RED, "Could not find the file because of:\n" + e.getMessage());
        }
    }

    public List<Path> searchForFiles(Path path, String fileName) throws IOException {

        List<Path> result;
        try (Stream<Path> pathStream = Files.find(path,
                Integer.MAX_VALUE,
                (p, basicFileAttributes) -> p.getFileName().toString().equals(fileName))
        ) {
            result = pathStream.collect(Collectors.toList());
        }
        return result;

    }
}