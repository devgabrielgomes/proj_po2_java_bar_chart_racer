package pt.ipbeja.po2.chartracer.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pt.ipbeja.po2.chartracer.model.OrderCities;
import pt.ipbeja.po2.chartracer.model.View;

import java.util.ArrayList;

public class BarchartBoard extends StackPane implements View {
    public BarchartBoard() {
        this.create();
    }

    public void create() {
        VBox box = new VBox();
        ArrayList<OrderCities> atualFirstYearOrdered = OrderCities.orderByPopulation(1500);
        for (int i = 0; i < atualFirstYearOrdered.size(); i++) {
            OrderCities orderCities = atualFirstYearOrdered.get(i);
            Rectangle r = new Rectangle(orderCities.getnPopulation(), 50);
            r.setFill(Color.RED);
            VBox.setMargin(r, new Insets(10, 10, 10, 10));
            box.getChildren().add(r);
        }
        box.setAlignment(Pos.TOP_LEFT);
        this.getChildren().add(box);
    }
    
}

//    Thread t = new Thread( () ->  {
//        for(int i = 0; i < 10; i++) {
//            Platform.runLater( () ->
//                    { view.updateCounter(this.counter); }
//            );
//
//            //view.updateCounter(this.counter);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            this.counter++;
//        }
//    });
//        t.start();

