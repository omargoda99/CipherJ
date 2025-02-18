package com.example.affine;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Attack {
    public static void main() throws IOException { // Fix the main method signature
        File file = new File("C:\\Users\\ogoda\\Desktop\\affine_attack.txt"); // Remove extra quotes

        if (file.exists()) {
            String cipherText = readCipherText(file);
            System.out.println("Cipher text read from file: " + cipherText);

            String bruteForceResults = bruteForceAttack(cipherText);
            String outputFilePath = "C:\\Users\\ogoda\\Desktop\\affine_attack_results.txt"; // Remove extra quotes
            writeToFile(outputFilePath, bruteForceResults);

            System.out.println("Brute Force results written to file: " + outputFilePath);

            String readResults = readCipherText(new File(outputFilePath));
            System.out.println("Brute Force results read from file:\n" + readResults);
        } else {
            System.out.println("Cipher text file does not exist at: " + file.getAbsolutePath()); // Debugging info
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
        for (int a = 1; a <= 26; a++) {
            if (gcd(a, 26) == 1) {
                for (int b = 0; b < 26; b++) {
                    StringBuilder decrypted = new StringBuilder();
                    for (char ch : enc.toCharArray()) {
                        if (Character.isUpperCase(ch)) {
                            char dec =  (char) (((inverse(a) *
                                    ((ch + 'a' - b)) % 26)) + 'a');
                            decrypted.append(dec);
                        } else if (Character.isLowerCase(ch)) {
                            char dec =  (char) (((inverse(a) *
                                    ((ch + 'a' - b)) % 26)) + 'a');
                            decrypted.append(dec);
                        } else {
                            decrypted.append(ch);
                        }
                    }
                    results.append("Key (a = ").append(a).append(", b = ").append(b).append("): ")
                            .append(decrypted).append("\n");
                }
            }
        }
        return results.toString();
    }

    public static void writeToFile(String filePath, String data) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(data);
        }
    }

    public static int inverse(int a) {
        int inv = 1;
        for (int i = 0; i < 26; i++) {
            if ((a * i) % 26 == 1) {
                inv = i;
                break;
            }
        }
        return inv;
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
