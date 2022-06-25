package pt.ipbeja.po2.chartracer.model;

/**
 * class to get information about a file
 *
 * @author Gabriel Alexandre Teixeira Gomes
 * @version 2022-06-22
 */
public class YearInfo {
    public static String[][] cities = BarchartModel.readFileToStringArray2D("datafiles/cities.txt", ",");

    /**
     * get the year, line and quantity of all years occurrences
     *
     * @return      array with information about all years
     */
    public static int[][] getAllYearsLines() {
        int[][] allYearsInfo = new int[cities.length][2];
        int yearIndex = 0;
        for (int i = 3; i < cities.length; i++) {
            if (String.valueOf(cities[i][0]).isEmpty()) {
                allYearsInfo[yearIndex] = new int[]{Integer.parseInt(cities[i + 2][0]), (i + 2), Integer.parseInt(cities[i + 1][0])};
                yearIndex++;
            }
        }
        return allYearsInfo;
    }

    /**
     * get the year, line and quantity of all years occurrences
     *
     * @param   year    year to get the line
     * @return          int variable with the line of the year
     */
    public static int getYearLine(int year) {
        int yearLine = 0;
        for (int i = 0; i < getAllYearsLines().length; i++) {
            if (getAllYearsLines()[i][0] == year) {
                yearLine = getAllYearsLines()[i][1];
                break;
            }
        }
        return yearLine;
    }
}