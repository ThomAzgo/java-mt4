package fr.hetic.Factory;

import impl.Addition;
import impl.Multiplication;
import impl.Substraction;
import Interface.Operation;

public class OperationFactory {
    public static Operation getOperation(String operation) {
        switch (operation) {
            case "+":
                return new Addition();
            case "-":
                return new Substraction();
            case "*":
                return new Multiplication();
        }
        return null;
    }
}