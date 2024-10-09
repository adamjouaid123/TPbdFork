package com.tp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tp.data.Utilisateur;

public class UtilisateurDao {

    private Connection connection; // L'objet de connexion à la base de données

    // Constructeur pour injecter la connexion
    public UtilisateurDao(Connection connection) {
        this.connection = connection;
    }

    // Méthode pour insérer un utilisateur dans la base de données
    public void create(Utilisateur utilisateur) throws SQLException {
        String sql = "INSERT INTO utilisateur (id, nom, prenom) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, utilisateur.getId());
            statement.setString(2, utilisateur.getNom());
            statement.setString(3, utilisateur.getPrenom());
            statement.executeUpdate();
        }
    }

    // Méthode pour récupérer un utilisateur par son ID
    public Utilisateur getById(int id) throws SQLException {
        String sql = "SELECT * FROM utilisateur WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Créer un nouvel utilisateur avec les données récupérées
                return new Utilisateur(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom")
                );
            }
        }
        return null; // Retourne null si aucun utilisateur n'est trouvé
    }

    // Méthode pour récupérer tous les utilisateurs de la base de données
    public List<Utilisateur> getAll() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                // Ajouter chaque utilisateur à la liste
                utilisateurs.add(new Utilisateur(
                        resultSet.getInt("id"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom")
                ));
            }
        }
        return utilisateurs;
    }

    // Méthode pour mettre à jour un utilisateur
    public void update(Utilisateur utilisateur) throws SQLException {
        String sql = "UPDATE utilisateur SET nom = ?, prenom = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, utilisateur.getNom());
            statement.setString(2, utilisateur.getPrenom());
            statement.setInt(3, utilisateur.getId());
            statement.executeUpdate();
        }
    }

    // Méthode pour supprimer un utilisateur par son ID
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM utilisateur WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
