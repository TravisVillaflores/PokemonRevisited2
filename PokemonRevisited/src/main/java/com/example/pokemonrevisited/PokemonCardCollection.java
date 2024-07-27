package com.example.pokemonrevisited;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PokemonCardCollection extends Application {
    private PokemonCanvas canvas;
    private PokemonCards pokemonDeck;
    private Timeline slideshowTimeline;

    @Override
    public void start(Stage primaryStage) {
        // Create intro scene
        Pane introPane = new Pane();
        Scene introScene = new Scene(introPane, 1280, 720);

        Label welcomeLabel = new Label("Welcome to the Pokedex");
        welcomeLabel.setFont(new Font("SansSerif", 24));
        welcomeLabel.setLayoutX(480);
        welcomeLabel.setLayoutY(320);

        Button startButton = new Button("Start");
        startButton.setLayoutX(600);
        startButton.setLayoutY(400);
        startButton.setOnAction(e -> primaryStage.setScene(mainScene(primaryStage)));

        introPane.getChildren().addAll(welcomeLabel, startButton);

        primaryStage.setTitle("Pokemon Card Collection");
        primaryStage.setScene(introScene);
        primaryStage.show();
    }

    private Scene mainScene(Stage primaryStage) {
        pokemonDeck = new PokemonCards();
        canvas = new PokemonCanvas();
        canvas.setWidth(1280);
        canvas.setHeight(720);

        Pane root = new Pane(canvas);
        Scene scene = new Scene(root, 1280, 720);

        int random = (int) (Math.random() * pokemonDeck.getSize());
        canvas.showPokemon(pokemonDeck.getPokemon(random));

        // Create a VBox for buttons
        VBox buttonBox = new VBox(10);
        buttonBox.setLayoutX(1100);
        buttonBox.setLayoutY(10);

        // Add slideshow button
        Button slideshowButton = new Button("Start Slideshow");
        slideshowButton.setOnAction(e -> startSlideshow());

        // Add search bar and button
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search Pokemon");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchPokemon(searchBar.getText()));

        // Add quit button
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> primaryStage.close());

        buttonBox.getChildren().addAll(slideshowButton, searchBar, searchButton, quitButton);
        root.getChildren().add(buttonBox);

        return scene;
    }

    private void startSlideshow() {
        slideshowTimeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            int random = (int) (Math.random() * pokemonDeck.getSize());
            canvas.showPokemon(pokemonDeck.getPokemon(random));
        }));
        slideshowTimeline.setCycleCount(Timeline.INDEFINITE);
        slideshowTimeline.play();
    }

    private void searchPokemon(String name) {
        int index = pokemonDeck.searchPokemon(name);
        if (index != -1) {
            canvas.showPokemon(pokemonDeck.getPokemon(index));
        } else {
            System.out.println("Pokemon not found");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}