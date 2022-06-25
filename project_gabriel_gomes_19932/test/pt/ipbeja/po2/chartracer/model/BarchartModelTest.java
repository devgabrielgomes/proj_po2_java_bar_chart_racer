package pt.ipbeja.po2.chartracer.model;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * class to test the written code
 *
 * @author Gabriel Alexandre Teixeira Gomes
 * @version 2022-06-22
 */
class BarchartModelTest {
    private int firstYear = 1500;
    private int lastYear = 2018;

    /**
     * read a file and confirm if the file with being well-read
     */
    @Test
    public void teste1() {
        String[][] atual = BarchartModel.readFileToStringArray2D("datafiles/cities.txt", ",");
        String[][] atualNLines = new String[3][5];
        int k = 0;
        for(int line = 5; line <= 7; line++) {
            atualNLines[k] = atual[line];
            k++;
        }

        for (int z = 0; z < atualNLines.length; z++) {
            System.out.println(z + ": " + Arrays.deepToString(atualNLines[z]));
        }

        String[][] expectedNLines = { {"1500", "Beijing", "China", "672", "East Asia"},
                {"1500", "Cairo", "Egypt", "400", "Middle East"}, {"1500", "Cuttack", "India", "140", "South Asia"} };
        assertArrayEquals(expectedNLines, atualNLines);
    }

    /**
     * sort the first and last year and confirm if the years are being well sorted
     */
    @Test
    public void teste2() {
        ArrayList<OrderCities> atualNLinesFirstYear = OrderCities.orderYearByPopulation(firstYear);
        ArrayList<OrderCities> expectedNLinesFirstYear = new ArrayList<OrderCities>();
        expectedNLinesFirstYear.add(new OrderCities(firstYear, "Beijing", "China", 672, "East Asia"));
        expectedNLinesFirstYear.add(new OrderCities(firstYear, "Vijayanagar", "India", 500, "South Asia"));
        expectedNLinesFirstYear.add(new OrderCities(firstYear, "Cairo", "Egypt", 400, "Middle East"));
        expectedNLinesFirstYear.add(new OrderCities(firstYear, "Hangzhou", "China", 250, "East Asia"));
        expectedNLinesFirstYear.add(new OrderCities(firstYear, "Tabriz", "Iran", 250, "Middle East"));
        expectedNLinesFirstYear.add(new OrderCities(firstYear, "Gauda", "India", 200, "South Asia"));
        expectedNLinesFirstYear.add(new OrderCities(firstYear, "Istanbul", "Turkey", 200, "Europe"));
        expectedNLinesFirstYear.add(new OrderCities(firstYear, "Paris", "France", 185, "Europe"));
        expectedNLinesFirstYear.add(new OrderCities(firstYear, "Guangzhou", "China", 150, "East Asia"));
        expectedNLinesFirstYear.add(new OrderCities(firstYear, "Nanjing", "China", 147, "East Asia"));
        expectedNLinesFirstYear.add(new OrderCities(firstYear, "Cuttack", "India", 140, "South Asia"));
        expectedNLinesFirstYear.add(new OrderCities(firstYear, "Fez", "Morocco", 130, "Middle East"));

        ArrayList<OrderCities> atualNLinesLastYear = OrderCities.orderYearByPopulation(lastYear);
        ArrayList<OrderCities> expectedNLinesLastYear = new ArrayList<OrderCities>();
        expectedNLinesLastYear.add(new OrderCities(lastYear,"Tokyo", "Japan", 38194, "East Asia"));
        expectedNLinesLastYear.add(new OrderCities(lastYear,"Delhi", "India", 27890, "South Asia"));
        expectedNLinesLastYear.add(new OrderCities(lastYear,"Shanghai", "China", 25779, "East Asia"));
        expectedNLinesLastYear.add(new OrderCities(lastYear,"Beijing", "China", 22674, "East Asia"));
        expectedNLinesLastYear.add(new OrderCities(lastYear,"Mumbai", "India", 22120, "South Asia"));
        expectedNLinesLastYear.add(new OrderCities(lastYear,"São Paulo", "Brazil", 21698, "Latin America"));
        expectedNLinesLastYear.add(new OrderCities(lastYear,"Mexico City", "Mexico", 21520, "Latin America"));
        expectedNLinesLastYear.add(new OrderCities(lastYear,"Osaka", "Japan", 20409, "East Asia"));
        expectedNLinesLastYear.add(new OrderCities(lastYear,"Cairo", "Egypt", 19850, "Middle East"));
        expectedNLinesLastYear.add(new OrderCities(lastYear,"Dhaka", "Bangladesh", 19633, "South Asia"));
        expectedNLinesLastYear.add(new OrderCities(lastYear,"New York", "United States", 18713, "North America"));
        expectedNLinesLastYear.add(new OrderCities(lastYear,"Karachi", "Pakistan", 18185, "South Asia"));

        assertEquals(expectedNLinesFirstYear, atualNLinesFirstYear);
        assertEquals(expectedNLinesLastYear, atualNLinesLastYear);
    }

    /**
     * write in a file the first five already sorted lines of two years last year
     * and confirm if the file is being well written
     */
    @Test
    public void teste3() {
        ArrayList<OrderCities> atualFirstYearOrderedFiveLines = new ArrayList<OrderCities>();
        for (int i = 0; i <= 4; i++) {
            atualFirstYearOrderedFiveLines.add(OrderCities.orderYearByPopulation(firstYear).get(i));
        }

        ArrayList<OrderCities> atualLastYearOrderedFiveLines = new ArrayList<OrderCities>();
        for (int i = 0; i <= 4; i++) {
            atualLastYearOrderedFiveLines.add(OrderCities.orderYearByPopulation(lastYear).get(i));
        }

        String[] expectedOrderedCities = {"Beijing China 672 East Asia", "Vijayanagar India 500 South Asia", "Cairo Egypt 400 Middle East", "Hangzhou China 250 East Asia", "Tabriz Iran 250 Middle East", "", "Tokyo Japan 38194 East Asia", "Delhi India 27890 South Asia", "Shanghai China 25779 East Asia", "Beijing China 22674 East Asia", "Mumbai India 22120 South Asia"};
        try {
            FileWriter f = new FileWriter("datafiles/cities_ordered.txt");
            for (OrderCities test: atualFirstYearOrderedFiveLines) {
                f.write(test.getCityName() + " " + test.getCountry() + " " + test.getnPopulation() + " " + test.getContinent() + "\n");
            }
            f.write("\n");
            for (OrderCities test: atualLastYearOrderedFiveLines) {
                f.write(test.getCityName() + " " + test.getCountry() + " " + test.getnPopulation() + " " + test.getContinent() + "\n");
            }
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] atualOrderedCities = new String[11];

        try {
            File myObj = new File("datafiles/cities_ordered.txt");
            Scanner myReader = new Scanner(myObj);
            int j = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                atualOrderedCities[j] = data;
                j++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < atualOrderedCities.length; i++) {
            System.out.println(i + ": " + atualOrderedCities[i]);
        }
        System.out.println("\nexpectedOrderedCities: ");
        for (int i = 0; i < expectedOrderedCities.length; i++) {
            System.out.println(i + ": " + expectedOrderedCities[i]);
        }

        assertArrayEquals(expectedOrderedCities, atualOrderedCities);
    }
}