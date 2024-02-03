module takin.rotate {
    requires javafx.controls;
    requires javafx.fxml;

    opens takin.rotate to javafx.fxml;
    exports takin.rotate;
}
