/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author BTS sio
 */
public class Emprunt {
    private String nom_utilisateur;
    private String titre_ouvrage;
    private String date_retrait;
    private String date_retour;
    private String email_utilisateur;
    
    
    /**
     * constructeur d'emprunt
     * @param nom_utilisateur
     * @param titre_ouvrage
     * @param date_retrait
     * @param date_retour
     * @param email_utilisateur
     */
public Emprunt(String nom_utilisateur, String titre_ouvrage, String date_retrait, String date_retour, String email_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
        this.titre_ouvrage = titre_ouvrage;
        this.date_retrait = date_retrait;
        this.date_retour = date_retour;
        this.email_utilisateur = email_utilisateur;
}
   
    
    /**
     * @return the nom_utilisateur
     */
    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    /**
     * @param nom_utilisateur the nom_utilisateur to set
     */
    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    /**
     * @return the titre_ouvrage
     */
    public String getTitre_ouvrage() {
        return titre_ouvrage;
    }

    /**
     * @param titre_ouvrage the titre_ouvrage to set
     */
    public void setTitre_ouvrage(String titre_ouvrage) {
        this.titre_ouvrage = titre_ouvrage;
    }

    /**
     * @return the date_retrait
     */
    public String getDate_retrait() {
        return date_retrait;
    }

    /**
     * @param date_retrait the date_retrait to set
     */
    public void setDate_retrait(String date_retrait) {
        this.date_retrait = date_retrait;
    }

    /**
     * @return the date_retour
     */
    public String getDate_retour() {
        return date_retour;
    }

    /**
     * @param date_retour the date_retour to set
     */
    public void setDate_retour(String date_retour) {
        this.date_retour = date_retour;
    }

    /**
     * @return the email_utilisateur
     */
    public String getEmail_utilisateur() {
        return email_utilisateur;
    }

    /**
     * @param email_utilisateur the email_utilisateur to set
     */
    public void setEmail_utilisateur(String email_utilisateur) {
        this.email_utilisateur = email_utilisateur;
    }
    
    
    /*
    *un pitit test
    */
    public static void main(String[] args) {
     String nom_utilisateur = "Bob";
     String titre_ouvrage = "Necronomicon";
     String date_retrait = "1992";
     String date_retour = "un jour?";
     String email_utilisateur = "hplovecraft@gmail.com";
     
     Emprunt empruntTest = new Emprunt(nom_utilisateur, titre_ouvrage, date_retrait, date_retour, email_utilisateur);
     
     
     System.out.println("nom de l'utilisateur :" + empruntTest.nom_utilisateur + "\n titre de l'ouvrage :" + empruntTest.titre_ouvrage);
    }
    
}
