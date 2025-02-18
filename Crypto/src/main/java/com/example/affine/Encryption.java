package com.example.affine;

public class Encryption {
    private int a;
    private int b;
    private String encrypted;

    Encryption(int a, int b, String encrypted) {
        this.a = a;
        this.b = b;
        this.encrypted = encrypted;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public String encrypt(int a, int b, String encrypted) {
        if (a < 0) {
            a += 26;
            a %= 26;
        }
        if (b < 0) {
            b += 26;
            b %= 26;
        }
        a %= 26;
        b %= 26;
        if (gcd(a, 26) != 1) {
            return "GCD a and 26 is not 1 relax and try again with some focus :>";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < encrypted.length(); i++) {
                char ch = encrypted.charAt(i);
                if (Character.isUpperCase(ch)) {
                    char enc = (char) (((a * (ch - 'A') + b) % 26) + 'A');
                    stringBuilder.append(ch);
                } else if (Character.isLowerCase(ch)) {
                    char enc = (char) (((a * (ch - 'a') + b) % 26) + 'a');
                    stringBuilder.append(ch);
                }
                else{
                    stringBuilder.append(ch);
                }
            }
            return stringBuilder.toString();
        }
    }
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
