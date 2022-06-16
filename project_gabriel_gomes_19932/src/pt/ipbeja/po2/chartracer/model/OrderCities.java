package pt.ipbeja.po2.chartracer.model;

import java.util.*;

public class OrderCities implements Comparable<OrderCities>{
    private int year;
    private String cityName;
    private int nPopulation;
    private String country;
    private String continent;
    private static int[][] allYearsInfo = YearInfo.getAllYearsLines();
    public static String[][] cities = BarchartModel.readFileToStringArray2D("datafiles/cities.txt", ",");


    public int compareTo(OrderCities temp) {
        if(this.getnPopulation() == temp.getnPopulation()) return 0;
        else if(this.getnPopulation() > temp.getnPopulation()) return 1;
        else return -1;
    }

    public OrderCities(int year, String cityName, String country, int nPopulation, String continent){
        this.year = year;
        this.cityName = cityName;
        this.nPopulation = nPopulation;
        this.continent = continent;
        this.country = country;
    }

    public int getYear() {
        return year;
    }
    public String getCityName() {
        return cityName;
    }
    public int getnPopulation() {
        return nPopulation;
    }
    public String getCountry() {
        return country;
    }
    public String getContinent() {
        return continent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCities that = (OrderCities) o;
        return year == that.year && nPopulation == that.nPopulation && cityName.equals(that.cityName) && country.equals(that.country) && continent.equals(that.continent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, cityName, nPopulation, country, continent);
    }

    public static ArrayList<OrderCities> orderYearByPopulation(int year){
        ArrayList<OrderCities> orderByCityPopulation = new ArrayList<OrderCities>();
        orderByCityPopulation.clear();
        int yearLine = YearInfo.getYearLine(year);
        for (int k = yearLine; k < yearLine+12; k++) {
            orderByCityPopulation.add(new OrderCities(year, cities[k][1], cities[k][2], Integer.parseInt(cities[k][3]), cities[k][4]));
        }
        Collections.sort(orderByCityPopulation, Comparator.reverseOrder());
        printOrdered(orderByCityPopulation);
        return orderByCityPopulation;
    }

//    public static void printAllOrderAllYears() {
//        for (int i = 0; i < allYearsInfo.length; i++) {
//            orderYearByPopulation(allYearsInfo[i][0]);
//        }
//    }

//    public static List<List<OrderCities>> orderAllYears() {
//        List <List<OrderCities>> orderAllYears = new ArrayList<List<OrderCities>>();
//
//        //ArrayList<OrderCities> orderAllYears = new ArrayList<OrderCities>();
//        for (int i = 0; i < allYearsInfo.length; i++) {
//            orderAllYears.add(orderYearByPopulation(allYearsInfo[i][0]));
//        }
//        //System.out.println(orderAllYears);
//        return orderAllYears;
//    }

    public static void printOrdered(ArrayList<OrderCities> arrayListToPrint) {
        System.out.println("Cities after sorting : ");
        for (OrderCities test: arrayListToPrint)
        {
            System.out.println(test.getYear() + " " +
                    test.getCityName() + " " +
                    test.getCountry() + " " +
                    test.getnPopulation() + " " +
                    test.getContinent());
        }
    }



//    public static ArrayList<OrderCities> orderByPopulation(){
//        ArrayList<OrderCities> allCitiesOrdered = new ArrayList<>();
//        for (int year = 1500; year <= 2018; year++) {
//            //ArrayList<OrderCities> atualFirstYearOrdered = OrderCities.orderByPopulation(1500);
//            allCitiesOrdered.add(OrderCities.orderByPopulation(year));
//        }
//    }

}


