/*
 * BTS SIO - Option SLAM - 2020
 * Lecture / écriture des créateurs dans la base de données
 * de la médiathèque
 */
package services;

import entites.Createur;
import entites.Editeur;
import entites.Ouvrage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe qui manipule le catalogue des ouvrages 
 * de la médiathèque
 */
public class Catalogue {
    protected CBDD bdd;
    ArrayList[] clesEtrangeres;
    ArrayList<Ouvrage> listeOuvrages;
    ArrayList<Createur> listeCreateurs;
    final String requeteOuvrage = "SELECT Ouvrage.id_officielle_Ouvrage, type_ouvrage.nom_type_ouvrage_Type_ouvrage, ouvrage.titre_Ouvrage, "
                        + "createur.nom_createur_Createur, createur.prenom_createur_Createur, ouvrage.cote_Ouvrage, "
                        + "ouvrage.vignette_Ouvrage, pages_Ouvrage, duree_Ouvrage, editeur.nom_editeur_Editeur, "
                        + "categorie.nom_categorie_Categorie, univers.nom_univers_Univers, edite_par.annee_edition, "
                        + "nom_langue_Langue, format_physique_Ouvrage, " 
                        + "catalogue.etat_catalogue_Catalogue, type_createur.type_createur_Type_createur, "
                        + "cree_par.date_creation_Cree_par, Type_editeur.type_editeur_Type_editeur "                        
                    + "FROM `ouvrage`, `univers`, `type_ouvrage`,`categorie`, `catalogue`, `type_createur`, `Cree_par`, "
                        + "`createur`, `langue`, `pays`, `edite_par`, `editeur`, `type_editeur` " 
                    + "WHERE Ouvrage.id_officielle_Ouvrage = Cree_par.id_officielle_Ouvrage " 
                    + "AND cree_par.id_createur_Createur = createur.id_createur_Createur " 
                    + "AND type_createur.id_type_createur_Type_createur = createur.id_type_createur_Type_createur " 
                    + "AND Ouvrage.code_langue_Langue = Langue.code_langue_Langue " 
                    + "AND ouvrage.id_type_ouvrage_Type_ouvrage = type_ouvrage.id_type_ouvrage_Type_ouvrage " 
                    + "AND ouvrage.id_categorie_Categorie = categorie.id_categorie_Categorie " 
                    + "AND ouvrage.id_univers_Univers = univers.id_univers_Univers "
                    + "AND ouvrage.id_catalogue_Catalogue = catalogue.id_catalogue_Catalogue " 
                    + "AND cree_par.id_pays_Pays = pays.id_pays_Pays " 
                    + "AND ouvrage.id_officielle_Ouvrage = edite_par.id_officielle_Ouvrage " 
                    + "AND edite_par.id_editeur_Editeur = editeur.id_editeur_Editeur " 
                    + "AND type_editeur.id_type_editeur_Type_editeur = editeur.id_type_editeur_Type_editeur ";

    /**
     * Crée un objet BDD 
     * @param bdd
     */
    public Catalogue(CBDD bdd) {
        this.bdd = bdd;
    }
    
    
    /**
    * Récupération des valeurs des clés étrangères pour les ouvrages
    * @return clesEtrangeres
    */
    public ArrayList[] clesEtrangeres() {
        ResultSet rs = null;
        clesEtrangeres = new ArrayList[7];
        if (bdd.connecter() == true) {
            //Liste des Types d'ouvrages
            ArrayList<String> clesTypes = new ArrayList<>();
            String requete = "SELECT * FROM `Type_ouvrage`;";
            rs = bdd.executerRequeteQuery(requete);
            try {
                    while (rs.next()) {
                       clesTypes.add(rs.getString(1));
                       clesTypes.add(rs.getString(2));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            clesEtrangeres[0] = clesTypes;
            
            //Liste des langues
            ArrayList<String> clesLangues = new ArrayList<>();
            requete = "SELECT * FROM `Langue`;";
            rs = bdd.executerRequeteQuery(requete);
            try {
                    while (rs.next()) {
                       clesLangues.add(rs.getString(1));
                       clesLangues.add(rs.getString(2));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            clesEtrangeres[1] = clesLangues;
            
            //Liste des état stock
            ArrayList<String> clesStock = new ArrayList<>();
            requete = "SELECT * FROM `catalogue`;";
            rs = bdd.executerRequeteQuery(requete);
            try {
                    while (rs.next()) {
                       clesStock.add(rs.getString(1));
                       clesStock.add(rs.getString(2));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            clesEtrangeres[2] = clesStock;
            
            //Liste des catégories
            ArrayList<String> clesCateg = new ArrayList<>();
            requete = "SELECT * FROM `categorie`;";
            rs = bdd.executerRequeteQuery(requete);
            try {
                    while (rs.next()) {
                       clesCateg.add(rs.getString(1));
                       clesCateg.add(rs.getString(2));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            clesEtrangeres[3] = clesCateg;
            
            //Liste des univers
            ArrayList<String> clesUniv = new ArrayList<>();
            requete = "SELECT * FROM `univers`;";
            rs = bdd.executerRequeteQuery(requete);
            try {
                    while (rs.next()) {
                       clesUniv.add(rs.getString(1));
                       clesUniv.add(rs.getString(2));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            clesEtrangeres[4] = clesUniv;
            
             //Liste des Types de créateurs
            ArrayList<String> clesCeateurs = new ArrayList<>();
            requete = "SELECT * FROM `Type_createur`;";
            rs = bdd.executerRequeteQuery(requete);
            try {
                    while (rs.next()) {
                       clesCeateurs.add(rs.getString(1));
                       clesCeateurs.add(rs.getString(2));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            clesEtrangeres[5] = clesCeateurs;
            
            //Liste des Types de éditeurs
            ArrayList<String> clesEditeurs = new ArrayList<>();
            requete = "SELECT * FROM `Type_editeur`;";
            rs = bdd.executerRequeteQuery(requete);
            try {
                    while (rs.next()) {
                       clesEditeurs.add(rs.getString(1));
                       clesEditeurs.add(rs.getString(2));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
                }
            clesEtrangeres[6] = clesEditeurs;
            
            bdd.deconnecter();
               
            return clesEtrangeres;
        } else {
            System.out.println("Echec de connexion à la BDD");
        }
        return null;
    }
    
    
    /**
    * Récupération de tous les ouvrages dans la BDD
    * @return listeOuvrages 

    */
    public ArrayList<Ouvrage> chargerLesOuvrages() {
        if (bdd.connecter() == true) {
            listeOuvrages = new ArrayList<>();
            ResultSet rs = bdd.executerRequeteQuery(requeteOuvrage);
            try {
                while (rs.next()) {
                    Ouvrage ouvrage = convertirDonneesBDDenOuvrages(rs);
                    listeOuvrages.add(ouvrage);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
            return listeOuvrages;
        } else {
            System.out.println("Echec de connexion à la BDD");
        }
        return null;
    }
    
    /**
     * Vérifie si le créateur ou l'éditeur de l'ouvrage existe déjà dans la BDD 
     * @param nomCreateur
     * @param prenom
     * @param nomEditeur
     * @return booléen
     */
    public boolean[] existeCreateurEditeur(String nomCreateur, String prenom, String nomEditeur) {
        boolean[] existe = {false,false}; 
        for (Ouvrage cetOuvrage : listeOuvrages) {
            boolean createurExiste = (cetOuvrage.getNom_Auteur().equalsIgnoreCase(nomCreateur)
                    && cetOuvrage.getPrenom_Auteur().equalsIgnoreCase(prenom));
            boolean editeurExiste = (cetOuvrage.getNom_Auteur().equalsIgnoreCase(nomEditeur));
            if (createurExiste){
                existe[0] = true;}
            if (editeurExiste){
                existe[1] = true;}
            if (existe[0] == true && existe[1] == true) {
                     break;
            }
        }
        return existe;
    }

    /**
     * Récupération des créateurs dans la BDD
     * @param nom
     * @param prenom
     * @return listeCreateurs
     */
    public ArrayList<Createur> chargerLesCreateurs(String nom, String prenom) {
        if (bdd.connecter() == true) {
            ArrayList<Createur> listeCreateurs = new ArrayList<>();
            ResultSet rs = bdd.executerRequeteQuery(
                "SELECT id_createur_Createur, nom_createur_Createur, prenom_createur_Createur, date_naissance_Createur, Type_createur.type_createur_Type_createur"
                + " FROM Createur, Type_createur" 
                + " WHERE Createur.id_type_createur_Type_createur = Type_createur.id_type_createur_Type_createur"
                + " AND nom_createur_Createur = '" + nom
                + "' AND prenom_createur_Createur = '" + prenom + "'");
            try {
                while (rs.next()) {
                    Createur createur = convertirDonneesBDDenCreateurs(rs);
                    listeCreateurs.add(createur);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
            return listeCreateurs;
        } else {
            System.out.println("Echec de connexion à la BDD");
        }
        return null;
    }
    
     /**
     * Récupération des éditeurs dans la BDD
     * @param nom
     * @return listeEditeurs
     */
    public ArrayList<Editeur> chargerLesEditeurs(String nom) {
        if (bdd.connecter() == true) {
            ArrayList<Editeur> listeEditeurs = new ArrayList<>();
            ResultSet rs = bdd.executerRequeteQuery(
                "SELECT id_editeur_Editeur, nom_editeur_Editeur, ville_editeur_Editeur, Type_editeur.type_editeur_Type_editeur"
                + " FROM Editeur, Type_editeur" 
                + " WHERE Editeur.id_type_editeur_Type_editeur = Type_editeur.id_type_editeur_Type_editeur"
                + " AND nom_editeur_Editeur = '" + nom + "'");
            try {
                while (rs.next()) {
                    Editeur editeur = convertirDonneesBDDenEditeurs(rs);
                    listeEditeurs.add(editeur);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
            return listeEditeurs;
        } else {
            System.out.println("Echec de connexion à la BDD");
        }
        return null;
    }
    
    
    /**
     * Ajoute un créateur dans la BDD
     * @param attributsCreateur
     * @return
     */
    public int insererCreateur(ArrayList<String> attributsCreateur) {
        int res = -1;
        ResultSet rs = null;
        int idType;
        int idCreateur = -1;
        
        //convertit le type de créateur de String à int
        String type = attributsCreateur.get(3); // Nom du type saisi
        int indexType = clesEtrangeres[5].indexOf(type); // index du type dans la liste
        idType = Integer.parseInt(clesEtrangeres[5].get(indexType - 1).toString()); // id du type
        
        //Requête d'insertion dans la BDD
        if (bdd.connecter() == true) {
            String requete = "INSERT INTO `Createur` (`nom_createur_Createur`, `prenom_createur_Createur`, "
                       + "`date_naissance_Createur`, `id_type_createur_Type_createur`) VALUES "
                       + "('" + attributsCreateur.get(0)
                       + "', '" + attributsCreateur.get(1)
                       + "', '" + attributsCreateur.get(2)
                       + "', " + idType + ");";
            res = bdd.executerRequeteUpdate(requete);
            if (res !=1 ){
               System.out.println("Echec de la mise à jour de la BDD"); 
            }
            bdd.deconnecter();
        } else {
            System.out.println("Echec de connexion à la BDD");
        }   
        
        //Récupère le créateur enregistré dans la BDD
        if (bdd.connecter() == true) {
            String  requeteID = "SELECT `id_createur_Createur` "
                        + "FROM `Createur` "
                        + "WHERE `nom_createur_Createur` = '" + attributsCreateur.get(0)
                        + "' AND `prenom_createur_Createur` = '" + attributsCreateur.get(1)
                        + "' AND `date_naissance_Createur` = '" + attributsCreateur.get(2)
                        + "' AND `id_type_createur_Type_createur` = " + idType + ";";
            rs = bdd.executerRequeteQuery(requeteID);
            try{
                while (rs.next()) {
                    idCreateur = rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
        } 
        return idCreateur;
    }
    
        
    /**
     * Ajoute un éditeur dans la BDD
     * @param nomEditeur
     * @return
     */
    public int insererEditeur(ArrayList<String> attributsEditeur) {
        int res = -1;
        ResultSet rs = null;
        int idType;
        int idEditeur = -1;
        
        //convertit le type d'éditeur de String à id
        String type = attributsEditeur.get(2); // Nom du type saisi
        int indexType = clesEtrangeres[6].indexOf(type); // index du type dans la liste
        idType = Integer.parseInt(clesEtrangeres[6].get(indexType - 1).toString()); // id du type
        
        //Requête d'insertion dans la BDD
        if (bdd.connecter() == true) {
            String requete = "INSERT INTO `Editeur` (`nom_editeur_Editeur`, `ville_editeur_Editeur`, "
                        + "`id_type_editeur_Type_editeur`) VALUES "
                        + "('" + attributsEditeur.get(0)
                        + "', '" + attributsEditeur.get(1)
                        + "', " + idType + ");";
            res = bdd.executerRequeteUpdate(requete);
            bdd.deconnecter();
        } else {
            System.out.println("Echec de connexion à la BDD");
        } 

        //Récupère l'éditeur enregistré dans la BDD
        if (bdd.connecter() == true) {
            String  requeteID = "SELECT `id_editeur_Editeur` "
                        + "FROM `Editeur` "
                        + "WHERE `nom_editeur_Editeur` = '" + attributsEditeur.get(0)
                        + "' AND `ville_editeur_Editeur` = '" + attributsEditeur.get(1)
                        + "' AND `id_type_editeur_Type_editeur` = " + idType + ";";
            rs = bdd.executerRequeteQuery(requeteID);
            try{
                while (rs.next()) {
                    idEditeur = rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            bdd.deconnecter();
        } 
        return res;
    }
    
    /**
     * Ajoute un ouvrage dans la BDD
     * @param attributsOuvrage
     * @param idCreateur
     * @param idEditeur
     * @return result of query
     */
    public int insererOuvrage(ArrayList<String> attributsOuvrage, int idCreateur, int idEditeur) {
        int res = -1;
        int idLangue;
        int idType;
        int idCatalogue;
        int idCateg;
        int idUniv;

         //conversion choix combo de texte à id 
        String type = attributsOuvrage.get(1); // Nom du type saisi
        int indexType = clesEtrangeres[0].indexOf(type); // index du type dans la liste
        idType = Integer.parseInt(clesEtrangeres[0].get(indexType - 1).toString()); // id du type
        
        String langue = attributsOuvrage.get(8); // Nom de la langue saisie
        int indexLangue = clesEtrangeres[1].indexOf(langue); // index de la langue dans la liste
        idLangue = Integer.parseInt(clesEtrangeres[1].get(indexLangue - 1).toString()); // id de la langue
        
        String catalogue = attributsOuvrage.get(9); // Nom de l'état stock saisi
        int indexCatalogue = clesEtrangeres[2].indexOf(catalogue); // index de l'état stock dans la liste
        idCatalogue = Integer.parseInt(clesEtrangeres[2].get(indexCatalogue - 1).toString()); // id de l'état stock
        
        String categ = attributsOuvrage.get(10); // Nom de la catégorie saisie
        int indexCateg = clesEtrangeres[3].indexOf(categ); // index de la catégorie dans la liste
        idCateg = Integer.parseInt(clesEtrangeres[3].get(indexCateg - 1).toString()); // id de la catégorie
        
        String univ = attributsOuvrage.get(11); // Nom de l'univers saisi
        int indexUniv = clesEtrangeres[4].indexOf(univ); // index de l'univers dans la liste
        idUniv = Integer.parseInt(clesEtrangeres[4].get(indexUniv - 1).toString()); // id de l'univers
        
    
        //Requête d'insertion de l'ouvrage dans la BDD
        if (bdd.connecter() == true) {
            String requete = "INSERT INTO `Ouvrage` (`id_officielle_Ouvrage`, `id_type_ouvrage_Type_ouvrage`, `titre_Ouvrage`, `pages_Ouvrage`, "
                      + "`code_langue_Langue`, `id_catalogue_Catalogue`, `id_categorie_Categorie`, `id_univers_Univers`, `cote_Ouvrage`) VALUES ('"
                        + attributsOuvrage.get(0)
                        + "', " + idType
                        + ", '" + attributsOuvrage.get(2) 
                        + "', " + attributsOuvrage.get(7) 
                        + ", " + idLangue
                        + ", " + idCatalogue
                        + ", " + idCateg
                        + ", " + idUniv
                        + ", '" + attributsOuvrage.get(12) + "');";
            res = bdd.executerRequeteUpdate(requete);
            bdd.deconnecter();
        } else {
            System.out.println("Echec de connexion à la BDD");
        } 
        
         //Requête d'insertion de l'association de création
        if (bdd.connecter() == true) {
            String requete = "INSERT INTO `Cree_par` (`id_officielle_Ouvrage`, `id_createur_Createur`) VALUES "
                        + "('" + attributsOuvrage.get(0)
                        + "', " + idCreateur + ");";
            res += bdd.executerRequeteUpdate(requete);
            bdd.deconnecter();
        } else {
            System.out.println("Echec de connexion à la BDD");
        } 
        
         //Requête d'insertion de l'association d'édition
        if (bdd.connecter() == true) {
            String requete = "INSERT INTO `edite_par` (`id_officielle_Ouvrage`, `id_editeur_Editeur`, "
                        + "`annee_edition`) VALUES "
                        + "('" + attributsOuvrage.get(0)
                        + "', " + idEditeur
                        + ", '" + attributsOuvrage.get(6) + "');";
            res += bdd.executerRequeteUpdate(requete);
            bdd.deconnecter();
        } else {
            System.out.println("Echec de connexion à la BDD");
        } 
        
        return res;
   }
   
    
     /**
     * Transformation des résultats de requêtes dans la BDD en objets Ouvrage
     * @param rs résultats de la requete Mysql
     * @return Ouvrage
     */
    public Ouvrage convertirDonneesBDDenOuvrages(ResultSet rs) {
        try {
            String id = rs.getString(1);
            String type = rs.getString(2);
            String titre = rs.getString(3);
            String nom_auteur = rs.getString(4);
            String prenom_auteur = rs.getString(5);
            String cote = rs.getString(6);
            String vignette = rs.getString(7);
            int pages = rs.getInt(8);
            int duree = rs.getInt(9);
            String editeur = rs.getString(10);
            String categorie = rs.getString(11);
            String univers = rs.getString(12);
            String annee = rs.getString(13);
            String langue = rs.getString(14);
            String format = rs.getString(15);
               
            Ouvrage ouvrage = new Ouvrage(id, type, titre, nom_auteur, prenom_auteur, cote, vignette, pages, duree,
            editeur, categorie, univers, annee, langue, format);
        
            return ouvrage;
        } catch (SQLException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
 
     /**
     * Transformation des résultats de requêtes dans la BDD en objets Createur
     * @param rs résultats de la requete Mysql
     * @return Createur
     */
    public Createur convertirDonneesBDDenCreateurs(ResultSet rs) {
        try {
            int id = rs.getInt(1);
            String nom = rs.getString(2);
            String prenom = rs.getString(3);
            String naissance = rs.getString(4);
            String type = rs.getString(5);
            
            Createur createur = new Createur(id, nom, prenom, naissance, type);
            return createur;
            
        } catch (SQLException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
    * Transformation des résultats de requêtes dans la BDD en objets Editeur
    * @param rs résultats de la requete Mysql
    * @return Editeur
    */
    public Editeur convertirDonneesBDDenEditeurs(ResultSet rs) {
        try {
            int id = rs.getInt(1);
            String nom = rs.getString(2);
            String ville = rs.getString(3);
            String type = rs.getString(4);
            
            Editeur editeur = new Editeur(id, nom, ville, type);
            return editeur;
            
        } catch (SQLException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
     /**
     * Recherche d'une chaîne de caractère dans un texte
     * 
     * @param texte source
     * @param chaine caractères recherchés
     * @return boolean
     */
    public boolean contientChaine(String texte, String chaine) {
        return texte.toLowerCase().contains(chaine.toLowerCase());
    }
    
    /**
     * Sélection des ouvrages du catalogue qui correspondent aux critères de recherche passés 
     * @param critere champ des données dans lequel rechercher
     * @param mot_cle texte à rechercher
     * @return
     */
    public ArrayList<Ouvrage> filtrerOuvrages(int critere, String mot_cle) {
        ArrayList<Ouvrage> resultatDeRecherche = new ArrayList<>();
        
        for (Ouvrage cetOuvrage : this.listeOuvrages) {   //parcours le catalogue complet
            String texte = new String();
            switch (critere) {       //Identifie le(s) champ(s) à chercher   
               case 2:
                   texte = cetOuvrage.getTitre();
                    break;
                case 3:
                    texte = cetOuvrage.getNom_Auteur() + cetOuvrage.getPrenom_Auteur();
                    break;
                case 4:
                    texte = cetOuvrage.getId();
                    break;
                default:
                    texte = cetOuvrage.getTitre() + cetOuvrage.getNom_Auteur() 
                          + cetOuvrage.getPrenom_Auteur() + cetOuvrage.getId();
                    break;
            }
            
            if (this.contientChaine(texte, mot_cle)) { //effectue la recherche 
                resultatDeRecherche.add(cetOuvrage);   
          
            }           
        }
        return resultatDeRecherche;   //Liste des ouvrages correspondants à la recherche
    }
    
       
    
    /**
     *Test local de récupération / sauvegarde d'ouvrages dans la BDD
     * @param args
     */
    public static void main(String[] args) {
        CBDD bdd = new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
        Catalogue testCatalogue = new Catalogue(bdd);
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
     /*     
        //Trouver les créateurs
        String nom = "Mieville";
        String prenom = "China";
        ArrayList<Createur> listeTestCreateurs = testCatalogue.chargerLesCreateurs(nom, prenom);
        for (Createur testCreateur : listeTestCreateurs) {
            System.out.println("--");
            System.out.println("Nom : " + testCreateur.getNom()
                + "\nprenom : " + testCreateur.getPrenom()
                + "\ntype : " + testCreateur.getType());
        }
         */
        /*
         //Enregistrer un ouvrage
       ArrayList<String> ouvrage= new ArrayList<>();
         int idCreateur = 1;
         int idEditeur = 1;
         ouvrage.add("ISBNxxx");
         ouvrage.add("Livre");
         ouvrage.add("Le titre");
         ouvrage.add("un nom");
         ouvrage.add("un prenom");
         ouvrage.add("editeur");
         ouvrage.add("2020");
         ouvrage.add("50");
         ouvrage.add("Français");
         ouvrage.add("En stock");
         ouvrage.add("Science");
        ouvrage.add("1");
         ouvrage.add("TEST-8");
         
       
         testCatalogue.insererOuvrage (ouvrage, idCreateur, idEditeur);
          System.out.println("done");
     */
    
  /*
  String id ="94949494949";
  String titre = "testsuppression";
  testCatalogue.supprimerOuvrage(id, titre);
  System.out.println("done");
  */
    }
    
    
    /**
     * Supprime un ouvrage dans la BDD
     * @param id
     * @param titre
     * @return result of query
     */
   public int supprimerOuvrage(String id, String titre) {
       
       int res = 0;
       if (bdd.connecter() == true){
      String requete = "DELETE FROM ouvrage WHERE `ouvrage`.`id_officielle_Ouvrage` ='" + id +"'AND `ouvrage`.`titre_ouvrage` ='" + titre + "';";
      res =bdd.executerRequeteUpdate(requete);
       }
       else{
           System.out.println("Echec de la connection à la BDD");
       }
       return res;
   }
    
}
