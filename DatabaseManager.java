import java.sql.*;


public class DatabaseManager {

  // Déclarer les attributs de la classe
  private String url; // L'URL de la base de données
  private String user; // Le nom d'utilisateur de la base de données
  private String password; // Le mot de passe de la base de données
  private Connection connection; // La connexion à la base de données

  // Créer le constructeur de la classe
  public DatabaseManager(String url, String user, String password) {
  this.url = url;
  this.user = user;
  this.password = password;
  this.connection = null;
  }

  // Créer la méthode connect pour établir la connexion à la base de données
  public void connect() {
    try {
      // Charger le driver JDBC pour MySQL
      Class.forName("com.mysql.cj.jdbc.Driver");

      // Établir la connexion à la base de données en utilisant les attributs de la classe
      connection = DriverManager.getConnection(this.url, this.user, this.password);

      // Afficher un message indiquant la réussite de la connexion
      System.out.println("Connexion à la base de données réussie.");
    } catch (ClassNotFoundException e) {

      // Gérer l'exception liée au chargement du driver JDBC
      System.out.println("Erreur : le driver JDBC n'a pas été trouvé.");
    } catch (SQLException e) {

      // Gérer l'exception liée à la connexion à la base de données
      System.out.println("Erreur : impossible de se connecter à la base de données.");
    }
  }

  // Créer la méthode executeQuery pour exécuter une requête SQL qui récupère des données et retourner le résultat
  public ResultSet executeQuery(String query) {
    try {
      // Créer un objet Statement pour exécuter une requête SQL
      Statement statement = this.connection.createStatement();

      // Exécuter la requête SQL et retourner le résultat
      return statement.executeQuery(query);
    } catch (SQLException e) {
      // Gérer l'exception liée à l'exécution de la requête SQL
      System.out.println("Erreur : impossible d'exécuter la requête SQL.");
      return null;
    }
  }

  // Créer la méthode executeUpdate pour exécuter une requête SQL qui modifie les données et retourner le nombre de lignes affectées
  public int executeUpdate(String query, Object[] parameters) {
    try {
      // Préparer la requête SQL avec des paramètres préparés
      PreparedStatement statement = this.connection.prepareStatement(query);
      // Remplacer les paramètres préparés par les valeurs fournies
      for (int i = 0; i < parameters.length; i++) {
        statement.setObject(i + 1, parameters[i]);
      }
      // Exécuter la requête SQL et retourner le nombre de lignes affectées
      return statement.executeUpdate();
    } catch (SQLException e) {
      // Gérer l'exception liée à l'exécution de la requête SQL
      System.out.println("Erreur : impossible d'exécuter la requête SQL.");
      return 0;
    }
  }

  // Créer la méthode close pour fermer la connexion à la base de données
  public void close() {
    try {
      // Fermer la connexion à la base de données
      this.connection.close();
      // Afficher un message indiquant la réussite de la fermeture
      System.out.println("Connexion à la base de données fermée.");
    } catch (SQLException e) {
      // Gérer l'exception liée à la fermeture de la connexion à la base de données
      System.out.println("Erreur : impossible de fermer la connexion à la base de données.");
    }
  }
}