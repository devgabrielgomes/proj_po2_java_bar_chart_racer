package pt.ipbeja.po2.chartracer.model;

import java.util.ArrayList;

/**
 * view interface of the project
 *
 * @author Gabriel Alexandre Teixeira Gomes
 * @version 2022-06-22
 */
public interface View {
    void create(ArrayList<OrderCities> orderCities, String[] fileLines, int currentYear);
}
