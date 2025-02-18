/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.caesar;

/**
 *
 * @author ogoda
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Caesar extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Caesar Cipher App");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        Label inputLabel = new Label("Input Text:");
        TextField inputField = new TextField();
        inputField.setPromptText("Enter text here");
        Label shiftLabel = new Label("Shift Value:");
        TextField shiftField = new TextField();
        shiftField.setPromptText("Enter shift value");

        Label outputLabel = new Label("Output Text:");
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        Button encryptButton = new Button("Encrypt");
        Button decryptButton = new Button("Decrypt");
        Button bruteForceButton = new Button("Brute Force");

        grid.add(inputLabel, 0, 0);
        grid.add(inputField, 1, 0);
        grid.add(shiftLabel, 0, 1);
        grid.add(shiftField, 1, 1);
        grid.add(outputLabel, 0, 2);
        grid.add(outputArea, 1, 2);
        grid.add(encryptButton, 0, 3);
        grid.add(decryptButton, 1, 3);
        grid.add(bruteForceButton, 2, 3);

        encryptButton.setOnAction(e -> {
            String inputText = inputField.getText();
            int shift = Integer.parseInt(shiftField.getText());
            Encryption enc = new Encryption(inputText, shift);
            outputArea.setText(enc.toString());
        });

        decryptButton.setOnAction(e -> {
            String inputText = inputField.getText();
            int shift = Integer.parseInt(shiftField.getText());
            Decryption dec = new Decryption(inputText, shift);
            outputArea.setText(dec.toString());
        });

        bruteForceButton.setOnAction(e -> {
            String inputText = inputField.getText();
            String bruteForceResults = Attack.bruteForceAttack(inputText);
            String outputFilePath = "C:\\Users\\ogoda\\Desktop\\Ciphers\\brute_force_results.txt";
            try {
                Attack.writeToFile(outputFilePath, bruteForceResults);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("File Created");
                alert.setHeaderText(null);
                alert.setContentText("Brute force results have been written to: " + outputFilePath);
                alert.showAndWait();
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while writing to the file: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
