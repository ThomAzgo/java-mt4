package fr.hetic;

public class Calculateur {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Calculateur <num1> <num2> <operation>");
            return;
        }

        double num1 = 0;
        double num2 = 0;

        try {
            num1 = Double.parseDouble(args[0]);
            num2 = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Les deux premiers arguments doivent être des nombres.");
            return;
        }

        String operation = args[2];

        switch (operation) {
            case "add":
                System.out.println("Résultat: " + (num1 + num2));
                break;
            case "sub":
                System.out.println("Résultat: " + (num1 - num2));
                break;
            case "mul":
                System.out.println("Résultat: " + (num1 * num2));
                break;
            case "div":
                if (num2 != 0) {
                    System.out.println("Résultat: " + (num1 / num2));
                } else {
                    System.out.println("Erreur: Division par zéro.");
                }
                break;
            default:
                System.out.println("Opération non reconnue. Les opérations valides sont: add, sub, mul, div.");
                break;
        }
    }
}