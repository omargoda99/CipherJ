/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.caesar;

/**
 *
 * @author ogoda
 */
public class Encryption {
    private String plain;
    private int key;
    Encryption(String plain , int key){
        this.plain=plain;
        this.key=key;
    }
    public void setPlain(String plain) {
        this.plain = plain;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public String getPlain() {
        return plain;
    }
    public int getKey() {
        return key;
    }
    public String encrypt(String encrypted, int key){
        key %= 26;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<encrypted.length();i++){
            if(Character.isUpperCase(encrypted.charAt(i))){
                char c = (char) (((encrypted.charAt(i)-'A'+key+26)%26)+'A');
                stringBuilder.append(c);
            }
            else if(Character.isLowerCase(encrypted.charAt(i))){
                char c = (char) (((encrypted.charAt(i)-'a'+key+26)%26)+'a');
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
        return encrypt(plain,key);
    }
}

// enc = (x+k) mod 26, k in [0,25]


