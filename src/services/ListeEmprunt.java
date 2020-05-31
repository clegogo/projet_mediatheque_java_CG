/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;



import entites.Emprunt;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BTS sio
 */
public class ListeEmprunt {
     protected CBDD bdd;
      ArrayList[] clesEtrangeres2;
      ArrayList<Emprunt> listeEmprunts;
   final String requeteEmprunt = "SELECT utilisateur.nom_Utilisateur, ouvrage.titre_Ouvrage, "
           + "emprunte.date_retrait_Emprunt, emprunte.date_retour_Emprunt, utilisateur.email_Utilisateur "
           + "FROM `utilisateur`, `emprunte`, `ouvrage` "
           + "WHERE utilisateur.id_utilisateur_Utilisateur = emprunte.id_utilisateur_Utilisateur "
           + "AND ouvrage.id_officielle_Ouvrage = emprunte.id_officielle_Ouvrage ";


   
    /**
     * Crée un objet BDD 
     * @param bdd
     */
    public ListeEmprunt(CBDD bdd) {
        this.bdd = bdd;
    }
    
    
    /**
    * Récupération des valeurs des clés étrangères pour les ouvrages
    * @return clesEtrangeres
    */
    public ArrayList[] clesEtrangeres() {
        ResultSet rs = null;
        clesEtrangeres2 = new ArrayList[2];
        if (bdd.connecter() == true) {
            //Liste emprunte
            ArrayList<String> clesEmprunte = new ArrayList<>();
            String requete = "SELECT * FROM `emprunte`;";
            rs = bdd.executerRequeteQuery(requete);
            try {
                    while (rs.next()) {
                       clesEmprunte.add(rs.getString(1));
                       clesEmprunte.add(rs.getString(2));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            clesEtrangeres2[0] = clesEmprunte;
          bdd.deconnecter();
          
           //Liste utilisateurs
            ArrayList<String> clesUtilisateur = new ArrayList<>();
            requete = "SELECT * FROM `utilisateur`;";
            rs = bdd.executerRequeteQuery(requete);
            try {
                    while (rs.next()) {
                       clesUtilisateur.add(rs.getString(1));
                       clesUtilisateur.add(rs.getString(2));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            clesEtrangeres2[1] = clesUtilisateur;
          bdd.deconnecter();
               
            return clesEtrangeres2;
        } else {
            System.out.println("Echec de connexion à la BDD");
        }
        return null;
    }
    
    /**
 * récupération des emprunts
 * @return listeEmprunts
 */
public ArrayList<entites.Emprunt> chargerEmprunts() {
if (bdd.connecter() == true) {
listeEmprunts = new ArrayList<>();
            ResultSet rs = bdd.executerRequeteQuery(requeteEmprunt);
            try {
                while (rs.next()) {
                    Emprunt emprunt = convertirDonneesBDDenEmprunts(rs);
                    listeEmprunts.add(emprunt);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
            return listeEmprunts;
     } else {
            System.out.println("Echec de connexion à la BDD");
        }
        return null;
    }

/**
     * Transformation des résultats de requêtes dans la BDD en objets Emprunt
     * @param rs résultats de la requete Mysql
     * @return Emprunt
     */
    public Emprunt convertirDonneesBDDenEmprunts(ResultSet rs) {
        try {
     String nom_utilisateur = rs.getString(1);
     String titre_ouvrage = rs.getString(2);
       String date_retrait = rs.getString(3);
     String date_retour = rs.getString(4);
     String email_utilisateur = rs.getString(5);
     
     Emprunt emprunt = new Emprunt(nom_utilisateur, titre_ouvrage,  date_retrait, date_retour, email_utilisateur);                 
            return emprunt;
            
        } catch (SQLException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    /**
     *Test local emprunts
     * @param args
     */
    public static void main(String[] args) {
        CBDD bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
        ListeEmprunt testCatalogue = new ListeEmprunt(bdd);
        
        /*
        //Trouver les ouvrages
        ArrayList<Ouvrage> listeTestOuvrages = testCatalogue.chargerLesOuvrages();
        for (Ouvrage testOuvrage : listeTestOuvrages) {
            System.out.println("--");
            System.out.println("Titre : " + testOuvrage.getTitre()
                + "\nCote : " + testOuvrage.getCote()
                + "\nauteur : " + testOuvrage.getNom_Auteur());
        }
        */
         
         
         ArrayList<Emprunt> listeTestOuvrages = testCatalogue.chargerEmprunts();
        for (Emprunt testOuvrage : listeTestOuvrages) {
            System.out.println("--");
            System.out.println("nom : " + testOuvrage.getNom_utilisateur()
                + "\ntitre : " + testOuvrage.getTitre_ouvrage()
                + "\n : " + testOuvrage.getDate_retrait()
            + "\n date retour : " + testOuvrage.getDate_retour()
            + "\n mail: " + testOuvrage.getEmail_utilisateur());
            
        }
    } 
    
}