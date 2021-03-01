module isen.Bdd {
    requires javafx.controls;
    requires javafx.fxml;

    opens isen.Bdd to javafx.fxml;
    exports isen.Bdd;
}