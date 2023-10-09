package com.tp.service;

import java.sql.SQLException;
import java.util.List;
import com.tp.dao.UtilisateurDao;
import com.tp.tool.Session;
import com.tp.data.Utilisateur;


public class UtilisateurService {

    private UtilisateurDao utilisateurDao;
    private Session session;

    // Constructeur pour injecter la connexion, le DAO et le gestionnaire de transactions
    public UtilisateurService(Session session) {
        this.session = session;
    }

    // Méthode pour créer un utilisateur
    public void createUser(Utilisateur utilisateur) throws SQLException {
        try {
            session.open();
            UtilisateurDao utilisateurDao = new UtilisateurDao(session.get());
            utilisateurDao.create(utilisateur);
            session.commit();
            session.close();
        } catch (SQLException e) {
            session.rollback();
            session.close();
            throw e;
        }
    }

    // Méthode pour récupérer un utilisateur par son ID
    public Utilisateur getUserById(int id) throws SQLException {
        try {
            session.open();
            Utilisateur utilisateur=null;;
            // A complérer
            session.close();
            return utilisateur;
        } catch (SQLException e) {
            session.close();
            throw e;
        }

    }

    // Méthode pour récupérer tous les utilisateurs
    public List<Utilisateur> getAllUsers() throws SQLException {
        try {
            session.open();
            List<Utilisateur> utilisateurs=null;;
            // A complérer
            session.close();
            return utilisateurs;
        } catch (SQLException e) {
            session.close();
            throw e;
        }

    }

    // Méthode pour mettre à jour un utilisateur
    public void updateUser(Utilisateur utilisateur) throws SQLException {
       // A complérer
    }

    // Méthode pour supprimer un utilisateur par son ID
    public void deleteUser(int id) throws SQLException {
        // A complérer
    }
}

