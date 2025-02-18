package com.example.caesar;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Attack {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\ogoda\\Desktop\\Ciphers\\cipher_text.txt");
        if (file.exists()) {
            String cipherText = readCipherText(file);
            System.out.println("Cipher text read from file: " + cipherText);
            String bruteForceResults = bruteForceAttack(cipherText);
            String outputFilePath = "C:\\Users\\ogoda\\Desktop\\Ciphers\\brute_force_results.txt";
            writeToFile(outputFilePath, bruteForceResults);
            System.out.println("Brute Force results written to file: " + outputFilePath);
            String readResults = readCipherText(new File(outputFilePath));
            System.out.println("Brute Force results read from file:\n" + readResults);
        } else {
            System.out.println("Cipher text file does not exist");
        }
    }

    public static String readCipherText(File file) throws IOException {
        StringBuilder cipherText = new StringBuilder();
        try (Scanner dataReader = new Scanner(file)) {
            while (dataReader.hasNextLine()) {
                cipherText.append(dataReader.nextLine()).append("\n");
            }
        }
        return cipherText.toString().trim();
    }

    public static String bruteForceAttack(String enc) {
        StringBuilder results = new StringBuilder();
        for (int i = 1; i <= 26; i++) {
            StringBuilder decrypted = new StringBuilder();
            for (char ch : enc.toCharArray()) {
                if (Character.isUpperCase(ch)) {
                    char c = (char) (((ch - 'A' - i + 26) % 26) + 'A');
                    decrypted.append(c);
                } else if (Character.isLowerCase(ch)) {
                    char c = (char) (((ch - 'a' - i + 26) % 26) + 'a');
                    decrypted.append(c);
                } else {
                    decrypted.append(ch);
                }
            }
            results.append("Key ").append(i).append(": ").append(decrypted.toString()).append("\n");
        }
        return results.toString();
    }

    public static void writeToFile(String filePath, String data) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(data);
        }
    }
}