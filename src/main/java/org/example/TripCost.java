package org.example;

import java.util.Scanner;

public class TripCost {

    public double calculateCost(double distance, double fuelPrice, double standardConsumption) {
        double fuelQuantity = fuelNeeded(distance, standardConsumption);
        return  fuelPrice * fuelQuantity;
    }

    public double fuelNeeded(double distance, double standardConsumption) {
        return (distance/100) * standardConsumption;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the driving distance: ");
        double distance = input.nextDouble();
        System.out.println("Enter the fuel price per litre: ");
        double fuelPrice = input.nextDouble();
        double standardConsumption = 5.0;

        TripCost tripCost = new TripCost();
        double cost = tripCost.calculateCost(distance, fuelPrice, standardConsumption);
        System.out.println("The cost of driving is $" + cost);


    }
}
