package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import pt.ipbeja.po2.chartracer.model.BarchartModel;
import pt.ipbeja.po2.chartracer.model.OrderCities;
import pt.ipbeja.po2.chartracer.model.View;

import java.io.File;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Board class of the project
 *
 * @author Gabriel Alexandre Teixeira Gomes
 * @version 2022-06-22
 */
public class BarchartBoard extends Pane implements View {
    private ListView listView;
    public Color[] barColorsArray = getRandomBarColors();
    public String textColor = "000000";
    public String backgroundColor = "FFFFFF";

    BarchartModel model = new BarchartModel(this);
    public BarchartBoard() {
        model.loadCitiesData();
    }

    /**
     * create all the graphics
     *
     * @param   orderCities         arraylist
     * @param   fileLines           array of string with file lines
     * @param   currentYear         int variable with current year
     * @param   maxCityPopulation   int variable with max city population
     */
    public void create(ArrayList<OrderCities> orderCities, String[] fileLines, int currentYear, int maxCityPopulation) {
        Platform.runLater( () ->
                {
                    Text title = new Text();
                    title.setText(BarchartModel.FILE_LINES[0]);
                    title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
                    title.setFill(Color.web(textColor));
                    title.relocate(400, 10);

                    Text sources = new Text();
                    sources.setText(BarchartModel.FILE_LINES[2]);
                    sources.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR , 20));
                    sources.setFill(Color.web(textColor));
                    sources.relocate(1100, 730);

                    this.setStyle("-fx-background-color: #" + backgroundColor);
                    VBox barBox = new VBox();
                    VBox countryBox = new VBox();
                    ArrayList<OrderCities> atualFirstYearOrdered = orderCities;
                    for (int i = 0; i < atualFirstYearOrdered.size(); i++) {
                        OrderCities orderCitiesElement = atualFirstYearOrdered.get(i);
                        int barWidth = (((BarchartStart.MAX_WIDTH - 300) * orderCitiesElement.getnPopulation())/maxCityPopulation);
                        Rectangle r = new Rectangle(barWidth, 30);
                        r.setFill(barColorsArray[i]);

                        Text country = new Text(orderCitiesElement.getCityName());
                        country.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,15));
                        country.setFill(Color.web(textColor));
                        barBox.setMargin(r, new Insets(15, 10, 10, 10));
                        countryBox.setMargin(country, new Insets(26, 10, 10, 10));
                        countryBox.getChildren().add(country);
                        barBox.getChildren().add(r);

                        Text currentYearText = new Text();
                        currentYearText.setText(String.valueOf(currentYear));
                        currentYearText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,130));
                        currentYearText.setFill(Color.web(textColor));
                        currentYearText.relocate(1260, 580);
                        this.getChildren().clear();
                        this.getChildren().add(currentYearText);
                    }
                    barBox.relocate(180, 70);
                    countryBox.relocate(20, 57);
                    this.getChildren().addAll(barBox, countryBox, title, sources);
                }
        );
    }

    /**
     * creates the menu bar
     *
     * @return   menuBar   MenuBar variable with the gui menu bar
     */
    public MenuBar createMenuBar() {
        // Menu to select files
        Menu select_file_menu = new Menu("Ficheiros");
        MenuItem select_file_option_1 = new MenuItem("Selecionar um ficheiro");
        select_file_menu.getItems().addAll(select_file_option_1);
        select_file_option_1.setOnAction(e -> fileChooserButton(e));

        // Themes sub-menu bar themes
        Menu themes_option_3 = new Menu("Mudar tema das barras");
        CheckMenuItem themes_option_3_1 = new CheckMenuItem ("Tema 1 - Cores aleatórias");
        CheckMenuItem themes_option_3_2 = new CheckMenuItem ("Tema 2 - Cores do Arco Iris");
        themes_option_3_1.setSelected(true);

        themes_option_3_1.setOnAction(e -> {
            themes_option_3_2.setSelected(false);
            barColorsArray = getRandomBarColors();
        });
        themes_option_3_2.setOnAction(e -> {
            themes_option_3_1.setSelected(false);
            barColorsArray = new Color[]{Color.web("ff0000"), Color.web("ff8000"), Color.web("ffff00"), Color.web("80ff00"), Color.web("00ff00"), Color.web("00ff80"), Color.web("00ffff"), Color.web("0080ff"), Color.web("0000ff"), Color.web("8000ff"), Color.web("ff00ff"), Color.web("ff0080")};
        });

        themes_option_3.getItems().addAll(themes_option_3_1, themes_option_3_2);

        // Menu to select different themes
        Menu themes_menu = new Menu("Skin");
        CheckMenuItem themes_option_1 = new CheckMenuItem ("Tema claro");
        CheckMenuItem themes_option_2 = new CheckMenuItem ("Tema escuro");
        themes_option_1.setSelected(true);
        themes_option_1.setOnAction(e -> {
            themes_option_2.setSelected(false);
            textColor = "000000";
            backgroundColor = "FFFFFF";
        });
        themes_option_2.setOnAction(e -> {
            themes_option_1.setSelected(false);
            textColor = "FFFFFF";
            backgroundColor = "121212";
        });

        themes_menu.getItems().addAll(themes_option_1, themes_option_2, new SeparatorMenuItem(), themes_option_3);

        // Create the MenuBar and add all the Menus
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(this.widthProperty());
        menuBar.getMenus().addAll(select_file_menu, themes_menu);
        return menuBar;
    }


    /**
     * populates a array with random colors
     *
     * @return   colorsArray   array with random colors
     */
    public Color[] getRandomBarColors() {
        Color[] colorsArray = new Color[12];
        for (int i = 0; i < 12; i++) {
            colorsArray[i] = randomColor();
        }
        return colorsArray;
    }

    /**
     * function used get the values os the random colors
     *
     * @return   ranColor   a rgb color
     */
    public Color randomColor() {
        Color ranColor = Color.rgb(getColor(), getColor(),getColor());
        return ranColor;
    }

    /**
     * function used to generate random colors
     *
     * @return  a random number
     */
    public static int getColor() {
        return (int) (Math.random()*256);
    }

    /**
     * loads the fileChooser
     *
     * @param   event   action event
     */
    public void fileChooserButton(ActionEvent event) {
        // https://www.youtube.com/watch?v=hNz8Xf4tMI
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("datafiles"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            listView.getItems().add(selectedFile.getAbsolutePath());
        } else {
            System.out.println("The file chose is not valid!");
        }
    }
}




