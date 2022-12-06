/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author benaisil
 */
public class JeuDevineLeMotOrdre extends Jeu {

    char[] caract;
    private int nbLettresrestantes;
    private Chronometre chrono;
    int nbLetterLeft;

    private ArrayList<String> motATrouver = new ArrayList<String>();

    private ArrayList<Letter> lettresRestantes;
    private ArrayList<Letter> lettresTrouvees;

    private boolean found = false;

    public JeuDevineLeMotOrdre() throws ParserConfigurationException {
        super();
        lettresRestantes = new ArrayList<Letter>();
        lettresTrouvees = new ArrayList<Letter>();

    }

    @Override
    protected void démarrePartie(Partie partie, String mot) {
        //au debut de la partie permet de recuperer le mot dans une variable annexe
        ajoutEnvJeu(mot);
        //initialisation du chrono 
        int limiteChrono = 20;
        chrono = new Chronometre(limiteChrono);
        chrono.start();

        //initilisation du nombre de lettre restante dans la partie 
        nbLetterLeft = partie.getMot().length();
    }

    @Override
    protected void appliqueRegles(Partie partie) {
        //si le mot n'est pas trouvé
        if (!motATrouver.isEmpty()) {
            for (Letter l : lettres) {
                //si tux est en contact avec une lettre
                if (coli(l)) {
                    //si la lettre en contact avec tux est la premiere lettre 
                    //du mot a trouvé
                    if (motATrouver.get(0).equals(l.toString())) {
                        //supprimer la lettre de la room et l'enlever du mot a trouver
                        env.removeObject(l);
                        motATrouver.remove(0);

                    }
                }

            }
        }

        //si le mot est trouvé -> fin de la partie
        if (motATrouver.isEmpty()) {
            found = true;
            partie.setEnd(true);

        }
        
        //si le chrono est fini -> fin de la partie
        if (!chrono.remainsTime()) {
            partie.setEnd(true);
        }
        //s
        if (nbLetterLeft == 0) {
            // System.out.println("\nFIN de la partie \nBravo! C'est gagné!!! ");
            partie.setEnd(true);
        }

    }

    @Override
    protected void terminePartie(Partie partie) {
        if (!chrono.remainsTime()) {
            partie.setTrouvé(nbLettresrestantes);
        } else {
            partie.setTemps(chrono.timeSpent());
            partie.setTrouvé(0);
        }
    }

    //recupere le mot splité et l'envoie dans la variable motATrouvezr
    private void ajoutEnvJeu(String mot) {
        caract = splitMot(mot);

        nbLettresrestantes = getNbLettresRestantes();
        for (char c : caract) {

            motATrouver.add(Character.toString(c));
        }
        //comporte les lettres qui sont mal choisi/désodre
    }

    private int getNbLettresRestantes() {
        return lettresRestantes.size();
    }

    @Override
    protected boolean isFound(Partie partie) {
        return this.found;

    }
}
