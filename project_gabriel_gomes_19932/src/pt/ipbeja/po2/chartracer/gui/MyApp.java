package pt.ipbeja.po2.chartracer.gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class MyApp {

    public static void main(String[] args) {
        getFileLines("datafiles/cities.txt");
    }


    public static void getFileLines(String filename) {
        String[] fileLines = readFileToStringArray(filename);
        for (int j = 0; j < 50; j++) {
            System.out.println(j + ": " + fileLines[j]);
        }
    }


    public static String[] readFileToStringArray(String filename) {
        try {
            return Files.readAllLines(Paths.get(filename)).toArray(new String[0]);
        } catch (IOException e) {
            String errorMessage = "Error reading file " + filename;
            System.out.println(errorMessage);
            System.out.println(errorMessage + " - Exception " + e.toString());
            return new String[0];
        }
    }
}
