package com.example.affine;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Affine extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Affine Cipher");

        Label titleLabel = new Label("Affine Cipher Encryption & Decryption");
        titleLabel.setFont(new Font("Arial", 18));
        titleLabel.setTextFill(Color.DARKBLUE);

        TextField inputField = new TextField();
        inputField.setPromptText("Enter text here...");

        TextField aField = new TextField();
        aField.setPromptText("Enter (a) value");

        TextField bField = new TextField();
        bField.setPromptText("Enter (b) value");

        TextArea outputArea = new TextArea();
        outputArea.setPromptText("Output will appear here...");
        outputArea.setEditable(false);
        outputArea.setWrapText(true);

        Button encryptButton = new Button("Encrypt");
        Button decryptButton = new Button("Decrypt");
        Button bruteForceButton = new Button("Brute Force Attack");

        encryptButton.setOnAction(e -> {
            try {
                int a = Integer.parseInt(aField.getText());
                int b = Integer.parseInt(bField.getText());
                String inputText = inputField.getText().toUpperCase();
                Encryption enc = new Encryption();
                enc.setA(a);
                enc.setB(b);
                enc.setPlain(inputText);
                enc.encrypt(a, b, inputText);
                outputArea.setText(enc.toString());
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter valid integers for a and b.");
            }
        });

        decryptButton.setOnAction(e -> {
            try {
                int a = Integer.parseInt(aField.getText());
                int b = Integer.parseInt(bField.getText());
                String inputText = inputField.getText().toUpperCase();
                Decryption dec = new Decryption();
                dec.setA(a);
                dec.setB(b);
                dec.setEncrypted(inputText);
                dec.decrypt(a, b, inputText);
                outputArea.setText(dec.toString());
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter valid integers for a and b.");
            }
        });

        bruteForceButton.setOnAction(e -> {
            String inputText = inputField.getText();
            String bruteForceResults = com.example.affine.Attack.bruteForceAttack(inputText);
            String outputFilePath = "C:\\Users\\ogoda\\Desktop\\affine_attack_results.txt";
            try {
                com.example.affine.Attack.writeToFile(outputFilePath, bruteForceResults);
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
            inputText = inputField.getText();
            bruteForceResults = Attack.bruteForceAttack(inputText);
            outputArea.setText(bruteForceResults);
        });

        VBox layout = new VBox(10, titleLabel, inputField, aField, bField, encryptButton, decryptButton, bruteForceButton, outputArea);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 450, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
