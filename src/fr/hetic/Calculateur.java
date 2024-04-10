import java.io.*;
package fr.hetic;

public class Calculateur {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Calculateur <chemin_dossier>");
            return;
        }

        String dossier = args[0];

        File dossierFichiers = new File(dossier);
        File[] fichiers = dossierFichiers.listFiles();

        if (fichiers == null) {
            System.out.println("Le dossier spécifié est invalide.");
            return;
        }

        for (File fichier : fichiers) {
            if (fichier.isFile() && fichier.getName().endsWith(".op")) {
                processFile(fichier);
            }
        }
    }

    private static void processFile(File fichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String nomFichierRes = fichier.getAbsolutePath().replace(".op", ".res");
            BufferedWriter writer = new BufferedWriter(new FileWriter(nomFichierRes));

            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] elements = ligne.split(" ");
                try {
                    double num1 = Double.parseDouble(elements[0]);
                    double num2 = Double.parseDouble(elements[1]);
                    String operateur = elements[2];

                    double resultat = calculerResultat(num1, num2, operateur);
                    writer.write(String.valueOf(resultat));
                    writer.newLine();
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    writer.write("ERROR");
                    writer.newLine();
                }
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double calculerResultat(double num1, double num2, String operateur) {
        switch (operateur) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "x":
                return num1 * num2;
            default:
                throw new UnsupportedOperationException("Opérateur non supporté : " + operateur);
        }
    }
}