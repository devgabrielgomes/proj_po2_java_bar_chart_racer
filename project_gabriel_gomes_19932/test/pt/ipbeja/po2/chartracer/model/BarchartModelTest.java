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

class BarchartModelTest {

    @Test
    public void teste1() {
        System.out.println("Ler um ficheiro de dados e verificar que está bem lido " +
                "(os dados lidos são os esperados)");

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

    @Test
    public void teste2() {
        ArrayList<OrderCities> atualNLines = OrderCities.orderByPopulation(1600);

        ArrayList<OrderCities> expectedNLines = new ArrayList<OrderCities>();
        expectedNLines.add(new OrderCities(1600, "Ahmedabad", "India", 190, "South Asia"));
        expectedNLines.add(new OrderCities(1600, "Nanjing", "China", 194, "East Asia"));
        expectedNLines.add(new OrderCities(1600, "Bijapur", "India", 200, "South Asia"));
        expectedNLines.add(new OrderCities(1600, "Cairo", "Egypt", 200, "Middle East"));
        expectedNLines.add(new OrderCities(1600, "Naples", "Italy", 224, "Europe"));
        expectedNLines.add(new OrderCities(1600, "Paris", "France", 245, "Europe"));
        expectedNLines.add(new OrderCities(1600, "Hangzhou", "China", 270, "East Asia"));
        expectedNLines.add(new OrderCities(1600, "Kyoto", "Japan", 300, "East Asia"));
        expectedNLines.add(new OrderCities(1600, "Osaka", "Japan", 360, "East Asia"));
        expectedNLines.add(new OrderCities(1600, "Agra", "India", 500,"South Asia"));
        expectedNLines.add(new OrderCities(1600, "Istanbul", "Turkey", 700, "Europe"));
        expectedNLines.add(new OrderCities(1600, "Beijing", "China", 706, "East Asia"));

        assertEquals(expectedNLines, atualNLines);
    }

    @Test
    public void teste3() {
        ArrayList<OrderCities> atualFirstYearOrdered = OrderCities.orderByPopulation(1500);
        ArrayList<OrderCities> atualFirstYearOrderedFiveLines = new ArrayList<OrderCities>();
        for (int i = 0; i <= 4; i++) {
            atualFirstYearOrderedFiveLines.add(atualFirstYearOrdered.get(i));
        }

        ArrayList<OrderCities> atualLastYearOrdered = OrderCities.orderByPopulation(2018);
        ArrayList<OrderCities> atualLastYearOrderedFiveLines = new ArrayList<OrderCities>();
        for (int i = 0; i <= 4; i++) {
            atualLastYearOrderedFiveLines.add(atualLastYearOrdered.get(i));
        }

//        ArrayList<OrderCities> expectedFirstYearOrderedFiveLines = new ArrayList<OrderCities>();
//        expectedFirstYearOrderedFiveLines.add(new OrderCities(1500, "Fez", "Morocco", 130, "Middle East"));
//        expectedFirstYearOrderedFiveLines.add(new OrderCities(1500, "Cuttack", "India", 140, "South Asia"));
//        expectedFirstYearOrderedFiveLines.add(new OrderCities(1500, "Nanjing", "China", 147, "East Asia"));
//        expectedFirstYearOrderedFiveLines.add(new OrderCities(1500, "Guangzhou", "China", 150, "East Asia"));
//        expectedFirstYearOrderedFiveLines.add(new OrderCities(1500, "Paris", "France", 185, "Europe"));
//
//        ArrayList<OrderCities> expectedLastYearOrderedFiveLines = new ArrayList<OrderCities>();
//        expectedLastYearOrderedFiveLines.add(new OrderCities(2018, "Karachi", "Pakistan", 18185, "South Asia"));
//        expectedLastYearOrderedFiveLines.add(new OrderCities(2018, "New York", "United States", 18713, "North America"));
//        expectedLastYearOrderedFiveLines.add(new OrderCities(2018, "Dhaka", "Bangladesh", 19633, "South Asia"));
//        expectedLastYearOrderedFiveLines.add(new OrderCities(2018, "Cairo", "Egypt", 19850, "Middle East"));
//        expectedLastYearOrderedFiveLines.add(new OrderCities(2018, "Osaka", "Japan", 20409, "East Asia"));
//
//        assertEquals(atualFirstYearOrderedFiveLines, expectedFirstYearOrderedFiveLines);
//        assertEquals(atualLastYearOrderedFiveLines, expectedLastYearOrderedFiveLines);


        String[] expectedOrderedCities = {"Fez Morocco 130 Middle East", "Cuttack India 140 South Asia", "Nanjing China 147 East Asia", "Guangzhou China 150 East Asia", "Paris France 185 Europe", "", "Karachi Pakistan 18185 South Asia", "New York United States 18713 North America", "Dhaka Bangladesh 19633 South Asia", "Cairo Egypt 19850 Middle East", "Osaka Japan 20409 East Asia"};

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
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //System.out.println(Arrays.deepToString(atualOrderedCities));

        for (int i = 0; i < atualOrderedCities.length; i++) {
            System.out.println(i + ": " + atualOrderedCities[i]);
        }
        System.out.println("\nexpectedOrderedCities: ");
        for (int i = 0; i < expectedOrderedCities.length; i++) {
            System.out.println(i + ": " + expectedOrderedCities[i]);
        }


        assertEquals(expectedOrderedCities, atualOrderedCities);
    }
}