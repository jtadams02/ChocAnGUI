module jt.chocolic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens jt.chocolic to javafx.fxml;
    exports jt.chocolic;
}