module com.csarch.csarch {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.csarch.csarch to javafx.fxml;
    exports com.csarch.csarch;
}