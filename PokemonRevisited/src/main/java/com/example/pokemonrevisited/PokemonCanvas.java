package com.example.pokemonrevisited;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PokemonCanvas extends Canvas {

    private Image loadImage(String path) {
        try {
            return new Image(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            System.out.println("Failed to load image: " + path);
            return null;
        }
    }

    public void showSingleBackgroundAndSprite(Pokemon pokemon) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());

        // Draw red and white background with thicker black line separation
        gc.setFill(Color.RED);
        gc.fillRect(0, 0, getWidth(), getHeight() / 2);

        gc.setFill(Color.WHITE);
        gc.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(50); // Increase the line width
        gc.strokeLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

        // Draw black circle in the center of the black line
        double circleDiameter = 100;
        double circleX = (getWidth() - circleDiameter) / 2;
        double circleY = (getHeight() / 2) - (circleDiameter / 2);
        gc.setFill(Color.BLACK);
        gc.fillOval(circleX, circleY, circleDiameter, circleDiameter);

        double innerCircleDiameter = 60;
        double innerCircleX = (getWidth() - innerCircleDiameter) / 2;
        double innerCircleY = (getHeight() / 2) - (innerCircleDiameter / 2);
        gc.setFill(Color.WHITE);
        gc.fillOval(innerCircleX, innerCircleY, innerCircleDiameter, innerCircleDiameter);

        String type = pokemon.getType().toLowerCase().replace("/", "_");
        Image background = loadImage("/assets/" + type + ".png");
        Image sprite = loadImage("/assets/" + pokemon.getName().toLowerCase() + ".png");

        // Set sprite size
        double spriteScale = 1.5;
        double spriteWidth = sprite.getWidth() * spriteScale;
        double spriteHeight = sprite.getHeight() * spriteScale;

        // Center the sprite and type background in a rectangle
        double boxWidth = spriteWidth + 20;
        double boxHeight = spriteHeight + 20;
        double boxX = (getWidth() - boxWidth) / 2;
        double boxY = (getHeight() / 4) - (boxHeight / 2);

        gc.setFill(Color.BLACK);
        gc.fillRect(boxX, boxY, boxWidth, boxHeight);

        if (background != null) {
            gc.drawImage(background, boxX + 10, boxY + 10, spriteWidth, spriteHeight);
        }

        if (sprite != null) {
            gc.drawImage(sprite, boxX + 10, boxY + 10, spriteWidth, spriteHeight);
        }

        // Draw stats in the white section with larger text
        gc.setFont(new Font("SansSerif", 24)); // Increase the font size
        gc.setFill(Color.BLACK);

        double textX = getWidth() / 2 - 100;
        double textY = getHeight() / 2 + 80; // Lower the text position

        drawTextWithOutline(gc, "Name: " + pokemon.getName(), textX, textY);
        drawTextWithOutline(gc, "Weight: " + pokemon.getWeight() + " kg", textX, textY + 40);
        drawTextWithOutline(gc, "Height: " + pokemon.getHeight() + " m", textX, textY + 80);
        drawTextWithOutline(gc, "Type: " + pokemon.getType(), textX, textY + 120);

        // Draw larger bars for Attack, Defense, and Stamina
        gc.setFill(Color.RED);
        gc.fillRect(textX, textY + 160, (pokemon.getAttack() + 5) * 20, 20); // Increase stats by 5

        gc.setFill(Color.BLUE);
        gc.fillRect(textX, textY + 190, (pokemon.getDefense() + 5) * 20, 20); // Increase stats by 5

        gc.setFill(Color.GREEN);
        gc.fillRect(textX, textY + 220, (pokemon.getStamina() + 5) * 20, 20); // Increase stats by 5
    }

    private void drawTextWithOutline(GraphicsContext gc, String text, double x, double y) {
        gc.setFill(Color.WHITE);
        gc.fillText(text, x - 1, y - 1);
        gc.fillText(text, x + 1, y - 1);
        gc.fillText(text, x - 1, y + 1);
        gc.fillText(text, x + 1, y + 1);

        gc.setFill(Color.BLACK);
        gc.fillText(text, x, y);
    }

    public void showPokemon(Pokemon pokemon) {
        showSingleBackgroundAndSprite(pokemon);
    }
}
