module com.main.cinetrack  {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;
    requires java.rmi;


    opens com.main.cinetrack to javafx.fxml;
    exports com.main.cinetrack.main;
    opens com.main.cinetrack.main to javafx.fxml;
    opens com.main.cinetrack.controllers to javafx.fxml;
}