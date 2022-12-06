/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import static com.jme3.math.FastMath.rand;
import env3d.Env;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.lwjgl.input.Keyboard;
import org.xml.sax.SAXException;

/**
 *
 * @author gladen
 */
public abstract class Jeu {

    enum MENU_VAL {
        MENU_SORTIE, MENU_CONTINUE, MENU_JOUE
    }

    final Env env;
    private Tux tux;
    final Room mainRoom;
    private final Room menuRoom;
    private Letter letter;
    private Profil profil;
    private final Dico dico;
    protected EnvTextMap menuText;                         //text (affichage des texte du jeu)
    protected ArrayList<Letter> lettres;

    public Jeu() throws ParserConfigurationException {

        // Crée un nouvel environnement
        env = new Env();

        // Instancie une Room
        mainRoom = new Room();

        // Instancie une autre Room pour les menus
        menuRoom = new Room();
        menuRoom.setTextureEast("textures/black.png");
        menuRoom.setTextureWest("textures/black.png");
        menuRoom.setTextureNorth("textures/black.png");
        menuRoom.setTextureBottom("textures/black.png");

        // Règle la camera
        env.setCameraXYZ(50, 60, 175);
        env.setCameraPitch(-20);

        // Désactive les contrôles par défaut
        env.setDefaultControl(false);

        // Instancie un profil par défaut
        profil = new Profil();

        lettres = new ArrayList<Letter>();

        // Dictionnaire
        dico = new Dico("../TuxLetterGame/src/Data/xml/dico.xml");

        // instancie le menuText
        menuText = new EnvTextMap(env);

        // Textes affichés à l'écran
        //deuxieme menu
        menuText.addText("Voulez vous ?", "Question", 130, 300);
        menuText.addText("Y. Commencer une nouvelle partie ?", "Jeu1", 180, 280);
        menuText.addText("U. Charger une partie existante ? (fonctionnalité à venir )", "Jeu2", 180, 260);
        menuText.addText("I. Revenir au menu principal ?", "Jeu3", 180, 240);
        menuText.addText("O. Quitter le jeu ?", "Jeu4", 180, 220);

        menuText.addText("Choisissez un nom de joueur : ", "NomJoueur", 200, 300);

        //menu 1
        menuText.addText("Y. Charger un profil de joueur existant ? ( fonctionnalité a venir)", "Principal1", 180, 280);
        menuText.addText("U. Créer un nouveau joueur ?", "Principal2", 180, 260);
        menuText.addText("I. Ajouter un mot au dictionnaire ?", "ajoutMot", 180, 240);
        menuText.addText("Choisissez un mot a ajouter : ", "nouveauMot", 200, 300);

        menuText.addText("O. Sortir du jeu ?", "Principal3", 180, 220);

        //ajout choix niveau ?
        menuText.addText("Veuillez choisir un niveau de difficulté : ", "choixniveau", 200, 300);
        menuText.addText("Y. Niveau 1", "Niveau1", 280, 280);
        menuText.addText("U. Niveau 2", "Niveau2", 280, 260);
        menuText.addText("I. Niveau 3", "Niveau3", 280, 240);
        menuText.addText("O. Niveau 4", "Niveau4", 280, 220);
        menuText.addText("P. Niveau 5", "Niveau5", 280, 200);

    }

    /**
     * Gère le menu principal
     *
     */
    public void execute() throws ParserConfigurationException, SAXException, IOException, TransformerException {

        MENU_VAL mainLoop;
        mainLoop = MENU_VAL.MENU_SORTIE;
        do {
            mainLoop = menuPrincipal();
        } while (mainLoop != MENU_VAL.MENU_SORTIE);
        this.env.setDisplayStr("Au revoir !", 300, 30);

        env.exit();
    }
//fonction pour recuperer la date du jour
    String getDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    // permet d'afficher le menu permettant de recuperer le nom du joueur a créer
    private String getNomJoueur() {
        String nomJoueur = "";
        menuText.getText("NomJoueur").display();
        nomJoueur = menuText.getText("NomJoueur").lire(true);
        menuText.getText("NomJoueur").clean();
        return nomJoueur;
    }

    //permet d'afficher le menu pour recuperer un nouveau mot a ajouter au dictionnaire
    private String getNewMot() {
        String newMot = "";
        menuText.getText("nouveauMot").display();
        newMot = menuText.getText("nouveauMot").lire(true);
        menuText.getText("nouveauMot").clean();
        return newMot;
    }

    //permet d'afficher le menu servant a recuperer le niveau choisis par l'utilisateur
    //soit pour ajouter un mot soit pour choisir le niveau de la partie a jouer
    int getNiveauMot() {

        //affichage choix niveau
        menuText.getText("choixniveau").display();
        menuText.getText("Niveau1").display();
        menuText.getText("Niveau2").display();
        menuText.getText("Niveau3").display();
        menuText.getText("Niveau4").display();
        menuText.getText("Niveau5").display();
        int niveau = 0;
        while (!(niveau == Keyboard.KEY_Y || niveau == Keyboard.KEY_U || niveau == Keyboard.KEY_I || niveau == Keyboard.KEY_O || niveau == Keyboard.KEY_P)) {
            niveau = env.getKey();
            env.advanceOneFrame();
        }

        menuText.getText("choixniveau").clean();
        menuText.getText("Niveau1").clean();
        menuText.getText("Niveau2").clean();
        menuText.getText("Niveau3").clean();
        menuText.getText("Niveau4").clean();
        menuText.getText("Niveau5").clean();
        switch (niveau) {
            case Keyboard.KEY_Y:
                niveau = 1;
                break;
            case Keyboard.KEY_U:
                niveau = 2;
                break;
            case Keyboard.KEY_I:
                niveau = 3;
                break;
            case Keyboard.KEY_O:
                niveau = 4;
                break;
            case Keyboard.KEY_P:
                niveau = 5;
                break;
            default:
                niveau = 1;
                break;
        }
        return niveau;
    }

    // affiche le second menu du jeu
    private MENU_VAL menuJeu() {

        MENU_VAL playTheGame;
        playTheGame = MENU_VAL.MENU_JOUE;
        Partie partie;
        do {
            // restaure la room du menu
            env.setRoom(menuRoom);
            // affiche menu
            menuText.getText("Question").display();
            menuText.getText("Jeu1").display();
            menuText.getText("Jeu2").display();
            menuText.getText("Jeu3").display();
            menuText.getText("Jeu4").display();

            // vérifie qu'une touche 1, 3 ou 4 est pressée
            int touche = 0;
            while (!(touche == Keyboard.KEY_Y  || touche == Keyboard.KEY_I || touche == Keyboard.KEY_O)) {
                touche = env.getKey();
                env.advanceOneFrame();
            }

            // nettoie l'environnement du texte
             menuText.getText("Question").clean();
            menuText.getText("Jeu1").clean();
            menuText.getText("Jeu2").clean();
            menuText.getText("Jeu3").clean();
            menuText.getText("Jeu4").clean();

            // restaure la room du jeu
            env.setRoom(menuRoom);

            // et décide quoi faire en fonction de la touche pressée
            switch (touche) {
                // -----------------------------------------
                // Touche 1 : Commencer une nouvelle partie
                // -----------------------------------------                
                case Keyboard.KEY_Y: // choisi un niveau et charge un mot depuis le dico
                    // .......... dico.******
                    // crée un nouvelle partie
                    int niveau = getNiveauMot();
                    String mot = dico.getMotDepuisListeNiveau(niveau);
                    String date = getDate();

                    partie = new Partie(date, mot, niveau);

                    menuText.addText("Votre mot a trouver est : " + partie.getMot(), "motGame", 280, 50);

                    menuText.getText("motGame").display();

                    //menuText.getText("motGame").clean();
                    //  menuText.getText("motGame").clean();
                    // joue          
                    env.setRoom(mainRoom);

                    joue(partie);
                  //  System.out.println(profil.getNom() + ".xml");
                   // profil.sauvegarder("Data/xml/profils/" + profil.getNom() + ".xml");

                    // enregistre la partie dans le profil --> enregistre le profil
                    // .......... profil.******
                    playTheGame = MENU_VAL.MENU_JOUE;
                    break;

                // -----------------------------------------
                // Touche 2 : Charger une partie existante
                // -----------------------------------------                
               /* case Keyboard.KEY_U: // charge une partie existante

                    niveau = getNiveauMot();
                    mot = dico.getMotDepuisListeNiveau(niveau);
                    date = getDate();
                    partie = new Partie(date, mot, niveau); //XXXXXXXXX
                    // Recupère le mot de la partie existante
                    // ..........
                    // joue

                    env.setRoom(mainRoom);

                    partie = new Partie(date, mot, niveau);
                    joue(partie);
                    // enregistre la partie dans le profil --> enregistre le profil
                    // .......... profil.******
                    playTheGame = MENU_VAL.MENU_JOUE;
                    break;
*/
                // -----------------------------------------
                // Touche 3 : Sortie de ce jeu
                // -----------------------------------------                
                case Keyboard.KEY_I:
                    playTheGame = MENU_VAL.MENU_CONTINUE;
                    break;

                // -----------------------------------------
                // Touche 4 : Quitter le jeu
                // -----------------------------------------                
                case Keyboard.KEY_O:
                    playTheGame = MENU_VAL.MENU_SORTIE;
            }
        } while (playTheGame == MENU_VAL.MENU_JOUE);
        return playTheGame;
    }

    //affiche le premier menu du jeu
    private MENU_VAL menuPrincipal() throws ParserConfigurationException, SAXException, IOException, TransformerException {

        MENU_VAL choix = MENU_VAL.MENU_CONTINUE;
        String nomJoueur;

        // restaure la room du menu
        env.setRoom(menuRoom);

        menuText.getText("Question").display();
        menuText.getText("Principal1").display();
        menuText.getText("Principal2").display();
        menuText.getText("ajoutMot").display();

        menuText.getText("Principal3").display();

        // vérifie qu'une touche 1, 2 ou 3 est pressée
        int touche = 0;
        while (!(touche == Keyboard.KEY_U || touche == Keyboard.KEY_I || touche == Keyboard.KEY_O)) {
            touche = env.getKey();
            env.advanceOneFrame();
        }

        menuText.getText("Question").clean();
        menuText.getText("Principal1").clean();
        menuText.getText("Principal2").clean();
        menuText.getText("Principal3").clean();
        menuText.getText("ajoutMot").clean();

        EditeurDico edit = new EditeurDico();

        // et décide quoi faire en fonction de la touche pressée
        switch (touche) {
            // -------------------------------------
            // Touche 1 : Charger un profil existant
            // -------------------------------------
            
            //code non fonctionnel pour le moment pour charger un profil
            
            /*case Keyboard.KEY_Y:
                // demande le nom du joueur existant
                nomJoueur = getNomJoueur();

                // charge le profil de ce joueur si possible
                if (profil.charge(nomJoueur)) {
                    profil = new Profil(nomJoueur);
                    profil.toString();
                    choix = menuJeu();
                } else {
                    choix = menuPrincipal();//CONTINUE;
                }
                break;*/

            // -------------------------------------
            // Touche 2 : Créer un nouveau joueur
            // -------------------------------------
            case Keyboard.KEY_U:
                //créer un nouveau joueur et passe au secon menu
                nomJoueur = getNomJoueur();
                profil = new Profil(nomJoueur, getDate());
                choix = menuJeu();
                break;

            case Keyboard.KEY_I:
                //permet d'ajouter un nouveau mot au dictionnaire
                String newMot = getNewMot();
                Integer niveau = getNiveauMot();
                edit.lireDOM("../TuxLetterGame/src/Data/xml/dico.xml");

                edit.ajouterMot(newMot, niveau);
                edit.ecrireDOM(".../TuxLetterGame/src/Data/xml/dico.xml");
                choix = menuJeu();
                break;
            // -------------------------------------
            // Touche 3 : Sortir du jeu
            // -------------------------------------
            case Keyboard.KEY_O:
                choix = MENU_VAL.MENU_SORTIE;

        }
        return choix;
    }

    //Split le mot en parametre dans un tableau 
    public char[] splitMot(String mot) {
        char motdecoupé[];
        motdecoupé = new char[mot.length()];

        for (int i = 0; i < mot.length(); i++) {
            motdecoupé[i] = mot.charAt(i);
        }

        return motdecoupé;
    }
//fonction permettant au jeu de se dérouler
    public void joue(Partie partie) {

        // Instancie un Tux et l'ajoute dans la room
        tux = new Tux(env, mainRoom);
        env.addObject(tux);
        
        
        //recuperer un mot et ajoute chacune de ses lettre dans la room
        String motAdd = partie.getMot();
        for (int i = 0; i < motAdd.length(); i++) {
            lettres.add(new Letter(motAdd.charAt(i), rand.nextInt(60), rand.nextInt(100)));
        }

        for (Letter l : lettres) {
            env.addObject(l);
        }

        
        démarrePartie(partie, motAdd);

        // Boucle de jeu
        Boolean finished;
        finished = false;
        Date startDate = new Date();

        while (!finished) {
            //affiche le mot a trouver pendant 10 seconde en bas de l'ecran
            Date endDate = new Date();
            int numSeconds = (int) ((endDate.getTime() - startDate.getTime()) / 1000);
            if (numSeconds == 10) {
                menuText.getText("motGame").clean();

            }
            
            
            Boolean col = false;

            //si on appuis sur echap fin de la partie
            if (env.getKey() == 1) {
                finished = true;
                menuText.getText("motGame").clean();
            }

            try {
                // Contrôles des déplacements de Tux (gauche, droite, ...)
                tux.deplace();
            } catch (InterruptedException ex) {
                Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Ici, on applique les regles
            appliqueRegles(partie);
            //si le mot est trouvé" entieremennt, fin de la partie
            finished = isFound(partie);

            // Fait avancer le moteur de jeu (mise à jour de l'affichage, de l'écoute des événements clavier...)
            env.advanceOneFrame();

        }
        //sassure que la room n'a plus aucune lettre
        for (Letter l : lettres) {
            env.removeObject(l);
        }
        lettres.clear();

        // Ici on peut calculer des valeurs lorsque la partie est terminée
        terminePartie(partie);

    }

    protected abstract void démarrePartie(Partie partie, String mot);

    protected abstract void appliqueRegles(Partie partie);

    protected abstract void terminePartie(Partie partie);

    protected abstract boolean isFound(Partie partie);

    //calcule la distance entre tux et la lettre en parametre
    protected double distance(Letter letter) {
        double dist;
        dist = Math.sqrt(Math.pow(letter.getX() - tux.getX(), 2) + Math.pow(letter.getY() - tux.getY(), 2) + Math.pow(letter.getZ() - tux.getZ(), 2));
        return dist;

    }

    //verifie si tux est au contact de la lettre en parametre
    public boolean coli(Letter lettre) {
        boolean res = false;
        if (distance(lettre) < tux.getScale() + lettre.getScale()) {
            res = true;
        }
        return res;
    }

}
