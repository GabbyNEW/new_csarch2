package com.csarch.csarch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SimulationController {
    @FXML
    private TextField A1, A10, A11, A12, A13, A14, A15, A16,
            A2, A3, A4, A5, A6, A7, A8, A9;
    @FXML
    private TextField Q1, Q10, Q11,  Q12, Q13, Q14, Q15, Q16,
            Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9;
    @FXML
    private TextField Q_1_1, Q_1_10, Q_1_11, Q_1_12, Q_1_13, Q_1_14, Q_1_15, Q_1_16,
            Q_1_2, Q_1_3, Q_1_4, Q_1_5, Q_1_6, Q_1_7, Q_1_8, Q_1_9;
    @FXML
    private TextField binary_output, decimal_output;
    @FXML
    private Button do_another_simulation_button, exit_button_simulation;
    @FXML
    private Text m_text, q_text;
    @FXML
    private Button output_result_in_text_file_button;
    @FXML
    private Button simulate_all_button, simulate_step_by_step_button;

    public String m, m_negative, a, q;
    public int q_negative;
    private int current_iteration = 0, numOfDigits;

    private String output;

    private void updateUi() {
        switch (this.current_iteration + 1) {
            case 1:
                A1.setText(String.valueOf(a));
                Q1.setText(String.valueOf(q));
                Q_1_1.setText(String.valueOf(q_negative));
                break;
            case 2:
                A2.setText(String.valueOf(a));
                Q2.setText(String.valueOf(q));
                Q_1_2.setText(String.valueOf(q_negative));
                break;
            case 3:
                A3.setText(String.valueOf(a));
                Q3.setText(String.valueOf(q));
                Q_1_3.setText(String.valueOf(q_negative));
                break;
            case 4:
                A4.setText(String.valueOf(a));
                Q4.setText(String.valueOf(q));
                Q_1_4.setText(String.valueOf(q_negative));
                break;
            case 5:
                A5.setText(String.valueOf(a));
                Q5.setText(String.valueOf(q));
                Q_1_5.setText(String.valueOf(q_negative));
                break;
            case 6:
                A6.setText(String.valueOf(a));
                Q6.setText(String.valueOf(q));
                Q_1_6.setText(String.valueOf(q_negative));
                break;
            case 7:
                A7.setText(String.valueOf(a));
                Q7.setText(String.valueOf(q));
                Q_1_7.setText(String.valueOf(q_negative));
                break;
            case 8:
                A8.setText(String.valueOf(a));
                Q8.setText(String.valueOf(q));
                Q_1_8.setText(String.valueOf(q_negative));
                break;
            case 9:
                A9.setText(String.valueOf(a));
                Q9.setText(String.valueOf(q));
                Q_1_9.setText(String.valueOf(q_negative));
                break;
            case 10:
                A10.setText(String.valueOf(a));
                Q10.setText(String.valueOf(q));
                Q_1_10.setText(String.valueOf(q_negative));
                break;
            case 11:
                A11.setText(String.valueOf(a));
                Q11.setText(String.valueOf(q));
                Q_1_11.setText(String.valueOf(q_negative));
                break;
            case 12:
                A12.setText(String.valueOf(a));
                Q12.setText(String.valueOf(q));
                Q_1_12.setText(String.valueOf(q_negative));
                break;
            case 13:
                A13.setText(String.valueOf(a));
                Q13.setText(String.valueOf(q));
                Q_1_13.setText(String.valueOf(q_negative));
                break;
            case 14:
                A14.setText(String.valueOf(a));
                Q14.setText(String.valueOf(q));
                Q_1_14.setText(String.valueOf(q_negative));
                break;
            case 15:
                A15.setText(String.valueOf(a));
                Q15.setText(String.valueOf(q));
                Q_1_15.setText(String.valueOf(q_negative));
                break;
            case 16:
                A16.setText(String.valueOf(a));
                Q16.setText(String.valueOf(q));
                Q_1_16.setText(String.valueOf(q_negative));
                break;
        }
    }

    public void initialize() {
        output_result_in_text_file_button.setDisable(true);
        m_text.setText("M: " + DataClass.multiplicand);
        q_text.setText("Q: " + DataClass.multiplier);
    }

    private void initialize_values() {
        /*
        a) A <- 0
        b) Q-1 <- 0
        c) M stores multiplicand
        d) Q stores multiplier
        */
        if (DataClass.isDecimalSelected) {
            m = Utility.decimalToBinary(DataClass.multiplicand);
            q = Utility.decimalToBinary(DataClass.multiplier);


            m = Utility.checkNumBitsMShort(m, q, Integer.parseInt(DataClass.multiplicand));
            q = Utility.checkNumBitsQShort(m, q, Integer.parseInt(DataClass.multiplier));

        }
        else {
            m = DataClass.multiplicand;
            q = DataClass.multiplier;

            m = "0" + m;
            q = "0" + q;
        }

        m_negative = Utility.findTwoscomplement(String.valueOf(m));

        if(m.length() > q.length())
            numOfDigits = m.length(); // Determine number of digits on M to initialize A
        else
            numOfDigits = q.length();

        m = Utility.additionalMSb(numOfDigits, m);
        q = Utility.additionalMSb(numOfDigits, q);

        DataClass.numberOfDigits = numOfDigits;
        String temp_a = "";
        for (int i = 0; i < numOfDigits; i++) {
            temp_a = temp_a.concat("0");
        }

        a = temp_a;

        q_negative = 0;
    }

    public void simulate_step() {
        if (current_iteration == 0)
            initialize_values();
        // Read Qlsb, Q-1
        int q0qMinus1 = Utility.getQ0Qminus1(q, q_negative);
        if (q0qMinus1 == 10) // A <- A-M
            a = Utility.binaryArithmeticSubtract(a, m_negative);
        else if (q0qMinus1 == 1) // A <- A+M
            a = Utility.binaryArithmeticAdd(a, m);
        // Arithmetic shift right
        Utility.arithmeticShiftRight(this); // Pass iteration

        // Update UI
        updateUi();
        current_iteration++;

        if (current_iteration == numOfDigits) {
            simulate_step_by_step_button.setDisable(true);
            simulate_all_button.setDisable(true);
            output_result_in_text_file_button.setDisable(false);
            String binaryOutput = a + q;
            binary_output.setText(binaryOutput);

            // Check for sign cases
            if((Integer.parseInt(DataClass.multiplicand) > 0 && Integer.parseInt(DataClass.multiplier) < 0) ||
                    (Integer.parseInt(DataClass.multiplicand) < 0 && Integer.parseInt(DataClass.multiplier) > 0)) {
                // do two's complement (not the output but internally then convert to int)
                binaryOutput = Utility.findTwoscomplement(binaryOutput);
                int out = Integer.parseInt(binaryOutput, 2);
                out *= -1;
                decimal_output.setText(String.valueOf(out));
            } else {
                decimal_output.setText(String.valueOf(Integer.parseInt(binaryOutput, 2)));
            }

            this.output = "Binary Output: " + binary_output.getText() + " | Decimal Output: " + decimal_output.getText();
        }
    }

    public void simulate_all() {
        if (current_iteration == 0)
            initialize_values();
        simulate_step_by_step_button.setDisable(true);

        while (current_iteration < numOfDigits)
            simulate_step();
    }

    public void writeOutputToFile() {
        try {
            Utility.writeToFile(this.output);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Information");
            a.setContentText("Successfully write output to txt file. The file is generated in the same directory of this program location.");
            a.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // close application
    @FXML
    public void closeApplication(ActionEvent event) {
        Stage stage = (Stage) exit_button_simulation.getScene().getWindow();
        stage.close();
    }
}