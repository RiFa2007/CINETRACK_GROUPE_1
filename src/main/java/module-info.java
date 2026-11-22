module com.main.cinetrack  {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;
    requires java.rmi;
    requires jfxtras.controls;


    opens com.main.cinetrack to javafx.fxml;
    exports com.main.cinetrack.main;
    opens com.main.cinetrack.main to javafx.fxml;
    opens com.main.cinetrack.controllers to javafx.fxml;
    opens com.main.cinetrack.controllers.message to javafx.fxml;
    opens com.main.cinetrack.controllers.SalleController to javafx.fxml;
    opens com.main.cinetrack.controllers.FilmControllers to javafx.fxml;
    opens com.main.cinetrack.controllers.TicketController to javafx.fxml;
}