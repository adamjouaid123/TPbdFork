package com.tp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.sql.Statement;
import com.tp.dao.UtilisateurDao;
import com.tp.service.UtilisateurService;
import com.tp.tool.Session;
import com.tp.data.Utilisateur;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

    private UtilisateurDao utilisateurDao;
    private UtilisateurService utilisateurService;

    @Before
    public void setUp() throws SQLException {

          // Créez un gestionnaire de transactions pour les tests
        Session session = new Session(true);
        try {
            session.open();    
            // Créez une déclaration SQL pour exécuter des requêtes SQL
            Statement statement = session.get().createStatement();

            // Exécutez un script SQL pour créer la table "Utilisateur"
            String createTableSQL = "CREATE TABLE Utilisateur (id INT PRIMARY KEY, nom VARCHAR(255), prenom VARCHAR(255))";
            statement.execute(createTableSQL);

            // Ajoutez des données initiales si nécessaire
            // String insertDataSQL = "INSERT INTO Utilisateur (id, nom, prenom) VALUES (1, 'Smith', 'Joe')";
            // statement.execute(insertDataSQL);

            // Fermez la connexion et la déclaration
            statement.close();
            session.close();
        } catch (SQLException e) {
            session.rollback();
            session.close();
            throw e;
        }

    }

    @After
    public void tearDown() throws SQLException {
            // Créez un gestionnaire de transactions pour les tests
            Session session = new Session(true);
        try {

            session.open();    
            // Créez une déclaration SQL pour exécuter des requêtes SQL
            Statement statement = session.get().createStatement();

            // Exécutez un script SQL pour supprimer la table "Utilisateur"
            String createTableSQL = "DROP TABLE Utilisateur";
            statement.execute(createTableSQL);

            // Fermez la connexion et la déclaration
            statement.close();
            session.close();
        } catch (SQLException e) {
            session.rollback();
            session.close();
            throw e;
        }

    }

    @Test
    public void testCreateAndRetrieveUser() throws SQLException {
        // Créez un utilisateur
        Utilisateur utilisateur = new Utilisateur(1, "John", "Doe");

        Session session = new Session (true);
        UtilisateurService utilisateurService = new UtilisateurService(session);
        // Créez l'utilisateur en utilisant le service
        utilisateurService.createUser(utilisateur);

        // Récupérez l'utilisateur par son ID en utilisant le service
        Utilisateur retrievedUser = utilisateurService.getUserById(1);

        // Vérifiez si l'utilisateur récupéré est le même que celui créé
        assertEquals("John", retrievedUser.getNom());
        assertEquals("Doe", retrievedUser.getPrenom());
    }

    
    @Test
    public void testUpdateUser() throws SQLException {
        // Créez un utilisateur
        Utilisateur utilisateur = new Utilisateur(1, "Alice", "Johnson");

        Session session = new Session (true);
        UtilisateurService utilisateurService = new UtilisateurService(session);
        // Créez l'utilisateur en utilisant le service
        utilisateurService.createUser(utilisateur);

        // Mettez à jour le nom de l'utilisateur en utilisant le service
        utilisateur.setNom("Bob");
        utilisateurService.updateUser(utilisateur);

        // Récupérez l'utilisateur mis à jour par son ID
        Utilisateur updatedUser = utilisateurService.getUserById(1);

        // Vérifiez si le nom a été mis à jour
        assertEquals("Bob", updatedUser.getNom());
    }

    @Test
    public void testDeleteUser() throws SQLException {
        // Créez un utilisateur
        Utilisateur utilisateur = new Utilisateur(1, "Jane", "Smith");

        Session session = new Session (true);
        UtilisateurService utilisateurService = new UtilisateurService(session);
        // Créez l'utilisateur en utilisant le service
        utilisateurService.createUser(utilisateur);

        // Supprimez l'utilisateur par son ID en utilisant le service
        utilisateurService.deleteUser(1);

        // Récupérez l'utilisateur après la suppression (doit être null)
        Utilisateur deletedUser = utilisateurService.getUserById(1);

        assertNull(deletedUser);
    }
    
}
