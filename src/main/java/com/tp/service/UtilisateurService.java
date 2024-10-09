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
            utilisateurDao = new UtilisateurDao(session.get());
            utilisateurDao.create(utilisateur);
            session.commit();
        } catch (SQLException e) {
            session.rollback();
            throw e;
        } finally {
            session.close(); // Ensure session is closed even in case of an exception
        }
    }

    // Méthode pour récupérer un utilisateur par son ID
    public Utilisateur getUserById(int id) throws SQLException {
        try {
            session.open();
            utilisateurDao = new UtilisateurDao(session.get());
            Utilisateur utilisateur = utilisateurDao.getById(id); // Retrieve user by ID
            return utilisateur;
        } catch (SQLException e) {
            throw e;
        } finally {
            session.close(); // Ensure session is closed even in case of an exception
        }
    }

    // Méthode pour récupérer tous les utilisateurs
    public List<Utilisateur> getAllUsers() throws SQLException {
        try {
            session.open();
            utilisateurDao = new UtilisateurDao(session.get());
            List<Utilisateur> utilisateurs = utilisateurDao.getAll(); // Retrieve all users
            return utilisateurs;
        } catch (SQLException e) {
            throw e;
        } finally {
            session.close(); // Ensure session is closed even in case of an exception
        }
    }

    // Méthode pour mettre à jour un utilisateur
    public void updateUser(Utilisateur utilisateur) throws SQLException {
        try {
            session.open();
            utilisateurDao = new UtilisateurDao(session.get());
            utilisateurDao.update(utilisateur); // Update user
            session.commit();
        } catch (SQLException e) {
            session.rollback();
            throw e;
        } finally {
            session.close(); // Ensure session is closed even in case of an exception
        }
    }

    // Méthode pour supprimer un utilisateur par son ID
    public void deleteUser(int id) throws SQLException {
        try {
            session.open();
            utilisateurDao = new UtilisateurDao(session.get());
            utilisateurDao.delete(id); // Delete user by ID
            session.commit();
        } catch (SQLException e) {
            session.rollback();
            throw e;
        } finally {
            session.close(); // Ensure session is closed even in case of an exception
        }
    }
}
