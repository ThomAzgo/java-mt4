package fr.hetic.Implementation

import Interface.Operation;

public class Multiplication implements Operation {
    @Override
    public double calcul(double a, double b) {
        return a * b;
    }
}