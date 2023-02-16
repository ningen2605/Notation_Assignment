module notation_assignment2.notation_assignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires java.desktop;
    requires org.junit.jupiter.api;

    opens notation_assignment2.notation_assignment to javafx.fxml;
    exports  notation_assignment2.notation_assignment;
}