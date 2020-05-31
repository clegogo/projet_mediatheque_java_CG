/*
 * BTS SIO - Option SLAM - 2020
 * Classe pour les objets créateurs de la médiathèque 
 */
package entites;


public class Createur extends Source {
    final String prenom;
    final String naissance;
    

    /**
     *Constructeur de créateur
     * @param id
     * @param type
     * @param nom
     * @param prenom
     * @param naissance
     */
    public Createur(int id, String nom, String prenom, String naissance, String type) {
        this.id = id;
        this.nom = nom;
        this.prenom =prenom;
        this.naissance = naissance;
        this.type = type;
    }
    
   
    /*
     * Test local
     */
    public static void main(String[] args) {
        int id = 222;
        String nom = "unNom";
        String prenom = "Unprenom";
        String naissance = "date naissance";
        String type = "Auteur";

        Createur createurTest = new Createur(id, nom, prenom, naissance, type);

        System.out.println("Prenom :"+ createurTest.getPrenom()
                    + "\nnom :" + createurTest.getNom());
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @return the naissance
     */
    public String getNaissance() {
        return naissance;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

}
