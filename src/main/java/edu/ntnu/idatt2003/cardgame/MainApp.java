package edu.ntnu.idatt2003.cardgame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApp extends Application {
    DeckOfCards deck = new DeckOfCards();
    HBox hbox = new HBox(40);

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        hbox.setAlignment(Pos.BOTTOM_LEFT); // or Pos.BOTTOM_CENTER, etc.
        hbox.setLayoutY(50);
        hbox.setLayoutX(100);
        hbox.setId("tableBox");


        Button dealButton = new Button("Deal hand");
        dealButton.setId("dealButton");
        dealButton.setOnAction(this::handleDealButton);

        Button checkButton = new Button("Check hand");
        checkButton.setId("checkButton");
        checkButton.setOnAction(this::handleCheckButton);


        VBox buttonBox = new VBox(20);
        buttonBox.setLayoutY(100);
        buttonBox.setLayoutX(1000);
        buttonBox.setId("buttonBox");
        buttonBox.getChildren().addAll(dealButton, checkButton);


        pane.getChildren().add(hbox);
        pane.getChildren().add(buttonBox);

        StackPane root = new StackPane();
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        root.getChildren().add(pane);

        Scene scene = new Scene(root,600,600);
        primaryStage.setTitle("My JavaFX Window");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void handleCheckButton(ActionEvent actionEvent) {

    }

    private void handleDealButton(ActionEvent actionEvent) {
        hbox.getChildren().clear();
        for(PlayingCard pc : deck.dealHand(5)){
            VBox playCard = new VBox();
            playCard.setAlignment(Pos.CENTER);
            Text nrText = new Text(""+pc.getFace());
            nrText.setStyle("-fx-font-size: 20px");
            playCard.getChildren().add(nrText);
            playCard.getChildren().add(pc.getImageIcon(pc.getSuit()));
            playCard.setId("playCard");
            hbox.getChildren().add(playCard);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
