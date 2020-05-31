
/*
 * BTS SIO - Option SLAM - 2020
 * Assure les échanges avec la base de données
 * en utilisant les paramètres de connexion récupérés 
 * dans la classe CParametesStockageBDD
 */

package services;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CBDD {

    protected CParametresStockageBDD parametresStockageBDD = null;
    Connection conn = null;
    Statement stmt = null;

    public CBDD(CParametresStockageBDD parametresStockageBDD) {
        this.parametresStockageBDD = parametresStockageBDD;
        try {
            Class.forName(parametresStockageBDD.getDriver()); // Chargement du driver
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *Renvoie les paramètres pour la connexion à la BDD
     * @return parametresStockageBDD
     */
    public CParametresStockageBDD getParametresStockageBDD() {
        return parametresStockageBDD;
    }
    
    /**
     *Connexion à la base de données 
     * @return booléen
     */
    public boolean connecter() {
        try {
            conn = DriverManager.getConnection(getParametresStockageBDD().getProtocole() + "//"
                    + getParametresStockageBDD().getIp() + "/"
                    + getParametresStockageBDD().getNomBase(),
                    getParametresStockageBDD().getUtilisateur(),
                    getParametresStockageBDD().getMotDePasse()
            );
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     *Déconnexion
     * @return booléen
     */
    public boolean deconnecter() {
        try {
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     *Mise à jour de la BDD (INSERT, DELETE, UPDATE)
     * @param requete
     * @return  nb de lignes affectées
     */
    public int executerRequeteUpdate(String requete) {
        try {
            stmt = conn.createStatement();
            return stmt.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    /**
     *Requetes dans la BDD
     * @param requete
     * @return objet ResultSet  
     */
    public ResultSet executerRequeteQuery(String requete) {
        try {
            stmt = conn.createStatement();
            return stmt.executeQuery(requete);
        } catch (SQLException ex) {
            Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
    public static String pretraiterChaineSQL(String s) {
        return s.trim().replace("'", "''");
    }
       
    
    /**
     * Test de connexion/déconnexion à la BDD
     * @param args
     */
    public static void main(String[] args) {
        CBDD bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
        if (bdd.connecter() == true) {

            bdd.deconnecter();
            System.out.println("Connexion OK");
        } else {
            System.out.println("Connexion impossible");
        }

    }

}


