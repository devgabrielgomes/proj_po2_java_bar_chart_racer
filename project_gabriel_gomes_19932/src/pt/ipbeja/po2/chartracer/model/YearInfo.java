package pt.ipbeja.po2.chartracer.model;

public class YearInfo {
    public static String[][] cities = BarchartModel.readFileToStringArray2D("datafiles/cities.txt", ",");

    public static int[][] getAllYearsLines() {
        int[][] allYearsInfo = new int[cities.length][2];
        int yearIndex = 0;
        for (int i = 3; i < cities.length; i++) {
            if (String.valueOf(cities[i][0]).isEmpty()) {
                // Ano, Linha, Quant ----------------------
                allYearsInfo[yearIndex] = new int[]{Integer.parseInt(cities[i + 2][0]), (i + 2), Integer.parseInt(cities[i + 1][0])};
                //System.out.println(Arrays.toString(allYearsInfo[yearIndex]));
                yearIndex++;
            }
        }
        return allYearsInfo;
    }

    public static int getYearLine(int year) {
        int yearLine = 0;
        for (int i = 0; i < getAllYearsLines().length; i++) {
            if (getAllYearsLines()[i][0] == year) {
                yearLine = getAllYearsLines()[i][1];
                break;
            }
        }
        //System.out.println("yearLine: " + yearLine);
        return yearLine;
    }
}
