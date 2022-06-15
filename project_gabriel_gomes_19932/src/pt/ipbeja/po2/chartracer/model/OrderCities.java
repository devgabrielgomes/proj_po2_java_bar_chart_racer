package pt.ipbeja.po2.chartracer.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class OrderCities implements Comparable<OrderCities>{
    private int year;
    private String cityName;
    private int nPopulation;
    private String country;
    private String continent;


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

    public static ArrayList<OrderCities> orderByPopulation(int year){
        String[][] cities = BarchartModel.readFileToStringArray2D("datafiles/cities.txt", ",");
        int yearLine = 0;
        for (int i = 3; i < cities.length; i++) {
            try{
                if(Integer.parseInt(cities[i][0]) == year) {
                    yearLine = i;
                    break;
                }
            }
            catch (NumberFormatException ex){
                ;
            }
        }

        ArrayList<OrderCities> orderByCityPopulation = new ArrayList<OrderCities>();
        for (int k = yearLine; k < yearLine+12; k++) {
            orderByCityPopulation.add(new OrderCities(year, cities[k][1], cities[k][2], Integer.parseInt(cities[k][3]), cities[k][4]));
        }
        Collections.sort(orderByCityPopulation, Comparator.reverseOrder());

//        System.out.println("Movies after sorting : ");
//        for (OrderCities test: orderByCityPopulation)
//        {
//            System.out.println(test.getYear() + " " +
//                    test.getCityName() + " " +
//                    test.getCountry() + " " +
//                    test.getnPopulation() + " " +
//                    test.getContinent());
//        }
        return orderByCityPopulation;
    }

    public static ArrayList<OrderCities> orderByPopulation(){
        ArrayList<OrderCities> allCitiesOrdered = new ArrayList<>();
        for (int year = 1500; year <= 2018; year++) {
            //ArrayList<OrderCities> atualFirstYearOrdered = OrderCities.orderByPopulation(1500);
            allCitiesOrdered.add(OrderCities.orderByPopulation(year));
        }
    }

}


