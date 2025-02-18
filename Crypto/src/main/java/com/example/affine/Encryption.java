package com.example.affine;

public class Encryption {
    private int a;
    private int b;
    private String plain;

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setPlain(String plain) {
        this.plain = plain;
    }

    public String encrypt(int a, int b, String plain) {
        if (a < 0) a = (a % 26 + 26) % 26;
        if (b < 0) b = (b % 26 + 26) % 26;
        if (gcd(a, 26) != 1) {
            return "GCD of a and 26 is not 1. Please use a valid key.";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < plain.length(); i++) {
                char ch = plain.charAt(i);
                if (Character.isUpperCase(ch)) {
                    char enc = (char) ((((a * (ch - 'A')) + b) % 26) + 'A');
                    stringBuilder.append(enc);
                } else if (Character.isLowerCase(ch)) {
                    char enc = (char) ((((a * (ch - 'a')) + b) % 26) + 'a');
                    stringBuilder.append(enc);
                } else {
                    stringBuilder.append(ch);
                }
            }
            return stringBuilder.toString();
        }
    }

    private int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    @Override
    public String toString() {
        return "The Original Text is " + plain + " and the encryption of a = " + a + " and b = " + b + " is " + encrypt(a, b, plain);
    }
}
