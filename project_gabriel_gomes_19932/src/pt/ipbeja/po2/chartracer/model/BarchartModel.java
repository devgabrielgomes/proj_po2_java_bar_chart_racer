package pt.ipbeja.po2.chartracer.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * modal class of the project
 *
 * @author Gabriel Alexandre Teixeira Gomes
 * @version 2022-06-22
 */
public class BarchartModel {
    /**
     * constructor of the class
     *
     * @param   view
     */
    public BarchartModel(View view) {
        this.view = view;
    }

    private View view;
    private int currentYear = 0;
    private int animationDelay = 100;

    /**
     * loads all city's sorted data to the View
     */
    public void loadCitiesData() {
        Thread t = new Thread(() -> {
            for (int i = 0; i < YearInfo.getAllYearsLines().length; i++) {
                currentYear = YearInfo.getAllYearsLines()[i][0];
                this.view.create(OrderCities.orderYearByPopulation(YearInfo.getAllYearsLines()[i][0]), readFileToStringArray("datafiles/cities.txt"), currentYear);
                try {
                    Thread.sleep(animationDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(currentYear);
        });
        t.start();
    }

    /**
     * read all lines to one array of Strings
     *
     * @param   filename    file to read
     * @return              array with one line in each position
     *                      or empty array if error reading file
     */
    public static String[] readFileToStringArray(String filename) {
        try {
            return Files.readAllLines(Paths.get(filename)).toArray(new String[0]);
        } catch (IOException e) {
            String errorMessage = "Error reading file " + filename;
            System.out.println(errorMessage + " - Exception " + e.toString());
            return new String[0];
        }
    }

    /**
     * read all lines to one array of arrays of Strings
     *
     * @param   filename    file to read
     * @param   separator   separator for tokens in each line
     * @return              array with one array of tokens in each position
     *                      or empty array if error reading file
     */
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
