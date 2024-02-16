module edu.au.cpsc.module6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens edu.au.cpsc.part1 to javafx.fxml;
    exports edu.au.cpsc.part1;
}