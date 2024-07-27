module com.example.pokemonrevisited {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.example.pokemonrevisited to javafx.fxml;
    exports com.example.pokemonrevisited;
}
