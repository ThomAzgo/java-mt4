package fr.hetic.Soustraction;

import Interface.Operation;

public class Substraction implements Operation {
    @Override
    public double calcul(double a, double b) {
        return a - b;
    }
}