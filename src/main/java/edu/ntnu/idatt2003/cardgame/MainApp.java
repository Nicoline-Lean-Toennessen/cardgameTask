package edu.ntnu.idatt2003.cardgame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApp extends Application {
    DeckOfCards deck = new DeckOfCards();
    HBox hbox = new HBox(40);
    VBox buttonBox = new VBox(20);
    HBox infoContainer = new HBox(10);
    PlayingCard queen = deck.getCards().get(44);


    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        hbox.setAlignment(Pos.BOTTOM_LEFT);
        hbox.setLayoutY(50);
        hbox.setLayoutX(100);
        hbox.setId("tableBox");

        Button dealButton = new Button("Deal hand");
        dealButton.setId("dealButton");
        dealButton.setOnAction(this::handleDealButton);

        Button checkButton = new Button("Check hand");
        checkButton.setId("checkButton");
        checkButton.setOnAction(this::handleCheckButton);

        buttonBox.setLayoutY(100);
        buttonBox.setLayoutX(1000);
        buttonBox.setId("buttonBox");
        buttonBox.getChildren().addAll(dealButton, checkButton);


        pane.getChildren().add(hbox);
        pane.getChildren().add(buttonBox);
        pane.getChildren().add(infoContainer);


        pane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());


        Scene scene = new Scene(pane, 600, 600);
        primaryStage.setTitle("Card game task");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void handleCheckButton(ActionEvent actionEvent) {
        infoContainer.getChildren().clear();


        int sum = deck.getDealtCards().stream()
                .mapToInt(PlayingCard::getFace)
                .sum();


        String nrOfHearts = deck.getDealtCards().stream()
                .filter(p -> p.getSuit() == 'H')
                .map(PlayingCard::getAsString)
                .reduce("", (acc, cardStr) -> acc + cardStr);

        if (nrOfHearts.isEmpty()) {
            nrOfHearts = "No hearts";
        }

        boolean queenBool = deck.getDealtCards().contains(queen);

        infoContainer.setId("infoContainer");
        infoContainer.setLayoutX(200);
        infoContainer.setLayoutY(550);

        VBox leftInfo = new VBox(10);
        leftInfo.setId("leftInfo");
        leftInfo.getChildren().add(new Text("Sum of the faces: " + sum));
        leftInfo.getChildren().add(new Text("Flush: " + deck.checkFlush(deck.getDealtCards())));

        VBox rightInfo = new VBox(10);
        rightInfo.setId("rightInfo");
        rightInfo.getChildren().add(new Text("Cards of hearts: " + nrOfHearts));
        rightInfo.getChildren().add(new Text("Queen of spades: " + queenBool));

        infoContainer.getChildren().addAll(leftInfo, rightInfo);
    }


    private void handleDealButton(ActionEvent actionEvent) {
        hbox.getChildren().clear();

        if (deck.getCards().size() < 5) {
            Text text = new Text("You don't have enough cards for a new hand");
            text.setStyle("-fx-font-size: 22px");
            hbox.getChildren().add(text);
            return;
        }

        deck.dealHand(5);

        deck.getDealtCards().stream()
                .map(pc -> {
                    VBox playCard = new VBox();
                    playCard.setAlignment(Pos.CENTER);
                    Text nrText = new Text("" + pc.getFace());
                    nrText.setStyle("-fx-font-size: 20px");
                    playCard.getChildren().add(nrText);
                    playCard.getChildren().add(pc.getImageIcon(pc.getSuit()));
                    playCard.setId("playCard");
                    return playCard;
                })
                .forEach(hbox.getChildren()::add);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
