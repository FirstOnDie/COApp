package org.cexpositoce.coapp;

import lombok.Data;

@Data
public class test {
    private String testField;

    public static void main(String[] args) {
        test test = new test();
        test.setTestField("Hello, Lombok!");
        System.out.println(test.getTestField());
    }
}
