package com.company;

import java.util.Scanner; // imports the Scanner class.
public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in); //creates a new Scanner object.
        System.out.println("How many fruits do you want to purchase? ");
        int numberOfFruits = userInput.nextInt(); //store the number of fruits in the numberOfFruits integer.
        String[] fruits = new String[numberOfFruits]; //creates an array that can hold the fruits we want to purchase.
        for (int i = 0; i <fruits.length; i++) {
            System.out.println("Fruit: ");
            fruits[i] = userInput.nextLine();
            userInput.next();
        }
    }
}