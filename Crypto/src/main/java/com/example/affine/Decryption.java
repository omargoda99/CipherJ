package com.example.affine;

public class Decryption {
    private int a;
    private int b;
    private String encrypted;

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public int inverse(int a){
        int inv = 0;
        for(int i=1;i<=25;i++){
            if((a*i)%26==1){
                inv=i;
            }
        }
        return inv;
    }

    public String decrypt(int a, int b , String encrypted){
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
                    char dec =  (char) (((inverse(a) *
                            ((ch + 'A' - b)) % 26)) + 'A');
                    stringBuilder.append(dec);
                } else if (Character.isLowerCase(ch)) {
                    char dec =  (char) (((inverse(a) *
                            ((ch + 'a' - b)) % 26)) + 'a');
                    stringBuilder.append(dec);
                }
                else{
                    stringBuilder.append(ch);
                }
            }
            return stringBuilder.toString();
        }
    }
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    @Override
    public String toString() {
        return "The Encrypted Text is " + encrypted + " and the decryption of a = " + a + " and b = " + b + " is " + decrypt(a, b, encrypted);
    }
}
