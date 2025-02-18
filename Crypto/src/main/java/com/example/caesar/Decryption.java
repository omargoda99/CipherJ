/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.caesar;

/**
 *
 * @author ogoda
 */
public class Decryption {
    private String encrypted;
    private int key;
    Decryption(String encrypted , int key){
        this.encrypted=encrypted;
        this.key=key;
    }
    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public String getEncrypted() {
        return encrypted;
    }

    public int getKey() {
        return key;
    }

    public String decrypt(String encrypted, int key){
        key %= 26;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<encrypted.length();i++){
            if(Character.isUpperCase(encrypted.charAt(i))){
                char c = (char) (((encrypted.charAt(i)-'A'-key+26)%26)+'A');
                stringBuilder.append(c);
            }
            else if(Character.isLowerCase(encrypted.charAt(i))){
                char c = (char) (((encrypted.charAt(i)-'a'-key+26)%26)+'a');
                stringBuilder.append(c);
            }
            else{
                stringBuilder.append(encrypted.charAt(i));
            }
        }
        return stringBuilder.toString();
    }
    @Override
    public String toString(){
        return decrypt(encrypted,key);
    }
}

// dec = (x-k) mod 26, k in [0,25]

