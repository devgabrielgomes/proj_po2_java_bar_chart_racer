package pt.ipbeja.po2.chartracer.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class BarchartModel {
    public BarchartModel(View view) {
        this.view = view;
    }

    private View view;

    public void loadCitiesData() {
        Thread t = new Thread(() -> {
            for (int i = 0; i < YearInfo.getAllYearsLines().length; i++) {
                this.view.create(OrderCities.orderYearByPopulation(YearInfo.getAllYearsLines()[i][0]));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        //this.view.create(OrderCities.orderYearByPopulation(1500));
    }

//    public static void main(String[] args) {
//        readFileLines("datafiles/cities.txt");
//    }
//
//
//    public static void readFileLines(String filename) {
//        String[] fileLines = readFileToStringArray(filename);
//        for (int j = 0; j < 50; j++) {
//            System.out.println(j + ": " + fileLines[j]);
//        }
//    }
//
//
//    public static String[] readFileToStringArray(String filename) {
//        try {
//            return Files.readAllLines(Paths.get(filename)).toArray(new String[0]);
//        } catch (IOException e) {
//            String errorMessage = "Error reading file " + filename;
//            System.out.println(errorMessage);
//            System.out.println(errorMessage + " - Exception " + e.toString());
//            return new String[0];
//        }
//    }

    public static String[][] readFileToStringArray2D(String filename, String separator) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            String[][] allData = new String[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                allData[i] = lines.get(i).split(separator);
            }
            return allData;
        } catch (IOException e) {
            String errorMessage = "Error reading file " + filename;
            System.out.println(errorMessage);
            System.out.println(errorMessage + " - Exception " + e.toString());
            return new String[0][];
        }
    }

}
