package com.csarch.csarch;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    public TextField multiplicand_input;
    public TextField multiplier_input;
    public RadioButton decimal_radio_button;
    public RadioButton binary_radio_button;

    @FXML
    private void initialize() {
        ToggleGroup group = new ToggleGroup();
        decimal_radio_button.setToggleGroup(group);
        binary_radio_button.setToggleGroup(group);
    }

    @FXML
    protected void onSimulate() throws IOException {
        // Load data
        DataClass.multiplicand = multiplicand_input.getText();
        DataClass.multiplier = multiplier_input.getText();
        DataClass.isDecimalSelected = decimal_radio_button.isSelected();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("simulation-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800,  790);
        Stage stage = new Stage();
        stage.setTitle("CSARCH2 Simulation");
        stage.setScene(scene);
        stage.show();
    }

    // TODO: Map this to the Exit button, end application
    public void exit() {
        Platform.exit();
    }

}