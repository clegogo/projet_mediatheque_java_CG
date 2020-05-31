/*
 * BTS SIO - Option SLAM - 2020
 * Lecture / écriture des ouvrages dans la base de données
 * de la médiathèque
 */
package main;

import IHM.App;

/**
 * Lancement de l'appli médiathèque
 * 
 */      
public class Main  {   
        
    public static void main(String[] args) {
        App app = new App();
        app.gestionCatalogue();
        }
}
