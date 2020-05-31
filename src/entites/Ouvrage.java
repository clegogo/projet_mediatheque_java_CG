/*
 * BTS SIO - Option SLAM - 2020
 * Classe pour les objets ouvrages  du catalogue de la médiathèque 
 */
package entites;

public class Ouvrage {

    private String id;
    private String type;
    private String titre;
    private String vignette;
    private String nom_auteur;
    private String prenom_auteur;
    private String cote;
    private int pages;
    private int duree;
    private String editeur;
    private String categorie;
    private String univers;
    private String annee;
    private String langue;
    private String format;

    /**
     *Constructeur d'ouvrage
     * @param id
     * @param type
     * @param titre
     * @param nom_auteur
     * @param prenom_auteur
     * @param cote
     * @param vignette
     * @param pages
     * @param duree
     * @param editeur
     * @param categorie
     * @param univers
     * @param annee
     * @param langue
     * @param format
     */
    public Ouvrage(String id, String type, String titre, String nom_auteur, String prenom_auteur, String cote, String vignette, 
           int pages, int duree, String editeur, String categorie, String univers, String annee, String langue, String format) {
        this.id = id;
        this.type = type;
        this.titre = titre;
        this.nom_auteur = nom_auteur;
        this.prenom_auteur = prenom_auteur;
        this.cote = cote;
        this.vignette = vignette;
        this.pages = pages;
        this.duree = duree;
        this.editeur = editeur;
        this.categorie = categorie;
        this.univers = univers;
        this.annee = annee;
        this.langue = langue;
        this.format = format;
    }
    
    /**
     *Constructeur d'ouvrage partiel
     * (données de la table ouvrage uniquement
     *  - pas de jointure)
     * @param id
     * @param titre
     * @param cote
     * @param vignette
     * @param pages
     * @param duree
     */
    public Ouvrage(String id, String titre, String cote, String vignette, int pages, int duree) {
        this.id = id;
        this.titre = titre;
        this.cote = cote;
        this.vignette = vignette;
        this.pages = pages;
        this.duree = duree;
    }
    
        /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }

    /**
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * @return the nom_auteur
     */
    public String getNom_Auteur() {
        return nom_auteur;
    }

    /**
     * @param nom_auteur the nom_auteur to set
     */
    public void setNom_Auteur(String nom_auteur) {
        this.nom_auteur = nom_auteur;
    }
    
    /**
     * @return the prenom_auteur
     */
    public String getPrenom_Auteur() {
        return prenom_auteur;
    }

    /**
     * @param prenom_auteur the prenom_auteur to set
     */
    public void setPrenomAuteur(String prenom_auteur) {
        this.prenom_auteur = prenom_auteur;
    }

    /**
     * @return the cote
     */
    public String getCote() {
        return cote;
    }

    /**
     * @param cote the cote to set
     */
    public void setCote(String cote) {
        this.cote = cote;
    }

    /**
     * @return the pages
     */
    public int getPages() {
        return pages;
    }

    /**
     * @param pages the pages to set
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * @return the duree
     */
    public int getDuree() {
        return duree;
    }

    /**
     * @param duree the duree to set
     */
    public void setDuree(int duree) {
        this.duree = duree;
    }

    /**
     * @return the editeur
     */
    public String getEditeur() {
        return editeur;
    }

    /**
     * @param editeur the editeur to set
     */
    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    /**
     * @return the categorie
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * @param categorie the categorie to set
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * @return the univers
     */
    public String getUnivers() {
        return univers;
    }

    /**
     * @param univers the univers to set
     */
    public void setUnivers(String univers) {
        this.univers = univers;
    }

    /**
     * @return the annee
     */
    public String getAnnee() {
        return annee;
    }

    /**
     * @param annee the annee to set
     */
    public void setAnnee(String annee) {
        this.annee = annee;
    }

    /**
     * @return the langue
     */
    public String getLangue() {
        return langue;
    }

    /**
     * @param langue the langue to set
     */
    public void setLangue(String langue) {
        this.langue = langue;
    }

    /**
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format the format to set
     */
    public void setFormat(String format) {
        this.format = format;
    }


  
    /*
     * Test local
     */
    public static void main(String[] args) {
        String id = "unISBN";
        String type = "Livre";
        String titre = "titre";
        String nom_auteur = "auteur";
        String prenom_auteur = "prenom";
        String cote = "la cote";
        String vignette = "la vignette";
        int pages = 100;
        int duree = -1;
        String editeur = "l'editeur";
        String categorie = "roman";
        String univers = "Policier";
        String annee = "2020";
        String langue = "langue";
        String format = "format";

        Ouvrage livreTest = new Ouvrage(id, type, titre, nom_auteur, prenom_auteur, cote, vignette, pages, duree,
            editeur, categorie, univers, annee, langue, format);

        // livreTest.afficher();
        System.out.println("titre :"+ livreTest.titre
                    + "\nauteur :" + livreTest.nom_auteur);
       
    }

}
