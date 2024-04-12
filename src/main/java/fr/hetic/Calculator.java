package fr.hetic;

import java.io.*;
import java.util.function.BinaryOperator;

public class Calculator {
    public static void main(String[] args) {
        double num1, num2;

        System.out.println("Veuillez saisir le chemin du dossier contenant les fichiers d'opérations:");

        String folderPath = System.console().readLine();

        if (folderPath == null || folderPath.isEmpty()) {
            folderPath = System.getProperty("user.dir");
        }

        File folder = new File(folderPath);

        System.out.println("Entrez le premier nombre:");
        num1 = Double.parseDouble(System.console().readLine());

        String operator = "";
        BinaryOperator<Double> operation = null;

        while (operation == null) {
            System.out.println("Entrez l'opérateur (+, -, *):");
            operator = System.console().readLine();
            operator = operator.replaceAll("\\s", "");

            operation = getOperation(operator);
        }

        System.out.println("Entrez le deuxième nombre:");
        num2 = Double.parseDouble(System.console().readLine());

        double result = operation.apply(num1, num2);

        writeResult(folder, operator, num1, num2);

        System.out.println("Le résultat est: " + result);
    }

    private static BinaryOperator<Double> getOperation(String operator) {
        switch (operator) {
            case "+":
                return (a, b) -> a + b;
            case "-":
                return (a, b) -> a - b;
            case "*":
                return (a, b) -> a * b;
            default:
                System.out.println("Opérateur invalide: " + operator);
                return null;
        }
    }

    private static String getOperatorName(String operator) {
        switch (operator) {
            case "+":
                return "Addtion";
            case "-":
                return "Soustraction";
            case "*":
                return "Multiplication";
            default:
                return "inconnu";
        }
    }

    private static void writeResult(File folder, String operator, double num1, double num2) {
        try {
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String operatorName = getOperatorName(operator);

            File operationFile = new File(folder, operatorName + ".op");
            File resultFile = new File(folder, operatorName + ".res");

            FileWriter operationWriter = new FileWriter(operationFile);
            operationWriter.write(String.format("%.2f %s %.2f", num1, operator, num2));
            operationWriter.close();

            FileWriter resultWriter = new FileWriter(resultFile);
            double result = getOperation(operator).apply(num1, num2);
            resultWriter.write(String.valueOf(result));
            resultWriter.close();

            System.out.println("Opération et résultat écrits avec succès.");
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture dans les fichiers.");
            e.printStackTrace();
        }
    }
}