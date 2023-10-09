package com.tp.data;

public class Utilisateur {

    private int id;
    private String nom;
    private String prenom;

    // Constructeur par défaut
    public Utilisateur() {
    }

    // Constructeur avec des paramètres
    public Utilisateur(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    // Getters et setters pour les attributs

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // Méthode toString pour l'affichage
    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
    }
}

