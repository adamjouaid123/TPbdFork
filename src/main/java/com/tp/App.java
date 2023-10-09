package com.tp;

import java.sql.SQLException;
import com.tp.tool.Session;
import com.tp.service.UtilisateurService;
import com.tp.data.Utilisateur;




public class App {

    public static void main(String[] args) throws SQLException {
        // Créez une connexion à votre base de données (par exemple, MySQL)
        Session session = new Session(false);

        try {

            // Créez un gestionnaire de transactions

            // Créez un service avec le DAO et le gestionnaire de transactions
            UtilisateurService utilisateurService = new UtilisateurService(session);

            // Exemple d'utilisation du service
            Utilisateur nouvelUtilisateur = new Utilisateur(1, "John", "Doe");
            utilisateurService.createUser(nouvelUtilisateur);

            Utilisateur utilisateurRecupere = utilisateurService.getUserById(1);
            System.out.println("Utilisateur récupéré : " + utilisateurRecupere);


        } catch (SQLException e) {
            e.printStackTrace();
            session.close();
        }
    }
}
