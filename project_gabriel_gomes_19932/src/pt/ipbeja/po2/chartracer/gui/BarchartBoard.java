package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

/**
 * Board class of the project
 *
 * @author Gabriel Alexandre Teixeira Gomes
 * @version 2022-06-22
 */
public class BarchartBoard extends VBox implements View {
    private ListView listView;

    BarchartModel model = new BarchartModel(this);
    public BarchartBoard() {
        model.loadCitiesData();
    }

    /**
     * create all the graphics
     *
     * @param   orderCities     arraylist
     * @param   fileLines       array of string with file lines
     * @param   currentYear     int variable with current year
     */
    public void create(ArrayList<OrderCities> orderCities, String[] fileLines, int currentYear) {
        // Menu to select files
        Menu select_file_menu = new Menu("Ficheiros");
        MenuItem select_file_option_1 = new MenuItem("Selecionar um ficheiro");
        select_file_menu.getItems().addAll(select_file_option_1);

        // Themes sub-menu bar themes
        Menu themes_option_3 = new Menu("Mudar tema das barras");
        MenuItem themes_option_3_1 = new MenuItem("Tema 1");
        MenuItem themes_option_3_2 = new MenuItem("Tema 2");
        MenuItem themes_option_3_3 = new MenuItem("Tema 3");
        themes_option_3.getItems().addAll(themes_option_3_1, themes_option_3_2, themes_option_3_3);

        // Menu to select different themes
        Menu themes_menu = new Menu("Skin");
        MenuItem themes_option_1 = new MenuItem("Mudar para o tema claro");
        MenuItem themes_option_2 = new MenuItem("Mudar para o tema escuro");

        themes_menu.getItems().addAll(themes_option_1, themes_option_2, new SeparatorMenuItem(), themes_option_3);

        // Create the MenuBar and add all the Menus
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(select_file_menu, themes_menu);

//        Text title = new Text();
//        title.setText(fileLines[0]);
//        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
//
//        Text description = new Text();
//        description.setText(fileLines[1]);
//        description.setFont(Font.font("verdana", FontPosture.REGULAR, 14));
//        description.setFill(Color.DARKGREY);
//
//        Text sources = new Text();
//        sources.setText(fileLines[2]);
//        sources.setFont(Font.font("verdana", FontPosture.REGULAR, 14));
//        description.setFill(Color.DARKGREY);
//
//        Text currentYearText = new Text();
//        currentYearText.setText(String.valueOf(currentYear));
//        currentYearText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,100));
//        currentYearText.setFill(Color.DARKGREY);
//
//        VBox box2 = new VBox();
//        box2.getChildren().addAll(menuBar);
//        box2.setAlignment(Pos.TOP_LEFT);
//        this.getChildren().addAll(box2, title, description, sources, currentYearText);

//        Text title = new Text();
//        title.setText(fileLines[0]);
//        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));

        VBox menuBox = new VBox();
        menuBox.getChildren().addAll(menuBar);
        menuBox.setAlignment(Pos.TOP_LEFT);

        select_file_option_1.setOnAction(e -> fileChooserButton(e));

        Platform.runLater( () ->
                {
                    VBox barBox = new VBox();
                    VBox countryBox = new VBox();
                    ArrayList<OrderCities> atualFirstYearOrdered = orderCities;
                    for (int i = 0; i < atualFirstYearOrdered.size(); i++) {
                        OrderCities orderCitiesElement = atualFirstYearOrdered.get(i);
                        Rectangle r = new Rectangle(orderCitiesElement.getnPopulation(), 30);
                        r.setFill(Color.RED);

                        Text country = new Text(orderCitiesElement.getCountry());
                        barBox.setMargin(r, new Insets(8, 10, 10, 10));
                        barBox.getChildren().addAll(r, country);

                        Text currentYearText = new Text();
                        currentYearText.setText(String.valueOf(currentYear));
                        currentYearText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,100));
                        currentYearText.setFill(Color.DARKGREY);

                        HBox yearBox = new HBox(currentYearText);
                        yearBox.setAlignment(Pos.TOP_RIGHT);

                        this.getChildren().clear();
                        this.getChildren().add(yearBox);
                    }
                    barBox.setAlignment(Pos.TOP_LEFT);
                    menuBox.setSpacing(20);
                    this.getChildren().addAll(menuBox, barBox);
                }
        );
    }

    /**
     * loads the fileChooser
     *
     * @param   event   action event
     */
    public void fileChooserButton(ActionEvent event) {
        // https://www.youtube.com/watch?v=hNz8Xf4tMI
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\Gabriel Gomes\\Documents\\GitHub\\po2_project\\project_gabriel_gomes_19932\\datafiles"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            listView.getItems().add(selectedFile.getAbsolutePath());
        } else {
            System.out.println("The file chose is not valid!");
        }
    }
}




