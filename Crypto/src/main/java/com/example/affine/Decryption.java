package com.example.affine;

public class Decryption {
    private int a;
    private int b;
    private int inverse_a;
    private String plain;
    Decryption(int a, int b, String plain){
        this.a=a;
        this.b=b;
        this.plain=plain;
    }
}
