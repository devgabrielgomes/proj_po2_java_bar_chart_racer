package pt.ipbeja.po2.chartracer.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pt.ipbeja.po2.chartracer.model.BarchartModel;
import pt.ipbeja.po2.chartracer.model.OrderCities;
import pt.ipbeja.po2.chartracer.model.View;

import java.util.ArrayList;

public class BarchartBoard extends StackPane implements View {
    BarchartModel model = new BarchartModel(this);
    public BarchartBoard() {
        model.loadCitiesData();
    }

    public void create(ArrayList<OrderCities> orderCities) {
        Platform.runLater( () ->
                {
                    VBox box = new VBox();

                    ArrayList<OrderCities> atualFirstYearOrdered = orderCities;
                    for (int i = 0; i < atualFirstYearOrdered.size(); i++) {
                        OrderCities orderCitiesElement = atualFirstYearOrdered.get(i);
                        Rectangle r = new Rectangle(orderCitiesElement.getnPopulation(), 50);
                        r.setFill(Color.RED);
                        VBox.setMargin(r, new Insets(10, 10, 10, 10));
                        box.getChildren().add(r);
                    }
                    box.setAlignment(Pos.TOP_LEFT);
                    this.getChildren().add(box);
                }
        );
    }
    
}




