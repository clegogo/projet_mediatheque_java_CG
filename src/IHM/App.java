/*
 * BTS SIO - Option SLAM - 2020
 * Application médiathèque
 */
package IHM;

import entites.Createur;
import entites.Editeur;
import entites.Ouvrage;
import entites.Emprunt;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.CBDD;
import services.ListeEmprunt;
import services.CParametresStockageBDD;
import services.Catalogue;


public class App {
    public JFrameBase bureau;
    public Catalogue catalogue;
    public ListeEmprunt emprunt;
    public ArrayList[] listeClesEtrangeres;
    public ArrayList<Ouvrage> listeOuvrages;
    public ArrayList<Emprunt> listeEmprunts;
    
    
     /**
     * Ouverture de l'interface principale de la médiathèque
     */
    public void gestionCatalogue() {   
        
        emprunt = new ListeEmprunt(new CBDD(new CParametresStockageBDD("parametresBdd.properties")));
        catalogue = new Catalogue(new CBDD(new CParametresStockageBDD("parametresBdd.properties")));
        listeClesEtrangeres = this.catalogue.clesEtrangeres();
        this.listeOuvrages = this.catalogue.chargerLesOuvrages();
        this.listeEmprunts = this.emprunt.chargerEmprunts();
        
        bureau = new JFrameBase(this);
      //  bureau.app = this;    //application devient attribut de l'objet JFrame
        
    //  this.bureau.getjTableCatalogue().setRowSelectionInterval(0, 0);
        bureau.setVisible(true);
    }
    
  /*   public ArrayList<String> recupererClesEtrangeres() { 
        ArrayList<String> listeCles = this.catalogue.clesEtrangeres();
        return listeCles;
     }
   */    
    /**
     * Affiche les ouvrages passés en paramètres, dans le tableau de résultats
     * @param liste
     */
    public void afficherListejTableRes(ArrayList<Ouvrage> liste) {
        this.setRowCountjTableRes(liste.size());   // ajuste le tableau à la longueur de la liste
        
        for (int i = 0; i < liste.size(); i++) {
            this.bureau.getjTableRes().setValueAt(liste.get(i).getTitre(), i, 0);
            this.bureau.getjTableRes().setValueAt(liste.get(i).getNom_Auteur() 
                    + ", " + liste.get(i).getPrenom_Auteur(), i, 1);
            this.bureau.getjTableRes().setValueAt(liste.get(i).getType(), i, 2);
            this.bureau.getjTableRes().setValueAt(liste.get(i).getEditeur(), i, 3);
            this.bureau.getjTableRes().setValueAt(liste.get(i).getAnnee(), i, 4);
            this.bureau.getjTableRes().setValueAt(liste.get(i).getPages(), i, 5);
            this.bureau.getjTableRes().setValueAt(liste.get(i).getLangue(), i, 6);
            this.bureau.getjTableRes().setValueAt(liste.get(i).getCategorie(), i, 7);
            this.bureau.getjTableRes().setValueAt(liste.get(i).getUnivers(), i, 8);
        }
    }
    
    /**
     * Redimensionne le tableau avec le nombre de lignes passé en paramètre
     * @param rowCount
     */
    public void setRowCountjTableRes(int rowCount) {
        DefaultTableModel model = (DefaultTableModel) bureau.getjTableRes().getModel();
        model.setRowCount(rowCount);
        bureau.getjTableRes().setModel(model);
    }
    
    /**
     * Redimensionne le tableau avec le nombre de lignes passé en paramètre
     * @param rowCount
     */
    public void setRowCountjTableCat(int rowCount) {
        DefaultTableModel model = (DefaultTableModel) bureau.getjTableCatalogue().getModel();
        model.setRowCount(rowCount);
        bureau.getjTableCatalogue().setModel(model);
    }

    /**
     * Affiche tous les ouvrages du catalogue
     */
       public void afficherToutCatalogue() {
        this.bureau.getjTableCatalogue().setRowSelectionInterval(0, 0);
        this.setRowCountjTableCat(this.listeOuvrages.size());   // ajuste le tableau à la longueur de la liste

        for (int i = 0; i < listeOuvrages.size(); i++) {    //parcours la lisite de tous les ouvrages
            this.bureau.getjTableCatalogue().setValueAt(listeOuvrages.get(i).getTitre(), i, 0);
            this.bureau.getjTableCatalogue().setValueAt(listeOuvrages.get(i).getNom_Auteur() 
                    + ", " + listeOuvrages.get(i).getPrenom_Auteur(), i, 1);
            this.bureau.getjTableCatalogue().setValueAt(listeOuvrages.get(i).getType(), i, 2);
            this.bureau.getjTableCatalogue().setValueAt(listeOuvrages.get(i).getEditeur(), i, 3);
            this.bureau.getjTableCatalogue().setValueAt(listeOuvrages.get(i).getAnnee(), i, 4);
            this.bureau.getjTableCatalogue().setValueAt(listeOuvrages.get(i).getPages(), i, 5);
            this.bureau.getjTableCatalogue().setValueAt(listeOuvrages.get(i).getLangue(), i, 6);
            this.bureau.getjTableCatalogue().setValueAt(listeOuvrages.get(i).getCategorie(), i, 7);
            this.bureau.getjTableCatalogue().setValueAt(listeOuvrages.get(i).getUnivers(), i, 8);
        }
    }
       

    /**
     * Lance la méthode de recherche dans le catalogue avec les paramètres capturés dans l'interface
     * @param selectedIndex pour le champ dans lequel chercher
     * @param text pour le texte à trouver
     */
    public void rechercherDansCatalogue(int selectedIndex, String text) {
        ArrayList<Ouvrage> $resultats = catalogue.filtrerOuvrages(selectedIndex, text);
        if ($resultats.isEmpty() ) {
            this.bureau.getjLabelRien().setVisible(true);
        }else {
           this.afficherListejTableRes($resultats); 
        }
    }
    
    /**
     * Valide les informations d'un ouvrage soumis pour ajout au catalogue
     * @param listeAttributsOuvrage
     */
    public void validerOuvrage(ArrayList<String> listeAttributsOuvrage) {
        String nomCreateur = listeAttributsOuvrage.get(3);
        String prenomCreateur = listeAttributsOuvrage.get(4);
        String nomEditeur = listeAttributsOuvrage.get(5);
        //Vérifie si l'ouvrage proposé (e.g.ISBN) existe dans la BDD
        boolean ouvrageExiste = false;
        for (Ouvrage cetOuvrage : this.listeOuvrages) { 
            ouvrageExiste = (cetOuvrage.getId().equalsIgnoreCase(listeAttributsOuvrage.get(0)));
            if (ouvrageExiste) {
                this.bureau.errorMessage("Cet ouvrage est déjà dans le catalogue");
                break;
            }
        }
        if (!ouvrageExiste){
            validerCreateur(nomCreateur, prenomCreateur);
        }
    }
    
    /**
     * Confirme les paramètres concernant le créateur
     * @param nomCreateur
     * @param prenomCreateur
     */
    public void validerCreateur(String nomCreateur, String prenomCreateur) {
        //Récupère les créateurs correspondants dans la BDD, s'il y en a
        ArrayList<Createur> listeCreateurs = this.catalogue.chargerLesCreateurs(nomCreateur, prenomCreateur);
                    
        //lance le formulaire Créateur 
        this.bureau.proposerCreateur(listeCreateurs, nomCreateur, prenomCreateur);
    }
    
    /**
     * Confirme les paramètres concernant l'éditeur
     * @param nomEditeur
     */
    public void validerEditeur(String nomEditeur) {        
        //Récupère les éditeurs correspondants dans la BDD, s'il y en a
        ArrayList<Editeur> listeEditeurs = this.catalogue.chargerLesEditeurs(nomEditeur);
        
        //lance le formulaire éditeur 
        this.bureau.proposerEditeur(listeEditeurs, nomEditeur);
    }       
    
    /**
     * Lance l'ajout d'un créateur dans la BDD
     * @param listeAttributsCreateur
     * @return idCreateur
     */
    public int ajouterCreateur(ArrayList<String> listeAttributsCreateur) {
       int idCreateur = this.catalogue.insererCreateur(listeAttributsCreateur);
       return idCreateur;
   }
    
    /**
     * Lance l'ajout d'un éditeur dans la BDD
     * @param listeAttributsEditeur
     * @return idEditeur
     */
    public int ajouterEditeur(ArrayList<String> listeAttributsEditeur) {
       int idEditeur = this.catalogue.insererEditeur(listeAttributsEditeur);
       return idEditeur;
   }

   
    void ajouterOuvrage(ArrayList<String> listeAttributsOuvrage, int idCreateur, int idEditeur) {
        int res = this.catalogue.insererOuvrage(listeAttributsOuvrage, idCreateur, idEditeur);
        if (res ==3) {
            JOptionPane.showMessageDialog(null, "Done !");
        }else {
            JOptionPane.showMessageDialog(null, "Problème");
        }
        this.bureau.closeAjouter();
        
    }
    
    
    
      /**
     * Recherche d'ouvrage destinée à la supression
     * @param selectedIndex pour le champ dans lequel chercher
     * @param text pour le texte à trouver
     */
    public void rechercherOuvrageSupression(int selectedIndex, String text) {
        ArrayList<Ouvrage> $resultats = catalogue.filtrerOuvrages(selectedIndex, text);
        if ($resultats.isEmpty() ) {
            this.bureau.getjLabelRien().setVisible(true);
        }
        else
            this.afficherListejTableSuprimer($resultats);
            
    }
    
    /**
 * Supprime un ouvrage de la BDD
 * @param id
 * @param titre
 */
 public void enleverOuvrage(String id, String titre){
     int res = this.catalogue.supprimerOuvrage( id, titre);
        if (res ==1) {
            JOptionPane.showMessageDialog(null, "Done !");
        }else {
            JOptionPane.showMessageDialog(null, "Problème");
        }
 this.catalogue.supprimerOuvrage( id, titre);
   }
 
 
 /**
 *affiche les résultats de la recherche pour supression
 *@param liste
 */
 
 public void afficherListejTableSuprimer(ArrayList<Ouvrage> liste) {
       
        
        for (int i = 0; i < liste.size(); i++) {
             this.bureau.getjTableSupprimer().setValueAt(liste.get(i).getId(), i, 0); 
            this.bureau.getjTableSupprimer().setValueAt(liste.get(i).getTitre(), i, 1);
        }
    }

    void enleverOuvrage(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    /**
     * affiche les emprunts
     */
    
    public void afficheEmprunt() {
        for (int i = 0; i < listeEmprunts.size(); i++){
         this.bureau.getjTableEmprunts().setValueAt(listeEmprunts.get(i).getNom_utilisateur(), i, 0);
         this.bureau.getjTableEmprunts().setValueAt(listeEmprunts.get(i).getTitre_ouvrage(), i, 1);
         this.bureau.getjTableEmprunts().setValueAt(listeEmprunts.get(i).getDate_retrait(), i, 2);
         this.bureau.getjTableEmprunts().setValueAt(listeEmprunts.get(i).getDate_retour(), i, 3);
         this.bureau.getjTableEmprunts().setValueAt(listeEmprunts.get(i).getEmail_utilisateur(), i, 4);
        }
    }
    
}
