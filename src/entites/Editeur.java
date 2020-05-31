/*
 * BTS SIO - Option SLAM - 2020
 * Classe pour les objets éditeurs de la médiathèque 
 */
package entites;


public class Editeur extends Source {
    final String ville;

    /**
     *Constructeur de d'éditeur
     * @param id
     * @param type
     * @param nom
     * @param ville
     */
    public Editeur(int id, String nom, String ville, String type) {
        this.id = id;
        this.nom = nom;
        this.ville = ville;
        this.type = type;
    }
    
   
    /*
     * Test local
     */
    public static void main(String[] args) {
        int id = 222;
        String nom = "unNom";
        String ville = "Une ville";
        String type = "Auteur";

        Editeur editeurTest = new Editeur(id, nom, ville, type);

        System.out.println("Editeur :"+ editeurTest.getNom()
                    + "\nville :" + editeurTest.getVille());
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
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
    * @return the type
    */
    public String getType() {
        return type;
    }

}
