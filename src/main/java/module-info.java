module com.example.assignment {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.assignment to javafx.fxml;
    exports com.example.assignment;
}