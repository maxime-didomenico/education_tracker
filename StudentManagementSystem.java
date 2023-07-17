import java.sql.*;
import java.util.Scanner;


// Créer la classe StudentManagementSystem
public class StudentManagementSystem {

    // Créer une méthode pour ajouter un étudiant dans la base de données
    public static void addStudent(DatabaseManager dbManager, Scanner scanner) {

        dbManager = new DatabaseManager("jdbc:mysql://localhost:3306/students_db", "root", "1");
        dbManager.connect();
        
        System.out.println("Nom :");
        String lastname = scanner.nextLine();
        
        System.out.println("Prénom :");
        String name = scanner.nextLine();

        System.out.println("Age :");
        int age = scanner.nextInt();

        // Demander à l'utilisateur de saisir les notes séparées par des virgules
        System.out.println("Veuillez saisir les notes de l'étudiant séparées par des virgules : ");
        
        // Lire l'entrée comme une chaîne de caractères
        String input = scanner.nextLine();
        
        // Séparer l'entrée par les virgules et stocker les éléments dans un tableau de chaînes de caractères
        String[] grades = input.split(",");

        // Créer un tableau d'entiers pour stocker les notes
        int[] notes = new int[grades.length];
        
        // Parcourir le tableau de chaînes de caractères et convertir chaque élément en entier
        for (int i = 0; i < grades.length; i++) {
            // Convertir la chaîne de caractères en entier et stocker la valeur dans le tableau de notes
            notes[i] = Integer.parseInt(grades[i]);
        }

        // Créer une requête SQL pour insérer un étudiant dans la base de données
        String query = "INSERT INTO students (lastname, name, age) VALUES ('" + lastname + "', '" + name + "', " + age + ")";

        // Exécuter la requête SQL
        dbManager.executeQuery(query);
    }

    // Créer une méthode pour modifier un étudiant dans la base de données
    public static void updateStudent(DatabaseManager dbManager, Scanner scanner) {
        
        dbManager = new DatabaseManager("jdbc:mysql://localhost:3306/students_db", "root", "1");
        dbManager.connect();

        System.out.println("Veuillez saisir l'identifiant de l'étudiant à modifier :");
        int id = scanner.nextInt();

        System.out.println("Nouvelles informations de l'étudiant :");

        System.out.println("Nom :");
        String lastname = scanner.nextLine();

        System.out.println("Prénom :");
        String name = scanner.nextLine();

        System.out.println("Âge :");
        int age = scanner.nextInt();

        // Créer une requête SQL pour modifier un étudiant dans la base de données
        String query = "UPDATE students SET lastname = '" + lastname + "', name = '" + name + "', age = " + age + " WHERE id = " + id;

        // Exécuter la requête SQL

        dbManager.executeQuery(query);
    }

    // Créer une méthode pour supprimer un étudiant dans la base de données
    public static void deleteStudent(DatabaseManager dbManager, Scanner scanner) {
        
        dbManager = new DatabaseManager("jdbc:mysql://localhost:3306/students_db", "root", "1");
        dbManager.connect();

        System.out.println("Veuillez saisir l'identifiant de l'étudiant à supprimer :");
        int id = scanner.nextInt();

        // Créer une requête SQL pour supprimer un étudiant dans la base de données
        String query = "DELETE FROM students WHERE id = " + id;

        // Exécuter la requête SQL
        dbManager.executeQuery(query);
    }

    // Créer une méthode pour afficher tous les étudiants dans la base de données
    public static void displayAllStudents(DatabaseManager dbManager) {
            
        dbManager = new DatabaseManager("jdbc:mysql://localhost:3306/students_db", "root", "1");
        dbManager.connect();

        // Créer une requête SQL pour afficher tous les étudiants dans la base de données
        String query = "SELECT * FROM students";

        // Exécuter la requête SQL
        ResultSet result = dbManager.executeQuery(query);

        // Afficher les étudiants
        try {
            // Parcourir les lignes du résultat de la requête SQL
            System.out.println("Resultat : \n");
            while (result.next()) {
                // Récupérer l'identifiant, le nom, le prénom et l'âge de l'étudiant
                int id = result.getInt("id");
                String lastname = result.getString("lastname");
                String name = result.getString("name");
                int age = result.getInt("age");

                // Afficher les informations de l'étudiant
                System.out.println("Identifiant : " + id);
                System.out.println("Nom : " + lastname);
                System.out.println("Prénom : " + name);
                System.out.println("Âge : " + age);
                System.out.println();
            }
        } catch (Exception e) {
            // Gérer l'exception liée à l'affichage des étudiants
            System.out.println("Erreur : impossible d'afficher les étudiants.");
        }
    }

    // Créer une méthode pour rechercher un étudiant dans la base de données
    public static void searchStudent(DatabaseManager dbManager, Scanner scanner) {
            
            dbManager = new DatabaseManager("jdbc:mysql://localhost:3306/students_db", "root", "1");
            dbManager.connect();
    
            System.out.println("Veuillez saisir l'identifiant de l'étudiant à rechercher :");
            int id = scanner.nextInt();
    
            // Créer une requête SQL pour rechercher un étudiant dans la base de données
            String query = "SELECT * FROM students WHERE id = " + id;
    
            // Exécuter la requête SQL
            ResultSet result = dbManager.executeQuery(query);
    
            // Afficher les informations de l'étudiant
            try {
                // Parcourir les lignes du résultat de la requête SQL
                while (result.next()) {
                    // Récupérer l'identifiant, le nom, le prénom et l'âge de l'étudiant
                    int studentId = result.getInt("id");
                    String lastname = result.getString("lastname");
                    String name = result.getString("name");
                    int age = result.getInt("age");
    
                    // Afficher les informations de l'étudiant
                    System.out.println("Identifiant : " + studentId);
                    System.out.println("Nom : " + lastname);
                    System.out.println("Prénom : " + name);
                    System.out.println("Âge : " + age);
                    System.out.println();
                }
            } catch (Exception e) {
                // Gérer l'exception liée à l'affichage des informations de l'étudiant
                System.out.println("Erreur : impossible d'afficher les informations de l'étudiant.");
            }
    }

    // Créer la méthode principale du programme
    public static void main(String[] args) {
        // Créer un objet Scanner pour lire les entrées de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // Créer un objet DatabaseManager pour gérer la connexion à la base de données
        DatabaseManager dbManager = new DatabaseManager("jdbc:mysql://localhost:3306/students_db", "root", "1");

        // Établir la connexion à la base de données
        dbManager.connect();

        // Créer une variable pour stocker le choix de l'utilisateur
        int choice = 0;

        // Créer une boucle pour afficher le menu et exécuter les fonctionnalités
        do {
        // Afficher le menu avec les différentes options
        System.out.println("Bienvenue dans le système de gestion des étudiants.");
        System.out.println("Veuillez choisir une option :");
        System.out.println("1. Ajouter un étudiant");
        System.out.println("2. Modifier un étudiant");
        System.out.println("3. Supprimer un étudiant");
        System.out.println("4. Afficher tous les étudiants");
        System.out.println("5. Rechercher un étudiant");
        System.out.println("6. Quitter le programme");

        // Lire le choix de l'utilisateur
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            // Gérer l'exception liée à la saisie d'un choix invalide
            System.out.println("Erreur : le choix doit être un nombre entier.");
            choice = 0; // Réinitialiser le choix à 0 pour continuer la boucle
        }

        // Exécuter la fonctionnalité correspondant au choix de l'utilisateur
        switch (choice) {
            case 1:
            // Appeler la méthode pour ajouter un étudiant
            addStudent(dbManager, scanner);
            break;
            case 2:
            // Appeler la méthode pour modifier un étudiant
            updateStudent(dbManager, scanner);
            break;
            case 3:
            // Appeler la méthode pour supprimer un étudiant
            deleteStudent(dbManager, scanner);
            break;
            case 4:
            // Appeler la méthode pour afficher tous les étudiants
            displayAllStudents(dbManager);
            break;
            case 5:
            // Appeler la méthode pour rechercher un étudiant
            searchStudent(dbManager, scanner);
            break;
            case 6:
            // Afficher un message indiquant la fin du programme
            System.out.println("Merci d'avoir utilisé le système de gestion des étudiants. Au revoir.");
            break;
            default:
            // Afficher un message d'erreur si le choix est invalide
            System.out.println("Erreur : le choix doit être compris entre 1 et 6.");
            break;
        }
        } while (choice != 6); // Répéter la boucle tant que le choix n'est pas 6

        // Fermer la connexion à la base de données
        dbManager.close();

        // Fermer l'objet Scanner
        scanner.close();
    }
}