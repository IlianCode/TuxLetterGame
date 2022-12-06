/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;

/**
 *
 * @author benaisil
 */
public class JeuDevineLeMotOrdre extends Jeu {

    char[] caract;
    private int nbLettresrestantes;
    private Chronometre chrono;
    int nbLetterLeft;

    private ArrayList<String> motATrouver = new  ArrayList<String>();

    private ArrayList<Letter> lettresRestantes;
    private ArrayList<Letter> lettresTrouvees;

    public JeuDevineLeMotOrdre() {
        super();
        lettresRestantes = new ArrayList<Letter>();
        lettresTrouvees = new ArrayList<Letter>();
        

    }

    @Override
    protected void démarrePartie(Partie partie, String mot) {
        ajoutEnvJeu(mot);
        //initialisation du chrono 
        int limiteChrono = 20;
        chrono = new Chronometre(limiteChrono);
        chrono.start();

        //initilisation du nombre de lettre restante dans la partie 
        nbLetterLeft = partie.getMot().length();
    }

    private Boolean tuxTrouveLettre() {
        //valeur par défaut 
        //Boolean trouvee = coli(lettres.get(i));
        for (Letter l : lettresRestantes) {
            System.out.println(l.toString());
            if (coli(l)) {
                System.out.println(coli(l));
                if (l == lettresRestantes.get(0)) {
                    lettresRestantes.remove(l);
                    lettresTrouvees.add(l);
                    env.removeObject(l);
                    System.out.print(l.getLetter());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void appliqueRegles(Partie partie) {
        int lettreIndice;
        int i = 0;

        for (Letter l : lettres) {
            if (coli(l)) {
                System.out.println(l.toString());
                if (motATrouver.get(0).equals(l.toString())) {
                    env.removeObject(l);
                    motATrouver.remove(0);

                }
                // System.out.println("in");
            } else {
                //  System.out.println("out");
            }

        }
        if (motATrouver.isEmpty()) {
            partie.setEnd(true);

        }
        if (!chrono.remainsTime()) {
            partie.setEnd(true);
            // System.out.println("\nFIN de la partie \nPas de chance!!!");
        }
        if (nbLetterLeft == 0) {
            // System.out.println("\nFIN de la partie \nBravo! C'est gagné!!! ");
            partie.setEnd(true);
        }

        if (tuxTrouveLettre()) {
            nbLetterLeft--;
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

    private void ajoutEnvJeu(String mot) {
        caract = decouppeMot(mot);

        nbLettresrestantes = getNbLettresRestantes();
        for (char c : caract) {
            
            motATrouver.add(Character.toString(c) );
        }
        //comporte les lettres qui sont mal choisi/désodre
    }

    private int getNbLettresRestantes() {
        return lettresRestantes.size();
    }

}
