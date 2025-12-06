module cse213.badc {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.graphics;
    requires jdk.compiler;

    opens cse213.badc to javafx.fxml;
    opens cse213.badc.rhythm to javafx.fxml;
    opens cse213.badc.saad to javafx.fxml;

    exports cse213.badc;
    exports cse213.badc.rhythm;
    exports cse213.badc.saad;
}

