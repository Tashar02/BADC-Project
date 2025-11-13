module cse213.badc {
    requires javafx.controls;
    requires javafx.fxml;


    opens cse213.badc to javafx.fxml;
    exports cse213.badc;
}